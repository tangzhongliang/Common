/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.impl.service;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttributeSet;

/**
 * SDKServiceからの非同期サービスイベントを通知するリスナーインターフェイスです。
 * The listener interface to notify the asynchronous service events from SDKService.
 */
public interface ServiceListener {
	/**
	 * Attributeの変更があったことを通知します。
	 * Notifies that the attribute has changed
	 *
	 * @param attributes
	 */
	abstract void onChangeScanServiceAttributes(ScanServiceAttributeSet attributes);
}
