package jp.co.ricoh.advop.mini.cheetahminiutil.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by duqiang on 7/14/2016.
 */
public class MediaEventReceiver extends BroadcastReceiver {
    /**
     * メディアマウント通知
     */
    static final String RECEIVE_MEDIA_MOUNTED_ACTION = "jp.co.ricoh.advop.monitorservice.MEDIA_MOUNTED";
    /**
     * メディアアンマウント通知
     */
    static final String RECEIVE_MEDIA_UNMOUNTED_ACTION = "jp.co.ricoh.advop.monitorservice.MEDIA_UNMOUNTED";
    /**
     * メディア状態取得結果通知
     */
    static final String REQ_GET_MEDIA_STATE_ACTION = "jp.co.ricoh.advop.monitorservice.GET_MEDIA_STATE";

    public static final int SD = 0;
    public static final int USB = 1;

    static final String KEY_MEDIA_TYPE = "MEDIA_TYPE";

    static final String KEY_MOUNT_TARGET = "MOUNT_TARGET";

    static final String KEY_MEDIA_PATH = "MEDIA_PATH";

    static final String KEY_MOUNT_STATE = "MOUNT_STATE";

    static final String KEY_STORAGE_STATE = "STORAGE_STATE";

    private ArrayList<MediaStateListener> mediaStateListenerList = new ArrayList<MediaStateListener>();
    private boolean isRegister;

    private boolean isSDMount = false;

    private static MediaEventReceiver mediaEventReceiver;

    public ArrayList<MediaInfo> mediaInfos;

    public MediaEventReceiver() {
        mediaInfos = new ArrayList<MediaInfo>();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle extras = intent.getExtras();
        final String action = intent.getAction();
        Log.e("onReceive", "onReceive");
        if (extras != null) {
            if (RECEIVE_MEDIA_MOUNTED_ACTION.equals(action)
                    || RECEIVE_MEDIA_UNMOUNTED_ACTION.equals(action)) {
                int type = extras.getInt(KEY_MEDIA_TYPE);
                int state = extras.getInt(KEY_MOUNT_STATE);
                String path = extras.getString(KEY_MEDIA_PATH);
                if (RECEIVE_MEDIA_MOUNTED_ACTION.equals(action)){
                    addMediaInfo(new MediaInfo(path, type));
                    for (MediaStateListener mediaStateListener :mediaStateListenerList) {
                        if (type == SD){
                            mediaStateListener.onSDChanged(path,true);
                        } else {
                            mediaStateListener.onUSBChanged(path,true);
                        }
                    }
                } else {
                    if (type == SD){
                        removeMediaInfo(MediaInfo.TYPE_SD_CARD);
                    } else {
                        removeMediaInfo(MediaInfo.TYPE_USB);
                    }
                    for (MediaStateListener mediaStateListener :mediaStateListenerList) {
                        if (type == SD){
                            mediaStateListener.onSDChanged(null,false);
                        } else {
                            mediaStateListener.onUSBChanged(null,false);
                        }
                    }
                }

            }

        }


    }

    private void addMediaInfo(MediaInfo info){
        removeMediaInfo(info.getType());
        mediaInfos.add(info);
    }

    private void removeMediaInfo(int type) {
        MediaInfo tmp = null;
        for(MediaInfo info :mediaInfos){
            if(info.getType()== type){
                tmp = info;
            }
        }
        mediaInfos.remove(tmp);
    }


    public void registerMediaEventReceiver(Context context, MediaStateListener mediaStateListener) {//listListener
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(MediaEventReceiver.RECEIVE_MEDIA_MOUNTED_ACTION);
        iFilter.addAction(MediaEventReceiver.RECEIVE_MEDIA_UNMOUNTED_ACTION);
        if (mediaStateListenerList.size() == 0){
            context.registerReceiver(this, iFilter);
            isRegister = true;
        }
        mediaStateListenerList.add(mediaStateListener);
    }

    public void unRegisterMediaEventReceiver(Context context, MediaStateListener listener) {
        mediaStateListenerList.remove(listener);
        if (mediaStateListenerList.size() == 0 && isRegister) {
            context.unregisterReceiver(this);
        }
    }

    public static MediaEventReceiver getInstance(){
        if (mediaEventReceiver == null) {
            synchronized (MediaEventReceiver.class) {
                if (mediaEventReceiver == null) {
                    mediaEventReceiver = new MediaEventReceiver();
                }
            }
        }
        return mediaEventReceiver;
    }



    public interface MediaStateListener {
        void onUSBChanged(String path, boolean isAvailable);
        void onSDChanged(String path, boolean isAvailable);
    }

}
