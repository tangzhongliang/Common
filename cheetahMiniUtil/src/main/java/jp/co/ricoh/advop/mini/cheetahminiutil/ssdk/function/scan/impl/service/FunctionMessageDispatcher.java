/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.service;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AbstractEventReceiver;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncConnectState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncEventListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.ScanEventReceiver;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.HashScanServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.OccuredErrorLevel;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerMediaStatus;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerRemainingMemory;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerRemainingMemoryLocal;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard.ScannerStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.BasicRestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestHeader;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property.GetAuthPropResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.AuthRestriction;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Capability;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetAuthRestrictionResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetCapabilityResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetConfigurationResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetJobStatusResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetScannerStatusResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.ScanConfiguration;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Scanner;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system.auth.Auth;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system.auth.CreateRestrictionPanelInfoRequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.system.auth.CreateRestrictionPanelInfoResponseBody;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SDKServiceからの非同期イベントを受け取るリスナークラスです。
 * The listener class to receive asynchronous events from SDKService.
 */
public class FunctionMessageDispatcher implements AsyncEventListener {

	/* single instance */
	private static FunctionMessageDispatcher mInstance = null;

	private ServiceListener mServiceListener;
    private final Object mLockServiceListener = new Object();

	/**
	 *  ジョブに非同期を通知するためのリスナー
	 *  The listener to notify asynchronous to the job
	 */
	private final List<AsyncJobEventHandler> mAsyncEvHandlers = new ArrayList<AsyncJobEventHandler>();
    private String mSubscribedId = null;

    /**
     *  内部管理するスキャナクラス
     *  The scanner class managed internally
     */
    private Scanner mScanner = null;

    /**
     * 内部管理する非同期イベント受信クラス
     * The class to receive asynchronous events managed internally
     */
    private AbstractEventReceiver mScanAsyncEventReceiver;
    private Auth mAuth = null;

	/*
	 * クラスロード時にインスタンスを生成
	 * Creates instance at the time of loading class
	 */
	static {
		mInstance = new FunctionMessageDispatcher();
	}

	/*
	 * デフォルトコンストラクタは不可視です。
	 * Default constructor is invisible
	 */
	private FunctionMessageDispatcher(){
	    // Registers an Internal listener to receive asynchronous events from SDKService
        mScanAsyncEventReceiver = new ScanEventReceiver();
        mScanAsyncEventReceiver.addAsyncEventListener(this);
        mAuth = new Auth(new BasicRestContext("https"));

        mScanner = new Scanner();
	}


	/**
	 * インスタンスを返します。
	 * Returns the instance.
	 *
	 * @return シングルトンインスタンス
	 *         Singleton instance
	 */
	public static FunctionMessageDispatcher getInstance(){
		return mInstance;
	}

	/**
	 * サービスリスナーを登録します。
     * 登録の結果、失敗する可能性があります。
     * その場合は、再登録することはせず、リスナー登録失敗としてUI層へ通知します。
     * Registers service listener
     * Registration may fail.
     * If failed, the listener is not registered again; registration failure is notified to the UI layer.
     *
	 * @param listener
     * @return 非同期接続状態の結果
     *         asynchronous connection state result
	 */
	public AsyncConnectState addServiceListener(ServiceListener listener){
       AsyncConnectState asyncConnectState = null;

        synchronized (this.mLockServiceListener) {
            if( this.mServiceListener == null ) {
                asyncConnectState = mScanAsyncEventReceiver.startReceiveAppStateEvent();
                if (asyncConnectState!=null && asyncConnectState.getState() == AsyncConnectState.STATE.CONNECTED) {
                    this.mServiceListener = listener;
                }
            } else {
                asyncConnectState = AsyncConnectState.valueOf(AsyncConnectState.STATE.CONNECTED, AsyncConnectState.ERROR_CODE.NO_ERROR);
            }
        }

        return asyncConnectState;
	}

    /**
     * サービスリスナーを解除します。
     * SDKServiceに問い合わせた結果、失敗する可能性があります。
     * Removes the service listener
     * Inquiry to SDKService may fail.
     *
     * @return 解除の可否
     *         Returns true if unregistration succeeded
     */
    public boolean removeServiceListener() {
        synchronized ( this.mLockServiceListener) {
            this.mServiceListener = null;
            AsyncConnectState async = mScanAsyncEventReceiver.endReceiveAppStateEvent();
            if (async==null) {
                return false;
            }
            return (async.getState()==AsyncConnectState.STATE.CONNECTED);
        }
    }

