
package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;


public class SendMailSetting {
    private int maxSendTimes = 1; // 最大送信回数[回]
    private int sendInterval = 2000; // 再送時間間隔

    private String smtpSvrName = null; // SMTPサーバ名
    private String smtpSvrPort = null; // STMPサーバの待ち受けポート番号
    private boolean isSsl = false; // STMPサーバの待ち受けポート番号

    private boolean isSmtpAuth = false; // SMTP認証の許可/禁止
    private String smtpAuthUserName = null; // SMTP認証用ユーザ名(制約：SMTP認証許可時はNULL禁止)
    private String smtpAuthPass = null; // SMTP認証用パスワード(制約：SMTP認証許可時はNULL禁止)
    private String mailaddr = null; // SMTP認証者のメールアドレス

    // ******************************************************
    // メール送信先の情報
    // ******************************************************
    private String mailSubject = "EngineCheck:SCFixing"; // メールの件名
    private String destMailAddr = "WangLi@rst.ricoh.com"; // 宛先メールアドレス
    private String charaCode = "ISO-2022-JP"; // 文字コード

    public SendMailSetting() {
    }

    public void initSendParam() {
        maxSendTimes = 1;
        sendInterval = 2000;

        smtpSvrName = "";
        smtpSvrPort = "";
        isSsl = false;

        isSmtpAuth = false;
        smtpAuthUserName = "";
        smtpAuthPass = "";
        mailaddr = "";
    }

    public void setSMTPServerSetting(MachineSMTPSetting.SMTPSettingData setting) {
        if (null != setting) {
            smtpSvrName = setting.getMail_sendinfo().getSmtp_server().getServername();
            smtpSvrPort = String.valueOf(setting.getMail_sendinfo().getSmtp_server().getPortno());
            isSsl = (setting.getMail_sendinfo().getSmtp_server().getSsl() == 0) ? false : true;

            isSmtpAuth = (setting.getMail_sendinfo().getSmtp_authentication().getSmtp_auth() == 0) ? false
                    : true;

            smtpAuthUserName = setting.getMail_sendinfo().getSmtp_authentication().getUsername();
            smtpAuthPass = setting.getMail_sendinfo().getSmtp_authentication().getPassword();
            mailaddr = setting.getMail_sendinfo().getSmtp_authentication().getMailaddr();
        }
    }

    public int getMaxSendTimes() {
        return maxSendTimes;
    }

    public void setMaxSendTimes(int maxSendTimes) {
        this.maxSendTimes = maxSendTimes;
    }

    public int getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(int sendInterval) {
        this.sendInterval = sendInterval;
    }

    public String getSmtpSvrName() {
        return smtpSvrName;
    }

    public void setSmtpSvrName(String smtpSvrName) {
        this.smtpSvrName = smtpSvrName;
    }

    public String getSmtpSvrPort() {
        return smtpSvrPort;
    }

    public void setSmtpSvrPort(String smtpSvrPort) {
        this.smtpSvrPort = smtpSvrPort;
    }

    public boolean isSsl() {
        return isSsl;
    }

    public void setIsSsl(boolean isUseSsl) {
        this.isSsl = isUseSsl;
    }

    public boolean isSmtpAuth() {
        return isSmtpAuth;
    }

    public void setSmtpAuth(boolean isSmtpAuth) {
        this.isSmtpAuth = isSmtpAuth;
    }

    public String getSmtpAuthUserName() {
        return smtpAuthUserName;
    }

    public void setSmtpAuthUserName(String smtpAuthUserName) {
        this.smtpAuthUserName = smtpAuthUserName;
    }

    public String getSmtpAuthPass() {
        return smtpAuthPass;
    }

    public void setSmtpAuthPass(String smtpAuthPass) {
        this.smtpAuthPass = smtpAuthPass;
    }

    public String getMailaddr() {
        return mailaddr;
    }

    public void setMailaddr(String mailaddr) {
        this.mailaddr = mailaddr;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getDestMailAddr() {
        return destMailAddr;
    }

    public void setDestMailAddr(String destMailAddr) {
        this.destMailAddr = destMailAddr;
    }

    public String getCharaCode() {
        return charaCode;
    }

    public void setCharaCode(String charaCode) {
        this.charaCode = charaCode;
    }

    // 機能
    // メール送信者名を返します
    // return:
    // 未設定時はnullが返ります
    public String getSenderName() {
        return null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("SmtpServerName:");
        sb.append(smtpSvrName);
        sb.append(",");

        sb.append("SmtpServerPort:");
        sb.append(smtpSvrPort);
        sb.append(",");

        sb.append("SSl:");
        sb.append(isSsl);
        sb.append(",");

        sb.append("SmtpAuth:");
        sb.append(isSmtpAuth);
        sb.append(",");

        sb.append("AuthUserName:");
        sb.append(smtpAuthUserName);
        sb.append(",");

        sb.append("AuthPassword:");
        sb.append(smtpAuthPass);
        sb.append(",");

        sb.append("MailAddress:");
        sb.append(mailaddr);
        sb.append(",");

        sb.append("MailSubject:");
        sb.append(mailSubject);
        sb.append(",");

        sb.append("DestMailAddress:");
        sb.append(destMailAddr);

        return sb.toString();
    }
}
