/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * ジョブ実行時に組み合わせで排他になる設定を自動的に補正するかを決める属性です。
 * The attribute to determine to automatically correct conflicting job settings at the time the job is started.
 */
public enum AutoCorrectJobSetting implements ScanRequestAttribute {
	AUTO_CORRECT_ON(true),
	AUTO_CORRECT_OFF(false);

	private final static String SCAN_AUTO_CORRECT_JOB_SETTING = "autoCorrectJobSetting";

	private Boolean isAutoCorrectJobSetting = null;

	private AutoCorrectJobSetting(boolean autoCorrectJobSetting){
		this.isAutoCorrectJobSetting = autoCorrectJobSetting;
	}

	public void setAutoCorrectJobSetting(boolean autoCorrectJobSetting) {
		this.isAutoCorrectJobSetting = autoCorrectJobSetting;
	}

	public boolean isAutoCorrectJobSetting(){
		return this.isAutoCorrectJobSetting;
	}

	@Override
	public Class<?> getCategory() {
		return AutoCorrectJobSetting.class;
	}

	@Override
	public String getName() {
		return SCAN_AUTO_CORRECT_JOB_SETTING;
	}

	@Override
	public Object getValue() {
		return this.isAutoCorrectJobSetting();
	}


	public static List<AutoCorrectJobSetting> getSupportedValue(List<Boolean> values) {
		if (values == null) {
			return Collections.emptyList();
		}

		List<AutoCorrectJobSetting> list = new ArrayList<AutoCorrectJobSetting>();
		for(Boolean value : values ) {
			list.add(value ? AUTO_CORRECT_ON : AUTO_CORRECT_OFF );
		}
		return list;
	}
}
