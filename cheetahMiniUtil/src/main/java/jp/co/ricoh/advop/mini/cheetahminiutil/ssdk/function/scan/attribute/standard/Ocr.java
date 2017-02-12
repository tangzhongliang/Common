/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public enum Ocr implements ScanRequestAttribute {
	
	ON(true),
	OFF(false);
	
	private static final String OCR = "ocr";
	
	private final boolean value;
	
	private Ocr(boolean value) {
		this.value = value;
	}
	
	@Override
	public Class<?> getCategory() {
		return Ocr.class;
	}

	@Override
	public String getName() {
		return OCR;
	}

	@Override
	public Object getValue() {
		return Boolean.valueOf(value);
	}


    public static List<Ocr> getSupportedValue(List<Boolean> values) {
    	if (values == null) {
    		return Collections.emptyList();
    	}
    	
        List<Ocr> list = new ArrayList<Ocr>();
        for(Boolean value : values) {
            list.add(value ? ON : OFF);
        }
        return list;
    }
}
