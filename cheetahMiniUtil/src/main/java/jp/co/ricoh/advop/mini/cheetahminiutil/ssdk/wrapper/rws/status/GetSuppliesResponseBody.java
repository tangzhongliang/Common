/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.status;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetSuppliesResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_TONERS		= "toners";
	private static final String KEY_WASTE_TONER	= "wasteToner";
	private static final String KEY_STAPLE		= "staple";
	private static final String KEY_PUNCH			= "punch";
	
	GetSuppliesResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * toners (Array[Object])
	 */
	public TonerArray getToners() {
		List<Map<String, Object>> value = getArrayValue(KEY_TONERS);
		if (value == null) {
			return null;
		}
		return new TonerArray(value);
	}
	
	/*
	 * wasteToner (Object)
	 */
	public WasteToner getWasteToner() {
		Map<String, Object> value = getObjectValue(KEY_WASTE_TONER);
		if (value == null) {
			return null;
		}
		return new WasteToner(value);
	}
	
	/*
	 * staple (Object)
	 */
	public Staple getStaple() {
		Map<String, Object> value = getObjectValue(KEY_STAPLE);
		if (value == null) {
			return null;
		}
		return new Staple(value);
	}
	
	/*
	 * punch (Object)
	 */
	public Punch getPunch() {
		Map<String, Object> value = getObjectValue(KEY_PUNCH);
		if (value == null) {
			return null;
		}
		return new Punch(value);
	}
	
	
	public static class TonerArray extends ArrayElement<Toner> {
		
		TonerArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected Toner createElement(Map<String, Object> values) {
			return new Toner(values);
		}
		
	}
	
	public static class Toner extends Element {
		
		private static final String KEY_COLOR		= "color";
		private static final String KEY_REMAIN	= "remain";
		private static final String KEY_STATUS	= "status";
		
		Toner(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * color (String)
		 */
		public String getColor() {
			return getStringValue(KEY_COLOR);
		}
		
		/*
		 * remain (Number)
		 */
		public Integer getRemain() {
			return getNumberValue(KEY_REMAIN);
		}
		
		/*
		 * status (String)
		 */
		public String getStatus() {
			return getStringValue(KEY_STATUS);
		}
		
	}
	
	
	public static class WasteToner extends Element {
		
		private static final String KEY_STATUS = "status";
		
		WasteToner(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * status (String)
		 */
		public String getStatus() {
			return getStringValue(KEY_STATUS);
		}
		
	}
	
	
	public static class Staple extends Element {
		
		private static final String KEY_STATUS = "status";
		
		Staple(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * status (String)
		 */
		public String getStatus() {
			return getStringValue(KEY_STATUS);
		}
		
	}
	
	
	public static class Punch extends Element {
		
		private static final String KEY_STATUS = "status";
		
		Punch(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * status (String)
		 */
		public String getStatus() {
			return getStringValue(KEY_STATUS);
		}
		
	}
	
}
