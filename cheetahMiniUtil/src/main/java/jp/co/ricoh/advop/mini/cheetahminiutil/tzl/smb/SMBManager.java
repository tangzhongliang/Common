package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.smb;


import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dalvik.system.DexClassLoader;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.HDDUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;


public class SMBManager {

    public static final String SMB_AUTH_FAIL = "SMB_AUTH_FAIL";
    public static final String SMB_FAIL = "SMB_FAIL";
    public static final String SMB_SUCCESSS = "SMB_SUCCESSS";

    public static final String SMB_CONNECT_SUCCESSS = "SMB_CONNECT_SUCCESSS";
    public static final String SMB_CONNECT_FAIL = "SMB_CONNECT_FAIL";
    public static final String SMB_DISCONNECT_SUCCESS = "SMB_DISCONNECT_SUCCESS";

    public static final String SMB_UPLOAD_SUCCESS = "SMB_UPLOAD_SUCCESS";
    public static final String SMB_UPLOAD_FAIL = "SMB_UPLOAD_FAIL";

    public static final String SMB_MKDIRS_SUCCESS = "SMB_MKDIRS_SUCCESS";
    public static final String SMB_MKDIRS_FAIL = "SMB_MKDIRS_FAIL";

    public static final String SMB_CAN_WRITE = "SMB_CAN_WRITE";
    public static final String SMB_CAN_NOT_WRITE = "SMB_CAN_NOT_WRITE";
    public static DexClassLoader classLoader;

    /*****************************************************/
    public static DexClassLoader getClassLoader() {
        if (classLoader == null) {
            Log.i("APPLEPEN", "load assert jar");
            final String libPath = "/mnt/hdd/" + CHolder.instance().getApp().getPackageName() + "/jcifs/" + "jcifs.jar";

            String path = HDDUtil.copyAssertJarToFile(CHolder.instance().getApp(),
                    "jcifs.jar", libPath);
            Log.i("APPLEPEN", "path is " + path);
            if (path != null) {
                final File optimizedDexOutputPath = CHolder.instance().getApp().getDir("jcifs", Context.MODE_PRIVATE);
                classLoader = new DexClassLoader(path,
                        optimizedDexOutputPath.getAbsolutePath(), null,
                        CHolder.instance().getApp().getClassLoader());
            }
        }
        if (classLoader == null) {
            throw new RuntimeException("don't find jcifs.jar in project assets folder");
        }
        return classLoader;
    }

    public static boolean createNewFile(String filePath) {
        if (filePath == null) {
            return false;
        }

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                boolean ret = file.getParentFile().mkdirs();
                Log.i("APPLEPEN", "file.getParentFile().mkdirs()" + String.valueOf(ret));
            }