	/**
	 * ジョブの非同期イベントリスナーを登録します。
	 * Registers the asynchronous event listener of the job
     *
     * @param handler
     * @return subscribedId
     */
	public String addAsyncJobEventListener(AsyncJobEventHandler handler){
		if(handler == null) {
			throw new NullPointerException("handler is null");
		}

        synchronized (mAsyncEvHandlers) {
            if (mSubscribedId == null) {
                this.mSubscribedId = mScanAsyncEventReceiver.startReceiveJobEvent();
            }
            if (mSubscribedId != null) {
                mAsyncEvHandlers.add(handler);
            }
            return this.mSubscribedId;
        }
	}

	/**
	 * ジョブの非同期イベントリスナー除去します。
	 * Removes the asynchronous event listener of the job
	 *
	 * @param handler
	 */
	public void removeAsyncJobEventListener(AsyncJobEventHandler handler){
		if(handler == null){
			throw new NullPointerException("handler is null");
		}
        synchronized (mAsyncEvHandlers) {
		    mAsyncEvHandlers.remove(handler);

            if(mAsyncEvHandlers.size() <= 0 && this.mSubscribedId != null) {
                mScanAsyncEventReceiver.endReceiveJobEvent();
                this.mSubscribedId = null;
            }
        }
	}


    /**
     * スキャンサービスの状態を取得し、属性セットとして値を返します。
     * Obtains the scan service state and returns the value as an attribute set.
     *
     * @return 現在のスキャンサービスの状態属性セット
     *         取得できない場合は空セットが返ります。
     *         The state attribute set of the current scan service.
     *         If the attribute set cannot be obtained, an empty set is returned.
     */
    public ScanServiceAttributeSet getScanStatus() {
        ScanServiceAttributeSet attributes = new HashScanServiceAttributeSet();

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

        Request req = new Request();
        req.setHeader(header);

        Response<GetScannerStatusResponseBody> resp;
        try {
            resp = mScanner.getScannerStatus(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
            	GetScannerStatusResponseBody body = resp.getBody();
            	if (body.getScannerStatus() != null) {
                    attributes.add(ScannerState.fromString(body.getScannerStatus()));
            	}
                if(body.getScannerStatusReasons() != null) {
                   	attributes.add(ScannerStateReasons.convertFrom(body.getScannerStatusReasons()));
                }
                if(body.getRemainingMemory() != null) {
                    attributes.add(new ScannerRemainingMemory(body.getRemainingMemory().intValue()));
                }
                if(body.getRemainingMemoryLocal() != null) {
                    attributes.add(new ScannerRemainingMemoryLocal(body.getRemainingMemoryLocal().intValue()));
                }
                if(body.getMediaStatus() != null) {
                    // SmartSDK V2.00
                    attributes.add(ScannerMediaStatus.getInstance(body.getMediaStatus()));
                }
                if(body.getOccuredErrorLevel() != null) {
                    attributes.add(OccuredErrorLevel.fromString(body.getOccuredErrorLevel()));
                }
            }
        } catch (IOException e) {
            LogC.w(e);
        } catch (InvalidResponseException e) {
            LogC.w(e);
        } catch (Exception e) {
            LogC.w(e);
        }

        return attributes;
    }

    /**
     * 設定可能値一覧をWebAPIから取得します。
     * 取得に失敗した場合は、nullが返ります。
     * Obtains the list of values that can be set from web API.
     * If failing to obtain the list, null is returned.
     *
     * @return
     */
    public Capability getScanCapability() {
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

        Request req = new Request();
        req.setHeader(header);

        Response<GetCapabilityResponseBody> resp;
        try{
            resp = mScanner.getCapability(req);
            if(resp.getStatusCode() == HttpStatus.SC_OK) {
                return resp.getBody().getJobSettingCapability();
            }
        } catch( IOException ex ) {
            LogC.w(ex);
        } catch( InvalidResponseException ex) {
            LogC.w(ex);
        }
        return null;
    }

    /**
     * 非同期イベントの接続状態を取得します
     * Obtains the state of asynchronous connection
     *
     * @return 非同期イベントの接続状態
     *         Asynchronous connection state
     */
    public AsyncConnectState getAsyncConnectState() {
        return mScanAsyncEventReceiver.getAsyncConnectState();
    }

