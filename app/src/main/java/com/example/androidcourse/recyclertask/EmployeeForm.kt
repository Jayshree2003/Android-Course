package com.example.androidcourse.recyclertask

import android.Manifest
import android.app.ComponentCaller
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.androidcourse.R
import com.example.androidcourse.databinding.ActivityEmployeeFormBinding
import com.example.androidcourse.recyclertask.database.AppDatabase
import com.example.androidcourse.recyclertask.database.EmployeeModel

class EmployeeForm : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeFormBinding
    private lateinit var db: AppDatabase
    private var selectedImageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_employee_form)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_employee_form)


        db = AppDatabase.getDatabase(this)
       /* val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 101)
        } else {
            openGallery()
        }*/



        binding.btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }



        binding.btnFormSubmit.setOnClickListener {
            val image= binding.imgForm.imageMatrix.toString()
            val name = binding.etvFormName.text.toString()
            val age = binding.etvFormAge.text.toString().toInt()
            val salary = binding.etvFormSalary.text.toString().toDouble()

            val employee = EmployeeModel(image = selectedImageUri.toString(),name = name, age = age, salary = salary)

            CoroutineScope(Dispatchers.IO).launch {
                db.employeeDao().insertEmployee(employee)
                withContext(Dispatchers.Main) {
                    startActivity(Intent(this@EmployeeForm, DisplayActivity::class.java))
                }
            }
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            selectedImageUri = data?.data
            binding.imgForm.setImageURI(selectedImageUri)
        }
    }

}