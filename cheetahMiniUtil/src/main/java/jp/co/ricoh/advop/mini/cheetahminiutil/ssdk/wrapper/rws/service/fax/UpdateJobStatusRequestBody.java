/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
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

public class UpdateJobStatusRequestBody extends WritableElement implements RequestBody {

    private static final String CONTENT_TYPE_JSON   = "application/json; charset=utf-8";

    private static final String KEY_JOB_STATUS      = "jobStatus";
    private static final String KEY_VALIDATE_ONLY   = "validateOnly";
    private static final String KEY_JOB_SETTING     = "jobSetting";

    public UpdateJobStatusRequestBody() {
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
     * jobStatus (String)
     */
    public String getJobStatus() {
        return getStringValue(KEY_JOB_STATUS);
    }
    public void setJobStatus(String value) {
        setStringValue(KEY_JOB_STATUS, value);
    }
    public String removeJobStatus() {
        return removeStringValue(KEY_JOB_STATUS);
    }

    /*
     * validateOnly (Boolean)
     */
    public Boolean getValidateOnly() {
        return getBooleanValue(KEY_VALIDATE_ONLY);
    }
    public void setValidateOnly(Boolean value) {
        setBooleanValue(KEY_VALIDATE_ONLY, value);
    }
    public Boolean removeValidateOnly() {
        return removeBooleanValue(KEY_VALIDATE_ONLY);
    }

    /*
     * jobSetting (Object)
     */
    public JobSetting getJobSetting() {
        Map<String, Object> value = getObjectValue(KEY_JOB_SETTING);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_JOB_SETTING, value);
        }
        return new JobSetting(value);
    }
//  public void setJobSetting(JobSetting value) {
//      throw new UnsupportedOperationException();
//  }
    public JobSetting removeJobSetting() {
        Map<String, Object> value = removeObjectValue(KEY_JOB_SETTING);
        if (value == null) {
            return null;
        }
        return new JobSetting(value);
    }

}
