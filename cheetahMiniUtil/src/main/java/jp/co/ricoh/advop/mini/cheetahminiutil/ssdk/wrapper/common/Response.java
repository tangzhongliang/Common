/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;

/**
 * 通信のレスポンスを示すクラス
 */
public class Response<T extends ResponseBody> {
	
	private final RestResponse response;
	private final T body;
	
	public Response(RestResponse response, T body) {
		if (response == null) {
			throw new NullPointerException("response must not be null.");
		}
		this.response = response;
		this.body = body;
	}
	
	public RestResponse getResponse() {
		return response;
	}
	
	public int getStatusCode() {
		return response.getStatusCode();
	}
	
	public Map<String, String> getAllHeaders() {
		return response.getAllHeaders();
	}
	
	public String getContentType() {
		return response.getContentType();
	}
	
	public byte[] getBytes() {
		return response.getBytes();
	}
	
	public boolean hasBody() {
		return (body != null);
	}
	
	public T getBody() {
		return body;
	}
	
}
