package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.smb;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Created by tangzhongliang on 1/6/2017.
 */

public class SmbUtil {
    public static String uploadToFolder(SMBInfo smbInfo, final String filePath, String folderName) {
        LogC.i("uploadToFolder file " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            LogC.i(filePath + "don't exit");
            return null;
        } else if (file.isFile()) {

        } else if (file.isDirectory()) {

        }
        final StringBuffer stringBuffer = new StringBuffer();
        if (smbInfo == null)
            return null;
        final String result = SMBManager.connect(smbInfo);
        if (result.equals(SMBManager.SMB_AUTH_FAIL)) {
            Log.i("APPLEPEN", "SMB_AUTH_FAIL");

            return result;
        } else if (result.equals(SMBManager.SMB_CONNECT_FAIL)) {
            Log.i("APPLEPEN", "SMB_CONNECT_FAIL");
            return result;
        }
//        if (SMBManager.isSmbCanWrite(smbInfo).equals(SMBManager.SMB_CAN_NOT_WRITE)) {

        final SMBInfo dirSmbInfo = new SMBInfo(smbInfo.getHostNameAndPath(), smbInfo.getUserName(), smbInfo.getPassword());
        if (!TextUtils.isEmpty(folderName)) {
            if (dirSmbInfo.getHostNameAndPath().endsWith("/")) {
                dirSmbInfo.setHostNameAndPath(smbInfo.getHostNameAndPath() + folderName);
            } else {
                dirSmbInfo.setHostNameAndPath(smbInfo.getHostNameAndPath() + "/" + folderName);
            }
            try {
                if (!SMBManager.isDirectory(dirSmbInfo)) {
                    String mkdirs = SMBManager.mkdirs(dirSmbInfo);
                    if (!mkdirs.equals(SMBManager.SMB_MKDIRS_SUCCESS)) {
                        return SMBManager.SMB_MKDIRS_FAIL;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                String parseException = SMBManager.parseException(e, "access directory");
                return parseException;
            }
        }
        if (!file.isDirectory()) {
            SMBManager.uploadFile(filePath, new File(filePath).getName(), dirSmbInfo, new SMBManager.UploadProgressListener() {
                @Override
                public void onUploadProgress(String currentStep, long uploadSize, File file) {
                    if (currentStep.equals(SMBManager.SMB_UPLOAD_FAIL)) {
                        stringBuffer.append(currentStep);
                        return;
                    } else {
                        LogC.i("upload " + filePath + " to " + dirSmbInfo.getPath() + " success");
                    }
                }
            });
        } else {
            for (File file1 : file.listFiles()) {
                SMBManager.uploadFile(file1.getAbsolutePath(), file1.getName(), dirSmbInfo, new SMBManager.UploadProgressListener() {
                    @Override
                    public void onUploadProgress(String currentStep, long uploadSize, File file) {
                        if (currentStep.equals(SMBManager.SMB_UPLOAD_FAIL)) {
                            stringBuffer.append(currentStep);
                            return;
                        } else {
                            LogC.i("upload " + filePath + " to " + dirSmbInfo.getPath() + " success");
                        }
                    }
                });
            }
        }
        return SMBManager.SMB_UPLOAD_SUCCESS;
    }

    public String connect(SMBInfo smbInfo) {
        String connect = SMBManager.connect(smbInfo);
        return connect;
    }
}
