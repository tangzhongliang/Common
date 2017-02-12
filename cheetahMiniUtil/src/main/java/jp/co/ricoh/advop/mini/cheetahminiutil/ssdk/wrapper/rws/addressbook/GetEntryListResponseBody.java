/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetEntryListResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_ENTRY_NUM			= "entryNum";
	private static final String KEY_ACQUIREDNUMBER	= "acquiredNumber";
	private static final String KEY_NEXTLINK			= "nextLink";
	private static final String KEY_ENTRIES_DATA		= "entriesData";
	
	GetEntryListResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * entryNum (Number)
	 */
	public Integer getEntryNum() {
		return getNumberValue(KEY_ENTRY_NUM);
	}
	
	/*
	 * acquiredNumber (Number)
	 */
	public Integer getAcquiredNumber() {
		return getNumberValue(KEY_ACQUIREDNUMBER);
	}
	
	/*
	 * nextLink (String)
	 */
	public String getNextLink() {
		return getStringValue(KEY_NEXTLINK);
	}
	
	/*
	 * entriesData (Array[Object])
	 */
	public EntryArray getEntriesData() {
		List<Map<String, Object>> value = getArrayValue(KEY_ENTRIES_DATA);
		if (value == null) {
			return null;
		}
		return new EntryArray(value);
	}
	
}
