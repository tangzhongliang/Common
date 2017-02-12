/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import java.util.ArrayList;
import java.util.List;

/**
 * SmartSDK上で動作するアプリケーションを示すクラスです。
 * SmartSDK上で動作するアプリを作成するときは、このクラスを継承します。
 * This class indicates the application which runs on SmartSDK.
 * This class is inherited when creating an application which runs on SmartSDK.
 */
public class SmartSDKApplication extends Application {

    private static String TAG = SmartSDKApplication.class.getSimpleName();

    protected static Context mApplicationContext;

    /**
     * ロック対象の電力モード：エンジンOFF状態
     * Power mode to be locked: Engine off state
     */
    private static final int PROHIBITED_POWER_MODE = 4;

    /**
     * SDKServiceのパーミッション
     * Permission of SDKService
     */
    protected static final String APP_CMD_PERMISSION =
            "jp.co.ricoh.isdk.sdkservice.common.SdkService.APP_CMD_PERMISSION";

    /**
     * プロダクトID
     * productId
     */
    private static String mProductId = null;

    /**
     * 内部管理するレシーバーリスト
     * ブロードキャストレシーバーの登録解除忘れのため、内部で登録されたレシーバーを管理します
     * The receiver list managed internally
     * Manages the receivers registered internally for the case of missing to clear broadcast receiver registration.
     */
    private final List<BroadcastReceiver> receiverList = new ArrayList<BroadcastReceiver>();

    /**
     * 起動直後にContextを参照した際に、nullを返す可能性があるため同期処理用ロック
     * Lock synchronous processes since null may be returned at the time of checking Context immediately after startup
     */
    private static final Object contextLock = new Object();


    @Override
    public void onCreate() {
        super.onCreate();

        synchronized (contextLock) {
            mApplicationContext = getApplicationContext();
            contextLock.notifyAll();
        }

        // obtain product id from the application tag in AndroidManifest
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            mProductId = String.valueOf(appInfo.metaData.getInt("productId", 0));
        } catch (PackageManager.NameNotFoundException e) {
            LogC.w(e);
            mProductId = "";
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        synchronized (this.receiverList) {
            for (BroadcastReceiver receiver : receiverList) {
                this.unregisterReceiver(receiver);
            }
        }

    }

