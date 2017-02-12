/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.copy;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetJobStatusResponseBody extends Element implements ResponseBody  {

	private static final String KEY_EVENT_DETAIL 		= "eventDetail";
	private static final String KEY_JOB_ID				= "jobId";
	private static final String KEY_JOB_STATUS			= "jobStatus";
	private static final String KEY_JOB_STATUS_REASONS	= "jobStatusReasons";
	private static final String KEY_JOB_STATUS_DETAILS	= "jobStatusDetails";	// SmartSDK V2.00
	private static final String KEY_SCANNING_INFO		= "scanningInfo";
	private static final String KEY_PRINTING_INFO		= "printingInfo";
	private static final String KEY_VALIDATE_ONLY		= "validateOnly";
	private static final String KEY_JOB_SETTING			= "jobSetting";
	private static final String KEY_OCCURED_ERROR_LEVEL	= "occuredErrorLevel";
			
	public GetJobStatusResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * eventDetail (String)
	 */
	public String getEventDetail(){		
		return getStringValue(KEY_EVENT_DETAIL);
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
	 * jobStatusDetails (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public JobStatusDetailArray getJobStatusDetails() {
		List<Map<String, Object>> value = getArrayValue(KEY_JOB_STATUS_DETAILS);
		if (value == null) {
			return null;
		}
		return new JobStatusDetailArray(value);
	}
	
	/*
	 * scanningInfo (Object)
	 */
	public ScanningInfo getScanningInfo() {
		Map<String, Object> value = getObjectValue(KEY_SCANNING_INFO);
		if (value == null) {
			return null;
		}
		return new ScanningInfo(value);
	}

	/*
	 * printingInfo (Object)
	 */
	public PrintingInfo getPrintingInfo() {
		Map<String, Object> value = getObjectValue(KEY_PRINTING_INFO);
		if (value == null) {
			return null;
		}
		return new PrintingInfo(value);
	}

	/*
	 * validateOnly (Boolean)
	 */
	public Boolean getValidateOnly() {
		return getBooleanValue(KEY_VALIDATE_ONLY);
	}

	/*
	 * jobSetting (Object)
	 */
	public JobSetting getJobSetting() {
		Map<String, Object> value = getObjectValue(KEY_JOB_SETTING);
		if (value == null) {
			return null;
		}
		return new JobSetting(value);
	}

	/*
	 * occuredErrorLevel (String)
	 */
	public String getOccuredErrorLevel() {
		return getStringValue(KEY_OCCURED_ERROR_LEVEL);
	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class JobStatusDetailArray extends ArrayElement<JobStatusDetail> {
		
		JobStatusDetailArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected JobStatusDetail createElement(Map<String, Object> values) {
			return new JobStatusDetail(values);
		}
	}
	
	/*
	 * @since SmartSDK V2.00
	 */
	public static class JobStatusDetail extends Element {
		
		private static final String KEY_MESSAGE					= "message";
		private static final String KEY_ADDITIONAL_INFO			= "additionalInfo";
		
		JobStatusDetail(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * message (String)
		 * @since SmartSDK V2.00
		 */
		public String getMessage() {
			return getStringValue(KEY_MESSAGE);
		}
		
		/*
		 * additionalInfo (Array[Object])
		 * @since SmartSDK V2.00
		 */
		public AdditionalInfoArray getAdditionalInfo() {
			List<Map<String, Object>> value = getArrayValue(KEY_ADDITIONAL_INFO);
			if (value == null) {
				return null;
			}
			return new AdditionalInfoArray(value);
		}
		
	}
	
	/*
	 * @since SmartSDK V2.00
	 */
	public static class AdditionalInfoArray extends ArrayElement<AdditionalInfo> {
		
		AdditionalInfoArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected AdditionalInfo createElement(Map<String, Object> values) {
			return new AdditionalInfo(values);
		}
		
	}
	
	/*
	 * @since SmartSDK V2.00
	 */
	public static class AdditionalInfo extends Element {
		
		private static final String KEY_ID						= "id";
		private static final String KEY_VALUE					= "value";
		
		AdditionalInfo(Map<String, Object> values) {
			super(values);
		}

		/*
		 * id (String)
		 * @since SmartSDK V2.00
		 */
		public String getId() {
			return getStringValue(KEY_ID);
		}
		
		/*
		 * value (Array[String])
		 * @since SmartSDK V2.00
		 */
		public List<String> getValue() {
			return getArrayValue(KEY_VALUE);
		}
		
	}
	
	
	public static class ScanningInfo extends Element {
		private static final String KEY_JOB_STATUS				= "jobStatus";
		private static final String KEY_JOB_STATUS_REASONS		= "jobStatusReasons";
		private static final String KEY_SCANNED_COUNT			= "scannedCount";
		private static final String KEY_SCANNED_SHEET_COUNT		= "scannedSheetCount";  					// SmartSDK V2.00
		private static final String KEY_RESET_ORIGINAL_COUNT	= "resetOriginalCount";
		
		ScanningInfo(Map<String, Object> values) {
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
		 * scannedCount (Number)
		 */
		public Integer getScannedCount() {
			return getNumberValue(KEY_SCANNED_COUNT);
		}
		
		/*
		 * scannedSheetCount (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getScannedSheetCount() {
			return getNumberValue(KEY_SCANNED_SHEET_COUNT);
		}		

		/*
		 * resetOriginalCount (Number)
		 */
		public Integer getResetOriginalCount() {
			return getNumberValue(KEY_RESET_ORIGINAL_COUNT);
		}
	}

	public static class PrintingInfo extends Element {
		private static final String KEY_JOB_STATUS				= "jobStatus";
		private static final String KEY_JOB_STATUS_REASONS		= "jobStatusReasons";
		private static final String KEY_PRINTED_COPIES			= "printedCopies";
		private static final String KEY_TRAY_IN_USE				= "trayInUse";

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
		 * printedCopies (Number)
		 */
		public Integer getPrintedCopies() {
			return getNumberValue(KEY_PRINTED_COPIES);
		}

		/*
		 * trayInUse (String)
		 */
		public String getTrayInUse() {
			return getStringValue(KEY_TRAY_IN_USE);
		}
	}

}
