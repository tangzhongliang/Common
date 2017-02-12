/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.supported;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;

public final class PdfSettingSupported {

    private final List<Boolean> supportedPdfA;
    private final List<Boolean> supportedHighCompressionPdf;
    private final List<Boolean> supportedDigitalSignaturePdf;

    public PdfSettingSupported(Capability.PdfSettingCapability capability) {
        this.supportedPdfA				 	= Conversions.getList(capability.getPdfAList());
        this.supportedHighCompressionPdf	= Conversions.getList(capability.getHighCompressedPdfList());
        this.supportedDigitalSignaturePdf	= Conversions.getList(capability.getDigitalSignaturePdfList());
    }

    public List<Boolean> getSupportedPdfA() {
    	return supportedPdfA;
    }
    
    public List<Boolean> getSupportedHighCompressionPdf() {
    	return supportedHighCompressionPdf;
    }
    
    public List<Boolean> supportedDigitalSignaturePdf() {
    	return supportedDigitalSignaturePdf;
    }
    
}
