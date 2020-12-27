package com.alex.interviewproject.ui.sensor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alex.interviewproject.R
import com.alex.interviewproject.common.CommonFunctions.round
import com.alex.interviewproject.framework.sensors.Accelerometer
import kotlinx.android.synthetic.main.fragment_sensor.*
import java.util.*
import kotlin.math.round


class SensorFragment : Fragment() {

    private lateinit var accelerometer:Accelerometer

    private var x:Float = 0F;
    private var y:Float = 0F;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToAccelerometer()
    }

    private fun subscribeToAccelerometer() {
        accelerometer = Accelerometer(requireContext())
        accelerometer.setListener(object : Accelerometer.Listener {
            @SuppressLint("SetTextI18n")
            override fun onMove(tx: Float, ty: Float, tz: Float) {
                xValue.text = "x = ${tx.toDouble().round(1)}"
                yValue.text = "y = ${ty.toDouble().round(1)}"
                zValue.text = "z = ${tz.toDouble().round(1)}"

                val displayMetrics = DisplayMetrics()
                requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
                val height = displayMetrics.heightPixels
                val width = displayMetrics.widthPixels

                if((x-tx*10).toInt() in 0..width){
                    x -= tx*10
                }
                if((y+ty*3).toInt() in 0..height){
                    y += ty*3
                }

                doComputation()
            }
        })
    }

    private fun doComputation() {
        val maxScale: Float = myImageView.maximumScale

        myImageView.setScale(
            maxScale,
            x,
            y,
            false
        )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor, container, false)
    }

    override fun onPause() {
        super.onPause()
        accelerometer.unregister()
    }

    override fun onResume() {
        super.onResume()
        accelerometer.register()
    }

}