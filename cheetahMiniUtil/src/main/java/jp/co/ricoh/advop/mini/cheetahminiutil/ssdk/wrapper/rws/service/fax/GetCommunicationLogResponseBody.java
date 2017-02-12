/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetCommunicationLogResponseBody extends Element implements ResponseBody {

    private static final String KEY_COMMUNICATION_LISTS     = "communicationLists";
    
    GetCommunicationLogResponseBody(Map<String, Object> value) {
        super(value);
    }

    /*
     * communicationLists (Array[Object])
     */
    public CommunicationLogArray getCommunicationLists() {
        List<Map<String, Object>> value = getArrayValue(KEY_COMMUNICATION_LISTS);
        if (value == null) {
            return null;
        }
        return new CommunicationLogArray(value);
    }
    
    
    public static class CommunicationLogArray extends ArrayElement<CommunicationLog> {

        CommunicationLogArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected CommunicationLog createElement(Map<String, Object> values) {
            return new CommunicationLog(values);
        }
        
    }

    public static class CommunicationLog extends Element {

        private static final String KEY_COMMUNICATION_KIND  = "communicationKind";
        private static final String KEY_TIME                = "time";
        private static final String KEY_ADDRESS             = "address";
        private static final String KEY_USER_NAME           = "userName";
        private static final String KEY_MODE                = "mode";
        private static final String KEY_ADDRESS_MODE        = "addressMode";
        private static final String KEY_PAGE_COUNT          = "pageCount";
        private static final String KEY_ADDRESS_COUNT       = "addressCount";
        private static final String KEY_RESULT              = "result";
        private static final String KEY_DOCUMENT_NUMBER     = "documentNumber";

        CommunicationLog(Map<String, Object> values) {
            super(values);
        }

        /*
         * communicationKind (String)
         */
        public String getCommunicationKind() {
            return getStringValue(KEY_COMMUNICATION_KIND);
        }

        /*
         * time (String)
         */
        public String getTime() {
            return getStringValue(KEY_TIME);
        }

        /*
         * address (String)
         */
        public String getAddress() {
            return getStringValue(KEY_ADDRESS);
        }

        /*
         * userName (String)
         */
        public String getUserName() {
            return getStringValue(KEY_USER_NAME);
        }

        /*
         * mode (String)
         */
        public String getMode() {
            return getStringValue(KEY_MODE);
        }

        /*
         * addressMode (String)
         */
        public String getAddressMode() {
            return getStringValue(KEY_ADDRESS_MODE);
        }

        /*
         * pageCount (Int)
         */
        public Integer getPageCount() {
            return getNumberValue(KEY_PAGE_COUNT);
        }

        /*
         * addressCount (Int)
         */
        public Integer getAddressCount() {
            return getNumberValue(KEY_ADDRESS_COUNT);
        }

        /*
         * result (String)
         */
        public String getResult() {
            return getStringValue(KEY_RESULT);
        }

        /*
         * documentNumber (String)
         */
        public String getDocumentNumber() {
            return getStringValue(KEY_DOCUMENT_NUMBER);
        }

    }

}
