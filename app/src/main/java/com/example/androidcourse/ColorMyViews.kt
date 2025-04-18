package com.example.androidcourse

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ColorMyViews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_my_views)
        setListener()
    }

    private fun setListener() {
        val clickableViews: List<View> = listOf(
            findViewById(R.id.tv_clr_box1),
            findViewById(R.id.tv_clr_box2),
            findViewById(R.id.tv_clr_box3),
            findViewById(R.id.tv_clr_box4),
            findViewById(R.id.tv_clr_box5),
            findViewById(R.id.contraint_layout),
            findViewById(R.id.btn_clr_Red),
            findViewById(R.id.btn_clr_Blue),
            findViewById(R.id.btn_clr_Green),

        )

        for(item in clickableViews){
            item.setOnClickListener{makeColored(it)}
        }
    }
    fun makeColored(view: View) {
        when (view.id) {

            // Boxes using Color class colors for background
            R.id.tv_clr_box1 -> view.setBackgroundColor(Color.DKGRAY)
            R.id.tv_clr_box2 -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            R.id.tv_clr_box3 -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.tv_clr_box4 -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.tv_clr_box5 -> view.setBackgroundResource(android.R.color.holo_green_light)

            // Boxes using custom colors for background
            R.id.btn_clr_Red -> findViewById<View>(R.id.tv_clr_box3).setBackgroundResource(R.color.Red)
            R.id.btn_clr_Blue -> findViewById<View>(R.id.tv_clr_box4).setBackgroundResource(R.color.blue)
            R.id.btn_clr_Green -> findViewById<View>(R.id.tv_clr_box5).setBackgroundResource(R.color.green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}