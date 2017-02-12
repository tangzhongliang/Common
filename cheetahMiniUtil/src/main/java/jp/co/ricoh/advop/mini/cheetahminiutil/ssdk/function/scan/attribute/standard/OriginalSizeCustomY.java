/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported.MaxMinSupported;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public final class OriginalSizeCustomY implements ScanRequestAttribute {

	private static final String SCAN_ORIGINAL_CUSTOM_SIZE_Y = "originalSizeCustomY";

	private final int sizeY;

	public OriginalSizeCustomY(int y) {
		this.sizeY = y;
	}

    @Override
    public String toString() {
        return Integer.toString(sizeY);
    }

	@Override
	public Class<?> getCategory() {
		return OriginalSizeCustomY.class;
	}

	@Override
	public String getName() {
		return SCAN_ORIGINAL_CUSTOM_SIZE_Y;
	}

	@Override
	public Object getValue() {
		return this.sizeY;
	}


	public static MaxMinSupported getSupportedValue(RangeElement values) {
		return MaxMinSupported.getMaxMinSupported(values);
	}
}
