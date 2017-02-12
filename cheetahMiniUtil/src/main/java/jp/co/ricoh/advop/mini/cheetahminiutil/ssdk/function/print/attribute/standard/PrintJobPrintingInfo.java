/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetJobStatusResponseBody;

/**
 * 印刷状態を示す属性クラスです。
 * The class indicates the job state.
 */
public class PrintJobPrintingInfo implements PrintJobAttribute{
    private PrintJobState mState;
    private PrintJobStateReasons mJobStateReasons;
    private int mPrintedCount;
    private boolean mFileReadCompleted;

    public static PrintJobPrintingInfo getInstance(GetJobStatusResponseBody.PrintingInfo info ) {
        if(info == null) {
            return null;
        }
        return new PrintJobPrintingInfo(info);
    }

    PrintJobPrintingInfo(GetJobStatusResponseBody.PrintingInfo info) {
        this.mState = PrintJobState.fromString(info.getJobStatus());
        this.mJobStateReasons = PrintJobStateReasons.getInstance(info.getJobStatusReasons());
        this.mPrintedCount = Conversions.getIntValue(info.getPrintedCount(), 0);
        this.mFileReadCompleted = Conversions.getBooleanValue(info.getFileReadCompleted(), false);
    }

    public PrintJobState getState() {
        return mState;
    }

    public PrintJobStateReasons getJobStateReasons() {
        return mJobStateReasons;
    }

    public int getPrintedCount() {
        return mPrintedCount;
    }

    public boolean isFileReadCompleted() {
        return mFileReadCompleted;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if(!(obj instanceof PrintJobPrintingInfo)) return false;

        PrintJobPrintingInfo otherInfo = (PrintJobPrintingInfo)obj;

        if(!isEqual(this.mState, otherInfo.getState())) return false;
        if(!isEqual(this.mJobStateReasons, otherInfo.getJobStateReasons())) return false;
        if(!isEqual(this.mPrintedCount, otherInfo.getPrintedCount())) return false;
        if(!isEqual(this.mFileReadCompleted, otherInfo.isFileReadCompleted())) return false;

        return true;
    }

    private boolean isEqual(Object original, Object target) {
        if(original == null) {
            return (target == null);
        }
        return original.equals(target);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.mState == null ? 0 : this.mState.hashCode());
        result = 31 * result + (this.mJobStateReasons == null ? 0 : this.mJobStateReasons.hashCode());
        result = 31 * result + this.mPrintedCount;
        result = 31 * result + (this.mFileReadCompleted ? 0: 1);
        return result;
    }

    private volatile String cache = null;
    @Override
    public String toString() {
        if(this.cache == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("state:").append(this.mState).append(",");
            sb.append("jobStateReasons:").append(this.mJobStateReasons).append(",");
            sb.append("printedCount:").append(this.mPrintedCount).append(",");
            sb.append("fileReadCompleted").append(this.mPrintedCount);
            sb.append("}");
            this.cache = sb.toString();
        }
        return this.cache;
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
