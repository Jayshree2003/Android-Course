package com.example.androidcourse.lifeCycles

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.R
import com.example.androidcourse.databinding.ActivityLifeCycle4Binding

class LifeCycleActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityLifeCycle4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_life_cycle4)
        // setContentView(R.layout.activity_life_cycle)
        Log.d("LifeCycle","onCreate()4")
        Toast.makeText(this, "onCreate4",Toast.LENGTH_SHORT ).show()
        //  btn=findViewById<Button>(R.id.button)



    }
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","OnStart()4")
        Toast.makeText(this,"onStart4",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","onStop()4")
        Toast.makeText(this,"onStop4",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","onPause()4")
        Toast.makeText(this,"onPause4",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","OnResume()4")
        Toast.makeText(this,"OnResume4",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LideCycle","onRestart()4")
        Toast.makeText(this,"OnRestart4",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","OnDestroy()4")
        Toast.makeText(this,"OnDestroy4",Toast.LENGTH_SHORT).show()
    }
}