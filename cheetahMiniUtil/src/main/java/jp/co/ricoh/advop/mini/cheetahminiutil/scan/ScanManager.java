package jp.co.ricoh.advop.mini.cheetahminiutil.scan;

import java.util.Iterator;
import java.util.Set;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.EXE_RESULT;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanStateMachine.ScanEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.Attribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.MediaSizeName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncConnectState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.ScanJob;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.ScanService;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.HashScanRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.AutoCorrectJobSetting;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.JobMode;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OccuredErrorLevel;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OriginalPreview;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OriginalSide;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OriginalSize;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanColor;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanDevice;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobScanningInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobSendingInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobStateReason;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanResolution;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerRemainingMemory;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerStateReason;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.WaitTimeForNextOriginal;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting.CompressionLevel;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting.CompressionMethod;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.FileSetting.FileFormat;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobAttributeListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanServiceAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanServiceAttributeListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import android.os.AsyncTask;
import android.os.Handler;


public class ScanManager {
    private final static String TAG = ScanManager.class.getSimpleName();
    private BaseApplication application;

    private ScanService mScanService;

    private ScanJob mScanJob;

    private ScanJobListener mScanJobListener;

    private ScanJobAttributeListener mScanJobAttrListener;

    private ScanServiceAttributeListener mScanServiceAttrListener;

    private ScanStateMachine mStateMachine;
    
    private ScanRequestAttributeSet scanRequestAttributeSet;

    private int scannedPages;
    
    private int remainMemory;

    /**
     * Maximum waiting time for accept the next page.
     * This timeout value supports "0" which means "keep waiting forever".
     */
    private int timeOfWaitingNextOriginal = 0;
    
    private ScanSysAlertDisplay scanSysAlertDisplay;
    
    public ScanManager() {
        application = CHolder.instance().getApplication();
    }
    
    public void onAppInit() {
        mStateMachine = new ScanStateMachine(new Handler());
        mScanService = ScanService.getService();
        scanSysAlertDisplay = new ScanSysAlertDisplay();
        mScanServiceAttrListener = new ScanServiceAttributeListenerImpl();
        initJobSetting();
    }
    
    public void onAppDestroy() {
        mScanService = null;
        mScanJob = null;
        mScanJobListener = null;
        mScanJobAttrListener = null;
        mStateMachine = null;
    }
    
    public boolean isJobRunning() {
        return mStateMachine.isJobRunning();
    }

    // run async
    public EXE_RESULT intiScanService(AsyncTask task) {
        // init scan service
        // from scan sample mainAct
        
        /*
         * Connects with the scan service asynchronously.
         * [Processes]
         *   (1) Sets the listener to receive scan service events.
         *       This task repeats until the machine becomes available or cancel button is touched.
         *   (2) Confirms the asynchronous connection.
         *       This task repeats until the connection is confirmed or cancel button is touched.
         *   (3) After the machine becomes available and connection is confirmed,
         *       obtains job setting values.
         */
        AsyncConnectState addListenerResult = null;
        AsyncConnectState getAsyncConnectStateResult = null;
        
        //(1)
        while (true) {
            if(task.isCancelled()) {
                return EXE_RESULT.CANCELED;
            }
            addListenerResult = mScanService.addScanServiceAttributeListener(mScanServiceAttrListener);

            if (addListenerResult == null) {
                CUtil.sleep(100);
                continue;
            }

            if (addListenerResult.getState() == AsyncConnectState.STATE.CONNECTED) {
                break;
            }

            if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.NO_ERROR){
                // do nothing
            } else if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.BUSY) {
                CUtil.sleep(10000);
            } else if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.TIMEOUT){
                // do nothing
            } else if (addListenerResult.getErrorCode() == AsyncConnectState.ERROR_CODE.INVALID){
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
            if(task.isCancelled()) {
                return EXE_RESULT.CANCELED;
            }
            getAsyncConnectStateResult = mScanService.getAsyncConnectState();

            if (getAsyncConnectStateResult == null) {
                CUtil.sleep(100);
                continue;
            }

            if (getAsyncConnectStateResult.getState() == AsyncConnectState.STATE.CONNECTED) {
                break;
            }

            if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.NO_ERROR){
                // do nothing
            } else if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.BUSY) {
                CUtil.sleep(10000);
            } else if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.TIMEOUT){
                // do nothing
            } else if (getAsyncConnectStateResult.getErrorCode() == AsyncConnectState.ERROR_CODE.INVALID){
                return checkInitResult(addListenerResult, getAsyncConnectStateResult);
            } else {
                // unknown state
                return checkInitResult(addListenerResult, getAsyncConnectStateResult);
            }
        }

