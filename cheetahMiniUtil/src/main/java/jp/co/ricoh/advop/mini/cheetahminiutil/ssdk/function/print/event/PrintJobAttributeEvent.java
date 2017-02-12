/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintJob;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttributeSet;

/**
 * プリンタジョブの状態が変更した際に通知されるジョブ属性イベントクラスです。
 * The event class to notify print job state changes.
 */
public class PrintJobAttributeEvent {

    private PrintJob mEventSource = null;
    private PrintJobAttributeSet mPrintJobAttributeSet = null;

    public PrintJobAttributeEvent(PrintJob source, PrintJobAttributeSet attributes) {
        this.mEventSource = source;
        this.mPrintJobAttributeSet = attributes;
    }

    public PrintJob getSource() {
        return this.mEventSource;
    }

    public PrintJobAttributeSet getUpdateAttributes() {
        return this.mPrintJobAttributeSet;
    }
}
