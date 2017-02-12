/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

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

/*
 * @since SmartSDK V2.00
 */
public class SearchLdapEntryRequestBody extends WritableElement implements RequestBody {

	private static final String CONTENT_TYPE_JSON			= "application/json; charset=utf-8";

	private static final String KEY_LDAP_SERVER_INFO		= "ldapServerInfo";
	private static final String KEY_LDAP_ENTRIES_FILTER		= "ldapEntriesFilter";
	private static final String KEY_LDAP_SEARCH_CONDITIONS	= "ldapSearchConditions";


	public SearchLdapEntryRequestBody() {
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
	 * ldapServerInfo (Object)
	 * @since SmartSDK V2.00
	 */
	public LdapServerInfo getLdapServerInfo() {
		Map<String, Object> value = getObjectValue(KEY_LDAP_SERVER_INFO);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_LDAP_SERVER_INFO, value);
		}
		return new LdapServerInfo(value);
	}
//	public void setLdapServerInfo(LdapServerInfo value) {
//		throw new UnsupportedOperationException();
//	}
	public LdapServerInfo removeLdapServerInfo() {
		Map<String, Object> value = removeObjectValue(KEY_LDAP_SERVER_INFO);
		if (value == null) {
			return null;
		}
		return new LdapServerInfo(value);
	}

	/*
	 * ldapEntriesFilter (Array[String])
	 * @since SmartSDK V2.00
	 */
	public List<String> getLdapEntriesFilter() {
		return getArrayValue(KEY_LDAP_ENTRIES_FILTER);
	}
	public void setLdapEntriesFilter(List<String> value) {
		setArrayValue(KEY_LDAP_ENTRIES_FILTER, value);
	}
	public List<String> removeLdapEntriesFilter() {
		return removeArrayValue(KEY_LDAP_ENTRIES_FILTER);
	}

	/*
	 * ldapSearchConditions (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public LdapSearchConditionArray getLdapSearchConditions() {
		List<Map<String, Object>> value = getArrayValue(KEY_LDAP_SEARCH_CONDITIONS);
		if (value == null) {
			value = Utils.createElementList();
			setArrayValue(KEY_LDAP_SEARCH_CONDITIONS, value);
		}
		return new LdapSearchConditionArray(value);
	}
//	public void setLdapSearchConditions(LdapSearchConditionArray value) {
//		throw new UnsupportedOperationException();
//	}
	public LdapSearchConditionArray removeLdapSearchConditions() {
		List<Map<String, Object>> value = removeArrayValue(KEY_LDAP_SEARCH_CONDITIONS);
		if (value == null) {
			return null;
		}
		return new LdapSearchConditionArray(value);
	}


	/*
	 * @since SmartSDK V2.00
	 */
	public static class LdapServerInfo extends WritableElement {

		private static final String KEY_SERVER_NAME			= "serverName";
		private static final String KEY_SEARCH_BASE			= "searchBase";

		LdapServerInfo(Map<String, Object> values) {
			super(values);
		}

		/*
		 * serverName (String)
		 * @since SmartSDK V2.00
		 */
		public String getServerName() {
			return getStringValue(KEY_SERVER_NAME);
		}
		public void setServerName(String value) {
			setStringValue(KEY_SERVER_NAME, value);
		}
		public String removeServerName() {
			return removeStringValue(KEY_SERVER_NAME);
		}

		/*
		 * searchBase (String)
		 * @since SmartSDK V2.00
		 */
		public String getSearchBase() {
			return getStringValue(KEY_SEARCH_BASE);
		}
		public void setSearchBase(String value) {
			setStringValue(KEY_SEARCH_BASE, value);
		}
		public String removeSearchBase() {
			return removeStringValue(KEY_SEARCH_BASE);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class LdapSearchConditionArray extends ArrayElement<LdapSearchCondition> {

		LdapSearchConditionArray(List<Map<String, Object>> list) {
			super(list);
		}

		/*
		 * @since SmartSDK V2.00
		 */
		public boolean add(LdapSearchCondition value) {
			if (value == null) {
				throw new NullPointerException("value must not be null.");
			}
			return list.add(value.cloneValues());
		}

		/*
		 * @since SmartSDK V2.00
		 */
		public LdapSearchCondition remove(int index) {
			Map<String, Object> value = list.remove(index);
			if (value == null) {
				return null;
			}
			return createElement(value);
		}

		/*
		 * @since SmartSDK V2.00
		 */
		public void clear() {
			list.clear();
		}

		@Override
		protected LdapSearchCondition createElement(Map<String, Object> values) {
			return new LdapSearchCondition(values);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class LdapSearchCondition extends WritableElement {

		private static final String KEY_MATCHING_METHOD		= "matchingMethod";
		private static final String KEY_ITEM				= "item";
		private static final String KEY_DATA				= "data";

		public LdapSearchCondition() {
			super(new HashMap<String, Object>());
		}

		LdapSearchCondition(Map<String, Object> values) {
			super(values);
		}

		/*
		 * matchingMethod (String)
		 * @since SmartSDK V2.00
		 */
		public String getMatchingMethod() {
			return getStringValue(KEY_MATCHING_METHOD);
		}
		public void setMatchingMethod(String value) {
			setStringValue(KEY_MATCHING_METHOD, value);
		}
		public String removeMatchingMethod() {
			return removeStringValue(KEY_MATCHING_METHOD);
		}

		/*
		 * item (String)
		 * @since SmartSDK V2.00
		 */
		public String getItem() {
			return getStringValue(KEY_ITEM);
		}
		public void setItem(String value) {
			setStringValue(KEY_ITEM, value);
		}
		public String removeItem() {
			return removeStringValue(KEY_ITEM);
		}

		/*
		 * data (String)
		 * @since SmartSDK V2.00
		 */
		public String getData() {
			return getStringValue(KEY_DATA);
		}
		public void setData(String value) {
			setStringValue(KEY_DATA, value);
		}
		public String removeData() {
			return removeStringValue(KEY_DATA);
		}

	}

}
