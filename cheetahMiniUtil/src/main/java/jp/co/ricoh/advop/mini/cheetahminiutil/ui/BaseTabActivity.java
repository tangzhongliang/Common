
package jp.co.ricoh.advop.mini.cheetahminiutil.ui;

import android.app.Activity;
import android.app.TabActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;


public class BaseTabActivity extends TabActivity implements ActivityInterface {
    private final static String TAG = BaseTabActivity.class.getSimpleName();

    /**
     * 省エネ復帰要求タスク Return request from energy saving task
     */
//    protected ReturnRequestFromEnergySavingTask mReturnRequestFromEnergySavingTask = null;

    /**
     * メインアクティビティ起動済みフラグ trueであれば、すでにMainActivityが起動済みです。 MainActivity running
     * flag If true, another Mainactivity instance is running.
     */
    private ImplActivity implActivity = new ImplActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //LanguageUtil.setLanguage(this, CHolder.instance().getInitParameters().getAppLocale());
        super.onCreate(savedInstanceState);
        if (implActivity == null)
            throw new RuntimeException("implActivity can not be null");
        implActivity.onCreate(savedInstanceState);
    }

    public void setImplActivity(ImplActivity implActivity) {
        this.implActivity = implActivity;
    }

    public ImplActivity getImplActivity() {
        return implActivity;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        implActivity.onConfigurationChanged(newConfig);
    }

    public void onLanguageChanged() {

    }


    @Override
    protected void onStart() {
        //LanguageUtil.setLanguage(this, CHolder.instance().getInitParameters().getAppLocale());
        super.onStart();
        implActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        implActivity.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        implActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        implActivity.onDestroy();
    }

    // ---------------------------------hide keyboard-------------------------------------------------------

    public void showSoftKeyboard(EditText edText) {
        implActivity.showSoftKeyboard(edText);
    }

    public void hideSoftKeyboard() {
        implActivity.hideSoftKeyboard();
    }

    public void setEnableBack() {
        implActivity.setEnableBack();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean b = implActivity.onKeyDown(keyCode, event);
        return b ? b : super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return implActivity.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return implActivity.onOptionsItemSelected(item);
    }

    //
    public String getVersion() {
        return implActivity.getVersion();
    }

    public String getLicense() {
        return implActivity.getLicense();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        implActivity.dispatchTouchEvent(ev);
        boolean b = super.dispatchTouchEvent(ev);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = implActivity.onTouchEvent(event);
        return b ? b : super.onTouchEvent(event);
    }
}
