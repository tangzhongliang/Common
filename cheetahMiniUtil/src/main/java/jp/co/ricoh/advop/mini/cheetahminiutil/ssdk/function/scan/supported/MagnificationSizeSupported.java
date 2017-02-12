/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability.MagnificationSizeCapability;

public final class MagnificationSizeSupported {

	private final List<String> supportedScanSize;
	private final MaxMinSupported supportedCustomX;
	private final MaxMinSupported supportedCustomY;

	public MagnificationSizeSupported (MagnificationSizeCapability capability) {
		supportedScanSize = Conversions.getList(capability.getSizeList());
		supportedCustomX = MaxMinSupported.getMaxMinSupported(capability.getCustomXRange());
		supportedCustomY = MaxMinSupported.getMaxMinSupported(capability.getCustomYRange());
	}

	public List<String> getSupportedScanSize() {
		return supportedScanSize;
	}

	public MaxMinSupported getSupportedCustomX() {
		return supportedCustomX;
	}

	public MaxMinSupported getSupportedCustomY() {
		return supportedCustomY;
	}

}
