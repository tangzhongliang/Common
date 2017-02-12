/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.log.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * プリンタ初期設定の取得要求に対する機器からのレスポンスを示す。<BR>
 * JSON構造をMap型で保持し、各要素にアクセスするためのアクセッサ（getter/setter）を提供する。<BR>
 * Map＜String, Object＞<BR>
 * 　　Key＜String＞は、WebAPIで定義されたJSON項目のキー名をそのまま用いる。<BR>
 * 　　Value＜Object＞は、項目によりデータ型が異なるためObject型を用いる。アクセッサメソッド内で、項目に定められた適切な型へのキャストを実施する。<BR>
 * 階層構造のJSONは、MapのValueが子要素のMapとなるイメージ<BR>
 */
public class GetConfigurationResponseBody extends Element implements ResponseBody {

    /** CNAME */
    static final String CNAME = "GetConfigurationResponseBody";

    /** キー名称：プリンタAPIを利用するのにユーザーコード認証が必要かどうかのフラグ */
    private static final String KEY_USER_CODE_REQUIRED = "userCodeRequired";
    /** キー名称：メディアから読込む印刷機能が有効かどうかのフラグ */
    private static final String KEY_MEMORY_DEVICE_TO_PRINT = "memoryDeviceToPrint";
    /** キー名称：ユーザコード認証の詳細 */
    private static final String KEY_RESTRICTEDFUNC = "restrictedFunc";
    /** キー名称：トレイ設定 */
    private static final String KEY_TRAY_SETTINGS = "traySettings";
    /** キー名称：メディアプリントプレビュー取得 */
    private static final String KEY_BITSWITCH_SETTINGS = "bitSwitchSettings";

    /**
     * 給紙トレイ状態を保持するインスタンスを取得する。<BR>
     * 
     * @return 給紙トレイ状態を保持するインスタンス<BR>
     */
    public TraySettingsArray getTraySettings() {
        List<Map<String, Object>> value = getArrayValue(KEY_TRAY_SETTINGS);
        if (value == null) {
            LogC.d(CNAME, "getTraySettings failed. value = null");
            value = Collections.emptyList();
        }
        return new TraySettingsArray(value);
    }
    
    /**
     * BITSWITCH状態を保持するインスタンスを取得する。<BR>
     * 
     * @return BITSWITCH状態を保持するインスタンス<BR>
     */
    public BitSettingsArray getBitSettings() {
        List<Map<String, Object>> value = getArrayValue(KEY_BITSWITCH_SETTINGS);
        if (value == null) {
            LogC.d(CNAME, "getTraySettings failed. value = null");
            value = Collections.emptyList();
        }
        return new BitSettingsArray(value);
    }

    /**
     * 搭載されている給紙トレイの個別状態リストを示す。<BR>
     * 搭載されているトレイ数がArrayの要素数となる。<BR>
     * JSON array 要素を定義するための基底クラス（ArrayElement）を継承している。<BR>
     * JSON構造をMap型で保持し、各要素にアクセスするためのアクセッサを提供する。<BR>
     * Map＜String, Obejct＞<BR>
     * 　　Key＜String＞は、WebAPIで定義されたJSON項目のキー名をそのまま用いる。<BR>
     * 　　Value＜Object＞は、項目によりデータ型が異なるためObject型を用いる。アクセッサメソッド内で、項目に定められた適切な型へのキャストを実施する。<BR>
     * 階層構造のJSONは、MapのValueが子要素のMapとなる。<BR>
     */
    public static class TraySettingsArray extends ArrayElement<TraySettings> {

        /**
         * 搭載されている給紙トレイの個別状態リストを構築する。<BR>
         * 
         * @param values JSON構造Map
         */
        TraySettingsArray(List<Map<String, Object>> list) {
            super(list);
        }

        /**
         * 搭載されている給紙トレイの個別状態を保持するインスタンスを構築する。<BR>
         * 
         * @return 搭載されている給紙トレイの個別状態を保持するインスタンス
         */
        @Override
        protected TraySettings createElement(Map<String, Object> values) {
            return new TraySettings(values);
        }

    }

    /**
     * プリンタ初期設定の取得要求に対する機器からのレスポンスを構築する。<BR>
     * 
     * @param values JSON構造Map
     */
    public GetConfigurationResponseBody(Map<String, Object> values) {
        super(values);
    }

    /**
     * 認証時に制限されるプリンタ機能
     * 
     * @return restrictedFunc (String)
     */
    public String getRestrictedFunc() {
        return getStringValue(KEY_RESTRICTEDFUNC);
    }

