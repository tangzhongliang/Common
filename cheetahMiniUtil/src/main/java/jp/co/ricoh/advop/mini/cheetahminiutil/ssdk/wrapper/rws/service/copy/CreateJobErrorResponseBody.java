/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.copy;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ErrorResponseBody;

/*
 * @since SmartSDK V2.00
 */
public class CreateJobErrorResponseBody extends ErrorResponseBody {

	private static final String KEY_MESSAGE_DETAILS		= "messageDetails";

	CreateJobErrorResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * messageDetails (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public MessageDetailArray getMessageDetails() {
		List<Map<String, Object>> value = getArrayValue(KEY_MESSAGE_DETAILS);
		if (value == null) {
			return null;
		}
		return new MessageDetailArray(value);
	}


	/*
	 * @since SmartSDK V2.00
	 */
	public static class MessageDetailArray extends ArrayElement<MessageDetail> {

		MessageDetailArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected MessageDetail createElement(Map<String, Object> values) {
			return new MessageDetail(values);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class MessageDetail extends Element {

		private static final String KEY_MESSAGE					= "message";
		private static final String KEY_ADDITIONAL_INFO			= "additionalInfo";

		MessageDetail(Map<String, Object> values) {
			super(values);
		}

		/*
		 * message (String)
		 * @since SmartSDK V2.00
		 */
		public String getMessage() {
			return getStringValue(KEY_MESSAGE);
		}

		/*
		 * additionalInfo (Array[Object])
		 * @since SmartSDK V2.00
		 */
		public AdditionalInfoArray getAdditionalInfo() {
			List<Map<String, Object>> value = getArrayValue(KEY_ADDITIONAL_INFO);
			if (value == null) {
				return null;
			}
			return new AdditionalInfoArray(value);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class AdditionalInfoArray extends ArrayElement<AdditionalInfo> {

		AdditionalInfoArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected AdditionalInfo createElement(Map<String, Object> values) {
			return new AdditionalInfo(values);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class AdditionalInfo extends Element {

		private static final String KEY_ID						= "id";
		private static final String KEY_VALUE					= "value";

		AdditionalInfo(Map<String, Object> values) {
			super(values);
		}

		/*
		 * id (String)
		 * @since SmartSDK V2.00
		 */
		public String getId() {
			return getStringValue(KEY_ID);
		}

		/*
		 * value (Array[String])
		 * @since SmartSDK V2.00
		 */
		public List<String> getValue() {
			return getArrayValue(KEY_VALUE);
		}

	}

}
