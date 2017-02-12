/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 要素配列の抽象クラス
 */
public abstract class ArrayElement<E extends Element> implements Iterable<E> {
	
	protected final List<Map<String, Object>> list;
	
	protected ArrayElement(List<Map<String, Object>> list) {
		if (list == null) {
			throw new NullPointerException("list must not be null.");
		}
		this.list = list;
	}
	
	public int size() {
		return list.size();
	}
	
	public E get(int index) {
		Map<String, Object> value = list.get(index);
		if (value == null) {
			return null;
		}
		return createElement(value);
	}
	
	abstract protected E createElement(Map<String, Object> values);
	
	@Override
	public Iterator<E> iterator() {
		return new ArrayElementIterator(list.iterator());
	}
	
	
	private class ArrayElementIterator implements Iterator<E> {
		
		private final Iterator<Map<String, Object>> iter;
		
		ArrayElementIterator(Iterator<Map<String, Object>> iter) {
			this.iter = iter;
		}
		
		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}
		
		@Override
		public E next() {
			Map<String, Object> value = iter.next();
			if (value == null) {
				return null;
			}
			return createElement(value);
		}
		
		@Override
		public void remove() {
			iter.remove();
		}
		
	}
	
}
