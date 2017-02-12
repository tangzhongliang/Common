/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.EncodedException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json.JsonUtils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.log.Logger;

import java.util.HashMap;
import java.util.Map;

/*
 * @since SmartSDK V2.12
 */
public class UpdateEmailRequestBody extends WritableElement implements RequestBody {

    private static final String CONTENT_TYPE_JSON               = "application/json; charset=utf-8";

	private static final String KEY_ADMINISTRATOR_EMAIL_ADDRESS = "administratorEmailAddress";
	private static final String KEY_RECEPTION                   = "reception";
	private static final String KEY_SMTP                        = "smtp";
	private static final String KEY_POP_BEFORE_SMTP             = "popBeforeSmtp";
	private static final String KEY_POP3_IMAP4                  = "pop3Imap4";
	private static final String KEY_EMAIL_COMMUNICATION_PORT    = "emailCommunicationPort";
	private static final String KEY_FAX_EMAIL_ACCOUNT           = "faxEmailAccount";
	private static final String KEY_EMAIL_NOTIFACATION_ACCOUNT  = "emailNotificationAccount";
	
    /**
     * Define the prefix for log information with abbreviation package and class name
     */
    private final static String PREFIX = "property:UpdateEmailReq:";
    
    public UpdateEmailRequestBody() {
        super(new HashMap<String, Object>());
    }
    public UpdateEmailRequestBody(Map<String, Object> values) {
        super(values);
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE_JSON;
    }

    @Override
    public String toEntityString() {
        try {
            return JsonUtils.getEncoder().encode(values);
        } catch (EncodedException e) {
            Logger.warn(Utils.getTagName(), PREFIX + e.toString());
            return "{}";
        }
    }

    
    /*
     * administratorEmailAddress (String)
     * @since SmartSDK V2.12
     */
    public String getAdministratorEmailAddress() {
    	return getStringValue(KEY_ADMINISTRATOR_EMAIL_ADDRESS);
    }
    public void setAdministratorEmailAddress(String value) {
    	setStringValue(KEY_ADMINISTRATOR_EMAIL_ADDRESS, value);
    }
    public String removeAdministratorEmailAddress() {
    	return removeStringValue(KEY_ADMINISTRATOR_EMAIL_ADDRESS);
    }
    
