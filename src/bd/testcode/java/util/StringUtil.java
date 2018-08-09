package bd.testcode.java.util;

import com.sun.istack.internal.Nullable;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 8. 9. created by seungtae.hwang
 */
public class StringUtil {

    /**
     * check if any item of String is empty.
     * @param args
     * @return
     */
    public static boolean isAnyEmpty(String... args) {
        if (args == null) {
            return true;
        }
        for (String str : args) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
