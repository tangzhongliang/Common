/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;

public class Entry extends WritableElement implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String KEY_ENTRY_ID			= "entryId";
	private static final String KEY_REGISTRATION_NUMBER	= "registrationNumber";
	private static final String KEY_KEY_DISPLAY			= "keyDisplay";
	private static final String KEY_NAME				= "name";
	private static final String KEY_PHONETIC_NAME		= "phoneticName";
	private static final String KEY_SELECT_LINE			= "selectLine";
	private static final String KEY_DISPLAY_PRIORITY	= "displayPriority";
	private static final String KEY_ENTRY_KIND			= "entryKind";
	private static final String KEY_USER_CODE_DATA		= "userCodeData";
	private static final String KEY_MAIL_DATA			= "mailData";
	private static final String KEY_FAX_DATA			= "faxData";
	private static final String KEY_FAX_SETTING_DATA	= "faxSettingData";
	private static final String KEY_FOLDER_DATA			= "folderData";
	private static final String KEY_PROTECTION_DATA		= "protectionData";
	private static final String KEY_SMTP_AUTH_DATA		= "smtpAuthData";
	private static final String KEY_LDAP_AUTH_DATA		= "ldapAuthData";
	private static final String KEY_FOLDER_AUTH_DATA	= "folderAuthData";
	private static final String KEY_LOCK_OUT			= "lockOut";
	private static final String KEY_CERTIFICATE_DATA	= "certificateData";    // SmartSDK V2.00
	private static final String KEY_DOC_ACL_DATA		= "docAclData";         // SmartSDK V2.00

	public Entry(Map<String, Object> values) {
		super(values);
	}

	/*
	 * entryId (String)
	 */
	public String getEntryId() {
		return getStringValue(KEY_ENTRY_ID);
	}
	public void setEntryId(String value) {
		setStringValue(KEY_ENTRY_ID, value);
	}
	public String removeEntryId() {
		return removeStringValue(KEY_ENTRY_ID);
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
	 * selectLine (String)
	 */
	public String getSelectLine() {
		return getStringValue(KEY_SELECT_LINE);
	}
	public void setSelectLine(String value) {
		setStringValue(KEY_SELECT_LINE, value);
	}
	public String removeSelectLine() {
		return removeStringValue(KEY_SELECT_LINE);
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
	 * entryKind (Array[String])
	 */
	public List<String> getEntryKind() {
		return getArrayValue(KEY_ENTRY_KIND);
	}
	public void setEntryKind(List<String> values) {
		setArrayValue(KEY_ENTRY_KIND, values);
	}
	public List<String> removeEntryKind() {
		return removeArrayValue(KEY_ENTRY_KIND);
	}

	/*
	 * userCodeData (Object)
	 */
	public UserCodeData getUserCodeData() {
		Map<String, Object> value = getObjectValue(KEY_USER_CODE_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_USER_CODE_DATA, value);
		}
		return new UserCodeData(value);
	}
	public UserCodeData removeUserCodeData() {
		Map<String, Object> value = removeObjectValue(KEY_USER_CODE_DATA);
		if (value == null) {
			return null;
		}
		return new UserCodeData(value);
	}

	/*
	 * mailData (Object)
	 */
	public MailData getMailData() {
		Map<String, Object> value = getObjectValue(KEY_MAIL_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_MAIL_DATA, value);
		}
		return new MailData(value);
	}
	public MailData removeMailData() {
		Map<String, Object> value = removeObjectValue(KEY_MAIL_DATA);
		if (value == null) {
			return null;
		}
		return new MailData(value);
	}

	/*
	 * faxData (Object)
	 */
	public FaxData getFaxData() {
		Map<String, Object> value = getObjectValue(KEY_FAX_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_FAX_DATA, value);
		}
		return new FaxData(value);
	}
	public FaxData removeFaxData() {
		Map<String, Object> value = removeObjectValue(KEY_FAX_DATA);
		if (value == null) {
			return null;
		}
		return new FaxData(value);
	}

	/*
	 * faxSettingData (Object)
	 */
	public FaxSettingData getFaxSettingData() {
		Map<String, Object> value = getObjectValue(KEY_FAX_SETTING_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_FAX_SETTING_DATA, value);
		}
		return new FaxSettingData(value);
	}
	public FaxSettingData removeFaxSettingData() {
		Map<String, Object> value = removeObjectValue(KEY_FAX_SETTING_DATA);
		if (value == null) {
			return null;
		}
		return new FaxSettingData(value);
	}

	/*
	 * folderData (Object)
	 */
	public FolderData getFolderData() {
		Map<String, Object> value = getObjectValue(KEY_FOLDER_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_FOLDER_DATA, value);
		}
		return new FolderData(value);
	}
	public FolderData removeFolderData() {
		Map<String, Object> value = removeObjectValue(KEY_FOLDER_DATA);
		if (value == null) {
			return null;
		}
		return new FolderData(value);
	}

	/*
	 * protectionData (Object)
	 */
	public ProtectionData getProtectionData() {
		Map<String, Object> value = getObjectValue(KEY_PROTECTION_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_PROTECTION_DATA, value);
		}
		return new ProtectionData(value);
	}
	public ProtectionData removeProtectionData() {
		Map<String, Object> value = removeObjectValue(KEY_PROTECTION_DATA);
		if (value == null) {
			return null;
		}
		return new ProtectionData(value);
	}

	/*
	 * smtpAuthData (Object)
	 */
	public SmtpAuthData getSmtpAuthData() {
		Map<String, Object> value = getObjectValue(KEY_SMTP_AUTH_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_SMTP_AUTH_DATA, value);
		}
		return new SmtpAuthData(value);
	}
	public SmtpAuthData removeSmtpAuthData() {
		Map<String, Object> value = removeObjectValue(KEY_SMTP_AUTH_DATA);
		if (value == null) {
			return null;
		}
		return new SmtpAuthData(value);
	}

	/*
	 * ldapAuthData (Object)
	 */
	public LdapAuthData getLdapAuthData() {
		Map<String, Object> value = getObjectValue(KEY_LDAP_AUTH_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_LDAP_AUTH_DATA, value);
		}
		return new LdapAuthData(value);
	}
	public LdapAuthData removeLdapAuthData() {
		Map<String, Object> value = removeObjectValue(KEY_LDAP_AUTH_DATA);
		if (value == null) {
			return null;
		}
		return new LdapAuthData(value);
	}

	/*
	 * folderAuthData (Object)
	 */
	public FolderAuthData getFolderAuthData() {
		Map<String, Object> value = getObjectValue(KEY_FOLDER_AUTH_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_FOLDER_AUTH_DATA, value);
		}
		return new FolderAuthData(value);
	}
	public FolderAuthData removeFolderAuthData() {
		Map<String, Object> value = removeObjectValue(KEY_FOLDER_AUTH_DATA);
		if (value == null) {
			return null;
		}
		return new FolderAuthData(value);
	}

	/*
	 * lockOut (Boolean)
	 */
	public Boolean getLockOut() {
		return getBooleanValue(KEY_LOCK_OUT);
	}
	public void setLockOut(Boolean value) {
		setBooleanValue(KEY_LOCK_OUT, value);
	}
	public Boolean removeLockOut() {
		return removeBooleanValue(KEY_LOCK_OUT);
	}

	/*
	 * certificateData (Object)
	 * @since SmartSDK V2.00
	 */
	public CertificateData getCertificateData() {
		Map<String, Object> value = getObjectValue(KEY_CERTIFICATE_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_CERTIFICATE_DATA, value);
		}
		return new CertificateData(value);
	}
	public CertificateData removeCertificateData() {
		Map<String, Object> value = removeObjectValue(KEY_CERTIFICATE_DATA);
		if (value == null) {
			return null;
		}
		return new CertificateData(value);
	}

	/*
	 * docAclData (Object)
	 * @since SmartSDK V2.00
	 */
	public DocAclData getDocAclData() {
		Map<String, Object> value = getObjectValue(KEY_DOC_ACL_DATA);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_DOC_ACL_DATA, value);
		}
		return new DocAclData(value);
	}
	public DocAclData removeDocAclData() {
		Map<String, Object> value = removeObjectValue(KEY_DOC_ACL_DATA);
		if (value == null) {
			return null;
		}
		return new DocAclData(value);
	}


	public static class UserCodeData extends WritableElement {

		private static final String KEY_LOGIN_USER_NAME	= "loginUserName";
		private static final String KEY_LOGIN_PASSWORD	= "loginPassword";

		UserCodeData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * loginUserName (String)
		 */
		public String getLoginUserName() {
			return getStringValue(KEY_LOGIN_USER_NAME);
		}
		public void setLoginUserName(String value) {
			setStringValue(KEY_LOGIN_USER_NAME, value);
		}
		public String removeLoginUserName() {
			return removeStringValue(KEY_LOGIN_USER_NAME);
		}

		/*
		 * loginPassword (String)
		 */
		public String getLoginPassword() {
			return getStringValue(KEY_LOGIN_PASSWORD);
		}
		public void setLoginPassword(String value) {
			setStringValue(KEY_LOGIN_PASSWORD, value);
		}
		public String removeLoginPassword() {
			return removeStringValue(KEY_LOGIN_PASSWORD);
		}

	}

	public static class MailData extends WritableElement {

		private static final String KEY_MAIL_ADDRESS	= "mailAddress";
		private static final String KEY_SEND_VIA_SMTP	= "sendViaSmtp";
		private static final String KEY_USAGE			= "usage";


		MailData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * mailAddress (String)
		 */
		public String getMailAddress() {
			return getStringValue(KEY_MAIL_ADDRESS);
		}
		public void setMailAddress(String value) {
			setStringValue(KEY_MAIL_ADDRESS, value);
		}
		public String removeMailAddress() {
			return removeStringValue(KEY_MAIL_ADDRESS);
		}

		/*
		 * sendViaSmtp (Boolean)
		 */
		public Boolean getSendViaSmtp() {
			return getBooleanValue(KEY_SEND_VIA_SMTP);
		}
		public void setSendViaSmtp(Boolean value) {
			setBooleanValue(KEY_SEND_VIA_SMTP, value);
		}
		public Boolean removeSendViaSmtp() {
			return removeBooleanValue(KEY_SEND_VIA_SMTP);
		}

		/*
		 * usage (Array[String])
		 */
		public List<String> getUsage() {
			return getArrayValue(KEY_USAGE);
		}
		public void setUsage(List<String> values) {
			setArrayValue(KEY_USAGE, values);
		}
		public List<String> removeUsage() {
			return removeArrayValue(KEY_USAGE);
		}

	}

	public static class FaxData extends WritableElement {

		private static final String KEY_FAX_NUMBER				= "faxNumber";
		private static final String KEY_INTERNATIONAL_TX_MODE	= "internationalTxMode";
		private static final String KEY_SUB_CODE				= "subCode";
		private static final String KEY_SID						= "sid";
		private static final String KEY_SEP_CODE				= "sepCode";
		private static final String KEY_PWD						= "pwd";
		private static final String KEY_UUI						= "uui";
		private static final String KEY_SUB_ADDRESS				= "subAddress";

		FaxData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * faxNumber (String)
		 */
		public String getFaxNumber() {
			return getStringValue(KEY_FAX_NUMBER);
		}
		public void setFaxNumber(String value) {
			setStringValue(KEY_FAX_NUMBER, value);
		}
		public String removeFaxNumber() {
			return removeStringValue(KEY_FAX_NUMBER);
		}
		/*
		 * internationalTxMode (Boolean)
		 */
		public Boolean getInternationalTxMode() {
			return getBooleanValue(KEY_INTERNATIONAL_TX_MODE);
		}
		public void setInternationalTxMode(Boolean value) {
			setBooleanValue(KEY_INTERNATIONAL_TX_MODE, value);
		}
		public Boolean removeInternationalTxMode() {
			return removeBooleanValue(KEY_INTERNATIONAL_TX_MODE);
		}

		/*
		 * subCode (String)
		 */
		public String getSubCode() {
			return getStringValue(KEY_SUB_CODE);
		}
		public void setSubCode(String value) {
			setStringValue(KEY_SUB_CODE, value);
		}
		public String removeSubCode() {
			return removeStringValue(KEY_SUB_CODE);
		}

		/*
		 * sid (String)
		 */
		public String getSid() {
			return getStringValue(KEY_SID);
		}
		public void setSid(String value) {
			setStringValue(KEY_SID, value);
		}
		public String removeSid() {
			return removeStringValue(KEY_SID);
		}

		/*
		 * sepCode (String)
		 */
		public String getSepCode() {
			return getStringValue(KEY_SEP_CODE);
		}
		public void setSepCode(String value) {
			setStringValue(KEY_SEP_CODE, value);
		}
		public String removeSepCode() {
			return removeStringValue(KEY_SEP_CODE);
		}

		/*
		 * pwd (String)
		 */
		public String getPwd() {
			return getStringValue(KEY_PWD);
		}
		public void setPwd(String value) {
			setStringValue(KEY_PWD, value);
		}
		public String removePwd() {
			return removeStringValue(KEY_PWD);
		}

		/*
		 * uui (String)
		 */
		public String getUui() {
			return getStringValue(KEY_UUI);
		}
		public void setUui(String value) {
			setStringValue(KEY_UUI, value);
		}
		public String removeUui() {
			return removeStringValue(KEY_UUI);
		}

		/*
		 * subAddress (String)
		 */
		public String getSubAddress() {
			return getStringValue(KEY_SUB_ADDRESS);
		}
		public void setSubAddress(String value) {
			setStringValue(KEY_SUB_ADDRESS, value);
		}
		public String removeSubAddress() {
			return removeStringValue(KEY_SUB_ADDRESS);
		}

	}

	public static class FaxSettingData extends WritableElement {

		private static final String KEY_FAX_HEADER			= "faxHeader";
		private static final String KEY_LABEL_INSERTION		= "labelInsertion";
		private static final String KEY_LABEL				= "label";
		private static final String KEY_STANDARD_MESSAGE	= "standardMessage";

		FaxSettingData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * faxHeader (String)
		 */
		public String getFaxHeader() {
			return getStringValue(KEY_FAX_HEADER);
		}
		public void setFaxHeader(String value) {
			setStringValue(KEY_FAX_HEADER, value);
		}
		public String removeFaxHeader() {
			return removeStringValue(KEY_FAX_HEADER);
		}

		/*
		 * labelInsertion (Boolean)
		 */
		public Boolean getLabelInsertion() {
			return getBooleanValue(KEY_LABEL_INSERTION);
		}
		public void setLabelInsertion(Boolean value) {
			setBooleanValue(KEY_LABEL_INSERTION, value);
		}
		public Boolean removeLabelInsertion() {
			return removeBooleanValue(KEY_LABEL_INSERTION);
		}

		/*
		 * label (String)
		 */
		public String getLabel() {
			return getStringValue(KEY_LABEL);
		}
		public void setLabel(String value) {
			setStringValue(KEY_LABEL, value);
		}
		public String removeLabel() {
			return removeStringValue(KEY_LABEL);
		}

		/*
		 * standardMessage (String)
		 */
		public String getStandardMessage() {
			return getStringValue(KEY_STANDARD_MESSAGE);
		}
		public void setStandardMessage(String value) {
			setStringValue(KEY_STANDARD_MESSAGE, value);
		}
		public String removeStandardMessage() {
			return removeStringValue(KEY_STANDARD_MESSAGE);
		}

	}

	public static class FolderData extends WritableElement {

		private static final String KEY_PROTOCOL_TYPE		= "protocolType";
		private static final String KEY_SERVER_NAME			= "serverName";
		private static final String KEY_JAPANESE_CHAR_CODE	= "japaneseCharCode";
		private static final String KEY_PORT_NUMBER			= "portNumber";
		private static final String KEY_PATH				= "path";
		private static final String KEY_CONNECTION_TYPE		= "connectionType";

		FolderData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * protocolType (String)
		 */
		public String getProtocolType() {
			return getStringValue(KEY_PROTOCOL_TYPE);
		}
		public void setProtocolType(String value) {
			setStringValue(KEY_PROTOCOL_TYPE, value);
		}
		public String removeProtocolType() {
			return removeStringValue(KEY_PROTOCOL_TYPE);
		}

		/*
		 * serverName (String)
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
		 * japaneseCharCode (String)
		 */
		public String getJapaneseCharCode() {
			return getStringValue(KEY_JAPANESE_CHAR_CODE);
		}
		public void setJapaneseCharCode(String value) {
			setStringValue(KEY_JAPANESE_CHAR_CODE, value);
		}
		public String removeJapaneseCharCode() {
			return removeStringValue(KEY_JAPANESE_CHAR_CODE);
		}

		/*
		 * portNumber (Int)
		 */
		public Integer getPortNumber() {
			return getNumberValue(KEY_PORT_NUMBER);
		}
		public void setPortNumber(Integer value) {
			setNumberValue(KEY_PORT_NUMBER, value);
		}
		public Integer removePortNumber() {
			return removeNumberValue(KEY_PORT_NUMBER);
		}

		/*
		 * path (String)
		 */
		public String getPath() {
			return getStringValue(KEY_PATH);
		}
		public void setPath(String value) {
			setStringValue(KEY_PATH, value);
		}
		public String removePath() {
			return removeStringValue(KEY_PATH);
		}

		/*
		 * connectionType (String)
		 */
		public String getConnectionType() {
			return getStringValue(KEY_CONNECTION_TYPE);
		}
		public void setConnectionType(String value) {
			setStringValue(KEY_CONNECTION_TYPE, value);
		}
		public String removeConnectionType() {
			return removeStringValue(KEY_CONNECTION_TYPE);
		}

	}

	public static class ProtectionData extends WritableElement {

		private static final String KEY_PROTECTION_CODE			= "protectionCode";
		private static final String KEY_PROTECTION_OBJECT_DATA	= "protectionObjectData";

		ProtectionData(Map<String, Object> values) {
			super(values);
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
		 * protectionObjectData (Object)
		 */
		public ProtectionObjectData getProtectionObjectData() {
			Map<String, Object> value = getObjectValue(KEY_PROTECTION_OBJECT_DATA);
			if (value == null) {
				value = Utils.createElementMap();
				setObjectValue(KEY_PROTECTION_OBJECT_DATA, value);
			}
			return new ProtectionObjectData(value);
		}
		public ProtectionObjectData removeProtectionObjectData() {
			Map<String, Object> value = removeObjectValue(KEY_PROTECTION_OBJECT_DATA);
			if (value == null) {
				return null;
			}
			return new ProtectionObjectData(value);
		}

	}

	public static class ProtectionObjectData extends WritableElement {

		private static final String KEY_SENDER				= "sender";
		private static final String KEY_FOLDER_DESTINATION	= "folderDestination";

		ProtectionObjectData(Map<String, Object> value) {
			super(value);
		}

		/*
		 * sender (Boolean)
		 */
		public Boolean getSender() {
			return getBooleanValue(KEY_SENDER);
		}
		public void setSender(Boolean value) {
			setBooleanValue(KEY_SENDER, value);
		}
		public Boolean removeSender() {
			return removeBooleanValue(KEY_SENDER);
		}

		/*
		 * folderDestination (Boolean)
		 */
		public Boolean getFolderDestination() {
			return getBooleanValue(KEY_FOLDER_DESTINATION);
		}
		public void setFolderDestination(Boolean value) {
			setBooleanValue(KEY_FOLDER_DESTINATION, value);
		}
		public Boolean removeFolderDestination() {
			return removeBooleanValue(KEY_FOLDER_DESTINATION);
		}

	}

	public static class SmtpAuthData extends WritableElement {

		private static final String KEY_SMTP_SELECT		= "smtpSelect";
		private static final String KEY_LOGIN_USER_NAME	= "loginUserName";
		private static final String KEY_LOGIN_PASSWORD	= "loginPassword";

		SmtpAuthData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * smtpSelect (Boolean)
		 */
		public Boolean getSmtpSelect() {
			return getBooleanValue(KEY_SMTP_SELECT);
		}
		public void setSmtpSelect(Boolean value) {
			setBooleanValue(KEY_SMTP_SELECT, value);
		}
		public Boolean removeSmtpSelect() {
			return removeBooleanValue(KEY_SMTP_SELECT);
		}

		/*
		 * loginUserName (String)
		 */
		public String getLoginUserName() {
			return getStringValue(KEY_LOGIN_USER_NAME);
		}
		public void setLoginUserName(String value) {
			setStringValue(KEY_LOGIN_USER_NAME, value);
		}
		public String removeLoginUserName() {
			return removeStringValue(KEY_LOGIN_USER_NAME);
		}

		/*
		 * loginPassword (String)
		 */
		public String getLoginPassword() {
			return getStringValue(KEY_LOGIN_PASSWORD);
		}
		public void setLoginPassword(String value) {
			setStringValue(KEY_LOGIN_PASSWORD, value);
		}
		public String removeLoginPassword() {
			return removeStringValue(KEY_LOGIN_PASSWORD);
		}

	}

	public static class LdapAuthData extends WritableElement {

		private static final String KEY_LDAP_SELECT		= "ldapSelect";
		private static final String KEY_LOGIN_USER_NAME	= "loginUserName";
		private static final String KEY_LOGIN_PASSWORD	= "loginPassword";

		LdapAuthData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * ldapSelect (Boolean)
		 */
		public Boolean getLdapSelect() {
			return getBooleanValue(KEY_LDAP_SELECT);
		}
		public void setLdapSelect(Boolean value) {
			setBooleanValue(KEY_LDAP_SELECT, value);
		}
		public Boolean removeLdapSelect() {
			return removeBooleanValue(KEY_LDAP_SELECT);
		}

		/*
		 * loginUserName (String)
		 */
		public String getLoginUserName() {
			return getStringValue(KEY_LOGIN_USER_NAME);
		}
		public void setLoginUserName(String value) {
			setStringValue(KEY_LOGIN_USER_NAME, value);
		}
		public String removeLoginUserName() {
			return removeStringValue(KEY_LOGIN_USER_NAME);
		}

		/*
		 * loginPassword (String)
		 */
		public String getLoginPassword() {
			return getStringValue(KEY_LOGIN_PASSWORD);
		}
		public void setLoginPassword(String value) {
			setStringValue(KEY_LOGIN_PASSWORD, value);
		}
		public String removeLoginPassword() {
			return removeStringValue(KEY_LOGIN_PASSWORD);
		}

	}

	public static class FolderAuthData extends WritableElement {

		private static final String KEY_FOLDER_SELECT	= "folderSelect";
		private static final String KEY_LOGIN_USER_NAME	= "loginUserName";
		private static final String KEY_LOGIN_PASSWORD	= "loginPassword";

		FolderAuthData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * folderSelect (Boolean)
		 */
		public Boolean getFolderSelect() {
			return getBooleanValue(KEY_FOLDER_SELECT);
		}
		public void setFolderSelect(Boolean value) {
			setBooleanValue(KEY_FOLDER_SELECT, value);
		}
		public Boolean removeFolderSelect() {
			return removeBooleanValue(KEY_FOLDER_SELECT);
		}

		/*
		 * loginUserName (String)
		 */
		public String getLoginUserName() {
			return getStringValue(KEY_LOGIN_USER_NAME);
		}
		public void setLoginUserName(String value) {
			setStringValue(KEY_LOGIN_USER_NAME, value);
		}
		public String removeLoginUserName() {
			return removeStringValue(KEY_LOGIN_USER_NAME);
		}

		/*
		 * loginPassword (String)
		 */
		public String getLoginPassword() {
			return getStringValue(KEY_LOGIN_PASSWORD);
		}
		public void setLoginPassword(String value) {
			setStringValue(KEY_LOGIN_PASSWORD, value);
		}
		public String removeLoginPassword() {
			return removeStringValue(KEY_LOGIN_PASSWORD);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class CertificateData extends WritableElement {

		private static final String KEY_ENCRYPTION		= "encryption";

		CertificateData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * encryption (String)
		 * @since SmartSDK V2.00
		 */
		public String getEncryption() {
			return getStringValue(KEY_ENCRYPTION);
		}
		public void setEncryption(String value) {
			setStringValue(KEY_ENCRYPTION, value);
		}
		public String removeEncryption() {
			return removeStringValue(KEY_ENCRYPTION);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class DocAclData extends WritableElement {

		private static final String KEY_ENTRY_CNT			= "entryCnt";
		private static final String KEY_ACL_INFO_ENTRIES	= "aclInfoEntries";

		DocAclData(Map<String, Object> values) {
			super(values);
		}

		/*
		 * entryCnt (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getEntryCnt() {
			return getNumberValue(KEY_ENTRY_CNT);
		}
		public void setEntryCnt(Integer value) {
			setNumberValue(KEY_ENTRY_CNT, value);
		}
		public Integer removeEntryCnt() {
			return removeNumberValue(KEY_ENTRY_CNT);
		}

		/*
		 * aclInfoEntries (Array[Object])
		 * @since SmartSDK V2.00
		 */
		public AclInfoEntries getAclInfoEntries() {
			List<Map<String, Object>> value = getArrayValue(KEY_ACL_INFO_ENTRIES);
			if (value == null) {
				value = Utils.createElementList();
				setArrayValue(KEY_ACL_INFO_ENTRIES, value);
			}
			return new AclInfoEntries(value);
		}
		public AclInfoEntries removeAclInfoEntries() {
			List<Map<String, Object>> value = removeArrayValue(KEY_ACL_INFO_ENTRIES);
			if (value == null) {
				return null;
			}
			return new AclInfoEntries(value);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class AclInfoEntries extends ArrayElement<AclInfoEntry> {

		AclInfoEntries(List<Map<String, Object>> list) {
			super(list);
		}

		public boolean add(AclInfoEntry value) {
			if (value == null) {
				throw new NullPointerException("value must not be null.");
			}
			return list.add(value.cloneValues());
		}

		public AclInfoEntry remove(int index) {
			Map<String, Object> value = list.remove(index);
			if (value == null) {
				return null;
			}
			return createElement(value);
		}

		public void clear() {
			list.clear();
		}

		@Override
		protected AclInfoEntry createElement(Map<String, Object> values) {
			return new AclInfoEntry(values);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class AclInfoEntry extends WritableElement {

		private static final String KEY_ENTRY_ID			= "entryId";
		private static final String KEY_AUTHORITY			= "authority";

		public AclInfoEntry() {
			super(new HashMap<String, Object>());
		}

		AclInfoEntry(Map<String, Object> values) {
			super(values);
		}

		/*
		 * entryId (String)
		 * @since SmartSDK V2.00
		 */
		public String getEntryId() {
			return getStringValue(KEY_ENTRY_ID);
		}
		public void setEntryId(String value) {
			setStringValue(KEY_ENTRY_ID, value);
		}
		public String removeEntryId() {
			return removeStringValue(KEY_ENTRY_ID);
		}

		/*
		 * authority (String)
		 * @since SmartSDK V2.00
		 */
		public String getAuthority() {
			return getStringValue(KEY_AUTHORITY);
		}
		public void setAuthority(String value) {
			setStringValue(KEY_AUTHORITY, value);
		}
		public String removeAuthority() {
			return removeStringValue(KEY_AUTHORITY);
		}

	}

}