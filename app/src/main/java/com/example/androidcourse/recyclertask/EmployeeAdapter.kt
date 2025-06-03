package com.example.androidcourse.recyclertask

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourse.databinding.ItemEmployeeBinding
import com.example.androidcourse.recyclertask.database.EmployeeModel

class EmployeeAdapter(private val list: List<EmployeeModel>) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val emp = list[position]
        holder.binding.imgItemEmployee.setImageURI(Uri.parse(emp.image))
        holder.binding.tvItemName.text = "Name: ${emp.name}"
        holder.binding.tvItemAge.text = "Age: ${emp.age}"
        holder.binding.tvItemSalary.text = "Salary: ${emp.salary}"
    }

    override fun getItemCount(): Int = list.size
}
