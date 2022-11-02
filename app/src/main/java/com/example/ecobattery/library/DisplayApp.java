package com.example.ecobattery.library;

import android.graphics.drawable.Drawable;

public class DisplayApp {

    private String packageName;
    private boolean isSelected;
    private Drawable icon;
    private String name;

    public DisplayApp(String packageName, boolean isSelected, Drawable icon, String name) {
        this.packageName = packageName;
        this.isSelected = isSelected;
        this.icon = icon;
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

}
