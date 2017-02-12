/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttributeSet;

/**
 * スキャンジョブのイベントを通知する際のイベント情報をまとめたイベントクラスです。
 * イベント情報には、発生したジョブ状態属性のセットが含まれます。
 * The event class which stores event information for the time of notifying scan job events.
 * An event information includes the set of job state attributes.
 */
public final class ScanJobEvent {
    private ScanJobAttributeSet mAttributeSet;

    public ScanJobEvent(ScanJobAttributeSet attributeSet) {
        this.mAttributeSet = attributeSet;
    }

    public ScanJobAttributeSet getAttributeSet() {
        return this.mAttributeSet;
    }
}
