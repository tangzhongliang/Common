/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;

public class Ldap extends WritableElement {

    private static final String KEY_SERVER_DISPLAY_NAME      = "serverDisplayName";
    private static final String KEY_SERVER_NAME              = "serverName";
    private static final String KEY_DN                       = "dn";
    private static final String KEY_USER_NAME                = "userName";
    private static final String KEY_PASSWORD                 = "password";
    private static final String KEY_LDAP_CHAR_CODE           = "ldapCharCode";
    private static final String KEY_PORT_NO                  = "portNo";
    private static final String KEY_SSL                      = "ssl";
    private static final String KEY_BIND                     = "bind";
    private static final String KEY_LDAP_INFO_REGISTERED     = "ldapInfoRegistered";
    private static final String KEY_CN_DISPLAY_NAME          = "cnDisplayName";
    private static final String KEY_CN                       = "cn";
    private static final String KEY_MAIL_DISPLAY_NAME        = "mailDisplayName";
    private static final String KEY_MAIL                     = "mail";
    private static final String KEY_FAX_DISPLAY_NAME         = "faxDisplayName";
    private static final String KEY_FAX                      = "fax";
    private static final String KEY_O_DISPLAY_NAME           = "oDisplayName";
    private static final String KEY_O                        = "o";
    private static final String KEY_OU_DISPLAY_NAME          = "ouDisplayName";
    private static final String KEY_OU                       = "ou";
    private static final String KEY_ATTRIBUTE_1_DISPLAY_NAME = "attribute1DisplayName";
    private static final String KEY_ATTRIBUTE_1              = "attribute1";
    private static final String KEY_ATTRIBUTE_2_DISPLAY_NAME = "attribute2DisplayName";
    private static final String KEY_ATTRIBUTE_2              = "attribute2";
    private static final String KEY_ATTRIBUTE_3_DISPLAY_NAME = "attribute3DisplayName";
    private static final String KEY_ATTRIBUTE_3              = "attribute3";
    private static final String KEY_KERBEROS                 = "kerberos";
    private static final String KEY_REALM                    = "realm";


    Ldap(Map<String, Object> values) {
        super(values);
    }

    /*
     * serverDisplayName (String)
     */
    public String getServerDisplayName() {
        return getStringValue(KEY_SERVER_DISPLAY_NAME);
    }
    public void setServerDisplayName(String value) {
        setStringValue(KEY_SERVER_DISPLAY_NAME, value);
    }
    public String removeServerDisplayName() {
        return removeStringValue(KEY_SERVER_DISPLAY_NAME);
    }

    /*
     * serverName (String)
     */
    public String getServerName() {
        return getStringValue(KEY_SERVER_NAME);
    }
    public void setServerName(String value) {
        setStringValue(KEY_SERVER_NAME, value);
    }
    public String removeServerName() {
        return removeStringValue(KEY_SERVER_NAME);
    }

    /*
     * dn (String)
     */
    public String getDn() {
        return getStringValue(KEY_DN);
    }
    public void setDn(String value) {
        setStringValue(KEY_DN, value);
    }
    public String removeDn() {
        return removeStringValue(KEY_DN);
    }

    /*
     * userName (String)
     */
    public String getUserName() {
        return getStringValue(KEY_USER_NAME);
    }
    public void setUserName(String value) {
        setStringValue(KEY_USER_NAME, value);
    }
    public String removeUserName() {
        return removeStringValue(KEY_USER_NAME);
    }

    /*
     * password (String)
     */
    public String getPassword() {
        return getStringValue(KEY_PASSWORD);
    }
    public void setPassword(String value) {
        setStringValue(KEY_PASSWORD, value);
    }
    public String removePassword() {
        return removeStringValue(KEY_PASSWORD);
    }

    /*
     * ldapCharCode (String)
     */
    public String getLdapCharCode() {
        return getStringValue(KEY_LDAP_CHAR_CODE);
    }
    public void setLdapCharCode(String value) {
        setStringValue(KEY_LDAP_CHAR_CODE, value);
    }
    public String removeLdapCharCode() {
        return removeStringValue(KEY_LDAP_CHAR_CODE);
    }

