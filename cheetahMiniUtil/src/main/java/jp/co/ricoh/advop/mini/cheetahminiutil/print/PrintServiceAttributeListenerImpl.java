/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.print;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrinterState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrinterStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintServiceAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintServiceAttributeListener;

/**
 * プリントサービスがポストするイベントを受け取るリスナークラスです。
 * The listener class to monitor scan service attribute changes.
 */
public class PrintServiceAttributeListenerImpl implements PrintServiceAttributeListener {

    private  PrintManager printManager;

    public PrintServiceAttributeListenerImpl(){
        printManager = CHolder.instance().getPrintManager();
    }

    /**
     * プリントサービスからイベントを受け取ったときに呼び出されるメソッドです。
     * The method called when receive an event from the print service.
     *
     * @param event
     */
    @Override
    public void attributeUpdate(PrintServiceAttributeEvent event) {
            PrintServiceAttributeSet attributes = event.getAttributes();
            StringBuilder statusString = new StringBuilder();

            PrinterState state = (PrinterState)attributes.get(PrinterState.class);
            if(state != null) {
                switch(state){
                    case IDLE:
                        statusString.append("printer_status_idle");
                        break;
                    case MAINTENANCE:
                        statusString.append("printer_status_maintenance");
                        break;
                    case PROCESSING:
                        statusString.append("printer_status_processing");
                        break;
                    case STOPPED:
                        statusString.append("printer_status_stopped");
                        break;
                    case UNKNOWN:
                        statusString.append("printer_status_unknown");
                        break;
                }
            }

            PrinterStateReasons reasons = (PrinterStateReasons)attributes.get(PrinterStateReasons.class);
            if(reasons != null) {
                statusString.append(": ");
                statusString.append(reasons.getReasons());
            }
            LogC.d(statusString.toString());
            
            printManager.getPrintSysAlertDisplay().showOnServiceAttributeChange(state, reasons);
    }
}