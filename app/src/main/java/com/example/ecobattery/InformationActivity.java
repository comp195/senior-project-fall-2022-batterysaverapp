package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ecobattery.library.BatteryInformation;
import com.example.ecobattery.library.OptimizationFileConfig;
import com.example.ecobattery.library.Optimize;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class InformationActivity extends AppCompatActivity {

    /*
    TODO: Consider live update on information
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        TextView batteryPercentText = (TextView)findViewById(R.id.textView4);
        batteryPercentText.setText("Battery Percentage: " + String.valueOf(BatteryInformation.getBatteryPercentage()));

       // TextView isChargingText = (TextView)findViewById(R.id.textView5);
       // isChargingText.setText("Is Charging: " + String.valueOf(BatteryInformation.isCharging()));

       // TextView isLowBatteryText = (TextView)findViewById(R.id.textView6);
       // isLowBatteryText.setText("Is Low Battery: " + String.valueOf(BatteryInformation.isLowBattery()));
    }
}