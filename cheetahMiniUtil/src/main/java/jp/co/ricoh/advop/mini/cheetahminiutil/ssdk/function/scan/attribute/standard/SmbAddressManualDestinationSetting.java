/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

public final class SmbAddressManualDestinationSetting implements DestinationSettingItem {
	
	private static final String NAME_DESTINATION_TYPE = "destinationType";
	private static final String DESTINATION_TYPE_MANUAL = "manual";
	
	private static final String NAME_MANUAL_DESTINATION_SETTING = "manualDestinationSetting";
	
	private static final String NAME_DESTINATION_KIND = "destinationKind";
	private static final String DESTINATION_KIND_SMB = "smb";
	
	private static final String NAME_SMB_ADDRESS_INFO = "smbAddressInfo";
	
	private static final String NAME_PATH = "path";
	private static final String NAME_USER_NAME = "userName";
	private static final String NAME_PASSWORD = "password";
	
	
	private String path;
	private String userName;
	private String password;
	
	
	public SmbAddressManualDestinationSetting() {
		path = null;
		userName = null;
		password = null;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public Object getValue() {
		Map<String, Object> smbAddressInfo = new HashMap<String, Object>();
		if (path != null) {
			smbAddressInfo.put(NAME_PATH, path);
		}
		if (userName != null) {
			smbAddressInfo.put(NAME_USER_NAME, userName);
		}
		if (password != null) {
			smbAddressInfo.put(NAME_PASSWORD, password);
		}
		
		Map<String, Object> manualDestinationSetting = new HashMap<String, Object>();
		manualDestinationSetting.put(NAME_DESTINATION_KIND, DESTINATION_KIND_SMB);
		manualDestinationSetting.put(NAME_SMB_ADDRESS_INFO, smbAddressInfo);
		
		Map<String, Object> destinationSetting = new HashMap<String, Object>();
		destinationSetting.put(NAME_DESTINATION_TYPE, DESTINATION_TYPE_MANUAL);
		destinationSetting.put(NAME_MANUAL_DESTINATION_SETTING, manualDestinationSetting);
		
		return destinationSetting;
	}
	
}