    protected void exit() {
        synchronized (this.receiverList) {
            for (BroadcastReceiver receiver : receiverList) {
                super.unregisterReceiver(receiver);
            }
            receiverList.clear();
        }

    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String permission, Handler schedule) {
        Intent retIntent = super.registerReceiver(receiver, filter, permission, schedule);

        synchronized (this.receiverList) {
            this.receiverList.add(receiver);
        }

        return retIntent;
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            super.unregisterReceiver(receiver);
        } catch (Exception e) {

        }
        if (receiverList.contains(receiver))
            synchronized (this.receiverList) {
                this.receiverList.remove(receiver);
            }
    }

    /**
     * 自身のコンテキストへの参照を返します。
     * Returns the reference to its own context.
     *
     * @return コンテキスト
     * context
     */
    public static synchronized Context getContext() {
        synchronized (contextLock) {
            // wait until onCreate() called (timeout: 5sec)
            if (mApplicationContext == null) {
                try {
                    contextLock.wait(5000);
                } catch (InterruptedException ex) {
                    LogC.w(ex);
                }
            }
        }
        return mApplicationContext;
    }

    public static String getProductId() {
        return mProductId;
    }

    /**
     * SDKServiceのAPIバージョン文字列を取得します。
     * バージョン取得APIがサポートされていない場合は、"1.0"を返します。
     * なお、このメソッドはバックグラウンドスレッドから呼び出してください。
     * Obtains the version string of SDKService API.
     * If the API to obtain version is not supported, "1.0" is returned.
     * Call this method from background thread.
     *
     * @return String of SDKService API version
     */
    public String getSdkServiceApiVersion() {
        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.common.GET_API_VERSION");

        String version = null;
        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra != null) {
            version = resultExtra.getString("VERSION");
        }

        return (version != null) ? version : "1.0";
    }

    private static String DISPLAY_ALERT_DIALOG = "jp.co.ricoh.isdk.sdkservice.panel.intent.AlertDialog.DISPLAY_ALERT_DIALOG";
    private static String UPDATE_ALERT_DIALOG = "jp.co.ricoh.isdk.sdkservice.panel.intent.AlertDialog.UPDATE_ALERT_DIALOG";
    private static String HIDE_ALERT_DIALOG = "jp.co.ricoh.isdk.sdkservice.panel.intent.AlertDialog.HIDE_ALERT_DIALOG";

    /**
     * 警告ダイアログを表示します。
     * アプリの状態とその理由を表示します。
     * Displays warning dialog.
     * Displays application state and its reason.
     *
     * @param appType アプリケーションの種別
     *                Application type
     * @param state   アプリの状態
     *                Application state
     * @param reason  理由
     *                reason
     */
    public void displayAlertDialog(String appType, String state, String reason) {
        Intent intent = new Intent(DISPLAY_ALERT_DIALOG);
        intent.putExtra("PACKAGE_NAME", mApplicationContext.getPackageName());
        intent.putExtra("APP_TYPE", appType);
        intent.putExtra("STATE", state);
        intent.putExtra("STATE_REASON", reason);
        mApplicationContext.sendBroadcast(intent);
        LogC.d(TAG, "DISPLAY_ALERT_DIALOG:" + intent.getExtras().getString("STATE_REASON"));
    }

    /**
     * 警告ダイアログを更新します。
     * Updates the warning dialog.
     *
     * @param appType アプリケーションの種別
     *                Application type
     * @param state   アプリの状態
     *                Application state
     * @param reason  理由
     *                reason
     */
    public void updateAlertDialog(String appType, String state, String reason) {
        Intent intent = new Intent(UPDATE_ALERT_DIALOG);
        intent.putExtra("PACKAGE_NAME", mApplicationContext.getPackageName());
        intent.putExtra("APP_TYPE", appType);
        intent.putExtra("STATE", state);
        intent.putExtra("STATE_REASON", reason);
        mApplicationContext.sendBroadcast(intent);
        LogC.d(TAG, "UPDATE_ALERT_DIALOG:" + intent.getExtras().getString("STATE_REASON"));
    }

    /**
     * 警告ダイアログを非表示にします。
     * Hides the warning dialog.
     *
     * @param appType      アプリケーションの種別
     *                     Application type
     * @param activityName アクティビティの名称
     *                     Activity name
     */
    public void hideAlertDialog(String appType, String activityName) {
        Intent intent = new Intent(HIDE_ALERT_DIALOG);
        intent.putExtra("PACKAGE_NAME", mApplicationContext.getPackageName());
        intent.putExtra("ACTIVITY_NAME", activityName);
        mApplicationContext.sendBroadcast(intent);
        LogC.d(TAG, "HIDE_ALERT_DIALOG:" + intent.getExtras().getString("ACTIVITY_NAME"));
    }

    /**
     * 電力モード移行ロックします。 ロックするモードは低電力モードです。
     * なお、このメソッドはバックグラウンドスレッドから呼び出してください。
     * Locking power mode. The mode to be locked is low-power mode.
     * Call this method from background thread.
     *
     * @return true: 成功 , false: 失敗
     * true: successful , false: failed
     */
    public Boolean lockPowerMode() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.PowerMode.LOCK_POWER_MODE");
        intent.putExtra("PACKAGE_NAME", packageName);
        intent.putExtra("POWER_MODE", PROHIBITED_POWER_MODE);

        LogC.d(TAG,
                "lockPowerMode() PACKAGE_NAME=" + packageName + ", POWER_MODE="
                        + PROHIBITED_POWER_MODE);

        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra == null) {
            LogC.e(TAG, "LOCK_POWER_MODE request : No response.(timeout)");
            return false;
        }
        return (Boolean) resultExtra.get("RESULT");
    }

    /**
     * 電力モード移行ロックを解除します。
     * Clears power mode locking.
     *
     * @return
     */
    public Boolean unlockPowerMode() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.PowerMode.UNLOCK_POWER_MODE");
        intent.putExtra("PACKAGE_NAME", packageName);
        intent.putExtra("POWER_MODE", PROHIBITED_POWER_MODE);

        LogC.d(TAG,
                "unlockPowerMode() PACKAGE_NAME=" + packageName + ", POWER_MODE="
                        + PROHIBITED_POWER_MODE);

        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
        return true;
    }

    /**
     * オフライン移行ロックします。
     * なお、このメソッドはバックグラウンドスレッドから呼び出してください。
     * Locking offline.
     * Call this method from background thread.
     *
     * @return true: 成功 , false: 失敗
     * true: successful , false: failed
     */
    public Boolean lockOffline() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.OfflineManager.LOCK_OFFLINE");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d(TAG,
                "lockOffline() PACKAGE_NAME=" + packageName);

        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra == null) {
            LogC.e(TAG, "LOCK_OFFLINE request : No response.(timeout)");
            return false;
        }
        return (Boolean) resultExtra.get("RESULT");
    }

    /**
     * オフライン移行ロックを解除します。
     * Clears offline locking.
     *
     * @return
     */
    public Boolean unlockOffline() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.OfflineManager.UNLOCK_OFFLINE");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d(TAG,
                "unlockOffline() PACKAGE_NAME=" + packageName);

        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
        return true;
    }

    /**
     * システムリセットが動作するのを抑止(ロック)します。
     * アプリケーション前面表示中、かつジョブ実行中のみ実行してください。
     * アプリケーションがバックグラウンドに遷移した場合は、すみやかにロックを解除してください。
     * Locking system auto reset.
     * Locking it when application is in foreground and job is executing.
     * Clear locking when application has been changed to background.
     */
    public void lockSystemReset() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.SystemManager.LOCK_SYSTEM_RESET");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d(TAG, "lockSystemReset() PACKAGE_NAME=" + packageName);

        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
    }

    /**
     * システムリセットの抑止(ロック)を解除します。
     * ジョブが終了した場合、アプリケーションがバックグラウンドに遷移した場合に実行してください。
     * Clear system auto reset locking.
     * Clear locking when job has finished or application has been changed to background.
     */
    public void unlockSystemReset() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.SystemManager.UNLOCK_SYSTEM_RESET");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d(TAG, "unlockSystemReset() PACKAGE_NAME=" + packageName);

        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
    }

    /**
     * アプリ状態設定で指定するエラー状態の定義
     * 0: 正常
     * 1: エラー
     * 2: 警告
     * Definition of error state specified for application state setting
     * 0: Normal
     * 1: Error
     * 2: Warning
     */
    public static final int APP_STATE_NORMAL = 0;
    public static final int APP_STATE_ERROR = 1;
    public static final int APP_STATE_WARNING = 2;

    /**
     * アプリ状態設定で指定する正常状態のメッセージ
     * Definition of normal state message specified for application state setting
     */
    public static final String APP_STATE_NORMAL_MSG = "";

    /**
     * アプリ状態設定で指定するアプリ種類の定義
     * 0: コピー
     * 1: スキャン
     * 2: ファクス
     * 3: プリンタ
     * Definition of application type specified for application state setting
     * 0: Copier
     * 1: Scanner
     * 2: FAX
     * 3: Printer
     */
    public static final int APP_TYPE_COPY = 0;
    public static final int APP_TYPE_SCANNER = 1;
    public static final int APP_TYPE_FAX = 2;
    public static final int APP_TYPE_PRINTER = 3;

    /**
     * アプリ状態を設定します。
     * ここで設定したアプリ状態は、状態確認画面に表示されます。
     * Specifies application state.
     * The specified application state is displayed on Check Status screen.
     *
     * @param activityName 状態確認画面からの遷移先Activity名
     *                     Name of the Activity changed from Check Status screen
     * @param state        エラー状態(APP_STATE_xxxx)
     *                     Error state (APP_STATE_xxxx)
     * @param errMessage   エラーメッセージ
     *                     Error message
     * @param appType      アプリ種類(APP_TYPE_xxxx)
     *                     Application type (APP_TYPE_xxxx)
     */
    public void setAppState(String activityName, int state, String errMessage, int appType) {
        // TODO
//        Intent intent = new Intent("jp.co.ricoh.isdk.sdkservice.panel.SET_APP_STAT");
//        intent.putExtra("PACKAGE_NAME", mApplicationContext.getPackageName());
//        intent.putExtra("ACTIVITY_NAME", activityName);
//        intent.putExtra("STAT", state);
//        intent.putExtra("ERR_MESSAGE", errMessage);
//        intent.putExtra("APP_TYPE", appType);
//
//        LogC.d(TAG, "SET_APP_STAT:" + state);
//        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
    }


    /**
     * システム状態要求で指定する要求状態の定義
     * 0: 待機定着OFF状態への復帰要求
     * 1: 通常待機状態への復帰要求
     * Definition of the state to request to be specified for requesting system state.
     * 0: Request to recover to fusing unit off state
     * 1: Request to recover to normal waiting state
     */
    public static final int REQUEST_CONTROLLER_STATE_FUSING_UNIT_OFF = 0;
    public static final int REQUEST_CONTROLLER_STATE_NORMAL_STANDBY = 1;

    /**
     * システム状態要求を行います。
     * なお、このメソッドはバックグラウンドスレッドから呼び出してください。
     * Requests for system state.
     * Call this method from background thread.
     *
     * @param requestState 要求状態 (REQUEST_CONTROLLER_STATE_xxxx)
     *                     State to request (REQUEST_CONTROLLER_STATE_xxxx)
     * @return true: 受付成功 false: 受付失敗
     * true: Successfully accepted, false: Failed to accept
     */
    public Boolean controllerStateRequest(int requestState) {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.PowerMode.CONTROLLER_STATE_REQUEST");
        intent.putExtra("PACKAGE_NAME", packageName);
        intent.putExtra("STATE", requestState);

        LogC.d(TAG,
                "controllerStateRequest() PACKAGE_NAME=" + packageName + " STATE=" + requestState);

        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra == null) {
            LogC.e(TAG, "CONTROLLER_STATE_REQUEST request : No response.(timeout)");
            return false;
        }
        return (Boolean) resultExtra.get("RESULT");
    }


    protected Bundle syncExecSendOrderedBroadCast(Intent intent) {

        final SystemResultReceiver resultReceiver = new SystemResultReceiver();

        this.sendOrderedBroadcast(
                intent, // intent
                APP_CMD_PERMISSION, // permission
                resultReceiver, // receiver
                null, // scheduler
                0, // initialCode
                null, // initialData
                new Bundle()); // initialExtras

        return resultReceiver.getResultExtras();
    }

    /**
     * ブロードキャストの結果を受け取るレシーバーです。
     * The receiver to receive broadcast result.
     */
    private static class SystemResultReceiver extends BroadcastReceiver {
        private Bundle resultExtras = null;
        private Object lockObj = new Object();

        @Override
        public void onReceive(Context context, Intent intent) {
            synchronized (lockObj) {
                resultExtras = getResultExtras(true);
                lockObj.notifyAll();
            }
        }

        public Bundle getResultExtras() {
            synchronized (lockObj) {
                for (int i = 0; i < 5; i++) {
                    if (resultExtras != null) {
                        return resultExtras;
                    }

                    try {
                        lockObj.wait(1000);
                    } catch (InterruptedException ignore) {
                    }
                }
                return null;
            }
        }
    }
}
