/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.RestResponse;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;

/**
 * @since SmartSDK V2.00
 */
public class CreateJobInvalidResponseException extends InvalidResponseException {

	private final CreateJobErrorResponseBody body;

	public CreateJobInvalidResponseException(RestResponse response, CreateJobErrorResponseBody body) {
		super(response, body);
		this.body = body;
	}

	@Override
	public CreateJobErrorResponseBody getBody() {
		return body;
	}

}
