/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute;

/**
 * スキャン属性の組み合わせによる例外クラスです。
 * サポートされないスキャン要求属性を設定しジョブを開始した場合に、組み合わせ不可となった属性を報告します。
 * Exception for scan attribute combination.
 * When setting unsupported scan request attributes and starting the job,
 * the attributes judged as invalid combination are reported.
 *
 */
public final class ScanAttributeException extends ScanException {

    private String mProhibitionJobSetting;

    public ScanAttributeException(String prohibitionAttributes) {
        super(prohibitionAttributes);
        this.mProhibitionJobSetting = prohibitionAttributes;
    }

    public String getUnsupportedAttributes() {
        return this.mProhibitionJobSetting;
    }

}
