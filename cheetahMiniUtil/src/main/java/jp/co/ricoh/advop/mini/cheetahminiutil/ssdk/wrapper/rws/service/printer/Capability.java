/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.CapabilityElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MagnificationElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MaxLengthElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public class Capability extends CapabilityElement {

	private static final String KEY_PRINT_COLOR_LIST				= "printColorList";
	private static final String KEY_TONER_SAVE_LIST					= "tonerSaveList";
	private static final String KEY_COPIES_RANGE					= "copiesRange";
	private static final String KEY_PRINT_PAGES_LENGTH				= "printPagesLength";
	private static final String KEY_SHEET_COLLATE_LIST				= "sheetCollateList";
	private static final String KEY_PRINT_SIDE_LIST					= "printSideList";
	private static final String KEY_COMBINE_LIST					= "combineList";
	private static final String KEY_COMBINE_ORDER_LIST				= "combineOrderList";
	private static final String KEY_COMBINE_SEPARATOR_LINE_LIST		= "combineSeparatorLineList";
	private static final String KEY_MAGNIFICATION_RANGE				= "magnificationRange";
	private static final String KEY_PAPER_TRAY_LIST					= "paperTrayList";
	private static final String KEY_PAPER_SIZE_LIST					= "paperSizeList";
	private static final String KEY_PAPER_KIND_LIST					= "paperKindList";
	private static final String KEY_EDGE_TO_EDGE_PRINT_LIST			= "edgeToEdgePrintList";
	private static final String KEY_PRINT_RESOLUTION_LIST			= "printResolutionList";
	private static final String KEY_STAPLE_LIST						= "stapleList";
	private static final String KEY_PUNCH_LIST						= "punchList";
	private static final String KEY_SILENT_LIST						= "silentList";
	private static final String KEY_PRINTER_SETTING_CAPABILITY		= "printerSettingCapability";

	Capability(Map<String, Object> values) {
		super(values);
	}

	/*
	 * printColorList (Array[String])
	 */
	public List<String> getPrintColorList() {
		return getArrayValue(KEY_PRINT_COLOR_LIST);
	}

	/*
	 * tonerSaveList (Array[Boolean])
	 */
	public List<Boolean> getTonerSaveList() {
		return getArrayValue(KEY_TONER_SAVE_LIST);
	}

	/*
	 * copiesRange (Range)
	 */
	public RangeElement getCopiesRange() {
		return getRangeValue(KEY_COPIES_RANGE);
	}

	/*
	 * printPagesLength (MaxLength)
	 */
	public MaxLengthElement getPrintPagesLength() {
		return getMaxLengthValue(KEY_PRINT_PAGES_LENGTH);
	}

	/*
	 * sheetCollateList (Array[String])
	 */
	public List<String> getSheetCollateList() {
		return getArrayValue(KEY_SHEET_COLLATE_LIST);
	}

	/*
	 * printSideList (Array[String])
	 */
	public List<String> getPrintSideList() {
		return getArrayValue(KEY_PRINT_SIDE_LIST);
	}

	/*
	 * combineList (Array[String])
	 */
	public List<String> getCombineList() {
		return getArrayValue(KEY_COMBINE_LIST);
	}

	/*
	 * combineOrderList (Array[String])
	 */
	public List<String> getCombineOrderList() {
		return getArrayValue(KEY_COMBINE_ORDER_LIST);
	}

	/*
	 * combineSeparatorLineList (Array[Boolean])
	 */
	public List<Boolean> getCombineSeparatorLineList() {
		return getArrayValue(KEY_COMBINE_SEPARATOR_LINE_LIST);
	}

	/*
	 * magnificationRange (Magnification)
	 */
	public MagnificationElement getMagnificationRange() {
		return getMagnificationValue(KEY_MAGNIFICATION_RANGE);
	}

	/*
	 * paperTrayList (Array[String])
	 */
	public List<String> getPaperTrayList() {
		return getArrayValue(KEY_PAPER_TRAY_LIST);
	}

	/*
	 * paperSizeList (Array[String])
	 */
	public List<String> getPaperSizeList() {
		return getArrayValue(KEY_PAPER_SIZE_LIST);
	}

	/*
	 * paperKindList (Array[String])
	 */
	public List<String> getPaperKindList() {
		return getArrayValue(KEY_PAPER_KIND_LIST);
	}

	/*
	 * edgeToEdgePrintList (Array[Boolean])
	 */
	public List<Boolean> getEdgeToEdgePrintList() {
		return getArrayValue(KEY_EDGE_TO_EDGE_PRINT_LIST);
	}

	/*
	 * printResolutionList (Array[String])
	 */
	public List<String> getPrintResolutionList() {
		return getArrayValue(KEY_PRINT_RESOLUTION_LIST);
	}

	/*
	 * stapleList (Array[String])
	 */
	public List<String> getStapleList() {
		return getArrayValue(KEY_STAPLE_LIST);
	}

	/*
	 * punchList (Array[String])
	 */
	public List<String> getPunchList() {
		return getArrayValue(KEY_PUNCH_LIST);
	}

	/*
	 * silentList (Array[Boolean])
	 */
	public List<Boolean> getSilentList() {
		return getArrayValue(KEY_SILENT_LIST);
	}

	/*
	 * printerSettingCapability (Object)
	 */
	public PrinterSettingCapability getPrinterSettingCapability() {
		Map<String, Object> values = getObjectValue(KEY_PRINTER_SETTING_CAPABILITY);
		if (values == null) {
			return null;
		}
		return new PrinterSettingCapability(values);
	}


	public static class PrinterSettingCapability extends CapabilityElement {

		private static final String KEY_PDF_PASSWORD_LENGTH			= "pdfPasswordLength";
		private static final String KEY_PRIORITY_AUTH_DATA_LIST		= "priorityAuthDataList";
		private static final String KEY_JOB_LOG_NAME_LENGTH			= "jobLogNameLength";
		private static final String KEY_HOST_LOGIN_NAME_LENGTH		= "hostLoginNameLength";
		private static final String KEY_HOST_PORT_NAME_LENGTH		= "hostPortNameLength";
		private static final String KEY_HOST_PRINTER_NAME_LENGTH	= "hostPrinterNameLength";
		private static final String KEY_HOST_NAME_LENGTH			= "hostNameLength";
		private static final String KEY_PRINT_INFO_LENGTH			= "printInfoLength";
		private static final String KEY_USER_ID_LENGTH				= "userIdLength";
		private static final String KEY_TRACK_ID_LENGTH				= "trackIdLength";
		private static final String KEY_DATE_LENGTH					= "dateLength";
		private static final String KEY_TIME_LENGTH					= "timeLength";
		private static final String KEY_HOST_CHARSET2_RANGE			= "hostCharset2Range";

		PrinterSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * pdfPasswordLength (MaxLength)
		 */
		public MaxLengthElement getPdfPasswordLength() {
			return getMaxLengthValue(KEY_PDF_PASSWORD_LENGTH);
		}

		/*
		 * priorityAuthDataList (Array[String])
		 */
		public List<String> getPriorityAuthDataList() {
			return getArrayValue(KEY_PRIORITY_AUTH_DATA_LIST);
		}

		/*
		 * jobLogNameLength (MaxLength)
		 */
		public MaxLengthElement getJobLogNameLength() {
			return getMaxLengthValue(KEY_JOB_LOG_NAME_LENGTH);
		}

		/*
		 * hostLoginNameLength (MaxLength)
		 */
		public MaxLengthElement getHostLoginNameLength() {
			return getMaxLengthValue(KEY_HOST_LOGIN_NAME_LENGTH);
		}

		/*
		 * hostPortNameLength (MaxLength)
		 */
		public MaxLengthElement getHostPortNameLength() {
			return getMaxLengthValue(KEY_HOST_PORT_NAME_LENGTH);
		}

		/*
		 * hostPrinterNameLength (MaxLength)
		 */
		public MaxLengthElement getHostPrinterNameLength() {
			return getMaxLengthValue(KEY_HOST_PRINTER_NAME_LENGTH);
		}

		/*
		 * hostNameLength (MaxLength)
		 */
		public MaxLengthElement getHostNameLength() {
			return getMaxLengthValue(KEY_HOST_NAME_LENGTH);
		}

		/*
		 * printInfoLength (MaxLength)
		 */
		public MaxLengthElement getPrintInfoLength() {
			return getMaxLengthValue(KEY_PRINT_INFO_LENGTH);
		}

		/*
		 * userIdLength (MaxLength)
		 */
		public MaxLengthElement getUserIdLength() {
			return getMaxLengthValue(KEY_USER_ID_LENGTH);
		}

		/*
		 * trackIdLength (MaxLength)
		 */
		public MaxLengthElement getTrackIdLength() {
			return getMaxLengthValue(KEY_TRACK_ID_LENGTH);
		}

		/*
		 * dateLength (MaxLength)
		 */
		public MaxLengthElement getDateLength() {
			return getMaxLengthValue(KEY_DATE_LENGTH);
		}

		/*
		 * timeLength (MaxLength)
		 */
		public MaxLengthElement getTimeLength() {
			return getMaxLengthValue(KEY_TIME_LENGTH);
		}

		/*
		 * hostCharset2Range (Range)
		 */
		public RangeElement getHostCharset2Range() {
			return getRangeValue(KEY_HOST_CHARSET2_RANGE);
		}

	}
}
