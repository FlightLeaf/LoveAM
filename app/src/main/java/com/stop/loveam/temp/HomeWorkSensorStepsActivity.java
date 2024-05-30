package com.stop.loveam.temp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stop.loveam.R;

public class HomeWorkSensorStepsActivity extends AppCompatActivity implements SensorEventListener {

    TextView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_sensor_steps);

        steps = findViewById(R.id.steps);
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor stepCounter = sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        if(stepCounter != null){
            Log.d("Sensor", "onSensorChanged: ");
            sm.registerListener(this, stepCounter, 1000000);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("Sensor", "onSensorChanged: " + event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}