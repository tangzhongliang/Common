/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event;

/**
 * スキャンジョブリスナーインターフェイスです。
 * スキャンジョブ実行時の状態変化を通知します。
 * 主に以下のイベントを通知します
 * ・スキャンジョブの待機中イベント
 * ・スキャンジョブの動作中イベント
 * ・スキャンジョブの一時停止イベント
 * ・スキャンジョブの完了イベント
 * ・スキャンジョブのキャンセルイベント
 * ・スキャンジョブの中止イベント
 *
 * Scan job listener interface
 * The following events are mainly notified.
 *  - Scan job pending event
 *  - Scan job processing event
 *  - Scan job pause event
 *  - Scan job end event
 *  - Scan job cancel event
 *  - Scan job aborted event
 */
public interface ScanJobListener {

    public abstract void jobPending(ScanJobEvent event);
    public abstract void jobProcessing(ScanJobEvent event);
    public abstract void jobProcessingStop(ScanJobEvent event);
	public abstract void jobCompleted(ScanJobEvent event);
	public abstract void jobCanceled(ScanJobEvent event);
    public abstract void jobAborted(ScanJobEvent event);
}
