package jp.co.ricoh.advop.mini.cheetahminiutil.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.LockManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.print.PrintManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog.MultiButtonDialog;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseOnClickListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Buzzer;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUIUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.HDDUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Copyright (C) 2013-2016 RICOH Co.,LTD
 * All rights reserved
 */

public class ImplActivity implements ActivityInterface {
    Activity activity;
    ActivityInterface implAct;

    public ScanManager scanManager = null;
    public PrintManager printManager = null;
    public BaseApplication application = null;

    public LockManager lockManager = null;
    public Handler handler = null;

    public boolean isCreateMenu = true;
    public Configuration config = null;

    public boolean isMainAct = false;
    public boolean mMultipleRunning = false;
    public boolean isEnable = true;

    public ImplActivity(Activity baseListActivity) {
        activity = baseListActivity;
        implAct = (ActivityInterface) activity;
    }

    protected void onCreate(Bundle savedInstanceState) {
        //LanguageUtil.setLanguage(this, CHolder.instance().getInitParameters().getAppLocale());
        scanManager = CHolder.instance().getScanManager();
        printManager = CHolder.instance().getPrintManager();
        application = CHolder.instance().getApplication();
        lockManager = CHolder.instance().getLockManager();
        handler = CHolder.instance().getMainUIHandler();

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        config = new Configuration(getResources().getConfiguration());
        isMainAct = false;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (!config.locale.equals(newConfig.locale)) {
            LogC.i(String.format("language changed %s->%s", config.locale.toString(), newConfig.locale));
            implAct.onLanguageChanged();

        }
        config = new Configuration(newConfig);
    }

    protected void onResume() {
        CHolder.instance().setActivity(activity);
        if (!CHolder.instance().isForeground() && CUIUtil.isForegroundApp(application)) {
            LogC.i("", "background -> foreground");

            CHolder.instance().setForeground(true);
            handler.post(new Runnable() {

                @Override
                public void run() {
                    scanManager.getScanSysAlertDisplay().showOnResume();
                    printManager.getPrintSysAlertDisplay().showOnResume();
                    lockManager.goForeground();
                }
            });
        }
    }

    protected void onStop() {
        if (CHolder.instance().isForeground() && !CUIUtil.isForegroundApp(application)) {
            LogC.i("", "foreground -> background");

            CHolder.instance().setForeground(false);
            lockManager.goBackground();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            if (isEnable) {
                Buzzer.play();

            } else {
                Buzzer.onBuzzer(Buzzer.BUZZER_NACK);
                return true;
            }
        }

        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (isCreateMenu) {
            activity.getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Buzzer.play();
        if (item.getItemId() == R.id.menu_show_app_info) {

            final MultiButtonDialog multiButtonDialog = MultiButtonDialog.createMsgDialog(activity, getVersion());
            //
            multiButtonDialog.getRightButton().setOnClickListener(new BaseOnClickListener() {
                @Override
                public void onWork(View v) {
                    // TODO Auto-generated method stub
                    multiButtonDialog.dismiss();

                }
            });
            multiButtonDialog.show();
            //
//            Intent intent = new Intent(this, AppInfoActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS); // 開発ガイドのとおり。
//
//            startActivity(intent);
//            overridePendingTransition(R.animator.in_from_right, R.animator.out_to_left);

            return true;
        } else if (item.getItemId() == R.id.menu_show_app_license) {

            final MultiButtonDialog multiButtonDialog = MultiButtonDialog.createMsgDialog(activity, getLicense());
            multiButtonDialog.setTextSize(17);
            multiButtonDialog.getRightButton().setOnClickListener(new BaseOnClickListener() {
                @Override
                public void onWork(View v) {
                    // TODO Auto-generated method stub
                    multiButtonDialog.dismiss();
                }
            });
            multiButtonDialog.show();
        }
        return false;
    }

    public Resources getResources() {
        return activity.getResources();
    }

    public String getVersion() {
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            String version = info.versionName;
            return activity.getString(R.string.txid_ppp_g_app_information_ver_label) + version;
        } catch (Exception e) {
            e.printStackTrace();
//            return this.getString(R.string.can_not_find_version_name);
            return "";
        }
    }

    public String getLicense() {
        try {
            String license = HDDUtil.readFileFromAssets(activity, "License.txt");
            return license;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void showSoftKeyboard(EditText edText) {
        edText.requestFocus();
        InputMethodManager lManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        lManager.showSoftInput(edText, edText.getInputType());
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogC.i("touch action");
        //don't click on edit text then hide keyboard and hide cursor
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            LogC.i("touch action up");
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                boolean pressed = currentFocus.isPressed();
                //don't click on edit text
                if (currentFocus instanceof EditText) {
                    if (pressed) {
                        ((EditText) currentFocus).setCursorVisible(true);
                    } else {
                        hideSoftKeyboard();
                        activity.getWindow().getDecorView().requestFocus();
                        ((EditText) currentFocus).setCursorVisible(false);
                        LogC.i("touch action clear focus");
                    }

                }
            }
        }
        return false;
    }

    public void hideSoftKeyboard() {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onLanguageChanged() {

    }

    public void onDestroy() {
        activity = null;
    }

    public void onStart() {
    }

    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }

    public void setEnableBack() {
        isEnable = true;
    }
}
