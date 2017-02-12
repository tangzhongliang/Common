/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

import java.util.Map;



/**
 * スキャナ初期設定の取得要求に対する機器からのレスポンスを示す。<BR>
 * JSON構造をMap型で保持し、各要素にアクセスするためのアクセッサ（getter/setter）を提供する。<BR>
 * Map＜String, Object＞<BR>
 * 　　Key＜String＞は、WebAPIで定義されたJSON項目のキー名をそのまま用いる。<BR>
 * 　　Value＜Object＞は、項目によりデータ型が異なるためObject型を用いる。アクセッサメソッド内で、項目に定められた適切な型へのキャストを実施する。<BR>
 * 階層構造のJSONは、MapのValueが子要素のMapとなるイメージ<BR>
 */
public class GetConfigurationResponseBody extends Element implements ResponseBody {
	/** キー名称：DSM以外の宛先表の使用設定 */
	private static final String KEY_NON_DSM_DESTINATION     = "nonDsmDestination";
	/** キー名称：読み取りサイズ登録1：読み取りサイズの名前 */
	private static final String KEY_REGISTERD_SCAN_SIZE_NAME_1 = "registerdScanSizeName1";
	/** キー名称：読み取りサイズ登録2：読み取りサイズの名前 */
	private static final String KEY_REGISTERD_SCAN_SIZE_NAME_2 = "registerdScanSizeName2";
	/** キー名称：読み取りサイズ登録3：読み取りサイズの名前 */
	private static final String KEY_REGISTERD_SCAN_SIZE_NAME_3 = "registerdScanSizeName3";
	/** キー名称：読み取りサイズ登録4：読み取りサイズの名前 */
	private static final String KEY_REGISTERD_SCAN_SIZE_NAME_4 = "registerdScanSizeName4";
	/** キー名称：読み取りサイズ登録5：読み取りサイズの名前 */
	private static final String KEY_REGISTERD_SCAN_SIZE_NAME_5 = "registerdScanSizeName5";
	/** キー名称：読み取りサイズ登録1：原稿幅 */
	private static final String KEY_REGISTERD_SCAN_SIZE_X_1 = "registerdScanSizeX1";
	/** キー名称：読み取りサイズ登録2：原稿幅 */
	private static final String KEY_REGISTERD_SCAN_SIZE_X_2 = "registerdScanSizeX2";
	/** キー名称：読み取りサイズ登録3：原稿幅 */
	private static final String KEY_REGISTERD_SCAN_SIZE_X_3 = "registerdScanSizeX3";
	/** キー名称：読み取りサイズ登録4：原稿幅 */
	private static final String KEY_REGISTERD_SCAN_SIZE_X_4 = "registerdScanSizeX4";
	/** キー名称：読み取りサイズ登録5：原稿幅 */
	private static final String KEY_REGISTERD_SCAN_SIZE_X_5 = "registerdScanSizeX5";
	/** キー名称：読み取りサイズ登録1：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_Y_1	= "registerdScanSizeY1";
    /** キー名称：読み取りサイズ登録2：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_Y_2 = "registerdScanSizeY2";
    /** キー名称：読み取りサイズ登録3：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_Y_3 = "registerdScanSizeY3";
    /** キー名称：読み取りサイズ登録4：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_Y_4 = "registerdScanSizeY4";
    /** キー名称：読み取りサイズ登録5：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_Y_5 = "registerdScanSizeY5";

    /** キー名称：読み取りサイズ登録1：原稿の横方向の長さ */
	private static final String KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_1 = "registerdScanSizeHorizontal1";
	/** キー名称：読み取りサイズ登録2：原稿の横方向の長さ */
	private static final String KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_2 = "registerdScanSizeHorizontal2";
	/** キー名称：読み取りサイズ登録3：原稿の横方向の長さ */
	private static final String KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_3 = "registerdScanSizeHorizontal3";
	/** キー名称：読み取りサイズ登録4：原稿の横方向の長さ */
	private static final String KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_4 = "registerdScanSizeHorizontal4";
	/** キー名称：読み取りサイズ登録5：原稿の横方向の長さ */
	private static final String KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_5 = "registerdScanSizeHorizontal5";
	/** キー名称：読み取りサイズ登録1：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_VERTICAL_1 = "registerdScanSizeVertical1";
    /** キー名称：読み取りサイズ登録2：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_VERTICAL_2 = "registerdScanSizeVertical2";
    /** キー名称：読み取りサイズ登録3：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_VERTICAL_3 = "registerdScanSizeVertical3";
    /** キー名称：読み取りサイズ登録4：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_VERTICAL_4 = "registerdScanSizeVertical4";
    /** キー名称：読み取りサイズ登録5：原稿長 */
    private static final String KEY_REGISTERD_SCAN_SIZE_VERTICAL_5 = "registerdScanSizeVertical5";

