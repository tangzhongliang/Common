/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
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
public class GetFileListResponseBody extends Element implements ResponseBody {

    private static final String KEY_PASS_NUM       = "passNum";
    private static final String KEY_ACQUIRED_NUM   = "acquiredNum";
    private static final String KEY_FILE_LIST      = "fileList";
    private static final String KEY_NEXT_LINK      = "nextLink";

    GetFileListResponseBody(Map<String, Object> values) {
        super(values);
    }

    /*
     * passNum (Number)
     * @since SmartSDK V1.01
     */
    public Integer getPassNum() {
        return getNumberValue(KEY_PASS_NUM);
    }

    /*
     * acquiredNum (Number)
     * @since SmartSDK V1.01
     */
    public Integer getAcquiredNum() {
        return getNumberValue(KEY_ACQUIRED_NUM);
    }

    /*
     * fileList (Array[Object])
     * @since SmartSDK V1.01
     */
    public FileListArray getFileList() {
        List<Map<String, Object>> value = getArrayValue(KEY_FILE_LIST);
        if (value == null) {
            return null;
        }
        return new FileListArray(value);
    }

    /*
     * nextLink (String)
     * @since SmartSDK V1.01
     */
    public String getNextLink() {
        return getStringValue(KEY_NEXT_LINK);
    }


    /*
     * @since SmartSDK V1.01
     */
    public static class FileListArray extends ArrayElement<FileList> {

        FileListArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected FileList createElement(Map<String, Object> values) {
            return new FileList(values);
        }

    }

    /*
     * @since SmartSDK V1.01
     */
    public static class FileList extends Element {

        private static final String KEY_FILE_ID             = "fileId";
        private static final String KEY_OPERATION_TYPE      = "operationType";
        private static final String KEY_FILE_NAME           = "fileName";
        private static final String KEY_USER_NAME           = "userName";
        private static final String KEY_CREATED_DATE_TIME   = "createdDateTime";
        private static final String KEY_IS_SET_PASSWORD     = "isSetPassword";
        private static final String KEY_IS_FILE_LOCKED      = "isFileLocked";
        private static final String KEY_PAGE_NUM            = "pageNum";

        FileList(Map<String, Object> values) {
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
         * operationType (Array[String])
         * @since SmartSDK V1.01
         */
        public List<String> getOperationType() {
            return getArrayValue(KEY_OPERATION_TYPE);
        }

        /*
         * fileName (String)
         * @since SmartSDK V1.01
         */
        public String getFileName() {
            return getStringValue(KEY_FILE_NAME);
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
         * isSetPassword (Boolean)
         * @since SmartSDK V1.01
         */
        public Boolean isSetPassword() {
            return getBooleanValue(KEY_IS_SET_PASSWORD);
        }

        /*
         * isFileLocked (Boolean)
         * @since SmartSDK V1.01
         */
        public Boolean isFileLocked() {
            return getBooleanValue(KEY_IS_FILE_LOCKED);
        }

        /*
         * pageNum (Number)
         * @since SmartSDK V1.01
         */
        public Integer getPageNum() {
            return getNumberValue(KEY_PAGE_NUM);
        }

    }

}
