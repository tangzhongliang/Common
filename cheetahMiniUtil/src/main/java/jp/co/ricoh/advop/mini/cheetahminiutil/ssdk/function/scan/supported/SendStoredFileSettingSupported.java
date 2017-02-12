/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability.SendStoredFileSettingCapability;

/**
 * @since SmartSDK V1.01
 */
public final class SendStoredFileSettingSupported {

	private final SendStoredFileSettingFolderInfoSupported folderInfo;
	private final SendStoredFileSettingStoredFileInfoSupported storedFileInfo;

	/**
	 * @since SmartSDK V1.01
	 */
	public SendStoredFileSettingSupported(SendStoredFileSettingCapability capability) {
		folderInfo = SendStoredFileSettingFolderInfoSupported.getInstance(capability.getFolderInfoCapability());
		storedFileInfo = SendStoredFileSettingStoredFileInfoSupported.getInstance(capability.getStoredFileInfoCapability());
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public SendStoredFileSettingFolderInfoSupported getFolderInfo() {
		return folderInfo;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public SendStoredFileSettingStoredFileInfoSupported getStoredFileInfo() {
		return storedFileInfo;
	}

}
