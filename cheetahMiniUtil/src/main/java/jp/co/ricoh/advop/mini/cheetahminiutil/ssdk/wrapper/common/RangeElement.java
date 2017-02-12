/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

public class RangeElement extends Element {
	
	private static final String KEY_MIN_VALUE	= "minValue";
	private static final String KEY_MAX_VALUE	= "maxValue";
	private static final String KEY_STEP		= "step";
	
	public RangeElement(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * minValue (String)
	 */
	public String getMinValue() {
		return getStringValue(KEY_MIN_VALUE);
	}
	
	/*
	 * maxValue (String)
	 */
	public String getMaxValue() {
		return getStringValue(KEY_MAX_VALUE);
	}
	
	/*
	 * step (String)
	 */
	public String getStep() {
		return getStringValue(KEY_STEP);
	}
	
}