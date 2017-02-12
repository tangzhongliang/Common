/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute;

import java.util.Collection;
import java.util.List;

/**
 * 属性セットの機能を提供するインターフェイスです
 * The interface to provide functions of attribute set
 *
 * @param <T> この属性セットに格納する属性型パラメータ
 *            Attribute type parameter stored to this attribute set
 */
public interface AttributeSet<T> {
	/**
	 * 指定した属性をこの属性セットに追加します。
	 * Adds the specified attribute to this attribute set.
	 *
	 * @param attribute 追加する属性
	 *                  attrubute to add
	 * @return 追加結果
	 *         Added result
	 */
	public boolean add(T attribute);

	/**
	 * この属性に指定されたセットの全ての要素を追加します。
     * 重複するカテゴリの値を設定した場合は、上書きされます。
     * Adds all elements of the set specified to this attribute.
     * If setting a duplicated category value, the value is overwritten.
     *
	 * @param attributes 追加する属性セット
	 *                   Attribute set to add
	 * @return 追加結果
	 *         Added result
	 */
	public boolean addAll(AttributeSet attributes);

    /**
     * この属性セットに格納されている全属性を削除します。
     * Removes all attributes stored to this attribute set.
     *
     * @return 削除結果
     *         Removed result
     */
	public boolean clear();

    /**
     * この属性セットに指定した属性が含まれるか確認します。
     * Checks if the specified attribute is contained in this attribute set.
     *
     * @param attribute 属性セットに含まれるか確認する属性
     *                  The attribute to be checked
     * @return 指定した属性がこの属性セットに含まれる場合はtrueが返ります。
     *         If the specified attribute is contained in this attribute set, true is returned.
     */
	public boolean containsValue(T attribute);

    /**
     * この属性セットと指定した属性セットが同じオブジェクトか確認します。
     * Checks if the specified attribute set is the same object as this attribute set.
     *
     * @param object 同じオブジェクトか確認する属性セット
     *               The attribute set to be checked
     * @return 指定した属性セットがこの属性セットと同一の場合はtrueが返ります。
     *         If the specified attribute set is the same object as this attribute set,
     *         true is returned.
     */
	public boolean equals(AttributeSet object);

    /**
     * この属性セットから指定したカテゴリの属性を取得します。
     * Obtains the attribute of the specified category from this attribute set.
     *
     * @param category 取得したい属性のカテゴリ
     *                 The category of the attribute to obtain
     * @return この属性セットに指定したカテゴリの属性が存在した場合は、見付かった属性を返します。
     *         見付からなかった場合はnullが返ります。
     *         If the attribute of the specified category exists in this attribute set,
     *         the found attribute is returned. If not found, null is returned.
     */
	public T get(Class<?> category);

    /**
     * ハッシュ値を取得します。
     * Returns hash value.
     *
     * @return ハッシュ値
     *         hash value
     */
	public int hashCode();

    /**
     * この属性セットが空か確認します。
     * Checks if this attribute set is empty.
     *
     * @return 属性セットが空の場合trueが返ります。
     *         If the attribute set is empty, null is returned.
     */
	public boolean isEmpty();

    /**
     * この属性セットから指定した属性を1つ削除します。
     * Removes one specified attribute from this attribute set.
     *
     * @param attribute この属性セットから削除する属性オブジェクト
     *                  The attribute object to be removed from this attribute set
     * @return 削除が成功した場合はtrueが返ります。
     *         If removed successfully, true is returned.
     */
	public boolean remove(T attribute);

    /**
     * この属性セットから指定したカテゴリに紐付く属性を１つ削除します。
     * Removes one attribute associated with the specified category from this attribute set.
     *
     * @param category この属性セットから削除する属性のカテゴリ
     *                 The category of the attribute to be removed from this attribute set
     * @return 削除が成功した場合はtrueが返ります。
     *         If removed successfully, true is returned.
     */
	public boolean remove(Class<?> category);

    /**
     * この属性セットの現在の大きさを取得する
     * Obtains the current size of this attribute set.
     *
     * @return この属性セットに格納されている属性数
     *         The number of attributes stored to this attribute set
     */
	public int size();

    /**
     * この属性セットに含まれる属性をリストとして取得します。
     * Obtains the list of attributes contained in this attribute set.
     *
     * @return この属性セットに含まれる属性のリスト
     *         The list of attributes contained in this attribute set
     */
	public List<T> getList();

    /**
     * この属性セットに含まれる属性をコレクションとして取得します。
     * Obtains the collection of attributes contained in this attribute set.
     *
     * @return この属性セットに含まれる属性のコレクション
     *         The collection of attributes contained in this attribute set.
     */
	public Collection<T> getCollection();
}
