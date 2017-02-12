/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.counter;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetManageCounterResponseBody extends Element implements ResponseBody {

	private static final String KEY_MANAGE_PRINT_COUNT		= "managePrintCount";
	private static final String KEY_MANAGE_SEND_COUNT		= "manageSendCount";
	private static final String KEY_MANAGE_COVERAGE_COUNT	= "manageCoverageCount";

	GetManageCounterResponseBody(Map<String, Object> values) {
		super(values);
	}

	/*
	 * managePrintCount (Object)
	 */
	public ManagePrintCount getManagePrintCount() {
		Map<String, Object> value = getObjectValue(KEY_MANAGE_PRINT_COUNT);
		if (value == null) {
			return null;
		}
		return new ManagePrintCount(value);
	}

	/*
	 * manageSendCount (Object)
	 */
	public ManageSendCount getManageSendCount() {
		Map<String, Object> value = getObjectValue(KEY_MANAGE_SEND_COUNT);
		if (value == null) {
			return null;
		}
		return new ManageSendCount(value);
	}

	/*
	 * manageCoverageCount (Object)
	 */
	public ManageCoverageCount getManageCoverageCount() {
		Map<String, Object> value = getObjectValue(KEY_MANAGE_COVERAGE_COUNT);
		if (value == null) {
			return null;
		}
		return new ManageCoverageCount(value);
	}


	public static class ManagePrintCount extends Element {

		private static final String KEY_TOTAL				= "total";
		private static final String KEY_COPY_FULL_COLOR		= "copyFullColor";
		private static final String KEY_COPY_BLACK			= "copyBlack";
		private static final String KEY_COPY_MONO_COLOR		= "copyMonoColor";
		private static final String KEY_COPY_TWIN_COLOR		= "copyTwinColor";
		private static final String KEY_PRINTER_FULL_COLOR	= "printerFullColor";
		private static final String KEY_PRINTER_BLACK		= "printerBlack";
		private static final String KEY_PRINTER_MONO_COLOR	= "printerMonoColor";
		private static final String KEY_PRINTER_TWIN_COLOR	= "printerTwinColor";
		private static final String KEY_FAX_BLACK			= "faxBlack";
		private static final String KEY_FAX_MONO_COLOR		= "faxMonoColor";
		private static final String KEY_A3_OVER				= "a3Over";
		private static final String KEY_DUPLEX				= "duplex";
		private static final String KEY_GPC					= "gpc";
		private static final String KEY_PRT_GPC				= "prtGPC";
		private static final String KEY_FULL_COLOR_GPC		= "fullColorGPC";
		private static final String KEY_A2_OVER				= "a2Over";
		private static final String KEY_BANNER              = "banner";        // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER1      = "specialToner1"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER2      = "specialToner2"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER3      = "specialToner3"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER4      = "specialToner4"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER_MONO_COLOR = "specialTonerMonoColor"; // SmartSDK V2.00

		ManagePrintCount(Map<String, Object> values) {
			super(values);
		}

		/*
		 * total (Number)
		 */
		public Integer getTotal() {
			return getNumberValue(KEY_TOTAL);
		}

		/*
		 * copyFullColor (Number)
		 */
		public Integer getCopyFullColor() {
			return getNumberValue(KEY_COPY_FULL_COLOR);
		}

		/*
		 * copyBlack (Number)
		 */
		public Integer getCopyBlack() {
			return getNumberValue(KEY_COPY_BLACK);
		}

		/*
		 * copyMonoColor (Number)
		 */
		public Integer getCopyMonoColor() {
			return getNumberValue(KEY_COPY_MONO_COLOR);
		}

		/*
		 * copyTwinColor (Number)
		 */
		public Integer getCopyTwinColor() {
			return getNumberValue(KEY_COPY_TWIN_COLOR);
		}

		/*
		 * printerFullColor (Number)
		 */
		public Integer getPrinterFullColor() {
			return getNumberValue(KEY_PRINTER_FULL_COLOR);
		}

		/*
		 * printerBlack (Number)
		 */
		public Integer getPrinterBlack() {
			return getNumberValue(KEY_PRINTER_BLACK);
		}

		/*
		 * printerMonoColor (Number)
		 */
		public Integer getPrinterMonoColor() {
			return getNumberValue(KEY_PRINTER_MONO_COLOR);
		}

		/*
		 * printerTwinColor (Number)
		 */
		public Integer getPrinterTwinColor() {
			return getNumberValue(KEY_PRINTER_TWIN_COLOR);
		}

		/*
		 * faxBlack (Number)
		 */
		public Integer getFaxBlack() {
			return getNumberValue(KEY_FAX_BLACK);
		}

		/*
		 * faxMonoColor (Number)
		 */
		public Integer getFaxMonoColor() {
			return getNumberValue(KEY_FAX_MONO_COLOR);
		}

		/*
		 * a3Over (Number)
		 */
		public Integer getA3Over() {
			return getNumberValue(KEY_A3_OVER);
		}

		/*
		 * duplex (Number)
		 */
		public Integer getDuplex() {
			return getNumberValue(KEY_DUPLEX);
		}

		/*
		 * gpc (Number)
		 */
		public Integer getGpc() {
			return getNumberValue(KEY_GPC);
		}

		/*
		 * prtGPC (Number)
		 */
		public Integer getPrtGPC() {
			return getNumberValue(KEY_PRT_GPC);
		}

		/*
		 * fullColorGPC (Number)
		 */
		public Integer getFullColorGPC() {
			return getNumberValue(KEY_FULL_COLOR_GPC);
		}

		/*
		 * a2Over (Number)
		 */
		public Integer getA2Over() {
			return getNumberValue(KEY_A2_OVER);
		}

		/*
		 * banner (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getBanner() {
			return getNumberValue(KEY_BANNER);
		}

		/*
		 * specialToner1 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialToner1() {
			return getNumberValue(KEY_SPECIAL_TONER1);
		}
	
		/*
		 * specialToner2 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialToner2() {
			return getNumberValue(KEY_SPECIAL_TONER2);
		}
		
		/*
		 * specialToner3 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialToner3() {
			return getNumberValue(KEY_SPECIAL_TONER3);
		}
		
		/*
		 * specialToner4 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialToner4() {
			return getNumberValue(KEY_SPECIAL_TONER4);
		}
		
		/*
		 * specialTonerMonoColor (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialTonerMonoColor() {
			return getNumberValue(KEY_SPECIAL_TONER_MONO_COLOR);
		}	
	}

	public static class ManageSendCount extends Element {

		private static final String KEY_FULL_COLOR_TOTAL	= "fullColorTotal";
		private static final String KEY_BLACK_TOTAL			= "blackTotal";
		private static final String KEY_FAX_SEND			= "faxSend";
		private static final String KEY_SCAN_SEND_COLOR		= "scanSendColor";
		private static final String KEY_SCAN_SEND_BLACK		= "scanSendBlack";

		ManageSendCount(Map<String, Object> values) {
			super(values);
		}

		/*
		 * fullColorTotal (Number)
		 */
		public Integer getFullColorTotal() {
			return getNumberValue(KEY_FULL_COLOR_TOTAL);
		}

		/*
		 * blackTotal (Number)
		 */
		public Integer getBlackTotal() {
			return getNumberValue(KEY_BLACK_TOTAL);
		}

		/*
		 * faxSend (Number)
		 */
		public Integer getFaxSend() {
			return getNumberValue(KEY_FAX_SEND);
		}

		/*
		 * scanSendColor (Number)
		 */
		public Integer getScanSendColor() {
			return getNumberValue(KEY_SCAN_SEND_COLOR);
		}

		/*
		 * scanSendBlack (Number)
		 */
		public Integer getScanSendBlack() {
			return getNumberValue(KEY_SCAN_SEND_BLACK);
		}

	}

	public static class ManageCoverageCount extends Element {

		private static final String KEY_COPY_FULL_COLOR_COVERAGE			= "copyFullColorCoverage";
		private static final String KEY_COPY_BLACK_COVERAGE					= "copyBlackCoverage";
		private static final String KEY_COPY_MONO_COLOR_COLOR_COVERAGE		= "copyMonoColorColorCoverage";
		private static final String KEY_COPY_TWIN_COLOR_COLOR_COVERAGE		= "copyTwinColorColorCoverage";
		private static final String KEY_PRINTER_FULL_COLOR_COVERAGE			= "printerFullColorCoverage";
		private static final String KEY_PRINTER_BLACK_COVERAGE				= "printerBlackCoverage";
		private static final String KEY_PRINTER_MONO_COLOR_COLOR_COVERAGE	= "printerMonoColorColorCoverage";
		private static final String KEY_PRINTER_TWIN_COLOR_COLOR_COVERAGE	= "printerTwinColorColorCoverage";
		private static final String KEY_FAX_BLACK_COVERAGE					= "faxBlackCoverage";
		private static final String KEY_FAX_MONO_COLOR_COVERAGE				= "faxMonoColorCoverage";
		private static final String KEY_SPECIAL_TONER_COVERAGE1             = "specialTonerCoverage1"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER_COVERAGE2             = "specialTonerCoverage2"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER_COVERAGE3             = "specialTonerCoverage3"; // SmartSDK V2.00
		private static final String KEY_SPECIAL_TONER_COVERAGE4             = "specialTonerCoverage4"; // SmartSDK V2.00

		ManageCoverageCount(Map<String, Object> values) {
			super(values);
		}

		/*
		 * copyFullColorCoverage (Number)
		 */
		public Integer getCopyFullColorCoverage() {
			return getNumberValue(KEY_COPY_FULL_COLOR_COVERAGE);
		}

		/*
		 * copyBlackCoverage (Number)
		 */
		public Integer getCopyBlackCoverage() {
			return getNumberValue(KEY_COPY_BLACK_COVERAGE);
		}

		/*
		 * copyMonoColorColorCoverage (Number)
		 */
		public Integer getCopyMonoColorColorCoverage() {
			return getNumberValue(KEY_COPY_MONO_COLOR_COLOR_COVERAGE);
		}

		/*
		 * copyTwinColorColorCoverage (Number)
		 */
		public Integer getCopyTwinColorColorCoverage() {
			return getNumberValue(KEY_COPY_TWIN_COLOR_COLOR_COVERAGE);
		}

		/*
		 * printerFullColorCoverage (Number)
		 */
		public Integer getPrinterFullColorCoverage() {
			return getNumberValue(KEY_PRINTER_FULL_COLOR_COVERAGE);
		}

		/*
		 * printerBlackCoverage (Number)
		 */
		public Integer getPrinterBlackCoverage() {
			return getNumberValue(KEY_PRINTER_BLACK_COVERAGE);
		}

		/*
		 * printerMonoColorColorCoverage (Number)
		 */
		public Integer getPrinterMonoColorColorCoverage() {
			return getNumberValue(KEY_PRINTER_MONO_COLOR_COLOR_COVERAGE);
		}

		/*
		 * printerTwinColorColorCoverage (Number)
		 */
		public Integer getPrinterTwinColorColorCoverage() {
			return getNumberValue(KEY_PRINTER_TWIN_COLOR_COLOR_COVERAGE);
		}

		/*
		 * faxBlackCoverage (Number)
		 */
		public Integer getFaxBlackCoverage() {
			return getNumberValue(KEY_FAX_BLACK_COVERAGE);
		}

		/*
		 * faxMonoColorCoverage (Number)
		 */
		public Integer getFaxMonoColorCoverage() {
			return getNumberValue(KEY_FAX_MONO_COLOR_COVERAGE);
		}

		/*
		 * specialTonerCoverage1 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialTonerCoverage1() {
			return getNumberValue(KEY_SPECIAL_TONER_COVERAGE1);
		}
		
		/*
		 * specialTonerCoverage2 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialTonerCoverage2() {
			return getNumberValue(KEY_SPECIAL_TONER_COVERAGE2);
		}
		
		/*
		 * specialTonerCoverage3 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialTonerCoverage3() {
			return getNumberValue(KEY_SPECIAL_TONER_COVERAGE3);
		}
	
		/*
		 * specialTonerCoverage4 (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getSpecialTonerCoverage4() {
			return getNumberValue(KEY_SPECIAL_TONER_COVERAGE4);
		}
	}

}
