/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.storage;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

/*
 * @since SmartSDK V1.01
 */
public class GetFolderResponseBody extends Element implements ResponseBody {

    private static final String KEY_FOLDER_NAME         = "folderName";
    private static final String KEY_IS_SET_PASSWORD     = "isSetPassword";
    private static final String KEY_CREATED_DATE_TIME   = "createdDateTime";
    private static final String KEY_FILE_NUM            = "fileNum";
    private static final String KEY_IS_FOLDER_LOCKED    = "isFolderLocked";

    GetFolderResponseBody(Map<String, Object> values) {
        super(values);
    }

    /*
     * folderName (String)
     * @since SmartSDK V1.01
     */
    public String getFolderName() {
        return getStringValue(KEY_FOLDER_NAME);
    }

    /*
     * isSetPassword (Boolean)
     * @since SmartSDK V1.01
     */
    public Boolean isSetPassword() {
        return getBooleanValue(KEY_IS_SET_PASSWORD);
    }

    /*
     * createdDateTime (String)
     * @since SmartSDK V1.01
     */
    public String getCreatedDateTime() {
        return getStringValue(KEY_CREATED_DATE_TIME);
    }

    /*
     * fileNum (Number)
     * @since SmartSDK V1.01
     */
    public Integer getFileNum() {
        return getNumberValue(KEY_FILE_NUM);
    }

    /*
     * isFolderLocked (Boolean)
     * @since SmartSDK V1.01
     */
    public Boolean isFolderLocked() {
        return getBooleanValue(KEY_IS_FOLDER_LOCKED);
    }

}
