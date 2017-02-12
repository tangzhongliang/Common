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

public class GetFaxStatusResponseBody extends Element implements ResponseBody {

    private static final String KEY_FAX_STATUS                  = "faxStatus";
    private static final String KEY_FAX_STATUS_REASONS          = "faxStatusReasons";
    private static final String KEY_FAX_PRINT_STATUS            = "faxPrintStatus";        // SmartSDK V1.02
    private static final String KEY_FAX_PRINT_STATUS_REASONS    = "faxPrintStatusReasons"; // SmartSDK V1.02
    private static final String KEY_FAX_STATUS_DETAILS          = "faxStatusDetails";      // SmartSDK V1.02
    private static final String KEY_REMAINING_MEMORY            = "remainingMemory";
    private static final String KEY_OCCURED_ERROR_LEVEL         = "occuredErrorLevel";

    public GetFaxStatusResponseBody(Map<String, Object> values) {
        super(values);
    }

    /*
     * faxStatus (String)
     */
    public String getFaxStatus() {
        return getStringValue(KEY_FAX_STATUS);
    }

    /*
     * faxStatusReasons (Array[Object])
     */
    public FaxStatusReasonsArray getFaxStatusReasons() {
        List<Map<String, Object>> value = getArrayValue(KEY_FAX_STATUS_REASONS);
        if (value == null) {
            return null;
        }
        return new FaxStatusReasonsArray(value);
    }

    /*
     * faxPrintStatus (String)
     * @since SmartSDK V1.02
     */
    public String getFaxPrintStatus() {
        return getStringValue(KEY_FAX_PRINT_STATUS);
    }

    /*
     * faxPrintStatusReasons (Array[Object])
     * @since SmartSDK V1.02
     */
    public FaxPrintStatusReasonsArray getFaxPrintStatusReasons() {
        List<Map<String, Object>> value = getArrayValue(KEY_FAX_PRINT_STATUS_REASONS);
        if (value == null) {
            return null;
        }
        return new FaxPrintStatusReasonsArray(value);
    }

    /*
     * faxStatusDetails (Array[Object])
     * @since SmartSDK V1.02
     */
    public FaxStatusDetailsArray getFaxStatusDetails() {
        List<Map<String, Object>> value = getArrayValue(KEY_FAX_STATUS_DETAILS);
        if (value == null) {
            return null;
        }
        return new FaxStatusDetailsArray(value);
    }

    /*
     * remainingMemory (Number)
     */
    public Integer getRemainingMemory() {
        return getNumberValue(KEY_REMAINING_MEMORY);
    }
    
    /*
     * occuredErrorLevel (String)
     */
    public String getOccuredErrorLevel() {
        return getStringValue(KEY_OCCURED_ERROR_LEVEL);
    }
    
    
    public static class FaxStatusReasonsArray extends ArrayElement<FaxStatusReasons> {

        FaxStatusReasonsArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected FaxStatusReasons createElement(Map<String, Object> values) {
            return new FaxStatusReasons(values);
        }
        
    }
    
    public static class FaxStatusReasons extends Element {
        
        private static final String KEY_FAX_STATUS_REASON = "faxStatusReason";
        private static final String KEY_SEVERITY          = "severity";

        FaxStatusReasons(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * faxStatusReason (String)
         */
        public String getFaxStatusReason() {
            return getStringValue(KEY_FAX_STATUS_REASON);
        }
        
        /*
         * severity (String)
         */
        public String getSeverity() {
            return getStringValue(KEY_SEVERITY);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class FaxPrintStatusReasonsArray extends ArrayElement<FaxPrintStatusReasons> {

        FaxPrintStatusReasonsArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected FaxPrintStatusReasons createElement(Map<String, Object> values) {
            return new FaxPrintStatusReasons(values);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class FaxPrintStatusReasons extends Element {

        private static final String KEY_FAX_PRINT_STATUS_REASON = "faxPrintStatusReason";
        private static final String KEY_SEVERITY                = "severity";

        FaxPrintStatusReasons(Map<String, Object> values) {
            super(values);
        }

        /*
         * faxPrintStatusReason (String)
         * @since SmartSDK V1.02
         */
        public String getFaxPrintStatusReason() {
            return getStringValue(KEY_FAX_PRINT_STATUS_REASON);
        }

        /*
         * severity (String)
         * @since SmartSDK V1.02
         */
        public String getSeverity() {
            return getStringValue(KEY_SEVERITY);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class FaxStatusDetailsArray extends ArrayElement<FaxStatusDetails> {

        FaxStatusDetailsArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected FaxStatusDetails createElement(Map<String, Object> values) {
            return new FaxStatusDetails(values);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class FaxStatusDetails extends Element {

        private static final String KEY_MESSAGE                 = "message";
        private static final String KEY_ADDITIONALINFO          = "additionalInfo";
        private static final String KEY_ACTION                  = "action"; // SmartSDK V2.00

        FaxStatusDetails(Map<String, Object> values) {
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

        /*
         * action (Array[String])
         * @since SmartSDK V2.00
         */
        public List<String> getAction() {
        	return getArrayValue(KEY_ACTION);
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

}
