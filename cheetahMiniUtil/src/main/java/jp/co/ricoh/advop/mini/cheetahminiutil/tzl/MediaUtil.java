/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.InitParameters;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.MediaEventReceiver;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.MediaInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import static jp.co.ricoh.isdk.sdkservice.common.intent.Constants.APP_CMD_PERMISSION;


public class MediaUtil {

    public static final String ACTION = "jp.co.ricoh.advop.monitorservice.GET_MEDIA_STATE";

    Context context;

    public ArrayList<MediaInfo> getMediaState(Context context) {
        this.context = context;
        ArrayList arrayList = new ArrayList();
        try {
            Intent intentSD = new Intent();
            intentSD.setAction(ACTION);
            intentSD.putExtra("MEDIA_TYPE", 0);
            Bundle bundle = new Bundle();
            bundle.putInt("MEDIA_TYPE", 0);
            final Bundle resultExtraSD = SendOrderBroadcast.getsInstance().syncSendOrderBroadcast(context, ACTION, APP_CMD_PERMISSION, bundle).resultExtras;
            String pathSD = resultExtraSD.getString("MEDIA_PATH");
            int stateSD = resultExtraSD.getInt("MOUNT_STATE");
            if (stateSD == 1) {
                arrayList.add(new MediaInfo(pathSD, 0));
            }

            Intent intentUSB = new Intent();
            intentUSB.setAction("jp.co.ricoh.advop.monitorservice.GET_MEDIA_STATE");
            intentUSB.putExtra("MEDIA_TYPE", 1);
            bundle = new Bundle();
            bundle.putInt("MEDIA_TYPE", 1);
            final Bundle resultExtraUSB = SendOrderBroadcast.getsInstance().syncSendOrderBroadcast(context, ACTION, APP_CMD_PERMISSION, bundle).resultExtras;

            String pathUSB = resultExtraUSB.getString("MEDIA_PATH");
            int stateUSB = resultExtraUSB.getInt("MOUNT_STATE");
            if (stateUSB == 1) {
                arrayList.add(new MediaInfo(pathUSB, 1));
            }

            LogC.d(pathSD + "-----" + stateSD);
            LogC.d(pathUSB + "-----" + stateUSB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
