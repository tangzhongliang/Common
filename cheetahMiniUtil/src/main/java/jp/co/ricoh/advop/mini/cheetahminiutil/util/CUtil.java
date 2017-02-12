package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CUtil {

//	private static CUtil instance;
	
	private CUtil() {
	}
    // for button double click
	static long prelongTime = 0;

    static long myPrelongTime = 0;


    // for button double click
	static long INTERVAL_TIME = 300;
//	public static CUtil instance() {
//	    if(instance == null) {
//	        instance = new CUtil();
//	    }
//		return instance;
//	}
	
    public static boolean isStringEmpty(String str) {
        if(str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String target) {
        return !isStringEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }         
    
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LogC.w("sleep", e);
        }
    } 

    // for button double click 	
    protected static boolean isContinue(){
//    	if(prelongTime == 0){
//			prelongTime = (new Date()).getTime();     
//		}
    	long curTime = (new Date()).getTime();
    	LogC.d("-----------------interval time is " + (curTime - prelongTime));
    	
    	if((curTime - prelongTime) < INTERVAL_TIME) {    		
    		return false;
    	} else {
    		prelongTime = curTime;
    		return true;
    	}
    }

    public static boolean isContinue(int intervalTime){
//    	if(prelongTime == 0){
//			prelongTime = (new Date()).getTime();
//		}
        long curTime = (new Date()).getTime();
        LogC.d("-----------------interval time is " + (curTime - myPrelongTime));

        if((curTime - myPrelongTime) < intervalTime) {
            return false;
        } else {
            myPrelongTime = curTime;
            return true;
        }
    }

    public static String MillisToDate(long mills){
        Date d = new Date(mills);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(d);
    }


    // for button double click
    public static void setPrelongTime(long prelongTime) {
		CUtil.prelongTime = prelongTime;
	}
//    public <E> List<E> createElementList() {
//		return new ArrayList<E>();
//	}
}
