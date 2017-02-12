/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import java.util.ArrayList;

/**
 * スキャナ初期設定値を表す。<BR>
 * SmartSDKで取得したスキャナ初期設定値を保持し、各要素にアクセスするためのアクセッサ（getter/setter）を提供する。<BR>
 */
public class ScanConfiguration {

	/** 登録読取りサイズの最大登録数 */
    public static final int MAX_REGISTERD_SCAN_SIZE_NUM = 5;
	/** 変倍率設定の最大登録数 */
    public static final int MAX_REGISTERD_MAGNIFICATION_RATIO_NUM = 12;
    /** ScanToFolder入力設定：パスワードを毎回入力する */
    public static final String EACH_TIME = "each_time";
    /** ScanToFolder入力設定：設定されているパスワードを使用する */
    public static final String USE_REGISTERED_PASSWORD = "use_registered_password";
    /** Value文字列定義:"on" */
    private static final String VALUE_ON = "on";
    /** Value文字列定義:"off" */
    private static final String VALUE_OFF = "off";
    /** smimCheckMode文字列定義:"performance_mode" */
    public static final String PERFORMANCE_MODE = "performance_mode";
    /** smimCheckMode文字列定義:"off" */
    public static final String SECURE_MODE = "secure_mode";

    /*
     * Response Body の Value が"on"/"off"の場合はboolean(true/false)に置き換える
     * Response Body の Value が"数値"の場合はintに置き換える
     * */
    /** スキャナ初期設定値:DSM以外の宛先表の使用設定 */
    private boolean mNonDsmDestination = true;
    /** スキャナ初期設定値:登録読み取りサイズの名前 */
    private String[] mRegisterdScanSizeNameArray = new String[MAX_REGISTERD_SCAN_SIZE_NUM];
    /** スキャナ初期設定値:登録読み取りサイズの原稿幅 */
    private int[]   mRegisterdScanSizeXArray = new int[MAX_REGISTERD_SCAN_SIZE_NUM];
    /** スキャナ初期設定値:登録読み取りサイズの原稿長 */
    private int[]   mRegisterdScanSizeYArray = new int[MAX_REGISTERD_SCAN_SIZE_NUM];
    /** スキャナ初期設定値:送信メールサイズ制限 */
    private boolean mLimitEmailSize;
    /** スキャナ初期設定値:送信メールサイズ制限のサイズ */
    private int     mMaxEmailSize;
    /** スキャナ初期設定値:メールサイズ制限オーバー時の分割設定 */
    private String  mEmailDividing;
    /** スキャナ初期設定値:メールサイズ制限オーバー時の最大分割数 */
    private int     mEmailDividingNumber;
    /** スキャナ初期設定値:読み取り終了時のジョブ設定リセット */
    private boolean mResetSecretJobSettings;
    /** スキャナ初期設定値:メニュープロテクト設定 */
    private String  mMenuProtect;
    /** スキャナ初期設定値:スキャナオートリセットタイマー設定 */
    private int     mAutoResetTimer;
    /** スキャナ初期設定値:スキャナAPI利用の認証要否 */
    private boolean mAuthenticationRequired;
    /** スキャナ初期設定値:宛先情報のプログラム登録設定 */
    private String mProgramSettingForDestinations;
    /** スキャナ初期設定値:圧縮設定（白黒２値） */
    private String mCompressionMonochrome;
    /** スキャナ初期設定値:圧縮設定（ｸﾞﾚｰｽｹｰﾙ/ﾌﾙｶﾗｰ） */
    private String mCompressionGrayscaleFullcolor;
    /** スキャナ初期設定値:SMBプロトコル */
    private String  mSmbProtocol;
    /** スキャナ初期設定値:OCR変換モジュール */
    private String  mOcrModule;
    /** スキャナ初期設定値:宛先表見出し切り替え */
    private String  mSwitchTitle;
    /** スキャナ初期設定値:宛先表初期表示選択 */
    private String  mDestinationListDisplayPriority;
    /** スキャナ初期設定値:WSD/DSM機能の利用 */
    private String  mUseWsdOrDsm;
    /** スキャナ初期設定値:変倍率設定 */
    private ArrayList<String> mMagnificationRatioList = null;
    /** スキャナ初期設定値:シングルページ番号桁設定 */
    private String  mSinglePageFilesDigits;
    /** スキャナ初期設定値:ScanToフォルダのパスワード入力設定 */
    private String  mScanAndSendFolderPasswordInput;
    /** スキャナ初期設定値:URLリンクから文書を直接ダウンロード */
    private String  mDownloadFileDirectlyFromUrlLink;
    /** スキャナ初期設定値:文書送信方法 */
    private String  mFlieEmailingMethod;
    /** クリアライトPDF作成時の圧縮方式 */
    private String mCompressionMethodHighCompressionPdf;
    /** クリアライト（高圧縮）PDF圧縮率設定 */
    private String mCompressionLevelHighCompressionPdf;
    /** OCRテキスト付きPDF白紙検知レベル */
    private String mBlankPageSensitivityLevel;
    /** S/MIME メール送信時の証明書チェック方法 */
    private String mSmimeCheckMode;

