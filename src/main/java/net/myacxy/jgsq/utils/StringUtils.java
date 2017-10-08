package net.myacxy.jgsq.utils;

public class StringUtils {
    private static final String OUT_OF_BAND = "\u00FF\u00FF\u00FF\u00FF";

    private StringUtils() {
        throw new IllegalAccessError();
    }

    /**
     * <p>^0 = black</p>
     * <p>^1 = red</p>
     * <p>^2 = green</p>
     * <p>^3 = yellow</p>
     * <p>^4 = blue</p>
     * <p>^5 = cyan</p>
     * <p>^6 = purple</p>
     * <p>^7 = white</p>
     * <p>^8 = orange</p>
     * <p>^9 = gray</p>
     *
     * @param coloredString
     * @return uncolored string
     */
    public static String removeColorCode(String coloredString) {
        return coloredString.replaceAll("(\\^\\d)|(\\$\\d)", "");
    }

    public static String prependOutOfBand(String string) {
        return OUT_OF_BAND + string;
    }
}
