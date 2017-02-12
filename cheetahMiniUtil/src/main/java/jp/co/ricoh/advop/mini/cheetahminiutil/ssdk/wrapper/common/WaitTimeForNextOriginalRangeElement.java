/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

/*
 * @since SmartSDK V2.10
 */
public class WaitTimeForNextOriginalRangeElement extends Element {
	
	private static final String KEY_MIN_VALUE			= "minValue";
	private static final String KEY_MAX_VALUE			= "maxValue";
	private static final String KEY_STEP				= "step";
	private static final String KEY_CONTINUOUS_WAIT		= "continuousWait";
	private static final String KEY_OFF					= "off";
	
	
	WaitTimeForNextOriginalRangeElement(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * minValue (String)
	 * @since SmartSDK V2.10
	 */
	public String getMinValue() {
		return getStringValue(KEY_MIN_VALUE);
	}
	
	/*
	 * maxValue (String)
	 * @since SmartSDK V2.10
	 */
	public String getMaxValue() {
		return getStringValue(KEY_MAX_VALUE);
	}
	
	/*
	 * step (String)
	 * @since SmartSDK V2.10
	 */
	public String getStep() {
		return getStringValue(KEY_STEP);
	}
	
	/*
	 * continuousWait (String)
	 * @since SmartSDK V2.10
	 */
	public String getContinuousWait() {
		return getStringValue(KEY_CONTINUOUS_WAIT);
	}
	
	/*
	 * off (String)
	 * @since SmartSDK V2.10
	 */
	public String getOff() {
		return getStringValue(KEY_OFF);
	}
	
}