    /**
     * DSM以外の宛先表の使用有無を設定する。<BE>
     * @param nonDsmDest 宛先表の使用有無
     */
    public void setNonDsmDestination(String nonDsmDest) {
        if(nonDsmDest.equals(VALUE_ON)) {
            mNonDsmDestination = true;
        }
        else {
            mNonDsmDestination = false;
        }
    }

    /**
     * 登録読み取りサイズの名前を設定する。<BR>
     * @param num 登録読み取りサイズの登録番号
     * @param sizeName 登録読み取りサイズの名前
     */
    public void setRegisterdScanSizeName(int num, String sizeName) {
        for(int i=0; i<MAX_REGISTERD_SCAN_SIZE_NUM; i++) {
            if(i == num - 1) {
                mRegisterdScanSizeNameArray[i] = sizeName;
                break;
            }
        }
    }

    /**
     * 登録読み取りサイズの原稿幅を設定する。<BR>
     * @param num 登録読み取りサイズの登録番号
     * @param width 登録読み取りサイズの原稿幅
     */
    public void setRegisterdScanSizeX(int num, String width) {
        for(int i=0; i<MAX_REGISTERD_SCAN_SIZE_NUM; i++) {
            if(i == num - 1) {
                mRegisterdScanSizeXArray[i] = Integer.valueOf(width);
                break;
            }
        }
    }

    /**
     * 登録読み取りサイズの原稿長を設定する。<BR>
     * @param num 登録読み取りサイズの登録番号
     * @param height 登録読み取りサイズの原稿長
     */
    public void setRegisterdScanSizeY(int num, String height) {
        for(int i=0; i<MAX_REGISTERD_SCAN_SIZE_NUM; i++) {
            if(i == num - 1) {
                mRegisterdScanSizeYArray[i] = Integer.valueOf(height);
                break;
            }
        }
    }

    /**
     * 送信メールサイズ制限要否を設定する。<BR>
     * @param eMailSize 送信メールサイズ制限要否
     */
    public void setLimitEmailSize(String eMailSize) {
        if(eMailSize.equals(VALUE_ON)) {
            mLimitEmailSize = true;
        }
        else {
            mLimitEmailSize = false;
        }
    }

    /**
     * 送信メールサイズ制限のサイズを設定する。<BR>
     * @param eMailSize 送信メールサイズ制限のサイズ
     */
    public void setMaxEmailSize(String eMailSize) {
        mMaxEmailSize = Integer.valueOf(eMailSize);
    }

    /**
     * メールサイズ制限オーバー時の分割要否を設定する。<BR>
     * @param eMailDivid メールサイズ制限オーバー時の分割要否
     */
    public void setEmailDividing(String eMailDivid) {
        mEmailDividing = eMailDivid;
    }

    /**
     * メールサイズ制限オーバー時の最大分割数を設定する。<BR>
     * @param num 最大分割数
     */
    public void setEmailDividingNumber(String num) {
        mEmailDividingNumber = Integer.valueOf(num);
    }

    /**
     * 読み取り終了時のジョブ設定リセット要否を設定する。<BR>
     * @param resetSetting ジョブ設定リセット要否
     */
    public void setResetSecretJobSettings(String resetSetting) {
        if(resetSetting.equals(VALUE_ON)) {
            mResetSecretJobSettings = true;
        }
        else {
            mResetSecretJobSettings = false;
        }
    }

