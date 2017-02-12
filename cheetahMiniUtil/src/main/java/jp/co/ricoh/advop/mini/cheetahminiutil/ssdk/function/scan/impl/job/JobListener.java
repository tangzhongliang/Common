/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.job;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttributeSet;

/**
 * ジョブ状態の変化を通知するためのリスナーです。
 * Listener to notify job state changes
 */
public interface JobListener {

	/**
	 * ジョブの状態を通知します。
	 * Notifies the job state
	 */
	void onChangeJobStatus(ScanJobAttributeSet jobStatus);

    /**
     * ジョブが開始され、ジョブIDが採番されたことを通知します。
     * Notifies that the job starts and the job ID is assigned
     *
     * @param jobId
     */
    void setJobId(String jobId);

    /**
     * 現在のジョブ番号を取得します。
     * Obtains the current job number
     *
     * @return
     */
    String getJobId();
}
