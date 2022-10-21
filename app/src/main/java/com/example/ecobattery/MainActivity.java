package com.example.ecobattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecobattery.library.BatteryInformation;
import com.example.ecobattery.library.Optimization;
//import android.view.View;
//import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        TODO simplify this code
         */
        BatteryInformation.setAppContext(getApplicationContext());
        Optimization.setAppContext(getApplicationContext());
    }

    public void batteryInfoScreen(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}