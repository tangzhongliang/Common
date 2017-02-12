/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

public class UpdateJobStatusRequestBody extends WritableElement implements RequestBody {
	
	private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
	
	private static final String KEY_JOB_STATUS			= "jobStatus";
	private static final String KEY_JOB_STATUS_DETAILS	= "jobStatusDetails";  // SmartSDK V2.00
	private static final String KEY_SCANNING_INFO		= "scanningInfo";
	private static final String KEY_VALIDATE_ONLY		= "validateOnly";
	private static final String KEY_JOB_SETTING			= "jobSetting";
	
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
	 * jobStatusDetails (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public JobStatusDetailArray getJobStatusDetails() {
		List<Map<String, Object>> value = getArrayValue(KEY_JOB_STATUS_DETAILS);
		if (value == null) {
			value = Utils.createElementList();
			setArrayValue(KEY_JOB_STATUS_DETAILS, value);
		}
		return new JobStatusDetailArray(value);
	}
//	public void setJobStatusDetails(JobStatusDetailArray value) {
//		throw new UnsupportedOperationException();
//	}
	public JobStatusDetailArray removeJobStatusDetails() {
		List<Map<String, Object>> value = removeArrayValue(KEY_JOB_STATUS_DETAILS);
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
			value = Utils.createElementMap();
			setObjectValue(KEY_SCANNING_INFO, value);
		}
		return new ScanningInfo(value);
	}
//	public void setScanningInfo(ScanningInfo value) {
//		throw new UnsupportedOperationException();
//	}
	public ScanningInfo removeScanningInfo() {
		Map<String, Object> value = removeObjectValue(KEY_SCANNING_INFO);
		if (value == null) {
			return null;
		}
		return new ScanningInfo(value);
	}
	
	/*
	 * validateOnly (Boolean)
	 */
	public Boolean getValidateOnly() {
		return getBooleanValue(KEY_VALIDATE_ONLY);
	}
	public void setValidateOnly(Boolean value) {
		setBooleanValue(KEY_VALIDATE_ONLY, value);
	}
	public Boolean removeValidateOnly() {
		return removeBooleanValue(KEY_VALIDATE_ONLY);
	}
	
	/*
	 * jobSetting (Object)
	 */
	public JobSetting getJobSetting() {
		Map<String, Object> value = getObjectValue(KEY_JOB_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_JOB_SETTING, value);
		}
		return new JobSetting(value);
	}
//	public void setJobSetting(JobSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public JobSetting removeJobSetting() {
		Map<String, Object> value = removeObjectValue(KEY_JOB_SETTING);
		if (value == null) {
			return null;
		}
		return new JobSetting(value);
	}
	
	
	/*
	 * @since SmartSDK V2.00
	 */
	public static class JobStatusDetailArray extends ArrayElement<JobStatusDetail> {
		
		JobStatusDetailArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		/*
		 * @since SmartSDK V1.01
		 */
		public boolean add(JobStatusDetail value) {
			if (value == null) {
				throw new NullPointerException("value must not be null.");
			}
			return list.add(value.cloneValues());
		}
		
		/*
		 * @since SmartSDK V1.01
		 */
		public JobStatusDetail remove(int index) {
			Map<String, Object> value = list.remove(index);
			if (value == null) {
				return null;
			}
			return createElement(value);
		}
		
		/*
		 * @since SmartSDK V1.01
		 */
		public void clear() {
			list.clear();
		}
		
		@Override
		protected JobStatusDetail createElement(Map<String, Object> values) {
			return new JobStatusDetail(values);
		}
		
	}
	
	/*
	 * @since SmartSDK V2.00
	 */
	public static class JobStatusDetail extends WritableElement {
		
		private static final String KEY_ACTION		= "action";
		
		public JobStatusDetail() {
			super(new HashMap<String, Object>());
		}
		
		JobStatusDetail(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * action (String)
		 * @since SmartSDK V2.10
		 */
		public String getAction() {
			return getStringValue(KEY_ACTION);
		}		
		public void setAction(String value) {
			setStringValue(KEY_ACTION, value);
		}
		public String removeAction() {
			return removeStringValue(KEY_ACTION);
		}
		
	}
	
	
	public static class ScanningInfo extends WritableElement {
		
		private static final String KEY_JOB_STATUS	= "jobStatus";
		private static final String KEY_REMAINING_TIME_OF_WAITING_NEXT_ORIGINAL	= "remainingTimeOfWaitingNextOriginal"; // SmartSDK V2.00
		
		ScanningInfo(Map<String, Object> values) {
			super(values);
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
		 * remainingTimeOfWaitingNextOriginal (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getRemainingTimeOfWaitingNextOriginal() {
			return getNumberValue(KEY_REMAINING_TIME_OF_WAITING_NEXT_ORIGINAL);
		}		
		public void setRemainingTimeOfWaitingNextOriginal(Integer value) {
			setNumberValue(KEY_REMAINING_TIME_OF_WAITING_NEXT_ORIGINAL, value);
		}
		public Integer removeRemainingTimeOfWaitingNextOriginal() {
			return removeNumberValue(KEY_REMAINING_TIME_OF_WAITING_NEXT_ORIGINAL);
		}
		
	}
	
}
