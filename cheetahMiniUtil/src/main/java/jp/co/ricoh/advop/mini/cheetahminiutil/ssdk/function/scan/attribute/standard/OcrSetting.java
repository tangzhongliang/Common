/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;

public final class OcrSetting implements ScanRequestAttribute {
	
	private static final String NAME_OCR_SETTING = "ocrSetting";
	
	private static final String NAME_OCR_LANGUAGE = "ocrLanguage";
	private static final String NAME_OMIT_BLANK_PAGE = "omitBlankPage";
	private static final String NAME_BLANK_PAGE_SENSITIVITY_LEVEL = "blankPageSensitivityLevel";
	private static final String NAME_AUTO_FILE_NAME = "autoFileName";
	private static final String NAME_VERTICAL_JUDGMENT = "verticalJudgment"; //SmartSDK V2.00
	
	
	private OcrLanguage ocrLanguage;
	private boolean omitBlankPage;
	private BlankPageSensitivityLevel blankPageSensitivityLevel;
	private boolean autoFileName;
	private boolean verticalJudgment; //SmartSDK V2.00
	
	
	public OcrSetting() {
		omitBlankPage = false;
		autoFileName = false;
		verticalJudgment = true;
	}
	
	public OcrLanguage getOcrLanguage() {
		return ocrLanguage;
	}
	
	public void setOcrLanguage(OcrLanguage value) {
		ocrLanguage = value;
	}
	
	public boolean isOmitBlankPage() {
		return omitBlankPage;
	}
	
	public void setOmitBlankPage(boolean value) {
		omitBlankPage = value;
	}
	
	public BlankPageSensitivityLevel getBlankPageSensitivityLevel() {
		return blankPageSensitivityLevel;
	}
	
	public void setBlankPageSensitivityLevel(BlankPageSensitivityLevel value) {
		blankPageSensitivityLevel = value;
	}
	
	public boolean isAutoFileName() {
		return autoFileName;
	}
	
	public void setAutoFileName(boolean value) {
		autoFileName = value;
	}
	
	/*
	 * @since SmartSDK V2.00
	 */
	public boolean isVerticalJudgment() {
		return verticalJudgment;
	}
	/*
	 * @since SmartSDK V2.00
	 */
	public void setVerticalJudgment(boolean value) {
		verticalJudgment = value;
	}
	
	
	@Override
	public Class<?> getCategory() {
		return OcrSetting.class;
	}

	@Override
	public String getName() {
		return NAME_OCR_SETTING;
	}

	@Override
	public Object getValue() {
		Map<String, Object> values = new HashMap<String, Object>();
		
		if (ocrLanguage != null) {
			values.put(NAME_OCR_LANGUAGE, ocrLanguage.getValue());
		}
		values.put(NAME_OMIT_BLANK_PAGE, Boolean.valueOf(omitBlankPage));
		if (blankPageSensitivityLevel != null) {
			values.put(NAME_BLANK_PAGE_SENSITIVITY_LEVEL, blankPageSensitivityLevel.getValue());
		}
		values.put(NAME_AUTO_FILE_NAME, Boolean.valueOf(autoFileName));
		values.put(NAME_VERTICAL_JUDGMENT, Boolean.valueOf(verticalJudgment)); //SmartSDK V2.00
		
		return values;
	}
	
	
	public static enum OcrLanguage {
		
		JAPANESE("japanese"),
		ENGLISH("english"),
		GERMAN("german"),
		FRENCH("french"),
		ITALIAN("italian"),
		SPANISH("spanish"),
		DUTCH("dutch"),
		PORTUGUESE("portuguese"),
		POLISH("polish"),
		SWEDISH("swedish"),
		FINNISH("finnish"),
		HUNGARIAN("hungarian"),
		NORWEGIAN("norwegian"),
		DANISH("danish");
		
		private final String value;
		
		private OcrLanguage(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}

        private static volatile Map<String, OcrLanguage> directroy = null;

        private static Map<String, OcrLanguage> getDirectroy() {
            if( directroy == null ) {
                OcrLanguage[] languages = values();
                Map<String, OcrLanguage> d = new HashMap<String, OcrLanguage>();
                for(OcrLanguage language : languages) {
                    d.put(language.getValue(), language);
                }
                directroy = d;
            }
            return directroy;
        }

        private static OcrLanguage fromString(String value) {
            return getDirectroy().get(value);
        }

        public static List<OcrLanguage> getSupportedValue(List<String> values) {
        	if (values == null) {
        		return Collections.emptyList();
        	}

            List<OcrLanguage> list = new ArrayList<OcrLanguage>();
            for(String value : values) {
                OcrLanguage language = fromString(value);
                if(language != null) {
                    list.add(language);
                }
            }
            return list;
        }
	}
	
	public static enum BlankPageSensitivityLevel {
		
		LEVEL1("level1"),
		LEVEL2("level2"),
		LEVEL3("level3"),
		LEVEL4("level4"),
		LEVEL5("level5");
		
		private final String value;
		
		private BlankPageSensitivityLevel(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}

        private static volatile  Map<String, BlankPageSensitivityLevel> directroy = null;

        private static Map<String,BlankPageSensitivityLevel> getDirectory() {
            if(directroy == null){
                Map<String, BlankPageSensitivityLevel> d = new HashMap<String, BlankPageSensitivityLevel>();
                for(BlankPageSensitivityLevel sensitivityLevel : values()) {
                    d.put(sensitivityLevel.getValue(), sensitivityLevel);
                }
                directroy = d;
            }
            return directroy;
        }

        private static BlankPageSensitivityLevel fromString(String value) {
            return getDirectory().get(value);
        }

        public static List<BlankPageSensitivityLevel> getSupportedValue(List<String> values) {
        	if (values == null) {
        		return Collections.emptyList();
        	}

        	List<BlankPageSensitivityLevel> list = new ArrayList<BlankPageSensitivityLevel>();
            for(String value : values) {
                BlankPageSensitivityLevel sensitivityLevel = fromString(value);
                if( sensitivityLevel != null ) {
                    list.add(sensitivityLevel);
                }
            }
            return list;
        }
	}
	
}
