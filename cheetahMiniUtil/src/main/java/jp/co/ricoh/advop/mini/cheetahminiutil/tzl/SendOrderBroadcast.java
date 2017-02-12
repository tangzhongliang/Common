/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * eg.
 * this is main method for you
 * SendOrderBroadcast.getsInstance().asyncSendOrderBroadcast(context, JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_HDD_GET_CTL_HDD_AVAILABILITY, null,BroadCastCallback)
 * you may need permission
 * jp.co.ricoh.isdk.sdkservice.common.SdkService.APP_CMD_PERMISSION
 * jp.co.ricoh.isdk.sdkservice.common.SdkService.APP_EVENT_PERMISSION.
 */

public class SendOrderBroadcast {

    public static final String MACHINE_ADMIN_AUTH = "MACHINE_ADMIN_AUTH";
    public static final String USER_ADMIN_AUTH = "USER_ADMIN_AUTH";
    public static final String DOCUMENT_ADMIN_AUTH = "DOCUMENT_ADMIN_AUTH";
    public static final String NETWORK_ADMIN_AUTH = "NETWORK_ADMIN_AUTH";

    public static final String INTERFACE_NAME = "INTERFACE_NAME";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING = "jp.co.ricoh.isdk.sdkservice.auth.GET_AUTH_SETTING";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_SYS_RES_LOGIN_USER_INFO = "jp.co.ricoh.isdk.sdkservice.auth.SYS_RES_LOGIN_USER_INFO";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION = "jp.co.ricoh.isdk.sdkservice.common.SdkService.APP_EVENT_PERMISSION";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_REQUEST_LOGIN_USER_INFO = "jp.co.ricoh.isdk.sdkservice.auth.REQUEST_LOGIN_USER_INFO";
    public static String USER_AUTH = "USER_AUTH";
    public static String PRINTER_PERMISSION = "PRINTER_PERMISSION";
    public static String SCANNER_PERMISSION = "SCANNER_PERMISSION";
    public static String PRINTER_COLOR_MODE_PERMISSION = "PRINTER_COLOR_MODE_PERMISSION";
    public static String SUCCESSED = "SUCCESSED";
    final String TAG = this.getClass().getSimpleName();

    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_STATE = "jp.co.ricoh.isdk.sdkservice.auth.GET_AUTH_STATE";

    //order broadcast
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_POWER_MODE_LOCK_POWER_OFF = "jp.co.ricoh.isdk.sdkservice.system.PowerMode.LOCK_POWER_OFF";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_OFFLINE_MANAGER_LOCK_OFFLINE = "jp.co.ricoh.isdk.sdkservice.system.OfflineManager.LOCK_OFFLINE";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_POWER_MODE_LOCK_PANEL_OFF = "jp.co.ricoh.isdk.sdkservice.system.PowerMode.LOCK_PANEL_OFF";

    //normal broadcast
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_POWER_MODE_UNLOCK_POWER_OFF = "jp.co.ricoh.isdk.sdkservice.system.PowerMode.UNLOCK_POWER_OFF";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_OFFLINE_MANAGER_UNLOCK_OFFLINE = "jp.co.ricoh.isdk.sdkservice.system.OfflineManager.UNLOCK_OFFLINE";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_POWER_MODE_UNLOCK_PANEL_OFF = "jp.co.ricoh.isdk.sdkservice.system.PowerMode.UNLOCK_PANEL_OFF";

    //not useful
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_HDD_GET_CTL_HDD_AVAILABILITY = "jp.co.ricoh.isdk.sdkservice.system.Hdd.GET_CTL_HDD_AVAILABILITY";

    public static final String LOGIN_STATUS = "LOGIN_STATUS";
    public static final String IS_COPIER_AVAILABLE = "IS_COPIER_AVAILABLE";
    public static final String JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_HDD_POWER_ON_HDD = "jp.co.ricoh.isdk.sdkservice.system.Hdd.POWER_ON_HDD";
    public static final String PACKAGE_NAME = "PACKAGE_NAME";
    public static final String POWER_MODE = "POWER_MODE";
    public static final String HDD_AVAILABLE = "HDD_AVAILABLE";
    public static final String ALL = "all";
    private final int OFF_MODE = 5;
    public static final String RESULT = "RESULT";

