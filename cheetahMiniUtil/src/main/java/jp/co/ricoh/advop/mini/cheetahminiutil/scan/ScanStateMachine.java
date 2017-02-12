/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.scan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.EXE_RESULT;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.JobData;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.JobScan;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.BaseJob;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.JobScanMulti;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.ScanImage;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobStateReason;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.WaitTimeForNextOriginal;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.FilePathResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUIUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.HDDUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.ToastUtil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * スキャンサンプルアプリのステートマシン
 * [処理内容]
 * ジョブのハンドリングを行います。
 * また、起動時及びジョブ発行後の以下のダイアログの生成・表示・破棄はすべてこのステートマシンを通して実行します。
 * ・お待ち下さいダイアログ
 * ・スキャン中ダイアログ
 * ・次原稿待ちダイアログ
 * ・プレビュー画面
 * <p>
 * State machine of scan sample application.
 * Handles the Scan job.
 * Display/update/hide processes of the following dialog/screen are always executed by this statemachine.
 * - Please wait dialog
 * - Scanning dialog
 * - Dialog asking for next original(s)
 * - Preview screen
 */
public class ScanStateMachine {

    private static String TAG = "ScanStateMachine";

    /**
     * デフォルトのダイアログの横幅
     * Default dialog width
     */
    private static final int DEFAULT_DIALOG_WIDTH = 400;

    /**
     * メインアクティビティへの参照
     * Reference to Main activity
     */
//    private static Activity mActivity;

    /**
     * スキャンサンプルアプリケーション
     * Scan sample application object
     */
    private static BaseApplication mApplication;


    private static ScanManager scanManager;

    /**
     * UIスレッドのハンドラー
     * UI thread handler
     */
    private Handler mHandler;
    private String jobID;
    private Dialog mCountDownDialog;
    private Dialog mNoCountDownDialog;
//    private AfterPowerModeLock mAfterPowerModeLock;

    public ScanStateMachine(Handler handler) {
        scanManager = CHolder.instance().getScanManager();
        mApplication = CHolder.instance().getApplication();
        mHandler = handler;
//        mAfterPowerModeLock = null;
    }

    /**
     * メインアクティビティを登録します。
     * Registers the MainActivity.
     *
     * @param act MainActivity
     */
//    public void registActivity(Activity act) {
//        mActivity = act;
//    }

    /**
     * 状態遷移イベント
     * State transition event
     */
    public enum ScanEvent {

        /**
         * ジョブ状態がPENDING（ジョブ実行前）に遷移したことを示すイベント
         * The event to indicate that the job state has changed to PENDING (before the job starts)
         */
        CHANGE_JOB_STATE_PENDING,

        /**
         * ジョブ状態がPROCESSING（ジョブ実行中）に遷移したことを示すイベント
         * The event to indicate that the job state has changed to PROCESSING (the job is processing)
         */
        CHANGE_JOB_STATE_PROCESSING,

        /**
         * ジョブ状態がABORTED（システム側で読取中止）に遷移したことを示すイベント
         * The event to indicate that the job state has changed to ABORTED (the job is aborted by system)
         */
        CHANGE_JOB_STATE_ABORTED,

        /**
         * ジョブ状態がCANCELED（ユーザー操作で読取中止）に遷移したことを示すイベント
         * The event to indicate that the job state has changed to CANCELED (the job is canceled by user)
         */
        CHANGE_JOB_STATE_CANCELED,

        /**
         * 送信前プレビュー状態に遷移したことを示すイベント
         * The event to indicate that the job state has changed to the preview state before the data is sent
         */
        CHANGE_JOB_STATE_STOPPED_PREVIEW,

        /**
         * 一時停止中のカウントダウン状態に遷移したことを示すイベント
         * The event to indicate that the job state has changed to the countdown state for pausing
         */
        CHANGE_JOB_STATE_STOPPED_COUNTDOWN,

        /**
         * その他の一時停止状態に遷移したことを示すイベント
         * The event to indicate that the job state has changed to the pausing state for other reason
         */
        CHANGE_JOB_STATE_STOPPED_OTHER,

