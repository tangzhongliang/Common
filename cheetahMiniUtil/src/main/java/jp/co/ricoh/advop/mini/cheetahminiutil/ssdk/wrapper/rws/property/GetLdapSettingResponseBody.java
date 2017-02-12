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
public class GetLdapSettingResponseBody extends Element implements ResponseBody{
	
	private static final String KEY_LDAP_SEARCH = "ldapSearch";
	
	GetLdapSettingResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * ldapSearch (String)
	 * @since SmartSDK V2.12
	 */
	public String getLdapSearch() {
		return getStringValue(KEY_LDAP_SEARCH);
	}
}