/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.print;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.MediaSizeName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintFile;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintService;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Copies;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PaperSize;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PaperTray;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintColor;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.supported.MaxMinSupported;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.status.GetTraysResponseBody;


/**
 * コピー設定情報クラスです。
 * Print setting data class.
 */
public class JobSettingSupportedHolder {

    private Set<Class<? extends PrintRequestAttribute>> mSelectableCategories;


    /**
     * 印刷カラー設定可能値の表示文字列IDのリストです。
     * List of display string ID for the available print color setting values.
     */
    private List<PrintColor> mSupportedPrintColorList;

    /**
     * 用紙トレイ設定可能値の表示文字列IDのリストです。
     * List of display string ID for the available paper tray setting values.
     */
    private List<PaperTray> mSupportedPaperTrayList;

    /**
     * 用紙サイズ設定可能値の表示文字列IDのリストです。
     * List of display string ID for the available paper size setting values.
     */
    private List<PaperSize> mSupportedPaperSizeList;

    public final static HashMap<PaperTray, Integer> PAPER_TRAY_Text;
    public final static HashMap<PrintColor, Integer> PRINT_COLOR_Text;
    static {
        PAPER_TRAY_Text = new HashMap<PaperTray, Integer>();
        PAPER_TRAY_Text.put(PaperTray.TRAY1, R.string.txid_print_b_top_tray_tray1);
        PAPER_TRAY_Text.put(PaperTray.TRAY2, R.string.txid_print_b_top_tray_tray2);
        PAPER_TRAY_Text.put(PaperTray.TRAY3, R.string.txid_print_b_top_tray_tray3);
        PAPER_TRAY_Text.put(PaperTray.TRAY4, R.string.txid_print_b_top_tray_tray4);
        PAPER_TRAY_Text.put(PaperTray.TRAY5, R.string.txid_print_b_top_tray_tray5);
        PAPER_TRAY_Text.put(PaperTray.TRAY6, R.string.txid_print_b_top_tray_tray6);
        PAPER_TRAY_Text.put(PaperTray.TRAY7, R.string.txid_print_b_top_tray_tray7);
        PAPER_TRAY_Text.put(PaperTray.TRAY8, R.string.txid_print_b_top_tray_tray8);
        PAPER_TRAY_Text.put(PaperTray.TRAY9, R.string.txid_print_b_top_tray_tray9);
        PAPER_TRAY_Text.put(PaperTray.TRAY10, R.string.txid_print_b_top_tray_tray10);
        PAPER_TRAY_Text.put(PaperTray.TRAY_A, R.string.txid_print_b_top_tray_trayA);
        PAPER_TRAY_Text.put(PaperTray.MANUAL, R.string.txid_print_b_top_tray_bypass);
        PAPER_TRAY_Text.put(PaperTray.AUTO, R.string.txid_print_b_top_tray_auto);
        PAPER_TRAY_Text.put(PaperTray.LARGE_CAPACITY, R.string.txid_print_b_top_tray_large_capacity);

        PRINT_COLOR_Text  = new HashMap<PrintColor, Integer>();
        PRINT_COLOR_Text.put(PrintColor.AUTO_COLOR, R.string.txid_print_auto_color);
        PRINT_COLOR_Text.put(PrintColor.MONOCHROME, R.string.txid_print_monochrome);
        PRINT_COLOR_Text.put(PrintColor.COLOR, R.string.txid_print_color);
        PRINT_COLOR_Text.put(PrintColor.RED_AND_BLACK, R.string.txid_print_red_and_black);
        PRINT_COLOR_Text.put(PrintColor.TWO_COLOR, R.string.txid_print_two_color);
        PRINT_COLOR_Text.put(PrintColor.SINGLE_COLOR, R.string.txid_print_single_color);
    }

    /**
     * コンストラクタです。
     * Constructor
     */
    public JobSettingSupportedHolder(PrintService service, PrintFile.PDL pdl) {
        mSupportedPrintColorList = new ArrayList<PrintColor>();
        mSupportedPaperTrayList = new ArrayList<PaperTray>();
        mSupportedPaperSizeList = new ArrayList<PaperSize>();
        init(service, pdl);
    }

