package com.example.ecobattery.library;

import android.graphics.drawable.Drawable;

public class DisplayApp {

    private final String packageName;
    private final Drawable icon;
    private final String name;

    public DisplayApp(String packageName, Drawable icon, String name) {
        this.packageName = packageName;
        this.icon = icon;
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

}
