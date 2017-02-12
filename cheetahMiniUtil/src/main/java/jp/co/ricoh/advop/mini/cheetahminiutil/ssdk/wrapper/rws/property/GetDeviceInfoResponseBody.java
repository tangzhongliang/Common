/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetDeviceInfoResponseBody extends Element implements ResponseBody {

    private static final String KEY_DEVICE_DESCRIPTION  = "deviceDescription";
    private static final String KEY_PLOTTER             = "plotter";
    private static final String KEY_SCANNER             = "scanner";

    GetDeviceInfoResponseBody(Map<String, Object> values) {
        super(values);
    }

    /*
     * deviceDescription (Object)
     */
    public DeviceDescription getDeviceDescription() {
        Map<String, Object> value = getObjectValue(KEY_DEVICE_DESCRIPTION);
        if (value == null) {
            return null;
        }
        return new DeviceDescription(value);
    }

    /*
     * plotter (Object)
     */
    public Plotter getPlotter() {
        Map<String, Object> value = getObjectValue(KEY_PLOTTER);
        if (value == null) {
            return null;
        }
        return new Plotter(value);
    }

    /*
     * scanner (Object)
     */
    public Scanner getScanner() {
        Map<String, Object> value = getObjectValue(KEY_SCANNER);
        if (value == null) {
            return null;
        }
        return new Scanner(value);
    }


    public static class DeviceDescription extends Element {

        private static final String KEY_SERIAL_NUMBER   = "serialNumber";
        private static final String KEY_DESTINATION     = "destination";
        private static final String KEY_LOCATION        = "location";
        private static final String KEY_DESCRIPTION     = "description";
        private static final String KEY_MODEL_NAME      = "modelName";
        private static final String KEY_VENDOR_NAME     = "vendorName";
        private static final String KEY_DEVICE_UUID     = "deviceUUID";
        private static final String KEY_PAPER_SIZE_TYPE = "paperSizeType";

        DeviceDescription(Map<String, Object> values) {
            super(values);
        }

        /*
         * serialNumber (String)
         */
        public String getSerialNumber() {
            return getStringValue(KEY_SERIAL_NUMBER);
        }

        /*
         * destination (String)
         */
        public String getDestination() {
            return getStringValue(KEY_DESTINATION);
        }

        /*
         * location (String)
         */
        public String getLocation() {
            return getStringValue(KEY_LOCATION);
        }

        /*
         * description (String)
         */
        public String getDescription() {
            return getStringValue(KEY_DESCRIPTION);
        }

        /*
         * modelName (String)
         */
        public String getModelName() {
            return getStringValue(KEY_MODEL_NAME);
        }

        /*
         * vendorName (String)
         */
        public String getVendorName() {
            return getStringValue(KEY_VENDOR_NAME);
        }

        /*
         * deviceUUID (String)
         */
        public String getDeviceUUID() {
            return getStringValue(KEY_DEVICE_UUID);
        }

        /*
         * paperSizeType (String)
         * @since SmartSDK V2.00
         */
        public String getPaperSizeType() {
            return getStringValue(KEY_PAPER_SIZE_TYPE);
        }

    }

    public static class Plotter extends Element {

        private static final String KEY_COLOR_SUPPORTED = "colorSupported";
        private static final String KEY_FEED_TRAY_INFO  = "feedTrayInfo";  // SmartSDK V2.00

        Plotter(Map<String, Object> values) {
            super(values);
        }

        /*
         * colorSupported (String)
         */
        public String getColorSupported() {
            return getStringValue(KEY_COLOR_SUPPORTED);
        }

        /*
         * feedTrayInfo (Object)
         * @since SmartSDK V2.00
         */
        public FeedTrayInfo getFeedTrayInfo() {
            Map<String, Object> value = getObjectValue(KEY_FEED_TRAY_INFO);
            if (value == null) {
                return null;
            }
            return new FeedTrayInfo(value);
        }

        /* 
         * @since SmartSDK V2.00
         */
        public static class FeedTrayInfo extends Element {

            private static final String KEY_CONNECTED_FEED_TRAY_CONFIG_INFO = "connectedFeedTrayConfigInfo";

            FeedTrayInfo(Map<String, Object> values) {
                super(values);
            }

            /*
             * connectedFeedTrayConfigInfo (Array[Object])
             * @since SmartSDK V2.00
             */
            public ConnectedFeedTrayConfigInfoArray getConnectedFeedTrayConfigInfo() {
                List<Map<String, Object>> value = getArrayValue(KEY_CONNECTED_FEED_TRAY_CONFIG_INFO);
                if (value == null) {
                    return null;
                }
                return new ConnectedFeedTrayConfigInfoArray(value);
            }

            /*
             * @since SmartSDK V2.00
             */
            public static class ConnectedFeedTrayConfigInfoArray extends ArrayElement<ConnectedFeedTrayConfigInfo> {

                protected ConnectedFeedTrayConfigInfoArray(
                        List<Map<String, Object>> list) {
                    super(list);
                }

                @Override
                protected ConnectedFeedTrayConfigInfo createElement(
                        Map<String, Object> values) {
                    return new ConnectedFeedTrayConfigInfo(values);
                }

            }

            /*
             * @since SmartSDK V2.00
             */
            public static class ConnectedFeedTrayConfigInfo extends Element {

                private static final String KEY_NAME = "name";
                private static final String KEY_KIND = "kind";

                ConnectedFeedTrayConfigInfo(Map<String, Object> values) {
                    super(values);
                }

                /*
                 * name (String)
                 * @since SmartSDK V2.00
                 */
                public String getName() {
                    return getStringValue(KEY_NAME);
                }

                /*
                 * kind (String)
                 * @since SmartSDK V2.00
                 */
                public String getKind() {
                    return getStringValue(KEY_KIND);
                }

            }
        }
    }

    public static class Scanner extends Element {

        private static final String KEY_COLOR_SUPPORTED = "colorSupported";

        Scanner(Map<String, Object> values) {
            super(values);
        }

        /*
         * colorSupported (String)
         */
        public String getColorSupported() {
            return getStringValue(KEY_COLOR_SUPPORTED);
        }

    }

}
