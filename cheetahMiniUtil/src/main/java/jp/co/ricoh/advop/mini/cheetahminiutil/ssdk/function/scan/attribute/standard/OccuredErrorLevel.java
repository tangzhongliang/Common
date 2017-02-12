/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;

import java.util.HashMap;
import java.util.Map;

public enum OccuredErrorLevel  implements ScanServiceAttribute {

    FATAL_ERROR("fatal_error"),
    ERROR("error"),
    WARNING("warning"),
    REPORT("report");

    private String errorLevel;
	private OccuredErrorLevel(String errorLevel){
		this.errorLevel = errorLevel;
	}

    private static volatile Map<String, OccuredErrorLevel> errors= null;
    static {
        errors = new HashMap<String, OccuredErrorLevel>();
        errors.put("fatal_error", FATAL_ERROR);
        errors.put("error", ERROR);
        errors.put("warning", WARNING);
        errors.put("report", REPORT);
    }

    /**
     * この列挙型に対応する文字列を返します。
     * Returns the strings indicates this enum type
     * @return
     */
    @Override
    public String toString(){
             return this.errorLevel;
    }

	@Override
	public Class<?> getCategory() {
        return this.getClass();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

    public static  OccuredErrorLevel fromString(String value) {
        if (null != value)
            return errors.get(value);
        else
            return null;
    }

}
