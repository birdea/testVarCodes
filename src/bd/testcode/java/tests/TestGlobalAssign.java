package bd.testcode.java.tests;

import bd.testcode.java.util.L;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class TestGlobalAssign {

    public void testGa() {
        L.msg("testGa() start");
        Thread tInit = new Thread(new Runnable() {
            @Override
            public void run() {
                L.msg("tInit > run()");
                int cntTry = 1000;
                int tSleep = 10;
                for (int i=0;i<cntTry;i++) {
                    sleep(tSleep);
                    init();
                }
            }
        });
        Thread tRelease = new Thread(new Runnable() {
            @Override
            public void run() {
                L.msg("tRelease > run()");
                int cntTry = 1000;
                int tSleep = 10;
                for (int i=0;i<cntTry;i++) {
                    sleep(tSleep);
                    release();
                }
            }
        });
        Thread tUsage = new Thread(new Runnable() {
            @Override
            public void run() {
                L.msg("tUsage > run()");
                int cntTry = 1000;
                int tSleep = 10;
                for (int i=0;i<cntTry;i++) {
                    sleep(tSleep);
                    usage();
                }
            }
        });

        tInit.setDaemon(false);
        tRelease.setDaemon(false);
        tUsage.setDaemon(false);

        tInit.start();
        tRelease.start();
        tUsage.start();

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
        if (inst!=null) {
            L.msg("inst!=null->toString()");
            inst.toString();
        } else {
            L.msg("inst==null");
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
            mInst = null;
            L.msg("release()");
        }
    }
}
