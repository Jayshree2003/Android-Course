package com.example.androidcourse.lifeCycles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.R
import com.example.androidcourse.databinding.ActivityLifeCycle3Binding

class LifeCycleActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityLifeCycle3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_life_cycle3)
        // setContentView(R.layout.activity_life_cycle)
        Log.d("LifeCycle","onCreate()3")
        Toast.makeText(this, "onCreate3",Toast.LENGTH_SHORT ).show()
        //  btn=findViewById<Button>(R.id.button)

        binding.btnLifecycle3.setOnClickListener {
            val intent=Intent(this, LifeCycleActivity4 ::class.java)
            startActivity(intent)
        }

    }
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","OnStart()3")
        Toast.makeText(this,"onStart3",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","onStop()3")
        Toast.makeText(this,"onStop3",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","onPause()3")
        Toast.makeText(this,"onPause3",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","OnResume()3")
        Toast.makeText(this,"OnResume3",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LideCycle","onRestart()3")
        Toast.makeText(this,"OnRestart3",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","OnDestroy()3")
        Toast.makeText(this,"OnDestroy3",Toast.LENGTH_SHORT).show()
    }
}