/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * ジョブ状態を示す属性クラス
 * The attribute class to indicate job state.
 */
public enum ScanJobState implements ScanJobAttribute {

	ABORTED ("aborted"),
	CANCELED ("canceled"),
	COMPLETED ("completed"),
	PENDING ("pending"),
	PROCESSING ("processing"),
	PROCESSING_STOPPED ("processing_stopped");

	private final String value;

	private ScanJobState(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobState.class;
	}

	@Override
	public String getName() {
		return ScanJobState.class.getSimpleName();
	}

	private static volatile Map<String, ScanJobState> states = null;

	private static Map<String, ScanJobState> getStates() {
		if (states == null) {
			ScanJobState[] stateArray = values();
			Map<String, ScanJobState> s = new HashMap<String, ScanJobState>(stateArray.length);
			for (ScanJobState state : stateArray) {
				s.put(state.value, state);
			}
			states = s;
		}
		return states;
	}

	public static ScanJobState fromString(String value) {
		return getStates().get(value);
	}

}
