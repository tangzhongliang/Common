/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.HashMap;
import java.util.Map;

/**
 * WebAPI通信でのリクエストのクエリを示すクラス。
 */
public final class RequestQuery {
	
	private final Map<String, String> queries;
	
	public RequestQuery() {
		queries = new HashMap<String, String>();
	}
	
	public Map<String, String> getQueries() {
		return new HashMap<String, String>(queries);
	}
	
	public String get(String key) {
		return queries.get(key);
	}
	
	public void put(String key, String value) {
		queries.put(key, value);
	}
	
	public void putAll(Map<String, String> values) {
		queries.putAll(values);
	}
	
	public String remove(String key) {
		return queries.remove(key);
	}
	
	public void clear() {
		queries.clear();
	}

}
