/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttributeSet;

/**
 * プリントサービスの状態が変更した際に通知されるプリントサービス属性イベントクラスです。
 * Print service state change event
 */
public final class PrintServiceAttributeEvent {
    PrintServiceAttributeSet mAttributes = null;

    public PrintServiceAttributeEvent(PrintServiceAttributeSet attributes) {
        this.mAttributes = attributes;
    }

    public PrintServiceAttributeSet getAttributes() {
        return this.mAttributes;
    }
}
