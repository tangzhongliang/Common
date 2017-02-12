/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum OriginalOrientation implements ScanRequestAttribute {

	READABLE("readable"),
	UNREADABLE("unreadable");

	private static final String ORIGINAL_ORIENTATION = "originalOrientation";

	/**
	 * 内部保持しておく原稿セット方向
	 * Document orientation to store internally
	 */
	private final String originalOrientation;

	/**
	 * 指定した原稿セット方向で列挙型を構築する
	 * Builds enum type with the specified orientation
	 * @param orientation
	 */
	private OriginalOrientation(String orientation) {
		this.originalOrientation = orientation;
	}

	@Override
	public Class<?> getCategory() {
		return OriginalOrientation.class;
	}

	@Override
	public String getName() {
		return ORIGINAL_ORIENTATION;
	}

	@Override
	public Object getValue() {
		return this.originalOrientation;
	}

	private static OriginalOrientation fromString(String value) {
		for(OriginalOrientation orientation : values()){
			if(orientation.getValue().equals(value)) return orientation;
		}

		return null;
	}

	public static List<OriginalOrientation> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<OriginalOrientation> list = new ArrayList<OriginalOrientation>();
		for(String value : values) {
			OriginalOrientation orientation = fromString(value);
			if( orientation != null ) {
				list.add(orientation);
			}
		}
		return list;
	}
}
