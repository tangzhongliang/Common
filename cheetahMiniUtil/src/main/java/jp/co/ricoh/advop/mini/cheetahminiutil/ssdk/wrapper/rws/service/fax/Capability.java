/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.fax;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ArrayElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.CapabilityElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.DateElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.MaxLengthElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RangeElement;

public class Capability extends CapabilityElement  {

    private static final String KEY_AUTO_CORRECT_JOB_SETTING_LIST           = "autoCorrectJobSettingList";
    private static final String KEY_JOB_MODE_LIST                           = "jobModeList";
    private static final String KEY_JOB_STOPPED_TIMEOUT_PERIOD_RANGE        = "jobStoppedTimeoutPeriodRange";       // SmartSDK V1.02
    private static final String KEY_ORIGINAL_SIZE_LIST                      = "originalSizeList";
    private static final String KEY_ORIGINAL_USER_SIZE_LIST                 = "originalUserSizeList";
    private static final String KEY_SCAN_STAMP_LIST                         = "scanStampList";
    private static final String KEY_ORIGINAL_SIDE_LIST                      = "originalSideList";
    private static final String KEY_ORIGINAL_ORIENTATION_LIST               = "originalOrientationList";
    private static final String KEY_ORIGINAL_PREVIEW_LIST                   = "originalPreviewList";
    private static final String KEY_ORIGINAL_TYPE_LIST                      = "originalTypeList";    
    private static final String KEY_FAX_RESOLUTION_LIST                     = "faxResolutionList";
    private static final String KEY_AUTO_DENSITY_LIST                       = "autoDensityList";
    private static final String KEY_MANUAL_DENSITY_RANGE                    = "manualDensityRange";
    private static final String KEY_EMAIL_SETTING_CAPABILITY                = "emailSettingCapability";
    private static final String KEY_FAX_SETTING_CAPABILITY                  = "faxSettingCapability";
    private static final String KEY_DESTINATION_SETTING_CAPABILITY          = "destinationSettingCapability";
    private static final String KEY_FAX_INFO_CAPABILITY                     = "faxInfoCapability";
    private static final String KEY_STORE_LOCAL_SETTING_CAPABILITY          = "storeLocalSettingCapability";        // SmartSDK V1.02
    private static final String KEY_SEND_STORED_FILE_SETTING_CAPABILITY     = "sendStoredFileSettingCapability";    // SmartSDK V1.02
    private static final String KEY_PRINT_STORED_FILE_SETTING_CAPABILITY    = "printStoredFileSettingCapability";   // SmartSDK V1.02
    
    Capability(Map<String, Object> values) {
        super(values);
    }
    
    /*
     * autoCorrectJobSettingList (Array[Boolean])
     */
    public List<Boolean> getAutoCorrectJobSettingList() {
        return getArrayValue(KEY_AUTO_CORRECT_JOB_SETTING_LIST);
    }

    /*
     * jobModeList (Array[String])
     */
    public List<String> getJobModeList() {
        return getArrayValue(KEY_JOB_MODE_LIST);
    }

    /*
     * jobStoppedTimeoutPeriodRange (Range)
     * @since SmartSDK V1.02
     */
    public RangeElement getJobStoppedTimeoutPeriodRange() {
        return getRangeValue(KEY_JOB_STOPPED_TIMEOUT_PERIOD_RANGE);
    }

    /*
     * originalSizeList (Array[String])
     */
    public List<String> getOriginalSizeList() {
        return getArrayValue(KEY_ORIGINAL_SIZE_LIST);
    }

    /*
     * originalUserSizeList (Array[Object])
     */
    public OriginalUserSizeList getOriginalUserSizeList() {
        List<Map<String, Object>> mapArray = getArrayValue(KEY_ORIGINAL_USER_SIZE_LIST);
        if (mapArray == null) {
            return null;
        }
        return new OriginalUserSizeList(mapArray);
    }

    /*
     * scanStampList (Array[Boolean])
     */
    public List<Boolean> getScanStampList() {
        return getArrayValue(KEY_SCAN_STAMP_LIST);
    }

    /*
     * originalSideList (Array[String])
     */
    public List<String> getOriginalSideList() {
        return getArrayValue(KEY_ORIGINAL_SIDE_LIST);
    }

    /*
     * originalOrientationList (Array[String])
     */
    public List<String> getOriginalOrientationList() {
        return getArrayValue(KEY_ORIGINAL_ORIENTATION_LIST);
    }

    /*
     * originalPreviewList (Array[Boolean])
     */
    public List<Boolean> getOriginalPreviewList() {
        return getArrayValue(KEY_ORIGINAL_PREVIEW_LIST);
    }

