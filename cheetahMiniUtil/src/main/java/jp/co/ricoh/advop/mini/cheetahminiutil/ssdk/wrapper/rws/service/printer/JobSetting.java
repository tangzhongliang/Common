/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;

public class JobSetting extends WritableElement {

	private static final String KEY_PRINT_COLOR				= "printColor";
	private static final String KEY_TONER_SAVE				= "tonerSave";
	private static final String KEY_COPIES					= "copies";
	private static final String KEY_PRINT_PAGES				= "printPages";
	private static final String KEY_SHEET_COLLATE			= "sheetCollate";
	private static final String KEY_PRINT_SIDE				= "printSide";
	private static final String KEY_COMBINE					= "combine";
	private static final String KEY_COMBINE_ORDER			= "combineOrder";
	private static final String KEY_COMBINE_SEPARATOR_LINE	= "combineSeparatorLine";
	private static final String KEY_MAGNIFICATION			= "magnification";
	private static final String KEY_PAPER_TRAY				= "paperTray";
	private static final String KEY_PAPER_SIZE				= "paperSize";
	private static final String KEY_PAPER_SIZE_CUSTOM_X		= "paperSizeCustomX";
	private static final String KEY_PAPER_SIZE_CUSTOM_Y		= "paperSizeCustomY";
	private static final String KEY_PAPER_KIND				= "paperKind";
	private static final String KEY_EDGE_TO_EDGE_PRINT		= "edgeToEdgePrint";
	private static final String KEY_PRINT_RESOLUTION		= "printResolution";
	private static final String KEY_STAPLE					= "staple";
	private static final String KEY_PUNCH					= "punch";
	private static final String KEY_SILENT					= "silent";
	private static final String KEY_PRINTER_SETTING			= "printerSetting";

	JobSetting(Map<String, Object> values) {
		super(values);
	}

	/*
	 * printColor (String)
	 */
	public String getPrintColor() {
		return getStringValue(KEY_PRINT_COLOR);
	}
	public void setPrintColor(String value) {
		setStringValue(KEY_PRINT_COLOR, value);
	}
	public String removePrintColor() {
		return removeStringValue(KEY_PRINT_COLOR);
	}

	/*
	 * tonerSave (Boolean)
	 */
	public Boolean getTonerSave() {
		return getBooleanValue(KEY_TONER_SAVE);
	}
	public void setTonerSave(Boolean value) {
		setBooleanValue(KEY_TONER_SAVE, value);
	}
	public Boolean removeTonerSave() {
		return removeBooleanValue(KEY_TONER_SAVE);
	}

	/*
	 * copies (Number)
	 */
	public Integer getCopies() {
		return getNumberValue(KEY_COPIES);
	}
	public void setCopies(Integer value) {
		setNumberValue(KEY_COPIES, value);
	}
	public Integer removeCopies() {
		return removeNumberValue(KEY_COPIES);
	}

	/*
	 * printPages (String)
	 */
	public String getPrintPages() {
		return getStringValue(KEY_PRINT_PAGES);
	}
	public void setPrintPages(String value) {
		setStringValue(KEY_PRINT_PAGES, value);
	}
	public String removePrintPages() {
		return removeStringValue(KEY_PRINT_PAGES);
	}

	/*
	 * sheetCollate (String)
	 */
	public String getSheetCollate() {
		return getStringValue(KEY_SHEET_COLLATE);
	}
	public void setSheetCollate(String value) {
		setStringValue(KEY_SHEET_COLLATE, value);
	}
	public String removeSheetCollate() {
		return removeStringValue(KEY_SHEET_COLLATE);
	}

	/*
	 * printSide (String)
	 */
	public String getPrintSide() {
		return getStringValue(KEY_PRINT_SIDE);
	}
	public void setPrintSide(String value) {
		setStringValue(KEY_PRINT_SIDE, value);
	}
	public String removePrintSide() {
		return removeStringValue(KEY_PRINT_SIDE);
	}

	/*
	 * combine (String)
	 */
	public String getCombine() {
		return getStringValue(KEY_COMBINE);
	}
	public void setCombine(String value) {
		setStringValue(KEY_COMBINE, value);
	}
	public String removeCombine() {
		return removeStringValue(KEY_COMBINE);
	}

	/*
	 * combineOrder (String)
	 */
	public String getCombineOrder() {
		return getStringValue(KEY_COMBINE_ORDER);
	}
	public void setCombineOrder(String value) {
		setStringValue(KEY_COMBINE_ORDER, value);
	}
	public String removeCombineOrder() {
		return removeStringValue(KEY_COMBINE_ORDER);
	}

	/*
	 * combineSeparatorLine (Boolean)
	 */
	public Boolean getCombineSeparatorLine() {
		return getBooleanValue(KEY_COMBINE_SEPARATOR_LINE);
	}
	public void setCombineSeparatorLine(Boolean value) {
		setBooleanValue(KEY_COMBINE_SEPARATOR_LINE, value);
	}
	public Boolean removeCombineSeparatorLine() {
		return removeBooleanValue(KEY_COMBINE_SEPARATOR_LINE);
	}

