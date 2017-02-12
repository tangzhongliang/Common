/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetCapabilityResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_APPLICATION		= "application";
	private static final String KEY_ADDRESS_MAX_NUM	= "addressMaxNum";
	private static final String KEY_USER_MAX_NUM	= "userMaxNum";
	private static final String KEY_TAG_MAX_NUM		= "tagMaxNum";
	private static final String KEY_GROUP_MAX_NUM	= "groupMaxNum";
	
	GetCapabilityResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * application (Array[String])
	 */
	public List<String> getApplication() {
		return getArrayValue(KEY_APPLICATION);
	}
	
	/*
	 * addressMaxNum (Int)
	 */
	public Integer getAddressMaxNum() {
		return getNumberValue(KEY_ADDRESS_MAX_NUM);
	}
	
	/*
	 * userMaxNum (Int)
	 */
	public Integer getUserMaxNum() {
		return getNumberValue(KEY_USER_MAX_NUM);
	}
	
	/*
	 * tagMaxNum (Int)
	 */
	public Integer getTagMaxNum() {
		return getNumberValue(KEY_TAG_MAX_NUM);
	}
	
	/*
	 * groupMaxNum (Int)
	 */
	public Integer getGroupMaxNum() {
		return getNumberValue(KEY_GROUP_MAX_NUM);
	}

}