            if (!file.exists()) {
                file.createNewFile();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public static void uploadFile(String sendFile, String dstFileName, SMBInfo smbInfo, UploadProgressListener listener) {
        DexClassLoader classLoader = getClassLoader();
        // TODO: 2016/12/30 ok end

        // TODO: 2016/12/30 test

        // TODO: 2016/12/30 test end
        BufferedInputStream buffIn = null;
        //File file = new File(CHolder.instance().getGlobalDataManager().getSendFileName());
        File file = new File(sendFile);
        try {
            String domain = "";
            String remotePath = smbInfo.getPath();
            if (!remotePath.endsWith("/")) {
                remotePath = remotePath + "/";
            }
            String username = smbInfo.getUserName();
            String password = smbInfo.getPassword();

            Class<?> ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);

            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (username == null) {
                ntlmPasswordAuthentication = null;
            } else {
                ntlmArgs = new Object[]{domain, username, password};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{remotePath, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(remotePath);
            }

            Method listMethod = smbFileClass.getMethod("list");
            String[] list = (String[]) listMethod.invoke(smbFile);
            List<String> files = Arrays.asList(list);
            if (dstFileName == null) {
                dstFileName = sendFile;
            } else {
                dstFileName = "/" + dstFileName;
            }
            String fileName = getFileNameNoSuffix(dstFileName);
            String prefix = getSuffix(dstFileName);

            int index = 1;
            String lastFileName = fileName + "." + prefix;
            while (files.contains(lastFileName)) {
                lastFileName = fileName + "-" + index + "." + prefix;
                index++;
            }

            remotePath = smbInfo.getPath() + "/" + lastFileName;

            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{remotePath, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB upload:\nremote path:" + remotePath + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(remotePath);

                LogC.d("SMB upload:\nremote path:" + remotePath + "\ndomain:" + domain + "\nAnonymous");
            }

            try {
                if (!canWrite(smbInfo)) {
                    listener.onUploadProgress(SMB_CAN_NOT_WRITE,0,file);
                    return ;
                }
            }catch (Exception e){
                e.printStackTrace();
                listener.onUploadProgress(SMB_CAN_NOT_WRITE,0,file);
                return ;
            }

            Class<?> smbFileOutputStreamClass = classLoader.loadClass("jcifs.smb.SmbFileOutputStream");
            Constructor<?> smbStreamConstructor = smbFileOutputStreamClass.getConstructor(smbFileClass);
            Object smbOut = smbStreamConstructor.newInstance(smbFile);

            Class<?>[] writeMethodParams = {byte[].class, int.class, int.class};
            Method writeMethod = smbFileOutputStreamClass.getMethod("write", writeMethodParams);
            Method flushMethod = smbFileOutputStreamClass.getMethod("flush");
            Method closeMethod = smbFileOutputStreamClass.getMethod("close");
            writeMethod.setAccessible(true);
            flushMethod.setAccessible(true);

            buffIn = new BufferedInputStream(new FileInputStream(file));

            byte[] buffer = new byte[8192];
            int length;

            while ((length = buffIn.read(buffer)) != -1) {
                Log.i("APPLEPEN", "length is " + String.valueOf(length));
                writeMethod.invoke(smbOut, buffer, 0, length);
                flushMethod.invoke(smbOut);
            }

            listener.onUploadProgress(SMBManager.SMB_UPLOAD_SUCCESS, file.length(),
                    file);
            try {
                if (null != smbOut)
                    closeMethod.invoke(smbOut);
                if (null != buffIn)
                    buffIn.close();
            } catch (Exception e2) {
                LogC.e("SMB disconnect failed!\n" + e2.getLocalizedMessage());
            }
        } catch (Exception e) {
            if (e.getClass().equals(InvocationTargetException.class)) {
                Throwable realError = ((InvocationTargetException) e).getTargetException();
                LogC.e("SMB upload failed!\n" + realError.getLocalizedMessage());
            } else {
                LogC.e("SMB upload failed!\n" + e.getLocalizedMessage());
            }
            try {
                if (null != buffIn) {
                    buffIn.close();
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                listener.onUploadProgress(SMBManager.SMB_UPLOAD_FAIL, 0,
                        file);
            }
        }
    }

    public static String connect(SMBInfo smbInfo) {
        DexClassLoader classLoader = getClassLoader();

        try {

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile = createSmbFile(smbInfo, classLoader, smbFileClass);

            Method connectMethod = smbFileClass.getDeclaredMethod("connect");
            connectMethod.setAccessible(true);
            connectMethod.invoke(smbFile);

            return SMBManager.SMB_CONNECT_SUCCESSS;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (e.getClass().equals(InvocationTargetException.class)) {
                    Throwable realError = ((InvocationTargetException) e).getTargetException();

                    LogC.e("SMB connect failed!", realError);
                    Class<?> smbAuthExceptionClass = classLoader.loadClass("jcifs.smb.SmbAuthException");
                    if (realError.getClass().equals(smbAuthExceptionClass)) {
                        return SMBManager.SMB_AUTH_FAIL;
                    }
                } else {
                    LogC.e("SMB connect failed!(1)\n" + e.getLocalizedMessage());
                }
                return SMBManager.SMB_CONNECT_FAIL;
            } catch (ClassNotFoundException e1) {
                LogC.e("SMB connect failed!(not find class)\n" + e1.getLocalizedMessage());
                return SMBManager.SMB_CONNECT_FAIL;
            }
        }
    }

    private static Object createSmbFile(SMBInfo smbInfo, DexClassLoader classLoader, Class<?> smbFileClass) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String domain = "";//PreferencesUtil.getInstance().getDomain();
        String path = smbInfo.getPath();
        Log.i("APPLEPEN", "path is : " + path);
        String username = smbInfo.getUserName();
        Log.i("APPLEPEN", "username is : " + username);

        String password = smbInfo.getPassword();
        Log.i("APPLEPEN", "password is : " + password);

        Class<?> ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
        Class<?>[] ntlmParams = {String.class, String.class, String.class};
        Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);

