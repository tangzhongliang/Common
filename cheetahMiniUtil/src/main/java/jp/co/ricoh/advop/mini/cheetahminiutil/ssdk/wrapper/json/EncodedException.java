/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json;

public class EncodedException extends Exception {
	
	private static final long serialVersionUID = 5420707295257149654L;

	public EncodedException() {
		super();
	}
	
	public EncodedException(String message) {
		super(message);
	}
	
	public EncodedException(Throwable cause) {
		super(cause);
	}
	
	public EncodedException(String message, Throwable cause) {
		super(message, cause);
	}

}
