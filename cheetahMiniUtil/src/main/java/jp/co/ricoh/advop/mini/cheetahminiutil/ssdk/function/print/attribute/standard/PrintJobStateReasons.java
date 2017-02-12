/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ジョブ状態の詳細の集合を示すクラスです。
 * The attribute class which lists the detail information of print job state.
 */
public final class PrintJobStateReasons implements PrintJobAttribute, Iterable<PrintJobStateReason> {

    private final Set<PrintJobStateReason> mReasons;

    public static PrintJobStateReasons getInstance(List<String> reasons) {
        if(reasons == null) {
            return null;
        }
        return new PrintJobStateReasons(reasons);
    }

    PrintJobStateReasons(List<String> reasonValues) {
        EnumSet<PrintJobStateReason> reasonSet = EnumSet.noneOf(PrintJobStateReason.class);
        for(String reasonValue : reasonValues ) {
            PrintJobStateReason reason = PrintJobStateReason.fromString(reasonValue);
            if(reason != null) {
                reasonSet.add(reason);
            }
        }
        this.mReasons = Collections.unmodifiableSet(reasonSet);
    }

    public Set<PrintJobStateReason> getReasons() {
        return this.mReasons;
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
    public Iterator<PrintJobStateReason> iterator() {
        return mReasons.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof PrintJobStateReasons)) {
            return false;
        }

        PrintJobStateReasons other = (PrintJobStateReasons)obj;
        return mReasons.equals(other.mReasons);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mReasons.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return mReasons.toString();
    }
}
