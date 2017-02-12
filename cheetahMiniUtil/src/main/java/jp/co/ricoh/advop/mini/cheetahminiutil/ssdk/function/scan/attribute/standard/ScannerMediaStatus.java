/*
 *  Copyright (C) 2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.standard;

import java.util.HashMap;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.Conversions;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanServiceAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetScannerStatusResponseBody;

/*
 * メディア状態を表すクラスです。
 * The class indicates the media status.
 *
 * @since SmartSDK V2.00
 */
public final class ScannerMediaStatus implements ScanServiceAttribute {

    private final AttachStatus attachStatus;
    private final int remainingMedia;

    public static ScannerMediaStatus getInstance(GetScannerStatusResponseBody.MediaStatus status) {
        if (status == null) {
            return null;
        }
        return new ScannerMediaStatus(status);
    }

    ScannerMediaStatus(GetScannerStatusResponseBody.MediaStatus status) {
        attachStatus = AttachStatus.fromString(status.getAttachStatus());
        remainingMedia = Conversions.getIntValue(status.getRemainingMedia(), 0);
    }

    /**
     * 装着状態を取得します。
     * Obtain attachment status.
     * @return 装着状態
     *         Attachment status
     * @since SmartSDK V2.00
     */
    public AttachStatus getAttachStatus() {
        return attachStatus;
    }

    /**
     * メディアの残容量をKB単位で取得します。
     * 非装着の場合は0KBとなります。
     * Obtain the remaining amount of the media in KB.
     * If attachStatus is "no_attach", the value is 0KB.
     * @return メディア残量
     *         Remaining media amount
     * @since SmartSDK V2.00
     */
    public int getRemainingMedia() {
        return remainingMedia;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScannerMediaStatus)) {
            return false;
        }
        
        ScannerMediaStatus other = (ScannerMediaStatus) obj;
        if (!isEqual(attachStatus, other.attachStatus)) {
            return false;
        }
        if (remainingMedia != other.remainingMedia) {
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
        result = 31 * result + (attachStatus == null ? 0 : attachStatus.hashCode());
        result = 31 * result + remainingMedia;
        return result;
    }

    private volatile String cache = null;

    @Override
    public String toString() {
        if (cache == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("attachStatus:").append(attachStatus).append(", ");
            sb.append("remainingMedia:").append(remainingMedia);
            sb.append("}");
            cache = sb.toString();
        }
        return cache;
    }

    @Override
    public Class<?> getCategory() {
        return ScannerMediaStatus.class;
    }

    @Override
    public String getName() {
        return ScannerMediaStatus.class.getSimpleName();
    }


    /**
     * 装着状態を表す列挙型です。
     * The enum type to indicate the attachment status.
     * 
     * @since SmartSDK V2.00
     */
    enum AttachStatus {

        /**
         * 装着済み
         * Attached
         * 
         * @since SmartSDK V2.00
         */
        ATTACHED ("attached"),

        /**
         * 非装着
         * Not attached
         * 
         * @since SmartSDK V2.00
         */
        NO_ATTACH ("no_attach");


        private String status;

        private AttachStatus(String status){
            this.status = status;
        }

        private static volatile Map<String, AttachStatus> states = null;
        static {
            states = new HashMap<String, AttachStatus>();
            states.put("attached", ATTACHED);
            states.put("no_attach", NO_ATTACH);
        }

        /**
         * この列挙型に対応する文字列を返します。
         * Returns the string indicated by this enum type
         * @return
         */
        @Override
        public String toString(){
            return status;
        }

        public static AttachStatus fromString(String value){
            return states.get(value);
        }

    }

}
