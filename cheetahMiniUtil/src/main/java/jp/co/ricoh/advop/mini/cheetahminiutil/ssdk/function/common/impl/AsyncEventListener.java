/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl;

/**
 * SDKServiceから受信する非同期イベントを通知するリスナーインターフェイスです。
 * The listener interface to notify asynchronous events received from SDKService.
 */
public interface AsyncEventListener {
	/**
	 * アプリ状態に関する非同期イベントを通知します。
	 * Notifies asynchronous events related to the application state.
	 * @param eventData
	 */
	public abstract void onReceiveAppEvent(String eventData);

	/**
	 * ジョブ状態に関する非同期イベントを通知します
	 * Notifies asynchronous events related to the job state.
	 * @param eventData
	 */
	public abstract void onReceiveJobEvent(String eventData);

	/**
	 * ファクス受信に関する非同期イベントを通知します。
	 * この通知は、FaxEventReceiverに登録したときのみ有効です
	 * Notifies asynchronous events related to fax reception.
	 * This notification is valid only when registered to FaxEventReceiver.
	 * @param eventData
	 */
	public abstract void onReceiveFaxReceiveEvent(String eventData);
}
