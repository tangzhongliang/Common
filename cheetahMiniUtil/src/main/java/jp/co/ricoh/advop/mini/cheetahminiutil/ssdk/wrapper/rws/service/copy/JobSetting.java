/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.copy;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;

public class JobSetting extends WritableElement {

	private static final String KEY_AUTO_CORRECT_JOB_SETTING	= "autoCorrectJobSetting";
	private static final String KEY_JOB_MODE					= "jobMode";
	private static final String KEY_JOB_STOPPED_TIMEOUT_PERIOD	= "jobStoppedTimeoutPeriod";    // SmartSDK V1.02
	private static final String KEY_ORIGINAL_SIZE				= "originalSize";
	private static final String KEY_ORIGINAL_SIZE_CUSTOM_X		= "originalSizeCustomX";
	private static final String KEY_ORIGINAL_SIZE_CUSTOM_Y		= "originalSizeCustomY";
	private static final String KEY_ORIGINAL_SIDE				= "originalSide";
	private static final String KEY_ORIGINAL_ORIENTATION		= "originalOrientation";
	private static final String KEY_ORIGINAL_TYPE				= "originalType";
	private static final String KEY_PRINT_COLOR					= "printColor";
	private static final String KEY_SPECIAL_COLOR_SETTING 		= "specialColorSetting";
	private static final String KEY_COPIES						= "copies";
	private static final String KEY_SHEET_COLLATE				= "sheetCollate";
	private static final String KEY_PRINT_SIDE					= "printSide";
	private static final String KEY_COMBINE						= "combine";
	private static final String KEY_COMBINE_ORDER				= "combineOrder";
	private static final String KEY_COMBINE_SEPARATOR_LINE		= "combineSeparatorLine";
	private static final String KEY_COMBINE_SEPARATOR_LINE_SETTING	= "combineSeparatorLineSetting";
	private static final String KEY_MAGNIFICATION				= "magnification";
	private static final String KEY_PAPER_TRAY					= "paperTray";
	private static final String KEY_AUTO_DENSITY				= "autoDensity";
	private static final String KEY_MANUAL_DENSITY				= "manualDensity";
	private static final String KEY_STAPLE						= "staple";
	private static final String KEY_PUNCH						= "punch";
	private static final String KEY_ERASE_CENTER				= "eraseCenter";
	private static final String KEY_ERASE_CENTER_SETTING		= "eraseCenterSetting";
	private static final String KEY_ERASE_BORDER				= "eraseBorder";
	private static final String KEY_ERASE_BORDER_SETTING		= "eraseBorderSetting";
	private static final String KEY_MARGIN						= "margin";
	private static final String KEY_MARGIN_SETTING				= "marginSetting";
	private static final String KEY_CENTERING					= "centering";
	private static final String KEY_ERASE_COLOR					= "eraseColor";
	private static final String KEY_ERASE_COLOR_SETTING			= "eraseColorSetting";
	private static final String KEY_PRESET_STAMP				= "presetStamp";
	private static final String KEY_PRESET_STAMP_SETTING		= "presetStampSetting";
	private static final String KEY_USER_STAMP					= "userStamp";
	private static final String KEY_USER_STAMP_SETTING			= "userStampSetting";
	private static final String KEY_DATE_STAMP					= "dateStamp";
	private static final String KEY_DATE_STAMP_SETTING			= "dateStampSetting";
	private static final String KEY_PAGE_STAMP					= "pageStamp";
	private static final String KEY_PAGE_STAMP_SETTING			= "pageStampSetting";
	private static final String KEY_TEXT_STAMP					= "textStamp";
	private static final String KEY_TEXT_STAMP_SETTING			= "textStampSetting";
	
	public JobSetting(Map<String, Object> values) {
		super(values);
	}
    
	/*
	 * autoCorrectJobSetting (Boolean)
	 */
	public Boolean getAutoCorrectJobSetting() {
		return getBooleanValue(KEY_AUTO_CORRECT_JOB_SETTING);
	}
	public void setAutoCorrectJobSetting(Boolean value) {
		setBooleanValue(KEY_AUTO_CORRECT_JOB_SETTING, value);
	}
	public Boolean removeAutoCorrectJobSetting() {
		return removeBooleanValue(KEY_AUTO_CORRECT_JOB_SETTING);
	}

	/*
	 * jobMode (String)
	 */
	public String getJobMode() {
		return getStringValue(KEY_JOB_MODE);
	}
	public void setJobMode(String value) {
		setStringValue(KEY_JOB_MODE, value);
	}
	public String removeJobMode() {
		return removeStringValue(KEY_JOB_MODE);
	}

	/*
	 * jobStoppedTimeoutPeriod (Number)
	 * @since SmartSDK V1.02
	 */
	public Integer getJobStoppedTimeoutPeriod() {
		return getNumberValue(KEY_JOB_STOPPED_TIMEOUT_PERIOD);
	}
	public void setJobStoppedTimeoutPeriod(Integer value) {
		setNumberValue(KEY_JOB_STOPPED_TIMEOUT_PERIOD, value);
	}
	public Integer removeJobStoppedTimeoutPeriod() {
		return removeNumberValue(KEY_JOB_STOPPED_TIMEOUT_PERIOD);
	}

