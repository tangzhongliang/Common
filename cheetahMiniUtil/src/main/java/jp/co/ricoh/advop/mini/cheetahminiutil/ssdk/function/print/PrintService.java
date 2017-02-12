/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncConnectState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintServiceAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintServiceAttributeListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service.FunctionMessageDispatcher;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service.ServiceListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service.SupportedAttributeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * プリンタのサービス機能を提供するクラスです。
 * Provides the print service functions for printing.
 */
public class PrintService {

    private static PrintService mServiceInstance;

    private final List<PrintServiceAttributeListener> mPrintServiceAttributeListeners = new ArrayList<PrintServiceAttributeListener>();

    private final Map<PrintFile.PDL, Map<Class<? extends PrintRequestAttribute>, Object>> mCapabilitiesMap;

    static {
        mServiceInstance = new PrintService();
    }

    private PrintService() {
        this.mCapabilitiesMap = new HashMap<PrintFile.PDL, Map<Class<? extends PrintRequestAttribute>, Object>>();
    }

    /**
     * プリンタサービスを取得します
     * Obtains the print service.
     * @return 利用可能なプリントサービス
     *         Available print service
     */
    public static PrintService getService() {
        return mServiceInstance;
    }

    /**
     * プリントサービス状態変更監視リスナーを登録します。
     * Registers the listener to monitor print service state changes.
     *
     * @param listener プリントサービス状態監視リスナー
     *                 print service attribute listener
     * @throws IllegalArgumentException 指定したリスナーがnullの場合
     *                                  When the specified listener is null
     * @return  非同期接続状態の結果。機器が利用不可能な状態の場合にはnullを返します。
     *          Returns the asynchronous connection state. If the device is unavailable, null is returned.
     */
    public AsyncConnectState addPrintServiceAttributeListener(PrintServiceAttributeListener listener) {
        if(listener == null) throw new IllegalArgumentException("listener is null");

        AsyncConnectState addListenerResult = null;
        synchronized (this.mPrintServiceAttributeListeners) {
            if( mPrintServiceAttributeListeners.contains(listener) ) {
                // do nothing if the specified listener is already registered
                return AsyncConnectState.valueOf(AsyncConnectState.STATE.CONNECTED, AsyncConnectState.ERROR_CODE.NO_ERROR);
            }

            if( this.mPrintServiceAttributeListeners.size() == 0 ) {
                addListenerResult =
                    FunctionMessageDispatcher.getInstance().addServiceListener(new ServiceListener() {
                        @Override
                        public void onChangePrintServiceAttributes(PrintServiceAttributeSet attributes) {
                            changePrintServiceAttributes(attributes);
                        }
                    });

                if(addListenerResult == null) {
                    return null;
                }
                if(addListenerResult.getState() != AsyncConnectState.STATE.CONNECTED) {
                    return addListenerResult;
                }
            } else {
                addListenerResult = AsyncConnectState.valueOf(AsyncConnectState.STATE.CONNECTED, AsyncConnectState.ERROR_CODE.NO_ERROR);
            }
            mPrintServiceAttributeListeners.add(listener);
        }

        //登録時にサービス状態を通知する
        // notify the specified listener of the current service state.
        PrintServiceAttributeSet notifySet = getAttributes();
        if(notifySet.size() > 0) {
            listener.attributeUpdate(new PrintServiceAttributeEvent(notifySet));
        }

        return addListenerResult;
    }


    /**
     * プリントサービス状態変更監視リスナーを除去します。
     * Removes the listener to monitor print service state changes
     *
     * @param listener 除去するリスナー
     *                 The listener to remove
     * @return 除去の成否
     *         Returns true if the listener unregistration has succeeded.
     * @throws IllegalArgumentException 指定したリスナーがnullの場合
     *                                  When the specified listener is null
     * @throws IllegalStateException 指定したリスナーが登録されていない場合
     *                               When the specified listener is not registered
     */
    public boolean removePrintServiceAttributeListener(PrintServiceAttributeListener listener) {
        if(listener == null) throw new IllegalArgumentException("listener is null");

        synchronized( this.mPrintServiceAttributeListeners ) {
            if(!this.mPrintServiceAttributeListeners.contains(listener)) return false;

            if(this.mPrintServiceAttributeListeners.size() == 1) {
                FunctionMessageDispatcher.getInstance().removeServiceListener();
            }

            this.mPrintServiceAttributeListeners.remove(listener);
        }

        return true;
    }

    /**
     * 非同期イベントの接続状態を取得します。
     * Obtains the state of asynchronous event connection
     *
     * @return 非同期イベントの接続状態
     *         State of asynchronous event connection
     */
    public AsyncConnectState getAsyncConnectState() {
        return FunctionMessageDispatcher.getInstance().getAsyncConnectState();
    }

    /**
     * プリントサービス属性セットを取得します
     * Obtains the print service attribute set.
     *
     * @return 現在のプリントサービス属性セット。取得できなかった場合は空セットが返ります。
     *         Current print service attribute set. If the attribute set cannot be obtained, an empty set is returned.
     */
    public PrintServiceAttributeSet getAttributes() {
        return FunctionMessageDispatcher.getInstance().getPrintStatus();
    }

    /**
     * 指定されたカテゴリのプリントサービス属性を取得します
     * Obtains the print service attribute for the specified category.
     *
     * @param category
     * @return 指定されたカテゴリの現在のプリントサービス属性を返します。取得できない場合はnullが返ります。
     *         Returns the current print service attribute for the specified category. If the attribute cannot be obtained, null is returned.
     */
    public PrintServiceAttribute getAttribute(Class<? extends PrintServiceAttribute> category) {
        if( category == null ) throw new IllegalArgumentException("category is null");

        PrintServiceAttributeSet attributeSet = FunctionMessageDispatcher.getInstance().getPrintStatus();
        if(attributeSet == null) return null;

        return attributeSet.get(category);
    }


