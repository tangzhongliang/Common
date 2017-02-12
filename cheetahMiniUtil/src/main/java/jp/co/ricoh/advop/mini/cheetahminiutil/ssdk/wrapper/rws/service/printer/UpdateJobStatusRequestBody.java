/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.HashMap;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

public class UpdateJobStatusRequestBody extends WritableElement implements RequestBody {

	private static final String CONTENT_TYPE_JSON	= "application/json; charset=utf-8";

	private static final String KEY_JOB_STATUS		= "jobStatus";
	private static final String KEY_USER_CODE		= "userCode";

	public UpdateJobStatusRequestBody() {
		super(new HashMap<String, Object>());
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_JSON;
	}

	@Override
	public String toEntityString() {
		try {
			return JsonUtils.getEncoder().encode(values);
		} catch (EncodedException e) {
		    LogC.w(e);
			return "{}";
		}
	}

	/*
	 * jobStatus (String)
	 */
	public String getJobStatus() {
		return getStringValue(KEY_JOB_STATUS);
	}
	public void setJobStatus(String value) {
		setStringValue(KEY_JOB_STATUS, value);
	}
	public String removeJobStatus() {
		return removeStringValue(KEY_JOB_STATUS);
	}

	/*
	 * userCode (String)
	 */
	public String getUserCode() {
		return getStringValue(KEY_USER_CODE);
	}
	public void setUserCode(String value) {
		setStringValue(KEY_USER_CODE, value);
	}
	public String removeUserCode() {
		return removeStringValue(KEY_USER_CODE);
	}

}
