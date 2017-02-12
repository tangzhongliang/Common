package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by baotao on 8/1/2016.
 */
public class LanguageUtil {

    public static void setLanguage(Context context,Locale locale){
        if (locale == null){
            return;
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }
}
