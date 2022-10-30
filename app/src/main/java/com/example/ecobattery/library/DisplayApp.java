package com.example.ecobattery.library;

import android.graphics.drawable.Drawable;

public class DisplayApp {

    private boolean isSelected;
    private Drawable icon;
    private String name;

    public DisplayApp(boolean isSelected, Drawable icon, String name) {
        this.isSelected = isSelected;
        this.icon = icon;
        this.name = name;
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
