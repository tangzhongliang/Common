/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common;

import java.util.Collections;
import java.util.List;

/**
 * オブジェクト型の変換処理をまとめたクラスです。
 * This class stores conversion processes in object type.
 */
public final class Conversions {

    /**
     * Integer型をint型に変換します。
     * Converts the Integer value to int value.
     *
     * @param value 変換するInteger型の値
     *              The value in Integer type to convert
     * @param defaultValue valueが設定されていなかったときに返されるデフォルト値
     *                     The default value returned when value is not set
     * @return 変換後のint値
     *         The int value after conversion
     */
	public static int getIntValue(Integer value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value.intValue();
	}

	/**
	 * Boolean型をboolean型に変換します。
	 * Converts the Boolean value to boolean value.
	 *
	 * @param value 変換するBoolean型の値
	 *              The value in Boolean type to convert
	 * @param defaultValue valueが設定されていなかったときに返されるデフォルト値
     *                     The default value returned when value is not set
	 * @return 変換後のboolean値
	 *         The boolean value after conversion
	 */
	public static boolean getBooleanValue(Boolean value, boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value.booleanValue();
	}

	public static <E> List<E> getList(List<E> list) {
		if (list == null) {
			return Collections.emptyList();
		}
		return list;
	}


	private Conversions() {}

}
