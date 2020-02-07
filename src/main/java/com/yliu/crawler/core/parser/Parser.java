package com.yliu.crawler.core.parser;


import com.yliu.crawler.core.bean.Page;
import com.yliu.crawler.core.exception.EllegalityUrlCatchtException;



public interface Parser {
	
	/**
	 * 
	 * @param input 输入内容
	 * @param source 抓取地址
	 * @return
	 * @throws EllegalityUrlCatchtException
	 */
	Page parse(String input,String source) throws EllegalityUrlCatchtException;
}
