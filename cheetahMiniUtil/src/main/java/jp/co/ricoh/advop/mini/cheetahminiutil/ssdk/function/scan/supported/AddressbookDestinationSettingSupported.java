/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

import java.util.List;

public final class AddressbookDestinationSettingSupported {
	
	private final List<String> supportedDestinationKinds;
	private final MaxLengthSupported supportedEntryIdLength;
	private final MaxMinSupported supportedRegistrationNoRange;
	private final List<String> supportedMailToCcBcc;

	static AddressbookDestinationSettingSupported getInstance(Capability.AddressbookDestinationSettingCapability capability) {
		if (capability == null) {
			return null;
		}
		return new AddressbookDestinationSettingSupported(capability);
	}
	
	private AddressbookDestinationSettingSupported(Capability.AddressbookDestinationSettingCapability capability) {
		supportedDestinationKinds = Conversions.getList(capability.getDestinationKindList());
		supportedEntryIdLength = MaxLengthSupported.getMaxLengthSupported(capability.getEntryIdLength());
		supportedRegistrationNoRange = MaxMinSupported.getMaxMinSupported(capability.getRegistrationNoRange());
		supportedMailToCcBcc = Conversions.getList(capability.getMailToCcBccList());
	}
	
	public List<String> getSupportedDestinationKinds() {
		return supportedDestinationKinds;
	}
	
	public MaxLengthSupported getSupportedEntryIdLength() {
		return supportedEntryIdLength;
	}
	
	public MaxMinSupported getSupportedRegistrationNoRange() {
		return supportedRegistrationNoRange;
	}
	
	public List<String> getSupportedMailToCcBcc() {
		return supportedMailToCcBcc;
	}
	
}
