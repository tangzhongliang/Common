/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

public final class MailAddressManualDestinationSetting implements DestinationSettingItem {
	
	private static final String NAME_DESTINATION_TYPE = "destinationType";
	private static final String DESTINATION_TYPE_MANUAL = "manual";
	
	private static final String NAME_MANUAL_DESTINATION_SETTING = "manualDestinationSetting";
	
	private static final String NAME_DESTINATION_KIND = "destinationKind";
	private static final String DESTINATION_KIND_MAIL = "mail";
	
	private static final String NAME_MAIL_ADDRESS_INFO = "mailAddressInfo";
	
	private static final String NAME_MAIL_ADDRESS = "mailAddress";
	private static final String NAME_MAIL_TO_CC_BCC = "mailToCcBcc";
	
	
	private String mailAddress;
	private MailToCcBcc mailToCcBcc;
	
	
	public MailAddressManualDestinationSetting() {
		mailAddress = null;
		mailToCcBcc = null;
	}
	
	public String getMailAddress() {
		return mailAddress;
	}


	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}


	public MailToCcBcc getMailToCcBcc() {
		return mailToCcBcc;
	}


	public void setMailToCcBcc(MailToCcBcc mailToCcBcc) {
		this.mailToCcBcc = mailToCcBcc;
	}


	@Override
	public Object getValue() {
		Map<String, Object> mailAddressInfo = new HashMap<String, Object>();
		if (mailAddress != null) {
			mailAddressInfo.put(NAME_MAIL_ADDRESS, mailAddress);
		}
		if (mailToCcBcc != null) {
			mailAddressInfo.put(NAME_MAIL_TO_CC_BCC, mailToCcBcc.getValue());
		}
		
		Map<String, Object> manualDestinationSetting = new HashMap<String, Object>();
		manualDestinationSetting.put(NAME_DESTINATION_KIND, DESTINATION_KIND_MAIL);
		manualDestinationSetting.put(NAME_MAIL_ADDRESS_INFO, mailAddressInfo);
		
		Map<String, Object> destinationSetting = new HashMap<String, Object>();
		destinationSetting.put(NAME_DESTINATION_TYPE, DESTINATION_TYPE_MANUAL);
		destinationSetting.put(NAME_MANUAL_DESTINATION_SETTING, manualDestinationSetting);
		
		return destinationSetting;
	}

	
	public static enum MailToCcBcc {
		
		TO("to"),
		CC("cc"),
		BCC("bcc");
		
		private final String value;
		
		private MailToCcBcc(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}

}
