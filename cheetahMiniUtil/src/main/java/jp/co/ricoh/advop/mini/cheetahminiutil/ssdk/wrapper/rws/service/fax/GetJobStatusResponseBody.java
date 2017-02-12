/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetJobStatusResponseBody extends Element implements ResponseBody {

	private static final String KEY_EVENT_DETAIL 		    = "eventDetail";
    private static final String KEY_JOB_ID                  = "jobId";
    private static final String KEY_JOB_STATUS              = "jobStatus";
    private static final String KEY_JOB_STATUS_REASONS      = "jobStatusReasons";
    private static final String KEY_JOB_STATUS_DETAILS      = "jobStatusDetails";   // SmartSDK V1.02
    private static final String KEY_SCANNING_INFO           = "scanningInfo";
    private static final String KEY_PRINTING_INFO           = "printingInfo";       // SmartSDK V1.02
    private static final String KEY_STORED_FILE_ID          = "storedFileId";       // SmartSDK V1.02
    private static final String KEY_VALIDATE_ONLY           = "validateOnly";
    private static final String KEY_JOB_SETTING             = "jobSetting";

    public GetJobStatusResponseBody(Map<String, Object> values) {
        super(values);
    }
    
	/*
	 * eventDetail (String)
	 */
	public String getEventDetail(){		
		return getStringValue(KEY_EVENT_DETAIL);
	}
    
    /*
     * jobId (String)
     */
    public String getJobId() {
        return getStringValue(KEY_JOB_ID);
    }

    /*
     * jobStatus (String)
     */
    public String getJobStatus() {
        return getStringValue(KEY_JOB_STATUS);
    }

    /*
     * jobStatusReasons (Array[String])
     */
    public List<String> getJobStatusReasons() {
        return getArrayValue(KEY_JOB_STATUS_REASONS);
    }

    /*
     * jobStatusDetails (Array[Object])
     * @since SmartSDK V1.02
     */
    public JobStatusDetailsArray getJobStatusDetails() {
        List<Map<String, Object>> value = getArrayValue(KEY_JOB_STATUS_DETAILS);
        if (value == null) {
            return null;
        }
        return new JobStatusDetailsArray(value);
    }

    /*
     * scanningInfo (Object)
     */
    public ScanningInfo getScanningInfo() {
        Map<String, Object> value = getObjectValue(KEY_SCANNING_INFO);
        if (value == null) {
            return null;
        }
        return new ScanningInfo(value);
    }

    /*
     * printingInfo (Object)
     * @since SmartSDK V1.02
     */
    public PrintingInfo getPrintingInfo() {
        Map<String, Object> value = getObjectValue(KEY_PRINTING_INFO);
        if (value == null) {
            return null;
        }
        return new PrintingInfo(value);
    }

    /*
     * storedFileId (String)
     * @since SmartSDK V1.02
     */
    public String getStoredFileId() {
        return getStringValue(KEY_STORED_FILE_ID);
    }

    /*
     * validateOnly (Boolean)
     */
    public Boolean getValidateOnly() {
        return getBooleanValue(KEY_VALIDATE_ONLY);
    }

    /*
     * jobSetting (Object)
     */
    public JobSetting getJobSetting() {
        Map<String, Object> value = getObjectValue(KEY_JOB_SETTING);
        if (value == null) {
            return null;
        }
        return new JobSetting(value);
    }

    /*
     * @since SmartSDK V1.02
     */
    public static class JobStatusDetailsArray extends ArrayElement<JobStatusDetails> {

        JobStatusDetailsArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected JobStatusDetails createElement(Map<String, Object> values) {
            return new JobStatusDetails(values);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class JobStatusDetails extends Element {

        private static final String KEY_MESSAGE                 = "message";
        private static final String KEY_ADDITIONALINFO          = "additionalInfo";

        JobStatusDetails(Map<String, Object> values) {
            super(values);
        }

        /*
         * message (String)
         * @since SmartSDK V1.02
         */
        public String getMessage() {
            return getStringValue(KEY_MESSAGE);
        }

        /*
         * additionalInfo (Array[Object])
         * @since SmartSDK V1.02
         */
        public AdditionalInfoArray getAdditionalInfo() {
            List<Map<String, Object>> value = getArrayValue(KEY_ADDITIONALINFO);
            if (value == null) {
                return null;
            }
            return new AdditionalInfoArray(value);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class AdditionalInfoArray extends ArrayElement<AdditionalInfo> {

        AdditionalInfoArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected AdditionalInfo createElement(Map<String, Object> values) {
            return new AdditionalInfo(values);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class AdditionalInfo extends Element {

        private static final String KEY_ID                      = "id";
        private static final String KEY_VALUE                   = "value";

        AdditionalInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * id (String)
         * @since SmartSDK V1.02
         */
        public String getId() {
            return getStringValue(KEY_ID);
        }

        /*
         * value (String)
         * @since SmartSDK V1.02
         */
        public String getValue() {
            return getStringValue(KEY_VALUE);
        }

    }


    public static class ScanningInfo extends Element {

        private static final String KEY_JOB_STATUS              = "jobStatus";
        private static final String KEY_JOB_STATUS_REASONS      = "jobStatusReasons";
        private static final String KEY_SCANNED_COUNT           = "scannedCount";
        private static final String KEY_REMAINING_TIME_OF_WAITING_NEXT_ORIGINAL = "remainingTimeOfWaitingNextOriginal";
        private static final String KEY_SCANNED_THUMBNAIL_URI   = "scannedThumbnailUri";

        ScanningInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * jobStatus (String)
         */
        public String getJobStatus() {
            return getStringValue(KEY_JOB_STATUS);
        }

        /*
         * jobStatusReasons (Array[String])
         */
        public List<String> getJobStatusReasons() {
            return getArrayValue(KEY_JOB_STATUS_REASONS);
        }

        /*
         * scannedCount (Number)
         */
        public Integer getScannedCount() {
            return getNumberValue(KEY_SCANNED_COUNT);
        }

        /*
         * remainingTimeOfWaitingNextOriginal (Number)
         */
        public Integer getRemainingTimeOfWaitingNextOriginal() {
            return getNumberValue(KEY_REMAINING_TIME_OF_WAITING_NEXT_ORIGINAL);
        }

        /*
         * scannedThumbnailUri (String)
         */
        public String getScannedThumbnailUri() {
            return getStringValue(KEY_SCANNED_THUMBNAIL_URI);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class PrintingInfo extends Element {

        private static final String KEY_JOB_STATUS              = "jobStatus";
        private static final String KEY_JOB_STATUS_REASONS      = "jobStatusReasons";

        PrintingInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * jobStatus (String)
         * @since SmartSDK V1.02
         */
        public String getJobStatus() {
            return getStringValue(KEY_JOB_STATUS);
        }

        /*
         * jobStatusReasons (Array[String])
         * @since SmartSDK V1.02
         */
        public List<String> getJobStatusReasons() {
            return getArrayValue(KEY_JOB_STATUS_REASONS);
        }

    }

}
