/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;

public class JobInfo extends Element {

	private static final String KEY_JOB_ID					= "jobId";
	private static final String KEY_JOB_STATUS				= "jobStatus";
	private static final String KEY_JOB_STATUS_REASONS		= "jobStatusReasons";
	private static final String KEY_PRINTING_INFO			= "printingInfo";
	private static final String KEY_JOB_NAME				= "jobName";
	private static final String KEY_USER_NAME				= "userName";

	JobInfo(Map<String, Object> values) {
		super(values);
	}

	/*
	 * jobId (String)
	 */
	public String getJobId() {
		return getStringValue(KEY_JOB_ID);
	}

	/*
	 * jobStatus (String)
	 */
	public String getJobStatus() {
		return getStringValue(KEY_JOB_STATUS);
	}

	/*
	 * jobStatusReasons (Array[String])
	 */
	public List<String> getJobStatusReasons() {
		return getArrayValue(KEY_JOB_STATUS_REASONS);
	}

	/*
	 * printingInfo (Object)
	 */
	public PrintingInfo getPrintingInfo() {
		Map<String, Object> values = getObjectValue(KEY_PRINTING_INFO);
		if (values == null) {
			return null;
		}
		return new PrintingInfo(values);
	}

	/*
	 * jobName (String)
	 */
	public String getJobName() {
		return getStringValue(KEY_JOB_NAME);
	}

	/*
	 * userName (String)
	 */
	public String getUserName() {
		return getStringValue(KEY_USER_NAME);
	}

	public static class PrintingInfo extends Element {

		private static final String KEY_JOB_STATUS				= "jobStatus";
		private static final String KEY_JOB_STATUS_REASONS		= "jobStatusReasons";
		private static final String KEY_FILE_READ_COMPLETED		= "fileReadCompleted";
		private static final String KEY_PRINTED_COUNT			= "printedCount";
		private static final String KEY_PULL_RESULT				= "pullResult"; // SmartSDK V2.10

		PrintingInfo(Map<String, Object> values) {
			super(values);
		}

		/*
		 * jobStatus (String)
		 */
		public String getJobStatus() {
			return getStringValue(KEY_JOB_STATUS);
		}

		/*
		 * jobStatusReasons (Array[String])
		 */
		public List<String> getJobStatusReasons() {
			return getArrayValue(KEY_JOB_STATUS_REASONS);
		}

		/*
		 * fileReadCompleted (Boolean)
		 */
		public Boolean getFileReadCompleted() {
			return getBooleanValue(KEY_FILE_READ_COMPLETED);
		}

		/*
		 * printedCount (Number)
		 */
		public Integer getPrintedCount() {
			return getNumberValue(KEY_PRINTED_COUNT);
		}
		
		/*
		 * pullResult (String)
		 * @since SmartSDK V2.10
		 */
		public String getPullResult() {
			return getStringValue(KEY_PULL_RESULT);
		}
	}

}
