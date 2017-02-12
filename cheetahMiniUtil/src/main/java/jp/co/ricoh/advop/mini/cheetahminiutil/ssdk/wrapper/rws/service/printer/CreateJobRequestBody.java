/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

public class CreateJobRequestBody extends WritableElement implements RequestBody {

	private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

	private static final String KEY_FILE_ID			= "fileId";
	private static final String KEY_FILE_PATH		= "filePath";
	private static final String KEY_PDL 			= "pdl";
	private static final String KEY_USER_CODE		= "userCode";
	private static final String KEY_VALIDATE_ONLY	= "validateOnly";
	private static final String KEY_JOB_SETTING		= "jobSetting";
	private static final String KEY_PROXY_INFO						 = "proxyInfo"; // SmartSDK V2.10
	private static final String KEY_URL_ACCESS_AUTH_INFO 			 = "urlAccessAuthInfo"; // SmartSDK V2.10

	public CreateJobRequestBody() {
		super(new HashMap<String, Object>());
	}

	@Override
	public String getContentType() {
		return CreateJobRequestBody.CONTENT_TYPE_JSON;
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
	 * fileId (String)
	 */
	public String getFileId() {
		return getStringValue(KEY_FILE_ID);
	}
	public void setFileId(String value) {
		setStringValue(KEY_FILE_ID, value);
	}
	public String removeFileId() {
		return removeStringValue(KEY_FILE_ID);
	}

	/*
	 * filePath (String)
	 */
	public String getFilePath() {
		return getStringValue(KEY_FILE_PATH);
	}
	public void setFilePath(String value) {
		setStringValue(KEY_FILE_PATH, value);
	}
	public String removeFilePath() {
		return removeStringValue(KEY_FILE_PATH);
	}

	/*
	 * pdl (String)
	 */
	public String getPdl() {
		return getStringValue(KEY_PDL);
	}
	public void setPdl(String value) {
		setStringValue(KEY_PDL, value);
	}
	public String removePdl() {
		return removeStringValue(KEY_PDL);
	}

	/*
	 * userCode (String)
	 */
	public String getUserCode() {
		return getStringValue(KEY_USER_CODE);
	}
	public void setUserCode(String value) {
		setStringValue(KEY_USER_CODE, value);
	}
	public String removeUserCode() {
		return removeStringValue(KEY_USER_CODE);
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
//	public void setJobSetting(JobSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public JobSetting removeJobSetting() {
		Map<String, Object> value = removeObjectValue(KEY_JOB_SETTING);
		if (value == null) {
			return null;
		}
		return new JobSetting(value);
	}
	
    /*
     * proxyInfo (Object)
     * @since SmartSDK V2.10
     */
    public ProxyInfo getProxyInfo() {		
    	Map<String, Object> value = getObjectValue(KEY_PROXY_INFO);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_PROXY_INFO, value);
		}
		return new ProxyInfo(value);
    }
    public ProxyInfo removeProxyInfo() {
    	Map<String, Object> value = removeObjectValue(KEY_PROXY_INFO);
		if (value == null) {
			return null;
		}
		return new ProxyInfo(value);
    }

    /*
     * urlAccessInfo (Object)
     * @since SmartSDK V2.10
     */
    public UrlAccessAuthInfo getUrlAccessAuthInfo()	{
    	Map<String, Object> value = getObjectValue(KEY_URL_ACCESS_AUTH_INFO);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_URL_ACCESS_AUTH_INFO, value);
		}
		return new UrlAccessAuthInfo(value);
    }
    public UrlAccessAuthInfo removeUrlAccessAuthInfo() {
    	Map<String, Object> value = removeObjectValue(KEY_URL_ACCESS_AUTH_INFO);
		if (value == null) {
			return null;
		}
		return new UrlAccessAuthInfo(value);
    }
	
	/*
	 * @since SmartSDK V2.10
	 */
	public static class ProxyInfo extends WritableElement {	
		
		private static final String KEY_ADDRESS  = "address";
		private static final String KEY_PORT 	 = "port";
		private static final String KEY_USERNAME = "userName";
		private static final String KEY_PASSWORD = "password";
		
		ProxyInfo(Map<String, Object> values) {
			super(values);
		}
			
		/*
		 * Address (String)
		 * @since SmartSDK V2.10
		 */
		public String getAddress() {
			return getStringValue(KEY_ADDRESS);
		}
	    public void setAddress(String value) {
	    	setStringValue(KEY_ADDRESS,value);
	    }
	    public String removeAddress() {
	    	return removeStringValue(KEY_ADDRESS);
	    }

	    /*
	     * Port (Integer)
	     * @since SmartSDK V2.10
	     */
	    public Integer getPort(){
	    	return getNumberValue(KEY_PORT);
	    }
	    public void setPort(Integer value){
	    	setNumberValue(KEY_PORT,value);
	    }
	    public Integer removePort(){
	    	return removeNumberValue(KEY_PORT);
	    }
	    /*
	     * userName (String)
	     * @since SmartSDK V2.10
	     */
	    public String getUserName(){
	    	return getStringValue(KEY_USERNAME);
	    }
	    public void setUserName(String value){
	    	setStringValue(KEY_USERNAME,value);
	    }
	    public String removeUserName(){
	    	return removeStringValue(KEY_USERNAME);
	    }
	    /*
	     *	password (String)
	     *	@since SmartSDK V2.10
	     */
	    public String getPassword(){
	    	return getStringValue(KEY_PASSWORD);
	    }
	    public void setPassword(String value){
	    	setStringValue(KEY_PASSWORD,value);
	    }
	    public String removePassword(){
	    	return removeStringValue(KEY_PASSWORD);
	    }

	}

	/*
	 * @since SmartSDK V2.10
	 */
	public static class UrlAccessAuthInfo extends WritableElement {
		
		private static final String KEY_USERNAME = "userName";
		private static final String KEY_PASSWORD = "password";

		UrlAccessAuthInfo(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * userName (String)
		 * @since SmartSDK V2.10
		 */
		public String getUserName() {
			return getStringValue(KEY_USERNAME);
		}
	    public void setUserName(String value){
	    	setStringValue(KEY_USERNAME,value);
	    }
	    public String removeUserName(){
	    	return removeStringValue(KEY_USERNAME);
	    }
	    
	    /*
	     * password (String)
	     * @since SmartSDK V2.10
	     */
	    public String getPassword(){
	    	return getStringValue(KEY_PASSWORD);
	    }
	   
	    public void setPassword(String value) {
	    	setStringValue(KEY_PASSWORD,value);
	    }
	    public String removePassword(){
	    	return removeStringValue(KEY_PASSWORD);
	    }

	}

}
