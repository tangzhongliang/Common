/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

/**
 * スキャンジョブの状態理由を示す列挙型です
 * The enum type to indicate the scan job state reason
 */
public enum ScanJobStateReason {

    /**
     * ジョブの実行に必要なリソースが利用できない状態である
     * The resources required to run the job are unavailable.
     */
    RESOURCES_ARE_NOT_READY("resources_are_not_ready"),

    /**
     * メモリフルのため中止/停止した
     * Cancelled/paused for memory full
     */
    MEMORY_OVER("memory_over"),

    /**
     * 待機中・実行中のジョブが一杯で、新しいジョブを生成できない
     * Cannot create a new job because of waiting and processing jobs.
     */
    JOB_FULL("job_full"),

    /**
     * 送信に失敗して終了した（不達）
     * Sending failed (not delivered) and the job ended.
     */
    DELIVERY_FAILED("delivery_failed"),

    /**
     * 一部の宛先への送信に失敗して終了した
     * Failed to deliver to some destinations.
     */
    SOME_DELIVERY_FAILED("some_delivery_failed"),

    /**
     * 送信先との認証に失敗したため中止した
     * Failed authentication with destination; job cancelled.
     */
    SERVER_AUTHENTICATE_FAIL("server_authenticate_fail"),

    /**
     * 送信先との接続に失敗したため中止した
     * Failed to connect with destination; job cancelled
     */
    SERVER_CONNECT_ERROR("server_connect_error"),

    /**
     * アクセス権の問題で中止した
     * Job cancelled for access privilege problem
     */
    PERMISSION_DENIED("permission_denied"),

    /**
     * 宛先数オーバーのためジョブを中止した
     * Exceeded the maximum limit of destinations; job cancelled.
     */
    BROADCAST_NUMBER_OVER("broadcast_number_over"),

    /**
     * メールサーバでディスクフルが発生したため中止した
     * Disk full error occurred for email server; job cancelled.
     */
    SERVER_DISK_FULL("server_disk_full"),

    /**
     * 全てのページが白紙検知したためジョブを中止した
     * All pages are detected as blank pages; job cancelled.
     */
    BLANK_PAGES_ONLY("blank_pages_only"),

    /**
     * 何らかのエラー
     * Internal error
     */
    INTERNAL_ERROR("internal_error"),

    /**
     * 設定された原稿に問題がありスタートできない。
     * Cannot start the job because of the problem of the document.
     */
    ORIGINAL_SET_ERROR("original_set_error"),

    /**
     * スキャナ初期設定で設定されている送信サイズの上限を超えた
     * Exceeded the maximum size that can be set which is specified with scanner's initial setting.
     */
    EXCEEDED_MAX_EMAIL_SIZE("exceeded_max_email_size"),

    /**
     * 読み取りページ数オーバー
     * Exceeded the number of pages to scan.
     */
    EXCEEDED_MAX_PAGE_COUNT("exceeded_max_page_count"),

    /**
     * 証明書が無効
     * Certificate is invalid.
     */
    CERTIFICATE_INVALID("certificate_invalid"),

    /**
     * 他プロセスでエラーが発生したため中止した
     * Error occurred in another process; job cancelled
     */
    OTHER_PROCESS_ERROR("other_process_error"),

    /**
     * 一定時間以上操作がなかったため、ジョブを自動で中止した
     * No operation is done for a certain time; job automatically cancelled.
     */
    TIMEOUT("timeout"),

    /**
     * ジョブを開始するための準備中
     * Preparing to start the job.
     */
    PREPARING_JOB_START("preparing_job_start"),

    /**
     * 時刻指定されたジョブのため待機中
     * 送信リトライ待ちのため待機中
     * Waiting for the job in which its start time is specified.
     * Waiting for data transfer retry.
     */
    PROCESSING_LATER("processing_later"),

    /**
     * 読み取りで原稿ジャムが発生した
     * Paper jam occurred for scanning.
     */
    SCANNER_JAM("scanner_jam"),

    /**
     * 次原稿がセットされるのを待っている
     * Waiting for the next document to be set.
     */
    WAIT_FOR_NEXT_ORIGINAL("wait_for_next_original"),

    /**
     * 次原稿がセットされ、再開指示されるのを待っている
     * Waiting for the next document to be set and to be specified to be continued.
     */
    WAIT_FOR_NEXT_ORIGINAL_AND_CONTINUE("wait_for_next_original_and_continue"),

    /**
     * ユーザーからジョブ停止された
     * The job is stopped by user.
     */
    USER_REQUEST("user_request"),

    /**
     * 原稿サイズ自動検知で原稿が置かれていないか、検知できない
     * Document is not set for document size auto detect, or the size cannot be detected.
     */
    CANNOT_DETECT_ORIGINAL_SIZE("cannot_detect_original_size"),

    /**
     * 1画像のサイズがフレームバッファサイズを超えている
     * Exceeded maximum frame buffer size.
     */
    EXCEEDED_MAX_DATA_CAPACITY("exceeded_max_data_capacity"),

    /**
     * 読み取りできない原稿の向きである
     * Unscannable document orientation.
     */
    NOT_SUITABLE_ORIGINAL_ORIENTATION("not_suitable_original_orientation"),

    /**
     * 読み取りサイズが小さすぎる
     * Scan size is too small.
     */
    TOO_SMALL_SCAN_SIZE("too_small_scan_size"),

    /**
     * 読み取りサイズが大きすぎる
     * Scan size is too large.
     */
    TOO_LARGE_SCAN_SIZE("too_large_scan_size"),

    /**
     * 送信前プレビュー操作待ち
     * Waiting for the document preview before sending.
     */
    WAIT_FOR_ORIGINAL_PREVIEW_OPERATION("wait_for_original_preview_operation"),

    /**
     * スキャナカバー（ADFカバー）オープン
     * Scanner cover (ADF cover) is open.
     */
    SCANNER_COVER_OPEN("scanner_cover_open"),

    /**
     * 課金装置で制限になったため中止した
     * Reached the limitation at charge unit.
     *
     * @since SmartSDK V1.01
     */
    CHARGE_UNIT_LIMIT("charge_unit_limit");


    private final String value;

    private ScanJobStateReason(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    private static volatile Map<String, ScanJobStateReason> reasons = null;

    private static Map<String, ScanJobStateReason> getReasons() {
        if (reasons == null) {
            ScanJobStateReason[] reasonArray = values();
            Map<String, ScanJobStateReason> r = new HashMap<String, ScanJobStateReason>(reasonArray.length);
            for (ScanJobStateReason reason : reasonArray) {
                r.put(reason.value, reason);
            }
            reasons = r;
        }
        return reasons;
    }

    public static ScanJobStateReason fromString(String value) {
        return getReasons().get(value);
    }

}
