
package jp.co.ricoh.advop.mini.cheetahminiutil.tzl.mail;

import android.text.TextUtils;

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {
    public static final String MAIL_SUBJECT = "Scan Jpeg";
    private String content = "From:Easy Jpeg Scan";
    private Logger mLogger = Logger.getLogger(MailUtil.class);

    private SendMailSetting mSendMailSetting = new SendMailSetting();
    private File mAttachmentFile;
    private File[] mAttachmentFiles;

    // SMTPサーバとの接続タイムアウト時間[ms]
    // 10秒
    static final private String SMTP_SVR_CONNECTION_TIMEOUT = "10000";
    // SMTPサーバとの送受信のタイムアウト時間[ms]
    // 900秒(Legacyスキャナと同じ値)
    static final private String SMTP_SVR_TRX_TIMEOUT = "900000";

    // 当該メールにおける総送信試行回数
    public int mTriedTimes = 0;

    // 最大送信回数
    // 再送禁止の場合は「1」
    private int mMaxTryTimes = 1;

    public MailResult send(String destMailAddress, String content, File[] attchmentFiles) {
        this.content = content;
        return send(destMailAddress, attchmentFiles);
    }

    public MailResult send(String destMailAddress, String content, File attchmentFile) {
        this.content = content;
        return send(destMailAddress, attchmentFile);
    }

    public MailResult send(String destMailAddress, File[] attchmentFiles) {
        mLogger.info("send", "triedTimes=" + this.mTriedTimes);
        mAttachmentFiles = attchmentFiles;

        MailSettingsCondition condition = setSendMailSetting(destMailAddress);
        mSendMailSetting.setMailSubject(MAIL_SUBJECT);

        if (MailSettingsCondition.SUCCESS != condition) {

            MailResult result;
            if (MailSettingsCondition.SMTP_SETING_GET_FAILD == condition) {
                result = new MailResult(
                        false, // 結果：失敗
                        MailFailReason.SVR_INFO_GET_ERR, // SMTPサーバ情報取得エラー
                        -1); // 応答コード
            } else {
                result = new MailResult(
                        false, // 結果：失敗
                        MailFailReason.SVR_SET_INVALID, // エラー理由：SMTPサーバ設定不正
                        -1); // 応答コード
            }

            return result;
        }

        // 送信試行回数をカウントアップ
        this.mTriedTimes++;

        // 送信試行
        final MailResult result = actSend();

        // 最大送信試行回数の上限をチェック
        if (this.mTriedTimes >= this.mMaxTryTimes) {
            // 当該試行によって、
            // 最大送信試行回数に到達したというフラグを設定する
            result.setMaxTimesTried();
        }

        // 送信成功ならば使用済みのＰＤＦリソースを開放する
        if (result.isSucceeded()) {
            mLogger.info("send", "Success");
        }

        result.print();
        mLogger.info("send", "end");

        return result;
    }

    // 機能：
    // メールを１通送信する
    public MailResult send(String destMailAddress, File attchmentFile) {
        if (attchmentFile.isDirectory()) {
            return send(destMailAddress, attchmentFile.listFiles());
        } else {
            return send(destMailAddress, new File[]{attchmentFile});
        }
    }

    // 機能：
    // メールを送信する
    // メールの添付ファイルを用意する
    private MailResult actSend() {
        // 通信セッションインスタンスを作成
        // ※通信はTransport.send()で発動
        final Session session = makeMailSession();

        // メールサーバとのネゴ内容を知りたい場合に有効化してください
        // session.setDebug(true);

        // 送信メッセージインスタンスを作成
        final MimeMessage objMsg = new MimeMessage(session);

        // --------------------------------
        // メールを作成する
        // --------------------------------
        final boolean boolResult = createMail(objMsg);
        if (!boolResult) {
            mLogger.error("actSend", "FATAL: actSend: fail_to_createMail.");
            return makeInvalidResult();
        }

        // --------------------------------
        // メールを送信する
        // --------------------------------
        final MailResult result = transmit(objMsg);

        return result;
    }

    private MailSettingsCondition setSendMailSetting(String destMailAddress) {
        mSendMailSetting.initSendParam();

        // 機器SMTP情報取得
        MachineSMTPSetting smtpSetting = new MachineSMTPSetting();
        if (!smtpSetting.getSMTPServerSetting(mSendMailSetting)) {
            return MailSettingsCondition.SMTP_SETING_GET_FAILD;
        }

        // SSL利用有無
        boolean ssl = mSendMailSetting.isSsl();
        if (ssl) {
            mLogger.error("getMachineSMTPServerInfo", "SSL_USE=Yes");
            return MailSettingsCondition.USE_SSL;
        }

        // SMTPサーバ名
        String smtpSvrName = mSendMailSetting.getSmtpSvrName();
        if (smtpSvrName == null || smtpSvrName.length() <= 0) {
            mLogger.error("getMachineSMTPServerInfo", "NO SMTP_SVR_ADDR");
            return MailSettingsCondition.NO_SMTP_SVR_ADDR;
        }

        // SMTPサーバのポート番号
        String smtpSvrPort = mSendMailSetting.getSmtpSvrPort();
        if (smtpSvrPort == null) {
            mLogger.error("getMachineSMTPServerInfo", "FATAL getSendParamFromCats: smtpSvrPort=null.");
            smtpSvrPort = "";
        }

        // SMTP認証用ユーザ名
        String smtpUserName = mSendMailSetting.getSmtpAuthUserName();
        if (smtpUserName == null) {
            mLogger.error("getMachineSMTPServerInfo",
                    "FATAL getSendParamFromCats: smtpUserName=null.");
            smtpUserName = "";
        }

        // SMTP認証用パスワード
        String smtpPass = mSendMailSetting.getSmtpAuthPass();
        if (smtpPass == null) {
            mLogger.error("getMachineSMTPServerInfo", "FATAL getSendParamFromCats: smtpPass=null.");
            smtpPass = "";
        }

        // 宛先メールアドレス
        mSendMailSetting.setDestMailAddr(destMailAddress);

        // 最大再送回数
        mSendMailSetting.setMaxSendTimes(1);

        // 再送時間間隔
        // mSendMailSetting.setSendInterval(resendInterval);

        // ハードキーストップ設定
        // mSendMailSetting.setHardKeyStopMode(stop_key_act);

        mLogger.debug("setSendMailSetting", "Seting=" + mSendMailSetting.toString());
        return MailSettingsCondition.SUCCESS;
    }

    // ------------------------------------
    // メールのセッション条件を作成する
    // ・接続先ＳＭＴＰサーバ名
    // ・接続先ホスト名
    // ・接続先ホストのポート番号
    // ・他
    // return：
    // 作成したセッション情報
    // ------------------------------------
    private Session makeMailSession() {
        Properties objPrp = new Properties();

        // SMTPサーバ名
        objPrp.put("mail.smtp.host", this.mSendMailSetting.getSmtpSvrName());

        // 接続するホスト名
        objPrp.put("mail.host", this.mSendMailSetting.getSmtpSvrName());

        // SMTPサーバのポート番号
        objPrp.put("mail.smtp.port", this.mSendMailSetting.getSmtpSvrPort());

        objPrp.put("mail.smtp.socketFactory.fallback", "false");

        // SMTPサーバとのコネクションＴ／Ｏ時間[ms]
        objPrp.put("mail.smtp.connectiontimeout", SMTP_SVR_CONNECTION_TIMEOUT);

        // SMTPサーバとの送受信のＴ／Ｏ時間[ms]
        objPrp.put("mail.smtp.timeout", SMTP_SVR_TRX_TIMEOUT);

        // SSL設定
        if (this.mSendMailSetting.isSsl()) {
            // objPrp.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // objPrp.setProperty("mail.smtp.socketFactory.port", this.config.getSmtpSvrPort());
        }

        if (this.mSendMailSetting.isSmtpAuth()) {
            // SMTP認証有効時
            objPrp.put("mail.smtp.auth", "true");
        } else {
            // SMTP認証禁止時
            objPrp.put("mail.smtp.auth", "false");
        }

        // 宛先に無効なアドレスが含まれていても
        // 有効なアドレスだけでも送信を試みるか否か
        objPrp.put("mail.smtp.sendpartial", "false");

        if (AppBuildConfig.DEBUG_MODE) {
            // mail.jarのデバッグメッセージ表示
            objPrp.put("mail.debug", "true");
        }

        // メールセッションを確立
        final Session session = Session.getInstance(
                objPrp,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                MailUtil.this.mSendMailSetting.getSmtpAuthUserName(),
                                MailUtil.this.mSendMailSetting.getSmtpAuthPass());
                    }
                });

        return session;
    }

    // 機能：
    // メールを作成する
    // ・メールヘッダ
    // ・件名
    // ・添付ファイル
    // ・他
    // objMsg：
    // 作成先のオブジェクト
    // return：
    // 結果
    // （失敗する場合はバグです）
    private boolean createMail(MimeMessage objMsg) {
        final MimeMultipart mpart = new MimeMultipart();

        // ------------------------------
        // ヘッダ／件名を設定
        // ------------------------------
        setMailHeader(objMsg);

        try {
            // ------------------------------
            // 本文を設定
            // ------------------------------
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            mpart.addBodyPart(contentPart);

            // ------------------------------
            // 添付ファイルを設定
            // ------------------------------
            if (mAttachmentFile != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(mAttachmentFile);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                try {
                    attachmentBodyPart
                            .setFileName(MimeUtility.encodeWord(mAttachmentFile.getName()));
                } catch (UnsupportedEncodingException e) {
                    mLogger.error("createMail", "FATAL UnsupportedEncodingException.");
                    return false;
                } catch (MessagingException e) {
                    mLogger.error("createMail", "FATAL MessagingException.");
                    return false;
                }

                mpart.addBodyPart(attachmentBodyPart);
            }
            if (mAttachmentFiles != null) {
                for (File attachmentFile : mAttachmentFiles) {
                    BodyPart attachmentBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachmentFile);
                    attachmentBodyPart.setDataHandler(new DataHandler(source));

                    try {
                        attachmentBodyPart
                                .setFileName(MimeUtility.encodeWord(attachmentFile.getName()));
                    } catch (UnsupportedEncodingException e) {
                        mLogger.error("createMail", "FATAL UnsupportedEncodingException.");
                        return false;
                    } catch (MessagingException e) {
                        mLogger.error("createMail", "FATAL MessagingException.");
                        return false;
                    }

                    mpart.addBodyPart(attachmentBodyPart);
                }

            }
            // bpart.setText(text); 本文にテキストパート添付

            // メッセージオブジェクトへマルチパートを設定

            objMsg.setContent(mpart);
        } catch (MessagingException e) {
            mLogger.error("createMail", "FATAL MessagingException.");
            return false;
        }

        return true;
    }

    // ------------------------------------
    // メールヘッダ／件名を設定する
    // arg：
    // メッセージオブジェクト
    // return：
    // なし
    // ------------------------------------
    private void setMailHeader(MimeMessage objMsg) {
        try {
            if (AppBuildConfig.DEBUG_MODE) {
                mLogger.debug("setMailHeader", "TO:" + this.mSendMailSetting.getDestMailAddr()
                        + ",From:" + this.mSendMailSetting.getSenderName()
                        + ",sub:" + this.mSendMailSetting.getMailSubject());
            }

            // Date(GMT)
            objMsg.setSentDate(new Date());

            // 送信先（TOのほか、CCやBCCも設定可能）
            objMsg.setRecipients(
                    javax.mail.Message.RecipientType.TO,
                    this.mSendMailSetting.getDestMailAddr()); // メール宛先アドレス

            // Senderヘッダ
            // 設定しない

            // Fromヘッダ
            InternetAddress objFrm = new InternetAddress(
                    this.mSendMailSetting.getDestMailAddr(), // メール宛先アドレス
                    this.mSendMailSetting.getSenderName()); // 送信者名

            objMsg.setFrom(objFrm);

            // 件名
            objMsg.setSubject(
                    this.mSendMailSetting.getMailSubject(), // 件名
                    this.mSendMailSetting.getCharaCode()); // 文字コード

            // Reply-Toヘッダ
            // 設定しない
        } catch (UnsupportedEncodingException e) {
            mLogger.error("setMailHeader", "FATAL UnsupportedEncodingException.");
        } catch (MessagingException e) {
            mLogger.error("setMailHeader", "FATAL MessagingException.");
        }
    }

    // ------------------------------------
    // メールへ１～複数のファイルを添付する
    // mpart：
    // 添付先のメッセージマルチパート
    // fileList：
    // 添付対象のファイルリスト
    // bFileName：
    // 添付ファイルの基準ファイル名
    // return：
    // なし
    // ------------------------------------
    // private void attachMultiFile(
    // MimeMultipart mpart,
    // List<Page> fileList,
    // String bFileName)
    // {
    // final Iterator<Page> ite = fileList.iterator();
    // int fileNum = this.firstFileNum;
    //
    // while(ite.hasNext())
    // {
    // final Page page = (Page)ite.next();
    //
    // // 詳細ファイルタイプ取得
    // FileTypeResult dFileType = FileTypeResult.NOT_SET;
    // if(page.getImageFormat() == ImageFormat.JPEG)
    // {
    // dFileType = FileTypeResult.TYPE_JPEG;
    // }
    // else if(page.getImageFormat() == ImageFormat.TIFF)
    // {
    // dFileType = FileTypeResult.TYPE_TIFF;
    // }
    //
    // // 添付実施
    // attachFile(
    // dFileType, // 詳細なファイルタイプ
    // mpart, // マルチボディパート
    // page.getImageData(),// 添付するファイルデータ
    // fileNum, // 当該添付ファイルのファイル番号
    // bFileName); // ファイル名
    //
    // // 添付ファイル番号をインクリメント
    // fileNum++;
    // }
    // }

    // ------------------------------------
    // メールへファイルデータを１つ添付する
    // dFileType：
    // 添付するファイルの詳細なファイルタイプ
    // PDF/TIFF/JPEG
    // mpart：
    // 添付先のメッセージマルチパート
    // fileData：
    // 添付対象のファイル（ファイルパス＋ファイル名称）
    // fileNum：
    // 当該添付ファイルのファイル番号
    // bFileName：
    // ベースファイル名称
    // これに-1や-2などのインデックスを付加してファイル名称とする
    // return：
    // なし
    // ------------------------------------
    // private void attachFile(
    // FileTypeResult dFileType,
    // MimeMultipart mpart,
    // BinaryData fileData,
    // int fileNum,
    // String bFileName)
    // {
    // if(null == fileData){
    // mLogger.error("attachFile", "ERR attachFile： fileData=null.");
    // return;
    // }
    //
    // if(fileData.isEmpty()){
    // mLogger.error("attachFile", "ERR attachFile： fileData_is_empty.");
    // return;
    // }
    //
    // if(null == bFileName){
    // mLogger.error("attachFile", "ERR attachFile： fileName=null.");
    // return;
    // }
    //
    // mLogger.error("attachFile", "attachFile： StoreLocation=" + fileData.getStoreLocation());
    //
    // // ボディパートインスタンス作成
    // final MimeBodyPart bpart = new MimeBodyPart();
    //
    // // ファイル名取得
    // final String fileName = makeFileName(
    // dFileType, // ファイルタイプ
    // bFileName, // 基準ファイル名
    // this.totalFileCntInJob, // 当該ジョブの総添付ファイル数
    // fileNum); // 当該添付ファイルのファイル番号
    //
    // try {
    // if (fileData.getStoreLocation() == StoreLocation.STORE_ON_MEMORY) {
    // // <CASE> メモリ上のファイルデータを添付する
    // bpart.setDataHandler(new DataHandler(
    // fileData.getMemoryData(), // メモリ上データのポインタ
    // getMimeType(dFileType))); // 添付ファイルのMIMEタイプ
    // } else if (fileData.getStoreLocation() == StoreLocation.STORE_IN_FILE) {
    // // <CASE> ストレージ上のデータを添付する
    //
    // // データハンドラ作成＆ボディパートへ設定
    // if (null == fileData.getFilePath()) {
    // Log.w(TAG, "attachFile failed. fileData.getFilePath() :null");
    // return;
    // }
    // FileDataSource fds = new FileDataSource(new File(fileData.getFilePath()));
    // bpart.setDataHandler(new DataHandler(fds));
    // }
    //
    // // ファイル名設定（Content-Diposition Header）
    // bpart.setFileName(fileName);
    //
    // // マルチパートへボディパートを設定
    // mpart.addBodyPart(bpart);
    // } catch (MessagingException e) {
    // e.printStackTrace();
    // }
    // }

    // -----------------------------------------
    // 機能：
    // メール送信する
    // objMsg：
    // 送信オブジェクト
    // return：
    // RESULT_OK：送信成功
    // RESULT_NG：送信失敗
    // 1以上の数値：サーバからの失敗応答コード
    // -----------------------------------------
    private MailResult transmit(MimeMessage objMsg) {
        MailResult result = null;

        try {
            // ------------------------------
            // メール送信
            // ------------------------------
            mLogger.debug("transmit", "transmit: start.");
            Transport.send(objMsg);
            mLogger.debug("transmit", "transmit: end.");
        } catch (MessagingException e) {
            mLogger.error("transmit", "transmit: MessagingException." + e);
            if (null != e) {
                String message = e.getMessage();
                String localizedMessage = e.getLocalizedMessage();
                mLogger.error("transmit", "message:" + message + ", localizedMessage:"
                        + localizedMessage);
            }
            e.printStackTrace();

            result = analyzeMessage(e);
            return result;
        }

        // 送信成功
        result = new MailResult(
                true, // 結果：成功
                MailFailReason.NO_SET, // エラー理由なし
                -1); // 応答コードなし
        return result;
    }

    // 機能
    // SMTPのエラーメッセージを解析してメール送信結果を作成する
    // 複数メッセージがチェーンしている場合にも対応する
    // msg：
    // 解析対象のメッセージ
    // return：
    // メール送信結果
    private MailResult analyzeMessage(MessagingException msg) {
        MailResult result = null;
        int respCode = -1;
        MessagingException tmp = msg;

        // 複数の応答コードが在る場合は、
        // 先頭のコードだけを検出する
        while (true) {
            if (tmp instanceof SMTPSendFailedException) {
                respCode = ((SMTPSendFailedException) tmp).getReturnCode();
                mLogger.error("analyzeMessage", "SMTPSendFailedException/respCode=" + respCode);
                if (respCode >= 0) {
                    // 応答コードが見つかった
                    break;
                }
            } else if (tmp instanceof SMTPAddressFailedException) {
                respCode = ((SMTPAddressFailedException) tmp).getReturnCode();
                mLogger.error("analyzeMessage", "SMTPAddressFailedException/respCode=" + respCode);
                if (respCode >= 0) {
                    // 応答コードが見つかった
                    break;
                }
            } else if (tmp instanceof AuthenticationFailedException) {
                mLogger.error("analyzeMessage", "AuthenticationFailedException.no_respCode.");
                result = new MailResult(
                        false, // 結果：失敗
                        MailFailReason.MAIL_ACCESS_AUTH_ERR, // エラー理由：サーバで認証失敗
                        -1); // 応答コードなし
                return result;
            }

            // 次のメッセージは在るか？
            final Exception next = tmp.getNextException();

            if (next == null
                    || !(next instanceof MessagingException)) {
                // 次メッセージは無かった
                break;
            }
            tmp = (MessagingException) next;
        }

        // 全メッセージ中に応答コードは在ったか？
        if (respCode >= 0) {
            // <CASE> 在った
            result = new MailResult(
                    false, // 結果：失敗
                    convertToReason(respCode), // エラー理由：ＮＧ応答コード受信
                    respCode); // 応答コード
            return result;
        }

        // <CASE> 無かった
        result = new MailResult(
                false, // 結果：失敗
                MailFailReason.MAIL_SVR_CONNECT_ERR, // エラー理由：TCP接続失敗
                -1); // 応答コードなし
        return result;
    }

    private MailResult makeInvalidResult() {
        return new MailResult(false, MailFailReason.INVALID_ERR, -1);
    }

    private MailFailReason convertToReason(int respCode) {
        if (is3XXCode(respCode)
                || respCode == 432
                || respCode == 454
                || respCode == 530
                || respCode == 534
                || respCode == 535
                || respCode == 538) {
            // 認証エラー（主にSMTP）
            return MailFailReason.MAIL_ACCESS_AUTH_ERR;
        } else if (respCode == 452) {
            // サーバのメモリがフル
            return MailFailReason.SVR_MEMORY_FULL;
        } else if (respCode == 450) {
            // 権限なし
            // 主にメールボックスへのアクセス権限なし
            return MailFailReason.MAILBOX_ACCESS_AUTH_ERR;
        } else if (respCode == 550) {
            // 権限なし
            // 主にメールボックスへのアクセス権限なし（致命的）
            return MailFailReason.MAILBOX_ACCESS_AUTH_FATAL_ERR;
        } else if (respCode == 421 || respCode == 551) {
            // その他エラー
            return MailFailReason.OTHER_ERR;
        } else if (is4XXCode(respCode)) {
            // 送信先との接続失敗
            return MailFailReason.MAIL_DEST_CONNECT_ERR;
        } else if (is5XXCode(respCode)) {
            // 送信先との接続失敗（サーバ要因）
            return MailFailReason.MAIL_DEST_CONNECT_FATAL_ERR;
        }

        return MailFailReason.OTHER_ERR;
    }

    private boolean is3XXCode(int respCode) {
        if (respCode >= 300 && respCode < 400) {
            return true;
        }
        return false;
    }

    private boolean is4XXCode(int respCode) {
        if (respCode >= 400 && respCode < 500) {
            return true;
        }
        return false;
    }

    private boolean is5XXCode(int respCode) {
        if (respCode >= 500 && respCode < 600) {
            return true;
        }
        return false;
    }
}
