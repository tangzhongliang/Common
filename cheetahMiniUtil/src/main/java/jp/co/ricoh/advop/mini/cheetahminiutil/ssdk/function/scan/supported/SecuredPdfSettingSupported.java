/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.SecuredPdfSetting;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

public final class SecuredPdfSettingSupported {

    private final List<Boolean> supportedEncryptions;
    private final MaxLengthSupported supportedEncryptionPasswords;
    private final List<SecuredPdfSetting.EncryptionLevel> supportedEncryptionLevels;
    private final List<Boolean> supportedDocumentSecurities;
    private final MaxLengthSupported supportedDocumentSecurityPasswords;
    private final List<SecuredPdfSetting.AllowPrint> supportedAllowPrints;
    private final List<Boolean> supportedAllowEdits;
    private final List<Boolean> supportedAllowCopyAndExtracts;

    public SecuredPdfSettingSupported(Capability.SecuredPdfSettingCapability capability) {
    	supportedEncryptions = Conversions.getList(capability.getEncryptionList());
    	supportedEncryptionPasswords = MaxLengthSupported.getMaxLengthSupported(capability.getEncryptionPasswordLength());
    	supportedEncryptionLevels = SecuredPdfSetting.EncryptionLevel.getSupportedValue(capability.getEncryptionLevelList());
    	supportedDocumentSecurities = Conversions.getList(capability.getDocumentSecurityList());
    	supportedDocumentSecurityPasswords = MaxLengthSupported.getMaxLengthSupported(capability.getDocumentSecurityPasswordLength());
    	supportedAllowPrints = SecuredPdfSetting.AllowPrint.getSupportedValue(capability.getAllowPrintList());
    	supportedAllowEdits = Conversions.getList(capability.getAllowEditList());
    	supportedAllowCopyAndExtracts = Conversions.getList(capability.getAllowCopyAndExtractList());
    }
    
    public List<Boolean> getSupportedEncryptions() {
        return supportedEncryptions;
    }

    public MaxLengthSupported getSupportedEncryptionPasswords() {
        return supportedEncryptionPasswords;
    }

    public List<SecuredPdfSetting.EncryptionLevel> getSupportedEncryptionLevels() {
        return supportedEncryptionLevels;
    }

    public List<Boolean> getSupportedDocumentSecurities() {
        return supportedDocumentSecurities;
    }

    public MaxLengthSupported getSupportedDocumentSecurityPasswords() {
        return supportedDocumentSecurityPasswords;
    }

    public List<SecuredPdfSetting.AllowPrint> getSupportedAllowPrints() {
        return supportedAllowPrints;
    }

    public List<Boolean> getSupportedAllowEdits() {
        return supportedAllowEdits;
    }

    public List<Boolean> getSupportedAllowCopyAndExtracts() {
        return supportedAllowCopyAndExtracts;
    }

}
