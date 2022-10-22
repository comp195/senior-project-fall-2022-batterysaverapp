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

    //TODO Root android device and save battery!
    public void terminateApplications() {
        // TODO
    }

}
