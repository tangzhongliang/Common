package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


public class CUIUtil {

	private static CUIUtil instance;
	
	private CUIUtil() {
	}
	
	public static CUIUtil instance() {
	    if(instance == null) {
	        instance = new CUIUtil();
	    }
		return instance;
	}
    
    /**
     * 指定されたアプリケーションがフォアグランド状態にあるかを取得します。
     * Obtains whether or not the specified application is in the foreground state.
     *
     * @param packageName アプリケーションのパッケージ名
     *                    Application package name
     * @return フォアグラウンド状態にある場合にtrue
     *         If the application is in the foreground state, true is returned.
     */
    public static boolean isForegroundApp(Application app) {
    	String packageName = app.getPackageName();
        boolean result = false;
        ActivityManager am = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> list = am.getRunningAppProcesses();
        for (RunningAppProcessInfo info : list) {
            if (packageName.equals(info.processName)) {
                result = (info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND);
                break;
            }
        }
        return result;
    }

    /**
     * 指定されたアプリケーションのアクティビティスタックの最上位クラスを取得します。
     * Obtains the top class in the activity stack of the specified application.
     *
     * @param packageName アプリケーションのパッケージ名
     *                    Application package name
     * @return 最上位クラスのFQCNクラス名. 取得できない場合はnull
     *         The name of the FQCN class name of the top class. If the name cannot be obtained, null is returned.
     */
    public static String getTopActivityClassName(Application app) {
    	String packageName = app.getPackageName();
        ActivityManager am = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(30);
        for (RunningTaskInfo info : list) {
            if (packageName.equals(info.topActivity.getPackageName())) {
                return info.topActivity.getClassName();
            }
        }
        return null;
    }

    /**
     * 指定されたアプリケーションのアクティビティスタック内のアクティビティ数を取得します。
     * Obtains the number of activities in the activity stack of the specified application.
     *
     * @param packageName アプリケーションのパッケージ名
     *                    Application package name
     * @return アクティビティ数. 取得できない場合は0
     *         The number of activitys. If the number cannot be obtained, 0 is returned.
     */
    public static int getNumActivities(Application app) {
    	String packageName = app.getPackageName();
        ActivityManager am = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(30);
        for (RunningTaskInfo info : list) {
            if (packageName.equals(info.topActivity.getPackageName())) {
                return info.numActivities;
            }
        }
        return 0;
    }
    
//    public static void showToastCenter(Context context, String msg) {
//    	Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//    	toast.setGravity(Gravity.CENTER, 0, 0);
//    	toast.show();
//    }
    
}
