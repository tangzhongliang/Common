package jp.co.ricoh.advop.mini.cheetahminiutil.scan;

import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OccuredErrorLevel;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUIUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;
import android.os.AsyncTask;

public class ScanSysAlertDisplay {

    /**
     * システム警告画面が表示されているかのフラグ
     * Flag to indicate if system warning screen is displayed
     */
    private volatile boolean mAlertDialogDisplayed = false;
    
    private ScanManager scanManager;
    private BaseApplication application;
    
    private boolean dispErrorOnlyInJobRunning = false;
    
    public ScanSysAlertDisplay() {
        scanManager = CHolder.instance().getScanManager();
        application = CHolder.instance().getApplication();
        dispErrorOnlyInJobRunning = true;
    }
    
    private boolean needDisp() {
//        if (dispErrorOnlyInJobRunning) {
//            return scanManager.isJobRunning();
//        }
        return true;
    }
    
    public void showOnResume() {
        new AsyncTask<Void, Void, ScanServiceAttributeSet>() {

            @Override
            protected ScanServiceAttributeSet doInBackground(Void... params) {
                return scanManager.getScanService().getAttributes();
            }

            @Override
            protected void onPostExecute(ScanServiceAttributeSet attributes) {
                OccuredErrorLevel errorLevel = (OccuredErrorLevel) attributes.get(OccuredErrorLevel.class);

                if (OccuredErrorLevel.ERROR.equals(errorLevel)
                        || OccuredErrorLevel.FATAL_ERROR.equals(errorLevel)) {
                    ScannerState state = (ScannerState) attributes.get(ScannerState.class);
                    ScannerStateReasons stateReasons = (ScannerStateReasons) attributes.get(ScannerStateReasons.class);

                    String stateString = makeAlertStateString(state);
                    String reasonString = makeAlertStateReasonString(stateReasons);
                    
                    if (!needDisp()) {
                        return;
                    }

                    application.displayAlertDialog(Const.ALERT_DIALOG_APP_TYPE_SCANNER, stateString, reasonString);

                    mAlertDialogDisplayed = true;
                }
            }
            
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);//.execute();
    }
    
    public void showOnServiceAttributeChange(ScannerState state, ScannerStateReasons stateReasons, OccuredErrorLevel errorLevel) {
        if (OccuredErrorLevel.ERROR.equals(errorLevel)
                || OccuredErrorLevel.FATAL_ERROR.equals(errorLevel)) {

            if (!needDisp()) {
                return;
            }
            
            String stateString = makeAlertStateString(state);
            String reasonString = makeAlertStateReasonString(stateReasons);

            if (mAlertDialogDisplayed) {
                application.updateAlertDialog(Const.ALERT_DIALOG_APP_TYPE_SCANNER, stateString, reasonString);
            } else if (CUIUtil.isForegroundApp(application)) {
                application.displayAlertDialog(Const.ALERT_DIALOG_APP_TYPE_SCANNER, stateString, reasonString);
                mAlertDialogDisplayed = true;
            }

        } else {
            // Error -> Normal
            if (mAlertDialogDisplayed) {
                String activityName = CUIUtil.getTopActivityClassName(application);
                if (activityName == null) {
                    activityName = CHolder.instance().getInitParameters().getMainActivityClass().getName();
                }
                application.hideAlertDialog(Const.ALERT_DIALOG_APP_TYPE_SCANNER, activityName);
                mAlertDialogDisplayed = false;
            }
        }
    }

    /**
     * システム警告画面表示要求に渡す状態文字列を生成します。
     * Creates the state string to be passed to system warning screen display request.
     *
     * @param state スキャンサービス状態
     *              State of scan service
     * @return 状態文字列
     *         State string
     */
    private String makeAlertStateString(ScannerState state) {
        String stateString = "";
        if (state != null) {
            stateString = state.toString();
        }
        return stateString;
    }
    

    /**
     * システム警告画面表示要求に渡す状態理由文字列を生成します。
     * 複数の状態理由があった場合、1つ目の状態理由のみを渡します。
     * Creates the state reason string to be passed to the system warning screen display request.
     * If multiple state reasons exist, only the first state reason is passed.
     *
     * @param stateReasons スキャナサービス状態理由
     *                     Scan service state reason
     * @return 状態理由文字列
     *         State reason string
     */
   private String makeAlertStateReasonString(ScannerStateReasons stateReasons) {
        String reasonString = "";
        if (stateReasons != null) {
            Object[] reasonArray = stateReasons.getReasons().toArray();
            if (reasonArray != null && reasonArray.length > 0) {
                reasonString = reasonArray[0].toString();
            }
        }
        return reasonString;
    }

}
