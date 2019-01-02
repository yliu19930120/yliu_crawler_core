package com.yliu.crawler.core.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
			
	public static String toJson(Object obj){
		return JSON.toJSON(obj).toString();
	}
}
