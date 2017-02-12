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

public class GetCopyStatusResponseBody extends Element implements ResponseBody  {

	private static final String KEY_COPY_STATUS				  = "copyStatus";
	private static final String KEY_COPY_STATUS_REASONS		  = "copyStatusReasons";
	private static final String KEY_COPY_STATUS_DETAILS	      = "copyStatusDetails"; // SmartSDK V2.00
	private static final String KEY_COPY_STATUS_SUPPLEMENTARY = "copyStatusSupplementary"; // SmartSDK V2.00
	private static final String KEY_OCCURED_ERROR_LEVEL       = "occuredErrorLevel";

	public GetCopyStatusResponseBody(Map<String, Object> values) {					
		super(values);
	}

	/*
	 * copyStatus (String)
	 */
	public String getCopyStatus() {
		return getStringValue(KEY_COPY_STATUS);
	}
	
	/*
	 * copyStatusReasons (Array[Object])
	 */
	public CopyStatusReasonsArray getCopyStatusReason() {
		List<Map<String, Object>> value = getArrayValue(KEY_COPY_STATUS_REASONS);
		if (value == null) {
			return null;
		}
		return new CopyStatusReasonsArray(value);
	}

	/*
	 * copyStatusDetails (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public CopyStatusDetailArray getCopyStatusDetails() {
		List<Map<String, Object>> value = getArrayValue(KEY_COPY_STATUS_DETAILS);
		if (value == null) {
			return null;
		}
		return new CopyStatusDetailArray(value);
	}
	
	/*
	 * copyStatusSupplementary (String)
	 * @since SmartSDK V2.00
	 */
	public String getCopyStatusSupplementary() {
		return getStringValue(KEY_COPY_STATUS_SUPPLEMENTARY);
	}
	
	/*
	 * occuredErrorLevel (String)
	 */
	public String getOccuredErrorLevel() {
	    return getStringValue(KEY_OCCURED_ERROR_LEVEL);
	}

	public static class CopyStatusReasonsArray extends ArrayElement<CopyStatusReasons> {

		CopyStatusReasonsArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected CopyStatusReasons createElement(Map<String, Object> values) {
			return new CopyStatusReasons(values);
		}

	}

	public static class CopyStatusReasons extends Element {

		private static final String KEY_COPY_STATUS_REASON	= "copyStatusReason";
		private static final String KEY_SEVERITY			= "severity";

		CopyStatusReasons(Map<String, Object> values) {
			super(values);
		}

		/*
		 * copyStatusReason (String)
		 */
		public String getCopyStatusReason() {
			return getStringValue(KEY_COPY_STATUS_REASON);
		}

		/*
		 * severity (String)
		 */
		public String getSeverity() {
			return getStringValue(KEY_SEVERITY);
		}

	}
	
	/*
	 * @since SmartSDK V2.00
	 */
	public static class CopyStatusDetailArray extends ArrayElement<CopyStatusDetail> {
		
		CopyStatusDetailArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected CopyStatusDetail createElement(Map<String, Object> values) {
			return new CopyStatusDetail(values);
		}
		
	}

	/*
	 * @since SmartSDK V2.00
	 */
    public static class CopyStatusDetail extends Element {
		
		private static final String KEY_MESSAGE					= "message";
		private static final String KEY_ADDITIONALINFO			= "additionalInfo";
		
		CopyStatusDetail(Map<String, Object> values) {
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
			List<Map<String, Object>> value = getArrayValue(KEY_ADDITIONALINFO);
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
}
