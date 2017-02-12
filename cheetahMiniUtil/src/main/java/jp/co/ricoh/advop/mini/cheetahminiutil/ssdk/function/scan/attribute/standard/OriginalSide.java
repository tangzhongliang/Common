/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OriginalSide implements ScanRequestAttribute {

	ONE_SIDE("one_sided"),
	TOP_TO_TOP("top_to_top"),
	TOP_TO_BOTTOM("top_to_bottom"),
	SPREAD("spread");

	private static final String ORIGINAL_SIDE = "originalSide";

	/**
	 * 内部保持する原稿面
	 * Document side to store internally
	 */
	private final String originalSide;

	/**
	 * 指定した原稿面で列挙型を構築する
	 * Builds enum type with the specified document side
	 * @param side
	 */
	private OriginalSide(String side) {
		this.originalSide = side;
	}

	@Override
	public Class<?> getCategory() {
		return OriginalSide.class;
	}

	@Override
	public String getName() {
		return ORIGINAL_SIDE;
	}

	@Override
	public Object getValue() {
		return this.originalSide;
	}

    private static volatile Map<String, OriginalSide> directory = null;

    private static Map<String, OriginalSide> getDirectory(){
        if( directory == null ) {
            Map<String, OriginalSide> d = new HashMap<String, OriginalSide>();
            for(OriginalSide side : values() ){
                d.put(side.getValue().toString(), side);
            }
            directory = d;
        }
        return directory;
    }

	private static OriginalSide fromString(String value) {
	    return getDirectory().get(value);
	}

	public static List<OriginalSide> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<OriginalSide> list = new ArrayList<OriginalSide>();
		for(String value : values){
			OriginalSide side = fromString(value);
			if( side != null ){
				list.add(fromString(value));
			}
		}
		return list;
	}
}
