package bd.testcode.java.tests;

import bd.testcode.java.util.L;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 12. 18. created by seungtae.hwang
 */
public class TestVersionCompare {

    private final static String VERSION_SINCE_CALL_FUNCTION = "2.0.0";

    public void tests() {

        L.msg("-- tests start --");
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
                "2.0.0",
                "2.0.1",
                "2.1.1",
                "3.0.0",
                "a.d.fd.f.df.df",
                "2.0.5_stg"
        };
        for (String ver : versions) {
            L.msg("--------------------");
            //boolean result = isNeedShowCallTutorial(ver);
            //L.msg("- result:"+result);
            L.msg("- version:"+ver+" vs "+VERSION_SINCE_CALL_FUNCTION);
            boolean isBigger = isBigger(getVersionArray(ver), getVersionArray(VERSION_SINCE_CALL_FUNCTION));
            L.msg("- isBigger:"+isBigger);
        }
        L.msg("-- tests end --");
    }

    public boolean isNeedShowCallTutorial(String version) {
        //if (JuniorPreferenceManager.getInstance().isShownCallTutorial()){
        //   BLog.d(TAG, "isNeedShowCallTutorial() already shown..");
        //    return false;
        //}
        //String version = "2.0.0";//Utils.getVersion(context); // "2.0.0"

        L.msg("isNeedShowCallTutorial() version:"+VERSION_SINCE_CALL_FUNCTION+" vs "+version);

        int[] versionSince = getVersionArray(VERSION_SINCE_CALL_FUNCTION);
        int[] versionCurrent = getVersionArray(version);

        if (isSmaller(versionSince, versionCurrent)) {
            return false;
        }
        return true;
    }

    private static final String REG_ONLY_INTEGER = "[^-?0-9]+";

    private int[] getVersionArray(String version) {
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

    private boolean isSmaller(int[] current, int[] base) {
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

    private boolean isBigger(int[] current, int[] previous) {
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