        Object[] ntlmArgs;
        Object ntlmPasswordAuthentication;
        if (isStringEmpty(domain) && isStringEmpty(username)) {
            ntlmPasswordAuthentication = null;
        } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
            ntlmArgs = new Object[]{domain, null, null};
            ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
        } else {
            ntlmArgs = new Object[]{domain, username, password};
            ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
        }

        Object smbFile;
        if (ntlmPasswordAuthentication != null) {
            Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
            Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
            Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
            smbFile = smbConstructor.newInstance(smbArgs);

            LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
        } else {
            Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
            smbFile = smbConstructor.newInstance(path);

            LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
        }
        return smbFile;
    }

    public static boolean isDirectory(SMBInfo smbInfo) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        DexClassLoader classLoader = getClassLoader();
        Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
        Object smbFile = createSmbFile(smbInfo, classLoader, smbFileClass);

        Method connectMethod = smbFileClass.getDeclaredMethod("isDirectory");
        connectMethod.setAccessible(true);
        return (Boolean) connectMethod.invoke(smbFile);

    }

    public static boolean canWrite(SMBInfo smbInfo) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        DexClassLoader classLoader = getClassLoader();
        Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
        Object smbFile = createSmbFile(smbInfo, classLoader, smbFileClass);

        Method connectMethod = smbFileClass.getDeclaredMethod("canWrite");
        connectMethod.setAccessible(true);
        return (Boolean) connectMethod.invoke(smbFile);

    }

    public interface UploadProgressListener {
        public void onUploadProgress(String currentStep, long uploadSize, File file);
    }


    public static String mkdirs(SMBInfo smbInfo) {
        DexClassLoader classLoader = getClassLoader();
        try {
            String domain = "";//PreferencesUtil.getInstance().getDomain();
            String path = smbInfo.getPath();
            Log.i("APPLEPEN", "path is : " + path);
            String username = smbInfo.getUserName();
            Log.i("APPLEPEN", "username is : " + username);

            String password = smbInfo.getPassword();
            Log.i("APPLEPEN", "password is : " + password);

            Class<?> ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);

            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmPasswordAuthentication = null;
            } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmArgs = new Object[]{domain, null, null};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            } else {
                ntlmArgs = new Object[]{domain, username, password};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(path);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }

            Method mkdirsMethod = smbFileClass.getDeclaredMethod("mkdirs");
            mkdirsMethod.setAccessible(true);
            mkdirsMethod.invoke(smbFile);

            return SMBManager.SMB_MKDIRS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return parseException(e, "mkdirs");
        }
    }

    public static String parseException(Exception e, String function) {
        try {
            if (e.getClass().equals(InvocationTargetException.class)) {
                Throwable realError = ((InvocationTargetException) e).getTargetException();

                LogC.e("SMB " + function + " failed !", realError);
                Class<?> smbAuthExceptionClass = getClassLoader().loadClass("jcifs.smb.SmbAuthException");
                if (realError.getClass().equals(smbAuthExceptionClass)) {
                    return SMBManager.SMB_AUTH_FAIL;
                }
            } else {
                LogC.e("SMB " + function + " failed!(1)\n" + e.getLocalizedMessage());
            }
            return SMBManager.SMB_FAIL;
        } catch (ClassNotFoundException e1) {
            LogC.e("SMB " + function + " failed!(not find class)\n" + e1.getLocalizedMessage());
            return SMBManager.SMB_CONNECT_FAIL;
        }
    }

    public static String isSmbCanWrite(SMBInfo smbInfo) {
        DexClassLoader classLoader = getClassLoader();
        // SMBInfo smbInfo1 = new SMBInfo("\\\\172.25.78.85\\share", "huanglijun", "22222");

        try {

            String domain = "";//PreferencesUtil.getInstance().getDomain();
            String path = smbInfo.getPath();
            String username = smbInfo.getUserName();
            String password = smbInfo.getPassword();
            Class<?> ntlmPasswordAuthenticationClass = null;
            ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);
            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmPasswordAuthentication = null;
            } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmArgs = new Object[]{domain, null, null};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            } else {
                ntlmArgs = new Object[]{domain, username, password};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(path);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }


            Method canWriteMethod = smbFileClass.getDeclaredMethod("canWrite");
            canWriteMethod.setAccessible(true);

            Boolean ret = (Boolean) canWriteMethod.invoke(smbFile);
            if (ret) {
                return SMB_CAN_WRITE;
            } else {
                return SMB_CAN_NOT_WRITE;
            }

        } catch (Exception e) {
            try {
                if (e.getClass().equals(InvocationTargetException.class)) {
                    Throwable realError = ((InvocationTargetException) e).getTargetException();

                    LogC.e("SMB connect failed!", realError);
                    Class<?> smbAuthExceptionClass = classLoader.loadClass("jcifs.smb.SmbException");
                    if (realError.getClass().equals(smbAuthExceptionClass)) {
                        return SMBManager.SMB_CAN_NOT_WRITE;
                    }

                } else {
                    LogC.e("SMB connect failed!\n" + e.getLocalizedMessage());
                }
                return SMBManager.SMB_CAN_NOT_WRITE;
            } catch (ClassNotFoundException e1) {
                LogC.e("SMB connect failed!\n" + e1.getLocalizedMessage());
                return SMBManager.SMB_CAN_NOT_WRITE;
            }
        }

    }

    public static boolean fileRename(SMBInfo smbInfo, String fileName) {
        LogC.d("file rename");
        DexClassLoader classLoader = getClassLoader();

        try {

            String domain = "";//PreferencesUtil.getInstance().getDomain();
            String path = smbInfo.getPath() + fileName + ".pdf";
            String username = smbInfo.getUserName();
            String pwd = smbInfo.getPassword();
            Class<?> ntlmPasswordAuthenticationClass = null;
            ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);
            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmPasswordAuthentication = null;
            } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmArgs = new Object[]{domain, null, null};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            } else {
                ntlmArgs = new Object[]{domain, username, pwd};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object dirSmbFile;

            //create smbfile object {path + "/"}
            String dirPath = getDirPath(path);
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{dirPath, ntlmPasswordAuthentication};
                dirSmbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                dirSmbFile = smbConstructor.newInstance(dirPath);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }

            Method listMethod = smbFileClass.getMethod("list");
            String[] list = (String[]) listMethod.invoke(dirSmbFile);
            List<String> files = Arrays.asList(list);

            //create smbfile object{path + fileName}
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(path);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }

            Method isFileMethod = smbFileClass.getDeclaredMethod("isFile");

            isFileMethod.setAccessible(true);

            if ((Boolean) isFileMethod.invoke(smbFile)) {
                //String str = path.substring(0, path.lastIndexOf("/"));
                Class[] parameterTypes = {smbFileClass};
                Method renameToMethod = smbFileClass.getDeclaredMethod("renameTo", parameterTypes);
                renameToMethod.setAccessible(true);
                Object newSmbFile;

                int index = 1;
                String lastFileName = fileName + ".del";
                while (files.contains(lastFileName)) {
                    lastFileName = fileName + ".del" + index;
                    index++;
                }

                String newPath = smbInfo.getPath() + lastFileName;

                if (ntlmPasswordAuthentication != null) {
                    Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                    Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);

                    Object[] smbArgs = new Object[]{newPath, ntlmPasswordAuthentication};
                    newSmbFile = smbConstructor.newInstance(smbArgs);

                    LogC.d("SMB test:\nnewPath:" + newPath + "\ndomain:" + domain + "\nNot anonymous");
                } else {
                    Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                    newSmbFile = smbConstructor.newInstance(newPath);

                    LogC.d("SMB test:\nnewPath:" + newPath + "\ndomain:" + domain + "\nAnonymous");
                }
                Object[] configArgs = new Object[]{newSmbFile};
                renameToMethod.invoke(smbFile, configArgs);
                return true;
            }

        } catch (Exception e) {
            LogC.e("SMBManager", e);

        }
        return false;
    }


    public static ArrayList<smbFileListInfo> getListFileFromSmb(SMBInfo smbInfo) {
        DexClassLoader classLoader = getClassLoader();
        ArrayList<smbFileListInfo> fileArray = new ArrayList<smbFileListInfo>();
        try {
            Class<?> configClass = classLoader.loadClass("jcifs.Config");
            Class<?>[] configParams = {String.class, String.class};
            Method setPropertyMethod = configClass.getMethod("setProperty", configParams);
            setPropertyMethod.setAccessible(true);
//
//
//            String configArgs1 ="jcifs.smb.client.disablePlainTextPasswords";
//            String configArgs2 = "false";
//            setPropertyMethod.invoke(configClass, configArgs1, configArgs2);
            //System.setProperty("jcifs.smb.client.disablePlainTextPasswords", "false");

            String domain = "";//PreferencesUtil.getInstance().getDomain();
            String path = smbInfo.getPath();
            String username = smbInfo.getUserName();
            String pwd = smbInfo.getPassword();
            Class<?> ntlmPasswordAuthenticationClass = null;
            ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);
            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmPasswordAuthentication = null;
            } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmArgs = new Object[]{domain, null, null};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            } else {
                ntlmArgs = new Object[]{domain, username, pwd};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(path);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }
            Class[] parameterTypes = {String.class};
            Method listFilesMethod = smbFileClass.getDeclaredMethod("listFiles", parameterTypes);
            listFilesMethod.setAccessible(true);

            String wildcard = "*.pdf";
            Object configArgs = new Object[]{wildcard};

            Object[] fileList = (Object[]) listFilesMethod.invoke(smbFile, wildcard);

            LogC.d("get fileList is" + fileList.toString());

            for (int i = 0; i < fileList.length; i++) {
                smbFileListInfo file = new smbFileListInfo();

                Object f = fileList[i];
                Method getNameMethod = smbFileClass.getDeclaredMethod("getName");
                getNameMethod.setAccessible(true);
                String name = (String) getNameMethod.invoke(f);
                file.fileName = name;

                Method lengthMethod = smbFileClass.getDeclaredMethod("length");
                lengthMethod.setAccessible(true);
                long size = (Long) lengthMethod.invoke(f);
                file.fileSize = size;

                //Method createTimeMethod = smbFileClass.getMethod("createTime");
                Method lastModifiedMethod = smbFileClass.getMethod("lastModified");
                lastModifiedMethod.setAccessible(true);
                long lastTime = (Long) lastModifiedMethod.invoke(f);
                LogC.d("file name is:" + name + "last modify time is" + lastTime);
                file.lastTime = new Date(lastTime);

                fileArray.add(file);
            }

        } catch (Exception e) {
            LogC.e("SMBManager", e);

        }
        return fileArray;
    }


    public static void getFolderSecurity(SMBInfo smbInfo) {
        DexClassLoader classLoader = getClassLoader();

        String domain = "";//PreferencesUtil.getInstance().getDomain();
        String path = smbInfo.getPath();
        String username = smbInfo.getUserName();
        String pwd = smbInfo.getPassword();
        Class<?> ntlmPasswordAuthenticationClass = null;

        try {
            ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");

            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = null;
            ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);

            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmPasswordAuthentication = null;
            } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmArgs = new Object[]{domain, null, null};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            } else {
                ntlmArgs = new Object[]{domain, username, pwd};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(path);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }
            Class[] parameterTypes = {boolean.class};


            //Class<?> ACEClass = classLoader.loadClass("jcifs.smb.ACE");
            boolean resolveSids = true;

            //Method getSecurityMethod = smbFileClass.getDeclaredMethod("getSecurity", parameterTypes);
            Method getSecurityMethod = smbFileClass.getDeclaredMethod("getSecurity");
            getSecurityMethod.setAccessible(true);
            Object[] aceShareEntry = (Object[]) getSecurityMethod.invoke(smbFile);
            for (int i = 0; i < aceShareEntry.length; i++) {
                LogC.d("aceShareEntry" + i + "is" + aceShareEntry[i].toString());
            }


            Method getShareSecurityMethod = smbFileClass.getDeclaredMethod("getShareSecurity", parameterTypes);
            getShareSecurityMethod.setAccessible(true);

            Object[] aceEntry = (Object[]) getShareSecurityMethod.invoke(smbFile, resolveSids);
            for (int i = 0; i < aceEntry.length; i++) {
                LogC.d("aceEntry" + i + "is" + aceEntry[i].toString());
            }

            // Class[] parameterTypes={boolean.class};

            //Class<?> ACEClass = classLoader.loadClass("jcifs.smb.ACE");
            //boolean resolveSids = true;
