/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetJobStatusResponseBody;

public final class ScanJobOcringInfo implements ScanJobAttribute {
	
	private final ScanJobState ocringState;
	private final ScanJobStateReasons ocringStateReasons;
	private final int ocredPageCount;
	private final int omittedBlankPageCount;
	private final String ocrdataUri;
	
	public static ScanJobOcringInfo getInstance(GetJobStatusResponseBody.OcringInfo info) {
		if (info == null) {
			return null;
		}
		return new ScanJobOcringInfo(info);
	}
	
	ScanJobOcringInfo(GetJobStatusResponseBody.OcringInfo info) {
		ocringState = ScanJobState.fromString(info.getJobStatus());
		ocringStateReasons = ScanJobStateReasons.getInstance(info.getJobStatusReasons());
		ocredPageCount = Conversions.getIntValue(info.getOcredPageCount(), 0);
		omittedBlankPageCount = Conversions.getIntValue(info.getOmittedBlankPageCount(), 0);
		ocrdataUri = info.getOcrdataUri();
	}

	public ScanJobState getOcringState() {
		return ocringState;
	}

	public ScanJobStateReasons getOcringStateReasons() {
		return ocringStateReasons;
	}

	public int getOcredPageCount() {
		return ocredPageCount;
	}

    public int getOmittedBlankPageCount() {
        return omittedBlankPageCount;
    }

	public String getOcrdataUri() {
		return ocrdataUri;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanJobOcringInfo)) {
			return false;
		}
		
		ScanJobOcringInfo other = (ScanJobOcringInfo) obj;
		if (!isEqual(ocringState, other.ocringState)) {
			return false;
		}
		if (!isEqual(ocringStateReasons, other.ocringStateReasons)) {
			return false;
		}
		if (ocredPageCount != other.ocredPageCount) {
			return false;
		}
        if (omittedBlankPageCount != other.omittedBlankPageCount) {
            return false;
        }
		if (!isEqual(ocrdataUri, other.ocrdataUri)) {
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
		result = 31 * result + (ocringState == null ? 0 : ocringState.hashCode());
		result = 31 * result + (ocringStateReasons == null ? 0 : ocringStateReasons.hashCode());
		result = 31 * result + ocredPageCount;
        result = 31 * result + omittedBlankPageCount;
		result = 31 * result + (ocrdataUri == null ? 0 : ocrdataUri.hashCode());
		return result;
	}

	private volatile String cache = null;

	@Override
	public String toString() {
		if (cache == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("ocringState:").append(ocringState).append(", ");
			sb.append("ocringStateReasons:").append(ocringStateReasons).append(", ");
			sb.append("ocredPageCount:").append(ocredPageCount).append(", ");
            sb.append("omittedBlankPageCount:").append(omittedBlankPageCount).append(", ");
			sb.append("ocrdataUri:").append(ocrdataUri);
			sb.append("}");
			cache = sb.toString();
		}
		return cache;
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobOcringInfo.class;
	}

	@Override
	public String getName() {
		return ScanJobOcringInfo.class.getSimpleName();
	}

}
