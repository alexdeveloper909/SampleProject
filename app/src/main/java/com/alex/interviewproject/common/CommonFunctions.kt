package com.alex.interviewproject.common

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.Toast
import kotlin.math.min

object CommonFunctions {

    fun getBMOptions(width: Int, height: Int): BitmapFactory.Options =
        BitmapFactory.Options().apply {
            inJustDecodeBounds = true
            val photoW: Int = outWidth
            val photoH: Int = outHeight
            val scaleFactor: Int = min(photoW / width, photoH / height)
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }
}