    /**
     * スキャンサービスの初期設定をWebAPIから取得する。</BR>
     * @return 現在のスキャンサービスの初期設定</BR>
     * @throws InvalidResponseException SmartSDK API側でエラーが発生した場合
     */
    public ScanConfiguration getScanConfiguration() throws InvalidResponseException {
        ScanConfiguration retConf = new ScanConfiguration();

        //スキャンの初期設定を取得しに行く
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

        Request req = new Request();
        req.setHeader(header);

        Response<GetConfigurationResponseBody> resp;
        try {
            resp = mScanner.getConfiguration(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
                //戻りが200(OK)のときだけ、Configurationを作成する
                // TODO 戻りが200以外でもConfigurationのインスタンス(空)は返している
                GetConfigurationResponseBody body = resp.getBody();
                String nonDsmDestination = body.getNonDsmDestination();
                if(nonDsmDestination != null) {
                    retConf.setNonDsmDestination(nonDsmDestination);
                }
                for(int num=1; num<=ScanConfiguration.MAX_REGISTERD_SCAN_SIZE_NUM; num++) {
                    if(body.getRegisterdScanSizeName(num) != null) {
                        retConf.setRegisterdScanSizeName(num, body.getRegisterdScanSizeName(num));
                    }
                    /* 搬送方向を考慮した,ユーザー視点の縦横を設定する */
                    if ( body.getRegisterdScanSizeHorizontal(num) != null &&
                            body.getRegisterdScanSizeVertical(num) != null ) {
                        /* ユーザーからみたときの横方向,縦方向が取得できた場合は、その値をそのまま使用する.(API Ver2.10以降サポート) */
                        retConf.setRegisterdScanSizeX(num, body.getRegisterdScanSizeHorizontal(num));
                        retConf.setRegisterdScanSizeY(num, body.getRegisterdScanSizeVertical(num));
                    }
                }
                String limitEmailSize = body.getLimitEmailSize();
                if(limitEmailSize != null) {
                    retConf.setLimitEmailSize(limitEmailSize);
                }
                if(body.getMaxEmailSize() != null) {
                    retConf.setMaxEmailSize(body.getMaxEmailSize());
                }
                if(body.getEmailDividing() != null) {
                    retConf.setEmailDividing(body.getEmailDividing());
                }
                if(body.getEmailDividingNumber() != null) {
                    retConf.setEmailDividingNumber(body.getEmailDividingNumber());
                }
                String secretJobSettings = body.getResetSecretJobSettings();
                if(secretJobSettings != null) {
                    retConf.setResetSecretJobSettings(secretJobSettings);
                }
                if(body.getMenuProtect() != null) {
                    retConf.setMenuProtect(body.getMenuProtect());
                }
                if(body.getAutoResetTimer() != null) {
                    retConf.setAutoResetTimer(body.getAutoResetTimer());
                }
                String authenticationRequired = body.getAuthenticationRequired();
                if(authenticationRequired != null) {
                    retConf.setAuthenticationRequired(authenticationRequired);
                }
                if(body.getProgramSettingForDestinations() != null) {
                    retConf.setProgramSettingForDestinations(body.getProgramSettingForDestinations());
                }
                if(body.getCompressionMonochrome() != null) {
                    retConf.setCompressionMonochrome(body.getCompressionMonochrome());
                }
                if(body.getCompressionGrayscaleFullcolor() != null) {
                    retConf.setCompressionGrayscaleFullcolor(body.getCompressionGrayscaleFullcolor());
                }
                retConf.setSmbProtocol(body.getSmbProtocol());
                retConf.setOcrModule(body.getOcrModule());
                retConf.setSwitchTitle(body.getSwitchTitle());
                retConf.setDestinationListDisplayPriority(body.getDestinationListDisplayPriority());
                retConf.setUseWsdOrDsm(body.getUseWsdOrDsm());
                ArrayList<String> list = new ArrayList<String>();
                for(int num=1; num<=ScanConfiguration.MAX_REGISTERD_MAGNIFICATION_RATIO_NUM; num++) {
                    String ratio = body.getMagnificationRatio(num);
                    if(null != ratio){
                        list.add(ratio);
                    }
                }
                if(0 != list.size()){
                    retConf.setMagnificationRatiolist(list);
                }
                retConf.setSinglePageFilesDigits(body.getSinglePageFilesDigits());
                retConf.setScanAndSendFolderPasswordInput(body.getScanAndSendFolderPasswordInput());
                retConf.setDownloadFileDirectlyFromUrlLink(body.getDownloadFileDirectlyFromUrlLink());
                retConf.setFlieEmailingMethod(body.getFlieEmailingMethod());
                retConf.setCompressionMethodHighCompressionPdf(body.getCompressionMethodHighCompressionPdf());
                retConf.setCompressionLevelHighCompressionPdf(body.getCompressionLevelHighCompressionPdf());
                retConf.setBlankPageSensitivityLevel(body.getBlankPageSensitivityLevel());
                // S/MIME メール送信時の証明書チェック方法
                if ( body.getSmimeCheckMode() != null ) {
                    retConf.setSmimeCheckMode(body.getSmimeCheckMode());
                }

            }
        } catch (IOException e) {
            LogC.w(e);
            
        } catch (InvalidResponseException e) {
            LogC.w(e);
            throw e;
        } catch (Exception e) {
            LogC.w(e);
        }
        return retConf;
    }

