/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.copy;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.CapabilityElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MagnificationElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MaxLengthElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public class Capability extends CapabilityElement {

	private static final String KEY_AUTO_CORRECT_JOB_SETTING_LIST = "autoCorrectJobSettingList";
	private static final String KEY_JOB_MODE_LIST = "jobModeList";
	private static final String KEY_JOB_STOPPED_TIMEOUT_PERIOD_RANGE = "jobStoppedTimeoutPeriodRange"; // SmartSDK V1.02
	private static final String KEY_ORIGINAL_SIZE_LIST = "originalSizeList";
	private static final String KEY_ORIGINAL_SIZE_CUSTOM_X_RANGE = "originalSizeCustomXRange";
	private static final String KEY_ORIGINAL_SIZE_CUSTOM_Y_RANGE = "originalSizeCustomYRange";
	private static final String KEY_ORIGINAL_SIDE_LIST = "originalSideList";
	private static final String KEY_ORIGINAL_ORIENTATION_LIST = "originalOrientationList";
	private static final String KEY_ORIGINAL_TYPE_LIST = "originalTypeList";
	private static final String KEY_PRINT_COLOR_LIST = "printColorList";
	private static final String KEY_SPECIAL_COLOR_SETTING_CAPABILITY = "specialColorSettingCapability";
	private static final String KEY_COPIES_RANGE = "copiesRange";
	private static final String KEY_SHEET_COLLATE_LIST = "sheetCollateList";
	private static final String KEY_PRINT_SIDE_LIST = "printSideList";
	private static final String KEY_COMBINE_LIST = "combineList";
	private static final String KEY_COMBINE_ORDER_LIST = "combineOrderList";
	private static final String KEY_COMBINE_SEPARATOR_LINE_LIST = "combineSeparatorLineList";
	private static final String KEY_COMBINE_SEPARATOR_LINE_SETTING_CAPABILITY = "combineSeparatorLineSettingCapability";
	private static final String KEY_MAGNIFICATION_RANGE = "magnificationRange";
	private static final String KEY_PAPER_TRAY_LIST = "paperTrayList";
	private static final String KEY_AUTO_DENSITY_LIST = "autoDensityList";
	private static final String KEY_MANUAL_DENSITY_RANGE = "manualDensityRange";
	private static final String KEY_STAPLE_LIST = "stapleList";
	private static final String KEY_PUNCH_LIST = "punchList";
	private static final String KEY_ERASE_CENTER_LIST = "eraseCenterList";
	private static final String KEY_ERASE_CENTER_SETTING_CAPABILITY = "eraseCenterSettingCapability";
	private static final String KEY_ERASE_BORDER_LIST = "eraseBorderList";
	private static final String KEY_ERASE_BORDER_SETTING_CAPABILITY = "eraseBorderSettingCapability";
	private static final String KEY_MARGIN_LIST = "marginList";
	private static final String KEY_MARGIN_SETTING_CAPABILITY = "marginSettingCapability";
	private static final String KEY_CENTERING_LIST = "centeringList";
	private static final String KEY_ERASE_COLOR_LIST = "eraseColorList";
	private static final String KEY_ERASE_COLOR_SETTING_LIST = "eraseColorSettingList";
	private static final String KEY_PRESET_STAMP_LIST = "presetStampList";
	private static final String KEY_PRESET_STAMP_SETTING_CAPABILITY = "presetStampSettingCapability";
	private static final String KEY_USER_STAMP_LIST = "userStampList";
	private static final String KEY_USER_STAMP_SETTING_CAPABILITY = "userStampSettingCapability";
	private static final String KEY_DATE_STAMP_LIST = "dateStampList";
	private static final String KEY_DATE_STAMP_SETTING_CAPABILITY = "dateStampSettingCapability";
	private static final String KEY_PAGE_STAMP_LIST = "pageStampList";
	private static final String KEY_PAGE_STAMP_SETTING_CAPABILITY = "pageStampSettingCapability";
	private static final String KEY_TEXT_STAMP_LIST = "textStampList";
	private static final String KEY_TEXT_STAMP_SETTING_CAPABILITY = "textStampSettingCapability";

	public Capability(Map<String, Object> values) {
		super(values);
	}

	/*
	 * autoCorrectJobSettingList (Array[Boolean])
	 */
	public List<Boolean> getAutoCorrectJobSettingList() {
		return getArrayValue(KEY_AUTO_CORRECT_JOB_SETTING_LIST);
	}

	/*
	 * jobModeList (Array[String])
	 */
	public List<String> getJobModeList() {
		return getArrayValue(KEY_JOB_MODE_LIST);
	}

	/*
	 * jobStoppedTimeoutPeriodRange (Range)
	 * @since SmartSDK V1.02
	 */
	public RangeElement getJobStoppedTimeoutPeriodRange() {
		return getRangeValue(KEY_JOB_STOPPED_TIMEOUT_PERIOD_RANGE);
	}

	/*
	 * originalSizeList (Array[String])
	 */
	public List<String> getOriginalSizeList() {
		return getArrayValue(KEY_ORIGINAL_SIZE_LIST);
	}

	/*
	 * originalSizeCustomXRange (Range)
	 */
	public RangeElement getOriginalSizeCustomXRange() {
		return getRangeValue(KEY_ORIGINAL_SIZE_CUSTOM_X_RANGE);
	}

	/*
	 * originalSizeCustomYRange (Range)
	 */
	public RangeElement getOriginalSizeCustomYRange() {
		return getRangeValue(KEY_ORIGINAL_SIZE_CUSTOM_Y_RANGE);
	}

	/*
	 * originalSideList (Array[String])
	 */
	public List<String> getOriginalSideList() {
		return getArrayValue(KEY_ORIGINAL_SIDE_LIST);
	}

	/*
	 * originalOrientationList (Array[String])
	 */
	public List<String> getOriginalOrientationList() {
		return getArrayValue(KEY_ORIGINAL_ORIENTATION_LIST);
	}

	/*
	 * originalTypeList (Array[String])
	 */
	public List<String> getOriginalTypeList() {
		return getArrayValue(KEY_ORIGINAL_TYPE_LIST);
	}

	/*
	 * printColorList (Array[String])
	 */
	public List<String> getPrintColorList() {
		return getArrayValue(KEY_PRINT_COLOR_LIST);
	}

	/*
	 * specialColorSettingCapability (Object)
	 */
	public SpecialColorSettingCapability getSpecialColorSetting() {
		Map<String, Object> mapValue = getObjectValue(KEY_SPECIAL_COLOR_SETTING_CAPABILITY);
		if (mapValue == null) {
			return null;
		}
		return new SpecialColorSettingCapability(mapValue);
	}

	/*
	 * copiesRange (Range)
	 */
	public RangeElement getCopiesRange() {
		return getRangeValue(KEY_COPIES_RANGE);
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
	 * combineSeparatorLineSettingCapability (Object)
	 */
	public CombineSeparatorLineSettingCapability getCombineSeparatorLineSettingCapability() {
		Map<String, Object> value = getObjectValue(KEY_COMBINE_SEPARATOR_LINE_SETTING_CAPABILITY);
		if (value == null) {
			return null;
		}
		return new CombineSeparatorLineSettingCapability(value);
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
	 * autoDensityList (Array[Boolean])
	 */
	public List<Boolean> getAutoDensityList() {
		return getArrayValue(KEY_AUTO_DENSITY_LIST);
	}

	/*
	 * manualDensityRange (Range)
	 */
	public RangeElement getManualDensityRange() {
		return getRangeValue(KEY_MANUAL_DENSITY_RANGE);
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
	 * eraseCenterList (Array[Boolean])
	 */
	public List<Boolean> getEraseCenterList() {
		return getArrayValue(KEY_ERASE_CENTER_LIST);
	}

	/*
	 * eraseCenterSettingCapability (Object)
	 */
	public EraseCenterSettingCapability getEraseCenterSettingCapability() {
		Map<String, Object> value = getObjectValue(KEY_ERASE_CENTER_SETTING_CAPABILITY);
		if (value == null) {
			return null;
		}
		return new EraseCenterSettingCapability(value);
	}

	/*
	 * eraseBorderList (Array[Boolean])
	 */
	public List<Boolean> getEraseBorderList() {
		return getArrayValue(KEY_ERASE_BORDER_LIST);
	}

	/*
	 * eraseBorderSettingCapability (Object)
	 */
	public EraseBorderSettingCapability getEraseBorderSettingCapability() {
		Map<String, Object> value = getObjectValue(KEY_ERASE_BORDER_SETTING_CAPABILITY);
		if (value == null) {
			return null;
		}
		return new EraseBorderSettingCapability(value);
	}

	/*
	 * marginList (Array[Boolean])
	 */
	public List<Boolean> getMarginList() {
		return getArrayValue(KEY_MARGIN_LIST);
	}

	/*
	 * marginSettingCapability (Object)
	 */
	public MarginSettingCapability getMarginSettingCapability() {
		Map<String, Object> value = getObjectValue(KEY_MARGIN_SETTING_CAPABILITY);
		if (value == null) {
			return null;
		}
		return new MarginSettingCapability(value);
	}

	/*
	 * centeringList (Array[Boolean])
	 */
	public List<Boolean> getCenteringList() {
		return getArrayValue(KEY_CENTERING_LIST);
	}

	/*
	 * eraseColorList (Array[Boolean])
	 */
	public List<Boolean> getEraseColorList() {
		return getArrayValue(KEY_ERASE_COLOR_LIST);
	}

	/*
	 * eraseColorSettingList (Array[String])
	 */
	public List<String> getEraseColorSettingList() {
		return getArrayValue(KEY_ERASE_COLOR_SETTING_LIST);
	}

	/*
	 * presetStampList (Array[Boolean])
	 */
	public List<Boolean> getPresetStampList() {
		return getArrayValue(KEY_PRESET_STAMP_LIST);
	}
	
	/*
	 * presetStampSettingCapability (Object)
	 */
	public PresetStampSettingCapability getPresetStampSettingCapability() {
		Map<String, Object> mapValue = getObjectValue(KEY_PRESET_STAMP_SETTING_CAPABILITY);
		if (mapValue == null) {
			return null;
		}
		return new PresetStampSettingCapability(mapValue);
	}

	/*
	 * userStampList (Array[Boolean])
	 */
	public List<Boolean> getUserStampList() {
		return getArrayValue(KEY_USER_STAMP_LIST);
	}

	/*
	 * userStampSettingCapability (Object)
	 */
	public UserStampSettingCapability getUserStampSettingCapability() {
		Map<String, Object> mapValue = getObjectValue(KEY_USER_STAMP_SETTING_CAPABILITY);
		if (mapValue == null) {
			return null;
		}
		return new UserStampSettingCapability(mapValue);
	}

	/*
	 * dateStampList (Array[Boolean])
	 */
	public List<Boolean> getDateStampList() {
		return getArrayValue(KEY_DATE_STAMP_LIST);
	}

	/*
	 * dateStampSettingCapability (Object)
	 */
	public DateStampSettingCapability getDateStampSettingCapability() {
		Map<String, Object> mapValue = getObjectValue(KEY_DATE_STAMP_SETTING_CAPABILITY);
		if (mapValue == null) {
			return null;
		}
		return new DateStampSettingCapability(mapValue);
	}

	/*
	 * pageStampList (Array[Boolean])
	 */
	public List<Boolean> getPageStampList() {
		return getArrayValue(KEY_PAGE_STAMP_LIST);
	}

	/*
	 * pageStampSettingCapability (Object)
	 */
	public PageStampSettingCapability getPageStampSettingCapability() {
		Map<String, Object> mapValue = getObjectValue(KEY_PAGE_STAMP_SETTING_CAPABILITY);
		if (mapValue == null) {
			return null;
		}
		return new PageStampSettingCapability(mapValue);
	}

	/*
	 * textStampList (Array[Boolean])
	 */
	public List<Boolean> getTextStampList() {
		return getArrayValue(KEY_TEXT_STAMP_LIST);
	}

	/*
	 * textStampSettingCapability (Object)
	 */
	public TextStampSettingCapability getTextStampSettingCapability() {
		Map<String, Object> mapValue = getObjectValue(KEY_TEXT_STAMP_SETTING_CAPABILITY);
		if (mapValue == null) {
			return null;
		}
		return new TextStampSettingCapability(mapValue);
	}

	public static class SpecialColorSettingCapability extends CapabilityElement {

		private static final String KEY_NON_BLACK_PART_COLOR_LIST = "nonBlackPartColorList";
		private static final String KEY_BLACK_PART_COLOR_LIST = "blackPartColorList";
		private static final String KEY_SINGLE_COLOR_LIST = "singleColorList";
		private static final String KEY_SINGLE_COLOR_DENSITY_RANGE = "singleColorDensityRange";
		private static final String KEY_USER_COLOR_NAME_LIST = "userColorNameList";

		SpecialColorSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * nonBlackPartColorList (Array[String])
		 */
		public List<String> getNonBlackPartColorList() {
			return getArrayValue(KEY_NON_BLACK_PART_COLOR_LIST);
		}

		/*
		 * blackPartColorList (Array[String])
		 */
		public List<String> getBlackPartColorList() {
			return getArrayValue(KEY_BLACK_PART_COLOR_LIST);
		}

		/*
		 * singleColorList (Array[String])
		 */
		public List<String> getSingleColorList() {
			return getArrayValue(KEY_SINGLE_COLOR_LIST);
		}

		/*
		 * singleColorDensityRange (Range)
		 */
		public RangeElement getSingleColorDensityRange() {
			return getRangeValue(KEY_SINGLE_COLOR_DENSITY_RANGE);
		}

		/*
		 * userColorNameList (Array[Object])
		 */
		public UserColorNameList getUserColorNameList() {
			List<Map<String, Object>> value = getArrayValue(KEY_USER_COLOR_NAME_LIST);
			if (value == null) {
				return null;
			}
			return new UserColorNameList(value);
		}

	}

	public static class UserColorNameList extends ArrayElement<UserColorName> {

		UserColorNameList(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected UserColorName createElement(Map<String, Object> values) {
			return new UserColorName(values);
		}

	}

	public static class UserColorName extends CapabilityElement {

		private static final String KEY_ID = "id";
		private static final String KEY_NAME = "name";

		UserColorName(Map<String, Object> values) {
			super(values);
		}

		/*
		 * id (String)
		 */
		public String getId() {
			return getStringValue(KEY_ID);
		}

		/*
		 * name (String)
		 */
		public String getName() {
			return getStringValue(KEY_NAME);
		}
	}

	public static class CombineSeparatorLineSettingCapability extends
			CapabilityElement {

		private static final String KEY_LINE_TYPE_LIST = "lineTypeList";
		private static final String KEY_LINE_COLOR_LIST = "lineColorList";

		CombineSeparatorLineSettingCapability(Map<String, Object> value) {
			super(value);
		}

		/*
		 * lineTypeList (Array[String])
		 */
		public List<String> getLineTypeList() {
			return getArrayValue(KEY_LINE_TYPE_LIST);
		}

		/*
		 * lineColorList (Array[String])
		 */
		public List<String> getLineColorList() {
			return getArrayValue(KEY_LINE_COLOR_LIST);
		}

	}

	public static class EraseCenterSettingCapability extends CapabilityElement {

		private static final String KEY_ERASE_WIDTH_RANGE = "eraseWidthRange";

		EraseCenterSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * eraseWidthRange (Range)
		 */
		public RangeElement getEraseWidthRange() {
			return getRangeValue(KEY_ERASE_WIDTH_RANGE);
		}

	}

	public static class EraseBorderSettingCapability extends CapabilityElement {

		private static final String KEY_ERASE_WIDTH_LEFT_RANGE = "eraseWidthLeftRange";
		private static final String KEY_ERASE_WIDTH_RIGHT_RANGE = "eraseWidthRightRange";
		private static final String KEY_ERASE_WIDTH_TOP_RANGE = "eraseWidthTopRange";
		private static final String KEY_ERASE_WIDTH_BOTTOM_RANGE = "eraseWidthBottomRange";

		EraseBorderSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * eraseWidthLeftRange (Range)
		 */
		public RangeElement getEraseWidthLeftRange() {
			return getRangeValue(KEY_ERASE_WIDTH_LEFT_RANGE);
		}

		/*
		 * eraseWidthRightRange (Range)
		 */
		public RangeElement getEraseWidthRightRange() {
			return getRangeValue(KEY_ERASE_WIDTH_RIGHT_RANGE);
		}

		/*
		 * eraseWidthTopRange (Range)
		 */
		public RangeElement getEraseWidthTopRange() {
			return getRangeValue(KEY_ERASE_WIDTH_TOP_RANGE);
		}

		/*
		 * eraseWidthBottomRange (Range)
		 */
		public RangeElement getEraseWidthBottomRange() {
			return getRangeValue(KEY_ERASE_WIDTH_BOTTOM_RANGE);
		}

	}

	public static class MarginSettingCapability extends CapabilityElement {

		private static final String KEY_MARGIN_WIDTH_FRONT_LEFT_RIGHT_RANGE = "marginWidthFrontLeftRightRange";
		private static final String KEY_MARGIN_WIDTH_FRONT_TOP_BOTTOM_RANGE = "marginWidthFrontTopBottomRange";
		private static final String KEY_MARGIN_WIDTH_BACK_LEFT_RIGHT_RANGE = "marginWidthBackLeftRightRange";
		private static final String KEY_MARGIN_WIDTH_BACK_TOP_BOTTOM_RANGE = "marginWidthBackTopBottomRange";

		MarginSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * marginWidthFrontLeftRightRange (Range)
		 */
		public RangeElement getMarginWidthFrontLeftRightRange() {
			return getRangeValue(KEY_MARGIN_WIDTH_FRONT_LEFT_RIGHT_RANGE);
		}

		/*
		 * marginWidthFrontTopBottomRange (Range)
		 */
		public RangeElement getMarginWidthFrontTopBottomRange() {
			return getRangeValue(KEY_MARGIN_WIDTH_FRONT_TOP_BOTTOM_RANGE);
		}

		/*
		 * marginWidthBackLeftRightRange (Range)
		 */
		public RangeElement getMarginWidthBackLeftRightRange() {
			return getRangeValue(KEY_MARGIN_WIDTH_BACK_LEFT_RIGHT_RANGE);
		}

		/*
		 * marginWidthBackTopBottomRange (Range)
		 */
		public RangeElement getMarginWidthBackTopBottomRange() {
			return getRangeValue(KEY_MARGIN_WIDTH_BACK_TOP_BOTTOM_RANGE);
		}

	}

	public static class PresetStampSettingCapability extends CapabilityElement {

		private static final String KEY_POSITION_LIST = "positionList";
		private static final String KEY_COLOR_LIST = "colorList";
		private static final String KEY_STAMP_KIND_LIST = "stampKindList";
		private static final String KEY_STAMP_NAME_LIST = "stampNameList";
		private static final String KEY_PAGE_LIST = "pageList";
		private static final String KEY_STAMP_SIZE_LIST = "stampSizeList";
		private static final String KEY_STAMP_DENSITY_LIST = "stampDensityList";

		PresetStampSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * positionList (Array[String])
		 */
		public List<String> getPositionList() {
			return getArrayValue(KEY_POSITION_LIST);
		}

		/*
		 * colorList (Array[String])
		 */
		public List<String> getColorList() {
			return getArrayValue(KEY_COLOR_LIST);
		}

		/*
		 * stampKindList (Array[String])
		 */
		public List<String> getStampKindList() {
			return getArrayValue(KEY_STAMP_KIND_LIST);
		}

		/*
		 * stampNameList (Array[Object])
		 */
		public StampNameList getStampNameList() {
			List<Map<String, Object>> value = getArrayValue(KEY_STAMP_NAME_LIST);
			if (value == null) {
				return null;
			}
			return new StampNameList(value);
		}

		/*
		 * pageList (Array[String])
		 */
		public List<String> getPageList() {
			return getArrayValue(KEY_PAGE_LIST);
		}

		/*
		 * stampSizeList (Array[Number])
		 */
		public List<Integer> getStampSizeList() {
			return getNumberArrayValue(KEY_STAMP_SIZE_LIST);
		}

		/*
		 * stampDensityList (Array[String])
		 */
		public List<String> getStampDensityList() {
			return getArrayValue(KEY_STAMP_DENSITY_LIST);
		}

	}

	public static class StampNameList extends ArrayElement<StampName> {

		StampNameList(List<Map<String, Object>> list) {
			super(list);
		}

		@Override
		protected StampName createElement(Map<String, Object> values) {
			return new StampName(values);
		}

	}

	public static class StampName extends CapabilityElement {

		private static final String KEY_ID = "id";
		private static final String KEY_NAME = "name";

		StampName(Map<String, Object> values) {
			super(values);
		}

		/*
		 * id (String)
		 */
		public String getId() {
			return getStringValue(KEY_ID);
		}

		/*
		 * name (String)
		 */
		public String getName() {
			return getStringValue(KEY_NAME);
		}
	}

	public static class UserStampSettingCapability extends CapabilityElement {

		private static final String KEY_POSITION_LIST = "positionList";
		private static final String KEY_COLOR_LIST = "colorList";
		private static final String KEY_STAMP_KIND_LIST = "stampKindList";
		private static final String KEY_STAMP_NAME_LIST = "stampNameList";
		private static final String KEY_PAGE_LIST = "pageList";

		UserStampSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * positionList (Array[String])
		 */
		public List<String> getPositionList() {
			return getArrayValue(KEY_POSITION_LIST);
		}

		/*
		 * colorList (Array[String])
		 */
		public List<String> getColorList() {
			return getArrayValue(KEY_COLOR_LIST);
		}

		/*
		 * stampKindList (Array[String])
		 */
		public List<String> getStampKindList() {
			return getArrayValue(KEY_STAMP_KIND_LIST);
		}

		/*
		 * stampNameList (Array[Object])
		 */
		public StampNameList getStampNameList() {
			List<Map<String, Object>> value = getArrayValue(KEY_STAMP_NAME_LIST);
			if (value == null) {
				return null;
			}
			return new StampNameList(value);
		}

		/*
		 * pageList (Array[String])
		 */
		public List<String> getPageList() {
			return getArrayValue(KEY_PAGE_LIST);
		}

	}

	public static class DateStampSettingCapability extends CapabilityElement {

		private static final String KEY_POSITION_LIST = "positionList";
		private static final String KEY_COLOR_LIST = "colorList";
		private static final String KEY_DATE_FORMAT_LIST = "dateFormatList";
		private static final String KEY_PAGE_LIST = "pageList";
		private static final String KEY_FONT_LIST = "fontList";
		private static final String KEY_FONT_SIZE_LIST = "fontSizeList";

		DateStampSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * positionList (Array[String])
		 */
		public List<String> getPositionList() {
			return getArrayValue(KEY_POSITION_LIST);
		}

		/*
		 * colorList (Array[String])
		 */
		public List<String> getColorList() {
			return getArrayValue(KEY_COLOR_LIST);
		}

		/*
		 * dateFormatList (Array[String])
		 */
		public List<String> getDateFormatList() {
			return getArrayValue(KEY_DATE_FORMAT_LIST);
		}

		/*
		 * pageList (Array[String])
		 */
		public List<String> getPageList() {
			return getArrayValue(KEY_PAGE_LIST);
		}

		/*
		 * fontList (Array[String])
		 */
		public List<String> getFontList() {
			return getArrayValue(KEY_FONT_LIST);
		}

		/*
		 * fontSizeList (Array[String])
		 */
		public List<String> getFontSizeList() {
			return getArrayValue(KEY_FONT_SIZE_LIST);
		}

	}

	public static class PageStampSettingCapability extends CapabilityElement {

		private static final String KEY_POSITION_LIST = "positionList";
		private static final String KEY_COLOR_LIST = "colorList";
		private static final String KEY_FONT_LIST = "fontList";
		private static final String KEY_FONT_SIZE_LIST = "fontSizeList";
		private static final String KEY_PAGE_SETTING_CAPABILITY = "pageSettingCapability";

		PageStampSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * positionList (Array[String])
		 */
		public List<String> getPositionList() {
			return getArrayValue(KEY_POSITION_LIST);
		}

		/*
		 * colorList (Array[String])
		 */
		public List<String> getColorList() {
			return getArrayValue(KEY_COLOR_LIST);
		}

		/*
		 * fontList (Array[String])
		 */
		public List<String> getFontList() {
			return getArrayValue(KEY_FONT_LIST);
		}

		/*
		 * fontSizeList (Array[String])
		 */
		public List<String> getFontSizeList() {
			return getArrayValue(KEY_FONT_SIZE_LIST);
		}

		/*
		 * pageSettingCapability (Object)
		 */
		public PageSettingCapability getPageSettingCapability() {
			Map<String, Object> mapValue = getObjectValue(KEY_PAGE_SETTING_CAPABILITY);
			if (mapValue == null) {
				return null;
			}
			return new PageSettingCapability(mapValue);
		}

	}

	public static class PageSettingCapability extends CapabilityElement {

		private static final String KEY_FORMAT_LIST = "formatList";
		private static final String KEY_FIRST_PAGE_RANGE = "firstPageRange";
		private static final String KEY_FIRST_NUMBER_RANGE = "firstNumberRange";
		private static final String KEY_LAST_NUMBER_RANGE = "lastNumberRange";
		private static final String KEY_TOTAL_PAGE_RANGE = "totalPageRange";

		PageSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * formatList (Array[String])
		 */
		public List<String> getFormatList() {
			return getArrayValue(KEY_FORMAT_LIST);
		}

		/*
		 * firstPageRange (Range)
		 */
		public RangeElement getFirstPageRange() {
			return getRangeValue(KEY_FIRST_PAGE_RANGE);
		}

		/*
		 * firstNumberRange (Range)
		 */
		public RangeElement getFirstNumberRange() {
			return getRangeValue(KEY_FIRST_NUMBER_RANGE);
		}

		/*
		 * lastNumberRange (Range)
		 */
		public RangeElement getLastNumberRange() {
			return getRangeValue(KEY_LAST_NUMBER_RANGE);
		}

		/*
		 * totalPageRange (Range)
		 */
		public RangeElement getTotalPageRange() {
			return getRangeValue(KEY_TOTAL_PAGE_RANGE);
		}

	}

	public static class TextStampSettingCapability extends CapabilityElement {
		private static final String KEY_POSITION_LIST = "positionList";
		private static final String KEY_COLOR_LIST = "colorList";
		private static final String KEY_PAGE_LIST = "pageList";
		private static final String KEY_FONT_LIST = "fontList";
		private static final String KEY_FONT_SIZE_LIST = "fontSizeList";
		private static final String KEY_TEXT_STRING_LENGTH = "textStringLength";

		TextStampSettingCapability(Map<String, Object> values) {
			super(values);
		}

		/*
		 * positionList (Array[String])
		 */
		public List<String> getPositionList() {
			return getArrayValue(KEY_POSITION_LIST);
		}

		/*
		 * colorList (Array[String])
		 */
		public List<String> getColorList() {
			return getArrayValue(KEY_COLOR_LIST);
		}

		/*
		 * pageList (Array[String])
		 */
		public List<String> getPageList() {
			return getArrayValue(KEY_PAGE_LIST);
		}

		/*
		 * fontList (Array[String])
		 */
		public List<String> getFontList() {
			return getArrayValue(KEY_FONT_LIST);
		}

		/*
		 * fontSizeList (Array[String])
		 */
		public List<String> getFontSizeList() {
			return getArrayValue(KEY_FONT_SIZE_LIST);
		}

		/*
		 * textStringLength (MaxLength)
		 */
		public MaxLengthElement getTextStringLength() {
			return getMaxLengthValue(KEY_TEXT_STRING_LENGTH);
		}

	}
}
