/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability.StoreLocalSettingCapability;

/**
 * @since SmartSDK V1.01
 */
public final class StoreLocalSettingSupported {

	private final FolderIdSupported  supportedFolderIdRange;
	private final MaxLengthSupported supportedFolderPasswordLength;
	private final MaxLengthSupported supportedFileNameLength;
	private final MaxLengthSupported supportedFilePasswordLength;

	/**
	 * @since SmartSDK V1.01
	 */
	public StoreLocalSettingSupported(StoreLocalSettingCapability capability) {
		supportedFolderIdRange = FolderIdSupported.getFolderIdSupported(capability.getFolderIdRange());
		supportedFolderPasswordLength = MaxLengthSupported.getMaxLengthSupported(capability.getFolderPasswordLength());
		supportedFileNameLength = MaxLengthSupported.getMaxLengthSupported(capability.getFileNameLength());
		supportedFilePasswordLength = MaxLengthSupported.getMaxLengthSupported(capability.getFilePasswordLength());
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

	/**
	 * @since SmartSDK V1.01
	 */
	public MaxLengthSupported getSupportedFileNameLength() {
		return supportedFileNameLength;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public MaxLengthSupported getSupportedFilePasswordLength() {
		return supportedFilePasswordLength;
	}

}
