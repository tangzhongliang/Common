/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;

public class Group extends WritableElement {

	private static final String KEY_GROUP_ID			= "groupId";
	private static final String KEY_ENTRY_NUM_ALL		= "entryNumAll";  // SmartSDK V2.00
	private static final String KEY_ENTRY_NUM_ACL		= "entryNumAcl";  // SmartSDK V2.00
	private static final String KEY_ENTRY_NUM			= "entryNum";
	private static final String KEY_ENTRY_LIST			= "entryList";
	private static final String KEY_REGISTRATION_NUMBER	= "registrationNumber";
	private static final String KEY_KEY_DISPLAY			= "keyDisplay";
	private static final String KEY_NAME				= "name";
	private static final String KEY_PHONETIC_NAME		= "phoneticName";
	private static final String KEY_DISPLAY_PRIORITY	= "displayPriority";
	private static final String KEY_PROTECTION_CODE		= "protectionCode";
	private static final String KEY_PROTECT_TYPES		= "protectTypes";


	Group(Map<String, Object> values) {
		super(values);
	}

	/*
	 * groupId (String)
	 */
	public String getGroupId() {
		return getStringValue(KEY_GROUP_ID);
	}
	public void setGroupId(String value) {
		setStringValue(KEY_GROUP_ID, value);
	}
	public String removeGroupId() {
		return removeStringValue(KEY_GROUP_ID);
	}

	/*
	 * entryNumAll (Int)
	 * @since SmartSDK V2.00
	 */
	public Integer getEntryNumAll() {
		return getNumberValue(KEY_ENTRY_NUM_ALL);
	}
	public void setEntryNumAll(Integer value) {
		setNumberValue(KEY_ENTRY_NUM_ALL, value);
	}
	public Integer removeEntryNumAll() {
		return removeNumberValue(KEY_ENTRY_NUM_ALL);
	}

	/*
	 * entryNumAcl (Int)
	 * @since SmartSDK V2.00
	 */
	public Integer getEntryNumAcl() {
		return getNumberValue(KEY_ENTRY_NUM_ACL);
	}
	public void setEntryNumAcl(Integer value) {
		setNumberValue(KEY_ENTRY_NUM_ACL, value);
	}
	public Integer removeEntryNumAcl() {
		return removeNumberValue(KEY_ENTRY_NUM_ACL);
	}

	/*
	 * entryNum (Int)
	 */
	public Integer getEntryNum() {
		return getNumberValue(KEY_ENTRY_NUM);
	}
	public void setEntryNum(Integer value) {
		setNumberValue(KEY_ENTRY_NUM, value);
	}
	public Integer removeEntryNum() {
		return removeNumberValue(KEY_ENTRY_NUM);
	}

	/*
	 * entryList (Array[String])
	 */
	public List<String> getEntryList() {
		return getArrayValue(KEY_ENTRY_LIST);
	}
	public void setEntryList(List<String> values) {
		setArrayValue(KEY_ENTRY_LIST, values);
	}
	public List<String> removeEnrtyList() {
		return removeArrayValue(KEY_ENTRY_LIST);
	}

	/*
	 * registrationNumber (Int)
	 */
	public Integer getRegistrationNumber() {
		return getNumberValue(KEY_REGISTRATION_NUMBER);
	}
	public void setRegistrationNumber(Integer value) {
		setNumberValue(KEY_REGISTRATION_NUMBER, value);
	}
	public Integer removeRegistrationNumber() {
		return removeNumberValue(KEY_REGISTRATION_NUMBER);
	}

	/*
	 * keyDisplay (String)
	 */
	public String getKeyDisplay() {
		return getStringValue(KEY_KEY_DISPLAY);
	}
	public void setKeyDisplay(String value) {
		setStringValue(KEY_KEY_DISPLAY, value);
	}
	public String removeKeyDisplay() {
		return removeStringValue(KEY_KEY_DISPLAY);
	}

	/*
	 * name (String)
	 */
	public String getName() {
		return getStringValue(KEY_NAME);
	}
	public void setName(String value) {
		setStringValue(KEY_NAME, value);
	}
	public String removeName() {
		return removeStringValue(KEY_NAME);
	}

	/*
	 * phoneticName (String)
	 */
	public String getPhoneticName() {
		return getStringValue(KEY_PHONETIC_NAME);
	}
	public void setPhoneticName(String value) {
		setStringValue(KEY_PHONETIC_NAME, value);
	}
	public String removePhoneticName() {
		return removeStringValue(KEY_PHONETIC_NAME);
	}

	/*
	 * displayPriority (Int)
	 */
	public Integer getDisplayPriority() {
		return getNumberValue(KEY_DISPLAY_PRIORITY);
	}
	public void setDisplayPriority(Integer value) {
		setNumberValue(KEY_DISPLAY_PRIORITY, value);
	}
	public Integer removeDisplayPriority() {
		return removeNumberValue(KEY_DISPLAY_PRIORITY);
	}

	/*
	 * protectionCode (String)
	 */
	public String getProtectionCode() {
		return getStringValue(KEY_PROTECTION_CODE);
	}
	public void setProtectionCode(String value) {
		setStringValue(KEY_PROTECTION_CODE, value);
	}
	public String removeProtectionCode() {
		return removeStringValue(KEY_PROTECTION_CODE);
	}

	/*
	 * protectTypes (Object)
	 */
	public ProtectTypes getProtectTypes() {
		Map<String, Object> value = getObjectValue(KEY_PROTECT_TYPES);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_PROTECT_TYPES, value);
		}
		return new ProtectTypes(value);
	}


	public static class ProtectTypes extends WritableElement {

		private static final String KEY_FOLDER	= "folder";

		ProtectTypes(Map<String, Object> value) {
			super(value);
		}

		/*
		 * folder (Boolean)
		 */
		public Boolean getFolder() {
			return getBooleanValue(KEY_FOLDER);
		}
		public void setFolder(Boolean value) {
			setBooleanValue(KEY_FOLDER, value);
		}
		public Boolean removeFolder() {
			return removeBooleanValue(KEY_FOLDER);
		}

	}

}
