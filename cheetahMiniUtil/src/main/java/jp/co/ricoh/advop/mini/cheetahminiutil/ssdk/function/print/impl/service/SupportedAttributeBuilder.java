/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Copies;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PaperSize;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PaperTray;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintColor;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintResolution;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Staple;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.supported.MaxMinSupported;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScanResolution;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Capability;

import java.util.HashMap;
import java.util.Map;

/**
 * 指定したCapabilityオブジェクトから、SupportedAttributeを生成するためのクラスです。
 * The class to create supportedAttribute from specified capability object.
 */
public class SupportedAttributeBuilder {
    private SupportedAttributeBuilder() {
    }

    public static Map<Class<? extends PrintRequestAttribute>, Object> getSupportedAttribute(Capability cap) {
        if( cap == null ) return null;

        Map<Class<? extends PrintRequestAttribute>, Object> retList = new HashMap<Class<? extends PrintRequestAttribute>, Object>();

        if(cap.getCopiesRange() != null ) {
            retList.put(Copies.class, MaxMinSupported.getMaxMinSupported(cap.getCopiesRange()));
        }

        if(cap.getStapleList() != null) {
            retList.put(Staple.class, Staple.getSupportedValue(cap.getStapleList()));
        }

        if(cap.getPrintColorList() != null) {
            retList.put(PrintColor.class, PrintColor.getSupportedValue(cap.getPrintColorList()));
        }

        if (cap.getPrintResolutionList() != null){
            retList.put(PrintResolution.class, PrintResolution.getSupportedValue(cap.getPrintResolutionList()));
        }
        if (cap.getPaperTrayList() != null){
            retList.put(PaperTray.class, PaperTray.getSupportedValue(cap.getPaperTrayList()));
        }
        if (cap.getPaperSizeList() != null){
            retList.put(PaperSize.class, PaperSize.getSupportedValue(cap.getPaperSizeList()));
        }

        return retList;
    }
}
