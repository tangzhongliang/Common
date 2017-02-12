/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetJobStatusResponseBody extends JobInfo implements ResponseBody {

	private static final String KEY_EVENT_DETAIL		= "eventDetail";
	
	public GetJobStatusResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * eventDetail (String)
	 */
	public String getEventDetail(){		
		return getStringValue(KEY_EVENT_DETAIL);
	}
}
