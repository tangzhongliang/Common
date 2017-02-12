/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobAttributeListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.job.JobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.job.PrintJobMessageDispatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 機器のプリントジョブを操作するクラスです
 * プリントサービスで利用可能状態が通知されてから利用が可能になります
 * This class operates print jobs of the device.
 * This is available after print service notifies availability state.
 */
public class PrintJob {

    private PrintJobMessageDispatcher mHandler = null;

    private final List<PrintJobAttributeListener> mAttributeListeners = new ArrayList<PrintJobAttributeListener>();
    private final List<PrintJobListener> mJobListeners = new ArrayList<PrintJobListener>();

    // ジョブIDはジョブが開始された段階で採番されます。
    // the job ID is assigned when the job starts
    private String mJobId = null;


    /**
     * プリントジョブを生成します
     * Creates a print job.
     */
    public PrintJob() {
        this.mHandler = new PrintJobMessageDispatcher();

        //非同期通信用のリスナー登録
        //sets the listener for the asynchronous communications
        this.mHandler.setPrintJobListener(new JobListener() {
            @Override
            public void onChangeJobStatus(PrintJobAttributeSet jobStatus) {
                changeJobStatus(jobStatus);
            }

            @Override
            public void setJobId(String jobId) {
                setCurrentJobId(jobId);
            }

            @Override
            public String getJobId() {
                return getCurrentJobId();
            }
        });
    }

    /**
     * プリントジョブの属性リスナーを設定します
     * Sets the attribute listener for the print job.
     *
     * @param listener プリントジョブの属性変更リスナー
     *                 Attribute listener for the print job
     */
    public void addPrintJobAttributeListener(PrintJobAttributeListener listener) {
        if (listener == null) throw new IllegalArgumentException("listener is null");

        synchronized (this.mAttributeListeners) {
            if (this.mAttributeListeners.contains(listener)) {
                return;
            }

            this.mAttributeListeners.add(listener);
        }
    }

    /**
     * プリントジョブの属性リスナーを解除します。
     * Removes the attribute listener.
     *
     * @param listener プリントジョブの属性変更リスナー
     *                 Attribute listener for the print job
     */
    public void removePrintJobAttributeListener(PrintJobAttributeListener listener) {
        if (listener == null) throw new IllegalArgumentException("listener is null");

        synchronized (this.mAttributeListeners) {
            if (!this.mAttributeListeners.contains(listener)) return;

            this.mAttributeListeners.remove(listener);
        }
    }

    /**
     * プリントジョブリスナーを設定します
     * Sets the print job listener
     *
     * @param listener プリントジョブリスナー
     *                 print job listener
     */
    public void addPrintJobListener(PrintJobListener listener) {
        if (listener == null) throw new IllegalArgumentException("listener is null");

        synchronized (this.mJobListeners) {
            if (this.mJobListeners.contains(listener)) return;
            this.mJobListeners.add(listener);
        }
    }

    /**
     * プリントジョブリスナーを解除します
     * Removes the print job listener
     *
     * @param listener プリントジョブリスナー
     *                 print job listener
     */
    public void removePrintJobListener(PrintJobListener listener) {
        if (listener == null) throw new IllegalArgumentException("listener is null");

        synchronized (this.mJobListeners) {
            if (!this.mJobListeners.contains(listener)) return;

            this.mJobListeners.remove(listener);
        }
    }

    /**
     * プリントジョブを開始します。
     * プリントジョブを機器が受け付けない場合は、戻り値がfalseとなります
     * Starts the print job
     * If the device cannot accept the print job, "false" is returned.
     *
     * @return ジョブ開始要求成功の可否
     * Indicates whether or not the request to start the print job has succeeded
     * @throws PrintException
     */
    public boolean print(PrintFile printFile, PrintRequestAttributeSet attributes, PrintUserCode userCode) throws PrintException {
        if (this.mHandler == null)
            throw new IllegalStateException("Cannot print after printing is completed.");
        if (printFile == null) throw new IllegalArgumentException("printFile is null");

        return mHandler.requestStartPrintJob(printFile, attributes, userCode);
    }

    public boolean print(PrintFile printFile, PrintRequestAttributeSet attributes) throws PrintException {
        return print(printFile, attributes, null);
    }

    /**
     * プリントジョブ要求属性を検証します。
     * Validates the print job request attribute.
     *
     * @return ジョブ開始要求成功の可否
     * Indicates whether or not the request to start the print job has succeeded
     * @throws PrintException
     */
    public boolean verify(PrintFile printFile, PrintRequestAttributeSet attributes, PrintUserCode userCode)
            throws PrintException {
        if (this.mHandler == null)
            throw new IllegalStateException("Cannot print after printing is completed.");
        return mHandler.verifyPrintJob(printFile, attributes, userCode);
    }

    public boolean verify(PrintFile printFile, PrintRequestAttributeSet attributes)
            throws PrintException {
        return verify(printFile, attributes, null);
    }

