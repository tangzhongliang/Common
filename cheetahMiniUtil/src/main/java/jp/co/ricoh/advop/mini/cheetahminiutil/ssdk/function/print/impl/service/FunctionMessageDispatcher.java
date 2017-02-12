/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AbstractEventReceiver;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncConnectState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncEventListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.PrinterEventReceiver;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintFile;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintUserCode;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.HashPrintJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.HashPrintServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintColor;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobPrintingInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobUserName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrinterState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrinterStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.UserCodeRestrictedFunc;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.GenericJsonDecoder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestHeader;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.AuthRestriction;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Capability;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetCapabilityResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetConfigurationResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetJobListResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetJobStatusResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetPrinterAuthRestrictionResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetPrinterStatusResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetSupportedPDLResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.JobInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.PrintConfiguration;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Printer;

/**
 * SDKServiceからの非同期イベントを受け取るリスナークラスです。
 * The listener class to receive asynchronous events from SDKService.
 */
public class FunctionMessageDispatcher implements AsyncEventListener{

    private static FunctionMessageDispatcher mInstance;

    private ServiceListener mServiceListener;
    private final Object mServiceListenerLock = new Object();

    private final List<AsyncJobEventHandler> mAsyncEvHandlers = new ArrayList<AsyncJobEventHandler>();
    private String mSubscribedId;

    private Printer mPrinter;

    private AbstractEventReceiver mPrintAsyncEventReceiver;

    private String mProductId;

    static {
        mInstance = new FunctionMessageDispatcher();
    }

    private FunctionMessageDispatcher() {
        mPrintAsyncEventReceiver = new PrinterEventReceiver();
        mPrintAsyncEventReceiver.addAsyncEventListener(this);

        mPrinter = new Printer();
        mProductId = SmartSDKApplication.getProductId();
    }

    /**
     * インスタンスを返します。
     * Returns the instance.
     *
     * @return シングルトンインスタンス
     *         Singleton instance
     */
    public static FunctionMessageDispatcher getInstance() {
        return mInstance;
    }

