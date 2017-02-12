/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ScanColor implements ScanRequestAttribute {
	AUTO_COLOR("auto_color"),
	MONOCHROME_TEXT("monochrome_text"),
	MONOCHROME_TEXT_PHOTO("monochrome_text_photo"),
	MONOCHROME_TEXT_LINEART("monochrome_text_lineart"),
	MONOCHROME_PHOTO("monochrome_photo"),
	GRAYSCALE("grayscale"),
	COLOR_TEXT_PHOTO("color_text_photo"),
	COLOR_GLOSSY_PHOTO("color_glossy_photo");

	private static final String SCAN_COLOR = "scanColor";


	/**
	 * 読み取りカラーモードを保持する
	 * Saves the scan color mode
	 */
	private final String scanColor;

	/**
	 * 指定した読み取りカラーモードで列挙型を構築する
	 * Builds enum type with the specified scan color mode
	 * @param color
	 */
	private ScanColor(String color){
		this.scanColor = color;
	}

	@Override
	public Class<?> getCategory() {
		return ScanColor.class;
	}

	@Override
	public String getName() {
		return SCAN_COLOR;
	}

	@Override
	public Object getValue() {
		return this.scanColor;
	}

    private static volatile Map<String, ScanColor> directory = null;

    private static Map<String, ScanColor> getDirectory() {
        if(directory == null){
            Map<String, ScanColor> d = new HashMap<String, ScanColor>();
            for(ScanColor color : values()) {
                d.put(color.getValue().toString(), color);
            }

            directory = d;
        }
        return directory;
    }

	public static ScanColor fromString(String value) {
		return getDirectory().get(value);
	}

	public static List<ScanColor> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<ScanColor> list = new ArrayList<ScanColor>();
		for(String value : values){
			ScanColor color = fromString(value);
			if( color != null ) {
				list.add(fromString(value));
			}
		}
		return list;
	}
}
