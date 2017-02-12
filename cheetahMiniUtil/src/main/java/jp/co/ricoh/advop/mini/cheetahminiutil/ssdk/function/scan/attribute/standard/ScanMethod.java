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

public enum ScanMethod implements ScanRequestAttribute {

	NORMAL("normal"),
	BATCH("batch"),
	SADF("sadf");

	private static final String SCAN_METHOD = "scanMethod";

	/**
	 * 内部保持しておく読み取り方法名
	 * Scan method name to store internally
	 */
	private final String scanMethod;

	/**
	 * 指定した文字列で読み取り方法を構築
	 * Builds the scan method with the specified string.
	 * @param method
	 */
	private ScanMethod(String method) {
		this.scanMethod = method;
	}

	@Override
	public Class<?> getCategory() {
		return ScanMethod.class;
	}

	@Override
	public String getName() {
		return SCAN_METHOD;
	}

	@Override
	public Object getValue() {
		return this.scanMethod;
	}

    private static volatile Map<String, ScanMethod> directory = null;

    private static Map<String, ScanMethod> getDirectory() {
        if(directory == null) {
            Map<String,ScanMethod> d = new HashMap<String, ScanMethod>();
            for(ScanMethod method : values()) {
                d.put(method.getValue().toString(), method);
            }
            directory = d;
        }
        return directory;
    }

	private static ScanMethod fromString(String value) {
		return getDirectory().get(value);
	}

	public static List<ScanMethod> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<ScanMethod> list = new ArrayList<ScanMethod>();
		for(String value : values){
			ScanMethod method = fromString(value);
			if( method != null ) {
				list.add(method);
			}
		}
		return list;
	}

}
