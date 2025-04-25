package com.example.androidcourse.lifeCycles

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.R
import com.example.androidcourse.databinding.ActivityLifeCycleBinding


class LifeCycleActivity : AppCompatActivity() {
    //lateinit var btn:Button
    private var processingDialog: AlertDialog? =null
    private lateinit var binding: ActivityLifeCycleBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_life_cycle)
      // setContentView(R.layout.activity_life_cycle)
        Log.d("LifeCycle","onCreate()")
        Toast.makeText(this, "onCreate",Toast.LENGTH_SHORT ).show()
      //  btn=findViewById<Button>(R.id.button)

        binding.button.setOnClickListener {
            val intent=Intent(this, LifeCycleActivity2 ::class.java)
            startActivity(intent)
        }
    }


    fun showProcessingDialog(context: Context): AlertDialog{
        val builder= AlertDialog.Builder(context)
        builder.setMessage("Processing...").setCancelable(false)
        val alert=builder.create()
        alert.show()
        Handler(Looper.getMainLooper()).postDelayed({
            alert.dismiss()
        }, 5000)
        return alert
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","OnStart()")
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show()
       // processingDialog= showProcessingDialog(this)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","onStop()")
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","onPause()")
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","OnResume()")
        Toast.makeText(this,"OnResume",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LideCycle","onRestart()")
        Toast.makeText(this,"OnRestart",Toast.LENGTH_SHORT).show()
       processingDialog= showProcessingDialog(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","OnDestroy()")
        Toast.makeText(this,"OnDestroy",Toast.LENGTH_SHORT).show()
    }
}