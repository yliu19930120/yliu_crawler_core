package com.yliu.crawler.core.bean;

import java.util.Date;
import java.util.Map;

public class Item {
	
	private String source;
	
    private Map<String,Object> items; //抓取数据
    
    private Date catchDate;
    
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Map<String, Object> getItems() {
		return items;
	}

	public void setItems(Map<String, Object> items) {
		this.items = items;
	}

	public Date getCatchDate() {
		return catchDate;
	}

	public void setCatchDate(Date catchDate) {
		this.catchDate = catchDate;
	}

    
}
