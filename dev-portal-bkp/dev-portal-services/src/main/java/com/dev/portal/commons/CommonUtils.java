package com.dev.portal.commons;

public class CommonUtils {

    public static void sleepSeconds(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
        }
    }

}
