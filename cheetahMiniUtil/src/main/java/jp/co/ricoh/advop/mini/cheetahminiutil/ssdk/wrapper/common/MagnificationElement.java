/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

/**
 * 変倍を示すクラス
 */
public class MagnificationElement extends Element {
	
	private static final String KEY_FITTING		= "fitting";
	private static final String KEY_MIN_VALUE	= "minValue";
	private static final String KEY_MAX_VALUE	= "maxValue";
	private static final String KEY_STEP		= "step";
	
	public MagnificationElement(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * fitting (Boolean)
	 */
	public Boolean getFitting() {
		return getBooleanValue(KEY_FITTING);
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