/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

import java.util.List;

public final class ManualDestinationSettingSupported {
	
	private List<String> supportedDestinationKinds;
	private MailAddressManualDestinationSettingSupported mailAddress;
	private SmbAddressManualDestinationSettingSupported smbAddress;
	private FtpAddressManualDestinationSettingSupported ftpAddress;
	private NcpAddressManualDestinationSettingSupported ncpAddress;
	
	static ManualDestinationSettingSupported getInstance(Capability.ManualDestinationSettingCapability capability) {
		if (capability == null) {
			return null;
		}
		return new ManualDestinationSettingSupported(capability);
	}
	
	private ManualDestinationSettingSupported(Capability.ManualDestinationSettingCapability capability) {
		supportedDestinationKinds = Conversions.getList(capability.getDestinationKindObject());
		mailAddress = MailAddressManualDestinationSettingSupported.getInstance(capability.getMailAddressInfoCapability());
		smbAddress = SmbAddressManualDestinationSettingSupported.getInstance(capability.getSmbAddressInfoCapability());
		ftpAddress = FtpAddressManualDestinationSettingSupported.getInstance(capability.getFtpAddressInfoCapability());
		ncpAddress = NcpAddressManualDestinationSettingSupported.getInstance(capability.getNcpAddressInfoCapability());
	}
	
	public List<String> getSupportedDestinationKinds() {
		return supportedDestinationKinds;
	}
	
	public MailAddressManualDestinationSettingSupported getMailAddress() {
		return mailAddress;
	}
	
	public SmbAddressManualDestinationSettingSupported getSmbAddress() {
		return smbAddress;
	}
	
	public FtpAddressManualDestinationSettingSupported getFtpAddress() {
		return ftpAddress;
	}
	
	public NcpAddressManualDestinationSettingSupported getNcpAddress() {
		return ncpAddress;
	}
	
}
