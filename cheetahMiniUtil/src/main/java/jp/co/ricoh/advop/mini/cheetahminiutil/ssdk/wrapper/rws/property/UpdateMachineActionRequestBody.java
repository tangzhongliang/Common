/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

/*
 * @since SmartSDK V2.12
 */
public class UpdateMachineActionRequestBody extends WritableElement implements RequestBody {

    private static final String CONTENT_TYPE_JSON               = "application/json; charset=utf-8";

	private static final String KEY_MACHINE_ACTION_WHEN_LIMIT_IS_REACHED = "machineActionWhenLimitIsReached";
    
	//ログ出力箇所を一意に識別できるPREFIXを設定（パッケージ名:クラス名）
	private final static String PREFIX = "property:UpdateMacActReq:";
	
    public UpdateMachineActionRequestBody() {
        super(new HashMap<String, Object>());
    }
    public UpdateMachineActionRequestBody(Map<String, Object> values) {
        super(values);
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
        	//Loggerクラスのメソッド呼び出し
            return "{}";
        }
    }

    /*
     * machineActionWhenLimitIsReached (String)
     * @since SmartSDK V2.12
     */
    public String getMachineActionWhenLimitIsReached() {
    	return getStringValue(KEY_MACHINE_ACTION_WHEN_LIMIT_IS_REACHED);
    }
    public void setMachineActionWhenLimitIsReached(String value) {
    	setStringValue(KEY_MACHINE_ACTION_WHEN_LIMIT_IS_REACHED, value);
    }
    public String removeMachineActionWhenLimitIsReached() {
    	return removeStringValue(KEY_MACHINE_ACTION_WHEN_LIMIT_IS_REACHED);
    }
}