/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.event;

/**
 * ファクスジョブ属性変化監視リスナーのインターフェースです。
 * The listener interface to monitor fax job attribute changes
 */
public interface ScanJobAttributeListener {

	public abstract void updateAttributes(ScanJobAttributeEvent attributesEvent);

}
