package bd.testcode.java.tests;

import bd.testcode.java.util.L;
import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 8. 22. created by seungtae.hwang
 */
public class TestStringSplit {

    @Test
    public void test_1() {
        String raw = "오전||오후";
        String delimeter = "\\|\\|";
        String deliNull = "";

        L.msg("contain empty string?"+raw.contains(deliNull));


        split("오전||오후", "");
        split("오전||오후", "||");
        split("오전||오후", "\\|\\|");
        contain("오전||오후", " ");
        contain("오전||오후", "||");
        contain("오전||오후", "\\|\\|");
    }

    private void contain(String raw, String delimeter) {
        L.msg("contain?"+raw.contains(delimeter)+", raw:"+raw+", deli:"+delimeter);
    }

    private void split(String raw, String delimeter) {
        String[] strArray = raw.split(delimeter);
        for (String item : strArray) {
            L.msg("item:"+item);
        }
        L.msg("-----------------------------------");
    }

}
