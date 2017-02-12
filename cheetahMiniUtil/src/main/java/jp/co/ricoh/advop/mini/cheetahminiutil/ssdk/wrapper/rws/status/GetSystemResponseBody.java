/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.status;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetSystemResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_STATUS	= "status";
	private static final String KEY_REASON	= "reason";
	
	GetSystemResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * status (String)
	 */
	public String getStatus() {
		return getStringValue(KEY_STATUS);
	}
	
	/*
	 * reason (String)
	 */
	public String getReason() {
		return getStringValue(KEY_REASON);
	}
	
}
