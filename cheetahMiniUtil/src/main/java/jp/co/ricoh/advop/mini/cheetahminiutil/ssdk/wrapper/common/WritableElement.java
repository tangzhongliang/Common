/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public abstract class WritableElement extends Element {
	
	public WritableElement(Map<String, Object> values) {
		super(values);
	}
	
	public void setValue(String key, Object value) {
		values.put(key, value);
	}
	public Object removeValue(String key) {
		return values.remove(key);
	}
	
	protected void setStringValue(String key, String value) {
		values.put(key, value);
	}
	protected String removeStringValue(String key) {
		return (String) values.remove(key);
	}
	
	protected void setNumberValue(String key, Integer value) {
		if (value == null) {
			values.put(key, null);
		} else {
			values.put(key, BigDecimal.valueOf(value));
		}
	}
	protected Integer removeNumberValue(String key) {
		Number prev = (Number) values.remove(key);
		if (prev == null) {
			return null;
		}
		return Integer.valueOf(prev.intValue());
	}
	
	protected void setBooleanValue(String key, Boolean value) {
		values.put(key, value);
	}
	protected Boolean removeBooleanValue(String key) {
		return (Boolean) values.remove(key);
	}
	
	protected <T> void setObjectValue(String key, Map<String, T> value) {
		values.put(key, value);
	}
	protected <T> Map<String, T> removeObjectValue(String key) {
		@SuppressWarnings("unchecked")
		Map<String, T> prev = (Map<String, T>)values.remove(key);
		return prev;
	}
	
	protected <T> void setArrayValue(String key, List<T> value) {
		values.put(key, value);
	}
	protected <T> List<T> removeArrayValue(String key) {
		@SuppressWarnings("unchecked")
		List<T> prev = (List<T>) values.remove(key);
		return prev;
	}

}
