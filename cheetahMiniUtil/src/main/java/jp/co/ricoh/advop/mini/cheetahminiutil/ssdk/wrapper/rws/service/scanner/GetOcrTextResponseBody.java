/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

import java.util.List;
import java.util.Map;

public class GetOcrTextResponseBody  extends Element implements ResponseBody {

    private static final String KEY_TITLE_1             = "title1";
    private static final String KEY_TITLE_2             = "title2";
    private static final String KEY_DPI_X               = "dpiX";
    private static final String KEY_DPI_Y               = "dpiY";
    private static final String KEY_WIDTH_DOT           = "widthDot";
    private static final String KEY_HEIGHT_DOT          = "heightDot";
    private static final String KEY_ROT_ANGLE           = "rotAngle";
    private static final String KEY_TEXT_REGION         = "textRegion";
    private static final String KEY_TEXT_HEADER_REGION  = "textHeaderRegion";
    private static final String KEY_TEXT_FOOTER_REGION  = "textFooterRegion";
    private static final String KEY_TEXT_CAPTION_REGION = "textCaptionRegion";
    private static final String KEY_TEXT_BODY_REGION    = "textBodyRegion";
    private static final String KEY_TEXT_REVERSE_REGION = "textReverseRegion";
    private static final String KEY_TEXT_TABLE_REGION   = "textTableRegion";
    private static final String KEY_V_SEPARATOR_REGION  = "vSeparatorRegion";
    private static final String KEY_H_SEPARATOR_REGION  = "hSeparatorRegion";
    private static final String KEY_DRAW_REGION         = "drawRegion";
    private static final String KEY_BLOCK_LINE_REGION   = "blockLineRegion";
    private static final String KEY_PICTURE_REGION      = "pictureRegion";
    private static final String KEY_OTHER_REGION        = "otherRegion";
    private static final String KEY_UNKNOWN_REGION      = "unknownRegion";


    GetOcrTextResponseBody(Map<String, Object> values) {
        super(values);
    }

    /*
     * title1 (String)
     */
    public String getTitle1() {
        return getStringValue(KEY_TITLE_1);
    }

    /*
     * title2 (String)
     */
    public String getTitle2() {
        return getStringValue(KEY_TITLE_2);
    }

    /*
     * dpiX (Number)
     */
    public Integer getDpiX() {
        return getNumberValue(KEY_DPI_X);
    }

    /*
     * dpiY (Number)
     */
    public Integer getDpiY() {
        return getNumberValue(KEY_DPI_Y);
    }

    /*
     * widthDot (Number)
     */
    public Integer getWidthDot() {
        return getNumberValue(KEY_WIDTH_DOT);
    }

    /*
     * heightDot (Number)
     */
    public Integer getHeightDot() {
        return getNumberValue(KEY_HEIGHT_DOT);
    }

    /*
     * rotAngle (Number)
     */
    public Integer getRotAngle() {
        return getNumberValue(KEY_ROT_ANGLE);
    }

