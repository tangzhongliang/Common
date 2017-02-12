/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event;

/**
 * プリントサービスに関連するイベントを受け取るためのインターフェースです。
 * The listener interface to notify that the print service state has changed.
 */
public interface PrintServiceAttributeListener {

    /**
     * プリントサービスの状態が変化した際に実行されます。
     * Executed when print service state changes.
     *
     * @param event プリントサービスイベント
     *              Print service event
     * @see PrintServiceAttributeEvent
     */
    public abstract void attributeUpdate(PrintServiceAttributeEvent event);
}
