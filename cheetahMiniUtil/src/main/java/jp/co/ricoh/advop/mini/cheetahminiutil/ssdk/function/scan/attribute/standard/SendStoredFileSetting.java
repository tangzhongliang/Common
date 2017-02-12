/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SendStoredFileSetting
 * @since SmartSDK V1.01
 */
public class SendStoredFileSetting implements ScanRequestAttribute {

    public static final String FOLDER_ID_SHARED_FOLDER          = "shared_folder";

    private static final String NAME_SEND_STORED_FILE_SETTING   = "sendStoredFileSetting";

    private static final String NAME_FOLDER_INFO                = "folderInfo";
    private static final String NAME_STORED_FILE_INFO           = "storedFileInfo";

    private FolderInfo folderInfo;
    private final List<StoredFileInfo> storedFileInfoList;

    public SendStoredFileSetting() {
        folderInfo = null;
        storedFileInfoList = new ArrayList<StoredFileInfo>();
    }

    public FolderInfo getFolderInfo() {
        return folderInfo;
    }

    public void setFolderInfo(FolderInfo folderInfo) {
        this.folderInfo = folderInfo;
    }

    public int getStoredFileInfoSize() {
        return storedFileInfoList.size();
    }

    public StoredFileInfo getStoredFileInfo(int index) {
        return storedFileInfoList.get(index);
    }

    public boolean addStoredFileInfo(StoredFileInfo info) {
        return storedFileInfoList.add(info);
    }

    public StoredFileInfo removeStoredFileInfo(int index) {
        return storedFileInfoList.remove(index);
    }

    public void clearStoredFileInfo() {
        storedFileInfoList.clear();
    }

    @Override
    public Class<?> getCategory() {
        return SendStoredFileSetting.class;
    }

    @Override
    public String getName() {
        return NAME_SEND_STORED_FILE_SETTING;
    }

    @Override
    public Object getValue() {
        Map<String, Object> values = new HashMap<String, Object>();

        if (folderInfo != null) {
            values.put(NAME_FOLDER_INFO, folderInfo.getValue());
        }
        if (storedFileInfoList.size() > 0) {
            List<Object> list = new ArrayList<Object>();
            for (StoredFileInfo info : storedFileInfoList) {
                list.add(info.getValue());
            }
            values.put(NAME_STORED_FILE_INFO, list);
        }

        return values;
    }


    public static class FolderInfo {

        private static final String NAME_FOLDER_ID          = "folderId";
        private static final String NAME_FOLDER_PASSWORD    = "folderPassword";

        private String folderId;
        private String folderPassword;

        public FolderInfo() {
            folderId = null;
            folderPassword = null;
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

        public Object getValue() {
            Map<String, Object> values = new HashMap<String, Object>();

            if (folderId != null) {
                values.put(NAME_FOLDER_ID, folderId);
            }
            if (folderPassword != null) {
                values.put(NAME_FOLDER_PASSWORD, folderPassword);
            }

            return values;
        }
    }

    public static class StoredFileInfo {

        private static final String NAME_FILE_ID = "fileId";
        private static final String NAME_FILE_PASSWORD = "filePassword";

        private String fileId;
        private String filePassword;

        public StoredFileInfo() {
            fileId = null;
            filePassword = null;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getFilePassword() {
            return filePassword;
        }

        public void setFilePassword(String filePassword) {
            this.filePassword = filePassword;
        }

        public Object getValue() {
            Map<String, Object> values = new HashMap<String, Object>();

            if (fileId != null) {
                values.put(NAME_FILE_ID, fileId);
            }
            if (filePassword != null) {
                values.put(NAME_FILE_PASSWORD, filePassword);
            }

            return values;
        }
    }

}
