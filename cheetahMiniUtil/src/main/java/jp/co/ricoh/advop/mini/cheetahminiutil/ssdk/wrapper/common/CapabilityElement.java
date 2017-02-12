/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

/**
 * 機器に対して設定可能な値を表すクラスの抽象クラス。
 * 各Functionの設定可能値を示すクラスは、本クラスを継承します。
 */
public abstract class CapabilityElement extends Element {
	
	public CapabilityElement(Map<String, Object> values) {
		super(values);
	}
	
	protected MaxLengthElement getMaxLengthValue(String key) {
		Map<String, Object> value = getObjectValue(key);
		if (value == null) {
			return null;
		}
		return new MaxLengthElement(value);
	}
	
	protected RangeElement getRangeValue(String key) {
		Map<String, Object> value = getObjectValue(key);
		if (value == null) {
			return null;
		}
		return new RangeElement(value);
	}
	
	protected MagnificationElement getMagnificationValue(String key) {
		Map<String, Object> value = getObjectValue(key);
		if (value == null) {
			return null;
		}
		return new MagnificationElement(value);
	}
	
	protected DateElement getDateValue(String key) {
		Map<String, Object> value = getObjectValue(key);
		if (value == null) {
			return null;
		}
		return new DateElement(value);
	}
	
	/*
	 * @since SmartSDK V1.01
	 */
	protected FolderIdElement getFolderIdValue(String key) {
		Map<String, Object> value = getObjectValue(key);
		if (value == null) {
			return null;
		}
		return new FolderIdElement(value);
	}
	
	/*
	 * @since SmartSDK V2.10
	 */
	protected WaitTimeForNextOriginalRangeElement getWaitTimeForNextOriginalRangeValue(String key) {
		Map<String, Object> value = getObjectValue(key);
		if (value == null) {
			return null;
		}
		return new WaitTimeForNextOriginalRangeElement(value);
	}
	
}
