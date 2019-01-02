package com.yliu.crawler.core.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yliu.crawler.core.bean.ErrorPage;
import com.yliu.crawler.core.bean.Item;
import com.yliu.crawler.core.utils.JsonUtil;

public class BaseStorer implements Storer{
	
	private static Logger log = LoggerFactory.getLogger(BaseStorer.class);
	
	@Override
	public int save(Item item) {
		log.info("抓取结果{}",JsonUtil.toJson(item));
		return 0;
	}

	@Override
	public int saveError(ErrorPage errorPage) {
		log.info("{}抓取错误,error|{}",errorPage.getUrl(),errorPage.getError());
		return 0;
	}

}
