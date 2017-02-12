/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * プリンタジョブの状態理由を示す列挙型です
 * The enum type to indicate print job state reason.
 */
public enum PrintJobStateReason implements PrintJobAttribute {

    /**
     * 印刷データの展開エラーのため中止した
     * Job cancelled for print data error
     */
    COMPRESSION_ERROR("compression_error"),

    /**
     * 印刷データのフォーマットエラーのため中止した
     * Job cancelled for print data format error
     */
    DOCUMENT_FORMAT_ERROR("document_format_error"),

    /**
     * ジョブが未確認のローカルユーザ、つまりデバイスのコンソールからログインしたユーザによって取り消されました。
     * Job was cancelled by an unidentified local user who logged in from the device console.
     */
    JOB_CANCELED_AT_DEVICE("job_canceled_at_device"),

    /**
     * ジョブの実行に必要なリソースが利用できない状態である
     * Cannot use resource for executing Jobs.
     */
    RESOURCES_ARE_NOT_READY("resources_are_not_ready"),

    /**
     * アクセス権の問題で中止した
     * Job cancelled for access privilege problem
     */
    PERMISSION_DENIED("permission_denied"),

    /**
     * 印刷利用量制限になったため中止した
     * Reached printing usage limitation.
     */
    PRINT_VOLUME_LIMIT("print_volume_limit"),

    /**
     * 一定時間以上操作がなかったため、ジョブを自動で中止した
     * timeout
     */
    TIMEOUT("timeout"),

    /**
     * ジョブは所有者、つまり認証 id が印刷ジョブを生成した発生元のユーザと一致するユーザ、
     * またはジョブ所有者のセキュリティグループのメンバなどの、一定の権限を持つエンドユーザによって取り消された
     * Job was cancelled by an owner who was the same user that had generated the print jobs wih its authentication ID
     * or by an end user who had a certain authority such as a member of Job owner's security group.
     */
    JOB_CANCELED_BY_USER("job_canceled_by_user"),

    /**
     * ジョブの生成中に取り消された
     * Cancelled during generating Job.
     */
    JOB_CANCELED_DURING_CREATING("job_canceled_during_creating"),

    /**
     * ジョブを開始するための準備中
     * Preparing to start the job.
     */
    PREPARING_JOB_START("preparing_job_start");


    private final String mPrintJobStateReason;

    private PrintJobStateReason(String value) {
        this.mPrintJobStateReason = value;
    }

    @Override
    public String toString() {
        return mPrintJobStateReason;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    private static volatile Map<String, PrintJobStateReason> reasons = null;

    private static Map<String, PrintJobStateReason> getReasons() {
        if (reasons == null) {
            PrintJobStateReason[] reasonArray = values();
            Map<String, PrintJobStateReason> r = new HashMap<String, PrintJobStateReason>(reasonArray.length);
            for (PrintJobStateReason reason : reasonArray) {
                r.put(reason.mPrintJobStateReason, reason);
            }
            reasons = r;
        }
        return reasons;
    }

    public static PrintJobStateReason fromString(String value) {
        return getReasons().get(value);
    }

}
