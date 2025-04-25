package com.example.androidcourse.trackmysleepquality.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidcourse.R
import com.example.androidcourse.databinding.FragmentSleepTrackerBinding
import com.example.androidcourse.trackmysleepquality.database.SleepDatabase
import com.example.androidcourse.trackmysleepquality.formatNights
import kotlin.apply

class SleepTrackerFragment : Fragment() {
    private lateinit var binding: FragmentSleepTrackerBinding
    private lateinit var viewModel: SleepTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_tracker, container, false)
        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        val dataSource = SleepDatabase.getInstance(requireActivity()).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

// Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(
                this,viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.sleepTrackerViewModel = viewModel

        // Observe the nightsString LiveData and update the TextView
        viewModel.nights.observe(viewLifecycleOwner, { nightsList ->
            // Format the nights when the list changes
            val formattedNights = formatNights(nightsList, resources)
            binding.textview.text = formattedNights // Update the TextView with the formatted nights
        })

        viewModel.nightToSleepQuality.observe(this, Observer{
            night->
            night?.let{
                val bundle= Bundle().apply {
                    putLong("sleepNightKey", night.NightId)
                }
                this.findNavController().navigate(R.id.sleepQualityFragment,bundle)
                viewModel.doneNavigation()
            }

        })


        /*// Observe the clearButtonVisible LiveData
        viewModel.clearButtonVisible.observe(viewLifecycleOwner, Observer {sleepList  ->
            // Show or hide the Clear button based on isVisible
            if(sleepList.isNullOrEmpty()){
            binding.clearButton.visibility = View.GONE
            }else {
                View.VISIBLE
            }
        })*/

        return binding.root
    }


}