	/*
	 * originalSize (String)
	 */
	public String getOriginalSize() {
		return getStringValue(KEY_ORIGINAL_SIZE);
	}
	public void setOriginalSize(String value) {
		setStringValue(KEY_ORIGINAL_SIZE, value);
	}
	public String removeOriginalSize() {
		return removeStringValue(KEY_ORIGINAL_SIZE);
	}

	/*
	 * originalSizeCustomX (String)
	 */
	public String getOriginalSizeCustomX() {
		return getStringValue(KEY_ORIGINAL_SIZE_CUSTOM_X);
	}
	public void setOriginalSizeCustomX(String value) {
		setStringValue(KEY_ORIGINAL_SIZE_CUSTOM_X, value);
	}
	public String removeOriginalSizeCustomX() {
		return removeStringValue(KEY_ORIGINAL_SIZE_CUSTOM_X);
	}

	/*
	 * originalSizeCustomY (String)
	 */
	public String getOriginalSizeCustomY() {
		return getStringValue(KEY_ORIGINAL_SIZE_CUSTOM_Y);
	}
	public void setOriginalSizeCustomY(String value) {
		setStringValue(KEY_ORIGINAL_SIZE_CUSTOM_Y, value);
	}
	public String removeOriginalSizeCustomY() {
		return removeStringValue(KEY_ORIGINAL_SIZE_CUSTOM_Y);
	}
    
	/*
	 * originalSide (String)
	 */
	public String getOriginalSide() {
		return getStringValue(KEY_ORIGINAL_SIDE);
	}
	public void setOriginalSide(String value) {
		setStringValue(KEY_ORIGINAL_SIDE, value);
	}
	public String removeOriginalSide() {
		return removeStringValue(KEY_ORIGINAL_SIDE);
	}

	/*
	 * originalOrientation (String)
	 */
	public String getOriginalOrientation() {
		return getStringValue(KEY_ORIGINAL_ORIENTATION);
	}
	public void setOriginalOrientation(String value) {
		setStringValue(KEY_ORIGINAL_ORIENTATION, value);
	}
	public String removeOriginalOrientation() {
		return removeStringValue(KEY_ORIGINAL_ORIENTATION);
	}

	/*
	 * originalType (String)
	 */
	public String getOriginalType() {
		return getStringValue(KEY_ORIGINAL_TYPE);
	}
	public void setOriginalType(String value) {
		setStringValue(KEY_ORIGINAL_TYPE, value);
	}
	public String removeOriginalType() {
		return removeStringValue(KEY_ORIGINAL_TYPE);
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
	 * specialColorSetting (Object)
	 */
	public SpecialColorSetting getSpecialColorSetting() {
		Map<String, Object> value = getObjectValue(KEY_SPECIAL_COLOR_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_SPECIAL_COLOR_SETTING, value);
		}
		return new SpecialColorSetting(value);
	}
//	public void setSpecialColorSetting(SpecialColorSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public SpecialColorSetting removeSpecialColorSetting() {
		Map<String, Object> value = removeObjectValue(KEY_SPECIAL_COLOR_SETTING);
		if (value == null) {
			return null;
		}
		return new SpecialColorSetting(value);
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
	 * combineSeparatorLineSetting (Object)
	 */
	public CombineSeparatorLineSetting getCombineSeparatorLineSetting() {
		Map<String, Object> value = getObjectValue(KEY_COMBINE_SEPARATOR_LINE_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_COMBINE_SEPARATOR_LINE_SETTING, value);
		}
		return new CombineSeparatorLineSetting(value);
	}
//	public void setCombineSeparatorLineSetting(CombineSeparatorLineSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public CombineSeparatorLineSetting removeCombineSeparatorLineSetting() {
		Map<String, Object> value = removeObjectValue(KEY_COMBINE_SEPARATOR_LINE_SETTING);
		if (value == null) {
			return null;
		}
		return new CombineSeparatorLineSetting(value);
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
	 * autoDensity (Boolean)
	 */
	public Boolean getAutoDensity() {
		return getBooleanValue(KEY_AUTO_DENSITY);
	}
	public void setAutoDensity(Boolean value) {
		setBooleanValue(KEY_AUTO_DENSITY, value);
	}
	public Boolean removeAutoDensity() {
		return removeBooleanValue(KEY_AUTO_DENSITY);
	}

	/*
	 * manualDensity (Number)
	 */
	public Integer getManualDensity() {
		return getNumberValue(KEY_MANUAL_DENSITY);
	}
	public void setManualDensity(Integer value) {
		setNumberValue(KEY_MANUAL_DENSITY, value);
	}
	public Integer removeManualDensity() {
		return removeNumberValue(KEY_MANUAL_DENSITY);
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
	 * eraseCenter (Boolean)
	 */
	public Boolean getEraseCenter() {
		return getBooleanValue(KEY_ERASE_CENTER);
	}
	public void setEraseCenter(Boolean value) {
		setBooleanValue(KEY_ERASE_CENTER, value);
	}
	public Boolean removeEraseCenter() {
		return removeBooleanValue(KEY_ERASE_CENTER);
	}

	/*
	 * eraseCenterSetting (Object)
	 */
	public EraseCenterSetting getEraseCenterSetting() {
		Map<String, Object> value = getObjectValue(KEY_ERASE_CENTER_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_ERASE_CENTER_SETTING, value);
		}
		return new EraseCenterSetting(value);
	}
