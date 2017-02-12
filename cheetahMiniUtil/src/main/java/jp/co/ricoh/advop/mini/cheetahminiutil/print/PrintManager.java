package jp.co.ricoh.advop.mini.cheetahminiutil.print;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.EXE_RESULT;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.ExeCallback;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncConnectState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintFile;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintFile.PDL;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintJob;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintService;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.HashPrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Copies;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Magnification;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Buzzer;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.HDDUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;


public class PrintManager {
    private final static String TAG = PrintManager.class.getSimpleName();
    private BaseApplication application;

    private PrintService mPrintService;
    private PrintJob mPrintJob;
//    private PrintJobAttributeListener mJobAttributeListener;

    private PrintRequestAttributeSet printRequestAttributeSet;
    private List<PDL> supportedPDL;
    private Map<PrintFile.PDL, JobSettingSupportedHolder> mSettingDataHolders = new HashMap<PDL, JobSettingSupportedHolder>();

    //alert dialog
    AlertDialog mAlertDialog = null;
    AlertDialog.Builder mBuilder = null;

    PrintFile.PDL mCurrentPDL = null;

    /**
     * 印刷に実現するために必要なイベントを受け取るためのリスナー
     * Listener to receive print service attribute event.
     */
    private PrintServiceAttributeListenerImpl mServiceAttributeListener;


    private PrintSysAlertDisplay printSysAlertDisplay;
    private PrintJobListener mPrintJobListener;

    public PrintManager() {
        application = CHolder.instance().getApplication();
    }

    public void onAppInit() {
        mPrintService = PrintService.getService();
        printSysAlertDisplay = new PrintSysAlertDisplay();
    }

    public void onAppDestroy() {
    }

