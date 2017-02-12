/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.content.Context;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SyncFailedException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.MediaInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.HDDUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;


public class FileUtil {
    public static String CONNOT_WRITE = "CONNOT_WRITE";
    public static String SUCCESS = "SUCCESS";
    public static String NO_MEDIA = "NO_MEDIA";
    private static String TAG = FileUtil.class.getSimpleName();

    public static String saveToMedia(Context context, String filePath, String dirName) {
        ArrayList<MediaInfo> mediaState = new MediaUtil().getMediaState(context);
        ResultObject resultObject = new ResultObject();
        if (mediaState.size() == 0) {
            return NO_MEDIA;
        }
        for (MediaInfo mediaInfo : mediaState) {
            File dir = new File(mediaInfo.getPath(), dirName);
            LogC.i("start to save into " + dir);
            if (!dir.exists() || dir.isFile()) {
                dir.delete();
                if (dir.mkdirs()) {
                    LogC.i("handleJpeg() create success");
                } else {
                    return CONNOT_WRITE;
                }
            }

            File file = new File(filePath);
            if (file.isDirectory()) {

                for (File file1 : file.listFiles()) {
                    String newPath = dir + "/" + file1.getName();
                    if (!HDDUtil.copyFile(file1.getAbsolutePath(), newPath)) {
                        return CONNOT_WRITE;
                    }
                }
            } else {
                String newPath = dir + "/" + file.getName();
                if (!HDDUtil.copyFile(filePath, newPath)) {
                    return CONNOT_WRITE;
                }
            }
        }

        return SUCCESS;
    }

    public static boolean writeToString(String str, String path) {
        return writeToString(str.getBytes(), path);
    }

    public static boolean writeToString(byte[] bytes, String path) {
        if (path == null) {
            LogC.e(TAG, "Empty parameter!");
            return false;
        }

        FileOutputStream fileOutputStream = null;

        try {
            if (!HDDUtil.createNewFile(path)) {
                return false;
            }

            fileOutputStream = new FileOutputStream(path);
            FileDescriptor fileDescriptor = fileOutputStream.getFD();
            fileOutputStream.write(bytes);
            fileDescriptor.sync();

            LogC.d(TAG, "Finish copying file.");
            return true;
        } catch (IOException e) {
            LogC.e(TAG, "Copy file error!" + e.toString());
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            return false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
