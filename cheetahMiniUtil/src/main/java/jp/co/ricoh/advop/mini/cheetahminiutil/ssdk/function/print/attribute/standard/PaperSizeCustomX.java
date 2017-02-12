package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Created by duqiang on 8/26/2016.
 */
public enum  PaperSizeCustomX implements PrintRequestAttribute{
    width_2480("2480"),
    width_3508("3508")
    ;
    private static final String TAG = "Custom size: Width";
    private final String PAPER_SIZE = "Custom size: Width";
//    private String PaperSizeCustomX;
    private final String value;

    PaperSizeCustomX(String val) {
        LogC.d(TAG, "PaperSize >> " + val);
        this.value = val;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Class<?> getCategory() {
        return PaperSizeCustomX.class;
    }

    @Override
    public String getName() {
        return PAPER_SIZE;
    }

    private static volatile Map<String, PaperSizeCustomX> directory = null;

    private static Map<String, PaperSizeCustomX> getDirectory() {
        if (directory == null) {
            Map<String, PaperSizeCustomX> d = new HashMap<String, PaperSizeCustomX>();
            for (PaperSizeCustomX size : values()) {
                d.put(size.getValue().toString(), size);
            }
            directory = d;
        }
        return directory;
    }

    public static PaperSizeCustomX fromString(String value) {
        return getDirectory().get(value);
    }

    public static List<PaperSizeCustomX> getSupportedValue(List<String> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        List<PaperSizeCustomX> list = new ArrayList<PaperSizeCustomX>();
        for (String value : values) {
            LogC.d(TAG, "supported paper size === " + String.valueOf(value));
            PaperSizeCustomX size = fromString(String.valueOf(value));
            if (size != null) {
                list.add(size);
            }
        }

        return list;
    }
}