    // run async
    public EXE_RESULT intiPrintService(AsyncTask task) {
        mServiceAttributeListener = new PrintServiceAttributeListenerImpl();
        AsyncConnectState addListenerResult = null;
        AsyncConnectState getAsyncConnectStateResult = null;
        
        /*
         * UIスレッドのバックグラウンドで実行されるメソッドです。
         * [処理内容]
         *   (1)プリントサービスのイベントを受信するリスナーを設定します。
         *      機器が利用可能になるか、キャンセルが押されるまでリトライします。
         *   (2)非同期イベントの接続確認を行います。
         *      接続可能になるか、キャンセルが押されるまでリトライします。
         *   (3)接続に成功した場合は、プリントサービスから各設定の設定可能値を取得します。
         *
         * Runs in the background on the UI thread.
         * [Processes]
         *   (1) Sets the listener to receive print service events.
         *       This task repeats until the machine becomes available or cancel button is touched.
         *   (2) Confirms the asynchronous connection.
         *       This task repeats until the connection is confirmed or cancel button is touched.
         *   (3) After the machine becomes available and connection is confirmed,
         *       obtains job setting values.
         */ //(1)
        while (true) {
            if (task.isCancelled()) {
                return EXE_RESULT.CANCELED;
            }
            addListenerResult = mPrintService.addPrintServiceAttributeListener(mServiceAttributeListener);

            if (addListenerResult == null) {
                CUtil.sleep(100);
                continue;
            }

            if (addListenerResult.getState() == AsyncConnectState.STATE.CONNECTED) {
                break;
            }

            if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.NO_ERROR) {
                // do nothing
            } else if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.BUSY) {
                CUtil.sleep(10000);
            } else if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.TIMEOUT) {
                // do nothing
            } else if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.INVALID) {
                return checkInitResult(addListenerResult, getAsyncConnectStateResult);
            } else {
                // unknown state
                return checkInitResult(addListenerResult, getAsyncConnectStateResult);
            }
        }

        if (addListenerResult.getState() != AsyncConnectState.STATE.CONNECTED) {
            return checkInitResult(addListenerResult, getAsyncConnectStateResult);
        }

        //(2)
        while (true) {
            if (task.isCancelled()) {
                return EXE_RESULT.CANCELED;
            }
            getAsyncConnectStateResult = mPrintService.getAsyncConnectState();

            if (getAsyncConnectStateResult == null) {
                CUtil.sleep(100);
                continue;
            }

            if (getAsyncConnectStateResult.getState() == AsyncConnectState.STATE.CONNECTED) {
                break;
            }

            if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.NO_ERROR) {
                // do nothing
            } else if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.BUSY) {
                CUtil.sleep(10000);
            } else if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.TIMEOUT) {
                // do nothing
            } else if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.INVALID) {
                return checkInitResult(addListenerResult, getAsyncConnectStateResult);
            } else {
                // unknown state
                return checkInitResult(addListenerResult, getAsyncConnectStateResult);
            }
        }

        //(3)
        if (addListenerResult.getState() == AsyncConnectState.STATE.CONNECTED
                && getAsyncConnectStateResult.getState() == AsyncConnectState.STATE.CONNECTED) {

            supportedPDL = mPrintService.getSupportedPDL();
            if (supportedPDL == null) return EXE_RESULT.FAILED;

            for (PrintFile.PDL pdl : supportedPDL) {
                mSettingDataHolders.put(pdl,
                        new JobSettingSupportedHolder(mPrintService, pdl));
            }

        }

        return checkInitResult(addListenerResult, getAsyncConnectStateResult);
    }

    private EXE_RESULT checkInitResult(AsyncConnectState addListenerResult, AsyncConnectState getAsyncConnectStateResult) {

        if (addListenerResult != null
                && getAsyncConnectStateResult != null
                && addListenerResult.getState() == AsyncConnectState.STATE.CONNECTED
                && getAsyncConnectStateResult.getState() == AsyncConnectState.STATE.CONNECTED) {
//            mStateMachine.procPrintEvent(PrintStateMachine.PrintEvent.CHANGE_APP_ACTIVITY_STARTED);;
            return EXE_RESULT.SUCCESSED;
        } else {
            if (addListenerResult != null) {
                LogC.d(TAG, "addPrinterServiceAttributeListener:" + addListenerResult.getState() + "," + addListenerResult.getErrorCode());
            }
            if (getAsyncConnectStateResult != null) {
                LogC.d(TAG, "getAsyncConnectState:" + getAsyncConnectStateResult.getState() + "," + getAsyncConnectStateResult.getErrorCode());
            }
            // the connection is invalid.
            LogC.d(TAG, "init Printer service failed");
//            mStateMachine.procPrintEvent(PrintStateMachine.PrintEvent.CHANGE_APP_ACTIVITY_START_FAILED);
            return EXE_RESULT.FAILED;
        }
    }

    public void destroyPrintService() {
//        mStateMachine.procPrintEvent(PrintStateMachine.PrintEvent.CHANGE_APP_ACTIVITY_DESTROYED);
        if (mServiceAttributeListener != null) {
            mPrintService.removePrintServiceAttributeListener(mServiceAttributeListener);
        }
    }

    /**
     * プリントサービスを取得します。
     * obtains the print service
     */
    public PrintService getPrintService() {
        return mPrintService;
    }

    public PrintSysAlertDisplay getPrintSysAlertDisplay() {
        return printSysAlertDisplay;
    }


    /**
     * 印刷を開始します。
     * Start print.
     *
     * @param holder
     */
    public void startPrint(ExeCallback exeCallback, PrintRequestAttributeSet printConditions, String path) {
//        mStateMachine.procPrintEvent(PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_INITIAL, null);
//        mStateMachine.procPrintEvent(PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_PRE_PROCESS, null);
        if (printConditions == null) {
            printConditions = createPrintRequestAttributeSet(path);
        }

        setPrintRequestAttributeSet(printConditions);
        new StartPrintJobTask(exeCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, path);

    }

    public EXE_RESULT startPrintSync(PrintRequestAttributeSet printConditions, String path) {
        if (printConditions == null) {
            printConditions = createPrintRequestAttributeSet(path);
        }

        setPrintRequestAttributeSet(printConditions);

        boolean result = startPrintLocal(path);
        if (!result) {
            showErrorMessageDialog();
            //ToastUtil.showToast(CHolder.instance().getActivity(), messageStartPrint, ToastUtil.ToastLengthType.TYPE_LONG);
            return EXE_RESULT.ERR_PRINT;
        }
        return EXE_RESULT.SUCCESSED;
    }

    /**
     * プリントジョブを初期化します。
     * Initializes the print job.
     */
    public void initialJob() {
        if (mPrintJob != null) {
//            if (mJobAttributeListener != null) {
//                mPrintJob.removePrintJobAttributeListener(mJobAttributeListener);
//            }
            if (mPrintJobListener != null) {
                mPrintJob.removePrintJobListener(mPrintJobListener);
            }
        }

        mPrintJob = new PrintJob();
//
        if (mPrintJobListener == null)
            mPrintJobListener = new PrintJobImplListener();
        mPrintJob.addPrintJobListener(mPrintJobListener);
    }

    public void setPrintJobListener(PrintJobListener mPrintJobListener) {
        this.mPrintJobListener = mPrintJobListener;
    }

    /**
     * プリントジョブを取得します。
     * Obtains the print job.
     */
    public PrintJob createPrintJob() {
        initialJob();
        return mPrintJob;
    }

    public PrintJob getPrintJob() {
        return mPrintJob;
    }

