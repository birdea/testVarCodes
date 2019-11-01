package com.birdea.testcode.test;

import com.birdea.testcode.util.L;

import java.util.Calendar;
import java.util.Date;

public class TestHalloweenaParty {

    public void tests() {
        L.msg("tests - start");
        L.msg("time(current): "+Calendar.getInstance().getTimeInMillis());
        L.msg("time(2019,10,29): "+getTimeInMillis(2019, 10,29));
        L.msg("time(2019,10,30): "+getTimeInMillis(2019, 10,30));
        L.msg("time(2019,10,31): "+getTimeInMillis(2019, 10,31));
        L.msg("time(2019,11,1): "+getTimeInMillis(2019, 11,1));
        L.msg("isHalloweenParty2019(10.30~11.1)?: "+isHalloweenParty2019());
        L.msg("isHalloweenParty2019(10.29~10.30)?: "+isHalloweenParty2019_2930());
        L.msg("isHalloweenParty2019(10.28~10.29)?: "+isHalloweenParty2019_2829());
        L.msg("tests - end");
    }

    public static long getTimeInMillis(int year, int month, int date) {
        Calendar calendar  = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month-1, date);
        return calendar.getTimeInMillis();
    }

    public static boolean isHalloweenParty2019() {
        long current  = Calendar.getInstance().getTimeInMillis();
        if (current >= getTimeInMillis(2019, 10, 30)
                && current < getTimeInMillis(2019,11,01)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHalloweenParty2019_2930() {
        long current  = Calendar.getInstance().getTimeInMillis();
        if (current >= getTimeInMillis(2019, 10, 29)
                && current < getTimeInMillis(2019,10,30)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHalloweenParty2019_2829() {
        long current  = Calendar.getInstance().getTimeInMillis();
        if (current >= getTimeInMillis(2019, 10, 28)
                && current < getTimeInMillis(2019,10,29)){
            return true;
        } else {
            return false;
        }
    }
}