    /**
     * スキャナ機能に制限されている認証情報をWebAPIから取得する。</BR>
     * Query Parameter に userCode が指定可能。<BR>
     * @param userCode ユーザーコード認証に対するユーザーコード。リクエストヘッダの指定が不要な場合は、nullとすること。
     * @return AuthRestriction 認証情報
     * @throws InvalidResponseException
     * @throws CommandContinueException
     */
    public AuthRestriction getScanAuthRestriction(String userCode) throws InvalidResponseException {
        AuthRestriction retAuth = new AuthRestriction();

        //スキャンの認証情報を取得しに行く
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());
        /* queryへの指定から拡張ヘッダへの指定に変更 */
        if(userCode != null) {
            header.put(RequestHeader.KEY_X_AUTHORIZATION_USERCODE, userCode);
        }

        Request req = new Request();
        req.setHeader(header);
        /* queryへの指定から拡張ヘッダへの指定に変更 */
      RequestQuery query = new RequestQuery();
        if(userCode != null) {
            query.put("userCode", userCode);
            req.setQuery(query);
        }

        Response<GetAuthRestrictionResponseBody> resp;
        try {
            resp = mScanner.getAuthRestriction(req);
            /* ########################### TEST ####################################### */
            /* 現状、SSDK APIの /scanner/authRestriction が実装されていないため SC_NOT_FOUND でも必ずAuthRestrictionに情報をセットするようにする */
//            if( resp.getStatusCode() == HttpStatus.SC_OK || (resp.getStatusCode() == HttpStatus.SC_NOT_FOUND) ){
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
            /* ########################### TEST ####################################### */
                //戻りが200(OK)のときだけ、AuthRestrictionを作成する
                GetAuthRestrictionResponseBody body = resp.getBody();
                Boolean loginPermission = body.getLoginPermission();
                if(loginPermission != null) {
                    LogC.d(" authRestriction permission : " + loginPermission);
                    retAuth.setLoginPermission(loginPermission);
                }
                if(body.getAuthenticationDevice() != null) {
                    retAuth.setAuthenticationDevice(body.getAuthenticationDevice());
                }
                if(body.getLoginErrorReason() != null) {
                    retAuth.setLoginErrorReason(body.getLoginErrorReason());
                }
                List<String> restrictionPanelId = body.getRestrictionPanelId();
                if(restrictionPanelId != null) {
                    retAuth.setRestrictionPanelId(restrictionPanelId);
                }
            }
        } catch (IOException e) {
            LogC.w(e);
        } catch( InvalidResponseException ex) {
            LogC.w(ex);
            int statusCode = ex.getStatusCode();
            switch (statusCode) {
            case HttpStatus.SC_BAD_REQUEST:     //400:
            case HttpStatus.SC_UNAUTHORIZED:    //401:
            case HttpStatus.SC_FORBIDDEN:       //403:
                throw ex;   //エラー
            default:
                throw ex;   //エラー
//                throw new SmartAppCommand.CommandContinueException(100, statusCode);
                // TODO retry
            }
        } catch (Exception e) {
            LogC.w(e);
        }
        return retAuth;
    }
    
    /**
     * スキャナ機能の制限画面情報をWebAPIから生成する。</BR>
     * @param panelId 制限画面ID。getScanAuthRestriction()で取得した認証画面IDを指定する。
     * @return 制限画面情報
     */
    public String createScanRestrictionPanelInfo(String panelId) throws InvalidResponseException {
        String ret = null;

        //スキャンの制限画面情報を生成する
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

        CreateRestrictionPanelInfoRequestBody reqBody = new CreateRestrictionPanelInfoRequestBody();
        reqBody.setApplicationType("scanner");
        reqBody.setRestrictionPanelId(panelId);

        Request req = new Request();
        req.setBody(reqBody);
        req.setHeader(header);
        Response<CreateRestrictionPanelInfoResponseBody> resp;
        try {
            resp = mAuth.createRestrictionPanelInfo(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
                //戻りが200(OK)のときだけ、制限画面情報を作成する
                CreateRestrictionPanelInfoResponseBody resBody = resp.getBody();
                if(resBody != null) {
                    ret = resBody.getRestrictionPanelInfo();
                    LogC.i("ScanRestrictionPanelInfo PanelInfo : " + ret);
                }
            }
        } catch (IOException e) {
            LogC.w(e);
        }
        return ret;
    }
    
    public boolean isOptionalCounterExist() throws InvalidResponseException {
        boolean ret = false;

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

        Request req = new Request();
        req.setHeader(header);
        Response<GetAuthPropResponseBody> resp;
        try {
            resp = mAuth.getAuthProp(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
                GetAuthPropResponseBody resBody = resp.getBody();
                if(resBody != null) {
                    ret = resBody.isOptionalCounterExist();
                    LogC.i("isOptionalCounterExist : " + ret);
                }
            }
        } catch (IOException e) {
            LogC.w(e);
        }
        return ret;
    }

    
    /***************************************************************************
	 *  SDKServiceからの通知
	 *  Notification from SDKService
	 **************************************************************************/
    /**
     * スキャナ状態イベントを受信します。
     * 状態の仕様ならびに、JSONフォーマット仕様はSmartSDK仕様に準拠します。
     * Receives scanner state event.
     * States and JSON format comply with SmartSDK specifications.
     *
     * @param eventData 受信したスキャナ状態を示すJSON文字列
     *                  The JSON string to indicate received scanner event
     */
    @Override
    public void onReceiveAppEvent(String eventData) {
        LogC.d(FunctionMessageDispatcher.class.getSimpleName(), "onReceiveStatusEvent[" + eventData + "]");
        if( eventData == null ) return;
        Map<String, Object> decoded = GenericJsonDecoder.decodeToMap(eventData);
        GetScannerStatusResponseBody body = new GetScannerStatusResponseBody((Map<String, Object>) decoded.get("data"));

        ScanServiceAttributeSet notifySet = new HashScanServiceAttributeSet();
        if(body.getScannerStatus() != null) {
            notifySet.add(ScannerState.fromString(body.getScannerStatus()));
        }
        if(body.getScannerStatusReasons() != null) {
            notifySet.add(ScannerStateReasons.convertFrom(body.getScannerStatusReasons()));
        }
        if(body.getRemainingMemory() != null) {
            notifySet.add(new ScannerRemainingMemory(body.getRemainingMemory().intValue()));
        }
        if(body.getRemainingMemoryLocal() != null) {
            notifySet.add(new ScannerRemainingMemoryLocal(body.getRemainingMemoryLocal().intValue()));
        }
        if(body.getMediaStatus() != null) {
            // SmartSDK V2.00
            notifySet.add(ScannerMediaStatus.getInstance(body.getMediaStatus()));
        }
        if(body.getOccuredErrorLevel() != null) {
            notifySet.add(OccuredErrorLevel.fromString(body.getOccuredErrorLevel()));
        }

        synchronized (this.mLockServiceListener) {
            if(this.mServiceListener != null) {
                this.mServiceListener.onChangeScanServiceAttributes(notifySet);
            }
        }
    }

    /**
     * スキャンジョブ状態イベントを受信します。
     * 状態の仕様ならびに、JSONフォーマット仕様はSmartSDK仕様に準拠します。
     * Receives scan job state event
     * States and JSON format comply with SmartSDK specifications.
     *
     * @param eventData 受信したスキャンジョブ状態を示すJSON文字列
     *                  The JSON string to indicate received scan job event
     */
    @Override
    public void onReceiveJobEvent(String eventData) {
        if(eventData == null) return;

        Map<String, Object> decoded = GenericJsonDecoder.decodeToMap(eventData);
        GetJobStatusResponseBody body = new GetJobStatusResponseBody((Map<String, Object>) decoded.get("data"));

        String jobId = body.getJobId();

        AsyncJobEventHandler[] handlers;
        synchronized (mAsyncEvHandlers) {
            handlers = mAsyncEvHandlers.toArray(new AsyncJobEventHandler[mAsyncEvHandlers.size()]);
        }

        for(AsyncJobEventHandler handler : handlers) {
            if( handler.getJobId() == null || handler.getJobId().equals(jobId)) {
                handler.onReceiveJobEvent(body);
            }
        }
    }

    @Override
    public void onReceiveFaxReceiveEvent(String eventData) {
    }
}
