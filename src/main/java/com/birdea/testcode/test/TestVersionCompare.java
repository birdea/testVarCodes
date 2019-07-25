package com.birdea.testcode.test;

import com.birdea.testcode.util.L;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 12. 18. created by seungtae.hwang
 */
public class TestVersionCompare {

    private final static String VERSION_OF_CALL_FUNCTION_ADD = "2.0.0";

    public void tests() {

        L.msg("-- doFirstLast start --");
        String[] versions = {
                //"1",
                //"1.1.",
                //"1.0.8",
                //"1.9.9",
                //"2.0",
                //"2.0.1",
                //"2.1.1",
                //"3.1",
                //"4",
                //"4.1.23.123.2.1.", // true
                //"1.1.2.3.1.1", // false
                //
                "1.1.0",
                "1.99.99",
//                "2.0.0",
//                "2.0.1",
//                "2.1.1",
//                "3.0.0",
//                "a.d.fd.f.df.df",
//                "2.0.5_stg",
//                "",
//                null

        };
        for (String ver : versions) {
            L.msg("--------------------");
            //boolean result = isNeedShowCallTutorial(ver);
            //L.msg("- result:"+result);
            L.msg("- version:"+ver+" vs "+VERSION_OF_CALL_FUNCTION_ADD);
            boolean isUpdated = isUpdated(ver, VERSION_OF_CALL_FUNCTION_ADD);
            L.msg("- isUpdated:"+isUpdated);
        }
        L.msg("-- doFirstLast end --");
    }

    public static boolean isUpdated(String currentVer, String previousVer) {
        if (!isValid(currentVer, previousVer)) {
            L.msg("Not Valid!!! "+currentVer+","+previousVer);
            return false;
        }
        int[] current = getVersionArray(currentVer);
        int[] previous = getVersionArray(previousVer);
        return isBigger(current, previous);
    }

    public static boolean isSmaller(String currentVer, String previousVer) {
        if (!isValid(currentVer, previousVer)) {
            return false;
        }
        int[] current = getVersionArray(currentVer);
        int[] previous = getVersionArray(previousVer);
        return isSmaller(current, previous);
    }

    private static boolean isValid(String... values) {
        for (String val : values) {
            if (val == null || val.length() == 0) {
                return false;
            }
        }
        return true;
    }

    private static final String REG_ONLY_INTEGER = "[^-?0-9]+";

    private static int[] getVersionArray(String version) {
        String[] elemVersion = version.split("\\.");
        int[] arrayVersion = new int[elemVersion.length];
        for (int i=0; i<elemVersion.length; i++) {
            try {
                String elem = elemVersion[i].replaceAll(REG_ONLY_INTEGER, "");
                arrayVersion[i] = Integer.parseInt(elem);
            } catch (NumberFormatException e) {
                arrayVersion[i] = 0;
            }
            L.msg("get [i:"+i+"]="+arrayVersion[i]);
        }
        return arrayVersion;
    }

    private static boolean isSmaller(int[] current, int[] base) {
        int lengthMin = Math.min(base.length, current.length);
        for (int i=0; i<lengthMin; i++) {
            if (current[i] < base[i]) {
                return true;
            }
            else if (current[i] > base[i]) {
                return false;
            }
        }
        return false;
    }

    private static boolean isBigger(int[] current, int[] previous) {
        int lengthMin = Math.min(current.length, current.length);
        for (int i=0; i<lengthMin; i++) {
            L.msg("isBigger : "+current[i]+" , "+previous[i]);
            if (current[i] > previous[i]) {
                return true;
            }
            else if (current[i] < previous[i]) {
                return false;
            }
        }
        return false;
    }

}
