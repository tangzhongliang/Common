/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported.MaxMinSupported;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public final class WaitTimeForNextOriginal implements ScanRequestAttribute {
	
	private static final String NAME_WAIT_TIME_FOR_NEXT_ORIGINAL = "waitTimeForNextOriginal";
	
	private final int time;
	
	public WaitTimeForNextOriginal(int time) {
		this.time = time;
	}

    @Override
    public String toString() {
        return Integer.toString(time);
    }

	@Override
	public Class<?> getCategory() {
		return WaitTimeForNextOriginal.class;
	}

	@Override
	public String getName() {
		return NAME_WAIT_TIME_FOR_NEXT_ORIGINAL;
	}

	@Override
	public Object getValue() {
		return Integer.valueOf(time);
	}

    public static MaxMinSupported getSupportedValue(RangeElement value){
        return MaxMinSupported.getMaxMinSupported(value);
    }

}
