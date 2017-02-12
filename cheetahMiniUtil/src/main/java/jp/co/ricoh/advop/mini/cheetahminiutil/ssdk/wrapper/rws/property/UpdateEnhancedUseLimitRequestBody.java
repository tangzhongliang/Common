/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.log.Logger;

/*
 * @since SmartSDK V2.12
 */
public class UpdateEnhancedUseLimitRequestBody extends WritableElement implements RequestBody{
	private static final String CONTENT_TYPE_JSON               = "application/json; charset=utf-8";
	
	private static final String KEY_ENHANCED_PRINT_VOLUME_USE_LIMITATION   = "enhancedPrintVolumeUseLimitation";
	
	/**
     * Define the prefix for log information with abbreviation package and class name
     */
    private final static String PREFIX = "property:UpdateEnhanceReq:";
	
	public UpdateEnhancedUseLimitRequestBody() {
		super(new HashMap<String, Object>());
	}

	public UpdateEnhancedUseLimitRequestBody(Map<String, Object> values) {
		super(values);
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
            Logger.warn(Utils.getTagName(), PREFIX + e.toString());
            return "{}";
        }
	}
	
	/*
	 * enhancedPrintVolumeUseLimitation (Object)
	 */
	public EnhancedPrintVolumeUseLimitation getEnhancedPrintVolumeUseLimitation() {
		Map<String, Object> value = getObjectValue(KEY_ENHANCED_PRINT_VOLUME_USE_LIMITATION);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_ENHANCED_PRINT_VOLUME_USE_LIMITATION, value);
        }
        return new EnhancedPrintVolumeUseLimitation(value);
	} 
	public EnhancedPrintVolumeUseLimitation removeEnhancedPrintVolumeUseLimitation() {
		Map<String, Object> value = removeObjectValue(KEY_ENHANCED_PRINT_VOLUME_USE_LIMITATION);
        if (value == null) {
        	return null;
        }
        
        return new EnhancedPrintVolumeUseLimitation(value);
	}
	
	public static class EnhancedPrintVolumeUseLimitation extends WritableElement {
		private static final String KEY_TRACKING_PERMISSION     = "trackingPermission";
		private static final String KEY_STOP_PRINTING           = "stopPrinting";

		EnhancedPrintVolumeUseLimitation(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * trackingPermission (String)
		 */
		public String getTrackingPermission() {
			return getStringValue(KEY_TRACKING_PERMISSION);
		}
		public void setTrackingPermission(String value) {
			setStringValue(KEY_TRACKING_PERMISSION, value);
		}
		public String removeTrackingPermission() {
			return removeStringValue(KEY_TRACKING_PERMISSION);
		}
		
		/*
		 * stopPrinting (String)
		 */
		public String getStopPrinting() {
			return getStringValue(KEY_STOP_PRINTING);
		}
		public void setStopPrinting(String value) {
			setStringValue(KEY_STOP_PRINTING, value);
		}
		public String removeStopPrinting() {
			return removeStringValue(KEY_STOP_PRINTING);
		}
		
		
		
	
	}	

}
