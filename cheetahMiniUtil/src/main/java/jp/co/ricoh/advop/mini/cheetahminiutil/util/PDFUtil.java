package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.jpg2pdf.jpeg2pdf;

/**
 * Created by baotao on 8/9/2016.
 */
public class PDFUtil {

    public static Boolean JpegToPdf(String pdfPath, List<String> jpgPathList, boolean isReadable) {
        LogC.e("JpegToPdf");
//        File file = new File(jpgPath);
//        width = 210mm / 25.4 * 72 = 595.3 pt
//        height = 297mm / 25.4 * 72 = 842 pt
//        A4 = 297mm * 210 mm
        long pdfW = 595;
        long pdfH = 842;
        if (!isReadable) {
            pdfW = 842;
            pdfH = 595;
        }

//        if (Util.getDefaultDest().equalsIgnoreCase("na")) {//8 1/2×11
//            pdfW = 612;
//            pdfH = 792;
//        }
//        if (file.exists()) {
        jpeg2pdf test = new jpeg2pdf();
        test.start(pdfPath);
        test.setPaperSize(pdfW, pdfH);
        for (String path : jpgPathList) {
            test.addPageJpeg(path);
        }
        test.end();
        LogC.e("JpegToPdf file exist");
        return true;
//        }

//        return false;
    }

}
