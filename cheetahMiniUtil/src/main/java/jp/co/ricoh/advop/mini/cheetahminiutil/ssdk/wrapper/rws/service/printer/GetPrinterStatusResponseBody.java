/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetPrinterStatusResponseBody extends Element implements ResponseBody {

	private static final String KEY_PRINTER_STATUS			= "printerStatus";
	private static final String KEY_PRINTER_STATUS_REASONS	= "printerStatusReasons";

	public GetPrinterStatusResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * printerStatus (String)
	 */
	public String getPrinterStatus() {
		return getStringValue(KEY_PRINTER_STATUS);
	}
	
	/*
	 * printerStatusReasons (Array[Object])
	 */
	public PrinterStatusReasonsArray getPrinterStatusReasons() {
		List<Map<String, Object>> value = getArrayValue(KEY_PRINTER_STATUS_REASONS);
		if (value == null) {
			return null;
		}
		return new PrinterStatusReasonsArray(value);
	}

	public static class PrinterStatusReasonsArray extends ArrayElement<PrinterStatusReasons> {

		PrinterStatusReasonsArray(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected PrinterStatusReasons createElement(Map<String, Object> values) {
			return new PrinterStatusReasons(values);
		}

	}

	public static class PrinterStatusReasons extends Element {

		private static final String KEY_PRINTER_STATUS_REASON	= "printerStatusReason";
		private static final String KEY_SEVERITY				= "severity";

		PrinterStatusReasons(Map<String, Object> values) {
			super(values);
		}

		/*
		 * printerStatusReason (String)
		 */
		public String getPrinterStatusReason() {
			return getStringValue(KEY_PRINTER_STATUS_REASON);
		}

		/*
		 * severity (String)
		 */
		public String getSeverity() {
			return getStringValue(KEY_SEVERITY);
		}

	}

}
