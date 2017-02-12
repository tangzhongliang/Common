/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * エラー状態のレスポンスボディを示すクラス
 */
public class ErrorResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_ERRORS	= "errors";
	
	public ErrorResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * errors (Array[Object])
	 */
	public ErrorsArray getErrors() {
		List<Map<String, Object>> value = getArrayValue(KEY_ERRORS);
		if (value == null) {
			value = Collections.emptyList();
		}
		return new ErrorsArray(value);
	}
	
	public static class ErrorsArray extends ArrayElement<Errors> {
		
		ErrorsArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected Errors createElement(Map<String, Object> values) {
			return new Errors(values);
		}
		
	}
	
	public static class Errors extends Element {
		
		private static final String KEY_MESSAGE_ID	= "message_id";
		private static final String KEY_MESSAGE		= "message";
		
		Errors (Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * messageId (String)
		 */
		public String getMessageId() {
			return getStringValue(KEY_MESSAGE_ID);
		}
		
		/*
		 * message (String)
		 */
		public String getMessage() {
			return getStringValue(KEY_MESSAGE);
		}
		
	}
	
}