    public static final String MACHINE_ADMIN_PERMISSION = "MACHINE_ADMIN_PERMISSION";
    public static final String IS_DOCUMENT_SERVER_AVAILABLE = "IS_DOCUMENT_SERVER_AVAILABLE";
    public static final String IS_FAX_AVAILABLE = "IS_FAX_AVAILABLE";
    public static final String IS_SCANNER_AVAILABLE = "IS_SCANNER_AVAILABLE";
    public static final String IS_PRINTER_AVAILABLE = "IS_PRINTER_AVAILABLE";
    public static final String IS_BROWSER_AVAILABLE = "IS_BROWSER_AVAILABLE";
    public static final String USER_ADMIN_PERMISSION = "USER_ADMIN_PERMISSION";
    public static final String DOCUMENT_ADMIN_PERMISSION = "DOCUMENT_ADMIN_PERMISSION";
    public static final String NETWORK_ADMIN_PERMISSION = "NETWORK_ADMIN_PERMISSION";
    public static final String CERTIFICATE_USER_PERMISSION = "CERTIFICATE_USER_PERMISSION";
    public static final String SUPERVISOR_PERMISSION = "SUPERVISOR_PERMISSION";
    public static final String BROWSER_PERMISSION = "BROWSER_PERMISSION";

    private static final int SLEEP_TIME = 200;
    private static final int CHECK_TIMES = 50;

    /**
     * key value is bool
     */
    private final ArrayList<String> mBoolKeyArrays = new ArrayList<String>(Arrays.asList(new String[]{
            RESULT,
            LOGIN_STATUS,
            IS_COPIER_AVAILABLE,
            IS_DOCUMENT_SERVER_AVAILABLE,
            IS_FAX_AVAILABLE,
            IS_SCANNER_AVAILABLE,
            IS_PRINTER_AVAILABLE,
            IS_BROWSER_AVAILABLE,
    }));
    /**
     * key value is none or all
     */
    private final ArrayList mNoneAndAllArrays = new ArrayList<String>(Arrays.asList(new String[]{
            MACHINE_ADMIN_PERMISSION,
            USER_ADMIN_PERMISSION,
            DOCUMENT_ADMIN_PERMISSION,
            NETWORK_ADMIN_PERMISSION,
            CERTIFICATE_USER_PERMISSION,
            SUPERVISOR_PERMISSION
    }));

    private static SendOrderBroadcast sInstance;

    public static SendOrderBroadcast getsInstance() {
        sInstance = new SendOrderBroadcast();
        return sInstance;
    }

    /**
     * The receiver to receive broadcast result.
     */
    public class SystemResultReceiver extends BroadcastReceiver {
        private BroadCastCallback callback;
        private String[] projections;
        private Object[] values;
        public Bundle resultExtras = null;
        private boolean isReturn = false;
        public String SUCCESSED = "SUCCESSED";
        public String FAILED = "FAILED";
        public String UNKOWN = "UNKOWN";
        boolean state = false;
        String result = UNKOWN;
        boolean isOrder = false;

        public SystemResultReceiver() {
        }

