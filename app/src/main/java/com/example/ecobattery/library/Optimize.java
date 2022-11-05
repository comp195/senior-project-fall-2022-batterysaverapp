package com.example.ecobattery.library;

import java.io.IOException;
import java.io.OutputStream;

public class Optimize {

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
