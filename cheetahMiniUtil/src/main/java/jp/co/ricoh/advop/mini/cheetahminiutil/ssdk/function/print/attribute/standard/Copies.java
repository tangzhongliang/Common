/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;

/**
 * 印刷部数を示すクラスです。
 * The class to indicate number of copies
 */
public class Copies implements PrintRequestAttribute {
    private static final String COPIES = "copies";

    private int mCopies;

    public Copies(int copies) {
        this.mCopies = copies;
    }

    @Override
    public String toString() {
        return Integer.toString(mCopies);
    }

    @Override
    public Object getValue() {
        return mCopies;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return COPIES;
    }
}
