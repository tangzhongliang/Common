/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.application;

import java.util.ArrayList;

import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.InitParameters;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.MediaInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LanguageUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

abstract public class BaseApplication extends SmartSDKApplication {
    private String pathPackageFolder = "";
    private SystemStateMonitor mSystemStateMonitor;

    /**
     * システム状態監視 System state monitor
     */

    @Override
    public void onCreate() {
        LogC.i("Application onCreate");
        super.onCreate();
        new CHolder(this).init();

        mSystemStateMonitor = new SystemStateMonitor(this);
        mSystemStateMonitor.start();

        CHolder.instance().setInitParameters(getInitParameters());
        CHolder.instance().getScanManager().onAppInit();
        CHolder.instance().getPrintManager().onAppInit();
        pathPackageFolder = Const.PATH_PACKAGE_FOLDER;

        //LanguageUtil.setLanguage(getContext(), CHolder.instance().getInitParameters().getAppLocale());
    }

    abstract protected InitParameters getInitParameters();

    public boolean waitHDDStart() {
        long time = 20000;
        long interval = 100;
        while (mSystemStateMonitor.getHddAvailable() != SystemStateMonitor.HDD_STATE_AVAILABLE) {
            if (time <= 0) {
                LogC.w("wait HDD available timeout!");
                return false;
            }
            LogC.d("wait HDD available");
            CUtil.sleep(interval);
            time -= interval;
        }
        return true;
    }

    public synchronized ArrayList<MediaInfo> getMediaState() {
        ArrayList arrayList = new ArrayList();
        Intent intentSD = new Intent();
        intentSD.setAction("jp.co.ricoh.advop.monitorservice.GET_MEDIA_STATE");
        intentSD.putExtra("MEDIA_TYPE", 0);
        final Bundle resultExtraSD = syncExecSendOrderedBroadCast(intentSD);
        if (resultExtraSD == null) {
            return arrayList;
        }
        String pathSD = resultExtraSD.getString("MEDIA_PATH");
        int stateSD = resultExtraSD.getInt("MOUNT_STATE");
        if (stateSD == 1) {
            arrayList.add(new MediaInfo(pathSD, 0));
        }

        Intent intentUSB = new Intent();
        intentUSB.setAction("jp.co.ricoh.advop.monitorservice.GET_MEDIA_STATE");
        intentUSB.putExtra("MEDIA_TYPE", 1);
        final Bundle resultExtraUSB = syncExecSendOrderedBroadCast(intentUSB);
        String pathUSB = resultExtraUSB.getString("MEDIA_PATH");
        int stateUSB = resultExtraUSB.getInt("MOUNT_STATE");
        if (stateUSB == 1) {
            arrayList.add(new MediaInfo(pathUSB, 1));
        }

        LogC.d(pathSD + "-----" + stateSD);
        LogC.d(pathUSB + "-----" + stateUSB);
        return arrayList;
    }

    @Override
    public void onTerminate() {
        LogC.i("Application onTerminate");
        super.onTerminate();
        mSystemStateMonitor.stop();
        CHolder.instance().getScanManager().onAppDestroy();
        CHolder.instance().getPrintManager().onAppDestroy();
    }

    public void exit() {
        LogC.i("Application exit");
        super.exit();
        mSystemStateMonitor.stop();
        CHolder.instance().getScanManager().onAppDestroy();
        CHolder.instance().getPrintManager().onAppDestroy();
        System.exit(0);
    }

    public Boolean lockLogout() {
        // if (!mSystemStateMonitor.isLogin()) {
        // LogC.d("no user login, ignore lockLogout");
        // return true;
        // }

        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.auth.LOCK_LOGOUT");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d("lockLogout() PACKAGE_NAME=" + packageName);

        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra == null) {
            LogC.e("lockLogout request : No response.(timeout)");
            return false;
        }
        return (Boolean) resultExtra.get("RESULT");
    }

    public Boolean unlockLogout() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.auth.UNLOCK_LOGOUT");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d("unlockLogout() PACKAGE_NAME=" + packageName);

        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
        return true;
    }

    public Boolean lockPanelOff() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.PowerMode.LOCK_PANEL_OFF");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d("lockPanelOff() PACKAGE_NAME=" + packageName);

        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra == null) {
            LogC.e("lockPanelOff request : No response.(timeout)");
            return false;
        }
        return (Boolean) resultExtra.get("RESULT");
    }

    public Boolean unlockPanelOff() {
        final String packageName = mApplicationContext.getPackageName();

        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.system.PowerMode.UNLOCK_PANEL_OFF");
        intent.putExtra("PACKAGE_NAME", packageName);

        LogC.d("unlockPanelOff() PACKAGE_NAME=" + packageName);

        mApplicationContext.sendBroadcast(intent, APP_CMD_PERMISSION);
        return true;
    }

    // async
    public boolean initLoginUserInfo() {
        boolean ret = false;

        // login setting
        final Intent intentLogin = new Intent();
        intentLogin.setAction("jp.co.ricoh.isdk.sdkservice.auth.GET_AUTH_STATE");

        LogC.d("getLoginUserInfo()");

        final Bundle resultExtraLogin = syncExecSendOrderedBroadCast(intentLogin);
        if (resultExtraLogin == null) {
            LogC.e("getLoginUserInfo request : No response.(timeout)");
            return false;
        }
        mSystemStateMonitor.setLoginUser(resultExtraLogin);
        ret = (Boolean) resultExtraLogin.get("RESULT");
        if (!ret) {
            return ret;
        }

        // admin auth setting
        final Intent intent = new Intent();
        intent.setAction("jp.co.ricoh.isdk.sdkservice.auth.GET_AUTH_SETTING_DETAIL");

        LogC.d("getLoginUserInfo() GET_AUTH_SETTING_DETAIL");

        final Bundle resultExtra = syncExecSendOrderedBroadCast(intent);
        if (resultExtra == null) {
            LogC.e("getLoginUserInfo GET_AUTH_SETTING_DETAIL request : No response.(timeout)");
            return false;
        }
        mSystemStateMonitor.setAdminAuthON(resultExtra);
        return (Boolean) resultExtra.get("RESULT");
    }

    public String getPathPackageFolder() {
        return pathPackageFolder;
    }

    public SystemStateMonitor getmSystemStateMonitor() {
        return mSystemStateMonitor;
    }

    public int getTimeOfWaitingNextOriginal() {
        return 0;
    }
}
