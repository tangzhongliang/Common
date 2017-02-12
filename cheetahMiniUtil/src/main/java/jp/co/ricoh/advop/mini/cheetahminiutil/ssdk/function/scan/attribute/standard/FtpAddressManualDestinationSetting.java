/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

public final class FtpAddressManualDestinationSetting implements DestinationSettingItem {
	
	private static final String NAME_DESTINATION_TYPE = "destinationType";
	private static final String DESTINATION_TYPE_MANUAL = "manual";
	
	private static final String NAME_MANUAL_DESTINATION_SETTING = "manualDestinationSetting";
	
	private static final String NAME_DESTINATION_KIND = "destinationKind";
	private static final String DESTINATION_KIND_FTP = "ftp";
	
	private static final String NAME_FTP_ADDRESS_INFO = "ftpAddressInfo";
	
	private static final String NAME_SERVER_NAME = "serverName";
	private static final String NAME_PATH = "path";
	private static final String NAME_USER_NAME = "userName";
	private static final String NAME_PASSWORD = "password";
	private static final String NAME_CHARACTER_CODE = "characterCode";
	private static final String NAME_PORT = "port";
	
	
	private String serverName;
	private String path;
	private String userName;
	private String password;
	private CharacterCode characterCode;
	private int port;
	
	
	public FtpAddressManualDestinationSetting() {
		serverName = null;
		path = null;
		userName = null;
		password = null;
		characterCode = null;
		port = 0;
	}
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
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

	public CharacterCode getCharacterCode() {
		return characterCode;
	}

	public void setCharacterCode(CharacterCode characterCode) {
		this.characterCode = characterCode;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	@Override
	public Object getValue() {
		Map<String, Object> ftpAddressInfo = new HashMap<String, Object>();
		if (serverName != null) {
			ftpAddressInfo.put(NAME_SERVER_NAME, serverName);
		}
		if (path != null) {
			ftpAddressInfo.put(NAME_PATH, path);
		}
		if (userName != null) {
			ftpAddressInfo.put(NAME_USER_NAME, userName);
		}
		if (password != null) {
			ftpAddressInfo.put(NAME_PASSWORD, password);
		}
		if (characterCode != null) {
			ftpAddressInfo.put(NAME_CHARACTER_CODE, characterCode.getValue());
		}
		ftpAddressInfo.put(NAME_PORT, Integer.valueOf(port));
		
		Map<String, Object> manualDestinationSetting = new HashMap<String, Object>();
		manualDestinationSetting.put(NAME_DESTINATION_KIND, DESTINATION_KIND_FTP);
		manualDestinationSetting.put(NAME_FTP_ADDRESS_INFO, ftpAddressInfo);
		
		Map<String, Object> destinationSetting = new HashMap<String, Object>();
		destinationSetting.put(NAME_DESTINATION_TYPE, DESTINATION_TYPE_MANUAL);
		destinationSetting.put(NAME_MANUAL_DESTINATION_SETTING, manualDestinationSetting);
		
		return destinationSetting;
	}
	
	
	public static enum CharacterCode {
		
		ASCII("ascii"),
		SJIS("sjis"),
		EUC("euc");
		
		private final String value;
		
		private CharacterCode(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}
}
