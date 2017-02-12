/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * プリンタの状態を定義する列挙型です。
 * The enum type to define the state of printer.
 */
public enum PrinterState implements PrintServiceAttribute {
    IDLE("idle"),
    MAINTENANCE("maintenance"),
    PROCESSING("processing"),
    STOPPED("stopped"),
    UNKNOWN("unknown");

    private final String state;

    private PrinterState(String value) {
        this.state = value;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }



    private static volatile Map<String,PrinterState> directory = null;
    private static Map<String,PrinterState> getDirectory() {
        if(directory == null) {
            PrinterState[] stateArray = values();
            Map<String, PrinterState> s = new HashMap<String, PrinterState>(stateArray.length);
            for(PrinterState state : stateArray) {
                s.put(state.state, state);
            }
            directory = s;
        }

        return directory;
    }

    public static PrinterState fromString(String value) {
        return getDirectory().get(value);
    }

    @Override
    public String toString() {
        return state;
    }
}
