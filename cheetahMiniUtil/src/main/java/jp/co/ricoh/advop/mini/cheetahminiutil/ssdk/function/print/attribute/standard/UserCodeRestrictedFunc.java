
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard;

public enum UserCodeRestrictedFunc {
    ALL_COLOR("all_color"),
    TWO_COLOR("two_color"),
    COLOR("color"),
    AUTO_REGISTER("auto_register"),
    NONE("none");

    private static String ID = "restrictedFunc";

    public static String getID() {
        return ID;
    }

    private String VALUE;

    UserCodeRestrictedFunc(String value) {
        this.VALUE = value;
    }

    public static UserCodeRestrictedFunc getValue(String value) {
        for (UserCodeRestrictedFunc d : UserCodeRestrictedFunc.values()) {
            if (d.VALUE.equals(value)) {
                return d;
            }
        }

        return null;
    }

    public String getValue() {
        return VALUE;
    }
}
