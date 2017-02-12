/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import java.io.IOException;
import java.util.List;
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

public class Scanner extends ApiClient {
	
	private static final String REST_PATH_CAPABILITY	= "/rws/service/scanner/capability";
	private static final String REST_PATH_JOBS			= "/rws/service/scanner/jobs";
	private static final String REST_PATH_STATUS		= "/rws/service/scanner/status";
	private static final String REST_PATH_CONFIGURATION = "/rws/service/scanner/configuration";
	private static final String REST_PATH_AUTH_RESTRICTION = "/rws/service/scanner/authRestriction";
	
	public Scanner() {
		super();
	}
	
	public Scanner(RestContext context) {
		super(context);
	}
	
	/*
	 * GET: /rws/service/scanner/capability
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetCapabilityResponseBody
	 */
	public Response<GetCapabilityResponseBody> getCapability(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_CAPABILITY, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetCapabilityResponseBody>(restResponse, new GetCapabilityResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * GET: /rws/service/scanner/jobs
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetJobListResponseBody
	 * 
	 * @since SmartSDK V2.00
	 */
	public Response<GetJobListResponseBody> getJobList(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_JOBS, request));

		switch (restResponse.getStatusCode()) {
			case 200:
				List<Map<String, Object>> body = GenericJsonDecoder.decodeToList(restResponse.makeContentString("UTF-8"));
				return new Response<GetJobListResponseBody>(restResponse, new GetJobListResponseBody(body));
			default:
				Map<String, Object> errorBody = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
				throw Utils.createInvalidResponseException(restResponse, errorBody);
		}
	}
	
	/*
	 * POST: /rws/service/scanner/jobs
	 * 
	 * RequestBody:  CreateJobRequestBody
	 * ResponseBody: CreateJobResponseBody
	 */
	public Response<CreateJobResponseBody> createJob(Request request) throws IOException, InvalidResponseException {
		// If you enable this comments, JSON structure that request will be output to the debug log.
		//if (Logger.isDebugEnabled()) {
		//	if (request.hasBody()) {
		//		Logger.debug("scanner createJob json: " + request.getBody().toEntityString());
		//	}
		//}
		
	    RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_JOBS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:	// validateOnly=true
				return new Response<CreateJobResponseBody>(restResponse, new CreateJobResponseBody(body));
			case 201:	// validateOnly=false, job created
				return new Response<CreateJobResponseBody>(restResponse, new CreateJobResponseBody(body));
			
			default:
				// SmartSDK V1.xx
				//throw Utils.createInvalidResponseException(restResponse, body);
				// SmartSDK V2.00 or later
				throw createCreateJobInvalidResponseException(restResponse, body);
		}
	}
	
	private CreateJobInvalidResponseException createCreateJobInvalidResponseException(RestResponse response, Map<String, Object> body) {
		CreateJobErrorResponseBody responseBody = null;
		if (body != null) {
			responseBody = new CreateJobErrorResponseBody(body);
		}
		return new CreateJobInvalidResponseException(response, responseBody);
	}
	
	/*
	 * GET: /rws/service/scanner/status
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetScannerStatusResponseBody
	 */
	public Response<GetScannerStatusResponseBody> getScannerStatus(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_STATUS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetScannerStatusResponseBody>(restResponse, new GetScannerStatusResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}

    public Response<GetConfigurationResponseBody> getConfiguration(Request request) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_CONFIGURATION, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<GetConfigurationResponseBody>(restResponse, new GetConfigurationResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }
    public Response<GetAuthRestrictionResponseBody> getAuthRestriction(Request request) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_AUTH_RESTRICTION, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<GetAuthRestrictionResponseBody>(restResponse, new GetAuthRestrictionResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }
}
