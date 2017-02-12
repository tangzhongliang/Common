/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting.CompressionLevel;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting.CompressionMethod;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting.FileFormat;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability.FileSettingCapability;

public final class FileSettingSupported {

	private List<CompressionMethod> compMethodList;
	private List<CompressionLevel> compLevelList;
	private List<FileFormat> fileFormatList;
	private List<Boolean> supportedMultipageFormat;
	private MaxLengthSupported supportedFileName;
	private List<Boolean> supportedNameTime;

	public FileSettingSupported(FileSettingCapability capability) {
		compMethodList = CompressionMethod.getSupportedValue(capability.getCompressionMethodList());
		compLevelList = CompressionLevel.getSupportedValue(capability.getCompressionLevelList());
		fileFormatList = FileFormat.getSupportedValue(capability.getFileFormatList());
		supportedFileName = MaxLengthSupported.getMaxLengthSupported(capability.getFileNameLength());
		supportedNameTime = capability.getFileNameTimeStampList();
		supportedMultipageFormat = capability.getMultiPageFormatList();
	}
	
	public List<CompressionMethod> getCompMethodList() {
		return compMethodList;
	}

	public List<CompressionLevel> getCompLevelList() {
		return compLevelList;
	}

	public List<FileFormat> getFileFormatList() {
		return fileFormatList;
	}

	public List<Boolean> getMultipageFormat() {
		return supportedMultipageFormat;
	}

	public MaxLengthSupported getSupportedFileName() {
		return supportedFileName;
	}

	public List<Boolean> getSupportedNameTime() {
		return supportedNameTime;
	}

}
