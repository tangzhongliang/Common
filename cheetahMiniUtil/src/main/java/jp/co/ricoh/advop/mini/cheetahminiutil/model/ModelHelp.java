package jp.co.ricoh.advop.mini.cheetahminiutil.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;

/**
 * Copyright (C) 2013-2016 RICOH Co.,LTD
 * All rights reserved
 */

public class ModelHelp {
    public static SharedPreferences getSharedPreferences(Application application, String name, int mode) {
        try {
            Class<?> aClass = Class.forName("android.content.ContextWrapper");
            Field[] declaredFields = aClass.getDeclaredFields();
            Field field = null;
            for (Field declaredField : declaredFields) {
                if (declaredField.getName().equals("mBase")) {
                    field = declaredField;
                }
            }
            if (field != null) {
                field.setAccessible(true);
                Object mBase = field.get(application);
                Class<?> contextImplCls = Class.forName("android.app.ContextImpl");
                Field[] declaredFields1 = contextImplCls.getDeclaredFields();
                for (Field field1 : declaredFields1) {
                    if (field1.getName().equals("sSharedPrefs")) {
                        field1.setAccessible(true);
                        HashMap<String, Object> sharePrefs = (HashMap<String, Object>) field1.get(mBase);
                        Object sharePref = sharePrefs.get(name);
                        if (sharePref == null) {
                            //create sharePref
                            Class<?> sharePrefImplCls = Class.forName("android.app.SharedPreferencesImpl");
                            Constructor<?> declaredConstructor = sharePrefImplCls.getDeclaredConstructor(File.class, int.class);
                            declaredConstructor.setAccessible(true);
                            sharePref = declaredConstructor.newInstance(new File(Const.PATH_PACKAGE_FOLDER, "shared_prefs/" + name + ".xml"), mode);
                            sharePrefs.put(name, sharePref);
                        } else {
                            //return
                        }
                        SharedPreferences sp = (SharedPreferences) sharePref;
                        if ((mode & Context.MODE_MULTI_PROCESS) != 0 ||
                                application.getApplicationInfo().targetSdkVersion < android.os.Build.VERSION_CODES.HONEYCOMB) {
                            // If somebody else (some other process) changed the prefs
                            // file behind our back, we reload it.  This has been the
                            // historical (if undocumented) behavior.
                            Class<?> sharePrefImplCls = Class.forName("android.app.SharedPreferencesImpl");
                            Method startReloadIfChangedUnexpectedly = sharePrefImplCls.getDeclaredMethod("startReloadIfChangedUnexpectedly");
                            startReloadIfChangedUnexpectedly.setAccessible(true);
                            startReloadIfChangedUnexpectedly.invoke(sp);
                        }
                        return sp;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
