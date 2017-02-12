/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

/*
 * @since SmartSDK V2.12
 */
public class GetPaperWeightListResponseBody extends Element implements ResponseBody{
	
	private static final String KEY_PAPER_WEIGHT_LIST = "paperWeightList";
	
	GetPaperWeightListResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * paperWeightList (Array[Object])
	 * @since SmartSDK V2.12
	 */
	public PaperWeightList getPaperWeightList() {
		List<Map<String, Object>> value = getArrayValue(KEY_PAPER_WEIGHT_LIST);
		if(value == null) {
			return null;
		}
		return new PaperWeightList(value);
	}
	
	/*
	 * @since SmartSDK V2.12
	 */
	public static class PaperWeight extends Element {
		
		private static final String  KEY_THICKNESS = "thickness";
		private static final String  KEY_WEIGHT_MAX = "weightMax";
		private static final String  KEY_WEIGHT_MIN = "weightMin";
	    
		PaperWeight(Map<String, Object> value) {
			super(value);
		}
		
		/*
		 * thickness (String)
		 * @since SmartSDK V2.12
		 */
	    public String getThickness() {
	    	return getStringValue(KEY_THICKNESS);
	    }
	    
	    /*
	     * weightMax (String)
	     * @since SmartSDK V2.12
	     */
	    public String getWeightMax() {
	    	return getStringValue(KEY_WEIGHT_MAX);
	    }
	    
	    /*
	     * weightMin (String)
	     * @since SmartSDK V2.12
	     */
	    public String getWeightMin() {
	    	return getStringValue(KEY_WEIGHT_MIN);
	    }
	}
	
	/*
	 * @since SmartSDK V2.12
	 */
	public static class PaperWeightList extends ArrayElement<PaperWeight> {
		
		protected PaperWeightList(List<Map<String, Object>> value) {
			super(value);
		}

		@Override
		protected PaperWeight createElement(Map<String, Object> values) {
			return new PaperWeight(values);
		}
	}
}
