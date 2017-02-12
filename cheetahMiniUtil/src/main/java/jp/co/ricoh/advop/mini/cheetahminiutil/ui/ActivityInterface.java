package jp.co.ricoh.advop.mini.cheetahminiutil.ui;

import android.content.res.Configuration;
import android.os.Handler;
import android.widget.EditText;

import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.LockManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.print.PrintManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanManager;

/**
 * Copyright (C) 2013-2016 RICOH Co.,LTD
 * All rights reserved
 */
public interface ActivityInterface {


    public String getVersion();

    public String getLicense();

    public void showSoftKeyboard(EditText edText);

    public void hideSoftKeyboard();

    void onLanguageChanged();
}
