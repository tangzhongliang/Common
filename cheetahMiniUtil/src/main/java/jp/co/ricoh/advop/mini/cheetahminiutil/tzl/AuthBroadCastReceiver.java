/*
 * Copyright (c) 2016.  RICOH Co.,LTD.
 * All rights reserved.
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import static jp.co.ricoh.advop.mini.cheetahminiutil.tzl.SendOrderBroadcast.AuthState.*;
import static jp.co.ricoh.advop.mini.cheetahminiutil.tzl.SendOrderBroadcast.*;


public class AuthBroadCastReceiver extends AppWidgetProvider {

    String TAG = getClass().getSimpleName();
    public static SendOrderBroadcast.AuthState sAuthState = null;

    public static Handler mHandler = new Handler();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if ("jp.co.ricoh.isdk.sdkservice.auth.SYS_RES_LOGIN_USER_INFO".equals(intent.getAction())) {
            if (sAuthState != null) {
                sAuthState.userName = intent.getStringExtra(USER_NAME);
                sAuthState.result[0 + COUNT] = ALL.equals(intent.getStringExtra(USER_ADMIN_PERMISSION));
                sAuthState.result[1 + COUNT] = ALL.equals(intent.getStringExtra(MACHINE_ADMIN_PERMISSION));
                sAuthState.result[2 + COUNT] = ALL.equals(intent.getStringExtra(NETWORK_ADMIN_PERMISSION));
                sAuthState.result[3 + COUNT] = ALL.equals(intent.getStringExtra(DOCUMENT_ADMIN_PERMISSION));
                sAuthState.userResult = true;
                sAuthState.isSuperUser = ALL.equals(intent.getStringExtra(SUPERVISOR_PERMISSION));
            }
        }
    }

    public void checkUserAuth(final Context context, String permission, final SendOrderBroadcast.AuthState authState, final ResultCallback resultCallback) {
        authState.permissionName = permission;
        authState.init();
        SendOrderBroadcast.getsInstance().sendBroadcast(context, "jp.co.ricoh.isdk.sdkservice.auth.REQUEST_LOGIN_USER_INFO");

        Intent intent = new Intent(JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING);
        intent.putExtra(PACKAGE_NAME, context.getPackageName());
        final BroadcastReceiver resultReceiver = new BroadcastReceiver() {

            private Bundle resultExtras;

            @Override
            public void onReceive(Context context, Intent intent) {
                //record user info
                resultExtras = getResultExtras(true);
                authState.result[0] = resultExtras.getBoolean(USER_ADMIN_AUTH, false);
                authState.result[1] = resultExtras.getBoolean(MACHINE_ADMIN_AUTH, false);
                authState.result[2] = resultExtras.getBoolean(NETWORK_ADMIN_AUTH, false);
                authState.result[3] = resultExtras.getBoolean(DOCUMENT_ADMIN_AUTH, false);
                authState.result[authState.result.length - 1] = resultExtras.getBoolean(USER_AUTH, false);
                authState.authSettingResult = true;
            }
        };
        Bundle initialExtras = new Bundle();
        initialExtras.putString(INTERFACE_NAME, JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING);

        context.sendOrderedBroadcast(
                intent, // intent
                null, // permission
                resultReceiver, // receiver
                null, // scheduler
                0, // initialCode
                null, // initialData
                initialExtras); // initialExtras
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i += 50) {
                    try {
                        Thread.sleep(50);
                        if (authState.isReturn()) {
                            if (resultCallback != null && authState.isGranted()) {
                                resultCallback.onSuccess();
                            } else if (resultCallback != null) {
                                resultCallback.onFailed();
                            }
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (resultCallback != null) {
                    Log.e(TAG, "get Auth time out \n maybe you do not register action:jp.co.ricoh.isdk.sdkservice.auth.SYS_RES_LOGIN_USER_INFO");
                    resultCallback.onFailed();
                }
            }
        }).start();
    }

}