//	public void setEraseCenterSetting(EraseCenterSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public EraseCenterSetting removeEraseCenterSetting() {
		Map<String, Object> value = removeObjectValue(KEY_ERASE_CENTER_SETTING);
		if (value == null) {
			return null;
		}
		return new EraseCenterSetting(value);
	}

	/*
	 * eraseBorder (Boolean)
	 */
	public Boolean getEraseBorder() {
		return getBooleanValue(KEY_ERASE_BORDER);
	}
	public void setEraseBorder(Boolean value) {
		setBooleanValue(KEY_ERASE_BORDER, value);
	}
	public Boolean removeEraseBorder() {
		return removeBooleanValue(KEY_ERASE_BORDER);
	}

	/*
	 * eraseBorderSetting (Object)
	 */
	public EraseBorderSetting getEraseBorderSetting() {
		Map<String, Object> value = getObjectValue(KEY_ERASE_BORDER_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_ERASE_BORDER_SETTING, value);
		}
		return new EraseBorderSetting(value);
	}
//	public void setEraseBorderSetting(EraseBorderSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public EraseBorderSetting removeEraseBorderSetting() {
		Map<String, Object> value = removeObjectValue(KEY_ERASE_BORDER_SETTING);
		if (value == null) {
			return null;
		}
		return new EraseBorderSetting(value);
	}

	/*
	 * margin (Boolean)
	 */
	public Boolean getMargin() {
		return getBooleanValue(KEY_MARGIN);
	}
	public void setMargin(Boolean value) {
		setBooleanValue(KEY_MARGIN, value);
	}
	public Boolean removeMargin() {
		return removeBooleanValue(KEY_MARGIN);
	}

	/*
	 * marginSetting (Object)
	 */
	public MarginSetting getMarginSetting() {
		Map<String, Object> value = getObjectValue(KEY_MARGIN_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_MARGIN_SETTING, value);
		}
		return new MarginSetting(value);
	}
//	public void setMarginSetting(MarginSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public MarginSetting removeMarginSetting() {
		Map<String, Object> value = removeObjectValue(KEY_MARGIN_SETTING);
		if (value == null) {
			return null;
		}
		return new MarginSetting(value);
	}

	/*
	 * centering (Boolean)
	 */
	public Boolean getCentering() {
		return getBooleanValue(KEY_CENTERING);
	}
	public void setCentering(Boolean value) {
		setBooleanValue(KEY_CENTERING, value);
	}
	public Boolean removeCentering() {
		return removeBooleanValue(KEY_CENTERING);
	}

	/*
	 * eraseColor (Boolean)
	 */
	public Boolean getEraseColor() {
		return getBooleanValue(KEY_ERASE_COLOR);
	}
	public void setEraseColor(Boolean value) {
		setBooleanValue(KEY_ERASE_COLOR, value);
	}
	public Boolean removeEraseColor() {
		return removeBooleanValue(KEY_ERASE_COLOR);
	}

	/*
	 * eraseColorSetting (Array[String])
	 */
	public List<String> getEraseColorSetting() {
		return getArrayValue(KEY_ERASE_COLOR_SETTING);
	}
	public void setEraseColorSetting(List<String> values) {
		setArrayValue(KEY_ERASE_COLOR_SETTING, values);
	}
	public List<String> removeEraseColorSetting() {
		return removeArrayValue(KEY_ERASE_COLOR_SETTING);
	}

	/*
	 * presetStamp (Boolean)
	 */
	public Boolean getPresetStamp() {
		return getBooleanValue(KEY_PRESET_STAMP);
	}
	public void setPresetStamp(Boolean value) {
		setBooleanValue(KEY_PRESET_STAMP, value);
	}
	public Boolean removePresetStamp() {
		return removeBooleanValue(KEY_PRESET_STAMP);
	}

	/*
	 * presetStampSetting (Object)
	 */
	public PresetStampSetting getPresetStampSetting() {
		Map<String, Object> value = getObjectValue(KEY_PRESET_STAMP_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_PRESET_STAMP_SETTING, value);
		}
		return new PresetStampSetting(value);
	}
