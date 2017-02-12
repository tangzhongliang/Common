/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestRequest;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ApiClient;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Printer extends ApiClient {

	private static final String REST_PATH_CAPABILITY		= "/rws/service/printer/capability";
	private static final String REST_PATH_JOBS				= "/rws/service/printer/jobs";
	private static final String REST_PATH_STATUS			= "/rws/service/printer/status";
	private static final String REST_PATH_SUPPORTED_PDL		= "/rws/service/printer/supportedPDL";
	private static final String REST_PATH_FILE				= "/rws/service/printer/file";
    /** REST通信 リソースのURI（プリンタ初期設定） */
    private static final String REST_PATH_CONFIGURATION = "/rws/service/printer/configuration";
    /** REST通信 リソースのURI：認証課金設定問い合わせ */
    private static final String REST_PATH_AUTH_RESTRICTION = "/rws/service/printer/authRestriction";

	public Printer() {
		super();
	}

	public Printer(RestContext context) {
		super(context);
	}

	/*
	 * GET: /rws/service/printer/capability
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
	 * GET: /rws/service/printer/jobs
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetJobListResponseBody
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
	 * POST: /rws/service/printer/jobs
	 * 
	 * RequestBody:  CreateJobRequestBody
	 * ResponseBody: CreateJobResponseBody
	 */
	public Response<CreateJobResponseBody> createJob(Request request) throws IOException, InvalidResponseException {
		// If you enable this comments, JSON structure that request will be output to the debug log.
		//if (Logger.isDebugEnabled()) {
		//	if (request.hasBody()) {
		//		Logger.debug("printer createJob json: " + request.getBody().toEntityString());
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
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}

	/*
	 * GET: /rws/service/printer/status
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetPrinterStatusResponseBody
	 */
	public Response<GetPrinterStatusResponseBody> getPrinterStatus(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_STATUS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetPrinterStatusResponseBody>(restResponse, new GetPrinterStatusResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}

	/*
	 * GET: /rws/service/printer/supportedPDL
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetSupportedPDLResponseBody
	 */
	public Response<GetSupportedPDLResponseBody> getSupportedPDL(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_SUPPORTED_PDL, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetSupportedPDLResponseBody>(restResponse, new GetSupportedPDLResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}

	/*
	 * POST: /rws/service/printer/file
	 * 
	 * RequestBody:  CreatePrintFileRequestBody
	 * ResponseBody: CreatePrintFileResponseBody
	 */
	public Response<CreatePrintFileResponseBody> createPrintFile(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_FILE, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<CreatePrintFileResponseBody>(restResponse, new CreatePrintFileResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}

	}
	/**
     * プリンタ機能の初期設定を取得する。<BR>
     * 　　　RequestBody : なし<BR>
     * 　　　ResponseBody : GetConfigurationResponseBody<BR>
     *
     * @param request リクエストデータ（GET: /rws/service/printer/configuration）
     * @return API実行結果を表すレスポンスデータ
     * @throws IOException IO例外
     * @throws InvalidResponseException SmartSDK API側でエラーが発生した場合
     */
    public Response<GetConfigurationResponseBody> getConfiguration(Request request)
            throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_CONFIGURATION, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse
                .makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case HttpStatus.SC_OK:
                return new Response<GetConfigurationResponseBody>(restResponse,
                        new GetConfigurationResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    public Response<GetPrinterAuthRestrictionResponseBody> getAuthRestriction(Request request)
            throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_AUTH_RESTRICTION, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse
                .makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case HttpStatus.SC_OK:
                return new Response<GetPrinterAuthRestrictionResponseBody>(restResponse,
                        new GetPrinterAuthRestrictionResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }
}
