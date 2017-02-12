/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetJobStatusResponseBody;

public final class ScanJobScanningInfo implements ScanJobAttribute {
	
	private final ScanJobState scanningState;
	private final ScanJobStateReasons scanningStateReasons;
	private final int scannedCount;
	private final int resetOriginalCount;
	private final int remainingTimeOfWaitingNextOriginal;
	private final String scannedThumbnailUri;
	
	public static ScanJobScanningInfo getInstance(GetJobStatusResponseBody.ScanningInfo info) {
		if (info == null) {
			return null;
		}
		return new ScanJobScanningInfo(info);
	}
	
	ScanJobScanningInfo(GetJobStatusResponseBody.ScanningInfo info) {
		scanningState = ScanJobState.fromString(info.getJobStatus());
		scanningStateReasons = ScanJobStateReasons.getInstance(info.getJobStatusReasons());
		scannedCount = Conversions.getIntValue(info.getScannedCount(), 0);
		resetOriginalCount = Conversions.getIntValue(info.getResetOriginalCount(), 0);
		remainingTimeOfWaitingNextOriginal = Conversions.getIntValue(info.getRemainingTimeOfWaitingNextOriginal(), 0);
		scannedThumbnailUri = info.getScannedThumbnailUri();
	}
	
	public ScanJobState getScanningState() {
		return scanningState;
	}

	public ScanJobStateReasons getScanningStateReasons() {
		return scanningStateReasons;
	}

	public int getScannedCount() {
		return scannedCount;
	}

	public int getResetOriginalCount() {
		return resetOriginalCount;
	}

    public int getRemainingTimeOfWaitingNextOriginal() {
        return remainingTimeOfWaitingNextOriginal;
    }

	public String getScannedThumbnailUri() {
		return scannedThumbnailUri;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanJobScanningInfo)) {
			return false;
		}
		
		ScanJobScanningInfo other = (ScanJobScanningInfo) obj;
		if (!isEqual(scanningState, other.scanningState)) {
			return false;
		}
		if (!isEqual(scanningStateReasons, other.scanningStateReasons)) {
			return false;
		}
		if (scannedCount != other.scannedCount) {
			return false;
		}
		if (resetOriginalCount != other.resetOriginalCount) {
			return false;
		}
        if (remainingTimeOfWaitingNextOriginal != other.remainingTimeOfWaitingNextOriginal) {
            return false;
        }
		if (!isEqual(scannedThumbnailUri, other.scannedThumbnailUri)) {
			return false;
		}
		return true;
	}

	private boolean isEqual(Object obj1, Object obj2) {
		if (obj1 == null) {
			return (obj2 == null);
		}
		return obj1.equals(obj2);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (scanningState == null ? 0 : scanningState.hashCode());
		result = 31 * result + (scanningStateReasons == null ? 0 : scanningStateReasons.hashCode());
		result = 31 * result + scannedCount;
		result = 31 * result + resetOriginalCount;
        result = 31 * result + remainingTimeOfWaitingNextOriginal;
		result = 31 * result + (scannedThumbnailUri == null ? 0 : scannedThumbnailUri.hashCode());
		return result;
	}

	private volatile String cache = null;

	@Override
	public String toString() {
		if (cache == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("scanningState:").append(scanningState).append(", ");
			sb.append("scanningStateReasons:").append(scanningStateReasons).append(", ");
			sb.append("scannedCount:").append(scannedCount).append(", ");
			sb.append("resetOriginalCount:").append(resetOriginalCount).append(", ");
            sb.append("remainingTimeOfWaitingNextOriginal:").append(remainingTimeOfWaitingNextOriginal).append(", ");
			sb.append("scannedThumbnailUri:").append(scannedThumbnailUri);
			sb.append("}");
			cache = sb.toString();
		}
		return cache;
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobScanningInfo.class;
	}

	@Override
	public String getName() {
		return ScanJobScanningInfo.class.getSimpleName();
	}

}
