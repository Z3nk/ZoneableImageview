package com.example.myapplication

import android.widget.ImageView
import androidx.constraintlayout.solver.widgets.Rectangle

data class ProportionalRect(val x: Float, val y: Float, val width: Float, val height: Float) {
    fun toRectangle(imageView: ImageView): Rectangle {
        val rect = Rectangle()
        rect.x = (x * imageView.width).toInt()
        rect.y = (y * imageView.height).toInt()
        rect.width = (width * imageView.width).toInt()
        rect.height = (height * imageView.height).toInt()
        return rect
    }
}