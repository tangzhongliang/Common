/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetJobStatusResponseBody;

public final class ScanJobFilingInfo implements ScanJobAttribute {
	
	private final ScanJobState filingState;
	private final ScanJobStateReasons filingStateReasons;
	private final int filedPageCount;
	private final String fileUri;
	
	public static ScanJobFilingInfo getInstance(GetJobStatusResponseBody.FilingInfo info) {
		if (info == null) {
			return null;
		}
		return new ScanJobFilingInfo(info);
	}
	
	ScanJobFilingInfo(GetJobStatusResponseBody.FilingInfo info) {
		filingState = ScanJobState.fromString(info.getJobStatus());
		filingStateReasons = ScanJobStateReasons.getInstance(info.getJobStatusReasons());
		filedPageCount = Conversions.getIntValue(info.getFiledPageCount(), 0);
		fileUri = info.getFileUri();
	}

	public ScanJobState getFilingState() {
		return filingState;
	}

	public ScanJobStateReasons getFilingStateReasons() {
		return filingStateReasons;
	}

	public int getFiledPageCount() {
		return filedPageCount;
	}

	public String getFileUri() {
		return fileUri;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanJobFilingInfo)) {
			return false;
		}
		
		ScanJobFilingInfo other = (ScanJobFilingInfo) obj;
		if (!isEqual(filingState, other.filingState)) {
			return false;
		}
		if (!isEqual(filingStateReasons, other.filingStateReasons)) {
			return false;
		}
		if (filedPageCount != other.filedPageCount) {
			return false;
		}
		if (!isEqual(fileUri, other.fileUri)) {
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
		result = 31 * result + (filingState == null ? 0 : filingState.hashCode());
		result = 31 * result + (filingStateReasons == null ? 0 : filingStateReasons.hashCode());
		result = 31 * result + filedPageCount;
		result = 31 * result + (fileUri == null ? 0 : fileUri.hashCode());
		return result;
	}

	private volatile String cache = null;

	@Override
	public String toString() {
		if (cache == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("filingState:").append(filingState).append(", ");
			sb.append("filingStateReasons:").append(filingStateReasons).append(", ");
			sb.append("filedPageCount:").append(filedPageCount).append(", ");
			sb.append("fileUri:").append(fileUri);
			sb.append("}");
			cache = sb.toString();
		}
		return cache;
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobFilingInfo.class;
	}

	@Override
	public String getName() {
		return ScanJobFilingInfo.class.getSimpleName();
	}

}
