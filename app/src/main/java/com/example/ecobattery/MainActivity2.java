package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ecobattery.library.BatteryInformation;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView batteryPercentText = (TextView)findViewById(R.id.textView4);
        batteryPercentText.setText("Battery Percentage: " + String.valueOf(BatteryInformation.getBatteryPercentage()));

        TextView isChargingText = (TextView)findViewById(R.id.textView5);
        isChargingText.setText("Is Charging: " + String.valueOf(BatteryInformation.isCharging()));

        TextView isLowBatteryText = (TextView)findViewById(R.id.textView6);
        isLowBatteryText.setText("Is Low Battery: " + String.valueOf(BatteryInformation.isLowBattery()));
    }
}