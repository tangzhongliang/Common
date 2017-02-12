/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json;

public class DecodedException extends Exception {
	
	private static final long serialVersionUID = 1828373960600673705L;

	public DecodedException() {
		super();
	}
	
	public DecodedException(String message) {
		super(message);
	}
	
	public DecodedException(Throwable cause) {
		super(cause);
	}
	
	public DecodedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
