/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.ScanJob;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttributeSet;

/**
 * スキャンジョブの状態変化を通知するためのイベントクラスです。
 * The event class to notify scan job state changes.
 */
public final class ScanJobAttributeEvent {

	private ScanJob mEventSource = null;
	private ScanJobAttributeSet mScanJobAttributeSet = null;


	/**
	 * スキャンジョブイベントを構築します。
	 * Builds scan job events.
	 *
	 * @param source 発生元のスキャンジョブクラス
	 *               The scan job class of the generated event.
	 */
	public ScanJobAttributeEvent(ScanJob source, ScanJobAttributeSet attributes) {
		this.mEventSource = source;
		this.mScanJobAttributeSet = attributes;
	}


	/**
	 * イベントが発生したスキャンジョブインスタンスを返します。
	 * 参照時にはスキャンジョブインスタンスが破棄されている可能性もあります。
	 * Returns the scan job instance in which the event is generated.
	 * The scan job instance may be discarded when checked.
	 *
	 * @return スキャンジョブインスタンス
	 *         Scan job instance
	 */
	public ScanJob getSource() {
		return mEventSource;
	}

	/**
	 * イベントが発生した際のスキャンジョブ属性のセットを返します。
	 * 参照時に属性セットインスタンスが破棄されている可能性もあります
	 * Returns the scan job attribute at the time the event is generated
	 * The attribute set instance may be discarded at the time the instance is checked.
	 *
	 * @return スキャンジョブ属性セットインスタンス
	 *         Scan job attribute set instance
	 */
	public ScanJobAttributeSet getUpdateAttributes() {
		return mScanJobAttributeSet;
	}

}
