/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;

/**
 * 用紙トレイを表すクラスです。
 * The class indicates a paper trap.
 */
public enum PaperTray implements PrintRequestAttribute {

	/**
	 * 自動トレイ選択
	 * Auto
	 */
	AUTO("auto"),

	/**
	 * トレイ1
	 * Tray 1
	 */
	TRAY1("tray1"),

	/**
	 * トレイ2
	 * Tray 2
	 */
	TRAY2("tray2"),

	/**
	 * トレイ3
	 * Tray 3
	 */
	TRAY3("tray3"),

	/**
	 * トレイ4
	 * Tray 4
	 */
	TRAY4("tray4"),

	/**
	 * トレイ5
	 * Tray 5
	 */
	TRAY5("tray5"),

	/**
	 * トレイ6
	 * Tray 6
	 */
	TRAY6("tray6"),

	/**
	 * トレイ7
	 * Tray 7
	 */
	TRAY7("tray7"),

	/**
	 * トレイ8
	 * Tray 8
	 */
	TRAY8("tray8"),

	/**
	 * トレイ9
	 * Tray 9
	 */
	TRAY9("tray9"),

	/**
	 * 手差しトレイ
	 * Bypass tray
	 */
	MANUAL("manual"),

	/**
	 * 大量給紙トレイ
	 * Large capacity tray
	 */
	LARGE_CAPACITY("large_capacity"),

	/**
	 * トレイ10
	 * Tray 10
	 * 
	 * @since SmartSDK V1.01
	 */
	TRAY10("tray10"),

	/**
	 * トレイA
	 * Tray A
	 * 
	 * @since SmartSDK V1.01
	 */
	TRAY_A("trayA");


	private static final String PAPER_TRAY = "paperTray";

	private final String paperTray;

	private PaperTray(String value) {
		this.paperTray = value;
	}

	@Override
	public Class<?> getCategory() {
		return PaperTray.class;
	}

	@Override
	public String getName() {
		return PAPER_TRAY;
	}

	@Override
	public Object getValue() {
		return this.paperTray;
	}

    private static volatile Map<String, PaperTray> trays = null;

    private static Map<String, PaperTray> getTrays(){
        if( trays == null ) {
            Map<String, PaperTray> map = new HashMap<String, PaperTray>();
            for(PaperTray side : values() ){
                map.put(side.getValue().toString(), side);
            }
            trays = map;
        }
        return trays;
    }

	public static PaperTray fromString(String value) {
	    return getTrays().get(value);
	}

	public static List<PaperTray> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<PaperTray> list = new ArrayList<PaperTray>();
		for(String value : values){
			PaperTray side = fromString(value);
			if( side != null ){
				list.add(fromString(value));
			}
		}
		return list;
	}

}
