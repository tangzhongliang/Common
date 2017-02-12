/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.counter;

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

public class Counter extends ApiClient {
	
	private static final String REST_PATH_CHARGE_COUNTER	= "/rws/counter/chargeCounter";
	private static final String REST_PATH_MANAGE_COUNTER	= "/rws/counter/manageCounter";
	private static final String REST_PATH_USER_COUNTER		= "/rws/counter/userCounter/%s";
	private static final String REST_PATH_ECO_COUNTER		= "/rws/counter/ecoCounter/%s";
	
	public Counter() {
		super();
	}
	
	public Counter(RestContext context) {
		super(context);
	}
	
	/*
	 * GET: /rws/counter/chargeCounter
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetChargeCounterResponseBody
	 */
	public Response<GetChargeCounterResponseBody> getChargeCounter(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_CHARGE_COUNTER, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetChargeCounterResponseBody>(restResponse, new GetChargeCounterResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/counter/manageCounter
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetManageCounterResponseBody
	 */
	public Response<GetManageCounterResponseBody> getManageCounter(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_MANAGE_COUNTER, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetManageCounterResponseBody>(restResponse, new GetManageCounterResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/counter/userCounter/{userId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetUserCounterResponseBody
	 */
	public Response<GetUserCounterResponseBody> getUserCounter(Request request, String userId) throws IOException, InvalidResponseException {
		if (userId == null) {
			throw new NullPointerException("userId must not be null.");
		}
		if (userId.trim().length() == 0) {
			throw new IllegalArgumentException("jobId must not be empty.");
		}
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, String.format(REST_PATH_USER_COUNTER, userId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetUserCounterResponseBody>(restResponse, new GetUserCounterResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/counter/ecoCounter/{userId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetEcoCounterResponseBody
	 */
	public Response<GetEcoCounterResponseBody> getEcoCounter(Request request, String userId) throws IOException, InvalidResponseException {
		if (userId == null) {
			throw new NullPointerException("userId must not be null.");
		}
		if (userId.trim().length() == 0) {
			throw new IllegalArgumentException("jobId must not be empty.");
		}
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, String.format(REST_PATH_ECO_COUNTER, userId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetEcoCounterResponseBody>(restResponse, new GetEcoCounterResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
}

