/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.HashAttributeSet;

/**
 * スキャン要求の属性を管理する属性セットクラスです。
 * 本属性セットはハッシュ構造を持ちます。
 * Attribute set class to manage scan request attributes.
 * This attribute set supports hash structure.
 */
public final class HashScanRequestAttributeSet extends HashAttributeSet<ScanRequestAttribute> implements ScanRequestAttributeSet {

	public HashScanRequestAttributeSet() {
		super();
	}

	public HashScanRequestAttributeSet(ScanRequestAttribute attribute) {
		super(attribute);
	}

	public HashScanRequestAttributeSet(ScanRequestAttribute[] attributes) {
		super(attributes);
	}

	public HashScanRequestAttributeSet(ScanRequestAttributeSet attributes) {
		super(attributes);
	}

}
