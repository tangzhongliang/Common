/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestRequest;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ApiClient;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.EmptyResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;

public class Addressbook extends ApiClient {
	
	private static final String REST_PATH_ENTRIES			= "/rws/addressbook/entries";
	private static final String REST_PATH_ENTRIES_ENTRYID	= "/rws/addressbook/entries/%s";
	private static final String REST_PATH_TAGS				= "/rws/addressbook/tags";
	private static final String REST_PATH_TAGS_TAGID		= "/rws/addressbook/tags/%s";
	private static final String REST_PATH_GROUPS			= "/rws/addressbook/groups";
	private static final String REST_PATH_GROUPS_GROUPID	= "/rws/addressbook/groups/%s";
	private static final String REST_PATH_STATUS			= "/rws/addressbook/status";
	private static final String REST_PATH_CAPABILITY		= "/rws/addressbook/capability";
	private static final String REST_PATH_LDAP_ENTRIES		= "/rws/addressbook/ldapEntries";
	
	public Addressbook() {
		super();
	}
	
	public Addressbook(RestContext context) {
		super(context);
	}
	
	
	/*
	 * GET: /rws/addressbook/entries
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetEntryListResponseBody
	 */
	public Response<GetEntryListResponseBody> getEntryList(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_ENTRIES, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetEntryListResponseBody>(restResponse, new GetEntryListResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * POST: /rws/addressbook/entries
	 * 
	 * RequestBody:  CreateEntryRequestBody
	 * ResponseBody: CreateEntryResponseBody
	 */
	public Response<CreateEntryResponseBody> createEntry(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_ENTRIES, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 201:
				return new Response<CreateEntryResponseBody>(restResponse, new CreateEntryResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * DELETE: /rws/addressbook/entries
	 * 
	 * RequestBody:  non
	 * ResponseBody: non (EmptyResponseBody)
	 */
	public Response<EmptyResponseBody> clearEntries(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_DELETE, REST_PATH_ENTRIES, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/entries/{entryId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetEntryResponseBody
	 */
	public Response<GetEntryResponseBody> getEntry(Request request, String entryId) throws IOException, InvalidResponseException {
		if (entryId == null) {
			throw new NullPointerException("entryId must not be null.");
		}
		if (entryId.trim().length() == 0) {
			throw new IllegalArgumentException("entryId must not be empty.");
		}
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, String.format(REST_PATH_ENTRIES_ENTRYID, entryId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetEntryResponseBody>(restResponse, new GetEntryResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * PUT: /rws/addressbook/entries/{entryId}
	 * 
	 * RequestBody:  UpdateEntryRequestBody
	 * ResponseBody: UpdateEntryResponseBody
	 */
	public Response<UpdateEntryResponseBody> updateEntry(Request request, String entryId) throws IOException, InvalidResponseException {
		if (entryId == null) {
			throw new NullPointerException("entryId must not be null.");
		}
		if (entryId.trim().length() == 0) {
			throw new IllegalArgumentException("entryId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_PUT, String.format(REST_PATH_ENTRIES_ENTRYID, entryId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<UpdateEntryResponseBody>(restResponse, new UpdateEntryResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * DELETE: /rws/addressbook/entries/{entryId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: non (EmptyResponseBody)
	 */
	public Response<EmptyResponseBody> deleteEntry(Request request, String entryId) throws IOException, InvalidResponseException {
		if (entryId == null) {
			throw new NullPointerException("entryId must not be null.");
		}
		if (entryId.trim().length() == 0) {
			throw new IllegalArgumentException("entryId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_DELETE, String.format(REST_PATH_ENTRIES_ENTRYID, entryId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/tags
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetTagListResponseBody
	 */
	public Response<GetTagListResponseBody> getTagList(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_TAGS, request));
        Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));

		switch (restResponse.getStatusCode()) {
		case 200:
			return new Response<GetTagListResponseBody>(restResponse, new GetTagListResponseBody(body));
		default:
			throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/tags/{tagId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetTagResponseBody
	 */
	public Response<GetTagResponseBody> getTag(Request request, String tagId) throws IOException, InvalidResponseException {
		if (tagId == null) {
			throw new NullPointerException("tagId must not be null.");
		}
		if (tagId.trim().length() == 0) {
			throw new IllegalArgumentException("tagId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, String.format(REST_PATH_TAGS_TAGID, tagId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetTagResponseBody>(restResponse, new GetTagResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * PUT: /rws/addressbook/tags/{tagId}
	 * 
	 * RequestBody:  UpdateTagRequestBody
	 * ResponseBody: UpdateTagResponseBody
	 */
	public Response<UpdateTagResponseBody> updateTag(Request request, String tagId) throws IOException, InvalidResponseException {
		if (tagId == null) {
			throw new NullPointerException("tagId must not be null.");
		}
		if (tagId.trim().length() == 0) {
			throw new IllegalArgumentException("tagId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_PUT, String.format(REST_PATH_TAGS_TAGID, tagId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<UpdateTagResponseBody>(restResponse, new UpdateTagResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/groups
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetGroupListResponseBody
	 */
	public Response<GetGroupListResponseBody> getGroupList(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_GROUPS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
		case 200:
			return new Response<GetGroupListResponseBody>(restResponse, new GetGroupListResponseBody(body));
		default:
			throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * POST: /rws/addressbook/groups
	 * 
	 * RequestBody:  CreateGroupRequestBody
	 * ResponseBody: CreateGroupResponseBody
	 */
	public Response<CreateGroupResponseBody> createGroup(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_GROUPS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
		case 201:
			return new Response<CreateGroupResponseBody>(restResponse, new CreateGroupResponseBody(body));
		default:
			throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * DELETE: /rws/addressbook/groups
	 * 
	 * RequestBody:  non
	 * ResponseBody: non (EmptyResponseBody)
	 */
	public Response<EmptyResponseBody> clearGroups(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_DELETE, REST_PATH_GROUPS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/groups/{groupId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetGroupResponseBody
	 */
	public Response<GetGroupResponseBody> getGroup(Request request, String groupId) throws IOException, InvalidResponseException {
		if (groupId == null) {
			throw new NullPointerException("groupId must not be null.");
		}
		if (groupId.trim().length() == 0) {
			throw new IllegalArgumentException("groupId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, String.format(REST_PATH_GROUPS_GROUPID, groupId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetGroupResponseBody>(restResponse, new GetGroupResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * PUT: /rws/addressbook/groups/{groupId}
	 * 
	 * RequestBody:  UpdateGroupRequestBody
	 * ResponseBody: UpdateGroupResponseBody
	 */
	public Response<UpdateGroupResponseBody> updateGroup(Request request, String groupId) throws IOException, InvalidResponseException {
		if (groupId == null) {
			throw new NullPointerException("groupId must not be null.");
		}
		if (groupId.trim().length() == 0) {
			throw new IllegalArgumentException("groupId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_PUT, String.format(REST_PATH_GROUPS_GROUPID, groupId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<UpdateGroupResponseBody>(restResponse, new UpdateGroupResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * DELETE: /rws/addressbook/groups/{groupId}
	 * 
	 * RequestBody:  non
	 * ResponseBody: non (EmptyResponseBody)
	 */
	public Response<EmptyResponseBody> deleteGroup(Request request, String groupId) throws IOException, InvalidResponseException {
		if (groupId == null) {
			throw new NullPointerException("groupId must not be null.");
		}
		if (groupId.trim().length() == 0) {
			throw new IllegalArgumentException("groupId must not be empty.");
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_DELETE, String.format(REST_PATH_GROUPS_GROUPID, groupId), request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<EmptyResponseBody>(restResponse, new EmptyResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/status
	 * 
	 * RequestBody:  non
	 * ResponseBody: GetStatusResponseBody
	 */
	public Response<GetStatusResponseBody> getStatus(Request request) throws IOException, InvalidResponseException {
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_GET, REST_PATH_STATUS, request));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<GetStatusResponseBody>(restResponse, new GetStatusResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * GET: /rws/addressbook/capability
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
	
	public Response<GetEntryListResponseBody> getEntryListResponseContinuation(
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
				return new Response<GetEntryListResponseBody>(restResponse, new GetEntryListResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	
	/*
	 * POST: /rws/addressbook/entries?_method=SEARCH
	 * 
	 * RequestBody:  SearchEntryRequestBody
	 * ResponseBody: SearchEntryResponseBody
	 * 
	 * @since SmartSDK V2.00
	 */
	public Response<SearchEntryResponseBody> searchEntry(Request request) throws IOException, InvalidResponseException {
		Request newRequest = null;
		if (request != null) {
			newRequest = new Request();
			if (request.hasHeader()) {
				newRequest.setHeader(request.getHeader());
			}
			if (request.hasBody()) {
				newRequest.setBody(request.getBody());
			}
			
			RequestQuery newQuery = new RequestQuery();
			if (request.hasQuery()) {
				newQuery.putAll(request.getQuery().getQueries());
			}
			newQuery.put("_method", "SEARCH");
			newRequest.setQuery(newQuery);
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_ENTRIES, newRequest));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<SearchEntryResponseBody>(restResponse, new SearchEntryResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
	/*
	 * POST: /rws/addressbook/ldapEntries?_method=SEARCH
	 * 
	 * RequestBody:  SearchLdapEntryRequestBody
	 * ResponseBody: SearchLdapEntryResponseBody
	 * 
	 * @since SmartSDK V2.00
	 */
	public Response<SearchLdapEntryResponseBody> searchLdapEntry(Request request) throws IOException, InvalidResponseException {
		Request newRequest = null;
		if (request != null) {
			newRequest = new Request();
			if (request.hasHeader()) {
				newRequest.setHeader(request.getHeader());
			}
			if (request.hasBody()) {
				newRequest.setBody(request.getBody());
			}
			
			RequestQuery newQuery = new RequestQuery();
			if (request.hasQuery()) {
				newQuery.putAll(request.getQuery().getQueries());
			}
			newQuery.put("_method", "SEARCH");
			newRequest.setQuery(newQuery);
		}
		
		RestResponse restResponse = execute(
				build(RestRequest.METHOD_POST, REST_PATH_LDAP_ENTRIES, newRequest));
		Map<String, Object> body = GenericJsonDecoder.decodeToMap(restResponse.makeContentString("UTF-8"));
		
		switch (restResponse.getStatusCode()) {
			case 200:
				return new Response<SearchLdapEntryResponseBody>(restResponse, new SearchLdapEntryResponseBody(body));
			default:
				throw Utils.createInvalidResponseException(restResponse, body);
		}
	}
	
}