    /*
     * textRegion (Array[Object])
     */
    public TextRegionArray getTextRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * textHeaderRegion (Array[Object])
     */
    public TextRegionArray getTextHeaderRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_HEADER_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * textFooterRegion (Array[Object])
     */
    public TextRegionArray getTextFooterRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_FOOTER_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * textCaptionRegion (Array[Object])
     */
    public TextRegionArray getTextCaptionRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_CAPTION_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * textBodyRegion (Array[Object])
     */
    public TextRegionArray getTextBodyRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_BODY_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * textReverseRegion (Array[Object])
     */
    public TextRegionArray getTextReverseRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_REVERSE_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * textTableRegion (Array[Object])
     */
    public TextRegionArray getTextTableRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_TEXT_TABLE_REGION);
        if (value == null) {
            return null;
        }
        return new TextRegionArray(value);
    }

    /*
     * vSeparatorRegion (Array[Object])
     */
    public NonTextRegionArray getVSeparatorRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_V_SEPARATOR_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }

    /*
     * hSeparatorRegion (Array[Object])
     */
    public NonTextRegionArray getHSeparatorRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_H_SEPARATOR_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }

    /*
     * drawRegion (Array[Object])
     */
    public NonTextRegionArray getDrawRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_DRAW_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }

    /*
     * blockLineRegion (Array[Object])
     */
    public NonTextRegionArray getBlockLineRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_BLOCK_LINE_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }

    /*
     * pictureRegion (Array[Object])
     */
    public NonTextRegionArray getPictureRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_PICTURE_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }

    /*
     * otherRegion (Array[Object])
     */
    public NonTextRegionArray getOtherRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_OTHER_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }

    /*
     * unknownRegion (Array[Object])
     */
    public NonTextRegionArray getUnknownRegion() {
        List<Map<String, Object>> value = getArrayValue(KEY_UNKNOWN_REGION);
        if (value == null) {
            return null;
        }
        return new NonTextRegionArray(value);
    }


    public static class TextRegionArray extends ArrayElement<TextRegion> {

        TextRegionArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected TextRegion createElement(Map<String, Object> values) {
            return new TextRegion(values);
        }

    }

    public static class TextRegion extends Element {

        private static final String KEY_REGION_NUM  = "regionNum";
        private static final String KEY_REGION_ID   = "regionId";
        private static final String KEY_LINE_DIR    = "lineDir";
        private static final String KEY_LANG        = "lang";
        private static final String KEY_RECT        = "rect";
        private static final String KEY_OCR_DATA    = "ocrData";

        TextRegion(Map<String, Object> values) {
            super(values);
        }

        /*
         * regionNum (Number)
         */
        public Integer getRegionNum() {
            return getNumberValue(KEY_REGION_NUM);
        }

        /*
         * regionId (Number)
         */
        public Integer getRegionId() {
            return getNumberValue(KEY_REGION_ID);
        }

        /*
         * lineDir (String)
         */
        public String getLineDir() {
            return getStringValue(KEY_LINE_DIR);
        }

        /*
         * lang (String)
         */
        public String getLang() {
            return getStringValue(KEY_LANG);
        }

        /*
         * rect (Object)
         */
        public OcrRect getRect() {
            Map<String, Object> value = getObjectValue(KEY_RECT);
            if (value == null) {
                return null;
            }
            return new OcrRect(value);
        }

        /*
         * ocrData (Array[Object])
         */
        public OcrDataArray getOcrData() {
            List<Map<String, Object>> value = getArrayValue(KEY_OCR_DATA);
            if (value == null) {
                return null;
            }
            return new OcrDataArray(value);
        }

    }

    public static class OcrRect extends Element {

        private static final String KEY_X_S     = "xS";
        private static final String KEY_Y_S     = "yS";
        private static final String KEY_X_E     = "xE";
        private static final String KEY_Y_E     = "yE";

        OcrRect(Map<String, Object> values) {
            super(values);
        }

        /*
         * xS (Number)
         */
        public Integer getXS() {
            return getNumberValue(KEY_X_S);
        }

        /*
         * yS (Number)
         */
        public Integer getYS() {
            return getNumberValue(KEY_Y_S);
        }

        /*
         * xE (Number)
         */
        public Integer getXE() {
            return getNumberValue(KEY_X_E);
        }

        /*
         * yE (Number)
         */
        public Integer getYE() {
            return getNumberValue(KEY_Y_E);
        }

    }

    public static class OcrDataArray extends ArrayElement<OcrData> {

        OcrDataArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected OcrData createElement(Map<String, Object> values) {
            return new OcrData(values);
        }

    }

    public static class OcrData extends Element {

        private static final String KEY_LINE        = "line";

        OcrData(Map<String, Object> values) {
            super(values);
        }

        /*
         * line (Array[Object])
         */
        public OcrLineArray getLine() {
            List<Map<String, Object>> value = getArrayValue(KEY_LINE);
            if (value == null) {
                return null;
            }
            return new OcrLineArray(value);
        }
    }

    public static class OcrLineArray extends ArrayElement<OcrLine> {

        OcrLineArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected OcrLine createElement(Map<String, Object> values) {
            return new OcrLine(values);
        }

    }

    public static class OcrLine extends Element {

        private static final String KEY_RECT        = "rect";
        private static final String KEY_CERT        = "cert";
        private static final String KEY_CODE        = "code";

        OcrLine(Map<String, Object> values) {
            super(values);
        }

        /*
         * rect (Object)
         */
        public OcrRect getRect() {
            Map<String, Object> value = getObjectValue(KEY_RECT);
            if (value == null) {
                return null;
            }
            return new OcrRect(value);
        }

        /*
         * cert (Number)
         */
        public Integer getCert() {
            return getNumberValue(KEY_CERT);
        }

        /*
         * code (Array[String])
         */
        public List<String> getCode() {
            return getArrayValue(KEY_CODE);
        }

    }

    public static class NonTextRegionArray extends ArrayElement<NonTextRegion> {

        NonTextRegionArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected NonTextRegion createElement(Map<String, Object> values) {
            return new NonTextRegion(values);
        }

    }

    public static class NonTextRegion extends Element {

        private static final String KEY_REGION_NUM  = "regionNum";
        private static final String KEY_REGION_ID   = "regionId";
        private static final String KEY_LINE_DIR    = "lineDir";
        private static final String KEY_LANG        = "lang";
        private static final String KEY_RECT        = "rect";

        NonTextRegion(Map<String, Object> values) {
            super(values);
        }

        /*
         * regionNum (Number)
         */
        public Integer getRegionNum() {
            return getNumberValue(KEY_REGION_NUM);
        }

        /*
         * regionId (Number)
         */
        public Integer getRegionId() {
            return getNumberValue(KEY_REGION_ID);
        }

        /*
         * lineDir (String)
         */
        public String getLineDir() {
            return getStringValue(KEY_LINE_DIR);
        }

        /*
         * lang (String)
         */
        public String getLang() {
            return getStringValue(KEY_LANG);
        }

        /*
         * rect (Object)
         */
        public OcrRect getRect() {
            Map<String, Object> value = getObjectValue(KEY_RECT);
            if (value == null) {
                return null;
            }
            return new OcrRect(value);
        }

    }

}