//            Object[] aceShareEntry = (Object[]) getShareSecurityMethod.invoke(smbFile, resolveSids);
//            for(int i = 0; i< aceShareEntry.length; i++) {
//                LogC.d("aceShareEntry" + i + "is" + aceEntry[i].toString());
//            }

        } catch (Exception e) {
            if (e.getClass().equals(InvocationTargetException.class)) {
                Throwable realError = ((InvocationTargetException) e).getTargetException();
                LogC.e("SMB download failed!\n" + realError.getLocalizedMessage());
            } else {
                LogC.e("SMB download failed!\n" + e.getLocalizedMessage());
            }
        }
    }


    public static File readFromSmb(SMBInfo smbInfo, String fileName, String localPath) {
        DexClassLoader classLoader = getClassLoader();

        File localfile = null;
        //InputStream bis=null;
        OutputStream bos = null;
        BufferedInputStream buffIn = null;

        // List<File> files = new ArrayList<File>();
        try {

            String domain = "";//PreferencesUtil.getInstance().getDomain();
            String path = smbInfo.getPath() + fileName;
            String username = smbInfo.getUserName();
            String pwd = smbInfo.getPassword();
            Class<?> ntlmPasswordAuthenticationClass = null;
            ntlmPasswordAuthenticationClass = classLoader.loadClass("jcifs.smb.NtlmPasswordAuthentication");
            Class<?>[] ntlmParams = {String.class, String.class, String.class};
            Constructor<?> ntlmConstructor = ntlmPasswordAuthenticationClass.getConstructor(ntlmParams);
            Object[] ntlmArgs;
            Object ntlmPasswordAuthentication;
            if (isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmPasswordAuthentication = null;
            } else if (!isStringEmpty(domain) && isStringEmpty(username)) {
                ntlmArgs = new Object[]{domain, null, null};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            } else {
                ntlmArgs = new Object[]{domain, username, pwd};
                ntlmPasswordAuthentication = ntlmConstructor.newInstance(ntlmArgs);
            }

            Class<?> smbFileClass = classLoader.loadClass("jcifs.smb.SmbFile");
            Object smbFile;
            if (ntlmPasswordAuthentication != null) {
                Class<?>[] smbParams = {String.class, ntlmPasswordAuthenticationClass};
                Constructor<?> smbConstructor = smbFileClass.getConstructor(smbParams);
                Object[] smbArgs = new Object[]{path, ntlmPasswordAuthentication};
                smbFile = smbConstructor.newInstance(smbArgs);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nNot anonymous");
            } else {
                Constructor<?> smbConstructor = smbFileClass.getConstructor(String.class);
                smbFile = smbConstructor.newInstance(path);

                LogC.d("SMB test:\npath:" + path + "\ndomain:" + domain + "\nAnonymous");
            }
            //SmbFile rmifile = new SmbFile(smbMachine);
            Class<?> smbFileInputStreamClass = classLoader.loadClass("jcifs.smb.SmbFileInputStream");

            Constructor<?> smbStreamConstructor = smbFileInputStreamClass.getConstructor(smbFileClass);
            Object smbIn = smbStreamConstructor.newInstance(smbFile);

            Class<?>[] writeMethodParams = {byte[].class, int.class, int.class};
            HDDUtil.createNewFile(localPath);
            localfile = new File(localPath);
            bos = new BufferedOutputStream(new FileOutputStream(localfile));
            if (bos == null) {
                return null;
            }
            //byte[] buffer=new byte[(int)fileList.get(0).getFileSize()];
            byte[] buffer = new byte[8192];

            Class<?>[] readMethodParams = {byte[].class};
            Method readMethod = smbFileInputStreamClass.getMethod("read", readMethodParams);

            //Method flushMethod = smbFileOutputStreamClass.getMethod("flush");
            Method closeMethod = smbFileInputStreamClass.getMethod("close");
            readMethod.setAccessible(true);
            closeMethod.setAccessible(true);
            int length = -1;
            while ((length = (Integer) readMethod.invoke(smbIn, buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            bos.flush();

            //byte[] buffer = new byte[8192];

            try {
                if (null != smbIn)
                    closeMethod.invoke(smbIn);
                if (null != buffIn)
                    buffIn.close();
            } catch (Exception e2) {
                LogC.e("SMB disconnect failed!\n" + e2.getLocalizedMessage());
            }
        } catch (Exception e) {
            localfile = null;
            if (e.getClass().equals(InvocationTargetException.class)) {
                Throwable realError = ((InvocationTargetException) e).getTargetException();
                LogC.e("SMB download failed!\n" + realError.getLocalizedMessage());
            } else {
                LogC.e("SMB download failed!\n" + e.getLocalizedMessage());
            }
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }

            } catch (IOException e) {
                localfile = null;
                LogC.e("SMB download failed!\n" + e.getLocalizedMessage());
            }
        }
        return localfile;
    }


    public static class smbFileListInfo {
        String fileName;
        long fileSize;
        Date lastTime;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }


        public Date getLastTime() {
            return lastTime;
        }

        public void setLastTime(Date lastTime) {
            this.lastTime = lastTime;
        }

        @Override
        public boolean equals(Object obj) {
            boolean bres = false;
            if (obj instanceof smbFileListInfo) {
                smbFileListInfo o = (smbFileListInfo) obj;
                bres = (this.fileSize == o.fileSize) & (this.fileName.equals(o.fileName)) & (this.lastTime.equals(o.lastTime));
            }
            return bres;
        }
    }

    public static String getFileNameNoSuffix(String pathAndName) {
        int start = pathAndName.lastIndexOf("/");
        int end = pathAndName.lastIndexOf(".");
        if (start != -1) {
            return pathAndName.substring(start + 1, end);
        } else {
            return null;
        }
    }

    public static String getSuffix(String filename) {
        int dix = filename.lastIndexOf('.');
        if (dix < 0) {
            return "";
        } else {
            return filename.substring(dix + 1);
        }
    }

    public static String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        //int end=pathandname.lastIndexOf(".");
        if (start != -1) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }

    }

    public static String getDirPath(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        //int end=pathandname.lastIndexOf(".");
        if (start != -1) {
            return pathandname.substring(0, start + 1);
        } else {
            return null;
        }

    }

    public static boolean isStringEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }
}