    //DongJingru modified on 2014/05/18
    private void setSupportedPrintColorList(List<PrintColor> printColorList) {
        mSupportedPrintColorList.clear();
        if (printColorList == null) return;

        final List<PrintColor> printColorListForUI = new ArrayList<PrintColor>();
        if (printColorList.contains(PrintColor.AUTO_COLOR))
            printColorListForUI.add(PrintColor.AUTO_COLOR);
        if (printColorList.contains(PrintColor.COLOR))
            printColorListForUI.add(PrintColor.COLOR);
        if (printColorList.contains(PrintColor.MONOCHROME))
            printColorListForUI.add(PrintColor.MONOCHROME);
        mSupportedPrintColorList.addAll(printColorListForUI);
    }


    private void setSupportedPaperTrayList(List<PaperTray> paperTrayList) {

        mSupportedPaperTrayList.clear();

        if (paperTrayList == null) return;

        final PaperTray[] PAPER_TRAY_UI_LIST = {
                PaperTray.LARGE_CAPACITY,
                PaperTray.AUTO,
                PaperTray.MANUAL,
                PaperTray.TRAY1,
                PaperTray.TRAY2,
                PaperTray.TRAY3,
                PaperTray.TRAY4,
                PaperTray.TRAY5,
                PaperTray.TRAY6,
                PaperTray.TRAY7,
                PaperTray.TRAY8,
                PaperTray.TRAY9,
                PaperTray.TRAY10,
                PaperTray.TRAY_A
        };

        for (int i = 0; i < PAPER_TRAY_UI_LIST.length; i++) {
            PaperTray paperTray = PAPER_TRAY_UI_LIST[i];
            if (paperTrayList.contains(paperTray))
                mSupportedPaperTrayList.add(paperTray);
        }
        if(mSupportedPaperTrayList.indexOf(PaperTray.MANUAL) >= 0){
            mSupportedPaperTrayList.remove(PaperTray.MANUAL);
            mSupportedPaperTrayList.add(PaperTray.MANUAL);
        }
    }

    private void setSupportedPaperSizeList(List<PaperSize> paperSizeList) {
        mSupportedPaperSizeList.clear();
        if (paperSizeList == null) return;

        final List<PaperSize> PaperSizeListForUI = new ArrayList<PaperSize>();
        for(PaperSize paperSize : paperSizeList) {
            PaperSizeListForUI.add(paperSize);
        }

        mSupportedPaperSizeList.addAll(PaperSizeListForUI);
    }

    /**
     * Serviceから各設定の設定可能値一覧を取得します。
     * Obtains the list of available setting values from ScanService.
     */
    @SuppressWarnings("unchecked")
    public void init(PrintService service, PrintFile.PDL pdl) {
//        service.clear();

        mSelectableCategories = getSelectableCategory(service, pdl);
        List<PrintColor> printColorList = (List<PrintColor>) service.
                getSupportedAttributeValues(pdl, PrintColor.class);
        List<PaperTray> paperTrayList = (List<PaperTray>) service.
                getSupportedAttributeValues(pdl, PaperTray.class);
        List<PaperSize> paperSizeList = (List<PaperSize>) service.
                getSupportedAttributeValues(pdl, PaperSize.class);
        setSupportedPrintColorList(printColorList);
        setSupportedPaperTrayList(paperTrayList);
        setSupportedPaperSizeList(paperSizeList);
    }

    public List<PrintColor> getSupportedPrintColorList() {
        return mSupportedPrintColorList;
    }

    public List<PaperTray> getSupportedPaperTrayList() {
        return mSupportedPaperTrayList;
    }

    public List<PaperSize> getmSupportedPaperSizeList() {
        return mSupportedPaperSizeList;
    }

    /**
     * サービスから指定可能な属性カテゴリのリストを取得します。
     * Obtains the list of the supported attribute categories from the service.
     * @param service
     * @param pdl
     * @return
     */
    private Set<Class<? extends PrintRequestAttribute>> getSelectableCategory(PrintService service, PrintFile.PDL pdl){
        return service.getSupportedAttributeCategories(pdl);
    }

    /**
     * 指定可能な属性カテゴリのセットを取得します。
     * Get the set of the supported print attribute setting categories.
     * @return
     */
    public Set<Class<? extends PrintRequestAttribute>> getSelectableCategories() {
        return this.mSelectableCategories;
    }
}
