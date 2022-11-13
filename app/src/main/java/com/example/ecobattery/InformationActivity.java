package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ecobattery.library.BatteryInformation;
import com.example.ecobattery.library.OptimizationFileConfig;
import com.example.ecobattery.library.Optimize;

import org.w3c.dom.Text;

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

        TextView batteryPercentText = (TextView)findViewById(R.id.batteryPercentText);
        batteryPercentText.setText("Battery Percentage : " + BatteryInformation.getBatteryPercentage() + "%");

        TextView estimatedTimeText = (TextView)findViewById(R.id.estimatedTimeText);
        estimatedTimeText.setText("Estimated Time : " + BatteryInformation.getEstimatedTime());

        TextView isChargingText = (TextView)findViewById(R.id.isChargingText);
        isChargingText.setText("Is Charging : " + BatteryInformation.isCharging());

        TextView isLowBatteryText = (TextView)findViewById(R.id.isLowBatteryText);
        isLowBatteryText.setText("Is Low Battery : " + BatteryInformation.isLowBattery());

        TextView timeToChargeText = (TextView)findViewById(R.id.timeToChargeText);
        timeToChargeText.setText("Time To Charge : " + BatteryInformation.getTimeToCharge());

    }
}