/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system;

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

/*
 * @since SmartSDK V1.02
 */
public class Validator extends ApiClient {

    private static final String REST_PATH_VALIDATE      = "/rws/system/validate";

    public Validator() {
        super();
    }

    public Validator(RestContext context) {
        super(context);
    }

    /*
     * POST: /rws/system/validate
     *
     * RequestBody:  non
     * ResponseBody: CreateSessionTokenResponseBody
     *
     * @since SmartSDK V1.02
     */
    public Response<CreateSessionTokenResponseBody> createSessionToken(Request request) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_POST, REST_PATH_VALIDATE, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<CreateSessionTokenResponseBody>(restResponse, new CreateSessionTokenResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

}