    /*
     * originalTypeList (Array[String])
     */
    public List<String> getOriginalTypeList() {
        return getArrayValue(KEY_ORIGINAL_TYPE_LIST);
    }

    /*
     * faxResolutionList (Array[String])
     */
    public List<String> getFaxResolutionList() {
        return getArrayValue(KEY_FAX_RESOLUTION_LIST);
    }

    /*
     * autoDensityList (Array[Boolean])
     */
    public List<Boolean> getAutoDensityList() {
        return getArrayValue(KEY_AUTO_DENSITY_LIST);
    }

    /*
     * manualDensityRange (Range)
     */
    public RangeElement getManualDensityRange() {
        return getRangeValue(KEY_MANUAL_DENSITY_RANGE);
    }

    /*
     * emailSettingCapability (Object)
     */
    public EmailSettingCapability getEmailSettingCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_EMAIL_SETTING_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new EmailSettingCapability(mapValue);
    }

    /*
     * faxSettingCapability (Object)
     */
    public FaxSettingCapability getFaxSettingCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_FAX_SETTING_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new FaxSettingCapability(mapValue);
    }

    /*
     * destinationSettingCapability (Object)
     */
    public DestinationSettingCapability getDestinationSettingCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_DESTINATION_SETTING_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new DestinationSettingCapability(mapValue);
    }

    /*
     * faxInfoCapability (Object)
     */
    public FaxInfoCapability getFaxInfoCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_FAX_INFO_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new FaxInfoCapability(mapValue);
    }
    
    /*
     * storeLocalSettingCapability (Object)
     * @since SmartSDK V1.02
     */
    public StoreLocalSettingCapability getStoreLocalSettingCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_STORE_LOCAL_SETTING_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new StoreLocalSettingCapability(mapValue);
    }

    /*
     * sendStoredFileSettingCapability (Object)
     * @since SmartSDK V1.02
     */
    public SendStoredFileSettingCapability getSendStoredFileSettingCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_SEND_STORED_FILE_SETTING_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new SendStoredFileSettingCapability(mapValue);
    }

    /*
     * printStoredFileSettingCapability (Object)
     * @since SmartSDK V1.02
     */
    public PrintStoredFileSettingCapability getPrintStoredFileSettingCapability() {
        Map<String, Object> mapValue = getObjectValue(KEY_PRINT_STORED_FILE_SETTING_CAPABILITY);
        if (mapValue == null) {
            return null;
        }
        return new PrintStoredFileSettingCapability(mapValue);
    }

    public static class OriginalUserSizeList extends ArrayElement<OriginalUserSize>{
        protected OriginalUserSizeList(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected OriginalUserSize createElement(Map<String, Object> values) {
            return new OriginalUserSize(values);
        }
    }

    public static class OriginalUserSize extends CapabilityElement {

        private static final String KEY_ID          = "id";
        private static final String KEY_SIZE_X      = "sizeX";
        private static final String KEY_SIZE_Y      = "sizeY";
        private static final String KEY_UNIT        = "unit"; // SmartSDK V2.00

        OriginalUserSize(Map<String, Object> values) {
            super(values);
        }

        /*
         * id (String)
         */
        public String getId() {
            return getStringValue(KEY_ID);
        }

        /*
         * sizeX (String)
         */
        public String getSizeX() {
            return getStringValue(KEY_SIZE_X);
        }

        /*
         * sizeY (String)
         */
        public String getSizeY() {
            return getStringValue(KEY_SIZE_Y);
        }

        /*
         * unit (String)
         * @since SmartSDK V2.00
         */
        public String getUnit() {
        	return getStringValue(KEY_UNIT);
        }

    }

    public static class EmailSettingCapability extends CapabilityElement {

        private static final String KEY_SUBJECT_LENGTH          		= "subjectLength";   
        private static final String KEY_BODY_LENGTH             		= "bodyLength";
        private static final String KEY_ADMIN_ADDRESS_AS_SENDER 		= "adminAddresAsSender";  
        private static final String KEY_SMIME_SIGNATURE_LIST    		= "smimeSignatureList";
        private static final String KEY_SMIME_ENCRYPTION_LIST   		= "smimeEncryptionList";

        EmailSettingCapability(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * subjectLength (MaxLength)
         */
        public MaxLengthElement getSubjectLength() {
            return getMaxLengthValue(KEY_SUBJECT_LENGTH);
        }

        /*
         * bodyLength (MaxLength)
         */
        public MaxLengthElement getBodyLength() {
            return getMaxLengthValue(KEY_BODY_LENGTH);
        }
        
        /*
         * adminAddresAsSender (Boolean)
         */
        public Boolean getAdminAddresAsSender() {
            return getBooleanValue(KEY_ADMIN_ADDRESS_AS_SENDER);
        }

        /*
         * smimeSignatureList (Array[Boolean])
         */
        public List<Boolean> getSmimeSignatureList() {
            return getArrayValue(KEY_SMIME_SIGNATURE_LIST);
        }

        /*
         * smimeEncryptionList (Array[Boolean])
         */
        public List<Boolean> getSmimeEncryptionList() {
            return getArrayValue(KEY_SMIME_ENCRYPTION_LIST);
        }

    }

    public static class FaxSettingCapability extends CapabilityElement {

        private static final String KEY_SEND_LATER_LIST             					= "sendLaterList";	
        private static final String KEY_SEND_LATER_TIME_FORMAT      					= "sendLaterTimeFormat";
        private static final String KEY_STANDARD_MESSAGE_LIST       					= "standardMessageList";
        private static final String KEY_STANDARD_MESSAGE_TEXT_LIST  					= "standardMessageTextList";
        private static final String KEY_AUTO_REDUCE_LIST            					= "autoReduceList";
        private static final String KEY_LABEL_INSERTION_LIST        					= "labelInsertionList";
        private static final String KEY_CLOSED_NETWORK_LIST         					= "closedNetworkList";
        private static final String KEY_FAX_HEADER_PRINT_LIST       					= "faxHeaderPrintList";
        private static final String KEY_FAX_HEADER_PRINT_TEXT_LIST  					= "faxHeaderPrintTextList";
        private static final String KEY_SENDER_ENTRY_ID_LENGTH      					= "senderEntryIdLength";
        private static final String KEY_STAMP_SENDER_NAME_LIST      					= "stampSenderNameList";
        private static final String KEY_EMAIL_SEND_RESULT_LIST      					= "emailSendResultList";
        private static final String KEY_SUB_CODE_TRANSMISSION_LIST  					= "subCodeTransmissionList";       
        private static final String KEY_MANUAL_PRINT_LIST               				= "manualPrintList"; // SmartSDK V1.02
        private static final String KEY_PRINT_ON_TWO_SIDES_LIST         				= "printOnTwoSidesList";// SmartSDK V1.02
        private static final String KEY_DELETE_FILE_AFTER_PRINTING_LIST 				= "deleteFileAfterPrintingList";// SmartSDK V1.02

        FaxSettingCapability(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * sendLaterList (Array[Boolean])
         */
        public List<Boolean> getSendLaterList() {
            return getArrayValue(KEY_SEND_LATER_LIST);
        }

        /*
         * sendLaterTimeFormat (Date)
         */
        public DateElement getSendLaterTimeFormat() {
            return getDateValue(KEY_SEND_LATER_TIME_FORMAT);
        }

        /*
         * standardMessageList (Array[String])
         */
        public List<String> getStandardMessageList() {
            return getArrayValue(KEY_STANDARD_MESSAGE_LIST);
        }

        /*
         * standardMessageTextList (Array[Object])
         */
        public StandardMessageTextList getStandardMessageTextList() {
            List<Map<String, Object>> mapArray = getArrayValue(KEY_STANDARD_MESSAGE_TEXT_LIST);
            if (mapArray == null) {
                return null;
            }
            return new StandardMessageTextList(mapArray);
        }

        /*
         * autoReduceList (Array[Boolean])
         */
        public List<Boolean> getAutoReduceList() {
            return getArrayValue(KEY_AUTO_REDUCE_LIST);
        }

        /*
         * labelInsertionList (Array[Boolean])
         */
        public List<Boolean> getLabelInsertionList() {
            return getArrayValue(KEY_LABEL_INSERTION_LIST);
        }

        /*
         * closedNetworkList (Array[Boolean])
         */
        public List<Boolean> getClosedNetworkList() {
            return getArrayValue(KEY_CLOSED_NETWORK_LIST);
        }

        /*
         * faxHeaderPrintList (Array[String])
         */
        public List<String> getFaxHeaderPrintList() {
            return getArrayValue(KEY_FAX_HEADER_PRINT_LIST);
        }

        /*
         * faxHeaderPrintTextList (Array[Object])
         */
        public FaxHeaderPrintTextList getFaxHeaderPrintTextList() {
            List<Map<String, Object>> mapArray = getArrayValue(KEY_FAX_HEADER_PRINT_TEXT_LIST);
            if (mapArray == null) {
                return null;
            }
            return new FaxHeaderPrintTextList(mapArray);
        }

        /*
         * senderEntryIdLength (MaxLength)
         */
        public MaxLengthElement getSenderEntryIdLength() {
            return getMaxLengthValue(KEY_SENDER_ENTRY_ID_LENGTH);
        }

        /*
         * stampSenderNameList (Array[Boolean])
         */
        public List<Boolean> getStampSenderNameList() {
            return getArrayValue(KEY_STAMP_SENDER_NAME_LIST);
        }

        /*
         * emailSendResultList (Array[Boolean])
         */
        public List<Boolean> getEmailSendResultList() {
            return getArrayValue(KEY_EMAIL_SEND_RESULT_LIST);
        }

        /*
         * subCodeTransmissionList (Array[Boolean])
         */
        public List<Boolean> getSubCodeTransmissionList() {
            return getArrayValue(KEY_SUB_CODE_TRANSMISSION_LIST);
        }
        
        /*
         * manualPrintList (Array[String])
         * @since SmartSDK V1.02
         */
        public List<String> getManualPrintList() {
            return getArrayValue(KEY_MANUAL_PRINT_LIST);
        }

        /*
         * printOnTwoSidesList (Array[Boolean])
         * @since SmartSDK V1.02
         */
        public List<Boolean> getPrintOnTwoSidesList() {
            return getArrayValue(KEY_PRINT_ON_TWO_SIDES_LIST);
        }

        /*
         * deleteFileAfterPrintingList (Array[Boolean])
         * @since SmartSDK V1.02
         */
        public List<Boolean> getDeleteFileAfterPrintingList() {
            return getArrayValue(KEY_DELETE_FILE_AFTER_PRINTING_LIST);
        }

    }
    
    public static class StandardMessageTextList extends ArrayElement<StandardMessageText>{

        StandardMessageTextList(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected StandardMessageText createElement(Map<String, Object> values) {
            return new StandardMessageText(values);
        }

    }

    public static class StandardMessageText extends CapabilityElement {

        private static final String KEY_ID          = "id";
        private static final String KEY_TEXT        = "text";

        StandardMessageText(Map<String, Object> values) {
            super(values);
        }

        /*
         * id (String)
         */
        public String getId() {
            return getStringValue(KEY_ID);
        }

        /*
         * text (String)
         */
        public String getText() {
            return getStringValue(KEY_TEXT);
        }

    }

    public static class FaxHeaderPrintTextList extends ArrayElement<FaxHeaderPrint> {

        FaxHeaderPrintTextList(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected FaxHeaderPrint createElement(Map<String, Object> values) {
            return new FaxHeaderPrint(values);
        }

    }

    public static class FaxHeaderPrint extends CapabilityElement {

        private static final String KEY_ID          = "id";
        private static final String KEY_NAME        = "name";

        public FaxHeaderPrint(Map<String, Object> values) {
            super(values);
        }

        /*
         * id (String)
         */
        public String getId() {
            return getStringValue(KEY_ID);
        }

        /*
         * name (String)
         */
        public String getName() {
            return getStringValue(KEY_NAME);
        }

    }

    public static class DestinationSettingCapability extends CapabilityElement {

        private static final String KEY_DESTINATION_TYPE_LIST                       = "destinationTypeList";
        private static final String KEY_ADDRESSBOOK_DESTINATION_SETTING_CAPABILITY  = "addressbookDestinationSettingCapability";
        private static final String KEY_MANUAL_DESTINATION_SETTING_CAPABILITY       = "manualDestinationSettingCapability";
        private static final String KEY_MAX_BROADCAST_NUMBER                        = "maxBroadcastNumber";

        DestinationSettingCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * destinationTypeList (Array[String])
         */
        public List<String> getDestinationTypeList() {
            return getArrayValue(KEY_DESTINATION_TYPE_LIST);
        }

        /*
         * addressbookDestinationSettingCapability (Object)
         */
        public AddressbookDestinationSettingCapability getAddressbookDestinationSettingCapability() {
            Map<String, Object> mapValue = getObjectValue(KEY_ADDRESSBOOK_DESTINATION_SETTING_CAPABILITY);
            if (mapValue == null) {
                return null;
            }
            return new AddressbookDestinationSettingCapability(mapValue);
        }

        /*
         * manualDestinationSettingCapability (Object)
         */
        public ManualDestinationSettingCapability getManualDestinationSettingCapability() {
            Map<String, Object> mapValue = getObjectValue(KEY_MANUAL_DESTINATION_SETTING_CAPABILITY);
            if (mapValue == null) {
                return null;
            }
            return new ManualDestinationSettingCapability(mapValue);
        }

        /*
         * maxBroadcastNumber (Object)
         */
        public MaxBroadcastNumber getMaxBroadcastNumber() {
            Map<String, Object> mapValue = getObjectValue(KEY_MAX_BROADCAST_NUMBER);
            if (mapValue == null) {
                return null;
            }
            return new MaxBroadcastNumber(mapValue);
        }

    }

    public static class AddressbookDestinationSettingCapability extends CapabilityElement {

        private static final String KEY_DESTINATION_KIND_LIST   = "destinationKindList";
        private static final String KEY_ENTRY_ID_LENGTH         = "entryIdLength";
        private static final String KEY_REGISTRATION_NO_RANGE   = "registrationNoRange";
        private static final String KEY_MAIL_TO_CC_BCC_LIST     = "mailToCcBccList";

        AddressbookDestinationSettingCapability(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * destinationKindList (Array[String])
         */
        public List<String> getDestinationKindList() {
            return getArrayValue(KEY_DESTINATION_KIND_LIST);
        }

        /*
         * entryIdLength (MaxLength)
         */
        public MaxLengthElement getEntryIdLength() {
            return getMaxLengthValue(KEY_ENTRY_ID_LENGTH);
        }

        /*
         * registrationNoRange (Range)
         */
        public RangeElement getRegistrationNoRange() {
            return getRangeValue(KEY_REGISTRATION_NO_RANGE);
        }

        /*
         * mailToCcBccList (Array[String])
         */
        public List<String> getMailToCcBccList() {
            return getArrayValue(KEY_MAIL_TO_CC_BCC_LIST);
        }

    }

    public static class ManualDestinationSettingCapability extends CapabilityElement {

        private static final String KEY_DESTINATION_KIND_LIST           = "destinationKindList";        
        private static final String KEY_FAX_ADDRESS_INFO_CAPABILITY     = "faxAddressInfoCapability";
        private static final String KEY_MAIL_ADDRESS_INFO_CAPABILITY    = "mailAddressInfoCapability";

        ManualDestinationSettingCapability(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * destinationKindList (Array[String])
         */
        public List<String> getDestinationKindList() {
            return getArrayValue(KEY_DESTINATION_KIND_LIST);
        }

        /*
         * faxAddressInfoCapability (Object)
         */
        public FaxAddressInfoCapability getFaxAddressInfoCapability() {
            Map<String, Object> mapValue = getObjectValue(KEY_FAX_ADDRESS_INFO_CAPABILITY);
            if (mapValue == null) {
                return null;
            }
            return new FaxAddressInfoCapability(mapValue);
        }

        /*
         * mailAddressInfoCapability (Object)
         */
        public MailAddressInfoCapability getMailAddressInfoCapability() {
            Map<String, Object> mapValue = getObjectValue(KEY_MAIL_ADDRESS_INFO_CAPABILITY);
            if (mapValue == null) {
                return null;
            }
            return new MailAddressInfoCapability(mapValue);
        }

    }

    public static class FaxAddressInfoCapability extends CapabilityElement {

        private static final String KEY_FAX_NUMBER_LENGTH       = "faxNumberLength";
        private static final String KEY_SUB_CODE_LENGTH         = "subCodeLength";
        private static final String KEY_SID_PASSWORD_LENGTH     = "sidPasswordLength";
        private static final String KEY_SEP_CODE_LENGTH         = "sepCodeLength";
        private static final String KEY_PWD_PASSWORD_LENGTH     = "pwdPasswordLength";        
        private static final String KEY_LINE_LIST               = "lineList";
        private static final String KEY_RE_ENTER_COUNT          = "reEnterCount";
        private static final String KEY_USABLE_CHARACTER_LIST   = "usableCharacterList";

        FaxAddressInfoCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * faxNumberLength (MaxLength)
         */
        public MaxLengthElement getFaxNumberLength() {
            return getMaxLengthValue(KEY_FAX_NUMBER_LENGTH);
        }

        /*
         * subCodeLength (MaxLength)
         */
        public MaxLengthElement getSubCodeLength() {
            return getMaxLengthValue(KEY_SUB_CODE_LENGTH);
        }

        /*
         * sidPasswordLength (MaxLength)
         */
        public MaxLengthElement getSidPasswordLength() {
            return getMaxLengthValue(KEY_SID_PASSWORD_LENGTH);
        }

        /*
         * sepCodeLength (MaxLength)
         */
        public MaxLengthElement getSepCodeLength() {
            return getMaxLengthValue(KEY_SEP_CODE_LENGTH);
        }

        /*
         * pwdPasswordLength (MaxLength)
         */
        public MaxLengthElement getPwdPasswordLength() {
            return getMaxLengthValue(KEY_PWD_PASSWORD_LENGTH);
        }

        /*
         * lineList (Array[String])
         */
        public List<String> getLineList() {
            return getArrayValue(KEY_LINE_LIST);
        }

        /*
         * reEnterCount (Number)
         */
        public Integer getReEnterCount() {
            return getNumberValue(KEY_RE_ENTER_COUNT);
        }

        /*
         * usableCharacterList (Array[String])
         */
        public List<String> getUsableCharacterList() {
            return getArrayValue(KEY_USABLE_CHARACTER_LIST);
        }

    }

    public static class MailAddressInfoCapability extends CapabilityElement {

        private static final String KEY_MAIL_ADDRESS_LENGTH = "mailAddressLength";
        private static final String KEY_DIRECT_SMTP_LIST    = "directSmtpList";
        private static final String KEY_MAIL_TO_CC_BCC_LIST = "mailToCcBccList";

        MailAddressInfoCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * mailAddressLength (MaxLength)
         */
        public MaxLengthElement getMailAddressLength() {
            return getMaxLengthValue(KEY_MAIL_ADDRESS_LENGTH);
        }

        /*
         * directSmtpList (Array[Boolean])
         */
        public List<Boolean> getDirectSmtpList() {
            return getArrayValue(KEY_DIRECT_SMTP_LIST);
        }

        /*
         * mailToCcBccList (Array[String])
         */
        public List<String> getMailToCcBccList() {
            return getArrayValue(KEY_MAIL_TO_CC_BCC_LIST);
        }

    }

    public static class MaxBroadcastNumber extends CapabilityElement {

        private static final String KEY_MAIL            = "mail";
        private static final String KEY_MANUAL_MAIL     = "manualMail";
        private static final String KEY_FOLDER          = "folder";
        private static final String KEY_MANUAL_FOLDER   = "manualFolder";
        private static final String KEY_FAX             = "fax";
        private static final String KEY_MANUAL_FAX      = "manualFax";
        private static final String KEY_TOTAL           = "total";
        private static final String KEY_MANUAL_TOTAL    = "manualTotal";

        MaxBroadcastNumber(Map<String, Object> values) {
            super(values);
        }

        /*
         * mail (Number)
         */
        public Integer getMail() {
            return getNumberValue(KEY_MAIL);
        }

        /*
         * manualMail (Number)
         */
        public Integer getManualMail() {
            return getNumberValue(KEY_MANUAL_MAIL);
        }

        /*
         * folder (Number)
         */
        public Integer getFolder() {
            return getNumberValue(KEY_FOLDER);
        }

        /*
         * manualFolder (Number)
         */
        public Integer getManualFolder() {
            return getNumberValue(KEY_MANUAL_FOLDER);
        }

        /*
         * fax (Number)
         */
        public Integer getFax() {
            return getNumberValue(KEY_FAX);
        }

        /*
         * manualFax (Number)
         */
        public Integer getManualFax() {
            return getNumberValue(KEY_MANUAL_FAX);
        }

        /*
         * total (Number)
         */
        public Integer getTotal() {
            return getNumberValue(KEY_TOTAL);
        }

        /*
         * manualTotal (Number)
         */
        public Integer getManualTotal() {
            return getNumberValue(KEY_MANUAL_TOTAL);
        }

    }

    public static class FaxInfoCapability extends CapabilityElement {

        private static final String KEY_FAX_MACHINE_TYPE                		= "faxMachineType";
        private static final String KEY_TRANSMISSION_STANDBY_FILE_LIST  		= "transmissionStandbyFileList";
        private static final String KEY_COMMUNICATION_LOG_LIST          		= "communicationLogList";
        private static final String KEY_RECENT_DESTINATIONS            			= "recentDestinations";
        private static final String KEY_REMOTE_LINK_FAX_INFO_LIST       		= "remoteLinkFaxInfoList";
        private static final String KEY_RECONFIRM_DESTINATION           		= "reconfirmDestination";
        private static final String KEY_CONFIRM_ADD_DESTINATION         		= "confirmAddDestination";
        private static final String KEY_OUTSIDE_ACCESS_NUMBER_INFO_LIST 		= "outsideAccessNumberInfoList";
        private static final String KEY_TWOSIDE_AND_STAMP_EXCLUSION     		= "twoSidedAndStampExclusion";  //SmartSDK V2.00        

        FaxInfoCapability(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * faxMachineType (String)
         */
        public String getFaxMachineType() {
            return getStringValue(KEY_FAX_MACHINE_TYPE);
        }

        /*
         * transmissionStandbyFileList (Array[String])
         */
        public List<String> getTransmissionStandbyFileList() {
            return getArrayValue(KEY_TRANSMISSION_STANDBY_FILE_LIST);
        }

        /*
         * communicationLogList (Array[String])
         */
        public List<String> getCommunicationLogList() {
            return getArrayValue(KEY_COMMUNICATION_LOG_LIST);
        }

        /*
         * recentDestinations (Boolean)
         */
        public Boolean getRecentDestinations() {
            return getBooleanValue(KEY_RECENT_DESTINATIONS);
        }

        /*
         * remoteLinkFaxInfoList (Array[Object])
         */
        public RemoteLinkFaxInfoList getRemoteLinkFaxInfoList() {
            List<Map<String, Object>> mapArray = getArrayValue(KEY_REMOTE_LINK_FAX_INFO_LIST);
            if (mapArray == null) {
                return null;
            }
            return new RemoteLinkFaxInfoList(mapArray);
        }

        /*
         * reconfirmDestination (Boolean)
         */
        public Boolean getReconfirmDestination() {
            return getBooleanValue(KEY_RECONFIRM_DESTINATION);
        }

        /*
         * confirmAddDestination (Boolean)
         */
        public Boolean getConfirmAddDestination() {
            return getBooleanValue(KEY_CONFIRM_ADD_DESTINATION);
        }

        /*
         * outsideAccessNumberInfoList (Array[Object])
         */
        public OutsideAccessNumberInfoList getOutsideAccessNumberInfoList() {
            List<Map<String, Object>> mapArray = getArrayValue(KEY_OUTSIDE_ACCESS_NUMBER_INFO_LIST);
            if (mapArray == null) {
                return null;
            }
            return new OutsideAccessNumberInfoList(mapArray);
        }

        /*
         * twoSidedAndStampExclusion (Boolean)
         * @since SmartSDK V2.00
         */
        public Boolean getTwoSidedAndStampExclusion() {
        	return getBooleanValue(KEY_TWOSIDE_AND_STAMP_EXCLUSION);
        }

    }

    public static class RemoteLinkFaxInfoList extends ArrayElement<RemoteLinkFaxInfo> {

        RemoteLinkFaxInfoList(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected RemoteLinkFaxInfo createElement(Map<String, Object> values) {
            return new RemoteLinkFaxInfo(values);
        }
    }

    public static class RemoteLinkFaxInfo extends CapabilityElement {

        private static final String KEY_ID              = "id";
        private static final String KEY_NAME            = "name";
        private static final String KEY_LOCATION        = "location";
        private static final String KEY_HOST_ADDRESS    = "hostAddress";
        private static final String KEY_STATUS          = "status";

        RemoteLinkFaxInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * id (String)
         */
        public String getId() {
            return getStringValue(KEY_ID);
        }

        /*
         * name (String)
         */
        public String getName() {
            return getStringValue(KEY_NAME);
        }

        /*
         * location (String)
         */
        public String getLocation() {
            return getStringValue(KEY_LOCATION);
        }

        /*
         * hostAddress (String)
         */
        public String getHostAddress() {
            return getStringValue(KEY_HOST_ADDRESS);
        }

        /*
         * status (String)
         */
        public String getStatus() {
            return getStringValue(KEY_STATUS);
        }

    }

    public static class OutsideAccessNumberInfoList extends ArrayElement<OutsideAccessNumberInfo> {

        OutsideAccessNumberInfoList(List<Map<String, Object>> list) {
            super(list);
        }

        @Override
        protected OutsideAccessNumberInfo createElement(Map<String, Object> values) {
            return new OutsideAccessNumberInfo(values);
        }

    }

    public static class OutsideAccessNumberInfo extends CapabilityElement {

        private static final String KEY_ID                      = "id";
        private static final String KEY_OUTSIDE_ACCESS_NUMBER   = "outsideAccessNumber";
        private static final String KEY_AUTO_ADD                = "autoAdd";

        OutsideAccessNumberInfo(Map<String, Object> values) {
            super(values);
        }

        /*
         * id (String)
         */
        public String getId() {
            return getStringValue(KEY_ID);
        }

        /*
         * outsideAccessNumber (String)
         */
        public String getOutsideAccessNumber() {
            return getStringValue(KEY_OUTSIDE_ACCESS_NUMBER);
        }

        /*
         * autoAdd (Boolean)
         */
        public Boolean getAutoAdd() {
            return getBooleanValue(KEY_AUTO_ADD);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class StoreLocalSettingCapability extends CapabilityElement {

        private static final String KEY_FILE_NAME_LENGTH        = "fileNameLength";
        private static final String KEY_FILE_PASSWORD_LENGTH    = "filePasswordLength";
        private static final String KEY_USER_NAME_LENGTH        = "userNameLength";

        StoreLocalSettingCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * fileNameLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getFileNameLength() {
            return getMaxLengthValue(KEY_FILE_NAME_LENGTH);
        }

        /*
         * filePasswordLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getFilePasswordLength() {
            return getMaxLengthValue(KEY_FILE_PASSWORD_LENGTH);
        }

        /*
         * userNameLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getUserNameLength() {
            return getMaxLengthValue(KEY_USER_NAME_LENGTH);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class SendStoredFileSettingCapability extends CapabilityElement {

        private static final String KEY_STORED_FILE_INFO_CAPABILITY = "storedFileInfoCapability";
        private static final String KEY_SELECT_STORED_FILE_LIST     = "selectStoredFileList";

        SendStoredFileSettingCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * storedFileInfoCapability (Object)
         * @since SmartSDK V1.02
         */
        public SendStoredFileInfoCapability getStoredFileInfoCapability() {
            Map<String, Object> mapValue = getObjectValue(KEY_STORED_FILE_INFO_CAPABILITY);
            if (mapValue == null) {
                return null;
            }
            return new SendStoredFileInfoCapability(mapValue);
        }

        /*
         * selectStoredFileList (Array[String])
         * @since SmartSDK V1.02
         */
        public List<String> getSelectStoredFileList() {
            return getArrayValue(KEY_SELECT_STORED_FILE_LIST);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class SendStoredFileInfoCapability extends CapabilityElement {

        private static final String KEY_FILE_ID_LENGTH              = "fileIdLength";
        private static final String KEY_FILE_PASSWORD_LENGTH        = "filePasswordLength";
        private static final String KEY_MAX_SELECT_FILE_NUMBER      = "maxSelectFileNumber";
        private static final String KEY_MAX_NUMBER_OF_PAGES         = "maxNumberOfPages";

        SendStoredFileInfoCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * fileIdLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getFileIdLength() {
            return getMaxLengthValue(KEY_FILE_ID_LENGTH);
        }

        /*
         * filePasswordLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getFilePasswordLength() {
            return getMaxLengthValue(KEY_FILE_PASSWORD_LENGTH);
        }

        /*
         * maxSelectFileNumber (Number)
         * @since SmartSDK V1.02
         */
        public Integer getMaxSelectFileNumber() {
            return getNumberValue(KEY_MAX_SELECT_FILE_NUMBER);
        }

        /*
         * maxNumberOfPages (Number)
         * @since SmartSDK V1.02
         */
        public Integer getMaxNumberOfPages() {
            return getNumberValue(KEY_MAX_NUMBER_OF_PAGES);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class PrintStoredFileSettingCapability extends CapabilityElement {

        private static final String KEY_STORED_FILE_INFO_CAPABILITY = "storedFileInfoCapability";

        PrintStoredFileSettingCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * storedFileInfoCapability (Object)
         * @since SmartSDK V1.02
         */
        public PrintStoredFileInfoCapability getStoredFileInfoCapability() {
            Map<String, Object> mapValue = getObjectValue(KEY_STORED_FILE_INFO_CAPABILITY);
            if (mapValue == null) {
                return null;
            }
            return new PrintStoredFileInfoCapability(mapValue);
        }

    }

    /*
     * @since SmartSDK V1.02
     */
    public static class PrintStoredFileInfoCapability extends CapabilityElement {

        private static final String KEY_FILE_ID_LENGTH              = "fileIdLength";
        private static final String KEY_FILE_PASSWORD_LENGTH        = "filePasswordLength";
        private static final String KEY_MAX_SELECT_FILE_NUMBER      = "maxSelectFileNumber";
        private static final String KEY_MAX_NUMBER_OF_PAGES         = "maxNumberOfPages";

        PrintStoredFileInfoCapability(Map<String, Object> values) {
            super(values);
        }

        /*
         * fileIdLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getFileIdLength() {
            return getMaxLengthValue(KEY_FILE_ID_LENGTH);
        }

        /*
         * filePasswordLength (MaxLength)
         * @since SmartSDK V1.02
         */
        public MaxLengthElement getFilePasswordLength() {
            return getMaxLengthValue(KEY_FILE_PASSWORD_LENGTH);
        }

        /*
         * maxSelectFileNumber (Number)
         * @since SmartSDK V1.02
         */
        public Integer getMaxSelectFileNumber() {
            return getNumberValue(KEY_MAX_SELECT_FILE_NUMBER);
        }

        /*
         * maxNumberOfPages (Number)
         * @since SmartSDK V1.02
         */
        public Integer getMaxNumberOfPages() {
            return getNumberValue(KEY_MAX_NUMBER_OF_PAGES);
        }

    }
}
