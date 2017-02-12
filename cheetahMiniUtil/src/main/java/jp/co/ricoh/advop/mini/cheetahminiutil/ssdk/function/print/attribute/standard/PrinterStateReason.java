/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintServiceAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * プリンタ状態の詳細を示す列挙型です
 * The enum to indicate print service state reason.
 */
public enum PrinterStateReason implements PrintServiceAttribute {

    /**
     * デバイスのカバーが開いています。
     * Device cover is open.
     */
    COVER_OPEN("cover_open"),

    /**
     * 現像剤がなくなりました。
     * Developer is empty.
     */
    DEVELOPER_EMPTY("developer_empty"),

    /**
     * 現像剤が残り少なくなりました。
     * Developer is almost empty.
     */
    DEVELOPER_LOW("developer_low"),

    /**
     * 給紙トレーがデバイスに挿入されていません。
     * Paper tray is not set to the device.
     */
    INPUT_TRAY_MISSING("input_tray_missing"),

    /**
     * フォントやフォームなどのインタプリタリソースが利用できません。
     * Interpreter resources (e.g. fonts, forms) are unavailable.
     */
    INTERPRETER_RESOURCE_UNAVAILABLE("interpreter_resource_unavailable"),

    /**
     * マーカーサプライの空き容器収納部がもうすぐいっぱいになります。
     * Marker supply waste receptacle is almost full.
     */
    MARKER_WASTE_ALMOST_FULL("marker_waste_almost_full"),

    /**
     * マーカーサプライの空き容器収納部がいっぱいです。
     * Marker supply waste receptacle is full.
     */
    MARKER_WASTE_FULL("marker_waste_full"),

    /**
     * 空になっている給紙トレーがあります。
     * A tray is empty.
     */
    MEDIA_EMPTY("media_empty"),

    /**
     * デバイスで紙詰まりが発生しています。
     * Paper jam occurred.
     */
    MEDIA_JAM("media_jam"),

    /**
     * 光導電体が使用できなくなりました
     * OPC life is over.
     */
    OPC_LIFE_OVER("opc_life_over"),

    /**
     * 光導電体の交換時期が近づいています。
     * OPC needs to be replaced soon.
     */
    OPC_NEAR_EOL("opc_near_eol"),

    /**
     * 下記のエラー以外のエラーが検出されました。
     * Other error has been detected.
     */
    OTHER("other"),

    /**
     * いっぱいになっている排紙エリアがあります。
     * An output tray is full.
     */
    OUTPUT_AREA_FULL("output_area_full"),

    /**
     * 排紙トレーがデバイスに挿入されていません。
     * Output tray is not set to the device.
     */
    OUTPUT_TRAY_MISSING("output_tray_missing"),

    /**
     * ジョブが一時停止し、statusがstopped です。
     * Printer has paused and the state is "stopped"
     */
    PAUSED("paused"),

    /**
     * 複数の出力デバイスをプリンタが制御している場合、この理由は 1 つ以上の出力デバイスが停止していることを示します。
     * If printer controls multiple output devices, this indicates that one or more output device is being stopped.
     */
    STOPPED_PARTLY("stopped_partly"),

    /**
     * トナーがなくなりました。
     * Toner is empty.
     */
    TONER_EMPTY("toner_empty"),

    /**
     * トナーが残り少なくなりました。
     * Toner is almost empty.
     */
    TONER_LOW("toner_low"),

    /**
     * 送信履歴満杯であり、送信履歴の出力が必要です。
     * Communication log is full. Log output is needed.
     */
    COMMUNICATION_LOG_FULL("communication_log_full");


    private final String mPrinterStateReason;

    private PrinterStateReason(String value) {
        this.mPrinterStateReason = value;
    }

    @Override
    public String toString() {
        return mPrinterStateReason;
    }

    @Override
    public Class<?> getCategory() {
        return getClass();
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }


    private static volatile Map<String, PrinterStateReason> reasons = null;

    private static Map<String, PrinterStateReason> getReasons() {
        if(reasons == null) {
            PrinterStateReason[] reasonArray = values();
            Map<String, PrinterStateReason> r = new HashMap<String, PrinterStateReason>();
            for(PrinterStateReason reason : reasonArray) {
                r.put(reason.mPrinterStateReason, reason);
            }

            reasons = r;
        }
        return reasons;
    }

    public static PrinterStateReason fromString(String value) {
        return getReasons().get(value);
    }

}
