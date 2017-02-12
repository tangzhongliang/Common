/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event;

/**
 * スキャンサービスの状態が変更されたことを通知するためのリスナーです
 * The listener to notify that the scan service state has changed.
 *
 * @see jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.ScanService
 */
public interface ScanServiceAttributeListener {

	/**
	 * スキャンサービスの状態が変化した際に実行されます。
	 * Executed when scan service state changes.
	 *
	 * @param event スキャンサービスイベント
	 *              Scan service event
	 * @see ScanServiceAttributeEvent
	 * @see jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.ScanService
	 */
	public abstract void attributeUpdate(ScanServiceAttributeEvent event);
}
