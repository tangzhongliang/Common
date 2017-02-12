package jp.co.ricoh.advop.mini.cheetahminiutil.util;


/**
 * ブザー制御を提供する。<BR>
 */
public class Buzzer {
    /** 入力確認音 */
    public static final int BUZZER_ACK = 0;
    /** 入力無効音 */
    public static final int BUZZER_NACK = 1;
    /** 基点音 */
    public static final int BUZZER_BASED = 2;
    /** 正常終了音 */
    public static final int BUZZER_COMPLETED = 3;
    /** 準備完了音 */
    public static final int BUZZER_READY = 4;
    /** 弱注意音1 */
    public static final int BUZZER_CAUTION_L1 = 5;
    /** 弱注意音2 */
    public static final int BUZZER_CAUTION_L2 = 6;
    /** 弱注意音3 */
    public static final int BUZZER_CAUTION_L3 = 7;
    /** 強注意音 */
    public static final int BUZZER_CAUTION_H1 = 8;

    /** ブザー制御クラスのインスタンス(SDKService.jarから提供) */
    private static final jp.co.ricoh.isdk.sdkservice.panel.Buzzer buzzer = jp.co.ricoh.isdk.sdkservice.panel.Buzzer.getInstance();

    /**
     * 入力確認音のブザーを鳴動する。<BR>
     */
    public static void play() {
        buzzer.play(BUZZER_ACK);
    }

    /**
     * 指定されたブザーパターンでブザーを鳴動する。<BR>
     * @param toneType ブザーパターン
     */
    public static void play(int toneType) {
        buzzer.play(toneType);
    }

    /**
     * 指定されたブザーパターンでブザーを鳴動する。<BR>
     * @param toneType ブザーパターン
     */
    public static void onBuzzer(int toneType) {
        play(toneType);
    }
}
