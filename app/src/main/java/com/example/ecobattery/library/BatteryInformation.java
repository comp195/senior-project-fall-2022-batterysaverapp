package com.example.ecobattery.library;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryInformation {

    //TODO possibly load images for yes/no

    private static Context appContext;

    private static final int LOW_BATTERY_THRESHOLD = 20;

    public static void setAppContext(Context newContext) {
        appContext = newContext;
    }

    public static float getBatteryPercentage() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = appContext.registerReceiver(null, intentFilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return (level * 100) / (float)scale;
    }

    public static boolean isCharging() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = appContext.registerReceiver(null, intentFilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }

    public static boolean isLowBattery() {
        return getBatteryPercentage() <= LOW_BATTERY_THRESHOLD;
    }

    public static String getTimeToCharge() {
        if (!isCharging()) {
            return "NOT CHARGING";
        }
        if (getBatteryPercentage() >= 100) {
            return "FULL CHARGED";
        }
        BatteryManager mBatteryManager = (BatteryManager) appContext.getSystemService(Context.BATTERY_SERVICE);

        long time = -1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            time = mBatteryManager.computeChargeTimeRemaining();
        }

        if (time == -1) {
            return "NEEDS MORE DATA";
        } else {
            int seconds = (int) (time / 1000) % 60 ;
            int minutes = (int) ((time / (1000*60)) % 60);
            int hours   = (int) ((time / (1000*60*60)) % 24);
            return hours + "H " + minutes + "M " + seconds + "S";
        }
    }

}
