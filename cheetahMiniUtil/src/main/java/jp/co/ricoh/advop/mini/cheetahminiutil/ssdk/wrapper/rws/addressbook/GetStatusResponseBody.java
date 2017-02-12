/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetStatusResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_ENTRY_NUM		= "entryNum";
	private static final String KEY_TAG_NUM			= "tagNum";
	private static final String KEY_GROUP_NUM		= "groupNum";
	private static final String KEY_GENERATION_NUM	= "generationNum";  // SmartSDK V2.00
	private static final String KEY_ALL_CHANGE_NUM	= "allChangeNum";   // SmartSDK V2.00
	
	GetStatusResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * entryNum (Int)
	 */
	public Integer getEntryNum() {
		return getNumberValue(KEY_ENTRY_NUM);
	}
	
	/*
	 * tagNum (Int)
	 */
	public Integer getTagNum() {
		return getNumberValue(KEY_TAG_NUM);
	}
	
	/*
	 * groupNum (Int)
	 */
	public Integer getGroupNum() {
		return getNumberValue(KEY_GROUP_NUM);
	}
	
	/*
	 * generationNum (String)
	 * @since SmartSDK V2.00
	 */
	public String getGenerationNum() {
		return getStringValue(KEY_GENERATION_NUM);
	}
	
	/*
	 * allChangeNum (String)
	 * @since SmartSDK V2.00
	 */
	public String getAllChangeNum() {
		return getStringValue(KEY_ALL_CHANGE_NUM);
	}
	
}
