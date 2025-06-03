package com.example.androidcourse.recyclertask

import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.R
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.androidcourse.databinding.ActivityDisplayBinding
import com.example.androidcourse.recyclertask.database.AppDatabase

class DisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_display)
        // setContentView(R.layout.activity_life_cycle)
        //setContentView(R.layout.activity_display)
        db = AppDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            val employees = db.employeeDao().getAllEmployees()

            withContext(Dispatchers.Main) {
                binding.recyclerView.layoutManager = LinearLayoutManager(this@DisplayActivity)
                binding.recyclerView.adapter = EmployeeAdapter(employees)
            }
        }
    }
}