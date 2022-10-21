package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobattery.library.BatteryInformation;
//import android.view.View;
//import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BatteryInformation.setAppContext(getApplicationContext());
    }

    public void batteryInfoScreen(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}