    /**
     * メニュープロテクト設定を設定する。<BR>
     * @param protect メニュープロテクト設定
     */
    public void setMenuProtect(String protect) {
        mMenuProtect = protect;
    }

    /**
     * スキャナオートリセットタイマー設定を設定する。<BR>
     * @param timer スキャナオートリセットタイマー設定
     */
    public void setAutoResetTimer(String timer) {
        mAutoResetTimer = Integer.valueOf(timer);
    }

    /**
     * スキャナAPI利用の認証要否を設定する。<BR>
     * @param authRequired スキャナAPI利用の認証要否
     */
    public void setAuthenticationRequired(String authRequired) {
        if(authRequired.equals(VALUE_ON)) {
            mAuthenticationRequired = true;
        }
        else {
            mAuthenticationRequired = false;
        }
    }

    /**
     * 宛先情報のプログラム登録設定を設定する。<BR>
     * @param setting 宛先情報のプログラム登録設定
     */
    public void setProgramSettingForDestinations(String setting) {
        mProgramSettingForDestinations = setting;
    }

    /**
     * 圧縮設定(白黒2値)を設定する。<BR>
     * @param setting 圧縮設定
     */
    public void setCompressionMonochrome(String setting) {
        mCompressionMonochrome = setting;
    }

    /**
     * 圧縮設定(グレースケール/フルカラー)を設定する。<BR>
     * @param setting 圧縮設定
     */
    public void setCompressionGrayscaleFullcolor(String setting) {
        mCompressionGrayscaleFullcolor = setting;
    }

    /**
     * SMBプロトコルを設定する。<BR>
     * @param setting SMBプロトコル
     */
    public void setSmbProtocol(String setting) {
    	mSmbProtocol = setting;
    }

    /**
     * OCR変換モジュールを設定する。<BR>
     * @param setting OCR変換モジュール
     */
    public void setOcrModule(String setting) {
    	mOcrModule = setting;
    }

    /**
     * 宛先表見出し切り替えを設定する。<BR>
     * @param setting 宛先表見出し切り替え
     */
    public void setSwitchTitle(String setting) {
    	mSwitchTitle = setting;
    }

    /**
     * 宛先表初期表示選択を設定する。<BR>
     * @param setting 宛先表初期表示選択
     */
    public void setDestinationListDisplayPriority(String setting) {
    	mDestinationListDisplayPriority = setting;
    }

    /**
     * WSD/DSM機能の利用を設定する。<BR>
     * @param setting WSD/DSM機能の利用
     */
    public void setUseWsdOrDsm(String setting) {
    	mUseWsdOrDsm = setting;
    }

    /**
     * 変倍率設定を設定する。<BR>
     * @param num 変倍率設定の登録番号
     * @param setting 変倍率設定値
     */
    public void setMagnificationRatiolist(ArrayList<String> setting){
    	mMagnificationRatioList = setting;
    }

    /**
     * シングルページ番号桁設定を設定する。<BR>
     * @param setting シングルページ番号桁設定
     */
    public void setSinglePageFilesDigits(String setting) {
    	mSinglePageFilesDigits = setting;
    }

    /**
     * ScanToフォルダのパスワード入力設定を設定する。<BR>
     * @param setting ScanToフォルダのパスワード入力設定
     */
    public void setScanAndSendFolderPasswordInput(String setting) {
    	mScanAndSendFolderPasswordInput = setting;
    }

    /**
     * URLリンクから文書を直接ダウンロード設定を設定する。<BR>
     * @param setting URLリンクから文書を直接ダウンロード設定
     */
    public void setDownloadFileDirectlyFromUrlLink(String setting) {
    	mDownloadFileDirectlyFromUrlLink = setting;
    }

    /**
     * 文書送信方法を設定する。<BR>
     * @param setting 文書送信方法
     */
    public void setFlieEmailingMethod(String setting) {
    	mFlieEmailingMethod = setting;
    }

