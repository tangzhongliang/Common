/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestContext implements RestContext {
	
	protected static final String HOST				= "host";
	protected static final String PORT				= "port";
    protected static final String SCHEME            = "scheme";
	protected static final String CONNECTION_TIMEOUT	= "connectionTimeout";
	protected static final String SO_TIMEOUT			= "soTimeout";

	private final Map<String, Object> parameters = new HashMap<String, Object>();
	
	public AbstractRestContext() {
		setIntParameter(CONNECTION_TIMEOUT, 3 * 1000);
		setIntParameter(SO_TIMEOUT, 60 * 1000);
	}
	
	protected String getStringParameter(String key, String defaultValue) {
		String value = (String) parameters.get(key);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}
	
	protected void setStringParameter(String key, String value) {
		parameters.put(key, value);
	}
	
	protected int getIntParameter(String key, int defaultValue) {
		Integer value = (Integer) parameters.get(key);
		if (value == null) {
			return defaultValue;
		}
		return value.intValue();
	}
	
	protected void setIntParameter(String key, int value) {
		parameters.put(key, Integer.valueOf(value));
	}
	
	protected boolean getBooleanParameter(String key, boolean defaultValue) {
		Boolean value = (Boolean) parameters.get(key);
		if (value == null) {
			return defaultValue;
		}
		return value.booleanValue();
	}
	
	protected void setBooleanParameter(String key, boolean value) {
		parameters.put(key, Boolean.valueOf(value));
	}
	
	@Override
	public String getHost() {
		return getStringParameter(HOST, null);
	}
	
	@Override
	public int getPort() {
		return getIntParameter(PORT, -1);
	}
	
	@Override
	public int getConnectionTimeout() {
		return getIntParameter(CONNECTION_TIMEOUT, 0);
	}
	
	@Override
	public int getSoTimeout() {
		return getIntParameter(SO_TIMEOUT, 0);
	}

    @Override
    public String getScheme() {
        return getStringParameter(SCHEME, "http");
    }

    public void setConnectionTimeout(int timeout) {
		setIntParameter(CONNECTION_TIMEOUT, timeout);
	}
	
	public void setSoTimeout(int timeout) {
		setIntParameter(SO_TIMEOUT, timeout);
	}
}
