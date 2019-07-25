package com.birdea.testcode.test;

import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 8. 31. created by seungtae.hwang
 */
public class TestTimeDuration {

    @Test
    public void test() {
        long current = System.currentTimeMillis();
        long start = current - 894234;
        long end = current + 20930;
        //Duration duration = Duration.between(LocalTime.start, end);
    }
}
