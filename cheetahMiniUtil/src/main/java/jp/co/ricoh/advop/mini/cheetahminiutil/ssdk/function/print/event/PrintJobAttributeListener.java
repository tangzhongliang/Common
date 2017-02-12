/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event;

/**
 * ジョブ属性イベントを受け取るためのリスナーを定義するインターフェースです。
 * The listener interface to receive job attribute events.
 */
public interface PrintJobAttributeListener {

    /**
     * プリントジョブ状態が変化した際に実行されます。
     * Executed when print job state changes.
     *
     * @param attributesEvent プリントジョブ属性イベント
     *              Print job attribute event
     * @see PrintServiceAttributeEvent
     */
    public abstract void updateAttributes(PrintJobAttributeEvent attributesEvent);
}
