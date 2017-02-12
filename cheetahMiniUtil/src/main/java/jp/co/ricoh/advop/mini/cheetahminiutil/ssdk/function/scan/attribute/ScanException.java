/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute;

/**
 * スキャンで発生するエラー条件をカプセル化します。
 * 本ベースクラスはエラー文字列のみ提供します。
 * 詳細な理由を知りたい場合は、サブクラスを利用することで理由を取得することができます。
 * Encapsulates the error conditions that occur for scanning.
 * This base class provides only error strings.
 * Detailed reason can be obtained by using the subclass.
 */
public class ScanException extends Exception{
    public ScanException(){
    }

    public ScanException(String message){
        super(message);
    }

    public ScanException(Exception ex) {
        super(ex.getMessage());
    }
}
