/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * ジョブ状態を示す列挙型です。
 * The enum type indicates the job state.
 */
public enum PrintJobState implements PrintJobAttribute {
    ABORTED ("aborted"),
    CANCELED ("canceled"),
    COMPLETED ("completed"),
    PENDING ("pending"),
    PROCESSING ("processing"),
    PROCESSING_STOPPED ("processing_stopped");

    private final String mJobState;

    private PrintJobState(String value) {
        this.mJobState = value;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }


    private static volatile Map<String, PrintJobState> states = null;

    private static Map<String, PrintJobState> getStates() {
        if(states == null) {
            Map<String, PrintJobState> s = new HashMap<String, PrintJobState>();
            for(PrintJobState state : values()) {
                s.put(state.mJobState , state);
            }
            states = s;
        }
        return states;
    }

    public static PrintJobState fromString(String value) {
        return getStates().get(value);
    }

    @Override
    public String toString() {
        return mJobState;
    }
}
