/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute;

/**
 * 属性を示すインターフェイスです。
 * 属性では、以下の基本機能を提供します。
 * ・本属性のカテゴリ取得
 * ・本属性名取得
 * 各メソッドで取得できる値は、実装クラスに依存します。
 *
 * The interface to indicate an attribute.
 * An attribute provides the following basic functions.
 *  - Obtains the category of this attribute
 *  - Obtains the name of this attribute
 * The values that can be obtained for each method depend on the implementation class.
 */
public interface Attribute {

    /**
     * 本属性のカテゴリを取得します。
     * Obtains the category of this attribute.
     *
     * @return 本属性のカテゴリ
     *         The category of this attribute
     */
	public abstract Class<?> getCategory();

    /**
     * 本属性の名前を取得します。
     * Obtains the name of this attribute.
     *
     * @return 本属性の名前
     *         The name of this attribute
     */
	public abstract String getName();

}
