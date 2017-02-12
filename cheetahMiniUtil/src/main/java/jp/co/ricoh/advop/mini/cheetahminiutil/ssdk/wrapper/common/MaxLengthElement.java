/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

public class MaxLengthElement extends Element {
	
	private static final String KEY_MAX_LENGTH					= "maxLength";
	private static final String KEY_AVAILABLE_CHARACTER_TYPE	= "availableCharacterType";
	
	public MaxLengthElement(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * maxLength (Number)
	 */
	public Integer getMaxLength() {
		return getNumberValue(KEY_MAX_LENGTH);
	}
	
	/*
	 * availableCharacterType (String)
	 */
	public String getAvailableCharacterType() {
		return getStringValue(KEY_AVAILABLE_CHARACTER_TYPE);
	}
	
}