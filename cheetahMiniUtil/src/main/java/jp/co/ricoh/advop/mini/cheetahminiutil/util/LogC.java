package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.os.Build;
import android.util.Log;

public class LogC {
	public static boolean runInEMU = false;//"Generic Android-x86".equals(Build.MODEL) && "x86".equals(Build.CPU_ABI);//false;
	public static boolean isMT = false;
	
	private final static int DEBUG = 3;
	private final static int WARN = 2;
	private final static int ERROR_AND_INFO = 1;
//	private final static int NONE = 0;
	
	private static int logLevel = DEBUG;
	
	private static String appTag = "";
	
	public static void init(String tag) {
		appTag = "M_" + tag;
	}
	
	private static String createTag(String tag) {
		if (CUtil.isStringEmpty(tag)) {
			return appTag;
		} else {
			return appTag + ":" + tag;
		}
	}

	public static void d(String tag, String msg) {
		if (logLevel >= DEBUG) {
			Log.d(createTag(tag), msg);
		}
	}
	
	public static void i(String tag, String msg) {
		if (logLevel >= ERROR_AND_INFO) {
			Log.i(createTag(tag), msg);
		}
	}
	
	public static void i(String msg) {
		LogC.i("", msg);
	}
	
	public static void d(String msg) {
		LogC.d("", msg);
	}
	
	public static void w(String tag, String msg) {
		if (logLevel >= WARN) {
			Log.w(createTag(tag), msg);
		}
	}
	
	public static void w(String msg) {
		LogC.w("", msg);
	}
	
	public static void w(String tag, String msg, Throwable tr) {
		if (logLevel >= WARN) {
			Log.w(createTag(tag), msg, tr);
		}
	}
	
	public static void w(Throwable tr) {
		LogC.w("", "", tr);
	}
	
	public static void w(String msg, Throwable tr) {
		LogC.w("", msg, tr);
	}
	
	public static void e(String tag, String msg) {
		if (logLevel >= ERROR_AND_INFO) {
			Log.e(createTag(tag), msg);
		}
	}
	
	public static void e(String msg) {
		LogC.e("", msg);
	}
	
	public static void e(String tag, String msg, Throwable tr) {
		if (logLevel >= ERROR_AND_INFO) {
			Log.e(createTag(tag), msg, tr);
		}
	}
	
	public static void e(String msg, Throwable tr) {
		LogC.e("", msg, tr);
	}

	public static void timestamp(String msg) {
		LogC.e("", msg + ":" + System.currentTimeMillis());
	}
}
