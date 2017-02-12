/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class CreatePrintFileResponseBody extends Element implements ResponseBody {

	private static final String KEY_FILE_ID		= "fileId";

	CreatePrintFileResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * fileId (String)
	 */
	public String getFileId() {
		return getStringValue(KEY_FILE_ID);
	}

}