    /**
     * サポートされているPDL情報を取得します。
     * Obtains information on supported PDL
     * @return サポートされているPDLのリスト
     *         supported PDL list
     */
    public List<PrintFile.PDL> getSupportedPDL() {
        List<PrintFile.PDL> supportedPdlList = new ArrayList<PrintFile.PDL>();
        List<String> supportedPdlValueList = FunctionMessageDispatcher.getInstance().getSupportedPDL();
        if(supportedPdlValueList == null) return null;

        for(String pdlValue : supportedPdlValueList) {
            PrintFile.PDL pdl = PrintFile.PDL.fromString(pdlValue);
            if(pdl != null) {
                supportedPdlList.add(pdl);
            }
        }

        return supportedPdlList;
    }

    /**
     * このプリントサービスに対して発行するジョブの属性設定時に、指定可能なプリントジョブ属性カテゴリの一覧を取得します。
     * Obtains the list of print job attribute categories that can be set, at the time of setting the attribute of the job
     *
     * @param pdl
     * @return ジョブ属性カテゴリ一覧
     *         Set of job attribute categories
     */
    public Set<Class<? extends PrintRequestAttribute>> getSupportedAttributeCategories(PrintFile.PDL pdl) {
        if(pdl == null) throw new IllegalArgumentException("pdl is null");

        Map<Class<? extends PrintRequestAttribute>, Object> capabilities = null;
        synchronized (this.mCapabilitiesMap) {
            capabilities  = this.mCapabilitiesMap.get(pdl);
        }

        if(capabilities == null) {
            capabilities = SupportedAttributeBuilder.getSupportedAttribute(
                    FunctionMessageDispatcher.getInstance().getPrintCapability(pdl));

            if(capabilities == null) return null;

            synchronized (this.mCapabilitiesMap){
                mCapabilitiesMap.put(pdl,capabilities);
            }
        }

        return Collections.unmodifiableSet(capabilities.keySet());
    }

    /**
     * このプリントサービスのジョブ設定時に指定可能なプリントジョブ属性カテゴリごとの値を取得します。
     * Obtains the values that can be specified for each print job attribute category at the time of setting the job for this print service.
     *
     * @param pdl
     * @param category
     * @return 属性値
     *         attribute value
     */
    public Object getSupportedAttributeValues(PrintFile.PDL pdl, Class<? extends PrintRequestAttribute> category) {
        if(category == null) throw new IllegalArgumentException("category is null");
        if(pdl == null) throw new IllegalArgumentException("pdl is null");

        Map<Class<? extends PrintRequestAttribute>, Object> capabilities = null;
        synchronized (this.mCapabilitiesMap) {
            capabilities  = this.mCapabilitiesMap.get(pdl);
        }

        if(capabilities == null) {
            capabilities = SupportedAttributeBuilder.getSupportedAttribute(
                    FunctionMessageDispatcher.getInstance().getPrintCapability(pdl));

            if(capabilities == null) return null;

            synchronized (this.mCapabilitiesMap){
                mCapabilitiesMap.put(pdl,capabilities);
            }
        }

        return capabilities.get(category);
    }

    /**
     * このプリントサービスのジョブ設定時に指定可能なプリント属性カテゴリごとの値のタイプを取得します。
     * Obtains the value type that can be specified for each print job attribute category at the time of setting the job for this print service.
     *
     * @param  pdl
     * @param category
     * @return type
     */
    public Class<?> getSupportedAttributeType(PrintFile.PDL pdl,Class<? extends PrintRequestAttribute> category) {
        if(category == null) throw new IllegalArgumentException("category is null");
        if(pdl == null) throw new IllegalArgumentException("pdl is null");

        Map<Class<? extends PrintRequestAttribute>, Object> capabilities = null;
        synchronized (this.mCapabilitiesMap) {
            capabilities  = this.mCapabilitiesMap.get(pdl);
        }

        if(capabilities == null) {
            capabilities = SupportedAttributeBuilder.getSupportedAttribute(
                    FunctionMessageDispatcher.getInstance().getPrintCapability(pdl));

            if(capabilities == null) return null;

            synchronized (this.mCapabilitiesMap){
                mCapabilitiesMap.put(pdl,capabilities);
            }
        }

        return capabilities.get(category).getClass();
    }


    public List<PrintJobAttributeSet> getJobList(PrintUserCode userCode) {
        return FunctionMessageDispatcher.getInstance().getPrintJobList(userCode);
    }

    /**
     * 内部管理層からのサービス状態変更通知
     * Service state change notification sent from internal management layer
     */
    private void changePrintServiceAttributes(PrintServiceAttributeSet attributes) {

        synchronized (this.mPrintServiceAttributeListeners) {
            if(attributes.size() <= 0) return;
            notifyAttributeListeners(attributes);
        }
    }

    /**
     * サービス状態変更通知
     * Service state change notification
     *
     * @param attributeSet
     */
    private void notifyAttributeListeners(PrintServiceAttributeSet attributeSet) {
        PrintServiceAttributeListener[] listeners;
        synchronized (this.mPrintServiceAttributeListeners) {
            listeners = this.mPrintServiceAttributeListeners
                            .toArray(new PrintServiceAttributeListener[this
                                    .mPrintServiceAttributeListeners.size()]);
        }

        if(listeners.length > 0) {
            PrintServiceAttributeEvent event = new PrintServiceAttributeEvent(attributeSet);
            for(PrintServiceAttributeListener listener : listeners) {
                listener.attributeUpdate(event);
            }
        }
    }
}
