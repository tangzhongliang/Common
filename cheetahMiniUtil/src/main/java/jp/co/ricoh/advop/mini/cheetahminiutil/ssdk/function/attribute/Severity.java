/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute;

import java.util.HashMap;
import java.util.Map;

/**
 * エラーメッセージのレベルを示す列挙型です。
 * The enum type to indicate error message levels.
 */
public enum Severity {
	ERROR("error"),
	WARNING("warning");

	private String severity;
	private static Map<String, Severity> severities;

	static {
		severities = new HashMap<String, Severity>();
		severities.put(ERROR.getSeverity(), ERROR);
		severities.put(WARNING.getSeverity(), WARNING);
	}

	Severity(String severity) {
		this.severity = severity;
	}

	public String getSeverity() {
		return this.severity;
	}


	public static Severity fromString(String severity){
		if( severity == null ) return null;
		return severities.get(severity);
	}
}
