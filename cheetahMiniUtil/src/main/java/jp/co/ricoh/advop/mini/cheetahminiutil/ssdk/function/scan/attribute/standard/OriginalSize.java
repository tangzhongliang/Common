/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.MediaSizeName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.Collections;
import java.util.List;

public final class OriginalSize implements ScanRequestAttribute {

	private static final String SCAN_ORIGINAL_SIZE = "originalSize";

	public static final OriginalSize AUTO  = new OriginalSize("auto");
	public static final OriginalSize MIXED = new OriginalSize("mixed");

	private final String scanSize;

	/**
	 * 指定無しで原稿サイズを指定します。
	 * Sets the document size with no size specified.
	 *
     * @param scanSize
	 */
	private OriginalSize(String scanSize) {
		this.scanSize = scanSize;
	}

	/**
	 * 指定した用紙サイズで原稿サイズを指定します。
	 * Sets the document size with specified paper size.
	 *
	 * @param scanSize
	 * @throws NullPointerException
	 */
	public OriginalSize(MediaSizeName scanSize){
		if (scanSize == null) {
			throw new NullPointerException("scanSize must not be null.");
		}
		this.scanSize = scanSize.getValue();
	}

    @Override
    public String toString() {
        return scanSize;
    }

	@Override
	public Class<?> getCategory() {
		return OriginalSize.class;
	}

	@Override
	public String getName() {
		return SCAN_ORIGINAL_SIZE;
	}

	@Override
	public Object getValue() {
		return this.scanSize;

	}

	public static List<String> getSupportedValue(List<String> values) {
	    if (values == null) {
	        return Collections.emptyList();
	    }
	    // without deep copy
		return values;
	}
}
