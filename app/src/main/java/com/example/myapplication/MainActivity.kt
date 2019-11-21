package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val runnable1 = Runnable{
            Log.d("MainActivity", "runnable1")
            zi_image.setDrawable(getDrawable(R.drawable.image2))
        }
        val runnable2 = Runnable{
            Log.d("MainActivity", "runnable2")
            zi_image.setDrawable(getDrawable(R.drawable.image1))
        }
        zi_image.addAreaEffect(ProportionalRect(0.25f, 0.05f, 0.35f, 0.45f), runnable1)
        zi_image.addAreaEffect(ProportionalRect(0.05f, 0.10f, 0.20f, 0.80f), runnable2)
        zi_image.addAreaEffect(ProportionalRect(0.05f, 0.50f, 0.90f, 0.45f), runnable2)
    }

}
