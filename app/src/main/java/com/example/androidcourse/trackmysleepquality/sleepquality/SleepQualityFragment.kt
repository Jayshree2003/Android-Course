package com.example.androidcourse.trackmysleepquality.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidcourse.R
import com.example.androidcourse.databinding.FragmentSleepQualityBinding
import com.example.androidcourse.trackmysleepquality.database.SleepDatabase


class SleepQualityFragment : Fragment() {
    private lateinit var viewModel: SleepQualityViewModel
    private lateinit var binding: FragmentSleepQualityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality,container,false)
        val application= requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(requireActivity()).sleepDatabaseDao
        val arguments = arguments?.getLong("sleepNightKey", 0L)?: 0L

        val viewModelFactory = SleepQualityViewModelFactory(arguments, dataSource)

       viewModel=
            ViewModelProvider(
                this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = viewModel

        viewModel.navigateToSleepTracker.observe(this, Observer{
            if(it == true){
                this.findNavController().navigate(R.id.sleepTrackerFragment)
                viewModel.doneNavigation()
            }
        })


        return binding.root
    }



}