	/*
	 * magnification (String)
	 */
	public String getMagnification() {
		return getStringValue(KEY_MAGNIFICATION);
	}
	public void setMagnification(String value) {
		setStringValue(KEY_MAGNIFICATION, value);
	}
	public String removeMagnification() {
		return removeStringValue(KEY_MAGNIFICATION);
	}

	/*
	 * paperTray (String)
	 */
	public String getPaperTray() {
		return getStringValue(KEY_PAPER_TRAY);
	}
	public void setPaperTray(String value) {
		setStringValue(KEY_PAPER_TRAY, value);
	}
	public String removePaperTray() {
		return removeStringValue(KEY_PAPER_TRAY);
	}

	/*
	 * paperSize (String)
	 */
	public String getPaperSize() {
		return getStringValue(KEY_PAPER_SIZE);
	}
	public void setPaperSize(String value) {
		setStringValue(KEY_PAPER_SIZE, value);
	}
	public String removePaperSize() {
		return removeStringValue(KEY_PAPER_SIZE);
	}

	/*
	 * paperSizeCustomX (String)
	 */
	public String getPaperSizeCustomX() {
		return getStringValue(KEY_PAPER_SIZE_CUSTOM_X);
	}
	public void setPaperSizeCustomX(String value) {
		setStringValue(KEY_PAPER_SIZE_CUSTOM_X, value);
	}
	public String removePaperSizeCustomX() {
		return removeStringValue(KEY_PAPER_SIZE_CUSTOM_X);
	}

	/*
	 * paperSizeCustomY (String)
	 */
	public String getPaperSizeCustomY() {
		return getStringValue(KEY_PAPER_SIZE_CUSTOM_Y);
	}
	public void setPaperSizeCustomY(String value) {
		setStringValue(KEY_PAPER_SIZE_CUSTOM_Y, value);
	}
	public String removePaperSizeCustomY() {
		return removeStringValue(KEY_PAPER_SIZE_CUSTOM_Y);
	}

	/*
	 * paperKind (String)
	 */
	public String getPaperKind() {
		return getStringValue(KEY_PAPER_KIND);
	}
	public void setPaperKind(String value) {
		setStringValue(KEY_PAPER_KIND, value);
	}
	public String removePaperKind() {
		return removeStringValue(KEY_PAPER_KIND);
	}

	/*
	 * edgeToEdgePrint (Boolean)
	 */
	public Boolean getEdgeToEdgePrint() {
		return getBooleanValue(KEY_EDGE_TO_EDGE_PRINT);
	}
	public void setEdgeToEdgePrint(Boolean value) {
		setBooleanValue(KEY_EDGE_TO_EDGE_PRINT, value);
	}
	public Boolean removeEdgeToEdgePrint() {
		return removeBooleanValue(KEY_EDGE_TO_EDGE_PRINT);
	}

	/*
	 * printResolution (String)
	 */
	public String getPrintResolution() {
		return getStringValue(KEY_PRINT_RESOLUTION);
	}
	public void setPrintResolution(String value) {
		setStringValue(KEY_PRINT_RESOLUTION, value);
	}
	public String removePrintResolution() {
		return removeStringValue(KEY_PRINT_RESOLUTION);
	}

	/*
	 * staple (String)
	 */
	public String getStaple() {
		return getStringValue(KEY_STAPLE);
	}
	public void setStaple(String value) {
		setStringValue(KEY_STAPLE, value);
	}
	public String removeStaple() {
		return removeStringValue(KEY_STAPLE);
	}

	/*
	 * punch (String)
	 */
	public String getPunch() {
		return getStringValue(KEY_PUNCH);
	}
	public void setPunch(String value) {
		setStringValue(KEY_PUNCH, value);
	}
	public String removePunch() {
		return removeStringValue(KEY_PUNCH);
	}

	/*
	 * silent (Boolean)
	 */
	public Boolean getSilent() {
		return getBooleanValue(KEY_SILENT);
	}
	public void setSilent(Boolean value) {
		setBooleanValue(KEY_SILENT, value);
	}
	public Boolean removeSilent() {
		return removeBooleanValue(KEY_SILENT);
	}

	/*
	 * printerSetting (Object)
	 */
	public PrinterSetting getPrinterSetting() {
		Map<String, Object> value = getObjectValue(KEY_PRINTER_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_PRINTER_SETTING, value);
		}
		return new PrinterSetting(value);
	}
