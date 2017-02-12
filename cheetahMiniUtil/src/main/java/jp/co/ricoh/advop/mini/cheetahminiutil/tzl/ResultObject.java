/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.text.TextUtils;

import java.util.ArrayList;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;


public class ResultObject {
    public static final int RET_INIT = -1;
    public static final int RET_SUCCESS = 1;
    public static final int RET_FAIL = 0;
    public int stateCode = RET_INIT;
    public String title = "";
    private ArrayList<ResultMessage> messages;

    public ResultObject() {
    }

    public ResultObject(String title) {
        this.title = title;
    }

    public void append(Object error) {
        if (error != null && !TextUtils.isEmpty(error.toString())) {
            if (messages == null) {
                messages = new ArrayList<ResultMessage>();
            }
            if (error.toString().contains("reason")) {
                addMessage(new ResultMessage(false, error.toString()));
            } else if (error.toString().contains("fail")) {
                addMessage(new ResultMessage(false, error.toString()));
            } else {
                addMessage(new ResultMessage(true, error.toString()));
            }
        }
    }

    public void append(String errorMessage, String reason, boolean state) {
        if (errorMessage != null && !TextUtils.isEmpty(errorMessage.toString())) {
            if (messages == null) {
                messages = new ArrayList<ResultMessage>();
            }
            addMessage(new ResultMessage(state, reason, errorMessage.toString()));
        }
    }

    private boolean addMessage(ResultMessage object) {
        if (!object.state) {
            stateCode = RET_FAIL;
        } else if (stateCode == RET_INIT) {
            stateCode = RET_SUCCESS;
        }
        return messages.add(object);
    }

    public String getMessage() {
        StringBuffer ret = new StringBuffer();
        if (messages == null)
            return ret.toString();
        for (ResultMessage message : messages) {
            ret.append(message + "\n");
        }
        if (ret.length() > 0)
            ret.deleteCharAt(ret.length() - 1);
        return ret.toString();
    }

    public static ResultObject[] plus(ResultObject... args) {
        return args;
    }

    public static ResultObject[] plus(ResultObject[] array, ResultObject... args) {
        if (array == null) {
            return args;
        }
        ResultObject[] resultObjects = new ResultObject[array.length + args.length];
        int i = 0;
        for (; i < array.length; i++) {
            resultObjects[i] = array[i];
        }
        for (; i < array.length + args.length; i++) {
            resultObjects[i] = args[i - array.length];
        }
        return resultObjects;
    }

    public static String getMessage(ResultObject[] arrays) {
        StringBuffer stringBuffer = new StringBuffer();
        for (ResultObject array : arrays) {
            if (array == null || TextUtils.isEmpty(array.getMessage())) {
                continue;
            }
            if (array.title != null) {
                stringBuffer.append(array.title);
            }
            if (array.getStateCode() == RET_SUCCESS) {
                stringBuffer.append("   " + CHolder.instance().getApp().getString(R.string.success) + "\n\n");
            } else {
                stringBuffer.append("   " + CHolder.instance().getApp().getString(R.string.fail) + "\n        "+CHolder.instance().getApp().getString(R.string.reason)+"  [" + array.getErrorMessage() + "]\n");
            }
        }
        if (stringBuffer.length() > 0)
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    public int getStateCode() {
        return stateCode;
    }

    public static int getStateCode(ResultObject[] arrays) {
        int ret = RET_SUCCESS;
        for (ResultObject array : arrays) {
            if (array == null) {
                continue;
            }
            if (array.getStateCode() == RET_FAIL) {
                ret = RET_FAIL;
                break;
            }
        }
        return ret;
    }

    public String getErrorMessage() {
        StringBuffer ret = new StringBuffer();
        if (messages == null)
            return ret.toString();
        for (ResultMessage message : messages) {
            if (!message.state) {
                ret.append(message + "\n");
            }
        }
        if (ret.length() > 0)
            ret.deleteCharAt(ret.length() - 1);
        return ret.toString();
    }

    public String getErrorReason() {
        StringBuffer ret = new StringBuffer();
        if (messages == null)
            return ret.toString();
        for (ResultMessage message : messages) {
            if (!TextUtils.isEmpty(message.reason)) {
                ret.append(message.reason + "\n");
            }
        }
        if (ret.length() > 0)
            ret.deleteCharAt(ret.length() - 1);
        return ret.toString();
    }

    public void append(String errorMessage, boolean b) {
        if (errorMessage != null && !TextUtils.isEmpty(errorMessage.toString())) {
            if (messages == null) {
                messages = new ArrayList<ResultMessage>();
            }
            addMessage(new ResultMessage(b, errorMessage.toString(), null));
        }
    }

    class ResultMessage {

        boolean state;
        String message;
        String reason;

        public ResultMessage(boolean state, String message) {
            this.state = state;
            this.message = message;
        }

        public ResultMessage(boolean state, String message, String reason) {
            this.state = state;
            this.message = message;
            this.reason = reason;
        }

        @Override
        public String toString() {
            return message == null ? "" : (reason == null ? message : message + reason);
        }
    }
}
