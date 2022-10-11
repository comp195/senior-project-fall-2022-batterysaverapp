package com.example.ecobattery.library;

import android.os.BatteryManager;

public class BatteryInformation {

    public int getBatteryPercentage() {
        return BatteryManager.BATTERY_PROPERTY_CAPACITY;
    }

    /*
    TODO Calculate estimated time
    Research suggests we should gather data to analyze.
    https://stackoverflow.com/questions/30208271/calculating-remaining-time-of-battery-live
     */
    public int getEstimatedTime() {
        return -1;
    }


}
