/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.MessageDispatchThread.EventData;

import java.util.ArrayList;
import java.util.List;

/**
 * 機器からのイベントを受信するための抽象クラスです。
 * 各Functionのイベントを受信するクラスは、本クラスを継承しています。
 * The abstract class to receive events from the device.
 * The class to receive events from functions inherits this class.
 */
public abstract class AbstractEventReceiver extends BroadcastReceiver{

	static final String SDK_SERVICE_PREFIX = "jp.co.ricoh.isdk.sdkservice.function.";
	static final String SDK_SERVICE_SEND_CMD_PERMISSION = "jp.co.ricoh.isdk.sdkservice.common.SdkService.APP_CMD_PERMISSION";
	static final String SDK_SERVICE_RECEIVE_PERMISSION = "jp.co.ricoh.isdk.sdkservice.common.SdkService.APP_EVENT_PERMISSION";


	private MessageDispatchThread mMessageThread = null;
	private final List<AsyncEventListener> mListeners;

	private HandlerThread broadcastResultReceiveThread = null;

	AbstractEventReceiver() {
		this.mMessageThread = new MessageDispatchThread();
		this.mMessageThread.start();

		registerApplicationContext();

		mListeners = new ArrayList<AsyncEventListener>();

		broadcastResultReceiveThread = new HandlerThread(getClass().getSimpleName() + this.hashCode());
		broadcastResultReceiveThread.start();
	}


	/**********************************************************************************************************
	 * 各Functionが実装すべきI/F
	 * methods that should be implemented to each function
	 **********************************************************************************************************/
	/**
	 * アプリ状態のイベントを開始します。
	 * Starts the event of the application state.
	 * @return
	 */
	public abstract AsyncConnectState startReceiveAppStateEvent();

	/**
	 * アプリ状態のイベントを終了します。
	 * Ends the event of the application state.
	 * @return
	 */
	public abstract AsyncConnectState endReceiveAppStateEvent();

	/**
	 * ジョブのイベントを開始します。
	 * Starts the event of the job.
	 * @return
	 */
	public abstract String startReceiveJobEvent();

	/**
	 * ジョブのイベントを終了します。
	 * Ends the event of the job.
	 * @return
	 */
	public abstract String endReceiveJobEvent();

	/**
	 * ファクス受信イベントを開始します。
	 * 本インターフェイスはFaxEventReceiverのみ有効です
	 * Starts the fax reception event.
	 * For this interface, only FaxEventReceiver is valid.
	 * @return
	 */
	public abstract AsyncConnectState startReceiveFaxInfoEvent();

	/**
	 * ファクス受信イベントを終了します。
	 * 本インターフェイスはFaxEventReceiverのみ有効です
	 * Ends the fax reception event.
     * For this interface, only FaxEventReceiver is valid.
	 * @return
	 */
	public abstract AsyncConnectState endReceiveFaxInfoEvent();

	/**
	 * 継承したクラスが受信するブロードキャストのアクションを返します。
	 * Returns the action of the broadcast received by the inherited class.
	 */
	abstract String[] getActions();

	/**
	 * 非同期通信の接続状態を返します。
	 * Returns the asynchronous connection state
	 */
	public abstract AsyncConnectState getAsyncConnectState();

	/**********************************************************************************************************
	 * Function共通の処理
	 * Common process for functions
	 **********************************************************************************************************/

	/**
	 * 非同期イベント用リスナーを登録します。
	 * Registers the listener for asynchronous events
	 * @param listener
	 */
	public void addAsyncEventListener(AsyncEventListener listener) {
		synchronized (this.mListeners) {
			this.mListeners.add(listener);
		}
	}

	/**
	 * 非同期イベント用リスナーを解除します。
	 * Clears the listener for asynchronous events
	 * @param listener
	 */
	public void removeAsyncEventListener(AsyncEventListener listener) {
		synchronized (this.mListeners) {
			if(!this.mListeners.contains(listener)) return;
			this.mListeners.remove(listener);
		}
	}

	/**
	 * ブロードキャストレシーバーを登録します。
	 * Registers broadcast receiver
	 */
	void registerApplicationContext() {
		if(SmartSDKApplication.getContext() == null) return;

		IntentFilter filter = new IntentFilter();
		for(String action : getActions()) {
			filter.addAction(action);
		}

		SmartSDKApplication.getContext().registerReceiver(this, filter, SDK_SERVICE_RECEIVE_PERMISSION,null);
	}

	/**
	 * メッセージ処理スレッドに対してイベントをキューイングします。
	 * Queues the event to the message processing thread.
	 * @param eventType
	 * @param eventData
	 */
	void postEvent(int eventType, String eventData) {
		this.mMessageThread.post(eventType, eventData, this);
	}

	/**
	 * 非同期イベントを1件リスナーへ通知します。
	 * Notifies one asynchronous event to the listener.
	 * @param evType
	 * @param evData
	 */
	void notifyAsyncEvent(int evType, String evData) {
		AsyncEventListener[] listenerArray;
		synchronized (this.mListeners) {
			listenerArray = this.mListeners.toArray(new AsyncEventListener[mListeners.size()]);
		}

		switch(evType){
		case EventData.EVENT_TYPE_APP_STATE:
			for(AsyncEventListener listener : listenerArray){
				listener.onReceiveAppEvent(evData);
			}
			break;
		case EventData.EVENT_TYPE_JOB_EVENT:
			for(AsyncEventListener listener : listenerArray){
				listener.onReceiveJobEvent(evData);
			}
			break;
		case EventData.EVENT_TYPE_RECEIVE_FAX:
			for(AsyncEventListener listener : listenerArray){
				listener.onReceiveFaxReceiveEvent(evData);
			}
			break;
		}
	}

