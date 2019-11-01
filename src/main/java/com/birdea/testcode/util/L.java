package com.birdea.testcode.util;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class L {
    public static void d(String tag, Object msg) {
        msg(tag, msg);
    }
    public static void i(String tag, Object msg) {
        msg(tag, msg);
    }
    public static void w(String tag, Object msg) {
        msg(tag, msg);
    }
    public static void msg(Object msg) {
        System.out.println(String.valueOf(msg));
    }
    public static void msg(String tag, Object msg) {
        msg(tag+"> "+msg);
    }

}
