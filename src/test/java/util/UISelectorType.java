package util;

/**
 * Created by User on 10/11/2014.
 */
public enum UISelectorType {
    RESOURCE_ID("resourceID"), TEXT_CONTAIN("textContain"), TEXT("text"), TEXT_START_WITH("textStartWith");
    private String UISelectorCode;

    /**
     *
     * @param s
     */
    private UISelectorType (String s) {
        UISelectorCode=s;
    }

    /**
     *
     * @return
     */
    public String getUISelectorCode(){
        return UISelectorCode;
    }

    /**
     *
     * @param selectorTypeStr
     * @return
     */
    public static UISelectorType fromString(String selectorTypeStr) {
        if (selectorTypeStr != null) {
            for (UISelectorType b : UISelectorType.values()) {
                if (selectorTypeStr.equalsIgnoreCase(b.UISelectorCode)) {
                    return b;
                }
            }
        }
        return null;
    }
}
