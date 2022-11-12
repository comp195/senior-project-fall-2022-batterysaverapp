package com.example.ecobattery.library;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.ecobattery.MainActivity;
import com.example.ecobattery.R;

import java.io.IOException;
import java.io.OutputStream;
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
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext, "EcoBatteryId")
                            .setContentTitle("My notification")
                            .setSmallIcon(R.drawable.ecobatterypic)
                            .setContentText("Much longer text that cannot fit one line...")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Much longer text that cannot fit one line..."))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(appContext);

// notificationId is a unique int for each notification that you must define
                    notificationManager.notify(5, builder.build());
                    isOptimized = true;
                    /*
                    AlertDialog aD = new AlertDialog.Builder(appContext)
                            .setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();*/
                }
                if (isOptimized && !BatteryInformation.isLowBattery()) {
                    isOptimized = false;
                }
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
