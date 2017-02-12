/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性セットの機能を提供するクラスです
 * 本属性セットはハッシュ構造を持ちます。
 * The class to provide attribute set functions.
 * This attribute set supports hash structure.
 *
 * @param <T> この属性セットに格納する属性型パラメータ
 *            The attribute type parameter stored to this attribute set
 */
public class HashAttributeSet<T extends Attribute> implements AttributeSet<T> {

	Map<Class<?>, T> mAttributes;

    /**
     * 空のハッシュ属性セットを生成します
     * Creates an empty hash attribute set.
     */
	public HashAttributeSet(){
		mAttributes = new HashMap<Class<?>,T>();
	}

    /**
     * 指定した属性1個を保持するハッシュ属性セットを生成します
     * Creates the hash attribute set which stores one specified attribute.
     * @param attribute
     */
	public HashAttributeSet(T attribute){
		this();
		this.add(attribute);
	}

    /**
     * 指定した属性配列を元にしたハッシュ属性セットを生成します
     * Creates the hash attribute set which is based on the specified attribute array.
     * @param attributes
     */
	public HashAttributeSet(T[] attributes){
		this();
        for(T attribute : attributes) {
            this.add(attribute);
        }
	}

    /**
     * 指定した属性セットを基にしたハッシュ属性セットを生成します
     * Creates the hash attribute set which is based on the specified attribute set.
     * @param attributes
     */
	public HashAttributeSet(AttributeSet attributes){
		this();
		this.addAll(attributes);
	}


	@Override
	public boolean add(T attribute) {
        if(attribute == null) return false;

		T attr = mAttributes.put(attribute.getClass(), attribute);

		return (attribute.equals(attr));
	}

	@SuppressWarnings("unchecked")
    @Override
	public boolean addAll(AttributeSet attributes) {
        boolean ret = true;


        for(T attribute : (List<T>)attributes.getList()) {
            T attr = this.mAttributes.put(attribute.getClass(), attribute);
            if(attr != attribute) ret = false;
        }

        return ret;
	}

	@Override
	public boolean clear() {
        this.mAttributes.clear();
        return true;
	}

	@Override
	public boolean containsValue(T attribute) {
        return this.mAttributes.containsValue(attribute);
	}

    @Override
    public boolean equals(AttributeSet object) {
        if( !(object instanceof HashAttributeSet) ) {
            return false;
        }

        return object.hashCode() == this.hashCode();
    }

    @Override
	public T get(Class<?> category) {
        return this.mAttributes.get(category);
	}

	@Override
	public boolean isEmpty() {
        return this.mAttributes.isEmpty();
	}

	@Override
	public boolean remove(T attribute) {
        T attr = this.mAttributes.remove(attribute.getCategory());
        return (attribute == attr);
	}

	@Override
	public boolean remove(Class<?> category) {
        T attr = this.mAttributes.remove(category);
        return (attr != null);
	}

	@Override
	public int size() {
        return this.mAttributes.size();
	}

	@Override
	public List<T> getList(){
        return new ArrayList<T>(this.getCollection());
	}

	@Override
	public Collection<T> getCollection() {
		return this.mAttributes.values();
	}

}