    /** キー名称：送信メールサイズ制限 */
	private static final String KEY_LIMIT_EMAIL_SIZE        = "limitEmailSize";
	/** キー名称：送信メールサイズ制限サイズ */
	private static final String KEY_MAX_EMAIL_SIZE          = "maxEmailSize";
	/** キー名称：メールサイズ制限オーバー時分割 */
	private static final String KEY_EMAIL_DIVIDING          = "emailDividing";
	/** キー名称：メールサイズ制限オーバー分割時最大分割数 */
	private static final String KEY_EMAIL_DIVIDING_NUMBER   = "emailDividingNumber";
	/** キー名称：読み取り終了時にジョブ設定をリセットするかどうかのフラグ */
	private static final String KEY_RESET_SECRET_JOB_SETTINGS = "resetSecretJobSettings";
	/** キー名称：メニュープロテクト設定 */
	private static final String KEY_MENU_PROTECT            = "menuProtect";
	/** キー名称：スキャナオートリセットタイマー設定 */
	private static final String KEY_AUTO_RESET_TIMER        = "autoResetTimer";
	/** キー名称：スキャナAPIを利用するのに認証が必要かどうかのフラグ */
	private static final String KEY_AUTHENTICATION_REQUIRED = "authenticationRequired";
	/** キー名称：宛先をプログラム登録する/しない */
	private static final String KEY_PROGRAM_SETTING_FOR_DESTINATIONS = "programSettingForDestinations";
	/** キー名称：圧縮設定（白黒２値） */
    private static final String KEY_COMPRESSION_MONOCHROME = "compressionMonochrome";
    /** キー名称：圧縮設定（ｸﾞﾚｰｽｹｰﾙ/ﾌﾙｶﾗｰ） */
    private static final String KEY_COMPRESSION_GRAY_FULLCOLOR = "compressionGrayscaleFullcolor";
    /** キー名称：SMBプロトコル */
    private static final String KEY_SMB_PROTOCOL = "smbProtocol";
	/** キー名称：OCR変換モジュール */
    private static final String KEY_OCR_MODULE = "ocrModule";
	/** キー名称：宛先表見出し切り替え */
    private static final String KEY_SWITCH_TITLE = "switchTitle";
	/** キー名称：宛先表初期表示選択 */
    private static final String KEY_DESTINATION_LIST_DISPLAY_PRIORITY = "destinationListDisplayPriority";
	/** キー名称：WSD/DSM機能の利用 */
    private static final String KEY_USE_WSD_OR_DSM = "useWsdOrDsm";
	/** キー名称：変倍率設定1 */
    private static final String KEY_MAGNIFICATION_RATIO_1 = "magnificationRatio1";
	/** キー名称：変倍率設定2 */
    private static final String KEY_MAGNIFICATION_RATIO_2 = "magnificationRatio2";
	/** キー名称：変倍率設定3 */
    private static final String KEY_MAGNIFICATION_RATIO_3 = "magnificationRatio3";
	/** キー名称：変倍率設定4 */
    private static final String KEY_MAGNIFICATION_RATIO_4 = "magnificationRatio4";
	/** キー名称：変倍率設定5 */
    private static final String KEY_MAGNIFICATION_RATIO_5 = "magnificationRatio5";
	/** キー名称：変倍率設定6 */
    private static final String KEY_MAGNIFICATION_RATIO_6 = "magnificationRatio6";
	/** キー名称：変倍率設定7 */
    private static final String KEY_MAGNIFICATION_RATIO_7 = "magnificationRatio7";
	/** キー名称：変倍率設定8 */
    private static final String KEY_MAGNIFICATION_RATIO_8 = "magnificationRatio8";
	/** キー名称：変倍率設定9 */
    private static final String KEY_MAGNIFICATION_RATIO_9 = "magnificationRatio9";
	/** キー名称：変倍率設定10 */
    private static final String KEY_MAGNIFICATION_RATIO_10 = "magnificationRatio10";
	/** キー名称：変倍率設定11 */
    private static final String KEY_MAGNIFICATION_RATIO_11 = "magnificationRatio11";
	/** キー名称：変倍率設定12 */
    private static final String KEY_MAGNIFICATION_RATIO_12 = "magnificationRatio12";
	/** キー名称：シングルページ番号桁設定 */
    private static final String KEY_SINGLE_PAGE_FILES_DIGITS = "singlePageFilesDigits";
	/** キー名称：ScanToフォルダのパスワード入力設定 */
    private static final String KEY_SCAN_AND_SEND_FOLDER_PASSWORD_INPUT = "scanAndSendFolderPasswordInput";
	/** キー名称：URLリンクから文書を直接ダウンロード */
    private static final String KEY_DOWNLOAD_FILE_DIRECTLY_FROM_URL_LINK = "downloadFileDirectlyFromUrlLink";
	/** キー名称：文書送信方法 */
    private static final String KEY_FILE_EMAILING_METHOD = "fileEmailingMethod";
    /** キー名称：クリアライトPDF作成時の圧縮方式 */
    private static final String KEY_COMPRESSION_METHOD_HIGH_COMPRESSION_PDF = "compressionMethodHighCompressionPdf";
    /** キー名称：クリアライト（高圧縮）PDF圧縮率設定 */
    private static final String KEY_COMPRESSION_LEVEL_HIGH_COMPRESSION_PDF = "compressionLevelHighCompressionPdf";
    /** キー名称：OCRテキスト付きPDF白紙検知レベル */
    private static final String KEY_BLANK_PAGE_SENSITIVITY_LEVEL = "blankPageSensitivityLevel";
    /** キー名称：S/MIME メール送信時の証明書チェック方法 */
    private static final String KEY_SMIME_CHECK_MODE = "smimeCheckMode";

