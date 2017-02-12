/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.Attribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.impl.AsyncConnectState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanServiceAttributeEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event.ScanServiceAttributeListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.service.FunctionMessageDispatcher;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.service.ServiceListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.service.SupportedAttributeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * スキャンに関するスキャナのサービス機能を提供します
 * ScanServiceは以下の機能を提供します。
 * ・スキャンサービスインスタンスファクトリ
 * ・スキャナ状態取得
 * ・スキャナ状態変化通知
 * ・Capability情報取得
 *
 * Provides the scan service functions for scanning.
 * ScanService provides the following functions.
 *  - Scan service instance factory
 *  - Obtain scanner states
 *  - Notify scanner state changes
 *  - Obtain capability information
 *
 */
public final class ScanService {
	// singleton
	private static ScanService mServiceInstance = null;

	private final List<ScanServiceAttributeListener> mScanServiceListeners = new ArrayList<ScanServiceAttributeListener>();

	private Map<Class<? extends ScanRequestAttribute>, Object> mCapabilitiesMap = null;
    private final Object mLockCapability = new Object();

	static{
		mServiceInstance = new ScanService();
	}

	// Constructor is invisible.
	private ScanService(){
	}

	/**
	 * スキャンサービスを取得します。
	 * 現在利用するためのスキャンサービスを返します。
	 * 取得直後は、本体サービスが開始していない可能性があるため、サービスで提供しているI/Fを利用しても
	 * 例外となる可能性があります。
	 * サービスが利用可能になったかは、サービスの状態を取得もしくは、監視してください。
	 * Obtains the scan service.
	 * Returns the current scan service to use.
	 * Immediately after obtaining the instance of the scan service, an exception may be thrown
	 * because the scan service may have not been started.
	 *
	 * @return 利用可能なスキャンサービス
	 *         Available scan service
	 */
	public static ScanService getService(){
		return mServiceInstance;
	}

