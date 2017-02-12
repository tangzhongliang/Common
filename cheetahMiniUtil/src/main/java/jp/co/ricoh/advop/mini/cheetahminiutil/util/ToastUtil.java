package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;

import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;

/**
 * Created by baotao on 8/26/2016.
 */
public class ToastUtil {

    public enum ToastLengthType{
        TYPE_SHORT, TYPE_LONG, TYPE_VERY_LONG
    }

    private static Toast mToast;

    private static Toast mToastVeryLong;


    public static void showToast(final Context mContext, final String text, final ToastLengthType type) {

        CHolder.instance().getMainUIHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mToastVeryLong != null) {
                    mToastVeryLong.cancel();
                }

                if (mToast == null) {
                    mToast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_SHORT);
                }

                mToast.setText(text);

                switch (type) {
                    case TYPE_SHORT:
                        mToast.setDuration(Toast.LENGTH_SHORT);
                        mToast.show();
                        break;
                    case TYPE_LONG:
                        mToast.setDuration(Toast.LENGTH_LONG);
                        mToast.show();
                        break;
                    case TYPE_VERY_LONG:
                        mToastVeryLong = Toast.makeText(mContext, text, Toast.LENGTH_LONG);

                        mToast.setDuration(Toast.LENGTH_LONG);

                        mToast.show();
                        mToastVeryLong.show();

                        break;
                }
            }
        });


    }

    public static void showToast(Context mContext, int resId, ToastLengthType type) {
        showToast(mContext, mContext.getResources().getString(resId), type);
    }

}