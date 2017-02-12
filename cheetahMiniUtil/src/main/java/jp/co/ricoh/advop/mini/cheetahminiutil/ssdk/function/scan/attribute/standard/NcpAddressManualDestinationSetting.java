/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

public final class NcpAddressManualDestinationSetting implements DestinationSettingItem {
	
	private static final String NAME_DESTINATION_TYPE = "destinationType";
	private static final String DESTINATION_TYPE_MANUAL = "manual";
	
	private static final String NAME_MANUAL_DESTINATION_SETTING = "manualDestinationSetting";
	
	private static final String NAME_DESTINATION_KIND = "destinationKind";
	private static final String DESTINATION_KIND_NCP = "ncp";
	
	private static final String NAME_NCP_ADDRESS_INFO = "ncpAddressInfo";
	
	private static final String NAME_PATH = "path";
	private static final String NAME_USER_NAME = "userName";
	private static final String NAME_PASSWORD = "password";
	private static final String NAME_CONNECTION_TYPE = "connectionType";
	
	
	private String path;
	private String userName;
	private String password;
	private ConnectionType connectionType;
	
	
	public NcpAddressManualDestinationSetting() {
		path = null;
		userName = null;
		password = null;
		connectionType = null;
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

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}


	@Override
	public Object getValue() {
		Map<String, Object> ncpAddressInfo = new HashMap<String, Object>();
		if (path != null) {
			ncpAddressInfo.put(NAME_PATH, path);
		}
		if (userName != null) {
			ncpAddressInfo.put(NAME_USER_NAME, userName);
		}
		if (password != null) {
			ncpAddressInfo.put(NAME_PASSWORD, password);
		}
		if (connectionType != null) {
			ncpAddressInfo.put(NAME_CONNECTION_TYPE, connectionType.getValue());
		}
		
		Map<String, Object> manualDestinationSetting = new HashMap<String, Object>();
		manualDestinationSetting.put(NAME_DESTINATION_KIND, DESTINATION_KIND_NCP);
		manualDestinationSetting.put(NAME_NCP_ADDRESS_INFO, ncpAddressInfo);
		
		Map<String, Object> destinationSetting = new HashMap<String, Object>();
		destinationSetting.put(NAME_DESTINATION_TYPE, DESTINATION_TYPE_MANUAL);
		destinationSetting.put(NAME_MANUAL_DESTINATION_SETTING, manualDestinationSetting);
		
		return destinationSetting;
	}
	
	
	public static enum ConnectionType {
		
		NDS("nds"),
		BINDERY("bindery");
		
		private final String value;
		
		private ConnectionType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}
	
}
