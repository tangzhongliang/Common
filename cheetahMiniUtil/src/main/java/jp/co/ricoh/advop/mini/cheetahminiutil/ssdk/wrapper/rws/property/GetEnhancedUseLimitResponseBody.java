/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;


/*
 * @since SmartSDK V2.12
 */
public class GetEnhancedUseLimitResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_ENHANCED_PRINT_VOLUME_USE_LIMITATION 		= "enhancedPrintVolumeUseLimitation";
	
	GetEnhancedUseLimitResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * enhancedPrintVolumeUseLimitation (Object)
	 * @since SmartSDK V2.12
	 */
	public EnhancedPrintVolumeUseLimitation getEnhancedPrintVolumeUseLimitation() {
		Map<String, Object> value = getObjectValue(KEY_ENHANCED_PRINT_VOLUME_USE_LIMITATION);
		if (value == null) {
			return null;
		}
		return new EnhancedPrintVolumeUseLimitation(value);
	}

	/*
	 * @since SmartSDK V2.12
	 */
    public static class EnhancedPrintVolumeUseLimitation extends Element {
		
		private static final String KEY_TRACKING_PERMISSION		= "trackingPermission";
		private static final String KEY_STOP_PRINTING			= "stopPrinting";
		
		EnhancedPrintVolumeUseLimitation(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * trackingPermission (String)
		 * @since SmartSDK V2.12
		 */
		public String getTrackingPermission() {
			return getStringValue(KEY_TRACKING_PERMISSION);
		}
		
		/*
		 * stopPrinting (String)
		 * @since SmartSDK V2.12
		 */
		public String getStopPrinting() {
			return getStringValue(KEY_STOP_PRINTING);
		}
	}
}