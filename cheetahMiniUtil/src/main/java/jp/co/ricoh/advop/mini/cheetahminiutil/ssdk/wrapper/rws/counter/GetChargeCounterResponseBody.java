/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.counter;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetChargeCounterResponseBody extends Element implements ResponseBody {

	private static final String KEY_CHARGE_COUNT		= "chargeCount";

	GetChargeCounterResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * chargeCount (Object)
	 */
	public ChargeCount getChargeCount() {
		Map<String, Object> value = getObjectValue(KEY_CHARGE_COUNT);
		if (value == null) {
			return null;
		}
		return new ChargeCount(value);
	}

	public static class ChargeCount extends Element {

		private static final String KEY_FULL_COLOR_TOTAL		= "fullColorTotal";
		private static final String KEY_FULL_COLOR_A3_OVER		= "fullColorA3Over";
		private static final String KEY_FULL_COLOR_A3_UNDER		= "fullColorA3Under";
		private static final String KEY_MONO_COLOR_TOTAL		= "monoColorTotal";
		private static final String KEY_COLOR_PRINTER			= "colorPrinter"; // SmartSDK V2.10
		private static final String KEY_MONO_COLOR_PRINTER		= "monoColorPrinter";
		private static final String KEY_TWIN_COLOR_MODE_PRINTER	= "twinColorModePrinter";
		private static final String KEY_FULL_COLOR_GPC			= "fullColorGPC";
		private static final String KEY_FULL_COLOR_COPY			= "fullColorCopy";
		private static final String KEY_BLACK_COPY				= "blackCopy";
		private static final String KEY_FULL_COLOR_PRINTER		= "fullColorPrinter";
		private static final String KEY_BLACK_PRINTER			= "blackPrinter";
		private static final String KEY_COLOR_TOTAL				= "colorTotal";
		private static final String KEY_BLACK_TOTAL				= "blackTotal";
		private static final String KEY_COLOR_TOTAL_A3_OVER		= "colorTotalA3Over";
		private static final String KEY_BLACK_TOTAL_A3_OVER		= "blackTotalA3Over";
		private static final String KEY_COLOR_DEV_TOTAL			= "colorDevTotal";
		private static final String KEY_BLACK_DEV_TOTAL			= "blackDevTotal";
		private static final String KEY_COLOR_DEV_TOTAL_A3_OVER	= "colorDevTotalA3Over";
		private static final String KEY_BLACK_DEV_TOTAL_A3_OVER	= "blackDevTotalA3Over";
		private static final String KEY_COLOR_COVERAGE_1		= "colorCoverage1";
		private static final String KEY_COLOR_COVERAGE_2		= "colorCoverage2";
		private static final String KEY_COLOR_COVERAGE_3		= "colorCoverage3";
		private static final String KEY_COLOR_YMC_COVERAGE_1	= "colorYMCCoverage1";
		private static final String KEY_COLOR_YMC_COVERAGE_2	= "colorYMCCoverage2";
		private static final String KEY_COLOR_YMC_COVERAGE_3	= "colorYMCCoverage3";
		private static final String KEY_ECO_COLOR_TOTAL			= "ecoColorTotal";
		private static final String KEY_ECO_BLACK_TOTAL			= "ecoBlackTotal";
		private static final String KEY_ECONOMY_COLOR_TOTAL		= "economyColorTotal";
		private static final String KEY_SPECIAL_COLOR_DEV_TOTAL	= "specialColorDevTotal"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_COLOR_TOTAL		= "specialColorTotal";    // SmartSDK V2.00
		private static final String KEY_MONO_BLACK_TOTAL		= "monoBlackTotal";
		private static final String KEY_PRINT					= "print";
		private static final String KEY_GPC						= "gpc";
		private static final String KEY_TOTAL_A3_DLT			= "totalA3DLT";



		ChargeCount(Map<String, Object> value) {
			super(value);
		}

		/*
		 * fullColorTotal (Number)
		 */
		public Integer getFullColorTotal() {
			return getNumberValue(KEY_FULL_COLOR_TOTAL);
		}

		/*
		 * fullColorA3Over (Number)
		 */
		public Integer getFullColorA3Over() {
			return getNumberValue(KEY_FULL_COLOR_A3_OVER);
		}

		/*
		 * fullColorA3Under (Number)
		 */
		public Integer getFullColorA3Under() {
			return getNumberValue(KEY_FULL_COLOR_A3_UNDER);
		}

		/*
		 * monoColorTotal (Number)
		 */
		public Integer getMonoColorTotal() {
			return getNumberValue(KEY_MONO_COLOR_TOTAL);
		}
		
		/*
		 * colorPrinter (Number)
		 * @since SmartSDK V2.10
		 */
		public Integer getColorPrinter() {
			return getNumberValue(KEY_COLOR_PRINTER);
		}

		/*
		 * monoColorPrinter (Number)
		 */
		public Integer getMonoColorPrinter() {
			return getNumberValue(KEY_MONO_COLOR_PRINTER);
		}

		/*
		 * twinColorModePrinter (Number)
		 */
		public Integer getTwinColorModePrinter() {
			return getNumberValue(KEY_TWIN_COLOR_MODE_PRINTER);
		}

		/*
		 * fullColorGPC (Number)
		 */
		public Integer getFullColorGPC() {
			return getNumberValue(KEY_FULL_COLOR_GPC);
		}

		/*
		 * fullColorCopy (Number)
		 */
		public Integer getFullColorCopy() {
			return getNumberValue(KEY_FULL_COLOR_COPY);
		}

		/*
		 * blackCopy (Number)
		 */
		public Integer getBlackCopy() {
			return getNumberValue(KEY_BLACK_COPY);
		}

		/*
		 * fullColorPrinter (Number)
		 */
		public Integer getFullColorPrinter() {
			return getNumberValue(KEY_FULL_COLOR_PRINTER);
		}

		/*
		 * blackPrinter (Number)
		 */
		public Integer getBlackPrinter() {
			return getNumberValue(KEY_BLACK_PRINTER);
		}

		/*
		 * colorTotal (Number)
		 */
		public Integer getColorTotal() {
			return getNumberValue(KEY_COLOR_TOTAL);
		}

		/*
		 * blackTotal (Number)
		 */
		public Integer getBlackTotal() {
			return getNumberValue(KEY_BLACK_TOTAL);
		}

		/*
		 * colorTotalA3Over (Number)
		 */
		public Integer getColorTotalA3Over() {
			return getNumberValue(KEY_COLOR_TOTAL_A3_OVER);
		}

		/*
		 * blackTotalA3Over (Number)
		 */
		public Integer getBlackTotalA3Over() {
			return getNumberValue(KEY_BLACK_TOTAL_A3_OVER);
		}

		/*
		 * colorDevTotal (Number)
		 */
		public Integer getColorDevTotal() {
			return getNumberValue(KEY_COLOR_DEV_TOTAL);
		}

		/*
		 * blackDevTotal (Number)
		 */
		public Integer getBlackDevTotal() {
			return getNumberValue(KEY_BLACK_DEV_TOTAL);
		}

		/*
		 * colorDevTotalA3Over (Number)
		 */
		public Integer getColorDevTotalA3Over() {
			return getNumberValue(KEY_COLOR_DEV_TOTAL_A3_OVER);
		}

		/*
		 * blackDevTotalA3Over (Number)
		 */
		public Integer getBlackDevTotalA3Over() {
			return getNumberValue(KEY_BLACK_DEV_TOTAL_A3_OVER);
		}

		/*
		 * colorCoverage1 (Number)
		 */
		public Integer getColorCoverage1() {
			return getNumberValue(KEY_COLOR_COVERAGE_1);
		}

		/*
		 * colorCoverage2 (Number)
		 */
		public Integer getColorCoverage2() {
			return getNumberValue(KEY_COLOR_COVERAGE_2);
		}

		/*
		 * colorCoverage3 (Number)
		 */
		public Integer getColorCoverage3() {
			return getNumberValue(KEY_COLOR_COVERAGE_3);
		}

		/*
		 * colorYMCCoverage1 (Number)
		 */
		public Integer getColorYMCCoverage1() {
			return getNumberValue(KEY_COLOR_YMC_COVERAGE_1);
		}

		/*
		 * colorYMCCoverage2 (Number)
		 */
		public Integer getColorYMCCoverage2() {
			return getNumberValue(KEY_COLOR_YMC_COVERAGE_2);
		}

		/*
		 * colorYMCCoverage3 (Number)
		 */
		public Integer getColorYMCCoverage3() {
			return getNumberValue(KEY_COLOR_YMC_COVERAGE_3);
		}

		/*
		 * ecoColorTotal (Number)
		 */
		public Integer getEcoColorTotal() {
			return getNumberValue(KEY_ECO_COLOR_TOTAL);
		}

		/*
		 * ecoBlackTotal (Number)
		 */
		public Integer getEcoBlackTotal() {
			return getNumberValue(KEY_ECO_BLACK_TOTAL);
		}

		/*
		 * economyColorTotal (Number)
		 */
		public Integer getEconomyColorTotal() {
			return getNumberValue(KEY_ECONOMY_COLOR_TOTAL);
		}
		
		/*
		 * specialColorDevTotal (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialColorDevTotal() {
			return getNumberValue(KEY_SPECIAL_COLOR_DEV_TOTAL);
		}
		
		/*
		 * specialColorTotal (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialColorTotal() {
			return getNumberValue(KEY_SPECIAL_COLOR_TOTAL);
		}
		
		/*
		 * monoBlackTotal (Number)
		 */
		public Integer getMonoBlackTotal() {
			return getNumberValue(KEY_MONO_BLACK_TOTAL);
		}

		/*
		 * print (Number)
		 */
		public Integer getPrint() {
			return getNumberValue(KEY_PRINT);
		}

		/*
		 * gpc (Number)
		 */
		public Integer getGpc() {
			return getNumberValue(KEY_GPC);
		}

		/*
		 * totalA3DLT (Number)
		 */
		public Integer getTotalA3DLT() {
			return getNumberValue(KEY_TOTAL_A3_DLT);
		}

	}

}
