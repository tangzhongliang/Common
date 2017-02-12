
package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;


/**
 * メール結果 (G1から流用。FileFormat、FileTypeの型のみ変更)
 * 
 * @author p000506557
 */
public class MailResult {
    private Logger mLogger = Logger.getLogger(MailResult.class);

    // 当該メール試行結果（成功／失敗）
    private boolean isSucceeded = false;

    // 失敗理由
    // 再送時は最後に発生した状態を記憶する
    // 失敗→成功だとNOT_SETを上書き設定する
    private MailFailReason failReason = MailFailReason.NO_SET;

    // サーバから受信した応答コード
    // 有効値は1以上
    private int respCode = -1;

    // 最大送信回数分だけ送信済みか否か
    private boolean isMaxTimesTried = false;

    public MailResult(boolean isSucceeded, MailFailReason failReason, int respCode) {
        this.isSucceeded = isSucceeded;
        this.failReason = failReason;
        this.respCode = respCode;
    }

    public boolean isSucceeded() {
        return this.isSucceeded;
    }

    public MailFailReason getFailReason() {
        return this.failReason;
    }

    // failReason == MailFailReason.RCV_NG_RESPONSE
    // 以外では無効値「-1」が返る
    public int getRespCode() {
        return this.respCode;
    }

    public void setMaxTimesTried() {
        this.isMaxTimesTried = true;
    }

    public boolean isMaxTimesTried() {
        return this.isMaxTimesTried;
    }

    public void print() {
        mLogger.info("print", "isSucceeded=" + this.isSucceeded
                + ".failReason=" + this.failReason
                + ".respCode=" + this.respCode
                + ".isMaxTimesTried=" + isMaxTimesTried);
    }
}