        public SystemResultReceiver(BroadCastCallback callback, String[] projections, Object[] values, boolean isOrder) {
            this.callback = callback;
            this.projections = projections;
            this.values = values;
            this.isOrder = isOrder;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            LogC.i(TAG, "action:" + intent.getAction());
            LogC.i(TAG, "action is Order" + isOrder);
            if (isOrder) {
                resultExtras = getResultExtras(true);
            } else {
                resultExtras = intent.getExtras();
            }
            String interfaceName = intent.getStringExtra(INTERFACE_NAME);
            if (resultExtras != null) {
                isReturn = true;
                LogC.i(TAG, "action :" + intent.getAction() + " return result");
                state = true;
                if (null == projections || null == values) {
                    return;
                }
                if (values.length != projections.length) {
                    throw new RuntimeException("projections and values's size must be same");
                }
                try {
                    for (int i = 0; i < projections.length; i++) {
                        Object result = resultExtras.get(projections[i]);
                        Object value = values[i];
                        state &= result.equals(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    state = false;
                }

                result = state ? SUCCESSED : FAILED;
                if (callback != null) {
                    if (state)
                        callback.onSuccess(interfaceName);
                    else
                        callback.onFailed(interfaceName);
                }
            } else {
                Log.i(TAG, "result is null/\ninterface name may be wrong\ninterface may be is not order broadcast\n");
                if (callback != null) {
                    callback.onFailed(interfaceName);
                }
            }
        }

        public boolean isReturn() {
            return isReturn;
        }

        public String getResult() {
            return result;
        }
    }


    public interface BroadCastCallback {

        void onSuccess(String interfaceName);

        void onFailed(String interfaceName);
    }

    public interface ResultCallback {

        void onSuccess();

        void onFailed();
    }

    /**
     * pass key arrays and value arrays which need to check whether result is right
     *
     * @param context
     * @param interfaceName broadcast action name
     * @param projections   value key you need to check
     * @param values        value you expect
     * @param callback
     */
    private void asyncSendOrderBroadcast(Context context, String interfaceName, Bundle inputBundle, String[] projections, Object[] values, final BroadCastCallback callback) {
        Intent intent = new Intent(interfaceName);
        if (null != inputBundle)
            intent.putExtras(inputBundle);
        intent.putExtra(PACKAGE_NAME, context.getPackageName());
        final SystemResultReceiver resultReceiver = new SystemResultReceiver(callback, projections, values, true);
        Bundle initialExtras = new Bundle();
        initialExtras.putString(INTERFACE_NAME, interfaceName);
        context.sendOrderedBroadcast(
                intent, // intent
                null, // permission
                resultReceiver, // receiver
                null, // scheduler
                0, // initialCode
                null, // initialData
                initialExtras); // initialExtras
    }

    /**
     * get stmartSDK's state
     *
     * @param context
     * @param interfaceName broadcast interface name that you want to get
     * @param projections   value key you need to check .it is null unless you check auth
     * @param callback
     */
    public void asyncSendOrderBroadcast(Context context, String interfaceName, String[] projections, final BroadCastCallback callback) {

        final String[] emptyProjections = new String[]{};
        final Object[] emptyValues = new Object[]{};

        final String[] resultProjections = new String[]{RESULT};
        final Object[] resultValues = new Object[]{true};
        Bundle bundle = new Bundle();
        if (JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_POWER_MODE_LOCK_POWER_OFF.equals(interfaceName)) {
            asyncSendOrderBroadcast(context, interfaceName, bundle, resultProjections, resultValues, callback);
        } else if (JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_OFFLINE_MANAGER_LOCK_OFFLINE.equals(interfaceName)) {
            asyncSendOrderBroadcast(context, interfaceName, emptyProjections, emptyValues, callback);
        } else if (JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_POWER_MODE_LOCK_PANEL_OFF.equals(interfaceName)) {
            asyncSendOrderBroadcast(context, interfaceName, resultProjections, resultValues, callback);
        } else if (JP_CO_RICOH_ISDK_SDKSERVICE_SYSTEM_HDD_GET_CTL_HDD_AVAILABILITY.equals(interfaceName)) {
            asyncSendOrderBroadcast(context, interfaceName, new String[]{HDD_AVAILABLE}, resultValues, callback);
        } else if (JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_STATE.equals(interfaceName)) {
            if (projections != null) {
                Object[] values = new Object[projections.length];
                for (int i = 0; i < projections.length; i++) {
                    if (mBoolKeyArrays.contains(projections[i])) {
                        values[i] = true;
                    } else if (mNoneAndAllArrays.contains(projections[i])) {
                        values[i] = ALL;
                    }
                }
                asyncSendOrderBroadcast(context, interfaceName, projections, values, callback);
            } else {
                throw new RuntimeException("projections must be not null if you want to get auth state");
            }
        } else {
            if (projections != null) {
                Object[] values = new Object[projections.length];
                for (int i = 0; i < projections.length; i++) {
                    if (mBoolKeyArrays.contains(projections[i])) {
                        values[i] = true;
                    } else if (mNoneAndAllArrays.contains(projections[i])) {
                        values[i] = ALL;
                    }
                }
                asyncSendOrderBroadcast(context, interfaceName, projections, values, callback);
            } else {
                throw new RuntimeException("projections must be not null if you want to get auth state");
            }
        }
    }

    public SystemResultReceiver syncSendOrderBroadcast(Context context, String interfaceName, String receivePermission, Bundle inputBundle) {
        Intent intent = new Intent(interfaceName);
        intent.putExtra(PACKAGE_NAME, context.getPackageName());
        if (inputBundle != null) {
            intent.putExtras(inputBundle);
        }
        final SystemResultReceiver resultReceiver = new SystemResultReceiver(null, null, null, true);
        Bundle initialExtras = new Bundle();
        initialExtras.putString(INTERFACE_NAME, interfaceName);

        context.sendOrderedBroadcast(
                intent, // intent
                receivePermission, // permission
                resultReceiver, // receiver
                null, // scheduler
                0, // initialCode
                null, // initialData
                initialExtras); // initialExtras
        int i;
        for (i = 0; i < CHECK_TIMES; i++) {
            try {
                Thread.sleep(SLEEP_TIME);
                if (resultReceiver.isReturn()) {
                    return resultReceiver;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LogC.i("receive result time out!" + interfaceName);
        return resultReceiver;
    }

    public SystemResultReceiver syncSendBroadcast(Context context, String interfaceName, String receivePermission, String receiveAction, Bundle inputBundle) {
        Intent intent = new Intent(interfaceName);
        if (null != inputBundle)
            intent.putExtras(inputBundle);
        if (receivePermission == null) {
            receivePermission = JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION;
        }
        IntentFilter filter = new IntentFilter();
        if (receiveAction != null)
            filter.addAction(receiveAction);
        final SystemResultReceiver resultReceiver = new SystemResultReceiver(null, null, null, false);
        try {
            context.registerReceiver(resultReceiver, filter, receivePermission, null);
        } catch (Exception e) {
            LogC.e(e.getMessage());
        }
        intent.putExtra(PACKAGE_NAME, context.getPackageName());
        Bundle initialExtras = new Bundle();
        initialExtras.putString(INTERFACE_NAME, interfaceName);
        context.sendBroadcast(intent); // initialExtras
        int i;
        for (i = 0; i < CHECK_TIMES; i++) {
            try {
                Thread.sleep(SLEEP_TIME);
                if (resultReceiver.isReturn()) {
                    try {
                        context.unregisterReceiver(resultReceiver);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return resultReceiver;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            context.unregisterReceiver(resultReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogC.i("receive result time out!" + interfaceName);
        return resultReceiver;
    }

    public void asyncSendOrderBroadcast(Context context, String interfaceName, String[] projections, Object[] values, BroadCastCallback callback) {
        asyncSendOrderBroadcast(context, interfaceName, null, projections, values, callback);
    }

    public void sendBroadcast(Context context, String interfaceName) {
        Intent intent = new Intent(interfaceName);
        intent.putExtra(PACKAGE_NAME, context.getPackageName());
        context.sendBroadcast(intent);
    }

    public void checkUserAuth(final Context context, String permission, final ResultCallback resultCallback) {
        checkUserAuth(context, permission, new AuthState(), resultCallback);
    }

    /**
     * can not be used in BroadcastReceiver that you register in manifest file
     *
     * @param context
     * @param permission
     * @param authState
     * @param resultCallback
     */
    public void checkUserAuth(final Context context, String permission, final AuthState authState, final ResultCallback resultCallback) {
        authState.permissionName = permission;
        authState.init();
        final BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                authState.userName = intent.getStringExtra(AuthState.USER_NAME);
                authState.result[0 + AuthState.COUNT] = ALL.equals(intent.getStringExtra(USER_ADMIN_PERMISSION));
                authState.result[1 + AuthState.COUNT] = ALL.equals(intent.getStringExtra(MACHINE_ADMIN_PERMISSION));
                authState.result[2 + AuthState.COUNT] = ALL.equals(intent.getStringExtra(NETWORK_ADMIN_PERMISSION));
                authState.result[3 + AuthState.COUNT] = ALL.equals(intent.getStringExtra(DOCUMENT_ADMIN_PERMISSION));
                authState.userResult = true;
                authState.isSuperUser = ALL.equals(intent.getStringExtra(SUPERVISOR_PERMISSION));
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_SYS_RES_LOGIN_USER_INFO);
        try {
            context.registerReceiver(receiver, filter);
        } catch (Exception e) {
            throw new RuntimeException("BroadcastReceiver do not allow register receiver,please user AuthBroadCastReceiver that you just need to extend");
        }
        SendOrderBroadcast.getsInstance().sendBroadcast(context, "jp.co.ricoh.isdk.sdkservice.auth.REQUEST_LOGIN_USER_INFO");
        Intent intent = new Intent(JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING);
        intent.putExtra(PACKAGE_NAME, context.getPackageName());
        final SystemResultReceiver resultReceiver = new SystemResultReceiver() {
            public Bundle resultExtras;

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
                for (int i = 0; i < CHECK_TIMES; i++) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                        if (authState.isReturn()) {
                            LogC.d(authState.toString());
                            if (resultCallback != null && authState.isGranted()) {
                                resultCallback.onSuccess();
                            } else if (resultCallback != null) {
                                resultCallback.onFailed();
                            }
                            context.unregisterReceiver(receiver);
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (resultCallback != null)
                    resultCallback.onFailed();
                context.unregisterReceiver(receiver);
            }
        }).start();
    }


    public ArrayList<String> getAdminList(Bundle resultExtras) {
        ArrayList<String> ret = new ArrayList<String>();
        if (resultExtras.getString(SendOrderBroadcast.DOCUMENT_ADMIN_PERMISSION).equals(SendOrderBroadcast.ALL)) {
            ret.add(SendOrderBroadcast.DOCUMENT_ADMIN_PERMISSION);
        }
        if (resultExtras.getString(SendOrderBroadcast.MACHINE_ADMIN_PERMISSION).equals(SendOrderBroadcast.ALL)) {
            ret.add(SendOrderBroadcast.MACHINE_ADMIN_PERMISSION);
        }
        if (resultExtras.getString(SendOrderBroadcast.NETWORK_ADMIN_PERMISSION).equals(SendOrderBroadcast.ALL)) {

            ret.add(SendOrderBroadcast.NETWORK_ADMIN_PERMISSION);
        }
        if (resultExtras.getString(SendOrderBroadcast.USER_ADMIN_PERMISSION).equals(SendOrderBroadcast.ALL)) {
            ret.add(SendOrderBroadcast.USER_ADMIN_PERMISSION);
        }
        if (resultExtras.getString(SendOrderBroadcast.SUPERVISOR_PERMISSION).equals(SendOrderBroadcast.ALL)) {
            ret.add(SendOrderBroadcast.SUPERVISOR_PERMISSION);
        }

        return ret;
    }

    public boolean checkBrowserPermission(Context context) {
        SendOrderBroadcast.SystemResultReceiver userResult = SendOrderBroadcast.getsInstance().syncSendBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_REQUEST_LOGIN_USER_INFO, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_SYS_RES_LOGIN_USER_INFO, null);
        SendOrderBroadcast.SystemResultReceiver settingResult = SendOrderBroadcast.getsInstance().syncSendOrderBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, null);
        return hasBrowserPermission(userResult, settingResult);
    }

    private boolean hasBrowserPermission(SystemResultReceiver userResult, SystemResultReceiver settingResult) {
        ArrayList<String> adminList = SendOrderBroadcast.getsInstance().getAdminList(userResult.resultExtras);
        if (settingResult.resultExtras.getBoolean(SendOrderBroadcast.USER_AUTH)) {
            if (adminList.size() > 0) {
                // is admin
                if (adminList.size() > 1 || !adminList.contains(SendOrderBroadcast.MACHINE_ADMIN_PERMISSION)) {
                    // just is not machine admin
                    LogC.i("user just is not machine admin");
                    return false;
                }
            } else {
                // user
                if (!userResult.resultExtras.getString(SendOrderBroadcast.BROWSER_PERMISSION).equals(SendOrderBroadcast.ALL)) {
                    LogC.i("user has no BROWSER_PERMISSION");
                    return false;
                }
            }
        } else {
            return true;
        }
        return true;
    }

    public boolean checkPrintPermission(Context context, String color) {
        SendOrderBroadcast.SystemResultReceiver userResult = SendOrderBroadcast.getsInstance().syncSendBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_REQUEST_LOGIN_USER_INFO, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_SYS_RES_LOGIN_USER_INFO, null);
        SendOrderBroadcast.SystemResultReceiver settingResult = SendOrderBroadcast.getsInstance().syncSendOrderBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, null);

        return hasPrintPermission(color, userResult, settingResult);
    }

    public Boolean[] checkScanPermission(Context context) {
        SendOrderBroadcast.SystemResultReceiver userResult = SendOrderBroadcast.getsInstance().syncSendBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_REQUEST_LOGIN_USER_INFO, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_SYS_RES_LOGIN_USER_INFO, null);
        SendOrderBroadcast.SystemResultReceiver settingResult = SendOrderBroadcast.getsInstance().syncSendOrderBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, null);
        Boolean[] ret = {false, false};
        ret[0] = hasScanPermission(userResult, settingResult);
        if (userResult.isReturn()) {
            ret[1] = userResult.resultExtras.getString(MACHINE_ADMIN_PERMISSION).equals(ALL);
        }
        return ret;
    }

    private boolean hasScanPermission(SystemResultReceiver userResult, SystemResultReceiver settingResult) {
        ArrayList<String> adminList = SendOrderBroadcast.getsInstance().getAdminList(userResult.resultExtras);
        if (adminList.size() > 0) {
            // is admin
            LogC.i("user is admin");
            return false;
        }

        if (settingResult.resultExtras.getBoolean(SendOrderBroadcast.USER_AUTH)) {
            String string = userResult.resultExtras.getString(SendOrderBroadcast.SCANNER_PERMISSION);
            if (string.equals("none")) {
                LogC.i("scanner permission is " + string);
                return false;
            }
        }
//        //basic auth is off
//        //user auth is on
//        if (!settingResult.resultExtras.getBoolean(SendOrderBroadcast.USER_AUTH) && settingResult.resultExtras.getBoolean(SendOrderBroadcast.USER_ADMIN_AUTH)) {
//            //
//            if (userResult.resultExtras.getString(RESULT).equals(RESULT)) {
//                return false;
//            }
//        }
        return true;
    }

    public boolean[] checkBrowserPrintPermission(Context context, String color) {
        boolean[] ret = new boolean[2];
        SendOrderBroadcast.SystemResultReceiver userResult = SendOrderBroadcast.getsInstance().syncSendBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_REQUEST_LOGIN_USER_INFO, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_SYS_RES_LOGIN_USER_INFO, null);
        SendOrderBroadcast.SystemResultReceiver settingResult = SendOrderBroadcast.getsInstance().syncSendOrderBroadcast(context, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_AUTH_GET_AUTH_SETTING, SendOrderBroadcast.JP_CO_RICOH_ISDK_SDKSERVICE_COMMON_SDK_SERVICE_APP_EVENT_PERMISSION, null);
        ret[0] = hasBrowserPermission(userResult, settingResult);
        ret[1] = hasPrintPermission(color, userResult, settingResult);
        return ret;
    }

