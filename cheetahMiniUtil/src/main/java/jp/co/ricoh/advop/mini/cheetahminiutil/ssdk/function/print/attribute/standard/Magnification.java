/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 印刷カラーモードを表すクラスです。
 * The class to indicate the print color mode.
 */
public final class Magnification implements PrintRequestAttribute {

	private static final String Magnification = "magnification";


	private final String magnification;

	public Magnification(String magnification){
		this.magnification = magnification;
	}

	@Override
	public Class<?> getCategory() {
		return Magnification.class;
	}

	@Override
	public String getName() {
		return Magnification;
	}

	@Override
	public Object getValue() {
		return this.magnification;
	}

    private static volatile Map<String, Magnification> directory = null;

    private static Map<String, Magnification> getDirectory() {
        if(directory == null){
            Map<String, Magnification> d = new HashMap<String, Magnification>();
//                d.put(color.getValue().toString(), color);
//                d.put(color.getValue().toString(), color);


            directory = d;
        }
        return directory;
    }

	private static Magnification fromString(String value) {
		return getDirectory().get(value);
	}

	public static List<Magnification> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<Magnification> list = new ArrayList<Magnification>();
		for(String value : values){
			Magnification color = fromString(value);
			if( color != null ) {
				list.add(fromString(value));
			}
		}
		return list;
	}
//	
//	@Override
//	public void applyToJobSetting(JobSetting jobSetting) {
//		jobSetting.setMagnification(magnification);
//	}


	
	
}
