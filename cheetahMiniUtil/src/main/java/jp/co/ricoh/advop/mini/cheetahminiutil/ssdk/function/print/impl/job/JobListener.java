/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.job;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttributeSet;

/**
 * ジョブの状態に関連する処理を定義したクラスです。
 * Listener to notify job state changes
 */
public interface JobListener {

    /**
     * ジョブの状態を通知します。
     * Notifies the job state
     */
    void onChangeJobStatus(PrintJobAttributeSet jobStatus);

    /**
     * ジョブが開始され、ジョブIDが採番されたことを通知します。
     * Notifies that the job starts and the job ID is assigned.
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
