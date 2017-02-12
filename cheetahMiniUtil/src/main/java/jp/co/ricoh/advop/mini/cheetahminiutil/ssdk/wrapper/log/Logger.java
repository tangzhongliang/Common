/*
 *  Copyright (C) 2013-2016 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.log;

public final class Logger {

    public interface LogRecorder {
        public void logging(String level, String tag, String msg);
    }

    public static final String TAG = "wrapper";
    
    private static boolean debug = true;

    private static LogRecorder recorder = null;

    private static final LogRecorder CONSOLE_RECORDER = new LogRecorder() {
        @Override
        public void logging(String level, String tag, String msg) {
            //StringBuilder sb = new StringBuilder();
            //sb.append("[").append(tag).append("]").append(msg);
            //System.out.println(sb.toString());
        }
    };

    public static LogRecorder getRecorder() {
        return recorder;
    }
    public static void setRecorder(LogRecorder recorder) {
        Logger.recorder = recorder;
    }

    public static boolean isDebugEnabled() {
        return debug;
    }
    public static void setDebugEnabled(boolean enabled) {
        debug = enabled;
    }

    private static void logging(String level, String tag, String msg) {
        if (recorder != null) {
            recorder.logging(level, tag, msg);
        } else {
            CONSOLE_RECORDER.logging(level, tag, msg);
        }
    }

    public static void debug(String msg) {
        debug(TAG, msg);
    }
    public static void debug(String tag, String msg) {
        if (isDebugEnabled()) {
            logging("debug", tag, msg);
        }
    }

    public static void info(String msg) {
        info(TAG, msg);
    }
    public static void info(String tag, String msg) {
        logging("info", tag, msg);
    }
    
    public static void warn(String msg) {
        warn(TAG, msg);
    }
    
    public static void warn(String tag, String msg) {
        logging("warn", tag, msg);
    }
    
    private Logger() {}

}
