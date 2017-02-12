/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

/**
 * WebAPI通信で用いるリクエストを示すクラス。
 */
public class Request {
	
	private RequestHeader header;
	private RequestQuery query;
	private RequestBody body;
	
	public Request() {
		header = null;
		query = null;
		body = null;
	}
	
	public boolean hasHeader() {
		return (header != null);
	}
	
	public RequestHeader getHeader() {
		return header;
	}
	
	public void setHeader(RequestHeader header) {
		this.header = header;
	}
	
	public boolean hasQuery() {
		return (query != null);
	}
	
	public RequestQuery getQuery() {
		return query;
	}
	
	public void setQuery(RequestQuery query) {
		this.query = query;
	}
	
	public boolean hasBody() {
		return (body != null);
	}
	
	public RequestBody getBody() {
		return body;
	}
	
	public void setBody(RequestBody body) {
		this.body = body;
	}
	
}
