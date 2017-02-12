/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

/*
 * @since SmartSDK V1.01
 */
public class FolderIdElement extends Element {

	private static final String KEY_SHARED_FOLDER		= "sharedFolder";
	private static final String KEY_FAX_RECEIVED_FILES	= "faxReceivedFiles";
	private static final String KEY_MIN_VALUE			= "minValue";
	private static final String KEY_MAX_VALUE			= "maxValue";
	private static final String KEY_STEP				= "step";

	/*
	 * @since SmartSDK V1.01
	 */
	public FolderIdElement(Map<String, Object> values) {
		super(values);
	}

	/*
	 * sharedFolder (Boolean)
	 * @since SmartSDK V1.01
	 */
	public Boolean getSharedFolder() {
		return getBooleanValue(KEY_SHARED_FOLDER);
	}

	/*
	 * faxReceivedFiles (Boolean)
	 * @since SmartSDK V1.01
	 */
	public Boolean getFaxReceivedFiles() {
		return getBooleanValue(KEY_FAX_RECEIVED_FILES);
	}

	/*
	 * minValue (String)
	 * @since SmartSDK V1.01
	 */
	public String getMinValue() {
		return getStringValue(KEY_MIN_VALUE);
	}

	/*
	 * maxValue (String)
	 * @since SmartSDK V1.01
	 */
	public String getMaxValue() {
		return getStringValue(KEY_MAX_VALUE);
	}

	/*
	 * step (String)
	 * @since SmartSDK V1.01
	 */
	public String getStep() {
		return getStringValue(KEY_STEP);
	}

}
