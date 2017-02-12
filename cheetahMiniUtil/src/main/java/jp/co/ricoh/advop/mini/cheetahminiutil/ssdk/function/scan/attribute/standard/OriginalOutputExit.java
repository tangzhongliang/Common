/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum OriginalOutputExit implements ScanRequestAttribute {

	TOP("top"),
	REAR("rear");

	private static final String ORIGINAL_OUTPUT_EXIT = "originalOutputExit";

	/**
	 * 内部保持しておく原稿排紙先
	 * Document output destination to store internally
	 */
	private final String originalOutputExit;

	/**
	 * 指定した原稿排紙先名で列挙型を構築する
	 * Builds enum type with the specified document output destination name
	 * @param outputExit
	 */
	private OriginalOutputExit(String outputExit) {
		this.originalOutputExit = outputExit;

	}

	@Override
	public Class<?> getCategory() {
		return OriginalOutputExit.class;
	}

	@Override
	public String getName() {
		return ORIGINAL_OUTPUT_EXIT;
	}

	@Override
	public Object getValue() {
		return this.originalOutputExit;
	}

	private static OriginalOutputExit fromString(String value) {
		for(OriginalOutputExit output : values()){
			if(output.getValue().equals(value)) return output;
		}
		return null;
	}

	public static List<OriginalOutputExit> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<OriginalOutputExit> list = new ArrayList<OriginalOutputExit>();
		for(String value : values) {
			OriginalOutputExit output = fromString(value);
			if( output != null ) {
				list.add(output);
			}
		}
		return list;
	}
}
