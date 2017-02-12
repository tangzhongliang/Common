/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.log.printerlog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

public class DeviceLog extends ApiClient {
	
	private static final String REST_PATH_JOB_LOG     = "/rws/log/printerlog/devicelog/joblog.json";
	private static final String REST_PATH_ACCESS_LOG  = "/rws/log/printerlog/devicelog/accesslog.json";
	private static final String REST_PATH_ECOLOGY_LOG = "/rws/log/printerlog/devicelog/ecologylog.json";
	private static final String REST_PATH_DEVICE_LOG  = "/rws/log/printerlog/devicelog.json";
	
	public DeviceLog() {
		super();
	}
	
	public DeviceLog(RestContext context) {
		super(context);
	}
	
	/*
	 * GET: /rws/log/printerlog/devicelog/joblog.json
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetDeviceLogResponseBody
	 */
	public Response<GetDeviceLogResponseBody> getJobLog(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_JOB_LOG, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetDeviceLogResponseBody>(restResponse, new GetDeviceLogResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/log/printerlog/devicelog/accesslog.json
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetDeviceLogResponseBody
	 */
	public Response<GetDeviceLogResponseBody> getAccessLog(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_ACCESS_LOG, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetDeviceLogResponseBody>(restResponse, new GetDeviceLogResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/log/printerlog/devicelog/ecologylog.json
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetDeviceLogResponseBody
	 */
	public Response<GetDeviceLogResponseBody> getEcologyLog(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_ECOLOGY_LOG, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetDeviceLogResponseBody>(restResponse, new GetDeviceLogResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/log/printerlog/devicelog.json
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetDeviceLogResponseBody
	 */
	public Response<GetDeviceLogResponseBody> getDeviceLog(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_DEVICE_LOG, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetDeviceLogResponseBody>(restResponse, new GetDeviceLogResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	public Response<GetDeviceLogResponseBody> getContinuationResponse(
			Request request, String nextLink) throws IOException, InvalidResponseException {
		
		final URL nextLinkURL = new URL(nextLink);
		final String path = nextLinkURL.getPath();
		final RestRequest restRequest = build(RestRequest.METHOD_GET, path, request);
		try {
			restRequest.setURI(nextLinkURL.toURI());
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
		
		RestResponse restResponse = execute(restRequest);
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetDeviceLogResponseBody>(restResponse, new GetDeviceLogResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
}
