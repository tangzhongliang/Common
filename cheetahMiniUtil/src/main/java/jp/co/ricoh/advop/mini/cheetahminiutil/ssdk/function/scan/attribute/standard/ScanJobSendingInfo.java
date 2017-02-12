/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetJobStatusResponseBody;

public final class ScanJobSendingInfo implements ScanJobAttribute {
	
	private final ScanJobState sendingState;
	private final ScanJobStateReasons sendingStateReasons;
	private final int sentDestinationCount;
	private final int totalDestinationCount;
	private final int omittedBlankPageCount;

	public static ScanJobSendingInfo getInstance(GetJobStatusResponseBody.SendingInfo info) {
		if (info == null) {
			return null;
		}
		return new ScanJobSendingInfo(info);
	}
	
	ScanJobSendingInfo(GetJobStatusResponseBody.SendingInfo info) {
		sendingState = ScanJobState.fromString(info.getJobStatus());
		sendingStateReasons = ScanJobStateReasons.getInstance(info.getJobStatusReasons());
		sentDestinationCount = Conversions.getIntValue(info.getSentDestinationCount(), 0);
		totalDestinationCount = Conversions.getIntValue(info.getTotalDestinationCount(), 0);
		omittedBlankPageCount = Conversions.getIntValue(info.getOmittedBlankPageCount(), 0);
	}
	
	public ScanJobState getSendingState() {
		return sendingState;
	}

	public ScanJobStateReasons getSendingStateReasons() {
		return sendingStateReasons;
	}

	public int getSentDestinationCount() {
		return sentDestinationCount;
	}

	public int getTotalDestinationCount() {
		return totalDestinationCount;
	}

	public int getOmittedBlankPageCount() {
		return omittedBlankPageCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ScanJobSendingInfo)) {
			return false;
		}
		
		ScanJobSendingInfo other = (ScanJobSendingInfo) obj;
		if (!isEqual(sendingState, other.sendingState)) {
			return false;
		}
		if (!isEqual(sendingStateReasons, other.sendingStateReasons)) {
			return false;
		}
		if (sentDestinationCount != other.sentDestinationCount) {
			return false;
		}
		if (totalDestinationCount != other.totalDestinationCount) {
			return false;
		}
		if (omittedBlankPageCount != other.omittedBlankPageCount) {
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
		result = 31 * result + (sendingState == null ? 0 : sendingState.hashCode());
		result = 31 * result + (sendingStateReasons == null ? 0 : sendingStateReasons.hashCode());
		result = 31 * result + sentDestinationCount;
		result = 31 * result + totalDestinationCount;
		result = 31 * result + omittedBlankPageCount;
		return result;
	}

	private volatile String cache = null;

	@Override
	public String toString() {
		if (cache == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("sendingState:").append(sendingState).append(", ");
			sb.append("sendingStateReasons:").append(sendingStateReasons).append(", ");
			sb.append("sentDestinationCount:").append(sentDestinationCount).append(", ");
			sb.append("totalDestinationCount:").append(totalDestinationCount).append(", ");
			sb.append("omittedBlankPageCount:").append(omittedBlankPageCount);
			sb.append("}");
			cache = sb.toString();
		}
		return cache;
	}

	@Override
	public Class<?> getCategory() {
		return ScanJobSendingInfo.class;
	}

	@Override
	public String getName() {
		return ScanJobSendingInfo.class.getSimpleName();
	}

}
