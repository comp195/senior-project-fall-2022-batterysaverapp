package com.example.ecobattery.library;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryInformation {

    private static final int LOW_BATTERY_THRESHOLD = 20;

    public static float getBatteryPercentage(Context context) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, intentFilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return (level * 100) / (float)scale;
    }

    /*
    TODO Calculate estimated time
    Research suggests we should gather data to analyze.
    https://stackoverflow.com/questions/30208271/calculating-remaining-time-of-battery-live
    Consider BatteryManager.computeChargeTimeRemaining() function from API 28
     */
    public static int getEstimatedTime() {
        return -1;
    }

    public static boolean isCharging(Context context) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, intentFilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        return status == BatteryManager.BATTERY_STATUS_CHARGING;
    }

    /*
    TODO Calculate getBatteryPercentage() + extra battery life
    Some physics calculation to calculate this
     */

    public static float getBatteryLife() {
        return -1;
    }

    public static boolean isLowBattery(Context context) {
        return getBatteryPercentage(context) <= LOW_BATTERY_THRESHOLD;
    }

    /*
    TODO Calculate time to charge
    Research suggests gather data to analyze.
    One approach:
    - Theoretically, battery is at 20%.
    - Start timer from 21%
    - End timer at 22%
    - Multiply 78 by (end-start) time to get "estimate".
    NOTE: Start timer from 21% because charge from 20% to 21% may vary if it's theoretically 20.9%.
     */
    public static int getTimeToCharge() {
        return -1;
    }

}