    /**
     * 指定されたジョブ属性の値を取得します.
     * Obtains the job attribute value of the specified job.
     *
     * @param category 取得対象とするPrintJobAttributeのカテゴリ. nullを指定することはできません。
     *                 The PrintJobAttribute category to obtain. Null cannot be specified.
     * @return 現在のジョブの値. 該当する属性値が存在しない場合はnull
     * The current job value. If there is no corresponding attribute value, null is returned.
     * @throws IllegalArgumentException categoryにnullを指定した場合
     *                                  When specifying null to category
     */
    public PrintJobAttribute getJobAttribute(Class<? extends PrintJobAttribute> category, PrintUserCode userCode) {
        if (category == null) throw new IllegalArgumentException("category is null");
        if (this.mHandler == null)
            throw new IllegalStateException("Cannot print after printing is completed.");

        PrintJobAttributeSet attributes = mHandler.requestJobStates(userCode);
        return attributes.get(category);
    }

    public PrintJobAttribute getJobAttribute(Class<? extends PrintJobAttribute> category) {
        return getJobAttribute(category, null);
    }

    /**
     * 本ジョブの現在のジョブ属性セットを取得します.
     * Obtains the current job attribute set for this job.
     *
     * @return 属性セット
     * attribute set
     */
    public PrintJobAttributeSet getJobAttributes(PrintUserCode userCode) {
        if (this.mHandler == null)
            throw new IllegalStateException("Cannot print after printing is completed.");
        return mHandler.requestJobStates(userCode);
    }

    public PrintJobAttributeSet getJobAttributes() {
        return getJobAttributes(null);
    }

    /**
     * プリントジョブを中止します
     * プリントジョブが一時停止もしくは、動作中の場合にスキャンジョブ中止を機器に要求します
     * 機器の状態によっては、プリントジョブ中止を受け付けない場合があります
     * Cancels the print job.
     * Requests the device to cancel the print job if the print job is being paused or being in process.
     * Print job cancel may not be accepted depending on the device state.
     *
     * @throws PrintException
     */
    public boolean cancelPrintJob(PrintUserCode userCode) throws PrintException {
        if (this.mHandler == null)
            throw new IllegalStateException("Cannot print after printing is completed.");
        return mHandler.requestCancelPrintJob(userCode);
    }

    public boolean cancelPrintJob() throws PrintException {
        return cancelPrintJob(null);
    }

    /**
     * ジョブの状態変化
     * Job state change
     */
    private void changeJobStatus(PrintJobAttributeSet attributeSet) {
        if (attributeSet.size() > 0) {
            notifyAttributeListeners(attributeSet);
        }

        //通知する属性に、ジョブ状態が存在した場合、ジョブリスナーへ通知する
        // notify job state if the job state is changed
        if (attributeSet.get(PrintJobState.class) != null) {
            notifyJobListeners(attributeSet);
        }

        //終了判定
        //ABORTED/CANCELED/COMPLETEDの場合は、ジョブ終了と判断する
        //The job ends if the job state is ABORTED, CANCELED, or COMPLETED.
        PrintJobState jobState = (PrintJobState) attributeSet.get(PrintJobState.class);

        if (PrintJobState.COMPLETED.equals(jobState) ||
                PrintJobState.CANCELED.equals(jobState) ||
                PrintJobState.ABORTED.equals(jobState)) {
            //終了時に不要なオブジェクトを解放する
            //relases unnecessary objects.
            this.mHandler.destroyJob();
            this.mAttributeListeners.clear();
            this.mJobListeners.clear();
            this.mHandler = null;
        }
    }

    private void setCurrentJobId(String jobId) {
        this.mJobId = jobId;
    }

    private String getCurrentJobId() {
        return this.mJobId;
    }

    private void notifyAttributeListeners(PrintJobAttributeSet attributes) {
        PrintJobAttributeListener[] listeners;
        synchronized (this.mAttributeListeners) {
            listeners = this.mAttributeListeners
                    .toArray(new PrintJobAttributeListener[this.mAttributeListeners.size()]);
        }

        if (listeners.length > 0) {
            PrintJobAttributeEvent event = new PrintJobAttributeEvent(this, attributes);
            for (PrintJobAttributeListener listener : listeners) {
                listener.updateAttributes(event);
            }
        }
    }

    private void notifyJobListeners(PrintJobAttributeSet attributes) {
        PrintJobListener[] listeners;
        synchronized (this.mJobListeners) {
            listeners = this.mJobListeners.toArray(new PrintJobListener[this.mJobListeners.size()]);
        }

        if (listeners.length > 0) {
            PrintJobEvent event = new PrintJobEvent(attributes);
            PrintJobState jobState = (PrintJobState) attributes.get(PrintJobState.class);
            if (jobState == null)
                return;
            for (PrintJobListener listener : listeners) {
                switch (jobState) {
                    case ABORTED:
                        listener.jobAborted(event);
                        break;
                    case CANCELED:
                        listener.jobCanceled(event);
                        break;
                    case COMPLETED:
                        listener.jobCompleted(event);
                        break;
                    case PENDING:
                        listener.jobPending(event);
                        break;
                    case PROCESSING:
                        listener.jobProcessing(event);
                        break;
                    case PROCESSING_STOPPED:
                        listener.jobProcessingStop(event);
                        break;
                    default:
                        throw new AssertionError("Unknown PrintJobState:" + jobState);
                }
            }
        }
    }

    public String getJobId() {
        return mJobId;
    }
}
