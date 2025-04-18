package com.example.androidcourse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.Architecture.ArchitectureMainActivity
import com.example.androidcourse.DessertLifeCycle.ModuleLifeCycleActivity

import com.example.androidcourse.LifeCycles.LifeCycleActivity
import com.example.androidcourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        Log.i("MainActivity"," onCreate")
       // binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
       /* val ColorButton:Button=findViewById(R.id.btn_clrMyViews)
        ColorButton.setOnClickListener(){*/
        binding.btnClrMyViews.setOnClickListener {
            val intent=Intent(this, ColorMyViews::class.java)
            startActivity(intent)
        }
       /* val DiceRoller=findViewById<Button>(R.id.btn_dice)
        DiceRoller.setOnClickListener()*/
        binding.btnDice.setOnClickListener{
            val intent1=Intent(this, DiceRollerActivity::class.java)
            startActivity(intent1)
        }
       /* val LifeCycle=findViewById<Button>(R.id.btn_lifecycle)
        LifeCycle.setOnClickListener*/
        binding.btnLifecycle.setOnClickListener{
            val intent2=Intent(this, LifeCycleActivity::class.java)
            startActivity(intent2)
        }

        /*val ModuleLifeCycle=findViewById<Button>(R.id.btn_modulelifecycle)
        ModuleLifeCycle.setOnClickListener */
        binding.btnModulelifecycle.setOnClickListener{
            val intent3=Intent(this, ModuleLifeCycleActivity::class.java)
            startActivity(intent3)
        }

        binding.btnArchitecture.setOnClickListener {
            val intent4=Intent(this, ArchitectureMainActivity::class.java)
            startActivity(intent4)
        }
    }
}