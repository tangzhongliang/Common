/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;

import java.util.HashMap;
import java.util.Map;

public enum ScannerState  implements ScanServiceAttribute {
    IDLE("idle"),
    MAINTENANCE("maintenance"),
    PROCESSING("processing"),
    STOPPED("stopped"),
    UNKNOWN("unknown");

    private String scannerStatus;
	private ScannerState(String state){
		this.scannerStatus = state;
	}

    private static volatile Map<String, ScannerState> states= null;
    static {
        states = new HashMap<String, ScannerState>();
        states.put("idle", IDLE);
        states.put("maintenance", MAINTENANCE);
        states.put("processing",PROCESSING);
        states.put("stopped",STOPPED);
        states.put("unknown",UNKNOWN);
    }


    /**
     * この列挙型に対応する文字列を返します。
     * Returns the string indicated by this enum type
     * @return
     */
    @Override
    public String toString(){
             return this.scannerStatus;
    }

	@Override
	public Class<?> getCategory() {
        return this.getClass();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

    public static  ScannerState fromString(String values) {
        return states.get(values);
    }

}
