package bd.testcode.java.tests;

import bd.testcode.java.util.L;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 8. 22. created by seungtae.hwang
 */
public class TestSyncronize {

    @Test
    public void test_1() {
        final List<String> list = new ArrayList<>();
        //final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        //final List<String> list = new CopyOnWriteArrayList<String>();
        final int nThreads = 2;
        ExecutorService es = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            es.execute(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            //synchronized (list)
                            {
                                //L.msg(String.format("loop-start[%s]", Thread.currentThread().getName()));
                                list.clear();
                                list.add("888");
                                list.remove(0);
                               // L.msg(String.format("loop-end[%s]", Thread.currentThread().getName()));
                            }
                        } catch(IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Test
    public void test_2() {
//        final List<String> list = new ArrayList<>();
        final List<String> list = new CopyOnWriteArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        for(String s : list) {
            if(s.equals("A")) {
                list.remove(s);
            }
        }
    }

    List<String> mList = new CopyOnWriteArrayList<>();
    //List<String> mList = Collections.synchronizedList(new ArrayList<>());
    boolean mRunnable = true;
    Object mSync = new Object();

    public void test_3() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //get
                while (mRunnable) {
                    try {
                        int size = mList.size();
                        if (size < 1) {
                            continue;
                        }
                        L.msg(String.format("Loop-start:size:%s", size));
                        //synchronized (mSync)
                            for (String s : mList) {
                                L.msg(String.format("Loop-in:size:%s, item:%s", size, s));
                            }
                        /*try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        mRunnable = false;
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            int item = 0;
            @Override
            public void run() {
                //add
                while (mRunnable) {
                    try {
                        String s = String.valueOf(item++);

                        //synchronized (mSync)
                            mList.add(s);
                        L.msg(String.format("added:%s", s));
                        /*try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                        mRunnable = false;
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
