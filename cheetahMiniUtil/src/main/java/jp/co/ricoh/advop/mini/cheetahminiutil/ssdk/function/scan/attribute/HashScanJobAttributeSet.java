/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.HashAttributeSet;

/**
 * スキャン属性を管理する属性セットクラスです。
 * 本属性セットはハッシュ構造を持ちます。
 * Attribute set class to manage scan attributes.
 * This attribute set supports hash structure.
 */
public final class HashScanJobAttributeSet extends HashAttributeSet<ScanJobAttribute> implements ScanJobAttributeSet {

	public HashScanJobAttributeSet() {
		super();
	}

	public HashScanJobAttributeSet(ScanJobAttribute attribute) {
		super(attribute);
	}

	public HashScanJobAttributeSet(ScanJobAttribute[] attributes) {
		super(attributes);
	}

	public HashScanJobAttributeSet(ScanJobAttributeSet attributes) {
		super(attributes);
	}

}
