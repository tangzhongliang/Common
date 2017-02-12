/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.FolderIdElement;

/**
 * @since SmartSDK V1.01
 */
public final class FolderIdSupported {

	private final Boolean sharedFolder;
	private final Boolean faxReceivedFiles;
	private final String minValue;
	private final String maxValue;
	private final String step;

	/**
	 * @since SmartSDK V1.01
	 */
	public FolderIdSupported(Boolean sharedFolder, Boolean faxReceivedFiles, String minValue, String maxValue, String step) {
		this.sharedFolder = sharedFolder;
		this.faxReceivedFiles = faxReceivedFiles;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.step = step;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public static FolderIdSupported getFolderIdSupported(FolderIdElement value) {
		if (value == null) {
			return null;
		}
		return new FolderIdSupported(value.getSharedFolder(), value.getFaxReceivedFiles(), value.getMinValue(), value.getMaxValue(), value.getStep());
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public Boolean getSharedFolder() {
		return sharedFolder;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public Boolean getFaxReceivedFiles() {
		return faxReceivedFiles;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public String getMinValue() {
		return minValue;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 * @since SmartSDK V1.01
	 */
	public String getStep() {
		return step;
	}

}
