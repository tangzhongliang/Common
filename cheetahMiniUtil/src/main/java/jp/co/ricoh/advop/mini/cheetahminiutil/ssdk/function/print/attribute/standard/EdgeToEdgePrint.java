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
public enum EdgeToEdgePrint implements PrintRequestAttribute {
    TRUE(true),
    FALSE(false)
    ;
    private final Boolean value;
    private static final String EDGE_TO_EDGE_PRINT = "edgeToEdgePrint";
    EdgeToEdgePrint(Boolean value) {
        this.value = value;
    }


    @Override
    public Class<?> getCategory() {
        return EdgeToEdgePrint.class;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getName() {
        return EDGE_TO_EDGE_PRINT;
    }

    private static volatile Map<String, EdgeToEdgePrint> directory = null;

    private static Map<String, EdgeToEdgePrint> getDirectory() {
        if (directory == null) {
            Map<String, EdgeToEdgePrint> d = new HashMap<String, EdgeToEdgePrint>();
            for (EdgeToEdgePrint edgeToEdgePrint : values()) {
                d.put(edgeToEdgePrint.getValue().toString(), edgeToEdgePrint);
            }
            directory = d;
        }
        return directory;
    }

    private static EdgeToEdgePrint fromString(Boolean value) {
        return getDirectory().get(value);
    }

    public static List<EdgeToEdgePrint> getSupportedValue(List<Boolean> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        List<EdgeToEdgePrint> list = new ArrayList<EdgeToEdgePrint>();
        for (Boolean value : values) {
            EdgeToEdgePrint edgeToEdgePrint = fromString(value);
            if (edgeToEdgePrint != null) {
                list.add(edgeToEdgePrint);
            }
        }
        return list;
    }

}
