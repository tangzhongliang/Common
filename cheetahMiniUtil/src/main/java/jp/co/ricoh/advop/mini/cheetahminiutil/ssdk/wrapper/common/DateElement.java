/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

/**
 * 日付情報または時刻情報を表すクラス。
 */
public class DateElement extends Element {
	
	private static final String KEY_DATE_TYPE = "dateType";
	private static final String KEY_TIME_TYPE = "timeType";
	
	public DateElement(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * dateType (String)
	 */
	public String getDateType() {
		return getStringValue(KEY_DATE_TYPE);
	}
	
	/*
	 * timeType (String)
	 */
	public String getTimeType() {
		return getStringValue(KEY_TIME_TYPE);
	}
	
}