package com.example.ecobattery.library;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Optimization {

    private static Context appContext;

    private static final int LOW_BATTERY_THRESHOLD = 20;

    public static void setAppContext(Context newContext) {
        appContext = newContext;
    }

    public List<ApplicationInfo> getListOfApplications() {
        /*
        try
        {
            System.out.println("INSTALLED");
            List<ApplicationInfo> xyz = getApplicationContext().getPackageManager().getInstalledApplications(0);
            for (ApplicationInfo a : xyz) {
                // If app info is non-system
                if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                    System.out.println(a.packageName);
            }
            Drawable icon = getApplicationContext().getPackageManager().getApplicationIcon("com.google.android.networkstack.tethering");
            ImageView imageView = (ImageView)findViewById(R.id.imageView2);
            imageView.setImageDrawable(icon);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
         */
        return null;
    }

    //TODO Root android device and save battery!
    public void terminateApplications() {
        // TODO
        Process process = null;
        try {
            //process = Runtime.getRuntime().exec("ps -A");
            process = Runtime.getRuntime().exec("pm list packages -3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert process != null;
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String s = null;

        while (true)
        {
            try {
                if ((s = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(s);
        }
        System.out.println("Made it");
    }

}