	/**
	 * スキャンサービス状態変更監視リスナーを登録します。
	 * 指定したスキャンサービス状態変更監視リスナーを登録します。
	 * 既にリスナが登録されている場合は、何も登録されません。
	 * Registers the listener to monitor scan service state changes.
	 * Registers the specified listener to monitor scan service state changes.
	 * If the specified listener is already registered, no listener is registered.
	 *
	 * @param listener スキャンサービス状態監視リスナー
	 *                 scan service attribute listener
	 * @throws IllegalArgumentException 指定したリスナーがnullの場合
	 *                                  When the specified listener is null
	 * @see ScanService#removeScanServiceAttributeListener(ScanServiceAttributeListener)
     * @return  非同期接続状態の結果。機器が利用不可能な状態の場合にはnullを返します。
     *          Returns the asynchronous connection state. If the device is unavailable, null is returned.
	 */
	public AsyncConnectState addScanServiceAttributeListener(ScanServiceAttributeListener listener){
		if(listener == null) throw new IllegalArgumentException("listener is null");

        AsyncConnectState addListenerResult = null;
        synchronized(this.mScanServiceListeners) {
		    if( mScanServiceListeners.contains(listener) ){
			    // do nothing if the specified listener is already registered
                return AsyncConnectState.valueOf(AsyncConnectState.STATE.CONNECTED, AsyncConnectState.ERROR_CODE.NO_ERROR);
		    }

            if( this.mScanServiceListeners.size() == 0) {
                addListenerResult = FunctionMessageDispatcher.getInstance().addServiceListener(new ServiceListener() {
                    @Override
                    public void onChangeScanServiceAttributes(ScanServiceAttributeSet attributes) {
                        changeScanServiceAttributes(attributes);
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

            mScanServiceListeners.add(listener);
        }

        // リスナー登録後、該当リスナーに対して現在のサービス状態を通知する
        // notify the specified listener of the current service state.
        ScanServiceAttributeSet notifySet = getAttributes();
        if( notifySet.size() > 0 ) {
            listener.attributeUpdate(new ScanServiceAttributeEvent(notifySet));
        }

        return addListenerResult;
    }

	/**
	 * 指定したスキャンサービス状態変更監視リスナーを除去します。
	 * Removes the specified listener to monitor scan service state changes.
	 *
	 * @param listener 除去するリスナー
	 *                 The listener to remove
	 * @return 除去の成否
	 *         Returns true if the listener unregistration has succeeded.
	 * @throws IllegalArgumentException 指定したリスナーがnullの場合
	 *                                  When the specified listener is null
	 * @throws IllegalStateException 指定したリスナーが登録されていない場合
	 *                               When the specified listener is not registered
	 * @see ScanService#addScanServiceAttributeListener(ScanServiceAttributeListener)
	 */
	public boolean removeScanServiceAttributeListener(ScanServiceAttributeListener listener){
		if (listener == null) throw new IllegalArgumentException("listener is null");

        synchronized ( this.mScanServiceListeners) {
    		if (!this.mScanServiceListeners.contains(listener)) return false;

            //リスナーが登録されていない場合は、SDKServiceのリスナーを解除する
    		// If no other listener is registered, unregister the SDKService listener.
            if( this.mScanServiceListeners.size() == 1 ) {
                FunctionMessageDispatcher.getInstance().removeServiceListener();
            }

            mScanServiceListeners.remove(listener);
        }

        return true;
	}

    /**
     * 非同期イベントの接続状態を取得します
     * Obtains the state of asynchronous event connection
     *
     * @return 非同期イベントの接続状態
     *         State of asynchronous event connection
     */
    public AsyncConnectState getAsyncConnectState() {
        return FunctionMessageDispatcher.getInstance().getAsyncConnectState();
    }

	/**
	 * スキャンサービス属性セットを取得します
	 * Obtains the scan service attribute set.
	 *
	 * @return 現在のスキャンサービス属性セット。取得できなかった場合は空セットが返ります。
	 *         Current scan service attribute set. If the attribute set cannot be obtained, an empty set is returned.
	 */
	public ScanServiceAttributeSet getAttributes(){
        return  FunctionMessageDispatcher.getInstance().getScanStatus();
	}

	/**
	 * 指定されたカテゴリのスキャンサービス属性を取得します
     * getAttributes()と同じ動きをしますが、1件だけ取得することが可能です
     * Obtains the scan service attribute for the specified category.
     * The behavior is same as getAttributes(), but only one attribute can be obtained.
     *
	 * @param category
	 * @return 指定されたカテゴリの現在のスキャンサービス属性を返します。取得できない場合はnullが返ります。
	 *         Returns the current scan service attribute for the specified category. If the attribute cannot be obtained, null is returned.
	 */
	public ScanServiceAttribute getAttribute(Class<? extends ScanServiceAttribute> category){
		if( category == null ) throw new IllegalArgumentException("category is null");
        ScanServiceAttributeSet attributeSet = FunctionMessageDispatcher.getInstance().getScanStatus();
        if(attributeSet == null) return null;

        return attributeSet.get(category);
	}

	/**
	 * このスキャンサービスに対して発行するジョブの属性設定時に、指定可能なスキャンジョブ属性カテゴリの一覧を取得します。
	 * ジョブ属性カテゴリは、Attributeインターフェイスを実装するClassにより示されます。
	 * このメソッドはサポートする属性のカテゴリだけを返し、実際に属性で指定できる「値」は返しません。
	 * 実際に指定可能な値を取得するには、ScanService{@link #getAttributes()}から取得できる属性のgetSupportedAttributeValues()を取得することで
	 * 設定可能値を取得することができます。
     * また、現在スキャンサービスが利用できない場合は、nullが返ります。
     * Obtains the list of scan job attribute categories that can be set, at the time of setting the attribute of the job
     * to issue for this scan service. Job attribute category is indicated by the class which implements the Attribute interface.
     * This method only returns the category of supported attributes; it does not return the values that can be actually specified
     * for attributes. The values that can be specified can be obtained by obtaining getSupportedAttributeValues() of the attribute
     * which can be acquired by ScanService{@link #getAttributes()}. If the scan service is currently unavailable, null is returned.
     *
	 * @return ジョブ属性カテゴリ一覧
	 *         Set of job attribute categories
	 * @see Attribute
	 * @see ScanRequestAttribute
	 */
	public Set<Class<? extends ScanRequestAttribute>> getSupportedAttributeCategories(){
        synchronized (this.mLockCapability) {
            if( this.mCapabilitiesMap == null ) {
                this.mCapabilitiesMap = SupportedAttributeBuilder.getSupportedAttribute(FunctionMessageDispatcher.getInstance().getScanCapability());
                if(mCapabilitiesMap == null) return null;
            }

		    return Collections.unmodifiableSet(mCapabilitiesMap.keySet());
        }
	}

	/**
	 * このスキャンサービスのジョブ設定時に指定可能なスキャンジョブ属性カテゴリごとの値を取得します。
     * また、現在スキャンサービスが利用できない場合は、nullが返ります。
     * Obtains the values that can be specified for each scan job attribute category at the time of setting the job for this scan service.
     * If the scan service is currently unavailable, null is returned.
     *
	 * @param category
	 * @return
	 */
	public Object getSupportedAttributeValues(Class<? extends ScanRequestAttribute> category){
		if( category == null ) throw new IllegalArgumentException("parameter is null");

        synchronized ( this.mLockCapability) {
            if( this.mCapabilitiesMap == null ) {
                this.mCapabilitiesMap = SupportedAttributeBuilder.getSupportedAttribute(FunctionMessageDispatcher.getInstance().getScanCapability());
                if(mCapabilitiesMap == null) return null;
            }

	    	if( !mCapabilitiesMap.containsKey(category) ){
    			return null;
    		}

		    return mCapabilitiesMap.get(category);
        }
	}


	/**
	 * このスキャンサービスのジョブ設定時に指定可能なスキャン属性カテゴリごとの値のタイプを取得します。
	 * 指定されたカテゴリに対応する指定可能なスキャン属性が存在しない場合はnullとなります。
     * また、現在スキャンサービスが利用できない場合は、nullが返ります。
     * Obtains the value type that can be specified for each scan job attribute category at the time of setting the job for this scan service.
     * If there is no scan attribute that can be set for the specified category, null is returned.
     * If the scan service is currently unavailable, null is returned.
     *
	 * @param category
	 * @return
	 */
	public Class<?> getSupportedAttributeType(Class<? extends ScanRequestAttribute> category) {
		if( category == null ) throw new IllegalArgumentException("parameter is null");

        synchronized ( this.mLockCapability) {
            if( mCapabilitiesMap == null ) {
                mCapabilitiesMap = SupportedAttributeBuilder.getSupportedAttribute(FunctionMessageDispatcher.getInstance().getScanCapability());
                if(mCapabilitiesMap == null) return null;
            }

		    if( !mCapabilitiesMap.containsKey(category) ) return null;

		    return mCapabilitiesMap.get(category).getClass();
        }
	}

	/**
	 * 内部管理層からのサービス状態変更通知
	 * Service state change notification sent from internal management layer
	 */
	private void changeScanServiceAttributes(ScanServiceAttributeSet attributes) {
        if( attributes.size() <= 0){
            return;
        }

        notifyAttributeListeners(attributes);
	}

    /**
     * サービス状態変更通知
     * Service state change notification
     *
     * @param attributeSet
     */
	private void notifyAttributeListeners(ScanServiceAttributeSet attributes) {
        ScanServiceAttributeListener[] listeners;
        synchronized (this.mScanServiceListeners) {
            listeners = this.mScanServiceListeners
                    .toArray(new ScanServiceAttributeListener[this.mScanServiceListeners.size()]);
		}
        if (listeners.length > 0) {
            ScanServiceAttributeEvent event = new ScanServiceAttributeEvent(attributes);
            for( ScanServiceAttributeListener listener : listeners ){
                listener.attributeUpdate(event);
            }
        }
	}

}
