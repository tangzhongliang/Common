/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

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

public class DeviceProperty extends ApiClient {

    private static final String REST_PATH_DEVICEINFO    		= "/rws/property/deviceInfo";
    private static final String REST_PATH_PROPERTY_LDAP 		= "/rws/property/ldap/%d";

    public DeviceProperty() {
        super();
    }

    public DeviceProperty(RestContext context) {
        super(context);
    }

    /*
     * GET: /rws/property/deviceInfo
     * 
     * RequestBody:  non
     * ResponseBody: GetDeviceInfoResponseBody
     */
    public Response<GetDeviceInfoResponseBody> getDeviceInfo(Request request) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_DEVICEINFO, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<GetDeviceInfoResponseBody>(restResponse, new GetDeviceInfoResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    
    /*
     * GET: /rws/property/ldap/{num}
     * 
     * RequestBody:  non
     * ResponseBody: GetLdapResponseBody
     */
    public Response<GetLdapResponseBody> getLdap(Request request, int num) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, String.format(REST_PATH_PROPERTY_LDAP, num), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<GetLdapResponseBody>(restResponse, new GetLdapResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    /*
     * PUT: /rws/property/ldap/{num}
     * 
     * RequestBody:  UpdateLdapRequestBody
     * ResponseBody: UpdateLdapResponseBody
     */
    public Response<UpdateLdapResponseBody> updateLdap(Request request, int num) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_PUT, String.format(REST_PATH_PROPERTY_LDAP, num), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<UpdateLdapResponseBody>(restResponse, new UpdateLdapResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

}