    /** 変倍率設定登録番号1 */
    public static final int MAGNIFICATION_RATIO_NO_1 = 1;
    /** 変倍率設定登録番号2 */
    public static final int MAGNIFICATION_RATIO_NO_2 = 2;
    /** 変倍率設定登録番号3 */
    public static final int MAGNIFICATION_RATIO_NO_3 = 3;
    /** 変倍率設定登録番号4 */
    public static final int MAGNIFICATION_RATIO_NO_4 = 4;
    /** 変倍率設定登録番号5 */
    public static final int MAGNIFICATION_RATIO_NO_5 = 5;
    /** 変倍率設定登録番号6 */
    public static final int MAGNIFICATION_RATIO_NO_6 = 6;
    /** 変倍率設定登録番号7 */
    public static final int MAGNIFICATION_RATIO_NO_7 = 7;
    /** 変倍率設定登録番号8 */
    public static final int MAGNIFICATION_RATIO_NO_8 = 8;
    /** 変倍率設定登録番号9 */
    public static final int MAGNIFICATION_RATIO_NO_9 = 9;
    /** 変倍率設定登録番号10 */
    public static final int MAGNIFICATION_RATIO_NO_10 = 10;
    /** 変倍率設定登録番号11 */
    public static final int MAGNIFICATION_RATIO_NO_11 = 11;
    /** 変倍率設定登録番号12 */
    public static final int MAGNIFICATION_RATIO_NO_12 = 12;

	/**
	 * スキャナ初期設定の取得要求に対する機器からのレスポンスを構築する。<BR>
	 * @param values JSON構造Map
	 */
	public GetConfigurationResponseBody(Map<String, Object> values) {
		super(values);
	}

	/**
	 * DSM以外の宛先表の使用設定情報を取得する。<BR>
	 * @return nonDsmDestination (String)
	 */
	public String getNonDsmDestination() {
		return getStringValue(KEY_NON_DSM_DESTINATION);
	}

