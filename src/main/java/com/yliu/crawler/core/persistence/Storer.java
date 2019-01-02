package com.yliu.crawler.core.persistence;

import com.yliu.crawler.core.bean.ErrorPage;
import com.yliu.crawler.core.bean.Item;

public interface Storer {

	int save(Item item);
	
	int saveError(ErrorPage errorPage);
}
