/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MaxLengthElement;

public final class MaxLengthSupported {

	private final int maxLength;
	private final String supportedCharType;

	public MaxLengthSupported(int maxLength, String supportedCharType){
		this.maxLength = maxLength;
		this.supportedCharType = supportedCharType;
	}

	public static MaxLengthSupported getMaxLengthSupported(MaxLengthElement value) {
		if (value == null) {
			return null;
		}
		return new MaxLengthSupported(value.getMaxLength(), value.getAvailableCharacterType());
	}

	public int getMaxLength() {
		return maxLength;
	}

	public String getSupportedCharType() {
		return supportedCharType;
	}

}