//	public void setPresetStampSetting(PresetStampSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public PresetStampSetting removePresetStampSetting() {
		Map<String, Object> value = removeObjectValue(KEY_PRESET_STAMP_SETTING);
		if (value == null) {
			return null;
		}
		return new PresetStampSetting(value);
	}

	/*
	 * userStamp (Boolean)
	 */
	public Boolean getUserStamp() {
		return getBooleanValue(KEY_USER_STAMP);
	}
	public void setUserStamp(Boolean value) {
		setBooleanValue(KEY_USER_STAMP, value);
	}
	public Boolean removeUserStamp() {
		return removeBooleanValue(KEY_USER_STAMP);
	}

	/*
	 * userStampSetting (Object)
	 */
	public UserStampSetting getUserStampSetting() {
		Map<String, Object> value = getObjectValue(KEY_USER_STAMP_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_USER_STAMP_SETTING, value);
		}
		return new UserStampSetting(value);
	}
//	public void setUserStampSetting(UserStampSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public UserStampSetting removeUserStampSetting() {
		Map<String, Object> value = removeObjectValue(KEY_USER_STAMP_SETTING);
		if (value == null) {
			return null;
		}
		return new UserStampSetting(value);
	}

	/*
	 * dateStamp (Boolean)
	 */
	public Boolean getDateStamp() {
		return getBooleanValue(KEY_DATE_STAMP);
	}
	public void setDateStamp(Boolean value) {
		setBooleanValue(KEY_DATE_STAMP, value);
	}
	public Boolean removeDateStamp() {
		return removeBooleanValue(KEY_DATE_STAMP);
	}

	/*
	 * dateStampSetting (Object)
	 */
	public DateStampSetting getDateStampSetting() {
		Map<String, Object> value = getObjectValue(KEY_DATE_STAMP_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_DATE_STAMP_SETTING, value);
		}
		return new DateStampSetting(value);
	}
//	public void setDateStampSetting(DateStampSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public DateStampSetting removeDateStampSetting() {
		Map<String, Object> value = removeObjectValue(KEY_DATE_STAMP_SETTING);
		if (value == null) {
			return null;
		}
		return new DateStampSetting(value);
	}

	/*
	 * pageStamp (Boolean)
	 */
	public Boolean getPageStamp() {
		return getBooleanValue(KEY_PAGE_STAMP);
	}
	public void setPageStamp(Boolean value) {
		setBooleanValue(KEY_PAGE_STAMP, value);
	}
	public Boolean removePageStamp() {
		return removeBooleanValue(KEY_PAGE_STAMP);
	}

	/*
	 * pageStampSetting (Object)
	 */
	public PageStampSetting getPageStampSetting() {
		Map<String, Object> value = getObjectValue(KEY_PAGE_STAMP_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_PAGE_STAMP_SETTING, value);
		}
		return new PageStampSetting(value);
	}
//	public void setPageStampSetting(PageStampSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public PageStampSetting removePageStampSetting() {
		Map<String, Object> value = removeObjectValue(KEY_PAGE_STAMP_SETTING);
		if (value == null) {
			return null;
		}
		return new PageStampSetting(value);
	}

	/*
	 * textStamp (Boolean)
	 */
	public Boolean getTextStamp() {
		return getBooleanValue(KEY_TEXT_STAMP);
	}
	public void setTextStamp(Boolean value) {
		setBooleanValue(KEY_TEXT_STAMP, value);
	}
	public Boolean removeTextStamp() {
		return removeBooleanValue(KEY_TEXT_STAMP);
	}

	/*
	 * textStampSetting (Object)
	 */
	public TextStampSetting getTextStampSetting() {
		Map<String, Object> value = getObjectValue(KEY_TEXT_STAMP_SETTING);
		if (value == null) {
			value = Utils.createElementMap();
			setObjectValue(KEY_TEXT_STAMP_SETTING, value);
		}
		return new TextStampSetting(value);
	}