	/**
	 * 「読み取りサイズ登録*：読み取りサイズの名前」情報を取得する。(*は1～5の何れかを示す)<BR>
	 * @return registerdScanSizeName* (String)
	 */
    public String getRegisterdScanSizeName(int num) {
        String key = null;
        switch (num) {
            case 1:
                key = KEY_REGISTERD_SCAN_SIZE_NAME_1;
                break;
            case 2:
                key = KEY_REGISTERD_SCAN_SIZE_NAME_2;
                break;
            case 3:
                key = KEY_REGISTERD_SCAN_SIZE_NAME_3;
                break;
            case 4:
                key = KEY_REGISTERD_SCAN_SIZE_NAME_4;
                break;
            case 5:
                key = KEY_REGISTERD_SCAN_SIZE_NAME_5;
                break;
            default:
                break;
        }
        return getStringValue(key);
    }

	/**
	 * 「読み取りサイズ登録*：原稿幅」情報を取得する。(*は1～5の何れかを示す)<BR>
	 * @return registerdScanSizeX* (String)
	 */
    public String getRegisterdScanSizeX(int num) {
        String key = null;
        switch (num) {
            case 1:
                key = KEY_REGISTERD_SCAN_SIZE_X_1;
                break;
            case 2:
                key = KEY_REGISTERD_SCAN_SIZE_X_2;
                break;
            case 3:
                key = KEY_REGISTERD_SCAN_SIZE_X_3;
                break;
            case 4:
                key = KEY_REGISTERD_SCAN_SIZE_X_4;
                break;
            case 5:
                key = KEY_REGISTERD_SCAN_SIZE_X_5;
                break;
            default:
                break;
        }
        return getStringValue(key);
    }

	/**
	 * 「読み取りサイズ登録*：原稿長」情報を取得する。(*は1～5の何れかを示す)<BR>
	 * @return registerdScanSizeY* (String)
	 */
    public String getRegisterdScanSizeY(int num) {
        String key = null;
        switch (num) {
            case 1:
                key = KEY_REGISTERD_SCAN_SIZE_Y_1;
                break;
            case 2:
                key = KEY_REGISTERD_SCAN_SIZE_Y_2;
                break;
            case 3:
                key = KEY_REGISTERD_SCAN_SIZE_Y_3;
                break;
            case 4:
                key = KEY_REGISTERD_SCAN_SIZE_Y_4;
                break;
            case 5:
                key = KEY_REGISTERD_SCAN_SIZE_Y_5;
                break;
            default:
                break;
        }
        return getStringValue(key);
    }

    /**
	 * 「読み取りサイズ登録*：原稿の横方向の長さ」情報を取得する。(*は1～5の何れかを示す)<BR>
	 * @return registerdScanSizeHorizontal* (String)
	 */
    public String getRegisterdScanSizeHorizontal(int num) {
        String key = null;
        switch (num) {
            case 1:
                key = KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_1;
                break;
            case 2:
                key = KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_2;
                break;
            case 3:
                key = KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_3;
                break;
            case 4:
                key = KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_4;
                break;
            case 5:
                key = KEY_REGISTERD_SCAN_SIZE_HORIZONTAL_5;
                break;
            default:
                break;
        }
        return getStringValue(key);
    }

	/**
	 * 「読み取りサイズ登録*：原稿の縦方向の長さ」情報を取得する。(*は1～5の何れかを示す)<BR>
	 * @return registerdScanSizeY* (String)
	 */
    public String getRegisterdScanSizeVertical(int num) {
        String key = null;
        switch (num) {
            case 1:
                key = KEY_REGISTERD_SCAN_SIZE_VERTICAL_1;
                break;
            case 2:
                key = KEY_REGISTERD_SCAN_SIZE_VERTICAL_2;
                break;
            case 3:
                key = KEY_REGISTERD_SCAN_SIZE_VERTICAL_3;
                break;
            case 4:
                key = KEY_REGISTERD_SCAN_SIZE_VERTICAL_4;
                break;
            case 5:
                key = KEY_REGISTERD_SCAN_SIZE_VERTICAL_5;
                break;
            default:
                break;
        }
        return getStringValue(key);
    }


