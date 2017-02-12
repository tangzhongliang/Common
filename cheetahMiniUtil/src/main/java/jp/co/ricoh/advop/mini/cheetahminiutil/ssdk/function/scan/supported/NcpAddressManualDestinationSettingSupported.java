/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

import java.util.List;

public final class NcpAddressManualDestinationSettingSupported {
	
	private final MaxLengthSupported supportedPathLength;
	private final MaxLengthSupported supportedUserNameLength;
	private final MaxLengthSupported supportedPasswordLength;
	private final List<String> supportedConnectionTypes;
	
	static NcpAddressManualDestinationSettingSupported getInstance(Capability.NcpAddressInfoCapability capability) {
		if (capability == null) {
			return null;
		}
		return new NcpAddressManualDestinationSettingSupported(capability);
	}
	
	private NcpAddressManualDestinationSettingSupported(Capability.NcpAddressInfoCapability capability) {
		supportedPathLength = MaxLengthSupported.getMaxLengthSupported(capability.getPathLength());
		supportedUserNameLength = MaxLengthSupported.getMaxLengthSupported(capability.getUserNameLength());
		supportedPasswordLength = MaxLengthSupported.getMaxLengthSupported(capability.getPasswordLength());
		supportedConnectionTypes = Conversions.getList(capability.getConnectionTypeList());
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
	
	public List<String> getSupportedConnectionTypes() {
		return supportedConnectionTypes;
	}
	
}