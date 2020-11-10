package com.juliusyam.mobileinteractionappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceRoller extends AppCompatActivity implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sensorManager;

    private float acelVal;
    private float acelLast;
    private float shake;

    public int diceState = 0;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        acelLast = acelVal;
        acelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));
        float delta = acelVal - acelLast;
        shake = shake * 0.9f + delta;

        if (shake > 12) {
            rollDice();
            //Toast.makeText(this, "shake speed "+ delta, Toast.LENGTH_LONG).show();
        }

        TextView accelerometerTV = findViewById(R.id.accelerometerTV);
        accelerometerTV.setText(
                "Accelerometer Data: " +
                        "x: " + String.valueOf(x) +
                        "y: " + String.valueOf(y) +
                        "z: " + String.valueOf(z)
        );

    }

    private void rollDice() {
        TextView diceVal = findViewById(R.id.dice_value);
        int randomNumber = 0;

        if (diceState == 0) {
            randomNumber = random.nextInt(6) + 1;
        } else if (diceState == 1) {
            randomNumber = random.nextInt(12) + 1;
        } else {
            randomNumber = random.nextInt(6) + 1;
        }

        diceVal.setText(String.valueOf(randomNumber));
    }

    public void diceCountPressed(View v) {
        Button diceCount = findViewById(R.id.dice_count);

        if (diceState == 0) {
            diceCount.setText("Change to 1 dice");
            diceState = 1;
        } else if (diceState == 1) {
            diceCount.setText("Change to 2 dice");
            diceState = 0;
        } else {
            diceCount.setText("Change to 1 dice");
            diceState = 1;
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    public void homePressed(View view) {
        Intent intent = new Intent (this, MainActivity. class);
        startActivity(intent);
    }
    public void dicePressed(View view) {
        Intent intent = new Intent (this, DiceRoller. class);
        startActivity(intent);
    }
    public void deckPressed(View view) {
        Intent intent = new Intent (this, CardDeck. class);
        startActivity(intent);
    }
}