    /**
     * プリンタAPIを利用するのにユーザーコード認証が必要かどうかの情報を取得する。<BR>
     * 
     * @return userCodeRequired (String)
     */
    public String getUserCodeRequired() {
        return getStringValue(KEY_USER_CODE_REQUIRED);
    }

    /**
     * メディアから読込む印刷機能が有効かどうかの情報を取得する。<BR>
     * 
     * @return memoryDeviceToPrint (String)
     */
    public String getMemoryDeviceToPrint() {
        return getStringValue(KEY_MEMORY_DEVICE_TO_PRINT);
    }

    public static class TraySettings extends Element {

        /** キー（トレイ名） */
        private static final String KEY_NAME = "name";
        /** キー（紙サイズ） */
        private static final String KEY_PRIORITY = "priority";

        /**
         * 給紙トレイ個別状態であるinputTraysのデータ構造を構築する。<BR>
         * 
         * @param values JSON構造Map<BR>
         */
        TraySettings(Map<String, Object> values) {
            super(values);
        }

        /*
         * name (String)
         */
        /**
         * 給紙トレイ名称を取得する。<BR>
         * 
         * @return 給紙トレイ名称<BR>
         */
        public String getName() {
            return getStringValue(KEY_NAME);
        }

        /*
         * paperSize (String)
         */
        /**
         * 紙サイズを取得する。<BR>
         * 
         * @return 紙サイズ<BR>
         */
        public String getPriority() {
            return getStringValue(KEY_PRIORITY);
        }
    }
    
    /**
     * 搭載されているBITSWITCHの個別状態リストを示す。<BR>
     * 搭載されているBITSWITCHがArrayの要素数となる。<BR>
     * JSON array 要素を定義するための基底クラス（ArrayElement）を継承している。<BR>
     * JSON構造をMap型で保持し、各要素にアクセスするためのアクセッサを提供する。<BR>
     * Map＜String, Obejct＞<BR>
     * 　　Key＜String＞は、WebAPIで定義されたJSON項目のキー名をそのまま用いる。<BR>
     * 　　Value＜Object＞は、項目によりデータ型が異なるためObject型を用いる。アクセッサメソッド内で、項目に定められた適切な型へのキャストを実施する。<BR>
     * 階層構造のJSONは、MapのValueが子要素のMapとなる。<BR>
     */
    public static class BitSettingsArray extends ArrayElement<BitSwitchSettings> {

        /**
         * 搭載されているBITSWITCHの個別状態リストを構築する。<BR>
         * 
         * @param values JSON構造Map
         */
        BitSettingsArray(List<Map<String, Object>> list) {
            super(list);
        }

        /**
         * 搭載されているBITSWITCHの個別状態を保持するインスタンスを構築する。<BR>
         * 
         * @return 搭載されているBITSWITCHの個別状態を保持するインスタンス
         */
        @Override
        protected BitSwitchSettings createElement(Map<String, Object> values) {
            return new BitSwitchSettings(values);
        }

    }

    /**
     * bitswitch設定
     * @author p000481137
     *
     */
    public static class BitSwitchSettings extends Element {

        /** キー（ビットスイッチ名） */
        private static final String KEY_NAME = "name";
        /** キー（詳細内容） */
        private static final String KEY_DETAIL = "detail";

        /**
         * データ構造を構築する。<BR>
         * 
         * @param values JSON構造Map<BR>
         */
        BitSwitchSettings(Map<String, Object> values) {
            super(values);
        }

        /*
         * name (String)
         */
        /**
         * ビットスイッチ名称を取得する。<BR>
         * 
         * @return 給紙トレイ名称<BR>
         */
        public String getName() {
            return getStringValue(KEY_NAME);
        }

        /*
         * paperSize (String)
         */
        /**
         * 詳細内容を取得する。<BR>
         * 
         * @return 紙サイズ<BR>
         */
        public String getBit() {
            return getStringValue(KEY_DETAIL);
        }
        
        /**
         * エラーケースはプレビュー有りで返す
         * @param array
         * @return
         */
        public static boolean getPrintPreview(BitSettingsArray array) {
            // メディアプリント プレビュー機能(#9 bit7 on：有り off:無し)
            if(null == array) {
                LogC.i(CNAME, "preview info is null");
                return true;
            } else {
                for (BitSwitchSettings set : array) {
                    if(set.getName().equals("9")) {
                        try {
                            if((Integer.parseInt(set.getBit()) & 0x80) == 0x00) {   // 7bit目
                                return true;
                            } else {
                                return false;
                            }
                        } catch (NumberFormatException e) {
                            // 正しい通知が下からこない
                            LogC.i(CNAME, "get bitswitch error!");
                            LogC.w(e);
                        }
                    }
                }
            }
            return true;
        }
    }
    
}
