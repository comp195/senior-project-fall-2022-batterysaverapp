package com.example.ecobattery.library;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class Battery {

    //Determine the current charging state
    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    private Context context;
    Intent batteryStatus = context.registerReceiver(null, ifilter);

    // Are we charging / charged?
    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

    // How are we charging?
    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
    boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

    //Determine the current battery level
    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

    float batteryPct = level * 100 / (float)scale;

    


}
