/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute;

/**
 * プリント属性の組み合わせによる例外を示すクラスです。
 * Exception for print attribute combination.
 */
public class PrintAttributeException extends  PrintException{

    private String mProhibitionJobSetting;

    public PrintAttributeException(String prohibitionJobSetting) {
        super(prohibitionJobSetting);
        this.mProhibitionJobSetting = prohibitionJobSetting;
    }

    public String getUnsupportedAttributes() {
        return this.mProhibitionJobSetting;
    }
}
