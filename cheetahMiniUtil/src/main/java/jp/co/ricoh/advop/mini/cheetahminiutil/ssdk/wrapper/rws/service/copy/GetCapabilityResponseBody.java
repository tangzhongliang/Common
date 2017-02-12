/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.copy;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetCapabilityResponseBody extends Element implements ResponseBody {

	private static final String KEY_JOB_SETTING_CAPABILITY	= "jobSettingCapability";

	GetCapabilityResponseBody(Map<String, Object> values) {
		super(values);
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
