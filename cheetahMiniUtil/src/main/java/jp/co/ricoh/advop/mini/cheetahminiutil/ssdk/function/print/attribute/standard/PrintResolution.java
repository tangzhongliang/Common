package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;

/**
 * Created by duqiang on 7/28/2016.
 */
public enum PrintResolution implements PrintRequestAttribute {
    DPI_300_300_1("300_300_1"),
    DPI_200_200_1("200_200_1"),
    DPI_600_600_1("600_600_1"),
    DPI_600_600_2("600_600_2"),
    DPI_600_600_3("600_600_3"),
    DPI_1200_1200_1("1200_1200_1"),
    DPI_1200_600_1("1200_600_1"),
    ;
    private final String value;
    private static final String NAME_PRINT_RESOLUTION = "printResolution";

    PrintResolution(String value) {
        this.value = value;
    }

    @Override
    public Class<?> getCategory() {
        return PrintResolution.class;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getName() {
        return NAME_PRINT_RESOLUTION;
    }

    private static volatile Map<String, PrintResolution> directory = null;

    private static Map<String, PrintResolution> getDirectory() {
        if (directory == null) {
            Map<String, PrintResolution> d = new HashMap<String, PrintResolution>();
            for (PrintResolution resolution : values()) {
                d.put(resolution.getValue().toString(), resolution);
            }
            directory = d;
        }
        return directory;
    }

    private static PrintResolution fromString(String value) {
        return getDirectory().get(value);
    }

    public static List<PrintResolution> getSupportedValue(List<String> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        List<PrintResolution> list = new ArrayList<PrintResolution>();
        for (String value : values) {
            PrintResolution resolution = fromString(value);
            if (resolution != null) {
                list.add(resolution);
            }
        }
        return list;
    }
}
