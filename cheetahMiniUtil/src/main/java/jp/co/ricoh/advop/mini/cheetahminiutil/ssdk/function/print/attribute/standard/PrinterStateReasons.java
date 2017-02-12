/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.Severity;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetPrinterStatusResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * プリンタ状態の詳細情報の集合を定義するクラスです。
 * The attribute class which lists the detail information of printer service state
 */
public class PrinterStateReasons implements PrintServiceAttribute {

    private final Map<PrinterStateReason, Severity> mReasons;

    public static PrinterStateReasons convertFrom(GetPrinterStatusResponseBody.PrinterStatusReasonsArray reasonsArray) {
        if(reasonsArray == null) return null;

        Map<PrinterStateReason, Severity> reasonsMap = new HashMap<PrinterStateReason, Severity>();
        for(GetPrinterStatusResponseBody.PrinterStatusReasons reasons : reasonsArray) {
            PrinterStateReason reason = PrinterStateReason.fromString(reasons.getPrinterStatusReason());
            Severity severity = Severity.fromString(reasons.getSeverity());
            if(reason != null) {
                reasonsMap.put(reason, severity);
            }
        }

        return new PrinterStateReasons(reasonsMap);
    }

    PrinterStateReasons(Map<PrinterStateReason, Severity> reasons) {
        this.mReasons = reasons;
    }

    public Set<PrinterStateReason> getReasons() {
        return Collections.unmodifiableSet(mReasons.keySet());
    }

    public Set<PrinterStateReason> printerStateReasonSet(Severity severity) {
        Set<PrinterStateReason> reasonSet = new HashSet<PrinterStateReason>();
        for(Map.Entry<PrinterStateReason, Severity> entry : mReasons.entrySet()) {
            if(entry.getValue() == severity) {
                reasonSet.add(entry.getKey());
            }
        }

        return Collections.unmodifiableSet(reasonSet);
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return mReasons.toString();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mReasons.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof PrinterStateReasons)) {
            return false;
        }

        PrinterStateReasons other = (PrinterStateReasons)o;
        return mReasons.equals(other.mReasons);
    }
}
