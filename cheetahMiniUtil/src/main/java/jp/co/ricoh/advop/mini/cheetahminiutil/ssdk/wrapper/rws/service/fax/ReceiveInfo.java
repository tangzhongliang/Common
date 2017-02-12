/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;

public class ReceiveInfo extends Element {

    private static final String KEY_PORT            = "port";
    private static final String KEY_RTI_CSI         = "rtiCsi";
    private static final String KEY_CODE            = "code";
    private static final String KEY_JOB_LOG_ID      = "jobLogId";
    private static final String KEY_FILE_ID         = "fileId";
    private static final String KEY_TIME            = "time";
    private static final String KEY_MAIL_SUBJECT    = "mailSubject";
    private static final String KEY_NUM_DISP        = "numDisp";
    private static final String KEY_SAVE_LOCATION   = "saveLocation";


    public ReceiveInfo(Map<String, Object> values) {
        super(values);
    }

    /*
     * port (String)
     */
    public String getPort() {
        return getStringValue(KEY_PORT);
    }

    /*
     * rtiCsi (String)
     */
    public String getRtiCsi() {
        return getStringValue(KEY_RTI_CSI);
    }

    /*
     * code (Object)
     */
    public Code getCode() {
        Map<String, Object> value = getObjectValue(KEY_CODE);
        if (value == null) {
            return null;
        }
        return new Code(value);
    }

    /*
     * jobLogId (Number)
     */
    public Integer getJobLogId() {
        return getNumberValue(KEY_JOB_LOG_ID);
    }

    /*
     * fileId (String)
     */
    public String getFileId() {
        return getStringValue(KEY_FILE_ID);
    }

    /*
     * time (String)
     */
    public String getTime() {
        return getStringValue(KEY_TIME);
    }

    /*
     * mailSubject (String)
     */
    public String getMailSubject() {
        return getStringValue(KEY_MAIL_SUBJECT);
    }

    /*
     * numDisp (String)
     */
    public String getNumDisp() {
        return getStringValue(KEY_NUM_DISP);
    }

    /*
     * saveLocation (String)
     */
    public String getSaveLocation() {
        return getStringValue(KEY_SAVE_LOCATION);
    }


    public static class Code extends Element {

        private static final String KEY_TYPE        = "type";
        private static final String KEY_INFO        = "info";

        Code(Map<String, Object> values) {
            super(values);
        }

        /*
         * type (String)
         */
        public String getType() {
            return getStringValue(KEY_TYPE);
        }

        /*
         * info (String)
         */
        public String getInfo() {
            return getStringValue(KEY_INFO);
        }

    }

}
