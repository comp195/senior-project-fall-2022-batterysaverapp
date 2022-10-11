package com.example.ecobattery.library;

import android.os.BatteryManager;

public class BatteryInformation {

    public int getBatteryPercentage() {
        return BatteryManager.BATTERY_PROPERTY_CAPACITY;
    }

}
