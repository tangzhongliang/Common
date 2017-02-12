package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;

/**
 * Created by duqiang on 7/29/2016.
 */
public enum PrintSide implements PrintRequestAttribute {
    ONE_SIDED("one_sided"),
    DUPLEX("top_to_top")
    ;
    private final String value;
    private static final String PRINT_SIDE = "printSide";
    PrintSide(String value) {
        this.value = value;
    }


    @Override
    public Class<?> getCategory() {
        return PrintSide.class;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getName() {
        return PRINT_SIDE;
    }

    private static volatile Map<String, PrintSide> directory = null;

    private static Map<String, PrintSide> getDirectory() {
        if (directory == null) {
            Map<String, PrintSide> d = new HashMap<String, PrintSide>();
            for (PrintSide printSide : values()) {
                d.put(printSide.getValue().toString(), printSide);
            }
            directory = d;
        }
        return directory;
    }

    private static PrintSide fromString(String value) {
        return getDirectory().get(value);
    }

    public static List<PrintSide> getSupportedValue(List<String> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        List<PrintSide> list = new ArrayList<PrintSide>();
        for (String value : values) {
            PrintSide printSide = fromString(value);
            if (printSide != null) {
                list.add(printSide);
            }
        }
        return list;
    }

}