    private boolean hasPrintPermission(String color, SystemResultReceiver userResult, SystemResultReceiver settingResult) {
        ArrayList<String> adminList = SendOrderBroadcast.getsInstance().getAdminList(userResult.resultExtras);
        if (adminList.size() > 0) {
            // is admin
            LogC.i("user is admin");
            return false;
        }

        if (settingResult.resultExtras.getBoolean(SendOrderBroadcast.USER_AUTH)) {

            String string = userResult.resultExtras.getString(SendOrderBroadcast.PRINTER_PERMISSION);
            if (color != null && !string.equals(string)) {
                return false;
            } else if (string.equals("none")) {
                LogC.i("print permission is " + string);
                return false;
            }
        }
        return true;
    }

    public class AuthState {
        public static final int COUNT = 4;
        public static final boolean BASIC_AUTH_ON = false;
        public final String UNKNOWN = "UNKNOWN";
        //    public boolean enable = true;
        public static final String USER_NAME = "USER_NAME";

        public String permissionName = MACHINE_ADMIN_AUTH;
        public String userName = "";
        public boolean userAuth = false;

        public boolean USER_AUTH_ON = false;
        public boolean MACHINE_AUTH_ON = false;
        public boolean DOCUMENT_AUTH_ON = false;
        public boolean NETWORK_AUTH_ON = false;

