package bd.testcode.java.tests;

import bd.testcode.java.util.L;
import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class TestSyncCall {

    @Test
    public void test() {
        L.msg("> testSc started..");

        for (int i=0;i<3;i++) {
//            callFruit();

            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    callFruit();
                }
            }, "thread.A"+i);

            /*Thread b = new Thread(new Runnable() {
                @Override
                public void run() {
                    callFruit();
                }
            }, "thread.B"+i);*/

            a.start();
            //b.start();
        }

        L.msg("> testSc ended..");
    }

    private Fruit fruit = new Fruit();

    private void callFruit() {
        fruit.callA();
    }

    private static class Fruit {
        Object sync = new Object();

        void callA() {
            L.msg("callA() : start");
            synchronized (sync) {
                String tName = Thread.currentThread().getName();
                L.msg(tName+" > inside sync callA()");
                callB();
            }
            L.msg("callA() : end");
        }

        void callB() {
            L.msg("callB() : start");
            synchronized (sync) {
                L.msg("inside sync callB()");
                callC();
            }
            L.msg("callB() : end");
        }

        void callC() {
            L.msg("callC() : start");
            synchronized (sync) {
                L.msg("inside sync callC()");
                callD();
            }
            L.msg("callC() : end");
        }

        void callD() {
            L.msg("callD() : start");
            synchronized (sync) {
                L.msg("inside sync callD()");
            }
            L.msg("callD() : end");
        }
    }

}
