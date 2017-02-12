/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

/**
 * RestContest for Remote application
 *
 */
public class RemoteRestContext extends AbstractRestContext {

    public RemoteRestContext(String host) {
        if (host == null) {
            throw new NullPointerException("host must not be null.");
        }
        setStringParameter(HOST, host);
        setIntParameter(PORT, 80);
        setStringParameter(SCHEME, "http");
    }

    public RemoteRestContext(String host, int port, String scheme) {
        if(host == null) throw new NullPointerException("host must not be null.");
        setStringParameter(HOST, host);

        if(0 > port || port > 65535) {
            setIntParameter(PORT, 80);
        } else {
            setIntParameter(PORT, port);
        }

        if(scheme == null) {
            setStringParameter(SCHEME, "http");
        } else {
            setStringParameter(SCHEME, scheme);
        }

    }

    public void setHost(String host) {
        if (host == null) {
            throw new NullPointerException("host must not be null.");
        }
        setStringParameter(HOST, host);
    }

    public void setPort(int port) {
        if( 0 > port || port > 65535) {
            throw new IllegalArgumentException("port number is invalid");
        }
        setIntParameter(PORT, port);
    }

    public void setScheme(String scheme) {
        if(scheme == null) {
            throw new NullPointerException("scheme must not be null.");
        }
        setStringParameter(SCHEME, scheme);
    }

}
