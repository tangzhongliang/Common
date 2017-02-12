/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.storage;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

/*
 * @since SmartSDK V2.10
 */
public class UpdateFileRequestBody extends WritableElement implements RequestBody {

	private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

	private static final String KEY_FILE_NAME 		= "fileName";
    private static final String KEY_CHANGED_FIELD 	= "changedField";
    private static final String KEY_FILE_INFO		= "fileInfo";
    private static final String KEY_ACL_INFO		= "aclInfo";

	public UpdateFileRequestBody(Map<String, Object> values) {
		super(values);
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
     * fileName (String)
     * @since SmartSDK V2.10 
     */
    public String getFileName() {
    	return getStringValue(KEY_FILE_NAME);
    }
    public void setFileName(String value) {
    	setStringValue(KEY_FILE_NAME, value);
    }
    public String removeFileName() {
    	return removeStringValue(KEY_FILE_NAME);
    }

    /*
     * changedField (String)
     * @since SmartSDK V2.10
     */
    public String getChangedField() {
    	return getStringValue(KEY_CHANGED_FIELD);
    }
    public void setChangedField(String value) {
    	setStringValue(KEY_CHANGED_FIELD, value);
    }
    public String removeChangedField() {
    	return removeStringValue(KEY_CHANGED_FIELD);
    }
    
    /*
     * fileInfo (Object)
     * @since SmartSDK V2.10
     */
    public FileInfo getFileInfo() {
    	Map<String, Object> value = getObjectValue(KEY_FILE_INFO);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_FILE_INFO, value);
		}
		return new FileInfo(value);
    }
    public FileInfo removeFileInfo() {
    	Map<String, Object> value = removeObjectValue(KEY_FILE_INFO);
    	if (value == null) {
    		return null;
    	}
    	return new FileInfo(value);
    }
    
    /*
     * aclInfo (Object)
     */
    public AclInfo getAclInfo() {
    	Map<String, Object> value = getObjectValue(KEY_ACL_INFO);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_ACL_INFO, value);
		}
		return new AclInfo(value);
    }
    public AclInfo removeAclInfo() {
    	Map<String, Object> value = getObjectValue(KEY_ACL_INFO);
    	if (value == null) {
    		return null;
    	}
    	return new AclInfo(value);
    }
    
    /*
     * @since SmartSDK V2.10
     */
	public static class FileInfo extends WritableElement {
    	
		private static final String KEY_USER_NAME 			= "userName";
		private static final String KEY_IS_SECURITY_LOCKED 	= "isSecurityLocked";
		private static final String KEY_OWNER_ENTRY_ID 		= "ownerEntryId";
		private static final String KEY_NEW_PASSWORD 		= "newPassword";

		public FileInfo(Map<String, Object> values) {
			super(values);
		}
		/*
		 * userName (String)
		 * @since SmartSDK V2.10
		 */
        public String getUserName() {
        	return getStringValue(KEY_USER_NAME);
        }
        public void setUserName(String value) {
	    	setStringValue(KEY_USER_NAME, value);
	    }
        public String removeUserName() {
        	return removeStringValue(KEY_USER_NAME);
        }

        /*
         * isSecurityLocked (Boolean)
         * @since SmartSDK V2.10
         */
        public Boolean getIsSecurityLocked() {
        	return getBooleanValue(KEY_IS_SECURITY_LOCKED);
        }
        public void setIsSecurityLocked(Boolean value) {
        	setBooleanValue(KEY_IS_SECURITY_LOCKED, value);
        }
        public Boolean removeIsSecurityLocked() {
        	return removeBooleanValue(KEY_IS_SECURITY_LOCKED);
        }

        /*
         * ownerEntryId (String) 
         * @since SmartSDK V2.10
         */
        public String getOwnerEntryId() {
        	return getStringValue(KEY_OWNER_ENTRY_ID);
        }
        public void setOwnerEntryId(String value) {
        	setStringValue(KEY_OWNER_ENTRY_ID, value);
        }
        public String removeOwnerEntryId() {
        	return removeStringValue(KEY_OWNER_ENTRY_ID);
        }

        /*
         * newPassword (String)
         * @since SmartSDK V2.10
         */
        public String getNewPassword() {
        	return getStringValue(KEY_NEW_PASSWORD);
        }
        public void setNewPassword(String value) {
        	setStringValue(KEY_NEW_PASSWORD, value);
        }
        public String removeNewPassword() {
        	return removeStringValue(KEY_NEW_PASSWORD);
        }
	}
    
    /*
     * @since SmartSDK V2.10
     */
	public static class AclInfo extends WritableElement {
		
		private static final String KEY_ENTRY_ID 	 = "entryId";
		private static final String KEY_AUTHORITY	 = "authority";

		public AclInfo(Map<String, Object> values) {
			super(values);
		}
		/*
		 * entryId (String)
		 * @since SmartSDK V2.10
		 */
        public String getEntryId() {
        	return getStringValue(KEY_ENTRY_ID);
        }
		public void setEntryId(String value) {
			setStringValue(KEY_ENTRY_ID, value);
		}
		public String removeEntryId() {
			return removeStringValue(KEY_ENTRY_ID);
		}

        /*
         * authority (String)
         * @since SmartSDK V2.10
         */
		public String getAuthority() {
			return getStringValue(KEY_AUTHORITY);
		}
		public void setAuthority(String value) {
			setStringValue(KEY_AUTHORITY, value);
		}
		public String removeAuthority() {
			return removeStringValue(KEY_AUTHORITY);
		}
	}

}