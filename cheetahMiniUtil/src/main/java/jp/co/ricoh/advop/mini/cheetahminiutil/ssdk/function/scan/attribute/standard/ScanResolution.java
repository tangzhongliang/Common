/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public enum ScanResolution implements ScanRequestAttribute {
	
	DPI_100("100"),
	DPI_150("150"),
	DPI_200("200"),
	DPI_300("300"),
	DPI_400("400"),
	DPI_600("600");
	
	private static final String NAME_SCAN_RESOLUTION = "scanResolution";
	
	private final String value;
	
	private ScanResolution(String value) {
		this.value = value;
	}


	@Override
	public Class<?> getCategory() {
		return ScanResolution.class;
	}

	@Override
	public String getName() {
		return NAME_SCAN_RESOLUTION;
	}

	@Override
	public Object getValue() {
		return value;
	}

    private static volatile Map<String, ScanResolution> directory = null;

    private static Map<String, ScanResolution> getDirectory() {
        if(directory == null) {
            Map<String, ScanResolution> d = new HashMap<String, ScanResolution>();
            for(ScanResolution resolution : values()) {
                d.put(resolution.getValue().toString(), resolution);
            }
            directory = d;
        }
        return directory;
    }

    private static ScanResolution fromString(String value) {
        return getDirectory().get(value);
    }

    public static List<ScanResolution> getSupportedValue(List<String> values){
    	if (values == null) {
    		return Collections.emptyList();
    	}

    	List<ScanResolution> list = new ArrayList<ScanResolution>();
        for(String value : values){
            ScanResolution resolution = fromString(value);
            if (resolution != null) {
                list.add(resolution);
            }
        }
        return list;
    }

}
