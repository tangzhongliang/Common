/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public enum OriginalPreview implements ScanRequestAttribute {

	ON(true),
	OFF(false);

	private static final String ORIGINAL_PREVIEW = "originalPreview";

	private final boolean value;

	private OriginalPreview(boolean value){
		this.value = value;

	}
	@Override
	public Class<?> getCategory() {
		return OriginalPreview.class;
	}

	@Override
	public String getName() {
		return ORIGINAL_PREVIEW;
	}

	@Override
	public Object getValue() {
		return Boolean.valueOf(value);
	}

	private static OriginalPreview fromBoolean(Boolean value) {
		for(OriginalPreview preview : values()){
			if(preview.getValue().equals(value)) return preview;
		}
		return null;
	}

	public static List<OriginalPreview> getSupportedValue(List<Boolean> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<OriginalPreview> list = new ArrayList<OriginalPreview>();
		for(Boolean value : values) {
			OriginalPreview preview = fromBoolean(value);
			if( preview != null ) {
				list.add(fromBoolean(value));
			}
		}
		return list;
	}
}