	/**
	 * 送信メールサイズ制限情報を取得する。<BR>
	 * @return limitEmailSize (String)
	 */
    public String getLimitEmailSize() {
        return getStringValue(KEY_LIMIT_EMAIL_SIZE);
    }

	/**
	 * 送信メールサイズ制限のサイズ情報を取得する。<BR>
	 * @return maxEmailSize (String)
	 */
    public String getMaxEmailSize() {
        return getStringValue(KEY_MAX_EMAIL_SIZE);
    }

	/**
	 * メールサイズ制限オーバー時の分割情報を取得する。<BR>
	 * @return emailDividing (String)
	 */
    public String getEmailDividing() {
        return getStringValue(KEY_EMAIL_DIVIDING);
    }

	/**
	 * メールサイズ制限オーバー分割時の最大分割数情報を取得する。<BR>
	 * @return emailDividingNumber (String)
	 */
    public String getEmailDividingNumber() {
        return getStringValue(KEY_EMAIL_DIVIDING_NUMBER);
    }

	/**
	 * 読み取り終了時にジョブ設定をリセットするかどうかの情報を取得する。<BR>
	 * @return resetSecretJobSettings (String)
	 */
    public String getResetSecretJobSettings() {
        return getStringValue(KEY_RESET_SECRET_JOB_SETTINGS);
    }

	/**
	 * メニュープロテクト設定情報を取得する。<BR>
	 * @return menuProtect (String)
	 */
    public String getMenuProtect() {
        return getStringValue(KEY_MENU_PROTECT);
    }

	/**
	 * スキャナオートリセットタイマー設定情報を取得する。<BR>
	 * @return autoResetTimer (String)
	 */
    public String getAutoResetTimer() {
        return getStringValue(KEY_AUTO_RESET_TIMER);
    }

	/**
	 * スキャナAPIを利用するのに認証が必要かどうかの情報を取得する。<BR>
	 * @return authenticationRequired (String)
	 */
    public String getAuthenticationRequired() {
        return getStringValue(KEY_AUTHENTICATION_REQUIRED);
    }

	/**
	 * 宛先をプログラム登録する/しないの情報を取得する。<BR>
	 * @return programSettingForDestinations (String)
	 */
    public String getProgramSettingForDestinations() {
        return getStringValue(KEY_PROGRAM_SETTING_FOR_DESTINATIONS);
    }

	/**
	 * 圧縮設定（白黒２値）情報を取得する。<BR>
	 * @return compressionMonochrome (String)
	 */
    public String getCompressionMonochrome() {
        return getStringValue(KEY_COMPRESSION_MONOCHROME);
    }

	/**
	 * 圧縮設定（ｸﾞﾚｰｽｹｰﾙ/ﾌﾙｶﾗｰ）情報を取得する。<BR>
	 * @return compressionGrayscaleFullcolor (String)
	 */
    public String getCompressionGrayscaleFullcolor() {
        return getStringValue(KEY_COMPRESSION_GRAY_FULLCOLOR);
    }

	/**
	 * SMBプロトコル情報を取得する。<BR>
	 * @return smbProtocol (String)
	 */
    public String getSmbProtocol() {
        return getStringValue(KEY_SMB_PROTOCOL);
    }

	/**
	 * OCR変換モジュール情報を取得する。<BR>
	 * @return ocrModule (String)
	 */
    public String getOcrModule() {
        return getStringValue(KEY_OCR_MODULE);
    }

	/**
	 * 宛先表見出し切り替え情報を取得する。<BR>
	 * @return switchTitle (String)
	 */
    public String getSwitchTitle() {
        return getStringValue(KEY_SWITCH_TITLE);
    }

	/**
	 * 宛先表初期表示選択情報を取得する。<BR>
	 * @return destinationListDisplayPriority (String)
	 */
    public String getDestinationListDisplayPriority() {
        return getStringValue(KEY_DESTINATION_LIST_DISPLAY_PRIORITY);
    }

	/**
	 * WSD/DSM機能の利用情報を取得する。<BR>
	 * @return useWsdOrDsm (String)
	 */
    public String getUseWsdOrDsm() {
        return getStringValue(KEY_USE_WSD_OR_DSM);
    }