    /**
     * クリアライトPDF作成時の圧縮方式を設定する。<BR>
     * @param setting クリアライトPDF作成時の圧縮方式
     */
    public void setCompressionMethodHighCompressionPdf(String setting) {
    	mCompressionMethodHighCompressionPdf = setting;
    }

    /**
     * クリアライト（高圧縮）PDF圧縮率設定を設定する。<BR>
     * @param setting クリアライト（高圧縮）PDF圧縮率設定
     */
    public void setCompressionLevelHighCompressionPdf(String setting) {
    	mCompressionLevelHighCompressionPdf = setting;
    }

    /**
     * OCRテキスト付きPDF白紙検知レベルを設定する。<BR>
     * @param setting OCRテキスト付きPDF白紙検知レベル
     */
    public void setBlankPageSensitivityLevel(String setting) {
    	mBlankPageSensitivityLevel = setting;
    }

    /**
     * S/MIME メール送信時の証明書チェック方法を設定する。<BR>
     * @param setting S/MIME メール送信時の証明書チェック方法
     */
    public void setSmimeCheckMode(String setting) {
    	mSmimeCheckMode = setting;
    }

    /**
     * DSM以外の宛先表の使用有無を取得する。<BR>
     * @return mNonDsmDestination DSM以外の宛先表の使用有無
     */
    public boolean getNonDsmDestination() {
        return mNonDsmDestination;
    }

    /**
     * 登録読み取りサイズの名前を取得する。<BR>
     * @param num 登録読み取りサイズの登録番号
     * @return 登録読み取りサイズの名前
     */
    public String getRegisterdScanSizeName(int num) {
        String ret = null;
        for(int i=0; i<MAX_REGISTERD_SCAN_SIZE_NUM; i++) {
            if(i == num - 1) {
                ret = mRegisterdScanSizeNameArray[i];
                break;
            }
        }
        return ret;
    }

    /**
     * 登録読み取りサイズの原稿幅を取得する。<BR>
     * @param num 登録読み取りサイズの登録番号
     * @return 登録読み取りサイズの原稿幅
     */
    public int getRegisterdScanSizeX(int num) {
        int ret = 0;
        for(int i=0; i<MAX_REGISTERD_SCAN_SIZE_NUM; i++) {
            if(i == num - 1) {
                ret = mRegisterdScanSizeXArray[i];
                break;
            }
        }
        return ret;
    }

    /**
     * 登録読み取りサイズの原稿長を取得する。<BR>
     * @param num 登録読み取りサイズの登録番号
     * @return 登録読み取りサイズの原稿長
     */
    public int getRegisterdScanSizeY(int num) {
        int ret = 0;
        for(int i=0; i<MAX_REGISTERD_SCAN_SIZE_NUM; i++) {
            if(i == num - 1) {
                ret = mRegisterdScanSizeYArray[i];
                break;
            }
        }
        return ret;
    }

    /**
     * 送信メールサイズ制限要否を取得する。<BR>
     * @return 送信メールサイズ制限要否
     */
    public boolean getLimitEmailSize() {
        return mLimitEmailSize;
    }

    /**
     * 送信メールサイズ制限のサイズを取得する。<BR>
     * @return 送信メールサイズ制限のサイズ
     */
    public int getMaxEmailSize() {
        return mMaxEmailSize;
    }

    /**
     * メールサイズ制限オーバー時の分割要否を取得する。<BR>
     * @return メールサイズ制限オーバー時の分割要否
     */
    public String getEmailDividing() {
        return mEmailDividing;
    }

    /**
     * メールサイズ制限オーバー時の最大分割数を取得する。<BR>
     * @return メールサイズ制限オーバー時の最大分割数
     */
    public int getEmailDividingNumber() {
        return mEmailDividingNumber;
    }

    /**
     * 読み取り終了時のジョブ設定リセット要否を取得する。<BR>
     * @return 読み取り終了時のジョブ設定リセット要否
     */
    public boolean getResetSecretJobSettings() {
        return mResetSecretJobSettings;
    }

    /**
     * メニュープロテクト設定を取得する。<BR>
     * @return メニュープロテクト設定
     */
    public String getMenuProtect() {
        return mMenuProtect;
    }

    /**
     * スキャナオートリセットタイマー設定を取得する。<BR>
     * @return スキャナオートリセットタイマー設定
     */
    public int getAutoResetTimer() {
        return mAutoResetTimer;
    }

