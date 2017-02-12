/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

/*
 * @since SmartSDK V2.12
 */
public class GetEmailResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_ADMINISTRATOR_EMAIL_ADDRESS = "administratorEmailAddress";
	private static final String KEY_RECEPTION                   = "reception";
	private static final String KEY_SMTP                        = "smtp";
	private static final String KEY_POP_BEFORE_SMTP             = "popBeforeSmtp";
	private static final String KEY_POP3_IMAP4                  = "pop3Imap4";
	private static final String KEY_EMAIL_COMMUNICATION_PORT    = "emailCommunicationPort";
	private static final String KEY_FAX_EMAIL_ACCOUNT           = "faxEmailAccount";
	private static final String KEY_EMAIL_NOTIFACATION_ACCOUNT  = "emailNotificationAccount";
	
	GetEmailResponseBody(Map<String, Object> values) {
		super(values);
	}


    /*
     * administratorEmailAddress (String)
     * @since SmartSDK V2.12
     */
    public String getAdministratorEmailAddress() {
    	return getStringValue(KEY_ADMINISTRATOR_EMAIL_ADDRESS);
    }
    
    /*
     * reception (Object)
     * @since SmartSDK V2.12
     */
    public Reception getReception() {
        Map<String, Object> value = getObjectValue(KEY_RECEPTION);
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
        	return null;
        }
        return new EmailNotificationAccount(value);
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class Reception extends Element {

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
        
        /*
         * emailReceptionInterval (Number)
         */
        public Integer getEmailReceptionInterval() {
            return getNumberValue(KEY_EMAIL_RECEPTION_INTERVAL);
        }
        
        /*
         * maxReceptionEmailSize (Number)
         */
        public Integer getMaxReceptionEmailSize() {
            return getNumberValue(KEY_MAX_RECEPTION_EMAIL_SIZE);
        }
        
        /*
         * emailStorageInServer (String)
         */
        public String getEmailStorageInServer() {
            return getStringValue(KEY_EMAIL_STORAGE_IN_SERVER);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class Smtp extends Element {

        private static final String KEY_SMTP_SERVER_NAME                  = "smtpServerName";
        private static final String KEY_SMTP_PORT_NO                      = "smtpPortNo";
        private static final String KEY_USE_SECURE_CONNECTION             = "useSecureConnection";
        private static final String KEY_SMTP_AUTHENTICATION               = "smtpAuthentication";
        private static final String KEY_SMTP_AUTH_EMAIL_ADDRESS           = "smtpAuthEmailAddress";
        private static final String KEY_SMTP_AUTH_USER_NAME               = "smtpAuthUserName";
        private static final String KEY_SMTP_AUTH_ENCRYPTION              = "smtpAuthEncryption";
        
        Smtp(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * smtpServerName (String)
         */
        public String getSmtpServerName() {
            return getStringValue(KEY_SMTP_SERVER_NAME);
        }
        
        /*
         * smtpPortNo (Number)
         */
        public Integer getSmtpPortNo() {
            return getNumberValue(KEY_SMTP_PORT_NO);
        }
        
        /*
         * useSecureConnection (String)
         */
        public String getUseSecureConnection() {
            return getStringValue(KEY_USE_SECURE_CONNECTION);
        }
        
        /*
         * smtpAuthentication (String)
         */
        public String getSmtpAuthentication() {
            return getStringValue(KEY_SMTP_AUTHENTICATION);
        }
        
        /*
         * smtpAuthEmailAddress (String)
         */
        public String getSmtpAuthEmailAddress() {
            return getStringValue(KEY_SMTP_AUTH_EMAIL_ADDRESS);
        }
        
        /*
         * smtpAuthUserName (String)
         */
        public String getSmtpAuthUserName() {
            return getStringValue(KEY_SMTP_AUTH_USER_NAME);
        }
        
        /*
         * smtpAuthEncryption (String)
         */
        public String getSmtpAuthEncryption() {
            return getStringValue(KEY_SMTP_AUTH_ENCRYPTION);
        }
    }

    /*
     * @since SmartSDK V2.12
     */
    public static class PopBeforeSmtp extends Element {

        private static final String KEY_POP_AUTH_BEFORE_SMTP              = "popAuthBeforeSmtp";
        private static final String KEY_POP_EMAIL_ADDRESS                 = "popEmailAddress";
        private static final String KEY_POP_USER_NAME                     = "popUserName";
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
        
        /*
         * popEmailAddress (String)
         */
        public String getPopEmailAddress() {
            return getStringValue(KEY_POP_EMAIL_ADDRESS);
        }
        
        /*
         * popUserName (String)
         */
        public String getPopUserName() {
            return getStringValue(KEY_POP_USER_NAME);
        }
        
        /*
         * timeoutSettingAfterPopAuth (Number)
         */
        public Integer getTimeoutSettingAfterPopAuth() {
            return getNumberValue(KEY_TIMEOUT_SETTING_AFTER_POP_AUTH);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class Pop3Imap4 extends Element {

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
        
        /*
         * pop3Imap4Encryption (String)
         */
        public String getPop3Imap4Encryption() {
            return getStringValue(KEY_POP3_IMAP4_ENCRYPTION);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class EmailCommunicationPort extends Element {

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
        
        /*
         * imap4ReceptionPortNo (Number)
         */
        public Integer getImap4ReceptionPortNo() {
            return getNumberValue(KEY_IMAP4_RECEPTION_PORT_NO);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class FaxEmailAccount extends Element {

        private static final String KEY_RECEIVE_FAX_EMAIL                  = "receiveFaxEmail";
        private static final String KEY_FAX_EMAIL_ADDRESS                  = "faxEmailAddress";
        private static final String KEY_FAX_EMAIL_USER_NAME                = "faxEmailUserName";
        
        FaxEmailAccount(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * receiveFaxEmail (String)
         */
        public String getReceiveFaxEmail() {
            return getStringValue(KEY_RECEIVE_FAX_EMAIL);
        }
        
        /*
         * faxEmailAddress (String)
         */
        public String getFaxEmailAddress() {
            return getStringValue(KEY_FAX_EMAIL_ADDRESS);
        }
        
        /*
         * faxEmailUserName (String)
         */
        public String getFaxEmailUserName() {
            return getStringValue(KEY_FAX_EMAIL_USER_NAME);
        }
    }
    
    /*
     * @since SmartSDK V2.12
     */
    public static class EmailNotificationAccount extends Element {

        private static final String KEY_RECEIVE_EMAIL_NOTIFICATION         = "receiveEmailNotification";
        private static final String KEY_EMAIL_NOTIFICATION_EMAIL_ADDRESS   = "emailNotificationEmailAddress";
        private static final String KEY_EMAIL_NOTIFICATION_USER_NAME       = "emailNotificationUserName";
        
        EmailNotificationAccount(Map<String, Object> values) {
            super(values);
        }
        
        /*
         * receiveEmailNotification (String)
         */
        public String getReceiveEmailNotification() {
            return getStringValue(KEY_RECEIVE_EMAIL_NOTIFICATION);
        }
        
        /*
         * emailNotificationEmailAddress (String)
         */
        public String getEmailNotificationEmailAddress() {
            return getStringValue(KEY_EMAIL_NOTIFICATION_EMAIL_ADDRESS);
        }
        
        /*
         * emailNotificationUserName (String)
         */
        public String getEmailNotificationUserName() {
            return getStringValue(KEY_EMAIL_NOTIFICATION_USER_NAME);
        }
    }
}