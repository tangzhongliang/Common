/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OcrSetting;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OcrSetting.BlankPageSensitivityLevel;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OcrSetting.OcrLanguage;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

public final class OcrSettingSupported {

    private final List<OcrSetting.OcrLanguage> supportedOcrLanguages;
    private final List<Boolean> supportedOmitBlankPages;
    private final List<OcrSetting.BlankPageSensitivityLevel> supportedBlankPageSensitivityLevels;
    private final List<Boolean> supportedAutoFileNames;
    private final List<Boolean> supportedVerticalJudgments; //SmartSDK V2.00

    public OcrSettingSupported(Capability.OcrSettingCapability capability) {
    	supportedOcrLanguages = OcrLanguage.getSupportedValue(capability.getOcrLanguageList());
        supportedOmitBlankPages = Conversions.getList(capability.getOmitBlankPageList());
        supportedBlankPageSensitivityLevels = BlankPageSensitivityLevel.getSupportedValue(capability.getBlankPageSensitivityLevelList());
        supportedAutoFileNames = Conversions.getList(capability.getAutoFileNameList());
        supportedVerticalJudgments = Conversions.getList(capability.getVerticalJudgmentList());
    }

    public List<OcrSetting.OcrLanguage> getSupportedOcrLanguages() {
        return supportedOcrLanguages;
    }

    public List<Boolean> getSupportedOmitBlankPages() {
        return supportedOmitBlankPages;
    }

    public List<OcrSetting.BlankPageSensitivityLevel> getSupportedBlankPageSensitivityLevels() {
        return supportedBlankPageSensitivityLevels;
    }

    public List<Boolean> getSupportedAutoFileNames() {
        return supportedAutoFileNames;
    }

    /*
     * @since SmartSDK V2.00
     */
    public List<Boolean> getSupportedVerticalJudgments() {
        return supportedVerticalJudgments;
    }

}
