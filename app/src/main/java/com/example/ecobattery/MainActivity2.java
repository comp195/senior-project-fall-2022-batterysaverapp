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
        batteryPercentText.setText("Battery Percentage: " + String.valueOf(BatteryInformation.getBatteryPercentage(getBaseContext())));
    }
}