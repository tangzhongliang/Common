/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MagnificationElement;

public final class MagnificationSupported {

	private Boolean fittingSupported;
	private String maxValue, minValue, step;

	public MagnificationSupported(MagnificationElement value) {
		fittingSupported = value.getFitting();
		maxValue = value.getMaxValue();
		minValue = value.getMinValue();
		step = value.getStep();
	}

	public Boolean getFittingSupported() {
		return fittingSupported;
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
