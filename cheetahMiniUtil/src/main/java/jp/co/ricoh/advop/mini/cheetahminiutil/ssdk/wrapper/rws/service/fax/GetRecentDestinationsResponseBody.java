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

public class GetRecentDestinationsResponseBody extends Element implements ResponseBody {
    
    private static final String KEY_RECENT_DESTINATIONS_LIST    = "recentDestinationsLists";

    GetRecentDestinationsResponseBody(Map<String, Object> value) {
        super(value);
    }
    
    /*
     * recentDestinationsLists (Array[Object])
     */
    public RecentDestinationArray getRecentDestinationsLists() {
        List<Map<String, Object>> value = getArrayValue(KEY_RECENT_DESTINATIONS_LIST);
        if (value == null) {
            return null;
        }
        return new RecentDestinationArray(value);
    }


    public static class RecentDestinationArray extends ArrayElement<RecentDestination> {

        RecentDestinationArray(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected RecentDestination createElement(Map<String, Object> values) {
            return new RecentDestination(values);
        }
        
    }

    public static class RecentDestination extends Element {

        public static final String KEY_DESTINATION_KIND     = "destinationKind";
        public static final String KEY_TIME                 = "time";
        public static final String KEY_FAX                  = "fax";

        RecentDestination(Map<String, Object> values) {
            super(values);
        }

        /*
         * destinationKind (String)
         */
        public String getDestinationKind() {
            return getStringValue(KEY_DESTINATION_KIND);
        }

        /*
         * time (String)
         */
        public String getTime() {
            return getStringValue(KEY_TIME);
        }

        /*
         * fax (Object)
         */
        public Fax getFax() {
            Map<String, Object> mapValue = getObjectValue(KEY_FAX);
            if (mapValue == null) {
                return null;
            }
            return new Fax(mapValue);
        }

    }

    public static class Fax extends Element {

        public static final String KEY_FAX_NUMBER   = "faxNumber";
        public static final String KEY_LINE         = "line";

        public Fax(Map<String, Object> values) {
            super(values);
        }

        /*
         * faxNumber (String)
         */
        public String getFaxNumber() {
            return getStringValue(KEY_FAX_NUMBER);
        }

        /*
         * line (String)
         */
        public String getLine() {
            return getStringValue(KEY_LINE);
        }

    }

}
