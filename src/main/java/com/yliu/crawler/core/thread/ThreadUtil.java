package com.yliu.crawler.core.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程工具类
 * 
 * @author Yonghua Liu
 * @email liuyonghua@bly100.com
 * @date 2018年1月19日下午4:37:07
 */
public class ThreadUtil{
	
	private final static Logger log = LoggerFactory.getLogger(ThreadUtil.class);
	
	private static int corePoolSize = 5;
	
	private static ThreadPoolExecutor executor;
	
	static{
		init();
	}
	
	private static void init(){
		int num = Runtime.getRuntime().availableProcessors();
		corePoolSize = num+2;
		log.info("当前cpu核数{},初始化线程池数{}",num,corePoolSize);
		executor = 
				new ThreadPoolExecutor(num, 12, 4, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(1));

	}


	public static void execute(Runnable r){
		executor.execute(r);
	}

	public static void close(){
		executor.shutdown();
	}
	
}
