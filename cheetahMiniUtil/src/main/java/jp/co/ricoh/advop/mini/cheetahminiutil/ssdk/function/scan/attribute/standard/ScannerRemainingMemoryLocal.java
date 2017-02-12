/*
 *  Copyright (C) 2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;

public final class ScannerRemainingMemoryLocal implements ScanServiceAttribute {
	
	private final int remainingMemoryLocal;
	
	public ScannerRemainingMemoryLocal(int remainingMemoryLocal) {
		this.remainingMemoryLocal = remainingMemoryLocal;
	}
	
	public int getRemainingMemoryLocal() {
		return remainingMemoryLocal;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScannerRemainingMemoryLocal)) {
			return false;
		}
		
		ScannerRemainingMemoryLocal other = (ScannerRemainingMemoryLocal) obj;
		return (remainingMemoryLocal == other.remainingMemoryLocal);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + remainingMemoryLocal;
		return result;
	}
	
	@Override
	public String toString() {
		return Integer.toString(remainingMemoryLocal);
	}
	
	@Override
	public Class<?> getCategory() {
		return getClass();
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

}
