/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

/**
 * RestContest for MultiLink-Panel(SmartOperation Panel) application
 * 
 */
public class BasicRestContext extends AbstractRestContext {

    private static final String DEFAULT_HOST_NAME = "gw.machine.address";
    private static final String DEFAULT_SCHEME    = "http";
    private static final int DEFAULT_HTTP_PORT    = 54080;
    private static final int DEFAULT_HTTPS_PORT   = 54443;

    public BasicRestContext() {
        this(DEFAULT_SCHEME);
    }

    public BasicRestContext(String scheme) {
        setStringParameter(HOST, DEFAULT_HOST_NAME);
        setStringParameter(SCHEME, scheme);
        setPortByScheme(scheme);
    }

    public void setScheme(String scheme) {
        if(scheme == null){
            throw new NullPointerException("scheme must not be null.");
        }
        setStringParameter(SCHEME, scheme);
        setPortByScheme(scheme);
    }

    private void setPortByScheme(String scheme){
        if("http".equals(scheme)){
            setIntParameter(PORT, DEFAULT_HTTP_PORT);
        } else if("https".equals(scheme)) {
            setIntParameter(PORT, DEFAULT_HTTPS_PORT);
        } else {
            setIntParameter(PORT, DEFAULT_HTTPS_PORT);
        }
    }
}

