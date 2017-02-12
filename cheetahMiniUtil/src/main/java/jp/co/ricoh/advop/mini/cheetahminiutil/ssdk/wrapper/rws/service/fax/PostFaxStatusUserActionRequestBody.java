/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

/*
 * @since SmartSDK V2.00
 */
public class PostFaxStatusUserActionRequestBody extends WritableElement implements RequestBody {

    private static final String CONTENT_TYPE_JSON   = "application/json; charset=utf-8";
    private static final String KEY_USER_ACTION     = "userAction";

    public PostFaxStatusUserActionRequestBody() {
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
     * userAction (Object)
     * @since SmartSDK V2.00
     */
    public UserAction getUserAction() {
        Map<String, Object> mapValue = getObjectValue(KEY_USER_ACTION);
        if (mapValue == null) {
            mapValue = Utils.createElementMap();
            setObjectValue(KEY_USER_ACTION, mapValue);
        }
        return new UserAction(mapValue);
    }
    public UserAction removeUserAction() {
        Map<String, Object> mapValue = removeObjectValue(KEY_USER_ACTION);
        if (mapValue == null) {
            return null;
        }
        return new UserAction(mapValue);
    }


    /*
     * @since SmartSDK V2.00
     */
    public class UserAction extends WritableElement {

        private static final String KEY_MESSAGE = "message";
        private static final String KEY_ACTION  = "action";

        UserAction(Map<String, Object> values) {
            super(values);
        }

        /*
         * message (String)
         * @since SmartSDK V2.00
         */
        public String getMessage() {
            return getStringValue(KEY_MESSAGE);
        }
        public void setMessage(String value) {
            setStringValue(KEY_MESSAGE, value);
        }
        public String removeMessage() {
            return removeStringValue(KEY_MESSAGE);
        }

        /*
         * action (String)
         */
        public String getAction() {
            return getStringValue(KEY_ACTION);
        }
        public void setAction(String value) {
            setStringValue(KEY_ACTION, value);
        }
        public String removeAction() {
            return removeStringValue(KEY_ACTION);
        }

    }

}
