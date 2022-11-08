package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobattery.library.BatteryInformation;
import com.example.ecobattery.library.Optimize;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Optimize optimize = new Optimize();
        optimize.runOptimization();
        /*
        TODO simplify this code
         */
        BatteryInformation.setAppContext(getApplicationContext());
    }

    public void batteryInfoScreen(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void batterySaverScreen(View view) {
        Intent intent = new Intent(this, SaverActivity.class);
        startActivity(intent);
    }
}