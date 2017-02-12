package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Created by tangzhongliang on 1/4/2017.
 */
public class Logger {
    public static Logger getLogger(Class mailUtilClass) {
        return new Logger();
    }

    public void info(String send, String s) {
        LogC.i(send, s);
    }

    public void error(String actSend, String s) {
        LogC.e(actSend, s);
    }

    public void debug(String setSendMailSetting, String s) {
        LogC.d(setSendMailSetting, s);
    }

    public void warn(String getSMTPServerSetting, String s) {
        LogC.w(getSMTPServerSetting,s);
    }
}
