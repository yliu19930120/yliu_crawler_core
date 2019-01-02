package com.yliu.crawler.core.parser;


import com.yliu.crawler.core.bean.Page;
import com.yliu.crawler.core.exception.EllegalityUrlCatchtException;



public interface Parser {
	
	Page parse(String input,String source) throws EllegalityUrlCatchtException;
}
