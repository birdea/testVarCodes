package com.birdea.testcode;

import com.birdea.testcode.test.KtJsonValue;
import com.birdea.testcode.test.TestVersionCompare;
import com.birdea.testcode.util.Chapter3_1Kt;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class JavaMain {

    public static void main(String[] args) {
//        TestGlobalAssign tga = new TestGlobalAssign();
//        tga.testGa();

//        TestSyncCall tsc = new TestSyncCall();
//        tsc.doFirstLast();

//        TestSyncronize ts = new TestSyncronize();
//        ts.test_3();

        TestVersionCompare tvc = new TestVersionCompare();
        tvc.tests();

        //TestJsonValue tjv = new TestJsonValue();
        //tjv.doFirstLast();

        KtJsonValue ktJsonValue = new KtJsonValue();
        ktJsonValue.tests();

        log("*** start java process ***");

        char lastChar = Chapter3_1Kt.lastChar("hello");
        log("lastChar(hello):"+lastChar);
        log("*** end java process ***");
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}
