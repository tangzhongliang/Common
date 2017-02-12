/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;

public final class ScanJobStateReasons implements ScanJobAttribute, Iterable<ScanJobStateReason> {
	
	private final Set<ScanJobStateReason> reasons;
	
	public static ScanJobStateReasons getInstance(List<String> reasons) {
		if (reasons == null) {
			return null;
		}
		return new ScanJobStateReasons(reasons);
	}
	
	ScanJobStateReasons(List<String> reasonValues) {
		EnumSet<ScanJobStateReason> reasonSet = EnumSet.noneOf(ScanJobStateReason.class);
		for (String reasonValue : reasonValues) {
			ScanJobStateReason reason = ScanJobStateReason.fromString(reasonValue);
			if (reason != null) {
				reasonSet.add(reason);
			}
		}
		this.reasons = Collections.unmodifiableSet(reasonSet);
	}
	
	public Set<ScanJobStateReason> getReasons() {
		return reasons;
	}

	@Override
	public Iterator<ScanJobStateReason> iterator() {
		return reasons.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanJobStateReasons)) {
			return false;
		}
		
		ScanJobStateReasons other = (ScanJobStateReasons) obj;
		return reasons.equals(other.reasons);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + reasons.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return reasons.toString();
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobStateReasons.class;
	}

	@Override
	public String getName() {
		return ScanJobStateReasons.class.getSimpleName();
	}

}
