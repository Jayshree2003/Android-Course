package com.example.androidcourse.architecture

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.androidcourse.R

class ArchitectureMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_architecture_main)

        //supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, TitleFragment()).commit()
    }
}