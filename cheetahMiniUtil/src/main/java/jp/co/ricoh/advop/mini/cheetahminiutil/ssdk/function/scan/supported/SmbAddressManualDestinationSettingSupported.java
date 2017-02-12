/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

public final class SmbAddressManualDestinationSettingSupported {
	
	private final MaxLengthSupported supportedPathLength;
	private final MaxLengthSupported supportedUserNameLength;
	private final MaxLengthSupported supportedPasswordLength;
	
	static SmbAddressManualDestinationSettingSupported getInstance(Capability.SmbAddressInfoCapability capability) {
		if (capability == null) {
			return null;
		}
		return new SmbAddressManualDestinationSettingSupported(capability);
	}
	
	private SmbAddressManualDestinationSettingSupported(Capability.SmbAddressInfoCapability capability) {
		supportedPathLength = MaxLengthSupported.getMaxLengthSupported(capability.getPathLength());
		supportedUserNameLength = MaxLengthSupported.getMaxLengthSupported(capability.getUserNameLength());
		supportedPasswordLength = MaxLengthSupported.getMaxLengthSupported(capability.getPasswordLength());
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
	
}