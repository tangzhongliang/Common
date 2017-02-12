/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;



/**
 * トレイ名の列挙を表す。<BR>
 * SmartSDK WebAPI仕様書->150.データ属性値定義の04.用紙トレイを参照<BR>
 */
public enum TrayName implements PrintRequestAttribute {

	/** 大容量給紙トレイ */
	LARGE_CAPACITY ("large_capacity"),
	/** 自動トレイ選択 */
	AUTO ("auto"),
	/** 手差しトレイ */
	MANUAL ("manual"),
	/** トレイ1 */
	TRAY1 ("tray1"),
	/** トレイ2 */
	TRAY2 ("tray2"),
	/** トレイ3 */
	TRAY3 ("tray3"),
	/** トレイ4 */
	TRAY4 ("tray4"),
	/** トレイ5 */
	TRAY5 ("tray5"),
	/** トレイ6 */
	TRAY6 ("tray6"),
	/** トレイ7 */
	TRAY7 ("tray7"),
	/** トレイ8 */
	TRAY8 ("tray8"),
	/** トレイ9 */
	TRAY9 ("tray9"),
	/** アイコン説明 */
	ICON_DETAIL ("icon_detail");

	/** トレイ名 */
	private final String mTrayname;

	/**
	 * トレイ名で列挙型を構築する。<BR>
	 * @param value トレイ名
	 */
	private TrayName(String name) {
		this.mTrayname = name;
	}

	/**
	 * トレイ名を取得する。<BR>
	 * @return トレイ名
	*/
	@Override
	public String toString() {
		return this.mTrayname;
	}

	/**
	 * この属性のカテゴリとして使用される属性クラスを取得する。<BR>
	 * @return トレイ名を示す属性クラス<BR>
	*/
	@Override
	public Class<?> getCategory() {
		return this.getClass();
	}

	/**
	 * この属性のカテゴリとして使用される属性クラスの名前を取得する。<BR>
	 * @return トレイ名を示す属性クラスの名前<BR>
	*/
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/** トレイ名を保持するマップ */
	private static volatile Map<String, TrayName> states = null;

	/**
	 * トレイ名を保持するマップを取得する。<BR>
	 * @return トレイ名を保持するマップ<BR>
	*/
	private static Map<String, TrayName> getStates() {
		if (states == null) {
			TrayName[] stateArray = values();
			Map<String, TrayName> s = new HashMap<String, TrayName>(stateArray.length);
			for (TrayName state : stateArray) {
				s.put(state.mTrayname, state);
			}
			states = s;
		}
		return states;
	}

	/**
	 * トレイ名を示すハッシュマップ中からString型の引数valueに対応したenumを取得する。<BR>
	 * @param value　取得したいトレイ名の文字列<BR>
	 * @return value に該当するトレイ名<BR>
	 * ※valueに該当するトレイ名が無い場合は、NULLとなる。<BR>
	*/
	public static TrayName fromString(String value) {
		return getStates().get(value);
	}

    @Override
    public String getValue() {        
        return mTrayname;
    }
}
