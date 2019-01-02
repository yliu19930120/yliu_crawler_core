package com.yliu.crawler.core.parser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yliu.crawler.core.bean.Item;
import com.yliu.crawler.core.bean.Page;

public class BaseParser implements Parser{

	@Override
	public Page parse(String input,String source) {
		Page page = new Page();
		Item item = new Item();
		item.setCatchDate(new Date());
		Map<String,Object> items = new HashMap<>();
		items.put("source", input);
		item.setSource(source);
		item.setItems(items);
		page.setItem(item);
		return page;
	}

}