//    /**
//     * ジョブ状態を監視するリスナークラスです。
//     * The listener class to monitor scan job state changes.
//     */
//    class PrintJobAttributeListenerImpl implements PrintJobAttributeListener {
//        /**
//         * ジョブ状態が変化すると呼び出されるメソッドです。
//         * ジョブ状態を示すイベントを受信し、受信したイベントに応じてステートマシンにイベントをポストします。
//         * Called when the job state changes.
//         * Receives the job state event and posts the appropriate event to the statemachine.
//         */
//        @Override
//        public void updateAttributes(PrintJobAttributeEvent attributesEvent) {
//            PrintJobAttributeSet attributeSet = attributesEvent.getUpdateAttributes();
//
//            PrintJobState jobState = (PrintJobState)attributeSet.get(PrintJobState.class);
//            LogC.d(getClass().getSimpleName(), "JobState[" + jobState + "]");
//            if(jobState == null) return;
//            Object reasons = null;
//            PrintServiceAttributeSet serviceAttributeSet = null;
//            switch (jobState) {
//                case PENDING:
//                    mStateMachine.procPrintEvent(
//                            PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_PENDING, null);
//                    break;
//                case PROCESSING:
//                    PrintJobPrintingInfo printingInfo = (PrintJobPrintingInfo)attributeSet.get(
//                            PrintJobPrintingInfo.class);
//
//                    mStateMachine.procPrintEvent(
//                            PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_PROCESSING, printingInfo);
//                    break;
//                case PROCESSING_STOPPED:
//                    serviceAttributeSet = mPrintService.getAttributes();
//                    reasons = (PrintJobStateReasons)attributeSet.get(PrintJobStateReasons.class);
//                    if(reasons == null) {
//                        reasons = (PrinterStateReasons) serviceAttributeSet.get((PrinterStateReasons.class));
//                    }
//
//                    mStateMachine.procPrintEvent(
//                            PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_PROCESSING_STOPPED, reasons);
//                    break;
//                case ABORTED:
//                    serviceAttributeSet = mPrintService.getAttributes();
//                     reasons = (PrintJobStateReasons)attributeSet.get(PrintJobStateReasons.class);
//                     if(reasons == null) {
//                         reasons = (PrinterStateReasons) serviceAttributeSet.get((PrinterStateReasons.class));
//                     }
//
//                    mStateMachine.procPrintEvent(
//                            PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_ABORTED, reasons);
//                    break;
//                case CANCELED:
//                    mStateMachine.procPrintEvent(
//                            PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_CANCELED, null);
//                    break;
//                case COMPLETED:
//                    mStateMachine.procPrintEvent(
//                            PrintStateMachine.PrintEvent.CHANGE_JOB_STATE_COMPLETED, null);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

    public PrintRequestAttributeSet createPrintRequestAttributeSet(String fileName) {
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        Set<Class<? extends PrintRequestAttribute>> categories;
        categories = mSettingDataHolders.get(getPDL(fileName)).getSelectableCategories();

        if (null == categories) {
            return attributeSet;
        }

        if (categories.contains(Copies.class)) {
            attributeSet.add(new Copies(1));
        } else {
            // do nothing
        }

        attributeSet.add(new Magnification("fitting"));

        return attributeSet;
    }

    public PrintRequestAttributeSet getPrintRequestAttributeSet() {
        return printRequestAttributeSet;
    }

    public void setPrintRequestAttributeSet(PrintRequestAttributeSet printRequestAttributeSet) {
        this.printRequestAttributeSet = printRequestAttributeSet;
    }

    private String messageStartPrint;

    private Boolean startPrintLocal(String path) {
        messageStartPrint = CHolder.instance().getActivity().getResources().getString(R.string.print_fail);
        PrintFile printFile;
        try {
            File file = new File(path);

            if (file.exists() && file.isFile()) {
                LogC.d("print file : " + path);
                String copyName = "printTmp" + System.currentTimeMillis() + file.getName();
                HDDUtil.copyFile(file.getPath(), file.getParentFile().getPath() + "/" + copyName);
                File[] files = file.getParentFile().listFiles();
                for (File tmpFile : files) {
                    if (tmpFile.getName().startsWith("printTmp")) {
                        long createTime = System.currentTimeMillis();
                        try {
                            String time = tmpFile.getName().substring(8, 21);
                            createTime = Long.parseLong(time);
                        } catch (Exception e) {
                            LogC.e(e.toString());
                        }
                        if (System.currentTimeMillis() - createTime > 30 * 60 * 1000) {
                            file.delete();
                        }
                    }
                }
                printFile = (new PrintFile.Builder()).printerFilePath(file.getParentFile().getPath() + "/" + copyName).pdl(PrintManager.this.getPDL(file.getParentFile().getPath() + "/" + copyName)).build();
            } else {
                messageStartPrint = CHolder.instance().getActivity().getResources().getString(R.string.print_fail);
                return false;
            }
        } catch (PrintException e) {
            //Toast.makeText(mContext, "get print file fail", Toast.LENGTH_LONG).show();
            LogC.e("get print file error happen!");
            if (e instanceof PrintResponseException) {
                messageStartPrint = makeJobErrorResponseMessage((PrintResponseException) e, messageStartPrint);
            } else if (CUtil.isStringEmpty(messageStartPrint)) {
                messageStartPrint = CHolder.instance().getActivity().getResources().getString(R.string.print_fail);// + e.getMessage();
            }

            return false;
        }

        PrintRequestAttributeSet attributeSet = PrintManager.this.getPrintRequestAttributeSet();
        if (attributeSet == null) {
            attributeSet = PrintManager.this.createPrintRequestAttributeSet(path);
        }
        if (printFile == null || attributeSet == null) {
            return false;
        }

        try {
            LogC.d("---------------StartPrintJobTask--------------");
            PrintManager.this.initialJob();
            PrintManager.this.createPrintJob().print(printFile, attributeSet, null);
            return true;
        } catch (PrintException e) {
            LogC.e("start print job error happen!");
            if (e instanceof PrintResponseException) {
                messageStartPrint = makeJobErrorResponseMessage((PrintResponseException) e, messageStartPrint);
            } else if (CUtil.isStringEmpty(messageStartPrint)) {
                messageStartPrint = CHolder.instance().getActivity().getResources().getString(R.string.print_fail);// + e.getMessage();
            }
        }
        return false;
    }

    private String makeJobErrorResponseMessage(PrintResponseException e, String base) {
        if (base == null) {
            base = "";
        }
        StringBuilder sb = new StringBuilder(base);
        if (e.hasErrors()) {
            Map<String, String> errors = e.getErrors();
            if (errors.size() > 0) {
                for (Map.Entry<String, String> entry : errors.entrySet()) {
                    LogC.e("Print Job Error Response id:" + entry.getKey());
                    if (Const.JOB_ERROR_MAP.get(entry.getKey()) != null) {
                        sb.append(CHolder.instance().getActivity().getString(Const.JOB_ERROR_MAP.get(entry.getKey())));
                        break;
                    }
                }
            }
        }

        if (CUtil.isStringEmpty(sb.toString())) {
            sb.append(CHolder.instance().getActivity().getString(R.string.print_fail));
        }
        return sb.toString();
    }

    class StartPrintJobTask extends AsyncTask<String, Void, Boolean> {
        private ExeCallback exeCallback;

        public StartPrintJobTask(ExeCallback exeCallback) {
            this.exeCallback = exeCallback;
        }

        @Override
        protected Boolean doInBackground(String... path) {
            return PrintManager.this.startPrintLocal(path[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                //ToastUtil.showToast(CHolder.instance().getActivity(), messageStartPrint, ToastUtil.ToastLengthType.TYPE_LONG);
                showErrorMessageDialog();
                exeCallback.onComplete(EXE_RESULT.ERR_PRINT);
                return;
            }
            exeCallback.onComplete(EXE_RESULT.SUCCESSED);
        }
    }

    private void showErrorMessageDialog() {
        mBuilder = new AlertDialog.Builder(CHolder.instance().getActivity());
        mAlertDialog = mBuilder.create();

        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(R.layout.dialog_alert);
        mAlertDialog.setCancelable(false);

        mAlertDialog.getWindow().findViewById(R.id.button_alert_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buzzer.play(0);
                mAlertDialog.dismiss();
            }
        });

        TextView textView = (TextView) mAlertDialog.getWindow().findViewById(R.id.textView_mydialog_error_line_1);
        textView.setText(messageStartPrint);
    }

    public PrintFile.PDL getPDL(String fileName) {

        if (fileName == null) {
            return null;
        }

        //(1)
        PrintFile.PDL currentPDL = null;
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        //(2)
        List<PDL> pdlList = supportedPDL;

        //(3)
        if (ext.equals("pdf")
                && pdlList.contains(PDL.PDF)) {
            currentPDL = PDL.PDF;
        } else if (ext.equals("prn")
                && pdlList.contains(PDL.RPCS)) {
            currentPDL = PDL.RPCS;
        } else if (ext.equals("xps")
                && pdlList.contains(PDL.XPS)) {
            currentPDL = PDL.XPS;
        } else if ((ext.equals("tif") || (ext.equals("tiff")) || (ext.equals("jpg")) || (ext.equals("jpeg")))
                && pdlList.contains(PDL.TIFF)) {
            currentPDL = PDL.TIFF;
        } else {
            currentPDL = PDL.PCL;
        }

        return currentPDL;
    }

    public Map<PDL, JobSettingSupportedHolder> getmSettingDataHolders() {
        return mSettingDataHolders;
    }
}
