/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public final class EmailSetting implements ScanRequestAttribute {
	
	private static final String NAME_EMAIL_SETTING = "emailSetting";
	
	private static final String NAME_SUBJECT = "subject";
	private static final String NAME_BODY = "body";
	private static final String NAME_SENDER_ENTRY_ID = "senderEntryId";
	private static final String NAME_SMIME_SIGNATURE = "smimeSignature";
	private static final String NAME_SMIME_ENCRYPTION = "smimeEncryption";
	
	
	private String subject;
	private String body;
	private String senderEntryId;
	private boolean smimeSignature;
	private boolean smimeEncryption;
	
	
	public EmailSetting() {
		subject = null;
		body = null;
		senderEntryId = null;
		smimeSignature = false;
		smimeEncryption = false;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSenderEntryId() {
		return senderEntryId;
	}

	public void setSenderEntryId(String senderEntryId) {
		this.senderEntryId = senderEntryId;
	}

	public boolean isSmimeSignature() {
		return smimeSignature;
	}

	public void setSmimeSignature(boolean smimeSignature) {
		this.smimeSignature = smimeSignature;
	}

	public boolean isSmimeEncryption() {
		return smimeEncryption;
	}

	public void setSmimeEncryption(boolean smimeEncryption) {
		this.smimeEncryption = smimeEncryption;
	}
	
	
	@Override
	public Class<?> getCategory() {
		return EmailSetting.class;
	}

	@Override
	public String getName() {
		return NAME_EMAIL_SETTING;
	}

	@Override
	public Object getValue() {
		Map<String, Object> values = new HashMap<String, Object>();
		
		if (subject != null) {
			values.put(NAME_SUBJECT, subject);
		}
		if (body != null) {
			values.put(NAME_BODY, body);
		}
		if (senderEntryId != null) {
			values.put(NAME_SENDER_ENTRY_ID, senderEntryId);
		}
		values.put(NAME_SMIME_SIGNATURE, Boolean.valueOf(smimeSignature));
		values.put(NAME_SMIME_ENCRYPTION, Boolean.valueOf(smimeEncryption));
		
		return values;
	}

}
