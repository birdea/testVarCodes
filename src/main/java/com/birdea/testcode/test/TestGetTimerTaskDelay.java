package com.birdea.testcode.test;

import com.birdea.testcode.util.L;

import java.util.Calendar;

public class TestGetTimerTaskDelay {

    public void tests() {
        Calendar calendar = Calendar.getInstance();
        L.msg("start >>>");
        printTimes(calendar);
        L.msg("loop >>>");
        for (int i=0;i<30;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            calendar.setTimeInMillis(System.currentTimeMillis());
            printTimes(calendar);
            L.msg(String.format("val:%s", getTimerTaskDelay(10)));
        }
        L.msg("end >>>");
    }

    private void printTimes(Calendar cal) {
        long raw = cal.getTimeInMillis();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        int ms = cal.get(Calendar.MILLISECOND);
        L.msg(String.format("raw:%s > h:m:s.ms > %s:%s:%s.%s", raw, h, m, s, ms));
    }

    private int getTimerTaskDelay(int periodSecond) {
        Calendar cal = Calendar.getInstance();
        int sec = cal.get(Calendar.SECOND);
        int msec = cal.get(Calendar.MILLISECOND);
        int val = periodSecond*1000 - (sec%periodSecond*1000 + msec);
        return val;
    }
}
