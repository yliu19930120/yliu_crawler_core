package com.yliu.crawler.core.bean;

import java.util.LinkedHashMap;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class MapHeaders extends LinkedHashMap<String, String>{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1967204994561581519L;

	public Header[] getHeaders(){
		Header[] headers = new Header[this.size()];
		int index = 0;
		for(java.util.Map.Entry<String, String> entry:this.entrySet()){
			BasicHeader header = new BasicHeader(entry.getKey(), entry.getValue());
			headers[index++] = header;
		}
		return headers;
	}
}
