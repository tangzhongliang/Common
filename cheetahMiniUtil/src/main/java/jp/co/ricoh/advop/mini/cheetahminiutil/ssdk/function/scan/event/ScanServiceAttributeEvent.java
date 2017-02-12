/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttributeSet;

/**
 * 変化したスキャンサービスの状態イベントです。
 * Scan service state change event
 *
 * @see ScanServiceAttributeListener
 *
 */
public final class ScanServiceAttributeEvent {
    ScanServiceAttributeSet mAttributes = null;

    public ScanServiceAttributeEvent(ScanServiceAttributeSet attributes) {
        this.mAttributes = attributes;
    }

	public ScanServiceAttributeSet getAttributes(){
        return this.mAttributes;
	}
}
