/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

import java.util.List;

public final class FtpAddressManualDestinationSettingSupported {
	
	private final MaxLengthSupported supportedServerNameLength;
	private final MaxLengthSupported supportedPathLength;
	private final MaxLengthSupported supportedUserNameLength;
	private final MaxLengthSupported supportedPasswordLength;
	private final List<String> supportedCharacterCodes;
	private final MaxMinSupported supportedPortRange;
	
	static FtpAddressManualDestinationSettingSupported getInstance(Capability.FtpAddressInfoCapability capability) {
		if (capability == null) {
			return null;
		}
		return new FtpAddressManualDestinationSettingSupported(capability);
	}
	
	private FtpAddressManualDestinationSettingSupported(Capability.FtpAddressInfoCapability capability) {
		supportedServerNameLength = MaxLengthSupported.getMaxLengthSupported(capability.getServerNameLength());
		supportedPathLength = MaxLengthSupported.getMaxLengthSupported(capability.getPathLength());
		supportedUserNameLength = MaxLengthSupported.getMaxLengthSupported(capability.getUserNameLength());
		supportedPasswordLength = MaxLengthSupported.getMaxLengthSupported(capability.getPasswordLength());
		supportedCharacterCodes = Conversions.getList(capability.getCharacterCodeList());
		supportedPortRange = MaxMinSupported.getMaxMinSupported(capability.getPortRange());
	}
	
	public MaxLengthSupported getSupportedServerNameLength() {
		return supportedServerNameLength;
	}
	
	public MaxLengthSupported getSupportedPathLength() {
		return supportedPathLength;
	}
	
	public MaxLengthSupported getSupportedUserNameLength() {
		return supportedUserNameLength;
	}
	
	public MaxLengthSupported getSupportedPasswordLength() {
		return supportedPasswordLength;
	}
	
	public List<String> getSupportedCharacterCodes() {
		return supportedCharacterCodes;
	}
	
	public MaxMinSupported getSupportedPortRange() {
		return supportedPortRange;
	}
	
}