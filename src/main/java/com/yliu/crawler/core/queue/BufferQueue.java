package com.yliu.crawler.core.queue;

import java.util.List;

/**
 * 缓存队列
 * 
 * @author Yonghua Liu
 * @email liuyonghua@bly100.com
 * @date 2018年4月21日下午4:44:02
 */
public interface BufferQueue {
	
	int push(String value);
	
	int push(List<String> values);
	
	String poll();
	
	boolean isEmpty();
	
	void clear();
}
