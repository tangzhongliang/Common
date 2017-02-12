/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

public final class AddressbookDestinationSetting implements DestinationSettingItem {

	private static final String NAME_DESTINATION_TYPE = "destinationType";
	private static final String DESTINATION_TYPE_ADDRESSBOOK = "addressbook";
	
	private static final String NAME_ADDRESSBOOK_DESTINATION_SETTING = "addressbookDestinationSetting";
	
	private static final String NAME_DESTINATION_KIND = "destinationKind";
	private static final String NAME_ENTRY_ID = "entryId";
	private static final String NAME_REGISTRATION_NO = "registrationNo";
	private static final String NAME_MAIL_TO_CC_BCC = "mailToCcBcc";

	private DestinationKind destinationKind;
	private String entryId;
	private int registrationNo;
	private MailToCcBcc mailToCcBcc;
	
	
	public AddressbookDestinationSetting() {
		destinationKind = null;
		entryId = null;
		registrationNo = 0;
		mailToCcBcc = null;
	}
	
	public DestinationKind getDestinationKind() {
		return destinationKind;
	}

	public void setDestinationKind(DestinationKind destinationKind) {
		this.destinationKind = destinationKind;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	public MailToCcBcc getMailToCcBcc() {
		return mailToCcBcc;
	}

	public void setMailToCcBcc(MailToCcBcc mailToCcBcc) {
		this.mailToCcBcc = mailToCcBcc;
	}

	
	@Override
	public Object getValue() {
		Map<String, Object> addressbookDestinationSetting = new HashMap<String, Object>();
		if (destinationKind != null) {
			addressbookDestinationSetting.put(NAME_DESTINATION_KIND, destinationKind.getValue());
		}
		if (entryId != null) {
			addressbookDestinationSetting.put(NAME_ENTRY_ID, entryId);
		}
		addressbookDestinationSetting.put(NAME_REGISTRATION_NO, registrationNo);
		if (mailToCcBcc != null) {
			addressbookDestinationSetting.put(NAME_MAIL_TO_CC_BCC, mailToCcBcc.getValue());
		}
		
		Map<String, Object> destinationSetting = new HashMap<String, Object>();
		destinationSetting.put(NAME_DESTINATION_TYPE, DESTINATION_TYPE_ADDRESSBOOK);
		destinationSetting.put(NAME_ADDRESSBOOK_DESTINATION_SETTING, addressbookDestinationSetting);
		
		return destinationSetting;
	}
	
	
	public static enum DestinationKind {
		
		MAIL("mail"),
		FOLDER("folder");
		
		private final String value;
		
		private DestinationKind(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
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
