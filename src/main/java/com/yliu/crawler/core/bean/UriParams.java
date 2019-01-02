package com.yliu.crawler.core.bean;

import java.util.LinkedHashMap;

public class UriParams extends LinkedHashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4439566694698578838L;

	public String getParams(){
		StringBuffer params = new StringBuffer("");
		int index = 0;
		for(java.util.Map.Entry<String, Object> entry:this.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue()==null?"":entry.getValue().toString();
			params.append(key);
			params.append("=");
			params.append(value);
			if(index!=this.size()-1){
				params.append("&");
			}
			index++;
		}
		return params.toString();
	}
}
