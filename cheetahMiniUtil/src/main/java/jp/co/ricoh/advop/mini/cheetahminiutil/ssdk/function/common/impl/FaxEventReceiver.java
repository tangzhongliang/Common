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
 * FAXに関連するイベントを受信するクラスです。
 * The class to receive events related to fax.
 */
public class FaxEventReceiver extends AbstractEventReceiver {

	private static final String TAG = FaxEventReceiver.class.getSimpleName();

	static final String RECEIVE_FAX_APP_STATE_ACTION    = SDK_SERVICE_PREFIX + "fax.AsyncAppState.RECEIVE_APP_STATE";
	static final String RECEIVE_FAX_JOB_STATE_ACTION    = SDK_SERVICE_PREFIX + "fax.AsyncJobEvent.RECEIVE_JOB_EVENT";
	static final String RECEIVE_FAX_RECEIVE_INFO_ACTION = SDK_SERVICE_PREFIX + "fax.AsyncReceiveEvent.RECEIVE_RECEIVE_INFO";

	static final String FAX_APP_EVENT_ACTION  = SDK_SERVICE_PREFIX + "fax.AsyncAppState.STATE_EVENT";
	static final String FAX_JOB_EVENT_ACTION  = SDK_SERVICE_PREFIX + "fax.AsyncJobEvent.JOB_EVENT";
	static final String FAX_INFO_EVENT_ACTION = SDK_SERVICE_PREFIX + "fax.AsyncReceiveEvent.RECEIVE_INFO";

	static final String FAX_CONNECT_STATE_ACTION  = SDK_SERVICE_PREFIX + "fax.AsyncConnection.CONNECT_STATE";

    private AsyncConnectState mAsyncConnectState = null;

	public FaxEventReceiver() {
		super();
	}

	@Override
	public AsyncConnectState startReceiveAppStateEvent() {
		return setReceiveFunctionAppState(RECEIVE_FAX_APP_STATE_ACTION, true);
	}

	@Override
	public AsyncConnectState endReceiveAppStateEvent() {
		return setReceiveFunctionAppState(RECEIVE_FAX_APP_STATE_ACTION, false);
	}

	@Override
	public String startReceiveJobEvent() {
		return setReceiveFunctionJobEvent(RECEIVE_FAX_JOB_STATE_ACTION, true);
	}

	@Override
	public String endReceiveJobEvent() {
		return setReceiveFunctionJobEvent(RECEIVE_FAX_JOB_STATE_ACTION, false);
	}

	@Override
	public AsyncConnectState startReceiveFaxInfoEvent(){
		return setReceiveFunctionAppState(RECEIVE_FAX_RECEIVE_INFO_ACTION, true);
	}

	@Override
	public AsyncConnectState endReceiveFaxInfoEvent(){
		return setReceiveFunctionAppState(RECEIVE_FAX_RECEIVE_INFO_ACTION, false);
	}

	@Override
	String[] getActions() {
		String[] actions = new String[4];
		actions[0] = FAX_APP_EVENT_ACTION;
		actions[1] = FAX_JOB_EVENT_ACTION;
		actions[2] = FAX_INFO_EVENT_ACTION;
		actions[3] = FAX_CONNECT_STATE_ACTION;
		return actions;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		String action = intent.getAction();

		if(extras == null) return;

		if(FAX_APP_EVENT_ACTION.equals(action)) {
			String eventData = extras.getString("APP_STATE");
			if(eventData != null) {
				postEvent(EventData.EVENT_TYPE_APP_STATE, eventData);
			}

		} else if( FAX_JOB_EVENT_ACTION.equals(action)) {
			String eventData = extras.getString("JOB_EVENT");
			if(eventData != null) {
				postEvent(EventData.EVENT_TYPE_JOB_EVENT, eventData);
			}

		} else if(FAX_INFO_EVENT_ACTION.equals(action)) {
			String eventData = extras.getString("RECEIVE_INFO");
			if(eventData != null) {
				postEvent(EventData.EVENT_TYPE_RECEIVE_FAX, eventData);
			}

		} else if(FAX_CONNECT_STATE_ACTION.equals(action)) {
			boolean state = extras.getBoolean("STATE", false);
			int errorCode = extras.getInt("ERROR_CODE", 0);
			LogC.d(TAG, "fax.AsyncConnectState:" + state + "," + errorCode);
			synchronized (this) {
	            mAsyncConnectState = new AsyncConnectState(state, errorCode);
            }
		}
	}

    @Override
    public AsyncConnectState getAsyncConnectState() {
        synchronized (this) {
            return mAsyncConnectState;
        }
    }
}
