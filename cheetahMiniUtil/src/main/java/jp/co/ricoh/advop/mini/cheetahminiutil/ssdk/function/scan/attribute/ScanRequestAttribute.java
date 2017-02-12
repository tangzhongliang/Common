/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.Attribute;

/**
 * スキャン要求属性のインターフェイスです
 * The interface of scan request attributes.
 */
public interface ScanRequestAttribute extends Attribute {

	/**
	 * この属性の設定される一意の値を返します。
	 * Returns a unique value set to this attribute.
	 * @return
	 */
	public abstract Object getValue();
}