    /**
     * サービスリスナーを登録します。
     * Registers service listener
     *
     * @param listener
     * @return 非同期接続状態の結果
     *         asynchronous connection state result
     */
    public AsyncConnectState addServiceListener(ServiceListener listener) {
        AsyncConnectState asyncConnectState = null;

        synchronized (this.mServiceListenerLock) {
            if( this.mServiceListener == null ) {
                asyncConnectState = mPrintAsyncEventReceiver.startReceiveAppStateEvent();
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
     * Removes the service listener.
     *
     * @return 解除の可否
     *         Returns true if unregistration succeeded
     */
    public boolean removeServiceListener() {
        synchronized (this.mServiceListenerLock) {
            this.mServiceListener = null;
            AsyncConnectState async = mPrintAsyncEventReceiver.endReceiveAppStateEvent();
            if (async==null) {
                return false;
            }
            return (async.getState()==AsyncConnectState.STATE.CONNECTED);
        }
    }

    /**
     * プリントサービスの状態を取得し、属性セットとして値を返します。
     * Obtains the print service state and returns the value as an attribute set.
     *
     * @return 現在のプリントサービスの状態属性セット
     *         取得できない場合は空セットが返ります。
     *         The state attribute set of the current print service.
     *         If the attribute set cannot be obtained, an empty set is returned.
     */
    public PrintServiceAttributeSet getPrintStatus() {
        PrintServiceAttributeSet attributes = new HashPrintServiceAttributeSet();

        Request req = new Request();

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);
        req.setHeader(header);

        Response<GetPrinterStatusResponseBody> resp;
        try {
            resp = mPrinter.getPrinterStatus(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
                GetPrinterStatusResponseBody body = resp.getBody();
                if(body.getPrinterStatus() != null) {
                    attributes.add(PrinterState.fromString(body.getPrinterStatus()));
                }
                if(body.getPrinterStatusReasons() != null) {
                    attributes.add(PrinterStateReasons.convertFrom(body.getPrinterStatusReasons()));
                }
            }
        } catch (IOException e) {
            LogC.e(e.getMessage());
        } catch (InvalidResponseException e) {
            LogC.e(e.getMessage());
        } catch (Exception e) {
            LogC.e(e.getMessage());
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
    public Capability getPrintCapability(PrintFile.PDL pdl) {

        RequestQuery query = new RequestQuery();
        query.put("pdl", pdl.toString());

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);

        Request req = new Request();
        req.setQuery(query);
        req.setHeader(header);

        Response<GetCapabilityResponseBody> resp;
        try{
            resp = mPrinter.getCapability(req);
            if(resp.getStatusCode() == HttpStatus.SC_OK) {
                return resp.getBody().getJobSettingCapability();
            }
        } catch( IOException ex ) {
            LogC.e(ex.getMessage());
        } catch( InvalidResponseException ex) {
            LogC.e(ex.getMessage());
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
        return mPrintAsyncEventReceiver.getAsyncConnectState();
    }

    /**
     * サポートしているPDLの一覧を取得します。
     * Obtains the list of supported PDL.
     * @return
     */
    public List<String> getSupportedPDL() {
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);

        Request req = new Request();
        req.setHeader(header);

        try {
            Response<GetSupportedPDLResponseBody> resp = mPrinter.getSupportedPDL(req);
            if(resp.getStatusCode() == HttpStatus.SC_OK) {
                return resp.getBody().getPdl();
            }
        } catch (IOException e) {
            LogC.e(e.getMessage());
        } catch (InvalidResponseException e) {
            LogC.e(e.getMessage());
        }
        return null;
    }

    /**
     * ジョブの非同期イベントリスナーを登録します。
     * Registers the asynchronous event listener of the job
     *
     * @param handler
     * @return subscribedId
     */
    public String addAsyncJobEventListener(AsyncJobEventHandler handler){
        if(handler == null) throw new IllegalArgumentException("handler is null");

        synchronized (this.mAsyncEvHandlers) {
            if (this.mSubscribedId == null) {
                this.mSubscribedId = mPrintAsyncEventReceiver.startReceiveJobEvent();
            }
            if (this.mSubscribedId != null) {
                this.mAsyncEvHandlers.add(handler);
            }
            return this.mSubscribedId;
        }
    }

    /**
     * ジョブの非同期イベントリスナーを除去します。
     * Removes the asynchronous event listener of the job
     *
     * @param handler
     */
    public void removeAsyncJobEventListener(AsyncJobEventHandler handler){
        if(handler == null) throw new IllegalArgumentException("handler is null");

        synchronized (this.mAsyncEvHandlers) {
            mAsyncEvHandlers.remove(handler);

            if(this.mAsyncEvHandlers.size() <= 0 && this.mSubscribedId != null) {
                this.mPrintAsyncEventReceiver.endReceiveJobEvent();
                this.mSubscribedId = null;
            }
        }
    }

    /**
     * プリントサービスの状態を取得し、属性セットとして値を返します。
     * Obtains the print service state and returns the value as an attribute set.
     *
     * @param userCode
     * @return 現在のプリントサービスの状態属性セット
     *         The state attribute set of the current print service.
     */
    public List<PrintJobAttributeSet> getPrintJobList(PrintUserCode userCode) {
        List<PrintJobAttributeSet> retList =
                new ArrayList<PrintJobAttributeSet>();
        RequestQuery query = new RequestQuery();
        if(userCode != null) {
            query.put("userCode", userCode.getUserCode());
        }

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);

        Request request = new Request();
        request.setQuery(query);
        request.setHeader(header);

        try {
            Response<GetJobListResponseBody> resp = mPrinter.getJobList(request);
            if(resp.getStatusCode() != HttpStatus.SC_OK) return retList;

            for(Iterator<JobInfo> ite = resp.getBody().iterator(); ite.hasNext();) {
                JobInfo info = ite.next();

                PrintJobAttributeSet jobAttributes = new HashPrintJobAttributeSet();
                if(info != null) {
                    putTo(jobAttributes, PrintJobState.fromString(info.getJobStatus()));
                    putTo(jobAttributes, PrintJobStateReasons.getInstance(info.getJobStatusReasons()));
                    putTo(jobAttributes, PrintJobPrintingInfo.getInstance(info.getPrintingInfo()));
                    putTo(jobAttributes, new PrintJobName(info.getJobName()));
                    putTo(jobAttributes, new PrintJobUserName(info.getUserName()));
                }

                retList.add(jobAttributes);
            }
        } catch (IOException e) {
            LogC.e(e.getMessage());
        } catch (InvalidResponseException e) {
            LogC.e(e.getMessage());
        }

        return retList;
    }
    
    public PrintConfiguration getPrintConfiguration() throws InvalidResponseException {
        PrintConfiguration conf = new PrintConfiguration();

        //スキャンの初期設定を取得しに行く
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());
        header.put(RequestHeader.X_TARGET, RequestHeader.INTERNAL);

        Request req = new Request();
        req.setHeader(header);

        Response<GetConfigurationResponseBody> resp;
        try {
            resp = mPrinter.getConfiguration(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
                GetConfigurationResponseBody resBody = resp.getBody();
                
                conf.setUerCodeRequired(resBody.getUserCodeRequired());
                conf.setMemoryDeviceToPrint(resBody.getMemoryDeviceToPrint());
                conf.setUserCodeFunc(UserCodeRestrictedFunc.getValue(resBody.getRestrictedFunc()));
                conf.setTraySettings(resBody.getTraySettings());
                conf.setBitSettingsArray(resBody.getBitSettings());
            }
        } catch (IOException e) {
            LogC.e(e.getMessage());
        } catch (InvalidResponseException e) {
            LogC.e(e.getMessage());
            throw e;
        } catch (Exception e) {
            LogC.e(e.getMessage());
        }
        return conf;
    }
    
    public AuthRestriction getPrintAuthRestriction(String userCode, PrintColor color) throws InvalidResponseException {
        //スキャンの認証情報を取得しに行く
        AuthRestriction retAuth = new AuthRestriction();
        
        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());
        header.put(RequestHeader.X_TARGET, RequestHeader.INTERNAL);
        
        RequestQuery query = new RequestQuery();
        query.put("userCode", userCode);
        if (PrintColor.COLOR == color) {
            query.put("printColor", "color");
        } else {
            query.put("printColor", "monochrome");
        }

        Request req = new Request();
        req.setHeader(header);
        req.setQuery(query);
        
        Response<GetPrinterAuthRestrictionResponseBody> resp;
        try {
            resp = mPrinter.getAuthRestriction(req);
            if( resp.getStatusCode() == HttpStatus.SC_OK ){
                GetPrinterAuthRestrictionResponseBody body = resp.getBody();

                retAuth.setLoginErrorReason(body.getLoginErrorReason());
                retAuth.setLoginPermission(body.getLoginPermission());
                retAuth.setRestrictionPanelId(body.getRestrictionPanelId());
               
            }
        } catch (IOException e) {
            LogC.e(e.getMessage());
        } catch( InvalidResponseException ex) {
            LogC.e(ex.getMessage());
            int statusCode = ex.getStatusCode();
            switch (statusCode) {
            case HttpStatus.SC_BAD_REQUEST:     //400:
            case HttpStatus.SC_UNAUTHORIZED:    //401:
            case HttpStatus.SC_FORBIDDEN:       //403:
                throw ex;   //エラー
            default:
                throw ex;   //エラー
                // TODO retry
            }
        } catch (Exception e) {
            LogC.e(e.getMessage());
        }
        return retAuth;
    }