	/**
	 * アプリ状態非同期イベントの通知を開始/終了します。
	 * Starts/ends notifying application state asynchronous events.
	 *
	 * @param action 開始対象のアプリ状態開始インテントアクション
	 *               Application state start intent action for the application to be started
	 * @param isReceiveEvent アプリ状態通知の開始・終了
	 *                       Starts/ends application state notification
	 * @return 非同期接続状態
	 *         機器が利用不可の場合は、nullが返ります。
	 *         Asynchronous connection state
	 *         If the device is unavailable, null is returned.
	 */
	AsyncConnectState setReceiveFunctionAppState(String action, boolean isReceiveEvent){
		if(action == null) throw new IllegalArgumentException("action is null");
		if(SmartSDKApplication.getContext() == null) return null;

		Intent sendIntent = new Intent(action);
		SDKServiceResultReceiver resultReceiver = new SDKServiceResultReceiver();

		// puts productId and receive setting
		sendIntent.putExtra("PRODUCT_ID", SmartSDKApplication.getProductId());
		sendIntent.putExtra("RECEIVE_SETTING", isReceiveEvent);

		Handler mHandler = new Handler(this.broadcastResultReceiveThread.getLooper());
		SmartSDKApplication.getContext().sendOrderedBroadcast(sendIntent, SDK_SERVICE_SEND_CMD_PERMISSION,
				resultReceiver, mHandler, 0, null, new Bundle());

		// synchronous processing
		Bundle bundle = null;
		try{
			bundle = resultReceiver.getResultExtras();
		}catch (InterruptedException ex) {
		    LogC.w(ex);
		}

		if (bundle == null) {
		    return null;
		}

        boolean result = bundle.getBoolean("RESULT", false);
        int errorCode =  bundle.getInt("ERROR_CODE", -1);

        return new AsyncConnectState(result, errorCode);
	}

	/**
	 * ジョブ状態非同期イベントの通知を開始/終了します。
	 * Starts/ends notifying job state asynchronous events.
	 *
	 * @param action 開始対象のジョブ状態開始インテントアクション
	 *               Job state start intent action for the job to be started
	 * @param isReceiveEvent ジョブ状態通知の開始・終了
     *                       Starts/ends application state notification
	 * @return 登録ID(ジョブ状態通知の終了を指定した場合はnull)
	 *         Registration ID (null if specifying to end job state notification)
	 */
	String setReceiveFunctionJobEvent(String action, boolean isReceiveEvent){
		if(action == null) return  null;
		if(SmartSDKApplication.getContext() == null) return null;

		Intent sendIntent = new Intent(action);
		SDKServiceResultReceiver resultReceiver = new SDKServiceResultReceiver();

		sendIntent.putExtra("PRODUCT_ID",SmartSDKApplication.getProductId());
		sendIntent.putExtra("RECEIVE_SETTING", isReceiveEvent);

		Handler mHandler = new Handler(this.broadcastResultReceiveThread.getLooper());
		SmartSDKApplication.getContext().sendOrderedBroadcast(sendIntent, SDK_SERVICE_SEND_CMD_PERMISSION,
				resultReceiver, mHandler, Activity.RESULT_OK, null, null);

		// synchronous processing
		Bundle bundle = null;
		try {
			bundle = resultReceiver.getResultExtras();
		} catch (InterruptedException e) {
		    LogC.w(e);
			return null;
		}
		String subscribedId = null;
		if (bundle != null) {
			boolean result = bundle.getBoolean("RESULT", false);
            int errorCode = bundle.getInt("ERROR_CODE", -1);
            AsyncConnectState asyncConnectState = new AsyncConnectState(result, errorCode);

			if (asyncConnectState.getState()==AsyncConnectState.STATE.CONNECTED) {
				subscribedId = bundle.getString("SUBSCRIBED_ID");
			} else if (asyncConnectState.getErrorCode()==AsyncConnectState.ERROR_CODE.INVALID){
			    return null;
			}
		}
		return subscribedId;
	}

	/**
	 * ブロードキャストを送信したときの結果受信用レシーバー
	 * AbstractEventReceiverのみで利用し、再利用は不可能です
	 * 必ずsendOrderedBroadcast()するたびにインスタンスを生成してください。
	 *
     * The receiver to receive result for the time broadcast is sent
     * Used only by AbstractEventReceiver; cannot be reused.
     * Always create instance whenever executing sendOrderedBroadcast().
	 */
	static class SDKServiceResultReceiver extends BroadcastReceiver {
		private Bundle resultExtras = null;

		@Override
		public void onReceive(Context context, Intent intent) {
			synchronized (this){
				resultExtras = getResultExtras(true);
				notifyAll();
			}
		}

		public Bundle getResultExtras() throws InterruptedException {
			synchronized (this) {
				while(resultExtras == null){
					wait(10000);
					break;
				}
				return resultExtras;
			}
		}
	}
}
