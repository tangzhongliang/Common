/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.MessageDispatchThread.EventData;

/**
 * スキャンに関連するイベントを受信するクラスです。
 * the class to receive events related to scanning.
 */
public class ScanEventReceiver extends AbstractEventReceiver {

	private static final String TAG = ScanEventReceiver.class.getSimpleName();

	static final String SCAN_APP_EVENT_ACTION = SDK_SERVICE_PREFIX + "scan.AsyncAppState.STATE_EVENT";
	static final String SCAN_JOB_EVENT_ACTION = SDK_SERVICE_PREFIX + "scan.AsyncJobEvent.JOB_EVENT";

	static final String RECEIVE_SCAN_APP_STATE_ACTION    = SDK_SERVICE_PREFIX + "scan.AsyncAppState.RECEIVE_APP_STATE";
	static final String RECEIVE_SCAN_JOB_STATE_ACTION    = SDK_SERVICE_PREFIX + "scan.AsyncJobEvent.RECEIVE_JOB_EVENT";

	static final String SCAN_CONNECT_STATE_ACTION = SDK_SERVICE_PREFIX + "scan.AsyncConnection.CONNECT_STATE";

	private AsyncConnectState mAsyncConnectState = null;

	public ScanEventReceiver() {
		super();
	}


	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		String action = intent.getAction();

		if(extras == null) return;

		if(SCAN_APP_EVENT_ACTION.equals(action)){
			String eventData = extras.getString("APP_STATE");
			if(eventData != null) {
				postEvent(EventData.EVENT_TYPE_APP_STATE, eventData);
			}

		} else if(SCAN_JOB_EVENT_ACTION.equals(action)){
			String eventData = extras.getString("JOB_EVENT");
			if(eventData != null) {
				postEvent(EventData.EVENT_TYPE_JOB_EVENT, eventData);
			}

		} else if(SCAN_CONNECT_STATE_ACTION.equals(action)){
			boolean state = extras.getBoolean("STATE", false);
			int errorCode = extras.getInt("ERROR_CODE", -1);
			LogC.d(TAG, "scan.AsyncConnectState:" + state + "," + errorCode);
			synchronized (this) {
	            mAsyncConnectState = new AsyncConnectState(state, errorCode);
            }
		}
	}

	@Override
	public AsyncConnectState startReceiveAppStateEvent() {
		return setReceiveFunctionAppState(RECEIVE_SCAN_APP_STATE_ACTION, true);
	}

	@Override
	public AsyncConnectState endReceiveAppStateEvent() {
		return setReceiveFunctionAppState(RECEIVE_SCAN_APP_STATE_ACTION, false);
	}

	@Override
	public String startReceiveJobEvent() {
		return setReceiveFunctionJobEvent(RECEIVE_SCAN_JOB_STATE_ACTION, true);
	}

	@Override
	public String endReceiveJobEvent() {
		return setReceiveFunctionJobEvent(RECEIVE_SCAN_JOB_STATE_ACTION, false);
	}

	@Override
	public AsyncConnectState startReceiveFaxInfoEvent() {
        return AsyncConnectState.valueOf(
                AsyncConnectState.STATE.DISCONNECTED,
                AsyncConnectState.ERROR_CODE.INVALID);
	}

	@Override
	public AsyncConnectState endReceiveFaxInfoEvent() {
        return AsyncConnectState.valueOf(
                AsyncConnectState.STATE.DISCONNECTED,
                AsyncConnectState.ERROR_CODE.INVALID);
	}

	@Override
	String[] getActions() {
		String[] actions = new String[3];
		actions[0] = SCAN_APP_EVENT_ACTION;
		actions[1] = SCAN_JOB_EVENT_ACTION;
		actions[2] = SCAN_CONNECT_STATE_ACTION;
		return actions;
	}

    @Override
    public AsyncConnectState getAsyncConnectState() {
        synchronized (this) {
            return mAsyncConnectState;
        }
    }
}
