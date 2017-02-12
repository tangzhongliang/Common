/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public final class Magnification implements ScanRequestAttribute {

	private static final String MAGNIFICATION = "magnification";

	/**
	 * 内部管理する変倍率を格納
	 * Stores the scaling ratio managed internally.
	 */
	private final String magnification;

	/**
	 * 指定した変倍率で変倍属性を構築する
	 * Builds the scaling attribute with the specified scaling ratio.
	 * @param magnification 変倍率を表す文字列
	 *                      The string to represent scaling ratio
	 */
	public Magnification(String magnification) {
		this.magnification = magnification;
	}

	@Override
	public Class<?> getCategory() {
		return Magnification.class;
	}

	@Override
	public String getName() {
		return MAGNIFICATION;
	}

	@Override
	public Object getValue() {
		return this.magnification;
	}

}
