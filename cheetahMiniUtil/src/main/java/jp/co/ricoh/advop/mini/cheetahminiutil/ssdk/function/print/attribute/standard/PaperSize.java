/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.MediaSizeName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import android.util.Log;

/**
 * 印刷カラーを示すクラスです。 The class to indicate print side mode.
 */
public class PaperSize implements PrintRequestAttribute {

	private static final String TAG = "PaperSize";
	private final String PAPER_SIZE = "paperSize";
	private String mPaperSize;
	private MediaSizeName mSizeName;

	public PaperSize(MediaSizeName val) {
		LogC.d(TAG, "PaperSize >> " + val);
		this.mPaperSize = val.getValue();
		this.mSizeName = val;
	}

	@Override
	public Object getValue() {
		LogC.d(TAG, "getValue >> " + mPaperSize);
		return mPaperSize;
	}

	@Override
	public Class<?> getCategory() {
		return getClass();
	}

	@Override
	public String getName() {
		return PAPER_SIZE;
	}

	@Override
	public String toString() {
		return mSizeName.getValue();
	}

	private static volatile Map<String, PaperSize> directory = null;

	private static Map<String, PaperSize> getDirectory() {
		if (directory == null) {
			Map<String, PaperSize> d = new HashMap<String, PaperSize>();
			for (MediaSizeName size : MediaSizeName.values()) {
				d.put(size.getValue(), new PaperSize(size));
			}
			directory = d;
		}
		return directory;
	}

	public static PaperSize fromString(String value) {
		return getDirectory().get(value);
	}

	public static List<PaperSize> getSupportedValue(List<String> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<PaperSize> list = new ArrayList<PaperSize>();
		for (String value : values) {
			LogC.d(TAG, "supported paper size === " + String.valueOf(value));
			PaperSize size = fromString(String.valueOf(value));
			if (size != null) {
				list.add(size);
			}
		}

		return list;
	}
}