	/**
	 * 変倍率設定*情報を取得する。(*は1～12の何れかを示す)<BR>
	 * @return magnificationRatio* (String)
	 */
    public String getMagnificationRatio(int num) {
        String key = null;
        switch (num) {
            case MAGNIFICATION_RATIO_NO_1:
                key = KEY_MAGNIFICATION_RATIO_1;
                break;
            case MAGNIFICATION_RATIO_NO_2:
                key = KEY_MAGNIFICATION_RATIO_2;
                break;
            case MAGNIFICATION_RATIO_NO_3:
                key = KEY_MAGNIFICATION_RATIO_3;
                break;
            case MAGNIFICATION_RATIO_NO_4:
                key = KEY_MAGNIFICATION_RATIO_4;
                break;
            case MAGNIFICATION_RATIO_NO_5:
                key = KEY_MAGNIFICATION_RATIO_5;
                break;
            case MAGNIFICATION_RATIO_NO_6:
                key = KEY_MAGNIFICATION_RATIO_6;
                break;
            case MAGNIFICATION_RATIO_NO_7:
                key = KEY_MAGNIFICATION_RATIO_7;
                break;
            case MAGNIFICATION_RATIO_NO_8:
                key = KEY_MAGNIFICATION_RATIO_8;
                break;
            case MAGNIFICATION_RATIO_NO_9:
                key = KEY_MAGNIFICATION_RATIO_9;
                break;
            case MAGNIFICATION_RATIO_NO_10:
                key = KEY_MAGNIFICATION_RATIO_10;
                break;
            case MAGNIFICATION_RATIO_NO_11:
                key = KEY_MAGNIFICATION_RATIO_11;
                break;
            case MAGNIFICATION_RATIO_NO_12:
                key = KEY_MAGNIFICATION_RATIO_12;
                break;
            default:
                break;
        }
        return getStringValue(key);
    }

	/**
	 * シングルページ番号桁設定情報を取得する。<BR>
	 * @return singlePageFilesDigits (String)
	 */
    public String getSinglePageFilesDigits() {
        return getStringValue(KEY_SINGLE_PAGE_FILES_DIGITS);
    }

	/**
	 * ScanToフォルダのパスワード入力設定情報を取得する。<BR>
	 * @return scanAndSendFolderPasswordInput (String)
	 */
    public String getScanAndSendFolderPasswordInput() {
        return getStringValue(KEY_SCAN_AND_SEND_FOLDER_PASSWORD_INPUT);
    }

	/**
	 * URLリンクから文書を直接ダウンロード情報を取得する。<BR>
	 * @return downloadFileDirectlyFromUrlLink (String)
	 */
    public String getDownloadFileDirectlyFromUrlLink() {
        return getStringValue(KEY_DOWNLOAD_FILE_DIRECTLY_FROM_URL_LINK);
    }

	/**
	 * 文書送信方法情報を取得する。<BR>
	 * @return flieEmailingMethod (String)
	 */
    public String getFlieEmailingMethod() {
        return getStringValue(KEY_FILE_EMAILING_METHOD);
    }

    /**
	 * クリアライトPDF作成時の圧縮方式を取得する。<BR>
	 * @return compressionMethodHighCompressionPdf (String)
	 */
    public String getCompressionMethodHighCompressionPdf() {
        return getStringValue(KEY_COMPRESSION_METHOD_HIGH_COMPRESSION_PDF);
    }

    /**
	 * クリアライト（高圧縮）PDF圧縮率設定を取得する。<BR>
	 * @return compressionLevelHighCompressionPdf (String)
	 */
    public String getCompressionLevelHighCompressionPdf() {
        return getStringValue(KEY_COMPRESSION_LEVEL_HIGH_COMPRESSION_PDF);
    }

    /**
	 * OCRテキスト付きPDF白紙検知レベルを取得する。<BR>
	 * @return blankPageSensitivityLevel (String)
	 */
    public String getBlankPageSensitivityLevel() {
        return getStringValue(KEY_BLANK_PAGE_SENSITIVITY_LEVEL);
    }

    /**
	 * S/MIME メール送信時の証明書チェック方法を取得する。<BR>
	 * @return smimeCheckMode (String)
	 */
    public String getSmimeCheckMode() {
        return getStringValue(KEY_SMIME_CHECK_MODE);
    }
}