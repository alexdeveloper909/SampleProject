package com.alex.interviewproject.framework.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class Accelerometer(context: Context){

    interface Listener{
        fun onMove(tx:Float,ty:Float,tz:Float);
    }

    private var listener:Listener?=null
    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            listener?.let {
                listener!!.onMove(event!!.values[0], event.values[1],event.values[2])
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    fun register(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_UI)
    }

    fun unregister(){
        sensorManager.unregisterListener(sensorEventListener)
    }

    fun setListener(l:Listener){
        listener = l
    }

}