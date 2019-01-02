package com.yliu.crawler.core.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPool {

	private final static Logger log = LoggerFactory.getLogger(ThreadPool.class);
	
	private  int corePoolSize = 1;
	
	private ExecutorService pool;
	
	public ThreadPool(int poolSize){
		init(poolSize);
	}
	
	private void init(int num){
		log.info("初始化线程池数{}",num);
		pool = Executors.newFixedThreadPool(corePoolSize);
	}


	public void execute(Runnable r){
		pool.execute(r);
	}

	public void close(){
		pool.shutdown();
	}
}
