/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Capability;

public class GetCapabilityResponseBody extends Element implements ResponseBody {

	private static final String KEY_PDL_CAPABILITY			= "capabilities";
	private static final String KEY_JOB_SETTING_CAPABILITY	= "jobSettingCapability";

	GetCapabilityResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * capabilities (Array[String])
	 */
	public List<String> getPdlCapability() {
		return getArrayValue(KEY_PDL_CAPABILITY);
	}

	/*
	 * jobSettingCapability (Object)
	 */
	public Capability getJobSettingCapability() {
		Map<String, Object> value = getObjectValue(KEY_JOB_SETTING_CAPABILITY);
		if (value == null) {
			return null;
		}
		return new Capability(value);
	}

}
