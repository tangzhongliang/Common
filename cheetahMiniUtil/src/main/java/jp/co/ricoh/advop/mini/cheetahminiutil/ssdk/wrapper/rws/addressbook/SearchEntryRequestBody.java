/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;

/*
 * @since SmartSDK V2.00
 */
public class SearchEntryRequestBody extends WritableElement implements RequestBody {

	private static final String CONTENT_TYPE_JSON			= "application/json; charset=utf-8";

	private static final String KEY_ENTRIES_FILTER			= "entriesFilter";
	private static final String KEY_SEARCH_CONDITIONS		= "searchConditions";


	public SearchEntryRequestBody() {
		super(new HashMap<String, Object>());
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_JSON;
	}

	@Override
	public String toEntityString() {
		try {
			return JsonUtils.getEncoder().encode(values);
		} catch (EncodedException e) {
		    LogC.w(e);
			return "{}";
		}
	}

	/*
	 * entriesFilter (Array[String])
	 * @since SmartSDK V2.00
	 */
	public List<String> getEntriesFilter() {
		return getArrayValue(KEY_ENTRIES_FILTER);
	}
	public void setEntriesFilter(List<String> value) {
		setArrayValue(KEY_ENTRIES_FILTER, value);
	}
	public List<String> removeEntriesFilter() {
		return removeArrayValue(KEY_ENTRIES_FILTER);
	}

	/*
	 * searchConditions (Array[Object])
	 * @since SmartSDK V2.00
	 */
	public SearchConditionArray getSearchConditions() {
		List<Map<String, Object>> value = getArrayValue(KEY_SEARCH_CONDITIONS);
		if (value == null) {
			value = Utils.createElementList();
			setArrayValue(KEY_SEARCH_CONDITIONS, value);
		}
		return new SearchConditionArray(value);
	}
//	public void setSearchConditions(SearchConditionArray value) {
//		throw new UnsupportedOperationException();
//	}
	public SearchConditionArray removeSearchConditions() {
		List<Map<String, Object>> value = removeArrayValue(KEY_SEARCH_CONDITIONS);
		if (value == null) {
			return null;
		}
		return new SearchConditionArray(value);
	}


	/*
	 * @since SmartSDK V2.00
	 */
	public static class SearchConditionArray extends ArrayElement<SearchCondition> {

		SearchConditionArray(List<Map<String, Object>> list) {
			super(list);
		}

		/*
		 * @since SmartSDK V2.00
		 */
		public boolean add(SearchCondition value) {
			if (value == null) {
				throw new NullPointerException("value must not be null.");
			}
			return list.add(value.cloneValues());
		}

		/*
		 * @since SmartSDK V2.00
		 */
		public SearchCondition remove(int index) {
			Map<String, Object> value = list.remove(index);
			if (value == null) {
				return null;
			}
			return createElement(value);
		}

		/*
		 * @since SmartSDK V2.00
		 */
		public void clear() {
			list.clear();
		}

		@Override
		protected SearchCondition createElement(Map<String, Object> values) {
			return new SearchCondition(values);
		}

	}

	/*
	 * @since SmartSDK V2.00
	 */
	public static class SearchCondition extends WritableElement {

		private static final String KEY_MATCHING_METHOD		= "matchingMethod";
		private static final String KEY_MATCHING_OPTION		= "matchingOption";
		private static final String KEY_ITEM				= "item";
		private static final String KEY_DATA				= "data";

		public SearchCondition() {
			super(new HashMap<String, Object>());
		}

		SearchCondition(Map<String, Object> values) {
			super(values);
		}

		/*
		 * matchingMethod (String)
		 * @since SmartSDK V2.00
		 */
		public String getMatchingMethod() {
			return getStringValue(KEY_MATCHING_METHOD);
		}
		public void setMatchingMethod(String value) {
			setStringValue(KEY_MATCHING_METHOD, value);
		}
		public String removeMatchingMethod() {
			return removeStringValue(KEY_MATCHING_METHOD);
		}

		/*
		 * matchingOption (Array[String])
		 * @since SmartSDK V2.00
		 */
		public List<String> getMatchingOption() {
			return getArrayValue(KEY_MATCHING_OPTION);
		}
		public void setMatchingOption(List<String> value) {
			setArrayValue(KEY_MATCHING_OPTION, value);
		}
		public List<String> removeMatchingOption() {
			return removeArrayValue(KEY_MATCHING_OPTION);
		}

		/*
		 * item (String)
		 * @since SmartSDK V2.00
		 */
		public String getItem() {
			return getStringValue(KEY_ITEM);
		}
		public void setItem(String value) {
			setStringValue(KEY_ITEM, value);
		}
		public String removeItem() {
			return removeStringValue(KEY_ITEM);
		}

		/*
		 * data (String)
		 * @since SmartSDK V2.00
		 */
		public String getData() {
			return getStringValue(KEY_DATA);
		}
		public void setData(String value) {
			setStringValue(KEY_DATA, value);
		}
		public String removeData() {
			return removeStringValue(KEY_DATA);
		}

	}

}