//	public void setTextStampSetting(TextStampSetting value) {
//		throw new UnsupportedOperationException();
//	}
	public TextStampSetting removeTextStampSetting() {
		Map<String, Object> value = removeObjectValue(KEY_TEXT_STAMP_SETTING);
		if (value == null) {
			return null;
		}
		return new TextStampSetting(value);
	}
	
	public static class SpecialColorSetting extends WritableElement {

		private static final String KEY_NON_BLACK_PART_COLOR	= "nonBlackPartColor";
		private static final String KEY_BLACK_PART_COLOR		= "blackPartColor";
		private static final String KEY_SINGLE_COLOR			= "singleColor";
		private static final String KEY_SINGLE_COLOR_DENSITY	= "singleColorDensity";

		SpecialColorSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * nonBlackPartColor (String)
		 */
		public String getNonBlackPartColor() {
			return getStringValue(KEY_NON_BLACK_PART_COLOR);
		}
		public void setNonBlackPartColor(String value) {
			setStringValue(KEY_NON_BLACK_PART_COLOR, value);
		}
		public String removeNonBlackPartColor() {
			return removeStringValue(KEY_NON_BLACK_PART_COLOR);
		}

		/*
		 * blackPartColor (String)
		 */
		public String getBlackPartColor() {
			return getStringValue(KEY_BLACK_PART_COLOR);
		}
		public void setBlackPartColor(String value) {
			setStringValue(KEY_BLACK_PART_COLOR, value);
		}
		public String removeBlackPartColor() {
			return removeStringValue(KEY_BLACK_PART_COLOR);
		}

		/*
		 * singleColor (String)
		 */
		public String getSingleColor() {
			return getStringValue(KEY_SINGLE_COLOR);
		}
		public void setSingleColor(String value) {
			setStringValue(KEY_SINGLE_COLOR, value);
		}
		public String removeSingleColor() {
			return removeStringValue(KEY_SINGLE_COLOR);
		}

		/*
		 * singleColorDensity (Number)
		 */
		public Integer getSingleColorDensity() {
			return getNumberValue(KEY_SINGLE_COLOR_DENSITY);
		}
		public void setSingleColorDensity(Integer value) {
			setNumberValue(KEY_SINGLE_COLOR_DENSITY, value);
		}
		public Integer removeSingleColorDensity() {
			return removeNumberValue(KEY_SINGLE_COLOR_DENSITY);
		}
	}

	public static class CombineSeparatorLineSetting extends WritableElement {

		private static final String KEY_LINE_TYPE		= "lineType";
		private static final String KEY_COLOR_TYPE	= "lineColor";

		CombineSeparatorLineSetting(Map<String, Object> value) {
			super(value);
		}

		/*
		 * lineType (String)
		 */
		public String getLineType() {
			return getStringValue(KEY_LINE_TYPE);
		}
		public void setLineType(String value) {
			setStringValue(KEY_LINE_TYPE, value);
		}
		public String removeLineType() {
			return removeStringValue(KEY_LINE_TYPE);
		}

		/*
		 * lineColor (String)
		 */
		public String getLineColor() {
			return getStringValue(KEY_COLOR_TYPE);
		}
		public void setLineColor(String value) {
			setStringValue(KEY_COLOR_TYPE, value);
		}
		public String removeLineColor() {
			return removeStringValue(KEY_COLOR_TYPE);
		}

	}

	public static class EraseCenterSetting extends WritableElement {

		private static final String KEY_ERASE_WIDTH			= "eraseWidth";

		EraseCenterSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * eraseWidth (String)
		 */
		public String getEraseWidth() {
			return getStringValue(KEY_ERASE_WIDTH);
		}
		public void setEraseWidth(String value) {
			setStringValue(KEY_ERASE_WIDTH, value);
		}
		public String removeEraseWidth() {
			return removeStringValue(KEY_ERASE_WIDTH);
		}

	}

	public static class EraseBorderSetting extends WritableElement {

		private static final String KEY_ERASE_WIDTH_LEFT		= "eraseWidthLeft";
		private static final String KEY_ERASE_WIDTH_RIGHT		= "eraseWidthRight";
		private static final String KEY_ERASE_WIDTH_TOP			= "eraseWidthTop";
		private static final String KEY_ERASE_WIDTH_BOTTOM		= "eraseWidthBottom";

		EraseBorderSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * eraseWidthLeft (String)
		 */
		public String getEraseWidthLeft() {
			return getStringValue(KEY_ERASE_WIDTH_LEFT);
		}
		public void setEraseWidthLeft(String value) {
			setStringValue(KEY_ERASE_WIDTH_LEFT, value);
		}
		public String removeEraseWidthLeft() {
			return removeStringValue(KEY_ERASE_WIDTH_LEFT);
		}

		/*
		 * eraseWidthRight (String)
		 */
		public String getEraseWidthRight() {
			return getStringValue(KEY_ERASE_WIDTH_RIGHT);
		}
		public void setEraseWidthRight(String value) {
			setStringValue(KEY_ERASE_WIDTH_RIGHT, value);
		}
		public String removeEraseWidthRight() {
			return removeStringValue(KEY_ERASE_WIDTH_RIGHT);
		}

		/*
		 * eraseWidthTop (String)
		 */
		public String getEraseWidthTop() {
			return getStringValue(KEY_ERASE_WIDTH_TOP);
		}
		public void setEraseWidthTop(String value) {
			setStringValue(KEY_ERASE_WIDTH_TOP, value);
		}
		public String removeEraseWidthTop() {
			return removeStringValue(KEY_ERASE_WIDTH_TOP);
		}

		/*
		 * eraseWidthBottom (String)
		 */
		public String getEraseWidthBottom() {
			return getStringValue(KEY_ERASE_WIDTH_BOTTOM);
		}
		public void setEraseWidthBottom(String value) {
			setStringValue(KEY_ERASE_WIDTH_BOTTOM, value);
		}
		public String removeEraseWidthBottom() {
			return removeStringValue(KEY_ERASE_WIDTH_BOTTOM);
		}

	}

	
	public static class MarginSetting extends WritableElement {

		private static final String KEY_MARGIN_WIDTH_FRONT_LEFT_RIGHT		= "marginWidthFrontLeftRight";
		private static final String KEY_MARGIN_WIDTH_FRONT_TOP_BOTTOM		= "marginWidthFrontTopBottom";
		private static final String KEY_MARGIN_WIDTH_BACK_LEFT_RIGHT		= "marginWidthBackLeftRight";
		private static final String KEY_MARGIN_WIDTH_BACK_TOP_BOTTOM		= "marginWidthBackTopBottom";

		MarginSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * marginWidthFrontLeftRight (String)
		 */
		public String getMarginWidthFrontLeftRight() {
			return getStringValue(KEY_MARGIN_WIDTH_FRONT_LEFT_RIGHT);
		}
		public void setMarginWidthFrontLeftRight(String value) {
			setStringValue(KEY_MARGIN_WIDTH_FRONT_LEFT_RIGHT, value);
		}
		public String removeMarginWidthFrontLeftRight() {
			return removeStringValue(KEY_MARGIN_WIDTH_FRONT_LEFT_RIGHT);
		}

		/*
		 * marginWidthFrontTopBottom (String)
		 */
		public String getMarginWidthFrontTopBottom() {
			return getStringValue(KEY_MARGIN_WIDTH_FRONT_TOP_BOTTOM);
		}
		public void setMarginWidthFrontTopBottom(String value) {
			setStringValue(KEY_MARGIN_WIDTH_FRONT_TOP_BOTTOM, value);
		}
		public String removeMarginWidthFrontTopBottom() {
			return removeStringValue(KEY_MARGIN_WIDTH_FRONT_TOP_BOTTOM);
		}

		/*
		 * marginWidthBackLeftRight (String)
		 */
		public String getMarginWidthBackLeftRight() {
			return getStringValue(KEY_MARGIN_WIDTH_BACK_LEFT_RIGHT);
		}
		public void setMarginWidthBackLeftRight(String value) {
			setStringValue(KEY_MARGIN_WIDTH_BACK_LEFT_RIGHT, value);
		}
		public String removeMarginWidthBackLeftRight() {
			return removeStringValue(KEY_MARGIN_WIDTH_BACK_LEFT_RIGHT);
		}

		/*
		 * marginWidthBackTopBottom (String)
		 */
		public String getMarginWidthBackTopBottom() {
			return getStringValue(KEY_MARGIN_WIDTH_BACK_TOP_BOTTOM);
		}
		public void setMarginWidthBackTopBottom(String value) {
			setStringValue(KEY_MARGIN_WIDTH_BACK_TOP_BOTTOM, value);
		}
		public String removeMarginWidthBackTopBottom() {
			return removeStringValue(KEY_MARGIN_WIDTH_BACK_TOP_BOTTOM);
		}

	}

	public static class PresetStampSetting extends WritableElement {

		private static final String KEY_POSITION 		= "position";
		private static final String KEY_COLOR			= "color";
		private static final String KEY_STAMP_KIND		= "stampKind";
		private static final String KEY_PAGE			= "page";
		private static final String KEY_STAMP_SIZE		= "stampSize";
		private static final String KEY_STAMP_DENSITY	= "stampDensity";

		PresetStampSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * position (String)
		 */
		public String getPosition() {
			return getStringValue(KEY_POSITION);
		}
		public void setPosition(String value) {
			setStringValue(KEY_POSITION, value);
		}
		public String removePosition() {
			return removeStringValue(KEY_POSITION);
		}

		/*
		 * color (String)
		 */
		public String getColor() {
			return getStringValue(KEY_COLOR);
		}
		public void setColor(String value) {
			setStringValue(KEY_COLOR, value);
		}
		public String removeColor() {
			return removeStringValue(KEY_COLOR);
		}

		/*
		 * stampKind (String)
		 */
		public String getStampKind() {
			return getStringValue(KEY_STAMP_KIND);
		}
		public void setStampKind(String value) {
			setStringValue(KEY_STAMP_KIND, value);
		}
		public String removeStampKind() {
			return removeStringValue(KEY_STAMP_KIND);
		}

		/*
		 * page (String)
		 */
		public String getPage() {
			return getStringValue(KEY_PAGE);
		}
		public void setPage(String value) {
			setStringValue(KEY_PAGE, value);
		}
		public String removePage() {
			return removeStringValue(KEY_PAGE);
		}

		/*
		 * stampSize (Number)
		 */
		public Integer getStampSize() {
			return getNumberValue(KEY_STAMP_SIZE);
		}
		public void setStampSize(Integer value) {
			setNumberValue(KEY_STAMP_SIZE, value);
		}
		public Integer removeStampSize() {
			return removeNumberValue(KEY_STAMP_SIZE);
		}

		/*
		 * stampDensity (String)
		 */
		public String getStampDensity() {
			return getStringValue(KEY_STAMP_DENSITY);
		}
		public void setStampDensity(String value) {
			setStringValue(KEY_STAMP_DENSITY, value);
		}
		public String removeStampDensity() {
			return removeStringValue(KEY_STAMP_DENSITY);
		}

	}

	public static class UserStampSetting extends WritableElement {

		private static final String KEY_POSITION 		= "position";
		private static final String KEY_COLOR			= "color";
		private static final String KEY_STAMP_KIND		= "stampKind";
		private static final String KEY_PAGE			= "page";

		UserStampSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * position (String)
		 */
		public String getPosition() {
			return getStringValue(KEY_POSITION);
		}
		public void setPosition(String value) {
			setStringValue(KEY_POSITION, value);
		}
		public String removePosition() {
			return removeStringValue(KEY_POSITION);
		}

		/*
		 * color (String)
		 */
		public String getColor() {
			return getStringValue(KEY_COLOR);
		}
		public void setColor(String value) {
			setStringValue(KEY_COLOR, value);
		}
		public String removeColor() {
			return removeStringValue(KEY_COLOR);
		}

		/*
		 * stampKind (String)
		 */
		public String getStampKind() {
			return getStringValue(KEY_STAMP_KIND);
		}
		public void setStampKind(String value) {
			setStringValue(KEY_STAMP_KIND, value);
		}
		public String removeStampKind() {
			return removeStringValue(KEY_STAMP_KIND);
		}

		/*
		 * page (String)
		 */
		public String getPage() {
			return getStringValue(KEY_PAGE);
		}
		public void setPage(String value) {
			setStringValue(KEY_PAGE, value);
		}
		public String removePage() {
			return removeStringValue(KEY_PAGE);
		}

	}

	public static class DateStampSetting extends WritableElement {

		private static final String KEY_POSITION 		= "position";
		private static final String KEY_COLOR			= "color";
		private static final String KEY_DATE_FORMAT		= "dateFormat";
		private static final String KEY_PAGE			= "page";
		private static final String KEY_FONT			= "font";
		private static final String KEY_FONT_SIZE		= "fontSize";

		DateStampSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * position (String)
		 */
		public String getPosition() {
			return getStringValue(KEY_POSITION);
		}
		public void setPosition(String value) {
			setStringValue(KEY_POSITION, value);
		}
		public String removePosition() {
			return removeStringValue(KEY_POSITION);
		}

		/*
		 * color (String)
		 */
		public String getColor() {
			return getStringValue(KEY_COLOR);
		}
		public void setColor(String value) {
			setStringValue(KEY_COLOR, value);
		}
		public String removeColor() {
			return removeStringValue(KEY_COLOR);
		}

		/*
		 * dateFormat (String)
		 */
		public String getDateFormat() {
			return getStringValue(KEY_DATE_FORMAT);
		}
		public void setDateFormat(String value) {
			setStringValue(KEY_DATE_FORMAT, value);
		}
		public String removeDateFormat() {
			return removeStringValue(KEY_DATE_FORMAT);
		}

		/*
		 * page (String)
		 */
		public String getPage() {
			return getStringValue(KEY_PAGE);
		}
		public void setPage(String value) {
			setStringValue(KEY_PAGE, value);
		}
		public String removePage() {
			return removeStringValue(KEY_PAGE);
		}

		/*
		 * font (String)
		 */
		public String getFont() {
			return getStringValue(KEY_FONT);
		}
		public void setFont(String value) {
			setStringValue(KEY_FONT, value);
		}
		public String removeFont() {
			return removeStringValue(KEY_FONT);
		}

		/*
		 * fontSize (String)
		 */
		public String getFontSize() {
			return getStringValue(KEY_FONT_SIZE);
		}
		public void setFontSize(String value) {
			setStringValue(KEY_FONT_SIZE, value);
		}
		public String removeFontSize() {
			return removeStringValue(KEY_FONT_SIZE);
		}

	}

	public static class PageStampSetting extends WritableElement {

		private static final String KEY_POSITION 		= "position";
		private static final String KEY_COLOR			= "color";
		private static final String KEY_FONT			= "font";
		private static final String KEY_FONT_SIZE		= "fontSize";
		private static final String KEY_PAGE_SETTING	= "pageSetting";

		PageStampSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * position (String)
		 */
		public String getPosition() {
			return getStringValue(KEY_POSITION);
		}
		public void setPosition(String value) {
			setStringValue(KEY_POSITION, value);
		}
		public String removePosition() {
			return removeStringValue(KEY_POSITION);
		}

		/*
		 * color (String)
		 */
		public String getColor() {
			return getStringValue(KEY_COLOR);
		}
		public void setColor(String value) {
			setStringValue(KEY_COLOR, value);
		}
		public String removeColor() {
			return removeStringValue(KEY_COLOR);
		}

		/*
		 * font (String)
		 */
		public String getFont() {
			return getStringValue(KEY_FONT);
		}
		public void setFont(String value) {
			setStringValue(KEY_FONT, value);
		}
		public String removeFont() {
			return removeStringValue(KEY_FONT);
		}

		/*
		 * fontSize (String)
		 */
		public String getFontSize() {
			return getStringValue(KEY_FONT_SIZE);
		}
		public void setFontSize(String value) {
			setStringValue(KEY_FONT_SIZE, value);
		}
		public String removeFontSize() {
			return removeStringValue(KEY_FONT_SIZE);
		}

		/*
		 * pageSetting (Object)
		 */
		public PageSetting getPageSetting() {
			Map<String, Object> value = getObjectValue(KEY_PAGE_SETTING);
			if (value == null) {
				value = Utils.createElementMap();
				setObjectValue(KEY_PAGE_SETTING, value);
			}
			return new PageSetting(value);
		}
//		public void setPageSetting(PageSetting value) {
//			throw new UnsupportedOperationException();
//		}
		public PageSetting removePageSetting() {
			Map<String, Object> value = removeObjectValue(KEY_PAGE_SETTING);
			if (value == null) {
				return null;
			}
			return new PageSetting(value);
		}

	}

	public static class PageSetting extends WritableElement {

		private static final String KEY_FORMAT 			= "format";
		private static final String KEY_FIRST_PAGE		= "firstPage";
		private static final String KEY_FIRST_NUMBER	= "firstNumber";
		private static final String KEY_LAST_NUMBER		= "lastNumber";
		private static final String KEY_TOTAL_PAGE		= "totalPage";

		PageSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * format (String)
		 */
		public String getFormat() {
			return getStringValue(KEY_FORMAT);
		}
		public void setFormat(String value) {
			setStringValue(KEY_FORMAT, value);
		}
		public String removeFormat() {
			return removeStringValue(KEY_FORMAT);
		}

		/*
		 * firstPage (Number)
		 */
		public Integer getFirstPage() {
			return getNumberValue(KEY_FIRST_PAGE);
		}
		public void setFirstPage(Integer value) {
			setNumberValue(KEY_FIRST_PAGE, value);
		}
		public Integer removeFirstPage() {
			return removeNumberValue(KEY_FIRST_PAGE);
		}

		/*
		 * firstNumber (Number)
		 */
		public Integer getFirstNumber() {
			return getNumberValue(KEY_FIRST_NUMBER);
		}
		public void setFirstNumber(Integer value) {
			setNumberValue(KEY_FIRST_NUMBER, value);
		}
		public Integer removeFirstNumber() {
			return removeNumberValue(KEY_FIRST_NUMBER);
		}

		/*
		 * lastNumber (Number)
		 */
		public Integer getLastNumber() {
			return getNumberValue(KEY_LAST_NUMBER);
		}
		public void setLastNumber(Integer value) {
			setNumberValue(KEY_LAST_NUMBER, value);
		}
		public Integer removeLastNumber() {
			return removeNumberValue(KEY_LAST_NUMBER);
		}

		/*
		 * totalPage (Number)
		 */
		public Integer getTotalPage() {
			return getNumberValue(KEY_TOTAL_PAGE);
		}
		public void setTotalPage(Integer value) {
			setNumberValue(KEY_TOTAL_PAGE, value);
		}
		public Integer removeTotalPage() {
			return removeNumberValue(KEY_TOTAL_PAGE);
		}

	}

	public static class TextStampSetting extends WritableElement {

		private static final String KEY_POSITION 		= "position";
		private static final String KEY_COLOR			= "color";
		private static final String KEY_PAGE			= "page";
		private static final String KEY_FONT			= "font";
		private static final String KEY_FONT_SIZE		= "fontSize";
		private static final String KEY_TEXT_STRING		= "textString";

		TextStampSetting(Map<String, Object> values) {
			super(values);
		}

		/*
		 * position (String)
		 */
		public String getPosition() {
			return getStringValue(KEY_POSITION);
		}
		public void setPosition(String value) {
			setStringValue(KEY_POSITION, value);
		}
		public String removePosition() {
			return removeStringValue(KEY_POSITION);
		}

		/*
		 * color (String)
		 */
		public String getColor() {
			return getStringValue(KEY_COLOR);
		}
		public void setColor(String value) {
			setStringValue(KEY_COLOR, value);
		}
		public String removeColor() {
			return removeStringValue(KEY_COLOR);
		}

		/*
		 * page (String)
		 */
		public String getPage() {
			return getStringValue(KEY_PAGE);
		}
		public void setPage(String value) {
			setStringValue(KEY_PAGE, value);
		}
		public String removePage() {
			return removeStringValue(KEY_PAGE);
		}

		/*
		 * font (String)
		 */
		public String getFont() {
			return getStringValue(KEY_FONT);
		}
		public void setFont(String value) {
			setStringValue(KEY_FONT, value);
		}
		public String removeFont() {
			return removeStringValue(KEY_FONT);
		}

		/*
		 * fontSize (String)
		 */
		public String getFontSize() {
			return getStringValue(KEY_FONT_SIZE);
		}
		public void setFontSize(String value) {
			setStringValue(KEY_FONT_SIZE, value);
		}
		public String removeFontSize() {
			return removeStringValue(KEY_FONT_SIZE);
		}

		/*
		 * textString (String)
		 */
		public String getTextString() {
			return getStringValue(KEY_TEXT_STRING);
		}
		public void setTextString(String value) {
			setStringValue(KEY_TEXT_STRING, value);
		}
		public String removeTextString() {
			return removeStringValue(KEY_TEXT_STRING);
		}

	}

}
