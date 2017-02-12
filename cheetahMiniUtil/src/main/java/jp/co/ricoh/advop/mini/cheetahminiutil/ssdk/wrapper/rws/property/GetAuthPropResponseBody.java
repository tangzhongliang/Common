/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

import java.util.Map;

public class GetAuthPropResponseBody extends Element implements ResponseBody {

    private static final String KEY_SETTINGS  = "settings";

    public GetAuthPropResponseBody(Map<String, Object> values) {
        super(values);
    }
    
    public Settings getSettings() {
        Map<String, Object> value = getObjectValue(KEY_SETTINGS);
        if (value == null) {
            return null;
        }
        return new Settings(value);
    }
    
    public boolean isOptionalCounterExist() {
        Settings settings = getSettings();
        if (settings == null) {
            return false;
        }
        
        String optionalCounterType = settings.getOptionalCounterType();
        if (CUtil.isStringEmpty(optionalCounterType)) {
            return false;
        }
        
        // OptionalCounterTypeの値を取得し、
        // 値が"none"以外であれば外部課金装置が接続（設定）されていると判断する。
        if (optionalCounterType.trim().equalsIgnoreCase("none")) {
            return false;
        }
        
        return true;
    }


    public static class Settings extends Element {
        
        private static final String KEY_OPTIONAL_COUNTER_TYPE = "OptionalCounterType";
        
        Settings(Map<String, Object> values) {
            super(values);
        }

        public String getOptionalCounterType() {
            return getStringValue(KEY_OPTIONAL_COUNTER_TYPE);
        }
    }
}
