package com.yliu.crawler.core.crawler;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yliu.crawler.core.bean.ErrorPage;
import com.yliu.crawler.core.bean.MapHeaders;
import com.yliu.crawler.core.bean.Page;
import com.yliu.crawler.core.bean.UriParams;
import com.yliu.crawler.core.constant.Decode;
import com.yliu.crawler.core.exception.EllegalityUrlCatchtException;
import com.yliu.crawler.core.parser.BaseParser;
import com.yliu.crawler.core.parser.Parser;
import com.yliu.crawler.core.persistence.BaseStorer;
import com.yliu.crawler.core.persistence.Storer;
import com.yliu.crawler.core.queue.BufferQueue;
import com.yliu.crawler.core.queue.JavaQueue;
import com.yliu.crawler.core.request.Request;
import com.yliu.crawler.core.thread.ThreadUtil;

public class Crawler {

	private static Logger log = LoggerFactory.getLogger(Crawler.class);
	
	private AtomicInteger targetRun;
	
	private MapHeaders headers;
	private String charset;
	private UriParams uriParams;
	private Map<String, Object> jsonParams;
	private Parser parser;
	private Queue<String> pagesQueue;
	private BufferQueue queue;
	private Storer storer;
	private Parser targetParser;
	private int reconnecNum;

	private Crawler() {
		this.headers = new MapHeaders();
	}

	public static Crawler create() {
		Crawler crawler = new Crawler();
		Parser parser = new BaseParser();
		crawler.setCharset(Decode.UTF8.getCode());
		crawler.setStorer(new BaseStorer());
		crawler.setParser(parser);
		crawler.setQueue(new JavaQueue());
		crawler.setTargetParser(parser);
		crawler.pagesQueue = new LinkedList<>();
		crawler.targetRun = new AtomicInteger(0);
		return crawler;
	}

	public Crawler addUrl(String url) {
		pagesQueue.add(url);
		return this;
	}

	public Crawler addUrl(String... urls) {
		for (String url : urls) {
			pagesQueue.add(url);
		}
		return this;
	}

	public Crawler setParser(Parser parser) {
		this.parser = parser;
		return this;
	}

	public Crawler setStorer(Storer storer) {
		this.storer = storer;
		return this;
	}

	public Crawler setHeaders(MapHeaders headers) {
		headers.putAll(headers);
		return this;
	}

	public Crawler setCharset(String charset) {
		this.charset = charset;
		return this;
	}

	public Crawler setTargetParser(Parser parser) {
		this.targetParser = parser;
		return this;
	}

	public Crawler setQueue(BufferQueue queue) {
		this.queue = queue;
		return this;
	}

	public Crawler withSession() {
		headers.put("Cookie", String.format("JSESSIONID=%s", Request.buildSessionId()));
		return this;
	}

	public void run() {
		run(1);
	}

	public void run(int threadNums) {
		while (!pagesQueue.isEmpty()) {
			String url = pagesQueue.poll();
			String res = get(url);
			Page page = null;
			try {
				page = parser.parse(res, url);
			} catch (Exception e) {
				log.error("列表页[{}]解析失败 e|{}", url, e);
				saveErrorMsg(url, e.toString());
			}
			if (page.getNext() != null) {
				pagesQueue.add(page.getNext());
			}
			// 有详情页，抓取详情页
			if (page.getTargets()!= null) {
				List<String> targets = page.getTargets();
				queue.push(targets);
				log.info("队列中添加 {}",targets.size());
				if(targetRun.get()==0){
					catchTarget(threadNums);
					targetRun.incrementAndGet();
				}
			}

		}
		log.info("所有url翻页完毕");
	}

	private String get(String url) {
		String res = null;
		try {
			res = Request.get(url, headers.getHeaders(), charset);
		} catch (Exception e) {
			log.warn("连接超时,10s 后重新抓取");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			reconnecNum++;
			if (reconnecNum < 5) {
				res = get(url);
			}
		}
		return res;
	}

	private void catchTarget(int threadNums) {
		Runnable r = catchRunnable();
		int i = 0;
		while (i < threadNums) {
			ThreadUtil.execute(r);
			i++;
		}
	}

	private Runnable catchRunnable() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (!queue.isEmpty()) {
					String target = queue.poll();
					String detail = get(target);
					Page detailPage = null;
					try {
						detailPage = targetParser.parse(detail, target);
					} catch (EllegalityUrlCatchtException e) {
						log.warn("非法地址|[{}]", target, e);
						continue;
					} catch (Exception e) {
						log.error("详情页[{}]解析失败 e|{}", target, e);
						saveErrorMsg(target, e.toString());
						continue;
					}

					if (detailPage.getItem() != null) {
						storer.save(detailPage.getItem());
					}
					log.info("抓取url|[{}]完毕", target);
				}
				log.info("队列中抓取完毕 ");
			}
		};
		return runnable;
	}
	
	private void saveErrorMsg(String url, String msg) {
		ErrorPage errorPage = new ErrorPage();
		errorPage.setDate(new Date());
		errorPage.setError(msg);
		errorPage.setUrl(url);
		storer.saveError(errorPage);
	};
}
