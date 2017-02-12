/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.status;

import java.io.IOException;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestRequest;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ApiClient;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;

public class SystemStatus extends ApiClient {
	
	private static final String REST_PATH_SYSTEM	= "/rws/status/system";
	private static final String REST_PATH_TRAYS		= "/rws/status/trays";
	private static final String REST_PATH_SUPPLIES	= "/rws/status/supplies";
	
	public SystemStatus() {
		super();
	}
	
	public SystemStatus(RestContext context) {
		super(context);
	}
	
	/*
	 * GET: /rws/status/system
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetSystemResponseBody
	 */
	public Response<GetSystemResponseBody> getSystem(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_SYSTEM, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetSystemResponseBody>(restResponse, new GetSystemResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/status/trays
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetTraysResponseBody
	 */
	public Response<GetTraysResponseBody> getTrays(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_TRAYS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetTraysResponseBody>(restResponse, new GetTraysResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/status/supplies
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetSuppliesResponseBody
	 */
	public Response<GetSuppliesResponseBody> getSupplies(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_SUPPLIES, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetSuppliesResponseBody>(restResponse, new GetSuppliesResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
}
