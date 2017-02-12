package jp.co.ricoh.advop.mini.cheetahminiutil.print;

import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

public class PrintJobImplListener implements PrintJobListener {

    @Override
    public void jobCanceled(PrintJobEvent event) {
        LogC.i(getString(event));
    }

    @Override
    public void jobCompleted(PrintJobEvent event) {

        LogC.i(getString(event));
    }

    @Override
    public void jobAborted(PrintJobEvent event) {
        LogC.i(getString(event));
    }

    @Override
    public void jobProcessing(PrintJobEvent event) {
        LogC.i(getString(event));
    }

    @Override
    public void jobPending(PrintJobEvent event) {
        LogC.i(getString(event));
    }

    protected String getString(PrintJobEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        List<PrintJobAttribute> list = event.getAttributeSet().getList();
        for (PrintJobAttribute printJobAttribute : list) {
            stringBuffer.append(printJobAttribute.toString() + "/n");
        }
        String string = stringBuffer.toString();
        return string;
    }

    @Override
    public void jobProcessingStop(PrintJobEvent event) {
        LogC.i(getString(event));
    }
}