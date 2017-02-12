/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public final class FileSetting implements ScanRequestAttribute {

	private static final String NAME_FILE_SETTING = "fileSetting";

	private static final String NAME_COMPRESSION_METHOD = "compressionMethod";
	private static final String NAME_COMPRESSION_LEVEL = "compressionLevel";
	private static final String NAME_FILE_FORMAT = "fileFormat";
	private static final String NAME_MULTI_PAGE_FORMAT = "multiPageFormat";
	private static final String NAME_FILE_NAME = "fileName";
	private static final String NAME_FILE_NAME_TIME_STAMP = "fileNameTimeStamp";


	private CompressionMethod compressionMethod;
	private CompressionLevel compressionLevel;
	private FileFormat fileFormat;
	private boolean multiPageFormat;
	private String fileName;
	private boolean fileNameTimeStamp;


	public FileSetting() {
		compressionMethod = null;
		compressionLevel = null;
		fileFormat = null;
		multiPageFormat = true;
		fileName = null;
		fileNameTimeStamp = false;
	}

	public CompressionMethod getCompressionMethod() {
		return compressionMethod;
	}

	public void setCompressionMethod(CompressionMethod method) {
		compressionMethod = method;
	}

	public CompressionLevel getCompressionLevel() {
		return compressionLevel;
	}

	public void setCompressionLevel(CompressionLevel level) {
		compressionLevel = level;
	}

	public FileFormat getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(FileFormat format) {
		fileFormat = format;
	}

	public boolean isMultiPageFormat() {
		return multiPageFormat;
	}

	public void setMultiPageFormat(boolean value) {
		multiPageFormat = value;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isFileNameTimeStamp() {
		return fileNameTimeStamp;
	}

	public void setFileNameTimeStamp(boolean value) {
		fileNameTimeStamp = value;
	}


	@Override
	public Class<?> getCategory() {
		return FileSetting.class;
	}

	@Override
	public String getName() {
		return NAME_FILE_SETTING;
	}

	@Override
	public Object getValue() {
		Map<String, Object> values = new HashMap<String, Object>();

		if (compressionMethod != null) {
			values.put(NAME_COMPRESSION_METHOD, compressionMethod.getValue());
		}
		if (compressionLevel != null) {
			values.put(NAME_COMPRESSION_LEVEL, compressionLevel.getValue());
		}
		if (fileFormat != null) {
			values.put(NAME_FILE_FORMAT, fileFormat.getValue());
		}
			values.put(NAME_MULTI_PAGE_FORMAT, Boolean.valueOf(multiPageFormat));
		if (fileName != null) {
			values.put(NAME_FILE_NAME, fileName);
		}
			values.put(NAME_FILE_NAME_TIME_STAMP, fileNameTimeStamp);

		return values;
	}


	public enum CompressionMethod {

		NONE("none"),
		MH("mh"),
		MR("mr"),
		MMR("mmr"),
		JBIG2("jbig2"),
		JPEG("jpeg"),
		JPEG2000("jpeg2000");

		private final String value;

		private CompressionMethod(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		private static CompressionMethod fromString(String value){
			for(CompressionMethod method : values()){
				if(method.getValue().equals(value)) return method;
			}
			return null;
		}
		
		public static List<CompressionMethod> getSupportedValue(List<String> values){
			if (values == null) {
				return Collections.emptyList();
			}
			
			List<CompressionMethod> list = new ArrayList<CompressionMethod>();
			for(String val : values) {
			    CompressionMethod method = CompressionMethod.fromString(val);
			    if (method != null) {
			        list.add(method);
			    }
			}
			return list;
		}

	}

	public enum CompressionLevel {

		LEVEL1("level1"),
		LEVEL2("level2"),
		LEVEL3("level3"),
		LEVEL4("level4"),
		LEVEL5("level5");

		private final String value;

		private CompressionLevel(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		private static CompressionLevel fromString(String value) {
			for(CompressionLevel level : values()){
				if(level.getValue().equals(value)) return level;
			}
			return null;
		}

		public static List<CompressionLevel> getSupportedValue(List<String> values) {
			if (values == null) {
				return Collections.emptyList();
			}
			
			List<CompressionLevel> list = new ArrayList<CompressionLevel>();
			for(String value : values){
			    CompressionLevel level = CompressionLevel.fromString(value);
			    if (level != null) {
			        list.add(level);
			    }
			}
			return list;
		}

	}

	public enum FileFormat {

		TIFF_JPEG("tiff_jpeg"),
		PDF("pdf");

		private final String value;

		private FileFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static FileFormat fromString(String value) {
			for(FileFormat fileFormat : values()){
				if( fileFormat.getValue().equals(value)) return fileFormat;
			}
			return null;
		}

		public static List<FileFormat> getSupportedValue(List<String> values) {
			if (values == null) {
				return Collections.emptyList();
			}
			
			List<FileFormat> list = new ArrayList<FileFormat>();
			for(String value : values){
			    FileFormat format = FileFormat.fromString(value);
			    if (format != null) {
			        list.add(format);
			    }
			}
			return list;
		}

	}

}
