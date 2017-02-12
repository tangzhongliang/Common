
package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;

import net.arnx.jsonic.JSON;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;

public class MachineSMTPSetting {
    private Logger mLogger = Logger.getLogger(MachineSMTPSetting.class);

    private final int SPACCESS_CONNECTION_TIMEOUT = 3000;
    private final int SPACCESS_IO_TIMEOUT = 2000;
    private String mRequestURL = "http://gw.machine.address:18305/service/systeminfo/mailparam/sendinfo";

    public boolean getSMTPServerSetting(SendMailSetting sendMailSetting) {
        boolean result = false;
        HttpGet httpGet = new HttpGet(mRequestURL);

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpParams params = client.getParams();
            // 接続タイムアウト時間
            params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                    SPACCESS_CONNECTION_TIMEOUT);
            // データ転送タイムアウト時間
            params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SPACCESS_IO_TIMEOUT);
            // Send Request
            HttpResponse response = client.execute(httpGet);
            // Get result
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                InputStream inputStream = entity.getContent();
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    SMTPSettingData settingData = JSON.decode(inputStream, SMTPSettingData.class);
                    sendMailSetting.setSMTPServerSetting(settingData);
                    mLogger.info("getSMTPServerSetting", "Result OK");
                    result = true;
                } else {
                    mLogger.warn("getSMTPServerSetting", "Result NG");
                }
            }
        } catch (ClientProtocolException e) {
            mLogger.error("getSMTPServerSetting", "ClientProtocolException");
        } catch (IOException e) {
            mLogger.error("getSMTPServerSetting", "IOException");
        } finally {
            httpGet.abort();
        }

        return result;
    }
    public static class SMTPSettingData {
        //{
        //  "mail_sendinfo":
        //  {
        //    "smtp_server":
        //     {
        //       "servername":"172.25.78.71",
        //       "portno":825,
        //       "ssl":0
        //     },
        //    "smtp_authentication":
        //     {
        //      "smtp_auth":0,
        //      "username":"",
        //      "password":"",
        //      "mailaddr":""
        //     }
        //  }
        //}
        private MailSendinfo mail_sendinfo;
        
        public MailSendinfo getMail_sendinfo() {
            return mail_sendinfo;
        }

        public void setMail_sendinfo(MailSendinfo mail_sendinfo) {
            this.mail_sendinfo = mail_sendinfo;
        }
        
        public String toString(){
            String info = "SMTPSettingData:{";
            if(null != mail_sendinfo){
                return info + mail_sendinfo.toString() + "}";
            }else{
                return info + "}";
            }
        }
    }

    public static class MailSendinfo{
        private SmtpServer smtp_server;
        private SmtpAuthentication smtp_authentication;

        public SmtpServer getSmtp_server() {
            return smtp_server;
        }

        public void setSmtp_server(SmtpServer smtp_server) {
            this.smtp_server = smtp_server;
        }

        public SmtpAuthentication getSmtp_authentication() {
            return smtp_authentication;
        }

        public void setSmtp_authentication(SmtpAuthentication smtp_authentication) {
            this.smtp_authentication = smtp_authentication;
        }

        public String toString() {
            String info = "";
            if (null != smtp_server) {
                info = smtp_server.toString();
            }

            if (null != smtp_authentication) {
                info = info + smtp_authentication.toString();
            }

            return info;
        }
    }

    public static class SmtpServer{
        private String mServername = "";
        private int mPortNo = 0;
        private int mSsl = 0;
        
        public String getServername() {
            return mServername;
        }

        public void setServername(String servername) {
            this.mServername = servername;
        }

        public int getPortno() {
            return mPortNo;
        }

        public void setPortno(int portno) {
            this.mPortNo = portno;
        }
        
        public int getSsl() {
            return mSsl;
        }

        public void setSsl(int ssl) {
            this.mSsl = ssl;
        }
        
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("servername=");
            sb.append(mServername);
            sb.append(",");
            
            sb.append("portno=");
            sb.append(mPortNo);
            sb.append(",");
            
            sb.append("Ssl=");
            sb.append(mSsl);
            return sb.toString();
        }
    }

    public static class SmtpAuthentication{
        private int mSmtpAuth = 0;
        private String mUsername = "";
        private String mPassword = "";
        private String mMailaddr = "";
        
        public int getSmtp_auth() {
            return mSmtpAuth;
        }

        public void setSmtp_auth(int smtp_auth) {
            this.mSmtpAuth = smtp_auth;
        }

        public String getUsername() {
            return mUsername;
        }

        public void setUsername(String username) {
            this.mUsername = username;
        }

        public String getPassword() {
            return mPassword;
        }

        public void setPassword(String password) {
            this.mPassword = password;
        }

        public String getMailaddr() {
            return mMailaddr;
        }

        public void setMailaddr(String mailaddr) {
            this.mMailaddr = mailaddr;
        }
        
        public String toString(){
            StringBuffer sb = new StringBuffer();

            sb.append("smtp_auth=");
            sb.append(mSmtpAuth);
            sb.append(",");
            
            sb.append("username=");
            sb.append(mUsername);
            sb.append(",");
            
            sb.append("password=");
            sb.append(mPassword);
            sb.append(",");
            
            sb.append("mailaddr=");
            sb.append(mMailaddr);
            
            return sb.toString();
        }
    }
}