        /**
         * ジョブ状態がCOMPLETED（ジョブ正常終了）に遷移したことを示すイベント
         * The event to indicate that the job state has changed to COMPLETED (the job ended successfully)
         */
        CHANGE_JOB_STATE_COMPLETED,


        /**
         * ジョブ実行のリクエストイベント
         * Job start request event
         */
        REQUEST_JOB_START,

        /**
         * ジョブキャンセルのリクエストイベント
         * Job cancel request event
         */
        REQUEST_JOB_CANCEL,

        /**
         * ジョブ一時停止のリクエストイベント
         * Job stop request event
         */
        REQUEST_JOB_STOP,

        /**
         * ジョブ続行のリクエストイベント
         * Job continue request event
         */
        REQUEST_JOB_CONTINUE,

        /**
         * ジョブ終了のリクエストイベント
         * Job end request event
         */
        REQUEST_JOB_END,


        /**
         * スキャン実行中の情報変更イベント
         * Information change event for scan job in process
         */
        UPDATE_JOB_STATE_PROCESSING,

        /**
         * アクティビティ生成イベント
         * MainActivity created event
         */
        ACTIVITY_CREATED,

        /**
         * アクティビティ終了イベント
         * MainActivity destroyed event
         */
        ACTIVITY_DESTROYED,


        /**
         * 初期化完了イベント
         * Initialization completed event
         */
        ACTIVITY_BOOT_COMPLETED,

        /**
         * 初期化失敗イベント
         * Initialization failed event
         */
        ACTIVITY_BOOT_FAILED,

        /**
         * ジョブリセット完了イベント
         * Job reset completed event
         */
        REBOOT_COMPLETED,
    }

    /**
     * ステートマシンの初期状態
     * Statemachine initial state
     */
    private State mState = State.INITIAL;

    public State getState() {
        return mState;
    }

    public boolean isJobRunning() {
        if (mState == State.INITIAL
                || mState == State.IDLE
                || mState == State.JOB_ABORTED
                || mState == State.JOB_CANCELED
                || mState == State.JOB_COMPLETED
                || mState == State.WAITING_JOB_START) {
            return false;
        }
        return true;
    }

