/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public enum AutoDensity implements ScanRequestAttribute {

	ON(true),
	OFF(false);

	private static final String NAME_AUTO_DENSITY = "autoDensity";

	private final boolean value;

	private AutoDensity(boolean value) {
		this.value = value;
	}

	@Override
	public Class<?> getCategory() {
		return AutoDensity.class;
	}

	@Override
	public String getName() {
		return NAME_AUTO_DENSITY;
	}

	@Override
	public Object getValue() {
		return Boolean.valueOf(value);
	}


	public static List<AutoDensity> getSupportedValue(List<Boolean> values) {
		if (values == null) {
			return Collections.emptyList();
		}
		
		List<AutoDensity> list = new ArrayList<AutoDensity>();
		for(Boolean value : values){
			list.add( value ? ON : OFF );
		}
		return list;
	}
}
