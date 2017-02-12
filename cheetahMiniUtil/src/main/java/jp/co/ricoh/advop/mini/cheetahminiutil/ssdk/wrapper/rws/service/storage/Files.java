/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.storage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestRequest;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ApiClient;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.BinaryResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.EmptyResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;

public class Files extends ApiClient {

    private static final String REST_PATH_FILES                 = "/rws/service/storage/files";
    private static final String REST_PATH_FILES_ID              = "/rws/service/storage/files/%s";
    private static final String REST_PATH_FILES_ID_IMAGE        = "/rws/service/storage/files/%s/image";
    private static final String REST_PATH_FILES_ID_THUMBNAIL    = "/rws/service/storage/files/%s/thumbnail";

    public Files() {
        super();
    }

    public Files(RestContext context) {
        super(context);
    }

    private String getQueryValue(RequestQuery query, String key) {
        if (query == null) {
            return null;
        }
        return query.get(key);
    }

    /*
     * GET: /rws/service/storage/files
     *
     * RequestBody:  non
     * ResponseBody: GetFileListResponseBody
     *
     * @since SmartSDK V1.01
     */
    public Response<GetFileListResponseBody> getFileList(Request request) throws IOException, InvalidResponseException {
        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, REST_PATH_FILES, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<GetFileListResponseBody>(restResponse, new GetFileListResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }
    public Response<GetFileListResponseBody> getFileListContinuationResponse(Request request, String nextLink) throws IOException, InvalidResponseException {
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
                return new Response<GetFileListResponseBody>(restResponse, new GetFileListResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }


    /*
     * GET: /rws/service/storage/files/{fileId}
     *
     * RequestBody:  non
     * ResponseBody: GetFileResponseBody
     *
     * @since SmartSDK V1.01
     */
    public Response<GetFileResponseBody> getFile(Request request, String fileId) throws IOException, InvalidResponseException {
        if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, String.format(REST_PATH_FILES_ID, fileId), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<GetFileResponseBody>(restResponse, new GetFileResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    /*
     * DELETE: /rws/service/storage/files/{fileId}
     *
     * RequestBody:  non
     * ResponseBody: non (EmptyResponseBody)
     *
     * @since SmartSDK V1.01
     */
    public Response<EmptyResponseBody> deleteFile(Request request, String fileId) throws IOException, InvalidResponseException {
        if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_DELETE, String.format(REST_PATH_FILES_ID, fileId), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }
    
    /*
     * PUT: /rws/service/storage/files/{fileId}
     *
     * RequestBody:  UpdateFileRequestBody
     * ResponseBody: non (EmptyResponseBody)
     *
     * @since SmartSDK V2.10
     */
    public Response<EmptyResponseBody> updateFile(Request request, String fileId) throws IOException, InvalidResponseException {
    	if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_PUT, String.format(REST_PATH_FILES_ID, fileId), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    /*
     * GET: /rws/service/storage/files/{fileId}/image?getMethod=direct
     *
     * RequestBody:  non
     * ResponseBody: BinaryResponseBody
     */
    public Response<BinaryResponseBody> getImage(Request request, String fileId) throws IOException, InvalidResponseException {
        if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        String getMethod = getQueryValue(request.getQuery(), "getMethod");
        if ((getMethod != null) && (! "direct".equals(getMethod))) {
            throw new IllegalArgumentException("Invalid parameter: getMethod.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, String.format(REST_PATH_FILES_ID_IMAGE, fileId), request));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<BinaryResponseBody>(restResponse, new BinaryResponseBody(restResponse.getBytes()));
            default:
                Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    /*
     * GET: /rws/service/storage/files/{fileId}/image?getMethod=filePath
     * MultiLink-Panel only
     *
     * RequestBody:  non
     * ResponseBody: FilePathResponseBody
     */
    public Response<FilePathResponseBody> getImagePath(Request request, String fileId) throws IOException, InvalidResponseException {
        if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        String getMethod = getQueryValue(request.getQuery(), "getMethod");
        if (getMethod == null) {
            throw new IllegalArgumentException("Required parameter: getMethod.");
        }
        if (! "filePath".equals(getMethod)) {
            throw new IllegalArgumentException("Invalid parameter: getMethod.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, String.format(REST_PATH_FILES_ID_IMAGE, fileId), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<FilePathResponseBody>(restResponse, new FilePathResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }


    /*
     * GET: /rws/service/storage/files/{fileId}/thumbnail?getMethod=direct
     *
     * RequestBody:  non
     * ResponseBody: BinaryResponseBody
     *
     * @since SmartSDK V1.01
     */
    public Response<BinaryResponseBody> getThumbnail(Request request, String fileId) throws IOException, InvalidResponseException {
        if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        String getMethod = getQueryValue(request.getQuery(), "getMethod");
        if ((getMethod != null) && (! "direct".equals(getMethod))) {
            throw new IllegalArgumentException("Invalid parameter: getMethod.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, String.format(REST_PATH_FILES_ID_THUMBNAIL, fileId), request));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<BinaryResponseBody>(restResponse, new BinaryResponseBody(restResponse.getBytes()));
            default:
                Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

    /*
     * GET: /rws/service/storage/files/{fileId}/thumbnail?getMethod=filePath
     * MultiLink-Panel only
     *
     * RequestBody:  non
     * ResponseBody: FilePathResponseBody
     *
     * @since SmartSDK V1.01
     */
    public Response<FilePathResponseBody> getThumbnailPath(Request request, String fileId) throws IOException, InvalidResponseException {
        if (fileId == null) {
            throw new NullPointerException("fileId must not be null.");
        }
        if (fileId.trim().length() == 0) {
        	throw new IllegalArgumentException("fileId must not be empty.");
        }

        String getMethod = getQueryValue(request.getQuery(), "getMethod");
        if (getMethod == null) {
            throw new IllegalArgumentException("Required parameter: getMethod.");
        }
        if (! "filePath".equals(getMethod)) {
            throw new IllegalArgumentException("Invalid parameter: getMethod.");
        }

        RestResponse restResponse = execute(
                build(RestRequest.METHOD_GET, String.format(REST_PATH_FILES_ID_THUMBNAIL, fileId), request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

        switch (restResponse.getStatusCode()) {
            case 200:
                return new Response<FilePathResponseBody>(restResponse, new FilePathResponseBody(body));
            default:
                throw Utils.createInvalidResponseException(restResponse, body);
        }
    }

}
