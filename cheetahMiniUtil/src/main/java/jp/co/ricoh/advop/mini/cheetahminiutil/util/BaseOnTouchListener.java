package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.view.MotionEvent;
import android.view.View;

public abstract class BaseOnTouchListener implements View.OnTouchListener {

    abstract public void onDown(View v, MotionEvent event);
    abstract public void onUP(View v, MotionEvent event);
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (CUtil.isContinue()) {
                    Buzzer.play();
                    onDown(v,event);
                } else {
                    LogC.d("The interval time of click is smaller than " + CUtil.INTERVAL_TIME);
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                onUP(v,event);
                break;
        }
        return false;
    }

}