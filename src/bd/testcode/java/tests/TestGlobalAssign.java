package bd.testcode.java.tests;

import bd.testcode.java.util.L;
import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class TestGlobalAssign {

    @Test
    public void testGa() {
        L.msg("testGa() start");
        Thread tInit = new Thread(new Runnable() {
            @Override
            public void run() {
                L.msg("tInit > run() - start");
                int tSleep = 10;
                while (true) {
                    sleep(tSleep);
                    init();
                }
            }
        });
        Thread tRelease = new Thread(new Runnable() {
            @Override
            public void run() {
                L.msg("tRelease > run() - start");
                int tSleep = 30;
                while (true) {
                    sleep(tSleep);
                    release();
                }
            }
        });
        Thread tUsage = new Thread(new Runnable() {
            @Override
            public void run() {
                L.msg("tUsage > run() - start");
                int tSleep = 1;
                while (true) {
                    sleep(tSleep);
                    usage();
                }
            }
        });

//        boolean daemon = true;
//        tInit.setDaemon(daemon);
//        tRelease.setDaemon(daemon);
//        tUsage.setDaemon(daemon);

        tInit.start();
        tUsage.start();
        tRelease.start();

        L.msg("testGa() end");
    }

    private void sleep(long ms) {
        if (ms <= 0) {
            return;
        }
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        L.msg("testGa() -> init");
        if (mGlass.getInst()==null) {
            mGlass.init();
        }
    }

    private void usage() {
        L.msg("testGa() -> usage");
        Object inst = mGlass.getInst();

        if (inst != null) {
            L.msg("[usage] inst is not null");
            for (int i=0;i<100;i++) {
                //sleep(100);
                L.msg("[usage] obj:"+inst.toString());
            }
            L.msg("[usage] usage is complete");
        } else {
            L.msg("[usage] inst is null");
        }
    }

    private void release() {
        L.msg("testGa() -> release");
        mGlass.release();
    }

    private Glass mGlass = new Glass();

    private class Glass {

        private Object mInst;

        public void init() {
            mInst = new Object();
            L.msg("init()");
        }

        public Object getInst() {
            L.msg("getInst() : "+mInst);
            return mInst;
        }

        public void release() {
            String tag = mInst.toString();
            mInst = null;
            L.msg("release() obj : "+tag);
        }
    }
}
