package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * Created by duqiang on 8/29/2016.
 */
public class SmartEditText extends EditText {

    public SmartEditText(Context context) {
        super(context);
    }

    public SmartEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        final int action = event.getAction() & MotionEvent.ACTION_MASK;
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (action == KeyEvent.ACTION_UP)) {
            onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
