package com.yliu.crawler.core.exception;

public class EllegalityUrlCatchtException extends ExceptionInInitializerError{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7435650134587268422L;

	public EllegalityUrlCatchtException(){
		
		super("非法地址的抓取");
	}
	
	public EllegalityUrlCatchtException(String url){
		
		super(String.format("非法地址的抓取|%s", url));
	}
}