        public boolean USER_AUTH_STATE = false;
        public boolean MACHINE_AUTH_STATE = false;
        public boolean DOCUMENT_AUTH_STATE = false;
        public boolean NETWORK_AUTH_STATE = false;

        public boolean userResult = false;
        public boolean authSettingResult = false;
        String[] permissions = new String[]{USER_ADMIN_AUTH, MACHINE_ADMIN_AUTH, NETWORK_ADMIN_AUTH, DOCUMENT_ADMIN_AUTH};
        boolean[] result = new boolean[]{USER_AUTH_ON, MACHINE_AUTH_ON, NETWORK_AUTH_ON, DOCUMENT_AUTH_ON,
                USER_AUTH_STATE, MACHINE_AUTH_STATE, NETWORK_AUTH_STATE, DOCUMENT_AUTH_STATE, BASIC_AUTH_ON};
        boolean isSuperUser = false;

        public AuthState(String permissionName) {
            this.permissionName = permissionName;
        }

        public AuthState() {
        }

        public void init() {
            USER_AUTH_ON = false;
            MACHINE_AUTH_ON = false;
            DOCUMENT_AUTH_ON = false;
            NETWORK_AUTH_ON = false;

            USER_AUTH_STATE = false;
            MACHINE_AUTH_STATE = false;
            DOCUMENT_AUTH_STATE = false;
            NETWORK_AUTH_STATE = false;

            isSuperUser = false;
            userResult = false;
            authSettingResult = false;
//        enable = true;
        }


        public boolean isGranted() {
            if (!isReturn()) {
                return false;
            }
            List<String> strings = Arrays.asList(permissions);
            int index = strings.indexOf(permissionName);
            if (isSuperUser) {
                return false;
            }
            if (result[index + COUNT]) {
                //machine permission
                return true;
            } else {
                // no machine permission
                if (result[index]) {
                    //machine permission on
                    return false;
                } else {
                    //machine permission off
                    //new
                    boolean ret1 = false;
                    for (int i = 0; i < COUNT; i++) {
                        ret1 |= result[COUNT + i];
                    }
                    if (userName != null && ret1) {
                        //login & has other admin permission
                        return false;
                    } else {
                        //logout or normal user
                        if (userName == null) {
                            //logout
                            if (result[result.length - 1]) {
                                //enable user auth
                                return false;
                            } else {
                                //unable user auth
                                return true;
                            }
                        } else {
                            //normal user
                            return true;
                        }
                    }
                    //new end
                }
            }
        }

        public boolean isReturn() {
            return userResult && authSettingResult;
        }

        @Override
        public String toString() {
            return Arrays.toString(result);
        }
    }
}
