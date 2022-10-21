package com.example.ecobattery.library;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

public class Optimization {

    private static Context appContext;

    private static final int LOW_BATTERY_THRESHOLD = 20;

    public static void setAppContext(Context newContext) {
        appContext = newContext;
    }

    public void terminateApplications() {
        ActivityManager am = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);

        System.out.println("TODO X Y D Z SJAJHJRHA A");

        for (ActivityManager.RunningAppProcessInfo pid : am.getRunningAppProcesses()) {
            if (!pid.processName.equalsIgnoreCase("com.example.ecobattery")) {

                System.out.println("Process Being Killed: " + pid.processName);
                //am.killBackgroundProcesses(pid.processName);
            }
        }
    }

}
