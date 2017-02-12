package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.print.JobSettingSupportedHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.MediaSizeName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintFile;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.HashPrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Copies;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.Magnification;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PaperSize;
import jp.co.ricoh.advop.mini.cheetahminiutil.ui.dialog.MultiButtonDialog;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Copyright (C) 2013-2016 RICOH Co.,LTD
 * All rights reserved
 */

public class ScanPrintUtil {
    public static void startPrint(Activity activity,final String path) {
        final MultiButtonDialog processingDlg = MultiButtonDialog.createNoButtonDialog(activity, activity.getString(R.string.printing));
        processingDlg.show();
        final PrintRequestAttributeSet attributeSet = getPrintRequestAttributeSet(path);

        new AsyncTask<Void, Void, JobManager.EXE_RESULT>() {

            @Override
            protected JobManager.EXE_RESULT doInBackground(Void... params) {
                JobManager.EXE_RESULT ret = CHolder.instance().getJobManager().sendPrintJobSync(attributeSet, path);
                LogC.i("jobresult:" + ret);
                return ret;
            }

            @Override
            protected void onPostExecute(JobManager.EXE_RESULT aVoid) {
                super.onPostExecute(aVoid);
                processingDlg.dismiss();
            }
        }.execute();
    }

    public static PrintRequestAttributeSet getPrintRequestAttributeSet(String path) {
        final PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        Set<Class<? extends PrintRequestAttribute>> categories;
        Map<PrintFile.PDL, JobSettingSupportedHolder> mSettingDataHolders = CHolder.instance().getPrintManager().getmSettingDataHolders();
        categories = mSettingDataHolders.get(CHolder.instance().getPrintManager().getPDL(path)).getSelectableCategories();

        if (null != categories) {
            if (categories.contains(Copies.class)) {
                attributeSet.add(new Copies(1));
            } else {
                // do nothing
            }

            if (categories.contains(PaperSize.class)) {
                List<PaperSize> paperSizesList = mSettingDataHolders.get(CHolder.instance().getPrintManager().getPDL(path)).getmSupportedPaperSizeList();

                for (PaperSize paperSize : paperSizesList) {
                    if (paperSize.toString().equals(MediaSizeName.ISO_A4.getValue())) {
                        attributeSet.add(new PaperSize(MediaSizeName.ISO_A4));
                        break;
                    } else if (paperSize.toString().equals(MediaSizeName.ISO_A4_LANDSCAPE.getValue())) {
                        attributeSet.add(new PaperSize(MediaSizeName.ISO_A4_LANDSCAPE));
                        break;
                    } else {
                        // do nothing
                    }
                }
            } else {
                // do nothing
            }
        }
        attributeSet.add(new Magnification("fitting"));
        return attributeSet;
    }
}
