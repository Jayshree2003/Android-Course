package com.example.androidcourse.LifeCycles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.R
import com.example.androidcourse.databinding.ActivityLifeCycle2Binding
import com.example.androidcourse.databinding.ActivityLifeCycleBinding

class LifeCycleActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityLifeCycle2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_life_cycle2)
        // setContentView(R.layout.activity_life_cycle)
        Log.d("LifeCycle","onCreate()2")
        Toast.makeText(this, "onCreate2",Toast.LENGTH_SHORT ).show()
        //  btn=findViewById<Button>(R.id.button)

        binding.btnLifecycle2.setOnClickListener {
            val intent=Intent(this, LifeCycleActivity3 ::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","OnStart()2")
        Toast.makeText(this,"onStart2",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","onStop()2")
        Toast.makeText(this,"onStop2",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","onPause()2")
        Toast.makeText(this,"onPause2",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","OnResume()2")
        Toast.makeText(this,"OnResume2",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LideCycle","onRestart()2")
        Toast.makeText(this,"OnRestart2",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","OnDestroy()2")
        Toast.makeText(this,"OnDestroy2",Toast.LENGTH_SHORT).show()
    }
}