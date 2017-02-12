/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Map型で提供される要素を示す抽象クラス。
 */
public abstract class Element implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected final Map<String, Object> values;
	
	public Element(Map<String, Object> values) {
		if (values == null) {
			throw new NullPointerException("values must not be null.");
		}
		this.values = values;
	}
	
	public Map<String, Object> cloneValues() {
		return new LinkedHashMap<String, Object>(values);
	}
	
	@Override
	public String toString() {
		return values.toString();
	}
	
	public boolean contains(String key) {
		return values.containsKey(key);
	}
	
	public Set<String> getKeys() {
		return values.keySet();
	}
	
	public Object getValue(String key) {
		return values.get(key);
	}
	
	protected String getStringValue(String key) {
		return (String) values.get(key);
	}
	
	protected Integer getNumberValue(String key) {
		Number value = (Number) values.get(key);
		if (value == null) {
			return null;
		}
		return Integer.valueOf(value.intValue());
	}
	
	protected Boolean getBooleanValue(String key) {
		return (Boolean) values.get(key);
	}
	
	protected <T> Map<String, T> getObjectValue(String key) {
		@SuppressWarnings("unchecked")
		Map<String, T> value = (Map<String, T>) values.get(key);
		return value;
	}
	
	protected <T> List<T> getArrayValue(String key) {
		@SuppressWarnings("unchecked")
		List<T> value = (List<T>) values.get(key);
		return value;
	}
	
	protected List<Integer> getNumberArrayValue(String key) {
		List<Number> numberList = getArrayValue(key);
		if (numberList == null) {
			return null;
		}
		
		List<Integer> integerList = Utils.createElementList();
		for (Number number : numberList) {
			integerList.add(Integer.valueOf(number.intValue()));
		}
		return integerList;
	}

}
