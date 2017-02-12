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
public enum PaperSizeCustomY implements PrintRequestAttribute {
    height_2480("2480"),
    height_3508("3508")
    ;
    private static final String TAG = "Custom size: Length";
    private final String PAPER_SIZE = "Custom size: Length";
    //    private String PaperSizeCustomY;
    private final String value;

    PaperSizeCustomY(String val) {
        LogC.d(TAG, "PaperSize >> " + val);
        this.value = val;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Class<?> getCategory() {
        return PaperSizeCustomY.class;
    }

    @Override
    public String getName() {
        return PAPER_SIZE;
    }

    private static volatile Map<String, PaperSizeCustomY> directory = null;

    private static Map<String, PaperSizeCustomY> getDirectory() {
        if (directory == null) {
            Map<String, PaperSizeCustomY> d = new HashMap<String, PaperSizeCustomY>();
            for (PaperSizeCustomY size : values()) {
                d.put(size.getValue().toString(), size);
            }
            directory = d;
        }
        return directory;
    }

    public static PaperSizeCustomY fromString(String value) {
        return getDirectory().get(value);
    }

    public static List<PaperSizeCustomY> getSupportedValue(List<String> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        List<PaperSizeCustomY> list = new ArrayList<PaperSizeCustomY>();
        for (String value : values) {
            LogC.d(TAG, "supported paper size === " + String.valueOf(value));
            PaperSizeCustomY size = fromString(String.valueOf(value));
            if (size != null) {
                list.add(size);
            }
        }

        return list;
    }
}