/*
 *  Copyright (C) 2014-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.storage;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

/*
 * @since SmartSDK V1.01
 */
public class GetFileResponseBody extends Element implements ResponseBody {

    private static final String KEY_FILE_ID         = "fileId";
    private static final String KEY_FILE_NAME       = "fileName";
    private static final String KEY_FILE_INFO       = "fileInfo";
    private static final String KEY_OCR_INFO        = "ocrInfo";
    private static final String KEY_ACL_INFO        = "aclInfo";
    private static final String KEY_EXTRA_INFO      = "extraInfo";

    GetFileResponseBody(Map<String, Object> values) {
        super(values);
    }

    /*
     * fileId (String)
     * @since SmartSDK V1.01
     */
    public String getFileId() {
        return getStringValue(KEY_FILE_ID);
    }

    /*
     * fileName (String)
     * @since SmartSDK V1.01
     */
    public String getFileName() {
        return getStringValue(KEY_FILE_NAME);
    }

    /*
     * fileInfo (Object)
     * @since SmartSDK V1.01
     */
    public FileInfo getFileInfo() {
        Map<String, Object> value = getObjectValue(KEY_FILE_INFO);
        if (value == null) {
            return null;
        }
        return new FileInfo(value);
    }

    /*
     * ocrInfo (Object)
     * @since SmartSDK V1.01
     */
    public OcrInfo getOcrInfo() {
        Map<String, Object> value = getObjectValue(KEY_OCR_INFO);
        if (value == null) {
            return null;
        }
        return new OcrInfo(value);
    }

    /*
     * aclInfo (Array[Object])
     * @since SmartSDK V1.01
     */
    public AclInfoArray getAclInfo() {
        List<Map<String, Object>> value = getArrayValue(KEY_ACL_INFO);
        if (value == null) {
            return null;
        }
        return new AclInfoArray(value);
    }

    /*
     * extraInfo (Object)
     * @since SmartSDK V1.01
     */
    public ExtraInfo getExtraInfo() {
        Map<String, Object> value = getObjectValue(KEY_EXTRA_INFO);
        if (value == null) {
            return null;
        }
        return new ExtraInfo(value);
    }


    /*
     * @since SmartSDK V1.01
     */
    public static class FileInfo extends Element {

        private static final String KEY_USER_NAME           = "userName";
        private static final String KEY_CREATED_DATE_TIME   = "createdDateTime";
        private static final String KEY_TOTAL_PAGE_NUMBER   = "totalPageNumber";
        private static final String KEY_POSSIBLE_OPERATION  = "possibleOperation";
        private static final String KEY_EXPIRATION_DATE     = "expirationDate";
        private static final String KEY_IS_SET_PASSWORD     = "isSetPassword";
        private static final String KEY_IS_SECURITY_LOCKED  = "isSecurityLocked";
        private static final String KEY_OWNER_ENTRY_ID   	= "ownerEntryId"; // SmartSDK V2.10

        FileInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * userName (String)
         * @since SmartSDK V1.01
         */
        public String getUserName() {
            return getStringValue(KEY_USER_NAME);
        }
        
        /*
         * createdDateTime (String)
         * @since SmartSDK V1.01
         */
        public String getCreatedDateTime() {
            return getStringValue(KEY_CREATED_DATE_TIME);
        }

        /*
         * totalPageNumber (Number)
         * @since SmartSDK V1.01
         */
        public Integer getTotalPageNumber() {
            return getNumberValue(KEY_TOTAL_PAGE_NUMBER);
        }

        /*
         * possibleOperation (Array[String])
         * @since SmartSDK V1.01
         */
        public List<String> getPossibleOperation() {
            return getArrayValue(KEY_POSSIBLE_OPERATION);
        }

        /*
         * expirationDate (String)
         * @since SmartSDK V1.01
         */
        public String getExpirationDate() {
            return getStringValue(KEY_EXPIRATION_DATE);
        }

        /*
         * isSetPassword (Boolean)
         * @since SmartSDK V1.01
         */
        public Boolean isSetPassword() {
            return getBooleanValue(KEY_IS_SET_PASSWORD);
        }

        /*
         * isSecurityLocked (Boolean)
         * @since SmartSDK V1.01
         */
        public Boolean isSecurityLocked() {
            return getBooleanValue(KEY_IS_SECURITY_LOCKED);
        }

