/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.counter;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetEcoCounterResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_ECO_COUNT		= "ecoCount";
	private static final String KEY_ECO_HIST		= "ecoHist";

	GetEcoCounterResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * ecoCount (Object)
	 */
	public EcoCount getEcoCount() {
		Map<String, Object> value = getObjectValue(KEY_ECO_COUNT);
		if (value == null) {
			return null;
		}
		return new EcoCount(value);
	}
	
	/*
	 * ecoHist (Object)
	 */
	public EcoHist getEcoHist() {
		Map<String, Object> value = getObjectValue(KEY_ECO_HIST);
		if (value == null) {
			return null;
		}
		return new EcoHist(value);
	}
	
	
	public static class EcoCount extends Element {
		
		private static final String KEY_TOTAL_PAGE				= "totalPage";
		private static final String KEY_COLOR_PAGE				= "colorPage";
		private static final String KEY_DUPLEX_SHEET			= "duplexSheet";
		private static final String KEY_COMBINE_PAGE			= "combinePage";
		private static final String KEY_COLOR_RATE				= "colorRate";
		private static final String KEY_DUPLEX_RATE				= "duplexRate";
		private static final String KEY_COMBINE_RATE			= "combineRate";
		private static final String KEY_PAPER_REDUCE_RATE		= "paperReduceRate";
		private static final String KEY_COUNT_START 			= "countStart"; // SmartSDK V2.00
		private static final String KEY_COUNT_END       		= "countEnd";   // SmartSDK V2.00
		
		EcoCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * totalPage (Number)
		 */
		public Integer getTotalPage() {
			return getNumberValue(KEY_TOTAL_PAGE);
		}
		
		/*
		 * colorPage (Number)
		 */
		public Integer getColorPage() {
			return getNumberValue(KEY_COLOR_PAGE);
		}
		
		/*
		 * duplexSheet (Number)
		 */
		public Integer getDuplexSheet() {
			return getNumberValue(KEY_DUPLEX_SHEET);
		}
		
		/*
		 * combinePage (Number)
		 */
		public Integer getCombinePage() {
			return getNumberValue(KEY_COMBINE_PAGE);
		}
		
		/*
		 * colorRate (Number)
		 */
		public Integer getColorRate() {
			return getNumberValue(KEY_COLOR_RATE);
		}
		
		/*
		 * duplexRate (Number)
		 */
		public Integer getDuplexRate() {
			return getNumberValue(KEY_DUPLEX_RATE);
		}
		
		/*
		 * combineRate (Number)
		 */
		public Integer getCombineRate() {
			return getNumberValue(KEY_COMBINE_RATE);
		}
		
		/*
		 * paperReduceRate (Number)
		 */
		public Integer getPaperReduceRate() {
			return getNumberValue(KEY_PAPER_REDUCE_RATE);
		}
		
		/*
		 * countStart (String)
		 * @since SmartSDK V2.00
		 */
		public String getCountStart() {
			return getStringValue(KEY_COUNT_START);
		}		
	
		/*
		 * countEnd (String)
		 * @since SmartSDK V2.00
		 */
		public String getCountEnd() {
			return getStringValue(KEY_COUNT_END);
		}
	}
	
	public static class EcoHist extends Element {
		
		private static final String KEY_TOTAL_PAGE_LATEST			= "totalPageLatest";
		private static final String KEY_COLOR_PAGE_LATEST			= "colorPageLatest";
		private static final String KEY_DUPLEX_SHEET_LATEST			= "duplexSheetLatest";
		private static final String KEY_COMBINE_PAGE_LATEST			= "combinePageLatest";
		private static final String KEY_COLOR_RATE_LATEST			= "colorRateLatest";
		private static final String KEY_DUPLEX_RATE_LATEST			= "duplexRateLatest";
		private static final String KEY_COMBINE_RATE_LATEST			= "combineRateLatest";
		private static final String KEY_PAPER_REDUCE_RATE_LATEST	= "paperReduceRateLatest";
		private static final String KEY_COUNT_START_LATEST			= "countStartLatest"; // SmartSDK V2.00
		private static final String KEY_COUNT_END_LATEST	        = "countEndLatest";	  // SmartSDK V2.00	

		EcoHist(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * totalPageLatest (Number)
		 */
		public Integer getTotalPageLatest() {
			return getNumberValue(KEY_TOTAL_PAGE_LATEST);
		}
		
		/*
		 * colorPageLatest (Number)
		 */
		public Integer getColorPageLatest() {
			return getNumberValue(KEY_COLOR_PAGE_LATEST);
		}
		
		/*
		 * duplexSheetLatest (Number)
		 */
		public Integer getDuplexSheetLatest() {
			return getNumberValue(KEY_DUPLEX_SHEET_LATEST);
		}
		
		/*
		 * combinePageLatest (Number)
		 */
		public Integer getCombinePageLatest() {
			return getNumberValue(KEY_COMBINE_PAGE_LATEST);
		}
		
		/*
		 * colorRateLatest (Number)
		 */
		public Integer getColorRateLatest() {
			return getNumberValue(KEY_COLOR_RATE_LATEST);
		}
		
		/*
		 * duplexRateLatest (Number)
		 */
		public Integer getDuplexRateLatest() {
			return getNumberValue(KEY_DUPLEX_RATE_LATEST);
		}
		
		/*
		 * combineRateLatest (Number)
		 */
		public Integer getCombineRateLatest() {
			return getNumberValue(KEY_COMBINE_RATE_LATEST);
		}
		
		/*
		 * paperReduceRateLatest (Number)
		 */
		public Integer getPaperReduceRateLatest() {
			return getNumberValue(KEY_PAPER_REDUCE_RATE_LATEST);
		}
		
		/*
		 * countStartLatest (String)
		 * @since SmartSDK V2.00
		 */
		public String getCountStartLatest() {
			return getStringValue(KEY_COUNT_START_LATEST);
		}		
	
		/*
		 * countEndLatest (String)
		 * @since SmartSDK V2.00
		 */
		public String getCountEndLatest() {
			return getStringValue(KEY_COUNT_END_LATEST);
		}
		
	}
	
}
