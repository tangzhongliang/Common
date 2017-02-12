/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.HashScanRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanJobState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobAttributeListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanJobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.job.JobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.job.ScanJobMessageDispatcher;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import java.util.ArrayList;
import java.util.List;

/**
 * 機器のスキャンジョブを操作するクラスです
 * スキャンサービスで利用可能状態が通知されてから利用が可能です。
 * This class operates scan jobs of the device.
 * This is available after scan service notifies availability state.
 */
public final class ScanJob {

	private ScanJobMessageDispatcher mHandler = null;

	private final List<ScanJobAttributeListener> mAttributeListeners = new ArrayList<ScanJobAttributeListener>();
	private final List<ScanJobListener> mJobListeners = new ArrayList<ScanJobListener>();

    // ジョブIDはジョブが開始された段階で採番されます。
	// the job id is assigned when the job starts
    private String mJobId = null;


	/**
	 * スキャンジョブを生成します
	 * 生成しても機器ではジョブを管理していない状態です。
	 * ジョブの開始をして始めて機器内部でジョブを認識するため、生成のみでは
	 * ジョブログ等の記録は残りません。
	 * Creates a scan job.
	 * The job is created but the device does not manage the job yet.
	 * Since the device recognizes the job only after the job is started,
	 * job creation itself does not remain as a log.
	 */
	public ScanJob(){
		this.mHandler = new ScanJobMessageDispatcher();

        // 非同期通信用の内部リスナーを登録
		// sets the listener for the asynchronous communications
		this.mHandler.setScanJobListener(new JobListener() {
            @Override
            public void onChangeJobStatus(ScanJobAttributeSet jobStatus) {
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
	 * スキャンジョブの属性リスナーを設定します
     * Sets the attribute listener for the scan job.
     *
	 * @param listener スキャンジョブの属性変更リスナー
	 *                 Attribute listener for the scan job
	 */
	public void addScanJobAttributeListener(ScanJobAttributeListener listener){
		if( listener == null ) throw new IllegalArgumentException("listener is null");

        synchronized (this.mAttributeListeners) {
            if(this.mAttributeListeners.contains(listener)){
                return;
            }

            this.mAttributeListeners.add(listener);
        }
	}

	/**
	 * スキャンジョブの属性リスナーを除去します
	 * Removes the attribute listener
	 *
     * @param listener スキャンジョブの属性変更リスナー
     *                 Attribute listener for the scan job
	 */
	public void removeScanJobAttributeListener(ScanJobAttributeListener listener){
        if( listener == null ) throw new IllegalArgumentException("listener is null");

        synchronized (this.mAttributeListeners) {
            if(!this.mAttributeListeners.contains(listener)){
                return;
            }

            this.mAttributeListeners.remove(listener);
        }
	}

	/**
	 * スキャンジョブリスナーを設定します
	 * Sets the scan job listener
	 *
	 * @param listener スキャンジョブリスナー
	 *                 scan job listener
	 */
	public void addScanJobListener(ScanJobListener listener){
		if(listener == null) throw new IllegalArgumentException("parameter is null");

        synchronized (this.mJobListeners) {
            if(this.mJobListeners.contains(listener)) {
                return;
            }
            this.mJobListeners.add(listener);
        }
	}

	/**
	 * スキャンジョブリスナーを除去します
	 * Removes the scan job listener
	 *
	 * @param listener スキャンジョブリスナー
	 *                 scan job listener
	 */
	public void removeScanJobListener(ScanJobListener listener){
		if( listener == null ) throw new IllegalArgumentException("parameter is null");

        synchronized (this.mJobListeners) {
            if(!this.mJobListeners.contains(listener)){
                return;
            }
            this.mJobListeners.remove(listener);
        }
	}

	/**
	 * スキャンジョブを開始します
	 * スキャンジョブを機器が受け付けない場合は、戻り値がfalseとなります
	 * Starts the scan job
	 * If the device cannot accept the scan job, "false" is returned.
	 *
	 * @return ジョブ開始要求成功の可否
	 *         Indicates whether or not the request to start the scan job has succeeded
     * @throws  ScanException
	 */
	public boolean  scan(ScanRequestAttributeSet attributes, String userCode) throws ScanException {
        if( this.mHandler == null ) throw new IllegalStateException("Cannot scan after scanning is completed.");

		return mHandler.requestStartScanJob(attributes, userCode);
	}

    /**
     * スキャンジョブ要求属性を検証します
     * スキャンジョブを機器が受け付けない場合は、ScanExceptionが発生します。
     * また、組み合わせの結果、実行不可能と判断された場合も、ScanAttributeExceptionが発生します。
     * Validates the scan job request attribute.
     * If the device does not accept the scan job, ScanException is thrown.
     * If the job is judged as "impossible to start" due to attribute combination, ScanAttributeException is thrown.
     *
     * @return ジョブ開始要求成功の可否
     *         Indicates whether or not the request to start the scan job has succeeded
     * @throws  ScanException
     */
    public boolean  verify(ScanRequestAttributeSet attributes) throws ScanException {
        return mHandler.verifyScanJob(attributes);
    }


    /**
     * 指定されたジョブ属性の値を取得します.
     * Obtains the job attribute value of the specified job.
     *
     * @param category 取得対象とするScanJobAttributeのカテゴリ. nullを指定することはできません。
     *                 The ScanJobAttribute category to obtain. Null cannot be specified.
     * @return 現在のジョブの値. 該当する属性値が存在しない場合はnull
     *         The current job value. If there is no corresponding attribute value, null is returned.
     * @throws IllegalArgumentException categoryにnullを指定した場合
     *         When specifying null to category
     */
    public ScanJobAttribute getJobAttribute(Class<? extends ScanJobAttribute> category) {
    	if (category == null) {
    		throw new IllegalArgumentException("category must not be null.");
    	}

        ScanJobAttributeSet attributes = mHandler.requestJobStates();
        return attributes.get(category);
    }

    /**
     * 本ジョブの現在のジョブ属性セットを取得します.
     * Obtains the current job attribute set for this job.
     * @return 属性セット
     *         attribute set
     */
    public ScanJobAttributeSet getJobAttributes() {
        return mHandler.requestJobStates();
    }

	/**
	 * スキャンジョブを再開します
	 * スキャンジョブが一時停止の場合にスキャンジョブ再開を機器に要求します
	 * スキャンジョブの状態によっては、スキャンジョブ再開ができない場合があります
	 * Starts the scan job.
	 * Requests the device to resume the scan job if the scan job is being paused.
	 * The scan job may not be able to resume depending on the scan job state.
	 *
     *  @param attributeSet
     *  @throws  ScanException
	 */
	public boolean continueScanJob(HashScanRequestAttributeSet attributeSet) throws ScanException {
		if(null == this.mHandler){
			LogC.i("handler is null");
			return true;
		}
		return this.mHandler.requestContinueScanJob(attributeSet);
	}

    /**
     * スキャンジョブを停止します
     * スキャンジョブが読み取り中の場合に読み取り一時停止を機器に要求します
     * スキャンジョブの状態によっては、スキャンジョブ停止ができない場合があります
     * Stops the scan job.
     * Requests the machine to stop scanning while a scan job is in scanning process.
     * The scan job may not be able to stop depending on the scan job state.
     *
     *  @throws  ScanException
     *  @since   SmartSDK V1.01
     */
    public boolean stopScanJob() throws ScanException {
		if(null == this.mHandler){
			LogC.i("handler is null");
			return true;
		}
        return this.mHandler.requestStopScanJob();
    }

    /**
     * スキャンジョブを終了します
     * スキャンジョブが一時停止の場合にスキャンジョブ終了を機器に要求します
     * スキャンジョブの状態によっては、スキャンジョブ終了ができない場合があります
     * Ends the scan job.
     * Requests the device to end the scan job if the scan job is being paused.
     * The scan job may not be able to finish depending on the scan job state.
     *
     *  @throws  ScanException
     */
    public boolean endScanJob() throws ScanException {
		if(null == this.mHandler){
			LogC.i("handler is null");
			return true;
		}
        return this.mHandler.requestEndScanJob();
    }

	/**
	 * スキャンジョブを中止します
	 * スキャンジョブが一時停止もしくは、動作中の場合にスキャンジョブ中止を機器に要求します
	 * 機器の状態によっては、スキャンジョブ中止を受け付けない場合があります
	 * Cancels the scan job.
	 * Requests the device to cancel the scan job if the scan job is being paused or being in process.
	 * Scan job cancel may not be accepted depending on the device state.
     *  @throws  ScanException
	 */
	public boolean cancelScanJob() throws ScanException {
		if(null == this.mHandler){
			LogC.i("handler is null");
			return true;
		}
        return this.mHandler.requestCancelScanJob();
	}

	/**
	 * ジョブの状態変化
	 * Job state change
	 */
    private void changeJobStatus(ScanJobAttributeSet jobStatus) {
        if(jobStatus.size() > 0) {
            notifyAttributeListeners(jobStatus);
        }

        //ジョブ状態(全体)が変化した場合、ジョブリスナーへ通知する
        // notify job state if the job state is changed
        if (jobStatus.get(ScanJobState.class) != null) {
        	notifyJobListeners(jobStatus);
        }

        // 終了判定処理
        // ABORTED/CANCELED/COMPLETEDの場合は、ジョブが終了したと判断される
        // Ends the job if the job state is ABORTED, CANCELED, or COMPLETED.
        ScanJobState jobState = (ScanJobState)jobStatus.get(ScanJobState.class);
        if(ScanJobState.COMPLETED.equals(jobState) ||
        		ScanJobState.CANCELED.equals(jobState) ||
        		ScanJobState.ABORTED.equals(jobState)) {

            //終了時に不要なオブジェクトを解放しておく
            //画像取得を考慮してジョブIDを吸い出しておく
            //relases unnecessary objects.
            //the job id is used to obtain the scanned images.
            this.mJobId = mHandler.getJobId();
            this.mHandler.destroyJob();
            this.mAttributeListeners.clear();
            this.mJobListeners.clear();
            this.mHandler = null;
        }
	}

    private synchronized void setCurrentJobId(String jobId) {
        this.mJobId = jobId;
    }

    public synchronized String getCurrentJobId() {
        return this.mJobId;
    }

    private void notifyAttributeListeners(ScanJobAttributeSet attributes) {
        ScanJobAttributeListener[] listeners;
        synchronized (this.mAttributeListeners) {
			listeners = this.mAttributeListeners
                    .toArray(new ScanJobAttributeListener[this.mAttributeListeners.size()]);
		}
        if(listeners.length > 0){
            ScanJobAttributeEvent event = new ScanJobAttributeEvent(this, attributes);
            for( ScanJobAttributeListener listener : listeners ) {
                listener.updateAttributes(event);
            }
        }
    }

	private void notifyJobListeners(ScanJobAttributeSet attributes) {
		ScanJobListener[] listeners;
		synchronized (this.mJobListeners) {
			listeners = this.mJobListeners.toArray(new ScanJobListener[this.mJobListeners.size()]);
		}
		if (listeners.length > 0) {
			ScanJobEvent event = new ScanJobEvent(attributes);
			ScanJobState jobState = (ScanJobState) attributes.get(ScanJobState.class);
			for (ScanJobListener listener : listeners) {
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
					throw new AssertionError("Unknown ScanJobState:" + jobState);
				}
			}
		}
	}
}


