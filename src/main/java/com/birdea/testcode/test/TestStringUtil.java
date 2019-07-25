package com.birdea.testcode.test;


import com.birdea.testcode.util.L;
import com.birdea.testcode.util.StringUtil;
import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 8. 9. created by seungtae.hwang
 */
public class TestStringUtil {

    @Test
    public void test() {
        String a = null;
        String b = "";
        String c = "c";
        String d = "d";

        L.msg(null+" isAnyEmpty? " + StringUtil.isAnyEmpty(null));
        L.msg(a+" isAnyEmpty? " + StringUtil.isAnyEmpty(a));
        L.msg(a+","+b+" isAnyEmpty? " + StringUtil.isAnyEmpty(a, b));
        L.msg(b+","+c+" isAnyEmpty? " + StringUtil.isAnyEmpty(b, c));
        L.msg(c+","+d+" isAnyEmpty? " + StringUtil.isAnyEmpty(c, d));
        L.msg(c+","+d+" hasAll? " + !StringUtil.isAnyEmpty(c, d));
    }
}
