package com.yliu.crawler.core.bean;

import java.util.Date;

public class ErrorPage {
		
	private String url;
	private String error;
	private Date date;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
