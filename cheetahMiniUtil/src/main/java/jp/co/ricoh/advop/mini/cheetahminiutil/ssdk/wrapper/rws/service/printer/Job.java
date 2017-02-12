/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.io.IOException;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestRequest;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ApiClient;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.EmptyResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;

public class Job extends ApiClient {

	private static final String REST_PATH_JOBS_ID			= "/rws/service/printer/jobs/%s";

	private final String jobId;

	public Job(String jobId) {
		super();
		if (jobId == null) {
			throw new NullPointerException("jobId must not be null.");
		}
		if(jobId.trim().length() == 0) {
			throw new IllegalArgumentException("jobId must not be empty.");
		}
		this.jobId = jobId;
	}

	public Job(RestContext context, String jobId) {
		super(context);
		if (jobId == null) {
			throw new NullPointerException("jobId must not be null.");
		}
		if(jobId.trim().length() == 0) {
			throw new IllegalArgumentException("jobId must not be empty.");
		}
		this.jobId = jobId;
	}

	/*
	 * GET: /rws/service/printer/jobs/{jobId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetJobStatusResponseBody
	 */
	public Response<GetJobStatusResponseBody> getJobStatus(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, String.format(REST_PATH_JOBS_ID, jobId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetJobStatusResponseBody>(restResponse, new GetJobStatusResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}

	/*
	 * PUT: /rws/service/printer/jobs/{jobId}
	 * 
	 * RequestBody:  UpdateJobStatusRequestBody
	 * ResponseBody: non (EmptyResponseBody)
	 */
	public Response<EmptyResponseBody> updateJobStatus(Request request) throws IOException, InvalidResponseException {
		// If you enable this comments, JSON structure that request will be output to the debug log.
		//if (Logger.isDebugEnabled()) {
		//	if (request.hasBody()) {
		//		Logger.debug("printer updateJobStatus json: " + request.getBody().toEntityString());
		//	}
		//}

		RestResponse restResponse = execute(
				build(RestRequest.METHOD_PUT, String.format(REST_PATH_JOBS_ID, jobId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
			case 202:
				return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}

}
