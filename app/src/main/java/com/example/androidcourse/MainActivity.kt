package com.example.androidcourse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
/*import androidx.databinding.DataBindingUtil*/
import com.example.androidcourse.architecture.ArchitectureMainActivity
//import com.example.androidcourse.dessertLifeCycle.ModuleLifeCycleActivity

//import com.example.androidcourse.lifeCycles.LifeCycleActivity
import com.example.androidcourse.databinding.ActivityMainBinding
import com.example.androidcourse.dessertLifeCycle.ModuleLifeCycleActivity
import com.example.androidcourse.lifeCycles.LifeCycleActivity
import com.example.androidcourse.recyclertask.EmployeeForm
import com.example.androidcourse.trackmysleepquality.TrackSleepMainActivity

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
        binding.btnSleep.setOnClickListener {
            val intent5= Intent(this, TrackSleepMainActivity::class.java)
            startActivity(intent5)
        }

        binding.btnRecyclertask.setOnClickListener{
            val intent5= Intent(this, EmployeeForm::class.java)
            startActivity(intent5)
        }
    }
}