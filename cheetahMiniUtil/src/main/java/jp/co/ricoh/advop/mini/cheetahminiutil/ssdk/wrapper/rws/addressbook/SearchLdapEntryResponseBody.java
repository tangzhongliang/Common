/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

/*
 * @since SmartSDK V2.00
 */
public class SearchLdapEntryResponseBody extends Element implements ResponseBody {

	private static final String KEY_ENTRY_NUMBER		= "entryNumber";
	private static final String KEY_ACQUIRED_NUMBER		= "acquiredNumber";
	private static final String KEY_LDAP_ENTRIES_DATA	= "ldapEntriesData";

	SearchLdapEntryResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * entryNumber (Number)
	 * @since SmartSDK V2.00
	 */
	public Integer getEntryNumber() {
		return getNumberValue(KEY_ENTRY_NUMBER);
	}

	/*
	 * acquiredNumber (Number)
	 * @since SmartSDK V2.00
	 */
	public Integer getAcquiredNumber() {
		return getNumberValue(KEY_ACQUIRED_NUMBER);
	}

	/*
	 * ldapEntriesData (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public LdapEntryArray getLdapEntriesData() {
		List<Map<String, Object>> value = getArrayValue(KEY_LDAP_ENTRIES_DATA);
		if (value == null) {
			return null;
		}
		return new LdapEntryArray(value);
	}


	/*
	 * @since SmartSDK V2.00
	 */
	public static class LdapEntryArray extends ArrayElement<LdapEntry> {

		LdapEntryArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected LdapEntry createElement(Map<String, Object> values) {
			return new LdapEntry(values);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class LdapEntry extends Element {

		private static final String KEY_ENTRY_ID			= "entryId";
		private static final String KEY_KEY_DISPLAY			= "keyDisplay";
		private static final String KEY_NAME				= "name";
		private static final String KEY_EMAIL_ADDRESS		= "emailAddress";
		private static final String KEY_FAX_NUMBER			= "faxNumber";
		private static final String KEY_COMPANY_NAME		= "companyName";
		private static final String KEY_DEPARTMENT_NAME		= "departmentName";
		private static final String KEY_ATTRIBUTE1			= "attribute1";
		private static final String KEY_ATTRIBUTE2			= "attribute2";
		private static final String KEY_ATTRIBUTE3			= "attribute3";

		LdapEntry(Map<String, Object> values) {
			super(values);
		}

		/*
		 * entryId (String)
		 * @since SmartSDK V2.00
		 */
		public String getEntryId() {
			return getStringValue(KEY_ENTRY_ID);
		}

		/*
		 * keyDisplay (String)
		 * @since SmartSDK V2.00
		 */
		public String getKeyDisplay() {
			return getStringValue(KEY_KEY_DISPLAY);
		}

		/*
		 * name (String)
		 * @since SmartSDK V2.00
		 */
		public String getName() {
			return getStringValue(KEY_NAME);
		}

		/*
		 * emailAddress (String)
		 * @since SmartSDK V2.00
		 */
		public String getEmailAddress() {
			return getStringValue(KEY_EMAIL_ADDRESS);
		}

		/*
		 * faxNumber (String)
		 * @since SmartSDK V2.00
		 */
		public String getFaxNumber() {
			return getStringValue(KEY_FAX_NUMBER);
		}

		/*
		 * companyName (String)
		 * @since SmartSDK V2.00
		 */
		public String getCompanyName() {
			return getStringValue(KEY_COMPANY_NAME);
		}

		/*
		 * departmentName (String)
		 * @since SmartSDK V2.00
		 */
		public String getDepartmentName() {
			return getStringValue(KEY_DEPARTMENT_NAME);
		}

		/*
		 * attribute1 (String)
		 * @since SmartSDK V2.00
		 */
		public String getAttribute1() {
			return getStringValue(KEY_ATTRIBUTE1);
		}

		/*
		 * attribute2 (String)
		 * @since SmartSDK V2.00
		 */
		public String getAttribute2() {
			return getStringValue(KEY_ATTRIBUTE2);
		}
		/*
		 * attribute3 (String)
		 * @since SmartSDK V2.00
		 */
		public String getAttribute3() {
			return getStringValue(KEY_ATTRIBUTE3);
		}

	}

}