    /*
     * portNo (Int)
     */
    public Integer getPortNo() {
        return getNumberValue(KEY_PORT_NO);
    }
    public void setPortNo(Integer value) {
        setNumberValue(KEY_PORT_NO, value);
    }
    public Integer removePortNo() {
        return removeNumberValue(KEY_PORT_NO);
    }

    /*
     * ssl (String)
     */
    public String getSsl() {
        return getStringValue(KEY_SSL);
    }
    public void setSsl(String value) {
        setStringValue(KEY_SSL, value);
    }
    public String removeSsl() {
        return removeStringValue(KEY_SSL);
    }

    /*
     * bind (String)
     */
    public String getBind() {
        return getStringValue(KEY_BIND);
    }
    public void setBind(String value) {
        setStringValue(KEY_BIND, value);
    }
    public String removeBind() {
        return removeStringValue(KEY_BIND);
    }

    /*
     * ldapInfoRegistered (String)
     */
    public String getLdapInfoRegistered() {
        return getStringValue(KEY_LDAP_INFO_REGISTERED);
    }
    public void setLdapInfoRegistered(String value) {
        setStringValue(KEY_LDAP_INFO_REGISTERED, value);
    }
    public String removeLdapInfoRegistered() {
        return removeStringValue(KEY_LDAP_INFO_REGISTERED);
    }

    /*
     * cnDisplayName (String)
     */
    public String getCnDisplayName() {
        return getStringValue(KEY_CN_DISPLAY_NAME);
    }
    public void setCnDisplayName(String value) {
        setStringValue(KEY_CN_DISPLAY_NAME, value);
    }
    public String removeCnDisplayName() {
        return removeStringValue(KEY_CN_DISPLAY_NAME);
    }

    /*
     * cn (String)
     */
    public String getCn() {
        return getStringValue(KEY_CN);
    }
    public void setCn(String value) {
        setStringValue(KEY_CN, value);
    }
    public String removeCn() {
        return removeStringValue(KEY_CN);
    }

    /*
     * mailDisplayName (String)
     */
    public String getMailDisplayName() {
        return getStringValue(KEY_MAIL_DISPLAY_NAME);
    }
    public void setMailDisplayName(String value) {
        setStringValue(KEY_MAIL_DISPLAY_NAME, value);
    }
    public String removeMailDisplayName() {
        return removeStringValue(KEY_MAIL_DISPLAY_NAME);
    }

    /*
     * mail (String)
     */
    public String getMail() {
        return getStringValue(KEY_MAIL);
    }
    public void setMail(String value) {
        setStringValue(KEY_MAIL, value);
    }
    public String removeMail() {
        return removeStringValue(KEY_MAIL);
    }

    /*
     * faxDisplayName (String)
     */
    public String getFaxDisplayName() {
        return getStringValue(KEY_FAX_DISPLAY_NAME);
    }
    public void setFaxDisplayName(String value) {
        setStringValue(KEY_FAX_DISPLAY_NAME, value);
    }
    public String removeFaxDisplayName() {
        return removeStringValue(KEY_FAX_DISPLAY_NAME);
    }

    /*
     * fax (String)
     */
    public String getFax() {
        return getStringValue(KEY_FAX);
    }
    public void setFax(String value) {
        setStringValue(KEY_FAX, value);
    }
    public String removeFax() {
        return removeStringValue(KEY_FAX);
    }

    /*
     * oDisplayName (String)
     */
    public String getODisplayName() {
        return getStringValue(KEY_O_DISPLAY_NAME);
    }
    public void setODisplayName(String value) {
        setStringValue(KEY_O_DISPLAY_NAME, value);
    }
    public String removeODisplayName() {
        return removeStringValue(KEY_O_DISPLAY_NAME);
    }

