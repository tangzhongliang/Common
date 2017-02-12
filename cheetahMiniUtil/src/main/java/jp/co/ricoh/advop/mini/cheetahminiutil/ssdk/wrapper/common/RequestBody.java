/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

/**
 * WebAPI通信でのリクエストボディ部分を定義するインターフェース。
 */
public interface RequestBody {
	
    /**
     * Content-Typeを取得する。
     * @return　Content-Typeとして設定されている文字列
     */
	public String getContentType();
	
	/**
	 * ボディ部分のデータを取得する。
	 * @return ボディとして設定されているデータ
	 */
	public String toEntityString();
	
}
