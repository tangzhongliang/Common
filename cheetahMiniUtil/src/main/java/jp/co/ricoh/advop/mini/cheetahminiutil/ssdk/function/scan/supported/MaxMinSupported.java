/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public final class MaxMinSupported {

	private String maxValue,minValue,step;

	public MaxMinSupported(String maxValue, String minValue, String step) {
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.step = step;
	}

	public static MaxMinSupported getMaxMinSupported(RangeElement value) {
		if (value == null) {
			return null;
		}
		return new MaxMinSupported(value.getMaxValue(), value.getMinValue(), value.getStep());
	}

	public String getMaxValue() {
		return maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public String getStep() {
		return step;
	}

}
