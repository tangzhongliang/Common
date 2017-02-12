package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.view.View;
import android.widget.AdapterView;

public abstract class BaseOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    abstract public void onWork(AdapterView<?> parent, View view, int position, long id);

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (CUtil.isContinue()) {
            Buzzer.play();
            onWork(parent, view, position, id);
        } else {
            LogC.d("The interval time of click is smaller than " + CUtil.INTERVAL_TIME);
            return;
        }
    }

}