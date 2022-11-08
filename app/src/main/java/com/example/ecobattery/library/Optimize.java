package com.example.ecobattery.library;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Optimize {

    public void runOptimization() {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this,1000);
            }
        }, 1000);
    }


    public void doOptimization() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("adb shell pm list packages");
            OutputStream outputStream = process.getOutputStream();
            System.out.println(outputStream.toString());
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