    /*
     * o (String)
     */
    public String getO() {
        return getStringValue(KEY_O);
    }
    public void setO(String value) {
        setStringValue(KEY_O, value);
    }
    public String removeO() {
        return removeStringValue(KEY_O);
    }

    /*
     * ouDisplayName (String)
     */
    public String getOuDisplayName() {
        return getStringValue(KEY_OU_DISPLAY_NAME);
    }
    public void setOuDisplayName(String value) {
        setStringValue(KEY_OU_DISPLAY_NAME, value);
    }
    public String removeOuDisplayName() {
        return removeStringValue(KEY_OU_DISPLAY_NAME);
    }

    /*
     * ou (String)
     */
    public String getOu() {
        return getStringValue(KEY_OU);
    }
    public void setOu(String value) {
        setStringValue(KEY_OU, value);
    }
    public String removeOu() {
        return removeStringValue(KEY_OU);
    }

    /*
     * attribute1DisplayName (String)
     */
    public String getAttribute1DisplayName() {
        return getStringValue(KEY_ATTRIBUTE_1_DISPLAY_NAME);
    }
    public void setAttribute1DisplayName(String value) {
        setStringValue(KEY_ATTRIBUTE_1_DISPLAY_NAME, value);
    }
    public String removeAttribute1DisplayName() {
        return removeStringValue(KEY_ATTRIBUTE_1_DISPLAY_NAME);
    }

    /*
     * attribute1 (String)
     */
    public String getAttribute1() {
        return getStringValue(KEY_ATTRIBUTE_1);
    }
    public void setAttribute1(String value) {
        setStringValue(KEY_ATTRIBUTE_1, value);
    }
    public String removeAttribute1() {
        return removeStringValue(KEY_ATTRIBUTE_1);
    }

    /*
     * attribute2DisplayName (String)
     */
    public String getAttribute2DisplayName() {
        return getStringValue(KEY_ATTRIBUTE_2_DISPLAY_NAME);
    }
    public void setAttribute2DisplayName(String value) {
        setStringValue(KEY_ATTRIBUTE_2_DISPLAY_NAME, value);
    }
    public String removeAttribute2DisplayName() {
        return removeStringValue(KEY_ATTRIBUTE_2_DISPLAY_NAME);
    }

    /*
     * attribute2 (String)
     */
    public String getAttribute2() {
        return getStringValue(KEY_ATTRIBUTE_2);
    }
    public void setAttribute2(String value) {
        setStringValue(KEY_ATTRIBUTE_2, value);
    }
    public String removeAttribute2() {
        return removeStringValue(KEY_ATTRIBUTE_2);
    }

    /*
     * attribute3DisplayName (String)
     */
    public String getAttribute3DisplayName() {
        return getStringValue(KEY_ATTRIBUTE_3_DISPLAY_NAME);
    }
    public void setAttribute3DisplayName(String value) {
        setStringValue(KEY_ATTRIBUTE_3_DISPLAY_NAME, value);
    }
    public String removeAttribute3DisplayName() {
        return removeStringValue(KEY_ATTRIBUTE_3_DISPLAY_NAME);
    }

    /*
     * attribute3 (String)
     */
    public String getAttribute3() {
        return getStringValue(KEY_ATTRIBUTE_3);
    }
    public void setAttribute3(String value) {
        setStringValue(KEY_ATTRIBUTE_3, value);
    }
    public String removeAttribute3() {
        return removeStringValue(KEY_ATTRIBUTE_3);
    }

    /*
     * kerberos (String)
     */
    public String getKerberos() {
        return getStringValue(KEY_KERBEROS);
    }
    public void setKerberos(String value) {
        setStringValue(KEY_KERBEROS, value);
    }
    public String removeKerberos() {
        return removeStringValue(KEY_KERBEROS);
    }

    /*
     * realm (String)
     */
    public String getRealm() {
        return getStringValue(KEY_REALM);
    }
    public void setRealm(String value) {
        setStringValue(KEY_REALM, value);
    }
    public String removeRealm() {
        return removeStringValue(KEY_REALM);
    }

}
