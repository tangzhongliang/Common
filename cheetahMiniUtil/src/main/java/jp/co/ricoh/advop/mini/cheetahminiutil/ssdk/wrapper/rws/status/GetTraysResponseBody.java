/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.status;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetTraysResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_INPUT_TRAYS	= "inputTrays";
	
	GetTraysResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * inputTrays (Array[Object])
	 */
	public InputTraysArray getInputTrays() {
		List<Map<String, Object>> value = getArrayValue(KEY_INPUT_TRAYS);
		if (value == null) {
			return null;
		}
		return new InputTraysArray(value);
	}
	
	public static class InputTraysArray extends ArrayElement<InputTrays> {
		
		InputTraysArray(List<Map<String, Object>> list) {
			super(list);
		}
		
		@Override
		protected InputTrays createElement(Map<String, Object> values) {
			return new InputTrays(values);
		}
		
	}
	
	public static class InputTrays extends Element {
		
		private static final String KEY_NAME								= "name";
		private static final String KEY_PAPER_SIZE							= "paperSize";
		private static final String KEY_PAPER_SIZE_CUSTOM_HORIZONTAL		= "paperSizeCustomHorizontal";     // SmartSDK V2.00
		private static final String KEY_PAPER_SIZE_COSTOM_VERTICAL			= "paperSizeCustomVertical";       // SmartSDK V2.00
		private static final String KEY_STATUS								= "status";
		private static final String KEY_PAPER_REMAIN						= "paperRemain";
		private static final String KEY_PAPER_SEPARATE_TANDEM_REMAIN_LEFT	= "paperSeparateTandemRemainLeft"; // SmartSDK V2.00
		private static final String KEY_PAPER_SEPARATE_TANDEM_REMAIN_RIGHT	= "paperSeparateTandemRemainRight";// SmartSDK V2.00
		private static final String KEY_PAPER_TYPE							= "paperType";
		private static final String KEY_TRAY_MOVE  							= "trayMove";					   // SmartSDK V2.00
		private static final String KEY_DPX_FORBIDDEN  						= "dpxForbidden";				   // SmartSDK V2.00
		private static final String KEY_SWITCH_FORBIDDEN  					= "switchForbidden";			   // SmartSDK V2.00
		private static final String KEY_TANDEM_INVALID 						= "tandemInvalid";			       // SmartSDK V2.00
		private static final String KEY_SLIP_SHEET							= "slipSheet";                     // SmartSDK V2.00
		private static final String KEY_COVER								= "cover";                         // SmartSDK V2.00
		private static final String KEY_COVER_BACK							= "coverBack";                     // SmartSDK V2.00
		private static final String KEY_CHAPTER_SHEET						= "chapterSheet";                  // SmartSDK V2.00

		
		InputTrays(Map<String, Object> values) {
			super(values);
		}

		/*
		 * name (String)
		 */
		public String getName() {
			return getStringValue(KEY_NAME);
		}
		
		/*
		 * paperSize (String)
		 */
		public String getPaperSize() {
			return getStringValue(KEY_PAPER_SIZE);
		}

        /*
		 * paperSizeCustomHorizontal (String)
		 * @since SmartSDK V2.00
		 */
		public String getPaperSizeCustomHorizontal() {
			return getStringValue(KEY_PAPER_SIZE_CUSTOM_HORIZONTAL);
		}
		
        /*
		 * paperSizeCustomVertical (String)
		 * @since SmartSDK V2.00
		 */
		public String getPaperSizeCustomVertical() {
			return getStringValue(KEY_PAPER_SIZE_COSTOM_VERTICAL);
		}

		/*
		 * status (String)
		 */
		public String getStatus() {
			return getStringValue(KEY_STATUS);
		}
		
		/*
		 * paperRemain (Number)
		 */
		public Integer getPaperRemain() {
			return getNumberValue(KEY_PAPER_REMAIN);
		}
		
		/*
		 * paperSeparateTandemRemainLeft (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getPaperSeparateTandemRemainLeft() {
			return getNumberValue(KEY_PAPER_SEPARATE_TANDEM_REMAIN_LEFT);
		}
		
		/*
		 * paperSeparateTandemRemainRight (Number)
		 * @since SmartSDK V2.00
		 */
		public Integer getPaperSeparateTandemRemainRight() {
			return getNumberValue(KEY_PAPER_SEPARATE_TANDEM_REMAIN_RIGHT);
		}
		
		/*
		 * paperType (String)
		 */
		public String getPaperType() {
			return getStringValue(KEY_PAPER_TYPE);
		}

		/*
		 * trayMove (String)
		 * @since SmartSDK V2.00
		 */
		public String getTrayMove() {
			return getStringValue(KEY_TRAY_MOVE);
		}
		
		/*
		 * dpxForbidden (Boolean)
		 * @since SmartSDK V2.00
		 */
		public Boolean getDpxForbidden() {
			return getBooleanValue(KEY_DPX_FORBIDDEN);
		}
		
		/*
		 * switchForbidden (Boolean)
		 * @since SmartSDK V2.00
		 */
		public Boolean getSwitchForbidden() {
			return getBooleanValue(KEY_SWITCH_FORBIDDEN);
		}
		
		/*
		 * tandemInvalid (Boolean)
		 * @since SmartSDK V2.00
		 */
		public Boolean getTandemInvalid() {
			return getBooleanValue(KEY_TANDEM_INVALID);
		}
		
        /*
		 * slipSheet (Boolean)
		 * @since SmartSDK V2.00
		 */
		public Boolean getSlipSheet() {
			return getBooleanValue(KEY_SLIP_SHEET);
		}
		
        /*
		 * cover (Boolean)
		 * @since SmartSDK V2.00
		 */
		public Boolean getCover() {
			return getBooleanValue(KEY_COVER);
		}
		
        /*
		 * coverBack (Boolean)
		 * @since SmartSDK V2.00
		 */
		public Boolean getCoverBack() {
			return getBooleanValue(KEY_COVER_BACK);
		}
		
        /*
		 * chapterSheet (String)
		 * @since SmartSDK V2.00
		 */
		public String getChapterSheet() {
			return getStringValue(KEY_CHAPTER_SHEET);
		}
		
	}
	
}