//        //(3)
//        if (addListenerResult.getState() == AsyncConnectState.STATE.CONNECTED
//                && getAsyncConnectStateResult.getState() == AsyncConnectState.STATE.CONNECTED) {
//           if (!LogC.runInEMU) {
//                try {
//                    userCode = null;
//                    EXE_RESULT ret = checkRestrict();
//                    if (ret != EXE_RESULT.SUCCESSED) {
//                        mStateMachine.procScanEvent(ScanEvent.ACTIVITY_BOOT_FAILED);
//                        return ret;
//                    }
//                } catch (InvalidResponseException e) {
//                    LogC.e("checkRestrict failed!", e);
//                    mStateMachine.procScanEvent(ScanEvent.ACTIVITY_BOOT_FAILED);
//                    return EXE_RESULT.ERR_INIT;
//                }
//            }
//            
//            boolean ret = mScanSettingDataHolder.init(mScanService);
//            if (!ret) {
//                return EXE_RESULT.ERR_INIT;
//            } 
//        }
        
        // check result
        return checkInitResult(addListenerResult, getAsyncConnectStateResult);
    }
    
    private EXE_RESULT checkInitResult(AsyncConnectState addListenerResult, AsyncConnectState getAsyncConnectStateResult) {

        if (addListenerResult != null 
                && getAsyncConnectStateResult != null 
                && addListenerResult.getState() == AsyncConnectState.STATE.CONNECTED
                && getAsyncConnectStateResult.getState() == AsyncConnectState.STATE.CONNECTED) {
            mStateMachine.procScanEvent(ScanEvent.ACTIVITY_BOOT_COMPLETED);
            return EXE_RESULT.SUCCESSED;
        }
        else {
            if (addListenerResult != null ) {
                LogC.d(TAG, "addScanServiceAttributeListener:" + addListenerResult.getState() + "," + addListenerResult.getErrorCode());
            }
            if (getAsyncConnectStateResult != null ) {
                LogC.d(TAG, "getAsyncConnectState:" + getAsyncConnectStateResult.getState() + "," + getAsyncConnectStateResult.getErrorCode());
            }
            // the connection is invalid.
            LogC.d(TAG, "init scan service failed");
            mStateMachine.procScanEvent(ScanEvent.ACTIVITY_BOOT_FAILED);
            return EXE_RESULT.FAILED;
        }
    }
    
    public void destroyScanService() {
        mStateMachine.procScanEvent(ScanEvent.ACTIVITY_DESTROYED);
        if (mScanServiceAttrListener != null) {
            mScanService.removeScanServiceAttributeListener(mScanServiceAttrListener);
        }
    }
    
    /**
     * Initializes the scan job.
     */
    public void initJobSetting() {
        //If a state change listener is registered to the current scan job, the listener is removed.
        if(mScanJob!=null) {
            if(mScanJobListener!=null) {
                mScanJob.removeScanJobListener(mScanJobListener);
            }
            if(mScanJobAttrListener!=null) {
                mScanJob.removeScanJobAttributeListener(mScanJobAttrListener);
            }
        }

        mScanJob = new ScanJob();
        mScanJobListener = new ScanJobListenerImpl();
        mScanJobAttrListener = new ScanJobAttributeListenerImpl();

        //Registers a new listener to the new scan job.
        mScanJob.addScanJobListener(mScanJobListener);
        mScanJob.addScanJobAttributeListener(mScanJobAttrListener);
        

    }

    /**
     * The class to implement the listener to monitor scan job state changes.
     * [Processes]
     *    (1) If scan information exists, the information is notified to the state machine.
     *    (2) If data transfer information exists, the information is notified to the state machine.
     */
    class ScanJobAttributeListenerImpl implements ScanJobAttributeListener {

        @Override
        public void updateAttributes(ScanJobAttributeEvent attributesEvent) {
            ScanJobAttributeSet attributes = attributesEvent.getUpdateAttributes();
            mStateMachine.procScanEvent(ScanEvent.UPDATE_JOB_STATE_PROCESSING, attributes);

            //(1)
            ScanJobScanningInfo scanningInfo = (ScanJobScanningInfo) attributes.get(ScanJobScanningInfo.class);
            if (scanningInfo != null && scanningInfo.getScanningState()==ScanJobState.PROCESSING) {
                String status = application.getString(R.string.txid_scan_g_running_scan) + " "
                        + String.format(application.getString(R.string.txid_scan_d_count), scanningInfo.getScannedCount());
                scannedPages = Integer.valueOf(scanningInfo.getScannedCount());
                mStateMachine.procScanEvent(ScanEvent.UPDATE_JOB_STATE_PROCESSING, status);
            }
            if (scanningInfo != null && scanningInfo.getScanningState() == ScanJobState.PROCESSING_STOPPED) {
                timeOfWaitingNextOriginal = Integer.valueOf(scanningInfo.getRemainingTimeOfWaitingNextOriginal());
            }

            //(2)
            ScanJobSendingInfo sendingInfo = (ScanJobSendingInfo) attributes.get(ScanJobSendingInfo.class);
            if (sendingInfo != null && sendingInfo.getSendingState()==ScanJobState.PROCESSING) {
                String status = application.getString(R.string.txid_sending);
                mStateMachine.procScanEvent(ScanEvent.UPDATE_JOB_STATE_PROCESSING, status);
            }
        }

    }

    /**
     * The listener to monitor scan job state changes.
     */
    class ScanJobListenerImpl  implements ScanJobListener {

        @Override
        public void jobCanceled(ScanJobEvent event) {
            mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_CANCELED);
        }

        @Override
        public void jobCompleted(ScanJobEvent event) {
            mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_COMPLETED);
        }

        @Override
        public void jobAborted(ScanJobEvent event) {
            ScanJobAttributeSet attributes = event.getAttributeSet();
            mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_ABORTED, attributes);
        }

        @Override
        public void jobProcessing(ScanJobEvent event) {
            ScanJobAttributeSet attributes = event.getAttributeSet();
            mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_PROCESSING, attributes);
        }

        @Override
        public void jobPending(ScanJobEvent event) {
            mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_PENDING);
        }

        /**
         * Called when the job is in paused state.
         * If multiple reasons exist, only the first reason is checked.
         * [Processes]
         *   (1) For the pause event for waiting for the next document when using exposure glass
         *        - Sends the document waiting event to the state machine.
         *   (2) For the pause event for preview display
         *        - Sends the preview display event to the state machine.
         *   (3) For the pause event for other reasons
         *        - The job is cancelled in this case.
         */
        @Override
        public void jobProcessingStop(ScanJobEvent event) {
            ScanJobAttributeSet attributes = event.getAttributeSet();

            ScanJobStateReasons reasons = (ScanJobStateReasons) attributes.get(ScanJobStateReasons.class);
            if (reasons != null) {
                Set<ScanJobStateReason> reasonSet = reasons.getReasons();
                
                boolean isError = false;
                Iterator<ScanJobStateReason> iter = reasonSet.iterator();
                ScanJobStateReason jobStateReason = null;
                while(iter.hasNext()){
                    jobStateReason = iter.next();
                    switch(jobStateReason){
                        case SCANNER_JAM:
                        case MEMORY_OVER:
                        case EXCEEDED_MAX_EMAIL_SIZE:
                        case EXCEEDED_MAX_PAGE_COUNT:
                            isError = true;
                            break;
                        default :
                            isError = false;
                            break;
                    }
                    
                    if(isError){
                        break;
                    }                        
                }

                // show Preview window
                if (reasonSet.contains(ScanJobStateReason.WAIT_FOR_ORIGINAL_PREVIEW_OPERATION)) {
                    mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_STOPPED_PREVIEW);
                    return;
                }

                // show CountDown window
                if (reasonSet.contains(ScanJobStateReason.WAIT_FOR_NEXT_ORIGINAL_AND_CONTINUE)) {
                    mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_STOPPED_COUNTDOWN);
                    return;
                }

                // show Other reason job stopped window
                StringBuilder sb = new StringBuilder();
                for (ScanJobStateReason reason : reasonSet) {
                    //sb.append(reason.toString()).append("\n");
                    LogC.e("scan job error:" + reason);
                    if(Const.SCAN_ERROR_MAP.get(reason) != null) {
                        sb.append(CHolder.instance().getActivity().getString(Const.SCAN_ERROR_MAP.get(reason)));
                    } 
                }
                
                if(CUtil.isStringEmpty(sb.toString())) {
                   LogC.e("scan error other reason");
                   sb.append(CHolder.instance().getActivity().getString(R.string.scan_fail));                    
                }
                
                mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_STOPPED_OTHER, sb.toString());
                return;
            }
                        
            // Unknown job stop reason
            mStateMachine.procScanEvent(ScanEvent.CHANGE_JOB_STATE_STOPPED_OTHER, "(unknown reason)");
        }
    }

    /**
     * obtains the scan service
     */
    public ScanService getScanService() {
        return mScanService;
    }

    /**
     * Obtains the scan job.
     */
    public ScanJob getScanJob() {
        return mScanJob;
    }

    /**
     * Obtains the statemachine.
     */
    public ScanStateMachine getStateMachine() {
        return mStateMachine;
    }
    
    /**
     * Obtains the number of total pages scanned.
     */
    public int getScannedPages() {
        return scannedPages;
    }
    
    public void setScannedPages(int pages){
        scannedPages = pages;
    }
    public int getRemainMemory() {
        return remainMemory;
    }

    /**
     * Obtains the maximum waiting time.
     * @return
     */
    public int getTimeOfWaitingNextOriginal() {
        return timeOfWaitingNextOriginal;
    }
    
    public ScanSysAlertDisplay getScanSysAlertDisplay() {
        return scanSysAlertDisplay;
    }

    /**
     * The listener class to monitor scan service attribute changes.
     * [Processes]
     *   (1) Rewrites the scan service state display label accordingly to the scan service state.
     *   (2) Requests to display/update/hide error screens.
     */
    class ScanServiceAttributeListenerImpl implements ScanServiceAttributeListener {

        @Override
        public void attributeUpdate(final ScanServiceAttributeEvent event) {
            ScannerState state = (ScannerState)event.getAttributes().get(ScannerState.class);
            ScannerStateReasons stateReasons = (ScannerStateReasons)event.getAttributes().get(ScannerStateReasons.class);
            OccuredErrorLevel errorLevel = (OccuredErrorLevel) event.getAttributes().get(OccuredErrorLevel.class);
            ScannerRemainingMemory memory = (ScannerRemainingMemory)event.getAttributes().get(ScannerRemainingMemory.class);
            if (memory != null) {
                remainMemory = memory.getRemainingMemory();
                LogC.d("remainMemory="+ remainMemory);
            }
            
            //(1)
            switch(state) {
            case IDLE :
                LogC.d(TAG, "ScannerState : IDLE");
                break;
            case MAINTENANCE:
                LogC.d(TAG, "ScannerState : MAINTENANCE");
                break;
            case PROCESSING:
                LogC.d(TAG, "ScannerState : PROCESSING");
                break;
            case STOPPED:
                LogC.d(TAG, "ScannerState : STOPPED");
                break;
            case UNKNOWN:
                LogC.d(TAG, "ScannerState : UNKNOWN");
                break;
            default:
                LogC.d(TAG, "ScannerState : never reach here ...");
                /* never reach here */
                break;
            }

            if( stateReasons != null ) {
                Set<ScannerStateReason> reasonSet = stateReasons.getReasons();
                for(ScannerStateReason reason : reasonSet) {
                    switch(reason) {
                        case COVER_OPEN:
                            LogC.d(TAG, "ScannerState : COVER_OPEN");
                            break;
                        case MEDIA_JAM:
                            LogC.d(TAG, "ScannerState : MEDIA_JAM");
                            break;
                        case PAUSED:
                            LogC.d(TAG, "ScannerState : PAUSED");
                            break;
                        case OTHER:
                            LogC.d(TAG, "ScannerState : OTHER");
                            break;
                        default:
                            /* never reach here */
                            break;
                    }
                }
            }

            //(2)
            scanSysAlertDisplay.showOnServiceAttributeChange(state, stateReasons, errorLevel);
        }
    }
    
    public interface ScanJobContinueCB{
        void scanJobContinue();
    }
    
    public ScanRequestAttributeSet createScanJobAttributes() {
        ScanRequestAttributeSet requestAttributes;
        requestAttributes = new HashScanRequestAttributeSet();
        requestAttributes.add(AutoCorrectJobSetting.AUTO_CORRECT_ON);
        requestAttributes.add(new OriginalSize(MediaSizeName.ISO_A4));

        requestAttributes.add(ScanResolution.DPI_300);
        requestAttributes.add(JobMode.SCAN_AND_STORE_TEMPORARY);
        
        //requestAttributes.add(new WaitTimeForNextOriginal(0)); // only scan 1 page

        requestAttributes.add(ScanColor.COLOR_TEXT_PHOTO);
        requestAttributes.add(OriginalSide.ONE_SIDE);
        requestAttributes.add(OriginalPreview.OFF);

        // FileSetting
        FileSetting fileSetting = new FileSetting();
        fileSetting.setFileFormat(FileFormat.TIFF_JPEG);
        fileSetting.setMultiPageFormat(false);
        fileSetting.setCompressionMethod(CompressionMethod.JPEG);
        fileSetting.setCompressionLevel(CompressionLevel.LEVEL1);
        requestAttributes.add(fileSetting);
       

        // original size
       /* if (Util.getDefaultDest().equalsIgnoreCase("na")) {
            requestAttributes.add(new OriginalSize(MediaSizeName.INVOICE_LANDSCAPE));
        } else {
            requestAttributes.add(new OriginalSize(MediaSizeName.ISO_A5_LANDSCAPE));
        }*/

        // scan device
        //requestAttributes.add(ScanDevice.CONTACT_GLASS);   
        for(ScanRequestAttribute attr : requestAttributes.getCollection()){
        	LogC.d(TAG, attr.getName()+"*************"+attr.getValue());
            
        }
        return requestAttributes;
    }

	public ScanRequestAttributeSet getScanRequestAttributeSet() {
		return scanRequestAttributeSet;
	}

	public void setScanRequestAttributeSet(ScanRequestAttributeSet scanRequestAttributeSet) {
		this.scanRequestAttributeSet = scanRequestAttributeSet;
	}
    
    
}
