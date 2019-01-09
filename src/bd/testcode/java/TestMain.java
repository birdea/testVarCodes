package bd.testcode.java;

import bd.testcode.java.tests.TestGlobalAssign;
import bd.testcode.java.tests.TestSyncCall;
import bd.testcode.java.tests.TestSyncronize;
import bd.testcode.java.tests.TestVersionCompare;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class TestMain {

    public static void main(String[] args) {
//        TestGlobalAssign tga = new TestGlobalAssign();
//        tga.testGa();

//        TestSyncCall tsc = new TestSyncCall();
//        tsc.test();

//        TestSyncronize ts = new TestSyncronize();
//        ts.test_3();

        TestVersionCompare tvc = new TestVersionCompare();
        tvc.tests();
    }

}
