package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ecobattery.library.BatteryInformation;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        TextView batteryPercentText = (TextView)findViewById(R.id.batteryPercentText);
        batteryPercentText.setText(BatteryInformation.getBatteryPercentage() + "%");

        ProgressBar batteryPercentBar = (ProgressBar)findViewById(R.id.progressBar);
        batteryPercentBar.setProgress((int) BatteryInformation.getBatteryPercentage());

        TextView isChargingText = (TextView)findViewById(R.id.isChargingText);
        String isChargingMessage = "NO";
        boolean isChargingBool = BatteryInformation.isCharging();
        if (isChargingBool) {
            isChargingMessage = "YES";
        }
        isChargingText.setText(isChargingMessage);

        TextView isLowBatteryText = (TextView)findViewById(R.id.isLowBatteryText);
        String isLowBatteryMessage = "NO";
        boolean isLowBatteryBool = BatteryInformation.isLowBattery();
        if (isLowBatteryBool) {
            isLowBatteryMessage = "YES";
        }
        isLowBatteryText.setText(isLowBatteryMessage);

        TextView timeToChargeText = (TextView)findViewById(R.id.timeToChargeText);
        timeToChargeText.setText(BatteryInformation.getTimeToCharge());

    }
    /*TODO not for this page, think of test plan
    - buttons?
    - edge cases?
    - user takes x action, expected output is y
    - installation (on android device)
    * */
}