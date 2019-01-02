package com.yliu.crawler.core.queue;


import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * java队列
 * 
 * @author Yonghua Liu
 * @email yliu19930120@163.com
 * @date 2018年5月1日下午2:44:20
 */
public class JavaQueue implements BufferQueue{
	
    private Queue<String> queue;
	
	public JavaQueue() {
		super();
		this.queue = new ConcurrentLinkedQueue<String>();
	}
	@Override
	public int push(String value) {
		if("value".equals(value)||value==null){
			return 0;
		}
		queue.add(value);
		return 1;
	}
	@Override
	public int push(List<String> values) {
		values = values.stream().filter(v->!"".equals(v)&&v!=null)
				.collect(Collectors.toList());
		queue.addAll(values);
		return values.size();
	}

	@Override
	public String poll() {
		return queue.poll();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}


}
