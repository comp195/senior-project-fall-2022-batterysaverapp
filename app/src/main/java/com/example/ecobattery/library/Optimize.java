package com.example.ecobattery.library;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.ecobattery.MainActivity;
import com.example.ecobattery.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Optimize {

    private boolean isOptimized = false;

    private Context appContext;

    public Optimize(Context appContext) {
        this.appContext = appContext;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "EcoBatteryName";
            String description = "EcoBatteryDescription";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("EcoBatteryId", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = appContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void runOptimization() {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isOptimized && BatteryInformation.isLowBattery()) {
                    List<String> optimizedApps = new OptimizationFileConfig(appContext).getOptimizedPackages();
                    StringBuilder optimizedNames = new StringBuilder();

                    final PackageManager pm = appContext.getPackageManager();
                    for (String packages : optimizedApps) {
                        ApplicationInfo ai;
                        try {
                            ai = pm.getApplicationInfo(packages, 0);
                        } catch (PackageManager.NameNotFoundException e) {
                            ai = null;
                        }
                        optimizedNames.append((String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)")).append("\n");
                    }

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext, "EcoBatteryId")
                            .setContentTitle("EcoBattery Optimize")
                            .setSmallIcon(R.drawable.ecobatterypic)
                            .setContentText("List of Applications Optimized:")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(optimizedNames))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(appContext);

// notificationId is a unique int for each notification that you must define
                    notificationManager.notify(5, builder.build());
                    isOptimized = true;
                    forceStopApplications();
                }
                if (isOptimized && !BatteryInformation.isLowBattery()) {
                    isOptimized = false;
                }
                handler.postDelayed(this,1000);
            }
        }, 1000);
    }


    public void forceStopApplications() {
        ActivityManager aM = (ActivityManager)appContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (String packageToOptimize : new OptimizationFileConfig(appContext).getOptimizedPackages()) {
            aM.killBackgroundProcesses(packageToOptimize);
        }
        /*
        //TODO Delete, just testing
        // info : adb shell pidof com.google.android.gms <-- checks if process is running
        Process process = null;
        try {
            for (String packagesToOptimize : new OptimizationFileConfig(appContext).getOptimizedPackages()) {
                process = Runtime.getRuntime().exec("am force-stop " + packagesToOptimize);

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                // Grab the results
                StringBuilder log = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    log.append(line + "\n");
                }

                System.out.println("Killing: " + packagesToOptimize);
                System.out.println(log.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
