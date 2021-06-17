package com.dev.portal.services.common;

import java.util.concurrent.TimeUnit;

public class CommonUtils {

    public static void delayInSeconds(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
        }
    }

}
