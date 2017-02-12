
package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;


import jp.co.ricoh.advop.mini.cheetahminiutil.R;

public enum MailFailReason {
    // 未設定（エラー無し）
    NO_SET(R.string.mail_sended_success),

    // *****************************************************
    // 送信前のエラー
    // *****************************************************
    // SMTPサーバ情報取得エラー
    SVR_INFO_GET_ERR(R.string.mail_smtp_server_access_failed),

    // SMTPサーバ設定不正
    SVR_SET_INVALID(R.string.mail_smtp_server_setting_invalid),

    // IOExceptionが発生した
    // (未使用：メール添付するＰＤＦを作成する,メモリ OR ストレージへ保存される:失敗)
    IO_ERR(R.string.mail_io_error),

    // バグなど在ってはならないエラー（メール情報作成：失敗）
    INVALID_ERR(R.string.mail_error),

    // *****************************************************
    // 送信後のエラー
    // *****************************************************
    // 認証エラー（主にSMTP）
    MAIL_ACCESS_AUTH_ERR(R.string.mail_auth_error),

    // サーバとのTCP接続エラー
    MAIL_SVR_CONNECT_ERR(R.string.mail_connection_error),

    // サーバのメモリがフル
    SVR_MEMORY_FULL(R.string.mail_server_full_error),

    // 権限なし
    // 主にメールボックスへのアクセス権限なし
    MAILBOX_ACCESS_AUTH_ERR(R.string.mail_connection_error),

    // 権限なし
    // 主にメールボックスへのアクセス権限なし（致命的）
    MAILBOX_ACCESS_AUTH_FATAL_ERR(R.string.mail_connection_error),

    // 送信先との接続失敗
    MAIL_DEST_CONNECT_ERR(R.string.mail_error),

    // 送信先との接続失敗（サーバ要因）
    MAIL_DEST_CONNECT_FATAL_ERR(R.string.mail_error),
    
    // その他エラー
    OTHER_ERR(R.string.mail_error);

    private int msgResourceId;
    
    private MailFailReason(int msgResId){
        msgResourceId = msgResId;
    }

    public int getMessageResourceId() {
        return msgResourceId;
    }
}
