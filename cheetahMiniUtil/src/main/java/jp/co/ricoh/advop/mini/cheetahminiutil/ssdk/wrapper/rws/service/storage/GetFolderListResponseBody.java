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
public class GetFolderListResponseBody extends Element implements ResponseBody {

    private static final String KEY_PASS_NUM       = "passNum";
    private static final String KEY_ACQUIRED_NUM   = "acquiredNum";
    private static final String KEY_FOLDER_LIST    = "folderList";
    private static final String KEY_NEXT_LINK      = "nextLink";

    GetFolderListResponseBody(Map<String, Object> values) {
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
     * folderList (Array[Object])
     * @since SmartSDK V1.01
     */
    public FolderListArray getFolderList() {
        List<Map<String, Object>> value = getArrayValue(KEY_FOLDER_LIST);
        if (value == null) {
            return null;
        }
        return new FolderListArray(value);
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
    public static class FolderListArray extends ArrayElement<FolderList> {

        FolderListArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected FolderList createElement(Map<String, Object> values) {
            return new FolderList(values);
        }

    }

    /*
     * @since SmartSDK V1.01
     */
    public static class FolderList extends Element {

        private static final String KEY_FOLDER_ID           = "folderId";
        private static final String KEY_FOLDER_NAME         = "folderName";
        private static final String KEY_CREATED_DATE_TIME   = "createdDateTime";
        private static final String KEY_IS_SET_PASSWORD     = "isSetPassword";
        private static final String KEY_IS_FOLDER_LOCKED    = "isFolderLocked";
        private static final String KEY_FILE_NUM            = "fileNum";

        FolderList(Map<String, Object> values) {
            super(values);
        }

        /*
         * folderId (String)
         * @since SmartSDK V1.01
         */
        public String getFolderId() {
            return getStringValue(KEY_FOLDER_ID);
        }

        /*
         * folderName (String)
         * @since SmartSDK V1.01
         */
        public String getFolderName() {
            return getStringValue(KEY_FOLDER_NAME);
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
         * isFolderLocked (Boolean)
         * @since SmartSDK V1.01
         */
        public Boolean isFolderLocked() {
            return getBooleanValue(KEY_IS_FOLDER_LOCKED);
        }

        /*
         * fileNum (Number)
         * @since SmartSDK V1.01
         */
        public Integer getFileNum() {
            return getNumberValue(KEY_FILE_NUM);
        }

    }

}
