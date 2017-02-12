/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;

public final class ScannerRemainingMemory implements ScanServiceAttribute {
	
	private final int remainingMemory;
	
	public ScannerRemainingMemory(int remainingMemory) {
		this.remainingMemory = remainingMemory;
	}
	
	public int getRemainingMemory() {
		return remainingMemory;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScannerRemainingMemory)) {
			return false;
		}
		
		ScannerRemainingMemory other = (ScannerRemainingMemory) obj;
		return (remainingMemory == other.remainingMemory);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + remainingMemory;
		return result;
	}
	
	@Override
	public String toString() {
		return Integer.toString(remainingMemory);
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