        /*
         * ownerEntryId (String)
         * @since SmartSDK V2.10
         */
        public String getOwnerEntryId() {
        	return getStringValue(KEY_OWNER_ENTRY_ID);
        }
    }

    /*
     * @since SmartSDK V1.01
     */
    public static class OcrInfo extends Element {

        private static final String KEY_IS_OCRED        = "isOcred";
        private static final String KEY_OCR_LANGUAGE    = "ocrLanguage";
        
        OcrInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * isOcred (Boolean)
         * @since SmartSDK V1.01
         */
        public Boolean isOcred() {
            return getBooleanValue(KEY_IS_OCRED);
        }

        /*
         * ocrLanguage (String)
         * @since SmartSDK V1.01
         */
        public String getOcrLanguage() {
            return getStringValue(KEY_OCR_LANGUAGE);
        }

    }

    /*
     * @since SmartSDK v1.01
     */
    public static class AclInfoArray extends ArrayElement<AclInfo> {

        AclInfoArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected AclInfo createElement(Map<String, Object> values) {
            return new AclInfo(values);
        }

    }

    /*
     * @since SmartSDK v1.01
     */
    public static class AclInfo extends Element {

        private static final String KEY_ENTRY_ID        = "entryId";
        private static final String KEY_AUTHORITY       = "authority";

        AclInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * entryId (String)
         * @since SmartSDK V1.01
         */
        public String getEntryId() {
            return getStringValue(KEY_ENTRY_ID);
        }

        /*
         * authority (String)
         * @since SmartSDK V1.01
         */
        public String getAuthority() {
            return getStringValue(KEY_AUTHORITY);
        }

    }

    /*
     * @since SmartSDK V1.01
     */
    public static class ExtraInfo extends Element {

        private static final String KEY_KIND        = "kind";

        ExtraInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * kind (String)
         * @since SmartSDK V1.01
         */
        public String getKind() {
            return getStringValue(KEY_KIND);
        }

        /*
         * only kind="fax"
         * @since SmartSDK V1.01
         */
        public ExtraInfoFax getFax() {
        	if ("fax".equals(getKind())) {
                return new ExtraInfoFax(values);
        	}
        	return null;
        }

    }

    /*
     * @since SmartSDK V1.01
     */
    public static class ExtraInfoFax extends Element {

        private static final String KEY_PORT            = "port";
        private static final String KEY_RTI_CSI         = "rtiCsi";
        private static final String KEY_CODE            = "code";
        private static final String KEY_JOB_LOG_ID      = "jobLogId";
        private static final String KEY_TIME            = "time";
        private static final String KEY_MAIL_SUBJECT    = "mailSubject";
        private static final String KEY_NUM_DISP        = "numDisp";

        ExtraInfoFax(Map<String, Object> values) {
            super(values);
        }

        /*
         * port (String)
         * @since SmartSDK V1.01
         */
        public String getPort() {
            return getStringValue(KEY_PORT);
        }

        /*
         * rtiCsi (String)
         * @since SmartSDK V1.01
         */
        public String getRtiCsi() {
            return getStringValue(KEY_RTI_CSI);
        }

        /*
         * code (Object)
         * @since SmartSDK V1.01
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
         * @since SmartSDK V1.01
         */
        public Integer getJobLogId() {
            return getNumberValue(KEY_JOB_LOG_ID);
        }

        /*
         * time (String)
         * @since SmartSDK V1.01
         */
        public String getTime() {
            return getStringValue(KEY_TIME);
        }

        /*
         * mailSubject (String)
         * @since SmartSDK V1.01
         */
        public String getMailSubject() {
            return getStringValue(KEY_MAIL_SUBJECT);
        }

        /*
         * numDisp (String)
         * @since SmartSDK V1.01
         */
        public String getNumDisp() {
            return getStringValue(KEY_NUM_DISP);
        }

        /*
         * @since SmartSDK V1.01
         */
        public static class Code extends Element {

            private static final String KEY_TYPE        = "type";
            private static final String KEY_INFO        = "info";

            Code(Map<String, Object> values) {
                super(values);
            }

            /*
             * type (String)
             * @since SmartSDK V1.01
             */
            public String getType() {
                return getStringValue(KEY_TYPE);
            }

            /*
             * info (String)
             * @since SmartSDK V1.01
             */
            public String getInfo() {
                return getStringValue(KEY_INFO);
            }

        }

    }

}
