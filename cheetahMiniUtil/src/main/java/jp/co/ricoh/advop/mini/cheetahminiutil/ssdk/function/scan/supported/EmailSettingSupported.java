/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

public final class EmailSettingSupported {
	
	private final MaxLengthSupported supportedSubjectLength;
	private final MaxLengthSupported supportedBodyLength;
	private final MaxLengthSupported supportedSenderEntryIdLength;
	private final boolean adminAddresAsSender;
	private final List<Boolean> supportedSmimeSignatures;
	private final List<Boolean> supportedSmimeEncryptions;
	
	public EmailSettingSupported(Capability.EmailSettingCapability capability) {
		supportedSubjectLength = MaxLengthSupported.getMaxLengthSupported(capability.getSubjectLength());
		supportedBodyLength = MaxLengthSupported.getMaxLengthSupported(capability.getBodyLength());
		supportedSenderEntryIdLength = MaxLengthSupported.getMaxLengthSupported(capability.getSenderEntryIdLength());
		adminAddresAsSender = Conversions.getBooleanValue(capability.getAdminAddresAsSender(), false);
		supportedSmimeSignatures = Conversions.getList(capability.getSmimeSignatureList());
		supportedSmimeEncryptions = Conversions.getList(capability.getSmimeEncryptionList());
	}
	
	public MaxLengthSupported getSupportedSubjectLength() {
		return supportedSubjectLength;
	}
	
	public MaxLengthSupported getSupportedBodyLength() {
		return supportedBodyLength;
	}
	
	public MaxLengthSupported getSupportedSenderEntryIdLength() {
		return supportedSenderEntryIdLength;
	}
	
	public boolean isAdminAddresAsSender() {
		return adminAddresAsSender;
	}
	
	public List<Boolean> getSupportedSmimeSignatures() {
		return supportedSmimeSignatures;
	}
	
	public List<Boolean> getSupportedSmimeEncryptions() {
		return supportedSmimeEncryptions;
	}
	
}
