/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 印刷カラーを示すクラスです。
 * The class to indicate print color mode.
 */
public enum PrintColor implements PrintRequestAttribute {
    AUTO_COLOR("auto_color"),
    MONOCHROME("monochrome"),
    COLOR("color"),
    RED_AND_BLACK("red_and_black"),
    TWO_COLOR("two_color"),
    SINGLE_COLOR("single_color")
    ;

    private final String PRINT_COLOR = "printColor";
    private String mPrintColor;
    private PrintColor(String val) {
        this.mPrintColor = val;
    }

    @Override
    public Object getValue() {
        return mPrintColor;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return PRINT_COLOR;
    }

    @Override
    public String toString() {
        return this.mPrintColor;
    }


    private static volatile Map<String, PrintColor> directory = null;

    private static Map<String, PrintColor> getDirectory() {
        if(directory == null) {
            Map<String, PrintColor> d = new HashMap<String, PrintColor>();
            for(PrintColor printColor : values()) {
                d.put(printColor.getValue().toString(), printColor);
            }
            directory = d;
        }
        return directory;
    }

    public static PrintColor fromString(String value) {
        return getDirectory().get(value);
    }

    public static List<PrintColor> getSupportedValue(List<String> values) {
        if( values == null ) {
            return Collections.emptyList();
        }

        List<PrintColor> list = new ArrayList<PrintColor>();
        for(String value : values) {
            PrintColor printColor = fromString(value);
            if( printColor != null ) {
                list.add(printColor);
            }
        }

        return list;
    }
}
