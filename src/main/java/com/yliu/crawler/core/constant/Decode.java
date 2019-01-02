package com.yliu.crawler.core.constant;

public enum Decode {

	GBK("GBK"),
	UTF8("UTF-8");
	
	private String code;

	private Decode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