    /*
     * reception (Object)
     * @since SmartSDK V2.12
     */
    public Reception getReception() {
        Map<String, Object> value = getObjectValue(KEY_RECEPTION);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_RECEPTION, value);
        }
        return new Reception(value);
    }
    public Reception removeReception() {
        Map<String, Object> value = removeObjectValue(KEY_RECEPTION);
            if (value == null) {
            return null;
        }
        return new Reception(value);
    }   
    
    /*
     * smtp (Object)
     * @since SmartSDK V2.12
     */
    public Smtp getSmtp() {
        Map<String, Object> value = getObjectValue(KEY_SMTP);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_SMTP, value);
        }
        return new Smtp(value);
    }
    public Smtp removeSmtp() {
        Map<String, Object> value = removeObjectValue(KEY_SMTP);
            if (value == null) {
            return null;
        }
        return new Smtp(value);
    }   
    
    /*
     * popBeforeSmtp (Object)
     * @since SmartSDK V2.12
     */
    public PopBeforeSmtp getPopBeforeSmtp() {
        Map<String, Object> value = getObjectValue(KEY_POP_BEFORE_SMTP);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_POP_BEFORE_SMTP, value);
        }
        return new PopBeforeSmtp(value);
    }
    public PopBeforeSmtp removePopBeforeSmtp() {
        Map<String, Object> value = removeObjectValue(KEY_POP_BEFORE_SMTP);
            if (value == null) {
            return null;
        }
        return new PopBeforeSmtp(value);
    }   
    
    /*
     * pop3Imap4 (Object)
     * @since SmartSDK V2.12
     */
    public Pop3Imap4 getPop3Imap4() {
        Map<String, Object> value = getObjectValue(KEY_POP3_IMAP4);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_POP3_IMAP4, value);
        }
        return new Pop3Imap4(value);
    }
    public Pop3Imap4 removePop3Imap4() {
        Map<String, Object> value = removeObjectValue(KEY_POP3_IMAP4);
            if (value == null) {
            return null;
        }
        return new Pop3Imap4(value);
    }   
    
    /*
     * emailCommunicationPort (Object)
     * @since SmartSDK V2.12
     */
    public EmailCommunicationPort getEmailCommunicationPort() {
        Map<String, Object> value = getObjectValue(KEY_EMAIL_COMMUNICATION_PORT);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_EMAIL_COMMUNICATION_PORT, value);
        }
        return new EmailCommunicationPort(value);
    }
    public EmailCommunicationPort removeEmailCommunicationPort() {
        Map<String, Object> value = removeObjectValue(KEY_EMAIL_COMMUNICATION_PORT);
            if (value == null) {
            return null;
        }
        return new EmailCommunicationPort(value);
    }   
    
    /*
     * faxEmailAccount (Object)
     * @since SmartSDK V2.12
     */
    public FaxEmailAccount getFaxEmailAccount() {
        Map<String, Object> value = getObjectValue(KEY_FAX_EMAIL_ACCOUNT);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_FAX_EMAIL_ACCOUNT, value);
        }
        return new FaxEmailAccount(value);
    }
    public FaxEmailAccount removeFaxEmailAccount() {
        Map<String, Object> value = removeObjectValue(KEY_FAX_EMAIL_ACCOUNT);
            if (value == null) {
            return null;
        }
        return new FaxEmailAccount(value);
    }   
    
    /*
     * emailNotificationAccount (Object)
     * @since SmartSDK V2.12
     */
    public EmailNotificationAccount getEmailNotificationAccount() {
        Map<String, Object> value = getObjectValue(KEY_EMAIL_NOTIFACATION_ACCOUNT);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_EMAIL_NOTIFACATION_ACCOUNT, value);
        }
        return new EmailNotificationAccount(value);
    }
    public EmailNotificationAccount removeEmailNotificationAccount() {
        Map<String, Object> value = removeObjectValue(KEY_EMAIL_NOTIFACATION_ACCOUNT);
            if (value == null) {
            return null;
        }
        return new EmailNotificationAccount(value);
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class Reception extends WritableElement {

        private static final String KEY_RECEPTION_PROTOCOL                = "receptionProtocol";
        private static final String KEY_EMAIL_RECEPTION_INTERVAL          = "emailReceptionInterval";
        private static final String KEY_MAX_RECEPTION_EMAIL_SIZE          = "maxReceptionEmailSize";
        private static final String KEY_EMAIL_STORAGE_IN_SERVER           = "emailStorageInServer";
        
        Reception(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * receptionProtocol (String)
         */
        public String getReceptionProtocol() {
            return getStringValue(KEY_RECEPTION_PROTOCOL);
        }
        public void setReceptionProtocol(String value) {
            setStringValue(KEY_RECEPTION_PROTOCOL, value);
        }
        public String removeReceptionProtocol() {
            return removeStringValue(KEY_RECEPTION_PROTOCOL);
        }
        
        /*
         * emailReceptionInterval (Number)
         */
        public Integer getEmailReceptionInterval() {
            return getNumberValue(KEY_EMAIL_RECEPTION_INTERVAL);
        }
        public void setEmailReceptionInterval(Integer value) {
            setNumberValue(KEY_EMAIL_RECEPTION_INTERVAL, value);
        }
        public Integer removeEmailReceptionInterval() {
            return removeNumberValue(KEY_EMAIL_RECEPTION_INTERVAL);
        }
        
        /*
         * maxReceptionEmailSize (Number)
         */
        public Integer getMaxReceptionEmailSize() {
            return getNumberValue(KEY_MAX_RECEPTION_EMAIL_SIZE);
        }
        public void setMaxReceptionEmailSize(Integer value) {
            setNumberValue(KEY_MAX_RECEPTION_EMAIL_SIZE, value);
        }
        public Integer removeMaxReceptionEmailSize() {
            return removeNumberValue(KEY_MAX_RECEPTION_EMAIL_SIZE);
        }
        
        /*
         * emailStorageInServer (String)
         */
        public String getEmailStorageInServer() {
            return getStringValue(KEY_EMAIL_STORAGE_IN_SERVER);
        }
        public void setEmailStorageInServer(String value) {
            setStringValue(KEY_EMAIL_STORAGE_IN_SERVER, value);
        }
        public String removeEmailStorageInServer() {
            return removeStringValue(KEY_EMAIL_STORAGE_IN_SERVER);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class Smtp extends WritableElement {

        private static final String KEY_SMTP_SERVER_NAME                  = "smtpServerName";
        private static final String KEY_SMTP_PORT_NO                      = "smtpPortNo";
        private static final String KEY_USE_SECURE_CONNECTION             = "useSecureConnection";
        private static final String KEY_SMTP_AUTHENTICATION               = "smtpAuthentication";
        private static final String KEY_SMTP_AUTH_EMAIL_ADDRESS           = "smtpAuthEmailAddress";
        private static final String KEY_SMTP_AUTH_USER_NAME               = "smtpAuthUserName";
        private static final String KEY_SMTP_AUTH_ENCRYPTION              = "smtpAuthEncryption";
        private static final String KEY_SMTP_AUTH_PASSWORD                = "smtpAuthPassword";
        
        Smtp(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * smtpServerName (String)
         */
        public String getSmtpServerName() {
            return getStringValue(KEY_SMTP_SERVER_NAME);
        }
        public void setSmtpServerName(String value) {
            setStringValue(KEY_SMTP_SERVER_NAME, value);
        }
        public String removeSmtpServerName() {
            return removeStringValue(KEY_SMTP_SERVER_NAME);
        }
        
        /*
         * smtpPortNo (Number)
         */
        public Integer getSmtpPortNo() {
            return getNumberValue(KEY_SMTP_PORT_NO);
        }
        public void setSmtpPortNo(Integer value) {
            setNumberValue(KEY_SMTP_PORT_NO, value);
        }
        public Integer removeSmtpPortNo() {
            return removeNumberValue(KEY_SMTP_PORT_NO);
        }
        
        /*
         * useSecureConnection (String)
         */
        public String getUseSecureConnection() {
            return getStringValue(KEY_USE_SECURE_CONNECTION);
        }
        public void setUseSecureConnection(String value) {
            setStringValue(KEY_USE_SECURE_CONNECTION, value);
        }
        public String removeUseSecureConnection() {
            return removeStringValue(KEY_USE_SECURE_CONNECTION);
        }
        
        /*
         * smtpAuthentication (String)
         */
        public String getSmtpAuthentication() {
            return getStringValue(KEY_SMTP_AUTHENTICATION);
        }
        public void setSmtpAuthentication(String value) {
            setStringValue(KEY_SMTP_AUTHENTICATION, value);
        }
        public String removeSmtpAuthentication() {
            return removeStringValue(KEY_SMTP_AUTHENTICATION);
        }
        
        /*
         * smtpAuthEmailAddress (String)
         */
        public String getSmtpAuthEmailAddress() {
            return getStringValue(KEY_SMTP_AUTH_EMAIL_ADDRESS);
        }
        public void setSmtpAuthEmailAddress(String value) {
            setStringValue(KEY_SMTP_AUTH_EMAIL_ADDRESS, value);
        }
        public String removeSmtpAuthEmailAddress() {
            return removeStringValue(KEY_SMTP_AUTH_EMAIL_ADDRESS);
        }
        
        /*
         * smtpAuthUserName (String)
         */
        public String getSmtpAuthUserName() {
            return getStringValue(KEY_SMTP_AUTH_USER_NAME);
        }
        public void setSmtpAuthUserName(String value) {
            setStringValue(KEY_SMTP_AUTH_USER_NAME, value);
        }
        public String removeSmtpAuthUserName() {
            return removeStringValue(KEY_SMTP_AUTH_USER_NAME);
        }
        
        /*
         * smtpAuthEncryption (String)
         */
        public String getSmtpAuthEncryption() {
            return getStringValue(KEY_SMTP_AUTH_ENCRYPTION);
        }
        public void setSmtpAuthEncryption(String value) {
            setStringValue(KEY_SMTP_AUTH_ENCRYPTION, value);
        }
        public String removeSmtpAuthEncryption() {
            return removeStringValue(KEY_SMTP_AUTH_ENCRYPTION);
        }
        
        /*
         * smtpAuthPassword (String)
         */
        public String getSmtpAuthPassword() {
            return getStringValue(KEY_SMTP_AUTH_PASSWORD);
        }
        public void setSmtpAuthPassword(String value) {
            setStringValue(KEY_SMTP_AUTH_PASSWORD, value);
        }
        public String removeSmtpAuthPassword() {
            return removeStringValue(KEY_SMTP_AUTH_PASSWORD);
        }
    }

    /*
     * @since SmartSDK V2.12
     */
    public static class PopBeforeSmtp extends WritableElement {

        private static final String KEY_POP_AUTH_BEFORE_SMTP              = "popAuthBeforeSmtp";
        private static final String KEY_POP_EMAIL_ADDRESS                 = "popEmailAddress";
        private static final String KEY_POP_USER_NAME                     = "popUserName";
        private static final String KEY_POP_PASSWORD                      = "popPassword";
        private static final String KEY_TIMEOUT_SETTING_AFTER_POP_AUTH    = "timeoutSettingAfterPopAuth";
        
        PopBeforeSmtp(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * popAuthBeforeSmtp (String)
         */
        public String getPopAuthBeforeSmtp() {
            return getStringValue(KEY_POP_AUTH_BEFORE_SMTP);
        }
        public void setPopAuthBeforeSmtp(String value) {
            setStringValue(KEY_POP_AUTH_BEFORE_SMTP, value);
        }
        public String removePopAuthBeforeSmtp() {
            return removeStringValue(KEY_POP_AUTH_BEFORE_SMTP);
        }
        
        /*
         * popEmailAddress (String)
         */
        public String getPopEmailAddress() {
            return getStringValue(KEY_POP_EMAIL_ADDRESS);
        }
        public void setPopEmailAddress(String value) {
            setStringValue(KEY_POP_EMAIL_ADDRESS, value);
        }
        public String removePopEmailAddress() {
            return removeStringValue(KEY_POP_EMAIL_ADDRESS);
        }
        
        /*
         * popUserName (String)
         */
        public String getPopUserName() {
            return getStringValue(KEY_POP_USER_NAME);
        }
        public void setPopUserName(String value) {
            setStringValue(KEY_POP_USER_NAME, value);
        }
        public String removePopUserName() {
            return removeStringValue(KEY_POP_USER_NAME);
        }
        
        /*
         * popPassword (String)
         */
        public String getPopPassword() {
            return getStringValue(KEY_POP_PASSWORD);
        }
        public void setPopPassword(String value) {
            setStringValue(KEY_POP_PASSWORD, value);
        }
        public String removePopPassword() {
            return removeStringValue(KEY_POP_PASSWORD);
        }
        
        /*
         * timeoutSettingAfterPopAuth (Number)
         */
        public Integer getTimeoutSettingAfterPopAuth() {
            return getNumberValue(KEY_TIMEOUT_SETTING_AFTER_POP_AUTH);
        }
        public void setTimeoutSettingAfterPopAuth(Integer value) {
            setNumberValue(KEY_TIMEOUT_SETTING_AFTER_POP_AUTH, value);
        }
        public Integer removeTimeoutSettingAfterPopAuth() {
            return removeNumberValue(KEY_TIMEOUT_SETTING_AFTER_POP_AUTH);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class Pop3Imap4 extends WritableElement {

        private static final String KEY_POP3_IMAP4_SERVER_NAME            = "pop3Imap4ServerName";
        private static final String KEY_POP3_IMAP4_ENCRYPTION             = "pop3Imap4Encryption";
        
        Pop3Imap4(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * pop3Imap4ServerName (String)
         */
        public String getPop3Imap4ServerName() {
            return getStringValue(KEY_POP3_IMAP4_SERVER_NAME);
        }
        public void setPop3Imap4ServerName(String value) {
            setStringValue(KEY_POP3_IMAP4_SERVER_NAME, value);
        }
        public String removePop3Imap4ServerName() {
            return removeStringValue(KEY_POP3_IMAP4_SERVER_NAME);
        }
        
        /*
         * pop3Imap4Encryption (String)
         */
        public String getPop3Imap4Encryption() {
            return getStringValue(KEY_POP3_IMAP4_ENCRYPTION);
        }
        public void setPop3Imap4Encryption(String value) {
            setStringValue(KEY_POP3_IMAP4_ENCRYPTION, value);
        }
        public String removePop3Imap4Encryption() {
            return removeStringValue(KEY_POP3_IMAP4_ENCRYPTION);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class EmailCommunicationPort extends WritableElement {

        private static final String KEY_POP3_RECEPTION_PORT_NO             = "pop3ReceptionPortNo";
        private static final String KEY_IMAP4_RECEPTION_PORT_NO            = "imap4ReceptionPortNo";
        
        EmailCommunicationPort(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * pop3ReceptionPortNo (Number)
         */
        public Integer getPop3ReceptionPortNo() {
            return getNumberValue(KEY_POP3_RECEPTION_PORT_NO);
        }
        public void setPop3ReceptionPortNo(Integer value) {
            setNumberValue(KEY_POP3_RECEPTION_PORT_NO, value);
        }
        public Integer removePop3ReceptionPortNo() {
            return removeNumberValue(KEY_POP3_RECEPTION_PORT_NO);
        }
        
        /*
         * imap4ReceptionPortNo (Number)
         */
        public Integer getImap4ReceptionPortNo() {
            return getNumberValue(KEY_IMAP4_RECEPTION_PORT_NO);
        }
        public void setImap4ReceptionPortNo(Integer value) {
            setNumberValue(KEY_IMAP4_RECEPTION_PORT_NO, value);
        }
        public Integer removeImap4ReceptionPortNo() {
            return removeNumberValue(KEY_IMAP4_RECEPTION_PORT_NO);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class FaxEmailAccount extends WritableElement {

        private static final String KEY_RECEIVE_FAX_EMAIL                  = "receiveFaxEmail";
        private static final String KEY_FAX_EMAIL_ADDRESS                  = "faxEmailAddress";
        private static final String KEY_FAX_EMAIL_USER_NAME                = "faxEmailUserName";
        private static final String KEY_FAX_EMAIL_PASSWORD                 = "faxEmailPassword";
        
        FaxEmailAccount(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * receiveFaxEmail (String)
         */
        public String getReceiveFaxEmail() {
            return getStringValue(KEY_RECEIVE_FAX_EMAIL);
        }
        public void setReceiveFaxEmail(String value) {
            setStringValue(KEY_RECEIVE_FAX_EMAIL, value);
        }
        public String removeReceiveFaxEmail() {
            return removeStringValue(KEY_RECEIVE_FAX_EMAIL);
        }
        
        /*
         * faxEmailAddress (String)
         */
        public String getFaxEmailAddress() {
            return getStringValue(KEY_FAX_EMAIL_ADDRESS);
        }
        public void setFaxEmailAddress(String value) {
            setStringValue(KEY_FAX_EMAIL_ADDRESS, value);
        }
        public String removeFaxEmailAddress() {
            return removeStringValue(KEY_FAX_EMAIL_ADDRESS);
        }
        
        /*
         * faxEmailUserName (String)
         */
        public String getFaxEmailUserName() {
            return getStringValue(KEY_FAX_EMAIL_USER_NAME);
        }
        public void setFaxEmailUserName(String value) {
            setStringValue(KEY_FAX_EMAIL_USER_NAME, value);
        }
        public String removeFaxEmailUserName() {
            return removeStringValue(KEY_FAX_EMAIL_USER_NAME);
        }
        
        /*
         * faxEmailPassword (String)
         */
        public String getFaxEmailPassword() {
            return getStringValue(KEY_FAX_EMAIL_PASSWORD);
        }
        public void setFaxEmailPassword(String value) {
            setStringValue(KEY_FAX_EMAIL_PASSWORD, value);
        }
        public String removeFaxEmailPassword() {
            return removeStringValue(KEY_FAX_EMAIL_PASSWORD);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class EmailNotificationAccount extends WritableElement {

        private static final String KEY_RECEIVE_EMAIL_NOTIFICATION         = "receiveEmailNotification";
        private static final String KEY_EMAIL_NOTIFICATION_EMAIL_ADDRESS   = "emailNotificationEmailAddress";
        private static final String KEY_EMAIL_NOTIFICATION_USER_NAME       = "emailNotificationUserName";
        private static final String KEY_EMAIL_NOTIFICATION_PASSWORD        = "emailNotificationPassword";
        
        EmailNotificationAccount(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * receiveEmailNotification (String)
         */
        public String getReceiveEmailNotification() {
            return getStringValue(KEY_RECEIVE_EMAIL_NOTIFICATION);
        }
        public void setReceiveEmailNotification(String value) {
            setStringValue(KEY_RECEIVE_EMAIL_NOTIFICATION, value);
        }
        public String removeReceiveEmailNotification() {
            return removeStringValue(KEY_RECEIVE_EMAIL_NOTIFICATION);
        }
        
        /*
         * emailNotificationEmailAddress (String)
         */
        public String getEmailNotificationEmailAddress() {
            return getStringValue(KEY_EMAIL_NOTIFICATION_EMAIL_ADDRESS);
        }
        public void setEmailNotificationEmailAddress(String value) {
            setStringValue(KEY_EMAIL_NOTIFICATION_EMAIL_ADDRESS, value);
        }
        public String removeEmailNotificationEmailAddress() {
            return removeStringValue(KEY_EMAIL_NOTIFICATION_EMAIL_ADDRESS);
        }
        
        /*
         * emailNotificationUserName (String)
         */
        public String getEmailNotificationUserName() {
            return getStringValue(KEY_EMAIL_NOTIFICATION_USER_NAME);
        }
        public void setEmailNotificationUserName(String value) {
            setStringValue(KEY_EMAIL_NOTIFICATION_USER_NAME, value);
        }
        public String removeEmailNotificationUserName() {
            return removeStringValue(KEY_EMAIL_NOTIFICATION_USER_NAME);
        }
        
        /*
         * emailNotificationPassword (String)
         */
        public String getEmailNotificationPassword() {
            return getStringValue(KEY_EMAIL_NOTIFICATION_PASSWORD);
        }
        public void setEmailNotificationPassword(String value) {
            setStringValue(KEY_EMAIL_NOTIFICATION_PASSWORD, value);
        }
        public String removeEmailNotificationPassword() {
            return removeStringValue(KEY_EMAIL_NOTIFICATION_PASSWORD);
        }
    }
}