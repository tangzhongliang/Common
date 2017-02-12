/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import java.util.HashMap;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

public class UpdateStandbyFileRequestBody extends WritableElement implements RequestBody  {

    private static final String CONTENT_TYPE_JSON   = "application/json; charset=utf-8";

    private static final String KEY_TIME            = "time";

    public UpdateStandbyFileRequestBody() {
        super(new HashMap<String, Object>());
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE_JSON;
    }

    @Override
    public String toEntityString() {
        try {
            return JsonUtils.getEncoder().encode(values);
        } catch (EncodedException e) {
            LogC.w(e);
            return "{}";
        }
    }


    /*
     * time (String)
     */
    public String getTime() {
        return getStringValue(KEY_TIME);
    }
    public void setTime(String value) {
        setStringValue(KEY_TIME, value);
    }
    public String removeTime() {
        return removeStringValue(KEY_TIME);
    }

}