//	public void setPrinterSetting(Map<String, Object> values) {
//		setObjectValue(JobSetting.KEY_PRINTER_SETTING, values);
//	}
	public PrinterSetting removePrinterSetting() {
		Map<String, Object> value = removeObjectValue(KEY_PRINTER_SETTING);
		if (value == null) {
			return null;
		}
		return new PrinterSetting(value);
	}


	public static class PrinterSetting extends WritableElement {

		private static final String KEY_PDF_PASSWORD			= "pdfPassword";
		private static final String KEY_PRIORITY_AUTH_DATA		= "priorityAuthData";
		private static final String KEY_JOB_LOG_NAME			= "jobLogName";
		private static final String KEY_HOST_LOGIN_NAME			= "hostLoginName";
		private static final String KEY_HOST_PORT_NAME			= "hostPortName";
		private static final String KEY_HOST_PRINTER_NAME		= "hostPrinterName";
		private static final String KEY_HOST_NAME				= "hostName";
		private static final String KEY_PRINT_INFO				= "printInfo";
		private static final String KEY_USER_ID					= "userId";
		private static final String KEY_TRACK_ID				= "trackId";
		private static final String KEY_DATE					= "date";
		private static final String KEY_TIME					= "time";
		private static final String KEY_HOST_CHARSET2			= "hostCharset2";

		PrinterSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * pdfPassword (String)
		 */
		public String getPdfPassword() {
			return getStringValue(KEY_PDF_PASSWORD);
		}
		public void setPdfPassword(String value) {
			setStringValue(KEY_PDF_PASSWORD, value);
		}
		public String removePdfPassword() {
			return removeStringValue(KEY_PDF_PASSWORD);
		}

		/*
		 * priorityAuthData (String)
		 */
		public String getPriorityAuthData() {
			return getStringValue(KEY_PRIORITY_AUTH_DATA);
		}
		public void setPriorityAuthData(String value) {
			setStringValue(KEY_PRIORITY_AUTH_DATA, value);
		}
		public String removePriorityAuthData() {
			return removeStringValue(KEY_PRIORITY_AUTH_DATA);
		}

		/*
		 * jobLogName (String)
		 */
		public String getJobLogName() {
			return getStringValue(KEY_JOB_LOG_NAME);
		}
		public void setJobLogName(String value) {
			setStringValue(KEY_JOB_LOG_NAME, value);
		}
		public String removeJobLogName() {
			return removeStringValue(KEY_JOB_LOG_NAME);
		}

		/*
		 * hostLoginName (String)
		 */
		public String getHostLoginName() {
			return getStringValue(KEY_HOST_LOGIN_NAME);
		}
		public void setHostLoginName(String value) {
			setStringValue(KEY_HOST_LOGIN_NAME, value);
		}
		public String removeHostLoginName() {
			return removeStringValue(KEY_HOST_LOGIN_NAME);
		}

		/*
		 * hostPortName (String)
		 */
		public String getHostPortName() {
			return getStringValue(KEY_HOST_PORT_NAME);
		}
		public void setHostPortName(String value) {
			setStringValue(KEY_HOST_PORT_NAME, value);
		}
		public String removeHostPortName() {
			return removeStringValue(KEY_HOST_PORT_NAME);
		}

		/*
		 * hostPrinterName (String)
		 */
		public String getHostPrinterName() {
			return getStringValue(KEY_HOST_PRINTER_NAME);
		}
		public void setHostPrinterName(String value) {
			setStringValue(KEY_HOST_PRINTER_NAME, value);
		}
		public String removeHostPrinterName() {
			return removeStringValue(KEY_HOST_PRINTER_NAME);
		}

		/*
		 * hostName (String)
		 */
		public String getHostName() {
			return getStringValue(KEY_HOST_NAME);
		}
		public void setHostName(String value) {
			setStringValue(KEY_HOST_NAME, value);
		}
		public String removeHostName() {
			return removeStringValue(KEY_HOST_NAME);
		}

		/*
		 * printInfo (String)
		 */
		public String getPrintInfo() {
			return getStringValue(KEY_PRINT_INFO);
		}
		public void setPrintInfo(String value) {
			setStringValue(KEY_PRINT_INFO, value);
		}
		public String removePrintInfo() {
			return removeStringValue(KEY_PRINT_INFO);
		}

		/*
		 * userId (String)
		 */
		public String getUserId() {
			return getStringValue(KEY_USER_ID);
		}
		public void setUserId(String value) {
			setStringValue(KEY_USER_ID, value);
		}
		public String removeUserId() {
			return removeStringValue(KEY_USER_ID);
		}

		/*
		 * trackId (String)
		 */
		public String getTrackId() {
			return getStringValue(KEY_TRACK_ID);
		}
		public void setTrackId(String value) {
			setStringValue(KEY_TRACK_ID, value);
		}
		public String removeTrackId() {
			return removeStringValue(KEY_TRACK_ID);
		}

		/*
		 * date (String)
		 */
		public String getDate() {
			return getStringValue(KEY_DATE);
		}
		public void setDate(String value) {
			setStringValue(KEY_DATE, value);
		}
		public String removeDate() {
			return removeStringValue(KEY_DATE);
		}

		/*
		 * time (String)
		 */
		public String getTime() {
			return getStringValue(KEY_TIME);
		}
		public void setTime(String value) {
			setStringValue(KEY_TIME, value);
		}
		public String removeTime() {
			return removeStringValue(KEY_TIME);
		}

		/*
		 * hostCharset2 (Number)
		 */
		public Integer getHostCharset2() {
			return getNumberValue(KEY_HOST_CHARSET2);
		}
		public void setHostCharset2(Integer value) {
			setNumberValue(KEY_HOST_CHARSET2, value);
		}
		public Integer removeHostCharset2() {
			return removeNumberValue(KEY_HOST_CHARSET2);
		}

	}

}
