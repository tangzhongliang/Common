/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.Severity;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetScannerStatusResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * スキャナサービスの状態についての追加情報(スキャナサービスのScannerState属性)を値のリストとして列挙する属性クラスです。
 * The attribute class which lists the additional information of scanner service state
 * (ScannerState attributes for the scanner service) in values.
 */
public final class ScannerStateReasons implements ScanServiceAttribute {

    private final Map<ScannerStateReason, Severity> reasons;

    public static ScannerStateReasons convertFrom(GetScannerStatusResponseBody.ScannerStatusReasonsArray reasonsArray) {
    	if (reasonsArray == null) {
    		return null;
    	}

    	Map<ScannerStateReason, Severity> reasonsMap = new HashMap<ScannerStateReason, Severity>();
    	for (GetScannerStatusResponseBody.ScannerStatusReasons reasons : reasonsArray) {
    		ScannerStateReason reason = ScannerStateReason.fromString(reasons.getScannerStatusReason());
    		Severity severity = Severity.fromString(reasons.getSeverity());
    		if (reason != null) {
    			reasonsMap.put(reason, severity);
    		}
    	}

    	return new ScannerStateReasons(reasonsMap);
    }

    ScannerStateReasons (Map<ScannerStateReason, Severity> reasons) {
        this.reasons = reasons;
    }

    public Set<ScannerStateReason> getReasons() {
    	return Collections.unmodifiableSet(reasons.keySet());
    }

    public Set<ScannerStateReason> scannerStateReasonSet(Severity severity) {
    	Set<ScannerStateReason> reasonSet = new HashSet<ScannerStateReason>();
    	for (Map.Entry<ScannerStateReason, Severity> entry : reasons.entrySet()) {
    		if (entry.getValue() == severity) {
    			reasonSet.add(entry.getKey());
    		}
    	}
    	return Collections.unmodifiableSet(reasonSet);
    }

    @Override
    public boolean equals(Object obj) {
    	if (obj == this) {
    		return true;
    	}
    	if (!(obj instanceof ScannerStateReasons)) {
    		return false;
    	}

    	ScannerStateReasons other = (ScannerStateReasons) obj;
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
        return ScannerStateReasons.class;
    }

    @Override
    public String getName() {
        // Returnes the class name (this class is not a job attribute)
        return this.getClass().getSimpleName();
    }
}