    /**
     * 状態定義
     * State definition
     */
    public enum State {
        /**
         * 初期状態
         * Initial state
         */
        INITIAL {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case ACTIVITY_CREATED:
                        return INITIAL;
                    case ACTIVITY_BOOT_COMPLETED:
                        return IDLE;
                    case ACTIVITY_BOOT_FAILED:
                        return INITIAL;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }
        },
        /**
         * ジョブ生成後のジョブ開始前状態
         * The state before the job is started after the job has been created
         */
        IDLE {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_PENDING:
                        return JOB_PENDING;
                    case REQUEST_JOB_START:
                        return WAITING_JOB_START;
                    //add
                    case ACTIVITY_CREATED:
                        return INITIAL;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }
        },
        /**
         * ジョブ開始待ち状態
         * Job start waiting state
         */
        WAITING_JOB_START {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;

                    case CHANGE_JOB_STATE_PENDING:
                        return JOB_PENDING;
                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;

                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                sm.new StartScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        },
        /**
         * ジョブ開始後の実行前状態
         * The state before the job is started after the job has been started
         */
        JOB_PENDING {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;

                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }
        },
        /**
         * ジョブ実行中
         * Job processing
         */
        JOB_PROCESSING {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;
                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;
                    case CHANGE_JOB_STATE_STOPPED_OTHER:
                        return JOB_STOPPED_OTHER;

                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case REQUEST_JOB_STOP:
                        return WAITING_JOB_STOP;
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;
                    case UPDATE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    //add
                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
//                actCloseJobStoppedDialog(sm, prm);
//                actUpdateScanningDialog(sm, prm);
            }
        },
        /**
         * システム側によりジョブキャンセル
         * Job cancelling by system
         */
        JOB_ABORTED {
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REBOOT_COMPLETED:
                        return IDLE;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
//                actCloseScanningDialog(sm,prm);
//                actCloseJobStoppedDialog(sm, prm);

                // show toast message with aborted reason
                String message = "";
                if (prm instanceof ScanJobAttributeSet) {
                    ScanJobAttributeSet attributes = (ScanJobAttributeSet) prm;
                    ScanJobStateReasons reasons = (ScanJobStateReasons) attributes.get(ScanJobStateReasons.class);
                    if (reasons != null) {
                        StringBuilder sb = new StringBuilder();
                        //sb.append(message);
                        //sb.append(System.getProperty("line.separator"));
                        //sb.append(reasons.getReasons().toString());
                        Set<ScanJobStateReason> reasonSet = reasons.getReasons();
                        for (ScanJobStateReason reason : reasonSet) {
                            //sb.append(reason.toString()).append("\n");
                            LogC.e("scan job error:" + reason);
                            if (Const.SCAN_ERROR_MAP.get(reason) != null) {
                                sb.append(CHolder.instance().getActivity().getString(Const.SCAN_ERROR_MAP.get(reason)));
                            }
                        }
//                        LogC.e("scan job error:" + reasons.getReasons());
//                        if(Const.SCAN_ERROR_MAP.get(reasons.getReasons()) != null) {
//                            sb.append(CHolder.instance().getActivity().getString(Const.SCAN_ERROR_MAP.get(reasons.getReasons())));
//                        } 
                        message = sb.toString();
                    }
                }
                if (CUtil.isStringEmpty(message)) {
                    message = CHolder.instance().getActivity().getString(R.string.scan_fail);//"job aborted.";
                }
                actShowToastMessage(sm, message);
                actInitJobSetting(sm, prm);

                BaseJob.forceComplete(EXE_RESULT.ERR_SCAN);
            }
        },
        /**
         * ジョブキャンセル中
         * Job cancelling
         */
        JOB_CANCELED {
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_PENDING:
                        return JOB_PENDING;
                    case REBOOT_COMPLETED:
                        return IDLE;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
//                actCloseScanningDialog(sm,prm);
//                actCloseJobStoppedDialog(sm, prm);
//                actClosePreview(sm,prm);
                actInitJobSetting(sm, prm);

                BaseJob.forceComplete(EXE_RESULT.ERR_SCAN);
            }
        },
        /**
         * ジョブ一時停止中(次原稿待ちダイアログ表示)
         * Job pausing (display "set next original" dialog)
         */
        JOB_STOPPED_COUNTDOWN {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REQUEST_JOB_CONTINUE:
                        return WAITING_JOB_CONTINUE;
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;
                    case REQUEST_JOB_END:
                        return WAITING_JOB_END;

                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    //add
                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;

                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
//                actCloseJobStoppedDialog(sm, prm);

                if (CHolder.instance().getJobData().getCurrentJob().isEnableWait()) {
                    sm.actWaitForNextOriginal(sm, prm);
                } else {
                    // only scan 1 page
                    sm.procScanEvent(ScanEvent.REQUEST_JOB_END);
                }
            }
        },
        /**
         * ジョブ一時停止中（プレビュー表示）
         * Job pausing (display preview)
         */
        JOB_STOPPED_PREVIEW {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REQUEST_JOB_END:
                        return WAITING_JOB_END;
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;

                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    //add
                    case REQUEST_JOB_CONTINUE:
                        return WAITING_JOB_CONTINUE;
                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;
                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
//                actCloseJobStoppedDialog(sm, prm);
                BaseJob currentJob = CHolder.instance().getJobData().getCurrentJob();
                currentJob.setCount(currentJob.getCount() + 1);
            }
        },
        /**
         * その他のジョブ一時停止
         * Job pausing (other)
         */
        JOB_STOPPED_OTHER {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REQUEST_JOB_CONTINUE:
                        return WAITING_JOB_CONTINUE;
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;
                    case REQUEST_JOB_END:
                        return WAITING_JOB_END;

                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;
                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;

                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                //actCloseWaitForNextOriginal(sm, prm);
                actShowToastMessage(sm, prm);
                sm.new CancelScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                actShowJobStoppedDialog(sm, prm);
            }
        },
        /**
         * ジョブ一時停止待ち
         * Job waiting to be stopped
         */
        WAITING_JOB_STOP {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REQUEST_JOB_CANCEL:
                        return WAITING_JOB_CANCEL;

                    case UPDATE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;

                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;
                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;
                    case CHANGE_JOB_STATE_STOPPED_OTHER:
                        return JOB_STOPPED_OTHER;

                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;
                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                sm.new StopScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        },
        /**
         * ジョブ再開待ち
         * Job waiting to be resumed
         */
        WAITING_JOB_CONTINUE {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;

                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case ACTIVITY_DESTROYED:
                        return WAITING_JOB_CANCEL;

                    //add
                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;
                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                sm.new ContinueScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        },
        /**
         * ジョブキャンセル待ち
         * Job waiting to be cancelled
         */
        WAITING_JOB_CANCEL {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;

                    //add
                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN:
                        return JOB_STOPPED_COUNTDOWN;
                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                sm.new CancelScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        },
        /**
         * ジョブ終了待ち
         * Job waiting to be finished
         */
        WAITING_JOB_END {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case CHANGE_JOB_STATE_COMPLETED:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_STOPPED_PREVIEW:
                        return JOB_STOPPED_PREVIEW;

                    case CHANGE_JOB_STATE_ABORTED:
                        return JOB_ABORTED;
                    case CHANGE_JOB_STATE_CANCELED:
                        return JOB_CANCELED;

                    //add
                    case CHANGE_JOB_STATE_PENDING:
                        return JOB_COMPLETED;
                    case CHANGE_JOB_STATE_PROCESSING:
                        return JOB_PROCESSING;
//                    case CHANGE_JOB_STATE_STOPPED_COUNTDOWN :
//                        return JOB_STOPPED_COUNTDOWN;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                //sm.new EndScanJobTask().execute();
                sm.new EndScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        },

        /**
         * ジョブ正常終了
         * Job completed successfully
         */
        JOB_COMPLETED {
            @Override
            public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
                switch (event) {
                    case REBOOT_COMPLETED:
                        return IDLE;
                    default:
                        return super.getNextState(sm, event, prm);
                }
            }

            @Override
            public void entry(final ScanStateMachine sm, final Object prm) {
                super.entry(sm, prm);
                // get temp file path
                sm.jobID = scanManager.getScanJob().getCurrentJobId();
                //sm.new GetFilePathTask(sm, prm).execute(scanManager.getScanJob().getCurrentJobId());
                sm.new GetFilePathTask(sm, prm).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, scanManager.getScanJob().getCurrentJobId());
            }
        },;


        /**
         * 次の状態を取得します。
         * 各状態がオーバーライドします。
         * Obtains the next state.
         * Each state should override this method.
         *
         * @param sm
         * @param event
         * @param prm   Object for additional information
         * @return
         */
        public State getNextState(final ScanStateMachine sm, final ScanEvent event, final Object prm) {
            switch (event) {
                default:
                    return null;
            }
        }

        /**
         * 状態に入るときに呼ばれるメソッドです。
         * 各状態が必要に応じてオーバーライドします。
         * This method is called when entering a state.
         * Each state should override this method if necessary.
         *
         * @param sm
         * @param prm Object for additional information
         */
        public void entry(final ScanStateMachine sm, final Object prm) {
        }

        /**
         * 状態から抜けるときに呼ばれるメソッドです。
         * 各状態が必要に応じてオーバーライドします。
         * This method is called when exiting a state.
         * Each state should override this method if necessary.
         *
         * @param sm
         * @param prm Object for additional information
         */
        public void exit(final ScanStateMachine sm, final Object prm) {
        }


        // +++++++++++++++++++++++++++++++++++++++++
        // アクション関数
        // Action method
        // +++++++++++++++++++++++++++++++++++++++++

        /**
         * Toastメッセージ表示
         * Displays toast message
         */
        protected void actShowToastMessage(final ScanStateMachine sm, final Object prm) {
            sm.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast(CHolder.instance().getActivity(), (String) prm, ToastUtil.ToastLengthType.TYPE_LONG);
                }
            });
        }

        /**
         * スキャンジョブの初期化
         * Initializes scan job
         */
        protected void actInitJobSetting(final ScanStateMachine sm, final Object prm) {
            sm.mHandler.post(new Runnable() {
                @Override
                public void run() {

                    scanManager.initJobSetting();
                    sm.procScanEvent(ScanEvent.REBOOT_COMPLETED);
                }
            });
        }
    }


    /**
     * 状態遷移を行います。
     * Changes states.
     *
     * @param event
     */
    public void procScanEvent(final ScanEvent event) {
        procScanEvent(event, null);
    }

    /**
     * 状態遷移を行います。
     * Changes states.
     *
     * @param event
     * @param prm   Object for additional information
     */
    public void procScanEvent(final ScanEvent event, final Object prm) {
        LogC.i(TAG, ">evtp :" + event);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                State newState = mState.getNextState(ScanStateMachine.this, event, prm);
                if (newState != null) {
                    LogC.i(TAG, "#evtp :" + event + " state:" + mState + " > " + newState);
                    mState.exit(ScanStateMachine.this, prm);
                    mState = newState;
                    mState.entry(ScanStateMachine.this, prm);
                }
            }
        });
    }

    /*=============================================================
     * ステートマシンから呼ばれるpublicメソッド
     * public methos called by statemachine
     *=============================================================*/

   

   /*=============================================================
    * スキャンジョブ操作の非同期タスク
    * The asynchronous task to start the scan job.
    *=============================================================*/

    /**
     * ScanResponseExceptionのエラー情報を文字列化します。
     * フォーマットは以下の通りです。
     * Creates the string of the ScanResponseException error information.
     * The format is as below.
     * <p>
     * base[separator]
     * [separator]
     * message_id: message[separator]
     * message_id: message[separator]
     * message_id: message
     *
     * @param e    文字列化対象のScanResponseException
     *             ScanResponseException to be converted as a string
     * @param base メッセージ先頭文字列
     *             Starting string of the message
     * @return メッセージ文字列
     * Message string
     */
    private String makeJobErrorResponceMessage(ScanResponseException e, String base) {
        if (base == null) {
            base = "";
        }
        StringBuilder sb = new StringBuilder(base);
        if (e.hasErrors()) {
            Map<String, String> errors = e.getErrors();
            if (errors.size() > 0) {
                //String separator = System.getProperty("line.separator");
                //sb.append(separator);
                for (Map.Entry<String, String> entry : errors.entrySet()) {
                    //sb.append(separator);
                    // message_id
                    //sb.append(entry.getKey());
                    // message (if exists)
                    //String message = entry.getValue();
                    if (Const.JOB_ERROR_MAP.get(entry.getKey()) != null) {
                        LogC.e("Scan Job Error Response id:" + entry.getKey());
                        sb.append(CHolder.instance().getActivity().getString(Const.JOB_ERROR_MAP.get(entry.getKey())));
                        break;
                    }
                    //LogC.d("message" + message);
//                   if ((message != null) && (message.length() > 0)) {
//                       sb.append(": ");
//                       sb.append(message);
//                       LogC.d("show message" + message);
//                       
//                   } 

                }
            }
        }
        if (CUtil.isStringEmpty(sb.toString())) {
            LogC.e("Scan Job error!");
            sb.append(CHolder.instance().getActivity().getString(R.string.scan_fail));
        }
        return sb.toString();
    }

    /**
     * スキャンジョブを実行開始するための非同期タスクです。
     * The asynchronous task to start the scan job.
     */
    private class StartScanJobTask extends AsyncTask<Void, Void, Boolean> {

        private String message = null;

        @Override
        protected Boolean doInBackground(Void... param) {

            // Sets scan attributes.
            ScanRequestAttributeSet requestAttributes = scanManager.getScanRequestAttributeSet();
            if (requestAttributes == null) {
                requestAttributes = scanManager.createScanJobAttributes();
            }

            requestAttributes.add(new WaitTimeForNextOriginal(CHolder.instance().getApplication().getTimeOfWaitingNextOriginal()));

            // start scan
            boolean result = false;
            try {
                LogC.d(TAG, "scan requesting...");
                result = scanManager.getScanJob().scan(requestAttributes, "");
                LogC.d(TAG, "scan requested. result=" + result);
            } catch (ScanException e) {
                message = "";
                if (e instanceof ScanResponseException) {
                    message = makeJobErrorResponceMessage((ScanResponseException) e, message);
                } else if (CUtil.isStringEmpty(message)) {
                    message = CHolder.instance().getActivity().getResources().getString(R.string.job_start_fail);// + e.getMessage();
                }
                LogC.w(TAG, message, e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (message != null) {
                LogC.d(message);
                ToastUtil.showToast(CHolder.instance().getActivity(), message, ToastUtil.ToastLengthType.TYPE_LONG);
            }

            if (!result) {
                ScanStateMachine.this.procScanEvent(ScanEvent.CHANGE_JOB_STATE_CANCELED);
            }
        }

    }

    /**
     * スキャンジョブをキャンセルするための非同期タスクです。
     * The asynchronous task to cancel the scan job.
     */
    private class CancelScanJobTask extends AsyncTask<Void, Void, Boolean> {
        String message = "";

        @Override
        protected Boolean doInBackground(Void... param) {
            boolean result = false;
            if (scanManager.getScanJob() == null) {
                return true;
            }
            try {
                LogC.d(TAG, "scan cancel requesting...");
                result = scanManager.getScanJob().cancelScanJob();
                LogC.d(TAG, "scan cancel requested. result=" + result);
            } catch (ScanException e) {
                if (e instanceof ScanResponseException) {
                    message = makeJobErrorResponceMessage((ScanResponseException) e, message);
                } else if (CUtil.isStringEmpty(message)) {
                    message = CHolder.instance().getActivity().getResources().getString(R.string.job_cancel_fail);//+ e.getMessage();

                }
                LogC.w(TAG, message, e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                ToastUtil.showToast(CHolder.instance().getActivity(), message, ToastUtil.ToastLengthType.TYPE_LONG);
                ScanStateMachine.this.procScanEvent(ScanEvent.CHANGE_JOB_STATE_CANCELED);
            }
        }
    }

    /**
     * スキャンジョブを一時停止するための非同期タスクです。
     * The asynchronous task to stop the scan job.
     */
    private class StopScanJobTask extends AsyncTask<Void, Void, Boolean> {

        private String message = null;

        @Override
        protected Boolean doInBackground(Void... param) {
            boolean result = false;
            try {
                LogC.d(TAG, "scan stop requesting...");
                result = scanManager.getScanJob().stopScanJob();
                LogC.d(TAG, "scan stop requested. result=" + result);
            } catch (ScanException e) {
                message = "";
                if (e instanceof ScanResponseException) {
                    message = makeJobErrorResponceMessage((ScanResponseException) e, message);
                } else if (CUtil.isStringEmpty(message)) {
                    message = CHolder.instance().getActivity().getResources().getString(R.string.job_stop_fail);//+ e.getMessage();
                }
                LogC.w(TAG, message, e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (message != null) {
                LogC.d(message);
                ToastUtil.showToast(CHolder.instance().getActivity(), message, ToastUtil.ToastLengthType.TYPE_LONG);
            }
        }
    }

    /**
     * スキャンジョブを再開するための非同期タスクです。
     * The asynchronous task to resume the scan job.
     */
    private class ContinueScanJobTask extends AsyncTask<Void, Void, Boolean> {

        private String message = null;

        @Override
        protected Boolean doInBackground(Void... param) {
            boolean result = false;
            try {
                LogC.d(TAG, "scan continue requesting...");
                result = scanManager.getScanJob().continueScanJob(null);
                LogC.d(TAG, "scan continue requested. result=" + result);
            } catch (ScanException e) {
                message = "";
                if (e instanceof ScanResponseException) {
                    message = makeJobErrorResponceMessage((ScanResponseException) e, message);
                } else if (CUtil.isStringEmpty(message)) {
                    message = CHolder.instance().getActivity().getResources().getString(R.string.job_continue_fail);//+ e.getMessage();
                }
                LogC.w(TAG, message, e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (message != null) {
                LogC.d(message);
                ToastUtil.showToast(CHolder.instance().getActivity(), message, ToastUtil.ToastLengthType.TYPE_LONG);
            }
            if (!result) {
                //new CancelScanJobTask().execute();
                new CancelScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    /**
     * スキャンジョブを終了するための非同期タスク
     * The asynchronous task to end the scan job.
     */
    private class EndScanJobTask extends AsyncTask<Void, Void, Boolean> {

        private String message = null;

        @Override
        protected Boolean doInBackground(Void... param) {
            boolean result = false;
            if (scanManager.getScanJob() == null) {
                return true;
            }
            try {
                LogC.d(TAG, "scan end requesting...");
                result = scanManager.getScanJob().endScanJob();
                LogC.d(TAG, "scan end requested. result=" + result);
            } catch (ScanException e) {
                message = "";
                if (e instanceof ScanResponseException) {
                    message = makeJobErrorResponceMessage((ScanResponseException) e, message);
                } else if (CUtil.isStringEmpty(message)) {
                    message = CHolder.instance().getActivity().getResources().getString(R.string.job_end_fail);// + e.getMessage();
                }
                LogC.w(TAG, message, e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (message != null) {
                LogC.d(message);
                ToastUtil.showToast(CHolder.instance().getActivity(), message, ToastUtil.ToastLengthType.TYPE_LONG);
            }
            if (!result) {
                //new CancelScanJobTask().execute();
                new CancelScanJobTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    private class GetFilePathTask extends AsyncTask<String, Void, List<FilePathResponseBody>> {
        private ScanStateMachine sm;
        private Object prm;

        public GetFilePathTask(ScanStateMachine sm, Object prm) {
            this.sm = sm;
            this.prm = prm;
        }

        @Override
        protected List<FilePathResponseBody> doInBackground(String... param) {
            LogC.d(TAG, "GetFilePathTask requesting...");
            List<FilePathResponseBody> ret = new ArrayList<FilePathResponseBody>();

            // get path
            ScanImage img = new ScanImage(param[0]);

            int count = scanManager.getScannedPages();
            BaseJob job = CHolder.instance().getJobData().getCurrentJob();
            if (job instanceof JobScan) {
                count = 1;
            }

            for (int i = 1; i <= count; i++) {
                FilePathResponseBody body = img.getImageFilePath(i);
                if (body == null) {
                    LogC.d(TAG, "GetFilePathTask failed.");
                    return null;
                }
                ret.add(body);
            }

            // copy to local folder
            for (int i = 1; i <= count; i++) {
                FilePathResponseBody body = ret.get(i - 1);

                String filePathScanner = body.getFilePath();
                if (LogC.runInEMU) {
                    // emu bug: return windows path
                    filePathScanner = "/mnt/hdd/scan.jpg";
                    body.setFilePath(filePathScanner);
                    ret.set((i - 1), body);
                    continue;
                }
                File file = new File(filePathScanner);
                final String dstPath = Const.PATH_TMP_FOLDER + file.getName();
                LogC.d("src path:" + filePathScanner + " dst path:" + dstPath);

                boolean result = HDDUtil.copyFile(filePathScanner, dstPath);
                if (!result) {
                    LogC.e("copy file fail!");
                    return null;
                }
                body.setFilePath(dstPath);
                ret.set((i - 1), body);
            }

            CHolder.instance().getScanManager().getStateMachine()
                    .delTempScanedFile();
            return ret;

        }

        @Override
        protected void onPostExecute(List<FilePathResponseBody> result) {
            mState.actInitJobSetting(sm, prm);

            JobData jobData = CHolder.instance().getJobData();
            BaseJob job = jobData.getCurrentJob();
            if (job == null) {
                return;
            }

            if (result == null) {
                BaseJob.forceComplete(EXE_RESULT.ERR_GET_SCANNED_FILE);
                return;
            }
            if (job instanceof JobScan) {
                LogC.d("set scan image path:" + result);
                ((JobScan) job).GetPathFinished(result.get(0).getFilePath(), result.get(0).getRotate());
            } else if (job instanceof JobScanMulti) {
                LogC.d("set scan image path:" + result);
                ((JobScanMulti) job).GetPathFinished(result);
            } else {
                BaseJob.forceComplete(EXE_RESULT.SUCCESSED);
            }

        }
    }

    // must call async
    public boolean delTempScanedFile() {
        if (CUtil.isStringEmpty(jobID)) {
            return false;
        }
        ScanImage img = new ScanImage(jobID);
        // MFPから削除
        return img.deleteImage();
    }

    /**
     * カウントダウンあり次原稿待ちダイアログを表示します。
     * Displays "set next original" dialog (with count down)
     */
    public void showCountDownDialog() {
        if (mCountDownDialog == null || mCountDownDialog.isShowing() == false) {
            mCountDownDialog = createCountDownDialog();
            mCountDownDialog.show();
        }
    }

    private Dialog createCountDownDialog() {
        final Dialog countDownDialog = new Dialog(CHolder.instance().getActivity());
        countDownDialog.setTitle("Scanning");
        countDownDialog.setContentView(R.layout.dlg_count_down);
        countDownDialog.show();

        final CountDownTimer timer = new CountDownTimer((mApplication.getTimeOfWaitingNextOriginal() + 1) * 1000, 1000) {
            @Override
            public void onTick(long millis) {
                ((TextView) countDownDialog.findViewById(R.id.txt_count_down))
                        .setText(String.format(CHolder.instance().getActivity().getString(R.string.txid_scan_t_count_down_text),
                                millis / 1000));
            }

            @Override
            public void onFinish() {
                countDownDialog.dismiss();
                procScanEvent(ScanEvent.REQUEST_JOB_END);
            }
        }.start();

        countDownDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                timer.cancel();
            }
        });

        // continue button
        Button start = (Button) countDownDialog.findViewById(R.id.btn_count_down_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                countDownDialog.dismiss();
                procScanEvent(ScanEvent.REQUEST_JOB_CONTINUE);
            }
        });

        // finish button
        Button finish = (Button) countDownDialog.findViewById(R.id.btn_count_down_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                countDownDialog.dismiss();
                procScanEvent(ScanEvent.REQUEST_JOB_END);
            }
        });

        return countDownDialog;
    }

    /**
     * 次原稿待ちダイアログの表示
     * Displays "set next original" dialog
     */
    protected void actWaitForNextOriginal(final ScanStateMachine sm, final Object prm) {
        if (mApplication.getTimeOfWaitingNextOriginal() == 0)
            sm.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    sm.showNoCountDownDialog();
                }
            });
        else
            sm.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    sm.showCountDownDialog();
                }
            });
    }

    public void showNoCountDownDialog() {
        if (mNoCountDownDialog == null || mNoCountDownDialog.isShowing() == false) {
            mNoCountDownDialog = createNoCountDownDialog();
            mNoCountDownDialog.show();
        }
    }

    /**
     * 一時停止イベント中のカウントダウンなしダイアログを生成します。
     * Creates "set next original" dialog (without countdown) for the pause event.
     */
    private Dialog createNoCountDownDialog() {
        final Dialog noCountDownDialog = new Dialog(CHolder.instance().getActivity());
        noCountDownDialog.setTitle("Scanning");
        noCountDownDialog.setContentView(R.layout.dlg_no_count_down);
        noCountDownDialog.show();

        // continue button
        Button start = (Button) noCountDownDialog.findViewById(R.id.btn_count_down_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noCountDownDialog.dismiss();
                procScanEvent(ScanEvent.REQUEST_JOB_CONTINUE);
            }
        });

        // finish button
        Button finish = (Button) noCountDownDialog.findViewById(R.id.btn_count_down_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noCountDownDialog.dismiss();
                procScanEvent(ScanEvent.REQUEST_JOB_END);
            }
        });

        return noCountDownDialog;
    }
}
