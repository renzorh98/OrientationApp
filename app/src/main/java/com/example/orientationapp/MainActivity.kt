package com.example.orientationapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txt: TextView = findViewById(R.id.text_gravity)
        val txt1: TextView = findViewById(R.id.text_magnetometer)
        val txtO: TextView = findViewById(R.id.text_orientation)

        var x = 0;
        var y = 0;
        var z = 0;

        var xg = 0;
        var yg = 0;
        var zg = 0;


        var sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        var listm = sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)

        var sel = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                var values = event?.values
                xg = values?.get(0)?.toInt()!!
                yg = values?.get(1)?.toInt()!!
                zg = values?.get(2)?.toInt()!!

                txt1.setText("X= $xg\nY= $yg\nZ= $zg")

            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

        }

        var listg = sm.getSensorList(Sensor.TYPE_GRAVITY)

        var se = object : SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                var values = event?.values
                x = values?.get(0)?.toInt()!!
                y = values?.get(1)?.toInt()!!
                z = values?.get(2)?.toInt()!!

                txt.setText("X= $x\nY= $y\nZ= $z")

                if(yg == 5 && zg == -48 && y == 9){
                    txtO.setText("Vertical 1")
                }
                if(yg == -5 && zg == -48 && y == -9){
                    txtO.setText("Vertical 2")
                }
                if(xg == -5 && zg == -48 && x == -9){
                    txtO.setText("Horizontal 1")
                }
                if(xg == 5 && zg == -48 && x == 9){
                    txtO.setText("Horizontal 2")
                }


            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

        }



        sm.registerListener(se, listg.get(0), SensorManager.SENSOR_DELAY_UI)
        sm.registerListener(sel, listm.get(0), SensorManager.SENSOR_DELAY_UI)




    }
}