    /***************************************************************************
     * private method
     **************************************************************************/

    /**
     * attributeSetに属性を追加します。attributeがnullの場合は登録されません。
     * Adds the attribute to attributeSet. If the attribute is null, the attribute is not registered.
     *
     * @param attributeSet 追加属性を格納する属性セット
     *                     Attribute set to store additional attribute
     * @param attribute 追加する属性
     *                  Attribute to add
     */
    private void putTo(PrintJobAttributeSet attributeSet, PrintJobAttribute attribute) {
        if(attribute != null) {
            attributeSet.add(attribute);
        }
    }

    /***************************************************************************
     *  SDKServiceからの通知
     *  Notification from SDKService
     **************************************************************************/
    /**
     * プリント状態イベントを受信します。
     * 状態の仕様ならびに、JSONフォーマット仕様はSmartSDK仕様に準拠します。
     * Receives print state event.
     * States and JSON format comply with SmartSDK specifications.
     *
     * @param eventData 受信したプリント状態を示すJSON文字列
     *                  The JSON string to indicate received print event
     */
    @Override
    public void onReceiveAppEvent(String eventData) {
        if(eventData == null) return;

        Map<String, Object> decoded = GenericJsonDecoder.decodeToMap(eventData);
        if(decoded == null) return;

        @SuppressWarnings("unchecked")
        GetPrinterStatusResponseBody body = new GetPrinterStatusResponseBody((Map<String,Object>)decoded.get("data"));

        PrintServiceAttributeSet notifySet = new HashPrintServiceAttributeSet();
        if(body.getPrinterStatus() != null) {
            notifySet.add(PrinterState.fromString(body.getPrinterStatus()));
        }

        if(body.getPrinterStatusReasons() != null) {
            notifySet.add(PrinterStateReasons.convertFrom(body.getPrinterStatusReasons()));
        }

        synchronized (this.mServiceListenerLock) {
            if(this.mServiceListener != null) {
                this.mServiceListener.onChangePrintServiceAttributes(notifySet);
            }
        }
    }

    /**
     * プリントジョブ状態イベントを受信します。
     * 状態の仕様ならびに、JSONフォーマット仕様はSmartSDK仕様に準拠します。
     * Receives print job state event
     * States and JSON format comply with SmartSDK specifications.
     *
     * @param eventData 受信したプリントジョブ状態を示すJSON文字列
     *                  The JSON string to indicate received print job event
     */
    @Override
    public void onReceiveJobEvent(String eventData) {
        if(eventData == null) return;

        Map<String, Object> decoded = GenericJsonDecoder.decodeToMap(eventData);
        if(decoded == null) return;

        @SuppressWarnings("unchecked")
        Map<String, Object> dataBody = (Map<String, Object>) decoded.get("data");
        if(dataBody == null) return;

        GetJobStatusResponseBody body = new GetJobStatusResponseBody(dataBody);
        String jobId = body.getJobId();

        AsyncJobEventHandler[] handlers;
        synchronized (mAsyncEvHandlers){
            handlers = mAsyncEvHandlers.toArray(new AsyncJobEventHandler[mAsyncEvHandlers.size()]);
        }

        for(AsyncJobEventHandler handler : handlers) {
            if(handler.getJobId() == null || handler.getJobId().equals(jobId)) {
                handler.onReceiveJobEvent(body);
            }
        }
    }

    @Override
    public void onReceiveFaxReceiveEvent(String eventData) {
        return;
    }
}
