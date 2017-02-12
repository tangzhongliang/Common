/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.counter;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetUserCounterResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_COPY_COUNT		= "copyCount";
	private static final String KEY_PRINTER_COUNT	= "printerCount";
	private static final String KEY_FAX_COUNT		= "faxCount";
	private static final String KEY_SCAN_COUNT		= "scanCount";
	private static final String KEY_PROCESS_COUNT	= "processCount";
	private static final String KEY_TOTAL_COUNT		= "totalCount";

	GetUserCounterResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * copyCount (Object)
	 */
	public CopyCount getCopyCount() {
		Map<String, Object> value = getObjectValue(KEY_COPY_COUNT);
		if (value == null) {
			return null;
		}
		return new CopyCount(value);
	}
	
	/*
	 * printerCount (Object)
	 */
	public PrinterCount getPrinterCount() {
		Map<String, Object> value = getObjectValue(KEY_PRINTER_COUNT);
		if (value == null) {
			return null;
		}
		return new PrinterCount(value);
	}
	
	/*
	 * faxCount (Object)
	 */
	public FaxCount getFaxCount() {
		Map<String, Object> value = getObjectValue(KEY_FAX_COUNT);
		if (value == null) {
			return null;
		}
		return new FaxCount(value);
	}
	
	/*
	 * scanCount (Object)
	 */
	public ScanCount getScanCount() {
		Map<String, Object> value = getObjectValue(KEY_SCAN_COUNT);
		if (value == null) {
			return null;
		}
		return new ScanCount(value);
	}
	
	/*
	 * processCount (Object)
	 */
	public ProcessCount getProcessCount() {
		Map<String, Object> value = getObjectValue(KEY_PROCESS_COUNT);
		if (value == null) {
			return null;
		}
		return new ProcessCount(value);
	}
	
	/*
	 * totalCount (Object)
	 */
	public TotalCount getTotalCount() {
		Map<String, Object> value = getObjectValue(KEY_TOTAL_COUNT);
		if (value == null) {
			return null;
		}
		return new TotalCount(value);
	}
	
	
	public static class CopyCount extends Element {
		
		private static final String KEY_BLACK_LARGE			= "blackLarge";
		private static final String KEY_BLACK_SMALL			= "blackSmall";
		private static final String KEY_MONO_COLOR_LARGE	= "monoColorLarge";
		private static final String KEY_MONO_COLOR_SMALL	= "monoColorSmall";
		private static final String KEY_TWIN_COLOR_LARGE	= "twinColorLarge";
		private static final String KEY_TWIN_COLOR_SMALL	= "twinColorSmall";
		private static final String KEY_FULL_COLOR_LARGE	= "fullColorLarge";
		private static final String KEY_FULL_COLOR_SMALL	= "fullColorSmall";
		private static final String KEY_DUPLEX_SHEET    	= "duplexSheet"; // SmartSDK V2.00
		private static final String KEY_COMBINE_PAGE    	= "combinePage"; // SmartSDK V2.00
		
		CopyCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * blackLarge (Number)
		 */
		public Integer getBlackLarge() {
			return getNumberValue(KEY_BLACK_LARGE);
		}
		
		/*
		 * blackSmall (Number)
		 */
		public Integer getBlackSmall() {
			return getNumberValue(KEY_BLACK_SMALL);
		}
		
		/*
		 * monoColorLarge (Number)
		 */
		public Integer getMonoColorLarge() {
			return getNumberValue(KEY_MONO_COLOR_LARGE);
		}
		
		/*
		 * monoColorSmall (Number)
		 */
		public Integer getMonoColorSmall() {
			return getNumberValue(KEY_MONO_COLOR_SMALL);
		}
		
		/*
		 * twinColorLarge (Number)
		 */
		public Integer getTwinColorLarge() {
			return getNumberValue(KEY_TWIN_COLOR_LARGE);
		}
		
		/*
		 * twinColorSmall (Number)
		 */
		public Integer getTwinColorSmall() {
			return getNumberValue(KEY_TWIN_COLOR_SMALL);
		}
		
		/*
		 * fullColorLarge (Number)
		 */
		public Integer getFullColorLarge() {
			return getNumberValue(KEY_FULL_COLOR_LARGE);
		}
		
		/*
		 * fullColorSmall (Number)
		 */
		public Integer getFullColorSmall() {
			return getNumberValue(KEY_FULL_COLOR_SMALL);
		}
	
		/*
		 * duplexSheet (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getDuplexSheet() {
			return getNumberValue(KEY_DUPLEX_SHEET);
		}	
		
		/*
		 * combinePage (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getCombinePage() {
			return getNumberValue(KEY_COMBINE_PAGE);
		}	
		
	}
	
	public static class PrinterCount extends Element {
		
		private static final String KEY_BLACK_LARGE			= "blackLarge";
		private static final String KEY_BLACK_SMALL			= "blackSmall";
		private static final String KEY_MONO_COLOR_LARGE	= "monoColorLarge";
		private static final String KEY_MONO_COLOR_SMALL	= "monoColorSmall";
		private static final String KEY_TWIN_COLOR_LARGE	= "twinColorLarge";
		private static final String KEY_TWIN_COLOR_SMALL	= "twinColorSmall";
		private static final String KEY_FULL_COLOR_LARGE	= "fullColorLarge";
		private static final String KEY_FULL_COLOR_SMALL	= "fullColorSmall";
		private static final String KEY_DUPLEX_SHEET    	= "duplexSheet"; // SmartSDK V2.00
		private static final String KEY_COMBINE_PAGE    	= "combinePage"; // SmartSDK V2.00
		
		PrinterCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * blackLarge (Number)
		 */
		public Integer getBlackLarge() {
			return getNumberValue(KEY_BLACK_LARGE);
		}
		
		/*
		 * blackSmall (Number)
		 */
		public Integer getBlackSmall() {
			return getNumberValue(KEY_BLACK_SMALL);
		}
		
		/*
		 * monoColorLarge (Number)
		 */
		public Integer getMonoColorLarge() {
			return getNumberValue(KEY_MONO_COLOR_LARGE);
		}
		
		/*
		 * monoColorSmall (Number)
		 */
		public Integer getMonoColorSmall() {
			return getNumberValue(KEY_MONO_COLOR_SMALL);
		}
		
		/*
		 * twinColorLarge (Number)
		 */
		public Integer getTwinColorLarge() {
			return getNumberValue(KEY_TWIN_COLOR_LARGE);
		}
		
		/*
		 * twinColorSmall (Number)
		 */
		public Integer getTwinColorSmall() {
			return getNumberValue(KEY_TWIN_COLOR_SMALL);
		}
		
		/*
		 * fullColorLarge (Number)
		 */
		public Integer getFullColorLarge() {
			return getNumberValue(KEY_FULL_COLOR_LARGE);
		}
		
		/*
		 * fullColorSmall (Number)
		 */
		public Integer getFullColorSmall() {
			return getNumberValue(KEY_FULL_COLOR_SMALL);
		}
		
		/*
		 * duplexSheet (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getDuplexSheet() {
			return getNumberValue(KEY_DUPLEX_SHEET);
		}	
		
		/*
		 * combinePage (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getCombinePage() {
			return getNumberValue(KEY_COMBINE_PAGE);
		}
		
	}
	
	public static class FaxCount extends Element {
		
		private static final String KEY_BLACK_LARGE			= "blackLarge";
		private static final String KEY_BLACK_SMALL			= "blackSmall";
		private static final String KEY_MONO_COLOR_LARGE	= "monoColorLarge";
		private static final String KEY_MONO_COLOR_SMALL	= "monoColorSmall";
		private static final String KEY_PAGE				= "page";
		private static final String KEY_CHARGE				= "charge";
		
		FaxCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * blackLarge (Number)
		 */
		public Integer getBlackLarge() {
			return getNumberValue(KEY_BLACK_LARGE);
		}
		
		/*
		 * blackSmall (Number)
		 */
		public Integer getBlackSmall() {
			return getNumberValue(KEY_BLACK_SMALL);
		}
		
		/*
		 * monoColorLarge (Number)
		 */
		public Integer getMonoColorLarge() {
			return getNumberValue(KEY_MONO_COLOR_LARGE);
		}
		
		/*
		 * monoColorSmall (Number)
		 */
		public Integer getMonoColorSmall() {
			return getNumberValue(KEY_MONO_COLOR_SMALL);
		}
		
		/*
		 * page (Number)
		 */
		public Integer getPage() {
			return getNumberValue(KEY_PAGE);
		}
		
		/*
		 * charge (Number)
		 */
		public Integer getCharge() {
			return getNumberValue(KEY_CHARGE);
		}
		
	}
	
	public static class ScanCount extends Element {
		
		private static final String KEY_BLACK_LARGE			= "blackLarge";
		private static final String KEY_BLACK_SMALL			= "blackSmall";
		private static final String KEY_FULL_COLOR_LARGE	= "fullColorLarge";
		private static final String KEY_FULL_COLOR_SMALL	= "fullColorSmall";
		
		ScanCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * blackLarge (Number)
		 */
		public Integer getBlackLarge() {
			return getNumberValue(KEY_BLACK_LARGE);
		}
		
		/*
		 * blackSmall (Number)
		 */
		public Integer getBlackSmall() {
			return getNumberValue(KEY_BLACK_SMALL);
		}
		
		/*
		 * fullColorLarge (Number)
		 */
		public Integer getFullColorLarge() {
			return getNumberValue(KEY_FULL_COLOR_LARGE);
		}
		
		/*
		 * fullColorSmall (Number)
		 */
		public Integer getFullColorSmall() {
			return getNumberValue(KEY_FULL_COLOR_SMALL);
		}
		
	}
	
	public static class ProcessCount extends Element {
		
		private static final String KEY_BLACK	= "black";
		private static final String KEY_YMC		= "ymc";
		
		ProcessCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * black (Number)
		 */
		public Integer getBlack() {
			return getNumberValue(KEY_BLACK);
		}
		
		/*
		 * ymc (Number)
		 */
		public Integer getYmc() {
			return getNumberValue(KEY_YMC);
		}
		
	}
	
	public static class TotalCount extends Element {
		
		private static final String KEY_BLACK_TOTAL		= "blackTotal";
		private static final String KEY_BLACK_ACCOUNT	= "blackAccount";
		private static final String KEY_COLOR_TOTAL		= "colorTotal";
		private static final String KEY_COLOR_ACCOUNT	= "colorAccount";
		private static final String KEY_SCAN_TOTAL		= "scanTotal";

		TotalCount(Map<String, Object> values) {
			super(values);
		}
		
		/*
		 * blackTotal (Number)
		 */
		public Integer getBlackTotal() {
			return getNumberValue(KEY_BLACK_TOTAL);
		}
		
		/*
		 * blackAccount (Number)
		 */
		public Integer getBlackAccount() {
			return getNumberValue(KEY_BLACK_ACCOUNT);
		}
		
		/*
		 * colorTotal (Number)
		 */
		public Integer getColorTotal() {
			return getNumberValue(KEY_COLOR_TOTAL);
		}
		
		/*
		 * colorAccount (Number)
		 */
		public Integer getColorAccount() {
			return getNumberValue(KEY_COLOR_ACCOUNT);
		}
		
		/*
		 * scanTotal (Number)
		 */
		public Integer getScanTotal() {
			return getNumberValue(KEY_SCAN_TOTAL);
		}
		
	}
	
}
