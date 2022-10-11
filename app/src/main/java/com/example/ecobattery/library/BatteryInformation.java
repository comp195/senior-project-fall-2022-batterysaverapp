package com.example.ecobattery.library;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryInformation {

    /*
    TODO Test with drained battery
     */
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
     */
    public static int getEstimatedTime() {
        return -1;
    }

}
