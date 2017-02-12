/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported.MaxMinSupported;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

/**
 * ジョブ停止中のタイムアウト時間を表す、スキャンジョブ実行条件を構築するための属性クラスです。
 * This attribute class represents timeout time for a stopped job, used for building scan job conditions.
 * 
 * @since SmartSDK V1.02
 */
public final class JobStoppedTimeoutPeriod implements ScanRequestAttribute {
	
	private static final String NAME_JOB_STOPPED_TIMEOUT_PERIOD = "jobStoppedTimeoutPeriod";
	
	private final int period;

	/**
	 * JobStoppedTimeoutPeriod インスタンスを生成します。
	 * Creates a JobStoppedTimeoutPeriod instance.
	 * 
	 * @param period タイムアウト時間
	 *                Timeout time.
	 * 
	 * @since SmartSDK V1.02
	 */
	public JobStoppedTimeoutPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return Integer.toString(period);
	}

	@Override
	public Class<?> getCategory() {
		return JobStoppedTimeoutPeriod.class;
	}

	@Override
	public String getName() {
		return NAME_JOB_STOPPED_TIMEOUT_PERIOD;
	}

	@Override
	public Object getValue() {
		return Integer.valueOf(period);
	}

	/**
	 * @since SmartSDK V1.02
	 */
	public static MaxMinSupported getSupportedValue(RangeElement value){
		return MaxMinSupported.getMaxMinSupported(value);
	}

}
