/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * StoreLocalSetting
 * @since SmartSDK V1.01
 */
public class StoreLocalSetting implements ScanRequestAttribute {

    public static final String FOLDER_ID_SHARED_FOLDER   = "shared_folder";

    private static final String NAME_STORE_LOCAL_SETTING = "storeLocalSetting";

    private static final String NAME_FOLDER_ID           = "folderId";
    private static final String NAME_FOLDER_PASSWORD     = "folderPassword";
    private static final String NAME_FILE_NAME           = "fileName";
    private static final String NAME_FILE_PASSWORD       = "filePassword";


    private String folderId;
    private String folderPassword;
    private String fileName;
    private String filePassword;

    public StoreLocalSetting() {
        folderId = null;
        folderPassword = null;
        fileName = null;
        filePassword = null;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFolderPassword() {
        return folderPassword;
    }

    public void setFolderPassword(String folderPassword) {
        this.folderPassword = folderPassword;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePassword() {
        return filePassword;
    }

    public void setFilePassword(String filePassword) {
        this.filePassword = filePassword;
    }

    @Override
    public Class<?> getCategory() {
        return StoreLocalSetting.class;
    }

    @Override
    public String getName() {
        return NAME_STORE_LOCAL_SETTING;
    }

    @Override
    public Object getValue() {
        Map<String, Object> values = new HashMap<String, Object>();

        if (folderId!=null) {
            values.put(NAME_FOLDER_ID, folderId);
        }
        if (folderPassword!=null) {
            values.put(NAME_FOLDER_PASSWORD, folderPassword);
        }
        if (fileName!=null) {
            values.put(NAME_FILE_NAME, fileName);
        }
        if (filePassword!=null) {
            values.put(NAME_FILE_PASSWORD, filePassword);
        }

        return values;
    }

}
