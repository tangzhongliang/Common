package jp.co.ricoh.advop.mini.cheetahminiutil.logic;

import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.JobData;
import jp.co.ricoh.advop.mini.cheetahminiutil.print.PrintManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUIUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import java.util.ResourceBundle;

public class CHolder {

    private static CHolder instance;
    private JobManager jobManager;
    private ScanManager scanManager;
    private PrintManager printManager;
    private BaseApplication application;
    private Handler mainUIHandler;
    private LockManager lockManager;
    private JobData jobData;
    private boolean isForeground;
    private Activity activity;
    private InitParameters initParameters;
    private boolean isRestarting;
//    private boolean isRestarting4Language;


    public CHolder(BaseApplication app) {
        // do not modify this function!
        // do init work in init()
        application = app;
        instance = this;
//        isRestarting = false;
//        isRestarting4Language = false;
    }

    public void init() {
        mainUIHandler = new Handler();
        jobManager = new JobManager();
        scanManager = new ScanManager();
        printManager = new PrintManager();
        lockManager = new LockManager();
        jobData = new JobData();
        isForeground = true;
    }

    public InitParameters getInitParameters() {
        return initParameters;
    }

    public void setInitParameters(InitParameters initParameters) {
        LogC.init(initParameters.getAppLogTag());
        this.initParameters = initParameters;
    }

    public static CHolder instance() {
        return instance;
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    public BaseApplication getApplication() {
        return application;
    }

    public Handler getMainUIHandler() {
        return mainUIHandler;
    }

    public ScanManager getScanManager() {
        return scanManager;
    }

    public PrintManager getPrintManager() {
        return printManager;
    }

    public LockManager getLockManager() {
        return lockManager;
    }

    public JobData getJobData() {
        return jobData;
    }

    public boolean isForeground() {
        return isForeground;
    }

    public void setForeground(boolean isForeground) {
        this.isForeground = isForeground;
        if (isForeground) {
            CUtil.setPrelongTime(0);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void restartApp() {
        if (activity != null)
            activity.finishAffinity();
    }

    public Application getApp() {
        return application;
    }
//    
//    public void restartApp4Language() {
//        LogC.i("restartApp4Language");
//        if (getJobData().getCurrentJob() != null) {
//            LogC.i("job is running, restartApp4Language wait");
//            isRestarting4Language = true;
//            if (CHolder.instance().getScanManager().isJobRunning()) {
//                CHolder.instance().getScanManager().getStateMachine()
//                        .procScanEvent(ScanEvent.REQUEST_JOB_CANCEL);
//            }
//            if (CHolder.instance().getPrintManager().isJobRunning()) {
//                CHolder.instance().getPrintManager().getStateMachine()
//                        .procPrintEvent(PrintEvent.REQUEST_JOB_CANCEL);
//            }
//        } else {
//            restartApp();
//        }
//        
//    }
//    
//    public void restartAppWhenJobEnd() {
//
//        if(!isRestarting4Language) {
//            return;
//        }
//        isRestarting4Language = false;
//        if (!CUIUtil.isForegroundApp(getApplication())) {
//            restartApp();
//            return;
//        }
//        
//        // restart when Foreground
//        mainUIHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                isRestarting = true;
//                if (!(getActivity() instanceof SplashActivity)) {
//                    LogC.d("restart FLAG_ACTIVITY_CLEAR_TOP");
//                    Intent intentFinish = new Intent(getActivity(), SplashActivity.class);
//                    intentFinish.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    getActivity().startActivity(intentFinish);
//                }
////                MultiButtonDialog.createMsgDialog(getActivity(), "Language Changed! \n Click [OK] to restart app!", new BaseDialogOnClickListener() {
////                    
////                    @Override
////                    public void onWork(BaseDialog dialog) {
////                        dialog.dismiss();
////                        isRestarting = true;
////                        if (!(getActivity() instanceof SplashActivity)) {
////                            LogC.d("restart FLAG_ACTIVITY_CLEAR_TOP");
////                            Intent intentFinish = new Intent(getActivity(), SplashActivity.class);
////                            intentFinish.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                            getActivity().startActivity(intentFinish);
////                        }
////                    }
////                }).show();
//            }
//        });
//    }
//
//    public boolean isRestarting4Language() {
//        return isRestarting4Language;
//    }
//
//    public boolean isRestarting() {
//        return isRestarting;
//    }
//
//    public void setRestarting(boolean isRestarting) {
//        this.isRestarting = isRestarting;
//    }
}
