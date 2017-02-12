/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.copy;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;

/*
 * @since SmartSDK V2.00
 */
public class JobInfo extends Element {

	private static final String KEY_JOB_ID			= "jobId";
	private static final String KEY_JOB_STATUS		= "jobStatus";

	JobInfo(Map<String, Object> values) {
		super(values);
	}

	/*
	 * jobId (String)
	 * @since SmartSDK V2.00
	 */
	public String getJobId() {
		return getStringValue(KEY_JOB_ID);
	}

	/*
	 * jobStatus (String)
	 * @since SmartSDK V2.00
	 */
	public String getJobStatus() {
		return getStringValue(KEY_JOB_STATUS);
	}

}
