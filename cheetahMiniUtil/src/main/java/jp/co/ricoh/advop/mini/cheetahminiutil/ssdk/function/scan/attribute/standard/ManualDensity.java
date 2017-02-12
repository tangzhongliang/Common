/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported.MaxMinSupported;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public final class ManualDensity implements ScanRequestAttribute {
	
	private static final String NAME_MANUAL_DENSITY = "manualDensity";
	
	private final int density;
	
	public ManualDensity(int density) {
		this.density = density;
	}

    @Override
    public String toString() {
        return Integer.toString(density);
    }

	@Override
	public Class<?> getCategory() {
		return ManualDensity.class;
	}

	@Override
	public String getName() {
		return NAME_MANUAL_DENSITY;
	}

	@Override
	public Object getValue() {
		return Integer.valueOf(density);
	}

    public static MaxMinSupported getSupportedValue(RangeElement value){
        return MaxMinSupported.getMaxMinSupported(value);
    }

}