    /**
     * スキャナAPI利用の認証要否を取得する。<BR>
     * @return スキャナAPI利用の認証要否
     */
    public boolean getAuthenticationRequired() {
        return mAuthenticationRequired;
    }

    /**
     * 宛先情報のプログラム登録設定を取得する。<BR>
     * @return 宛先情報のプログラム登録設定
     */
    public String getProgramSettingForDestinations() {
        return mProgramSettingForDestinations;
    }

    /**
     * 圧縮設定(白黒2値)を取得する。<BR>
     * @return 圧縮設定値(白黒2値)
     */
    public String getCompressionMonochrome() {
        return mCompressionMonochrome;
    }

    /**
     * 圧縮設定(白黒2値)を取得する。<BR>
     * @return 圧縮設定値(グレースケール/フルカラー)
     */
    public String getCompressionGrayscaleFullcolor() {
        return mCompressionGrayscaleFullcolor;
    }

    /**
     * SMBプロトコルを取得する。<BR>
     * @return SMBプロトコル
     */
    public String getSmbProtocol() {
        return mSmbProtocol;
    }

    /**
     * OCR変換モジュールを取得する。<BR>
     * @return OCR変換モジュール
     */
    public String getOcrModule() {
        return mOcrModule;
    }

    /**
     * 宛先表見出し切り替えを取得する。<BR>
     * @return 宛先表見出し切り替え
     */
    public String getSwitchTitle() {
        return mSwitchTitle;
    }

    /**
     * 宛先表初期表示選択を取得する。<BR>
     * @return 宛先表初期表示選択
     */
    public String getDestinationListDisplayPriority() {
        return mDestinationListDisplayPriority;
    }

    /**
     * WSD/DSM機能の利用を取得する。<BR>
     * @return WSD/DSM機能の利用
     */
    public String getUseWsdOrDsm() {
        return mUseWsdOrDsm;
    }

    /**
     * 変倍率設定を取得する。<BR>
     * @param num 変倍率設定の登録番号
     * @return 変倍率設定値
     */
    public ArrayList<String> getMagnificationRatioList(){
    	return mMagnificationRatioList;
    }

    /**
     * シングルページ番号桁設定を取得する。<BR>
     * @return シングルページ番号桁設定
     */
    public String getSinglePageFilesDigits() {
        return mSinglePageFilesDigits;
    }

    /**
     * ScanToフォルダのパスワード入力設定を取得する。<BR>
     * @return ScanToフォルダのパスワード入力設定
     */
    public String getScanAndSendFolderPasswordInput() {
        return mScanAndSendFolderPasswordInput;
    }

    /**
     * URLリンクから文書を直接ダウンロードを取得する。<BR>
     * @return URLリンクから文書を直接ダウンロード
     */
    public String getDownloadFileDirectlyFromUrlLink() {
        return mDownloadFileDirectlyFromUrlLink;
    }

    /**
     * 文書送信方法を取得する。<BR>
     * @return 文書送信方法
     */
    public String getFlieEmailingMethod() {
        return mFlieEmailingMethod;
    }

    /**
     * クリアライトPDF作成時の圧縮方式を取得する<BR>
     * @return クリアライトPDF作成時の圧縮方式
     */
    public String getCompressionMethodHighCompressionPdf() {
        return mCompressionMethodHighCompressionPdf;
    }

    /**
     * クリアライト（高圧縮）PDF圧縮率設定を取得する。<BR>
     * @return クリアライト（高圧縮）PDF圧縮率設定
     */
    public String getCompressionLevelHighCompressionPdf() {
        return mCompressionLevelHighCompressionPdf;
    }

    /**
     * OCRテキスト付きPDF白紙検知レベルを取得する。<BR>
     * @return OCRテキスト付きPDF白紙検知レベル
     */
    public String getBlankPageSensitivityLevel() {
        return mBlankPageSensitivityLevel;
    }

    /**
     * S/MIME メール送信時の証明書チェック方法を取得する。<BR>
     * @return S/MIME メール送信時の証明書チェック方法
     */
    public String getSmimeCheckMode() {
        return mSmimeCheckMode;
    }

}
