/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetGroupListResponseBody extends Element implements ResponseBody {

	private static final String KEY_ACQUIRED_NUMBER	= "acquiredNumber";
	private static final String KEY_NEXT_LINK		= "nextLink";
	private static final String KEY_GROUPS			= "groups";

	GetGroupListResponseBody(Map<String, Object> value) {
		super(value);
	}

	/*
	 * acquiredNumber (Number)
	 */
	public Integer getAcquiredNumber() {
		return getNumberValue(KEY_ACQUIRED_NUMBER);
	}

	/*
	 * nextLink (String)
	 */
	public String getNextLink() {
		return getStringValue(KEY_NEXT_LINK);
	}

	/*
	 * groups (Array[Object])
	 */
	public GroupArray getGroups() {
		List<Map<String, Object>> value = getArrayValue(KEY_GROUPS);
		if (value == null) {
			return null;
		}
		return new GroupArray(value);
	}

}
