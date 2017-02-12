/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;

/**
 * ジョブ名を示す属性クラスです。
 * The attribute class indicates job name.
 */
public class PrintJobName implements PrintJobAttribute {

    private String mPrintJobName;

    public PrintJobName(String name) {
        this.mPrintJobName = name;
    }

    public String getPrintJobName() {
        return mPrintJobName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrintJobName)) {
            return false;
        }

        PrintJobName other = (PrintJobName) obj;
        if (mPrintJobName == null) {
            if (other.mPrintJobName != null) {
                return false;
            }
        } else {
            if (other.mPrintJobName == null) {
                return false;
            }
            if (!mPrintJobName.equals(other.mPrintJobName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (mPrintJobName == null ? 0 : mPrintJobName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return mPrintJobName;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}
