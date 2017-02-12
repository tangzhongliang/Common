package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.widget.RadioGroup;

public abstract class BaseOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

    abstract public void onWork(RadioGroup group, int checkedId);

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            Buzzer.play();
            onWork(group, checkedId);
    }

}