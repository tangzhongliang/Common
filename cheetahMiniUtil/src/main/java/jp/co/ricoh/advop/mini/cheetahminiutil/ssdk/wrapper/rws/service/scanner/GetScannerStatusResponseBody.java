/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetScannerStatusResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_SCANNER_STATUS			= "scannerStatus";
	private static final String KEY_SCANNER_STATUS_REASONS	= "scannerStatusReasons";
	private static final String KEY_SCANNER_STATUS_DETAILS	= "scannerStatusDetails";  // SmartSDK V2.00
	private static final String KEY_REMAINING_MEMORY		= "remainingMemory";
    private static final String KEY_REMAINING_MEMORY_LOCAL	= "remainingMemoryLocal";
    private static final String KEY_MEDIA_STATUS			= "mediaStatus";           // SmartSDK V2.00
	private static final String KEY_OCCURED_ERROR_LEVEL     = "occuredErrorLevel";
	
	public GetScannerStatusResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * scannerStatus (String)
	 */
	public String getScannerStatus() {
		return getStringValue(KEY_SCANNER_STATUS);
	}
	
	/*
	 * scannerStatusReasons (Array[Object])
	 */
	public ScannerStatusReasonsArray getScannerStatusReasons() {
		List<Map<String, Object>> value = getArrayValue(KEY_SCANNER_STATUS_REASONS);
		if (value == null) {
			return null;
		}
		return new ScannerStatusReasonsArray(value);
	}
	
	/*
	 * scannerStatusDetails (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public ScannerStatusDetailArray getScannerStatusDetails() {
		List<Map<String, Object>> value = getArrayValue(KEY_SCANNER_STATUS_DETAILS);
		if (value == null) {
			return null;
		}
		return new ScannerStatusDetailArray(value);
	}
	
	/*
	 * remainingMemory (Number)
	 */
	public Integer getRemainingMemory() {
		return getNumberValue(KEY_REMAINING_MEMORY);
	}
	
	/*
	 * remainingMemoryLocal (Number)
	 */
	public Integer getRemainingMemoryLocal() {
		return getNumberValue(KEY_REMAINING_MEMORY_LOCAL);
	}
	
	/*
	 * mediaStatus (Object)
	 */
	public MediaStatus getMediaStatus() {
		Map<String, Object> mapValue = getObjectValue(KEY_MEDIA_STATUS);
		if (mapValue == null) {
			return null;
		}
		return new MediaStatus(mapValue);
	}
	
	/*
	 * occuredErrorLevel (String)
	 */
	public String getOccuredErrorLevel() {
	    return getStringValue(KEY_OCCURED_ERROR_LEVEL);
	}


	public static class ScannerStatusReasonsArray extends ArrayElement<ScannerStatusReasons> {
		
		ScannerStatusReasonsArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected ScannerStatusReasons createElement(Map<String, Object> values) {
			return new ScannerStatusReasons(values);
		}
		
	}
	
	public static class ScannerStatusReasons extends Element {
		
		private static final String KEY_SCANNER_STATUS_REASON	= "scannerStatusReason";
		private static final String KEY_SEVERITY				= "severity";
		
		ScannerStatusReasons(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * scannerStatusReason (String)
		 */
		public String getScannerStatusReason() {
			return getStringValue(KEY_SCANNER_STATUS_REASON);
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
	public static class ScannerStatusDetailArray extends ArrayElement<ScannerStatusDetail> {
		
		ScannerStatusDetailArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected ScannerStatusDetail createElement(Map<String, Object> values) {
			return new ScannerStatusDetail(values);
		}
		
	}

	/*
	 * @since SmartSDK V2.00
	 */
    public static class ScannerStatusDetail extends Element {
		
		private static final String KEY_MESSAGE					= "message";
		private static final String KEY_ADDITIONALINFO			= "additionalInfo";
		
		ScannerStatusDetail(Map<String, Object> values) {
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
		 * value (String)
		 * @since SmartSDK V2.00
		 */
		public String getValue() {
			return getStringValue(KEY_VALUE);
		}
		
	}


	/*
	 * @since SmartSDK V2.00
	 */
	public static class MediaStatus extends Element {
		
		private static final String KEY_ATTACH_STATUS			= "attachStatus";
		private static final String KEY_REMAINING_MEDIA			= "remainingMedia";
		
		MediaStatus(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * attachStatus (String)
		 * @since SmartSDK V2.00
		 */
		public String getAttachStatus() {
			return getStringValue(KEY_ATTACH_STATUS);
		}
		
		/*
		 * remainingMedia (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getRemainingMedia() {
			return getNumberValue(KEY_REMAINING_MEDIA);
		}
		
	}

}
