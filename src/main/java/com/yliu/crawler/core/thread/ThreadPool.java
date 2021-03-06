package com.yliu.crawler.core.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPool {

	private final static Logger log = LoggerFactory.getLogger(ThreadPool.class);
	
	private  int corePoolSize = 1;
	
	private static ThreadPoolExecutor executor;
	
	public ThreadPool(int poolSize){
		init(poolSize);
	}
	
	private void init(int num){
		log.info("初始化线程池数{}",num);
		executor = 
				new ThreadPoolExecutor(num, 12, 4, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(1));
	}


	public void execute(Runnable r){
		executor.execute(r);
	}

	public void close(){
		executor.shutdown();
	}
}
