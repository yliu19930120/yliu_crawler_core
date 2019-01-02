package com.yliu.crawler.core.bean;

import java.util.List;

public class Page {
	
	private Item item;
	/**
	 * 解析出下一页的url
	 */
	private String next;
	/**
	 * 抓取的目标页集合
	 */
	private List<String> targets;
	
	
	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(List<String> targets) {
		this.targets = targets;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
	
	
}
