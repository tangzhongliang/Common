/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;

/**
 * 印刷ユーザー名を示すクラスです。
 * The class to indicate the print user id.
 */
public class PrintJobUserName implements PrintJobAttribute {

    private String mUserName;

    public PrintJobUserName(String name) {
        this.mUserName = name;
    }

    public String getUserName() {
        return mUserName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrintJobUserName)) {
            return false;
        }

        PrintJobUserName other = (PrintJobUserName) obj;
        if (mUserName == null) {
            if (other.mUserName != null) {
                return false;
            }
        } else {
            if (other.mUserName == null) {
                return false;
            }
            if (!mUserName.equals(other.mUserName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (mUserName == null ? 0 : mUserName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return mUserName;
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
