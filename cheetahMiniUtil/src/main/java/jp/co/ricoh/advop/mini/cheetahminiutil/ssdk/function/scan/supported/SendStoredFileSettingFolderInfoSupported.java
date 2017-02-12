/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

/**
 * @since SmartSDK V1.01
 */
public final class SendStoredFileSettingFolderInfoSupported {

	private final FolderIdSupported supportedFolderIdRange;
	private final MaxLengthSupported supportedFolderPasswordLength;

	static SendStoredFileSettingFolderInfoSupported getInstance(Capability.FolderInfoCapability capability) {
		if (capability == null) {
			return null;
		}
		return new SendStoredFileSettingFolderInfoSupported(capability);
	}

	private SendStoredFileSettingFolderInfoSupported(Capability.FolderInfoCapability capability) {
		supportedFolderIdRange = FolderIdSupported.getFolderIdSupported(capability.getFolderIdRange());
		supportedFolderPasswordLength = MaxLengthSupported.getMaxLengthSupported(capability.getFolderPasswordLength());
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public FolderIdSupported getSupportedFolderIdRange() {
		return supportedFolderIdRange;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public MaxLengthSupported getSupportedFolderPasswordLength() {
		return supportedFolderPasswordLength;
	}

}
