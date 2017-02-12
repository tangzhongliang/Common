
package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;

public enum MailSettingsCondition {
    SUCCESS, // エラー無し
    SMTP_SETING_GET_FAILD, // SMTPサーバ設定情報取得失敗
    USE_SSL, // SSL利用時
    NO_SMTP_SVR_ADDR, // SMTPサーバアドレスが未設定
}
