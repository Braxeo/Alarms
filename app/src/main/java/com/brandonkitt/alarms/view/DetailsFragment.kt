package com.brandonkitt.alarms.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.brandonkitt.alarms.R
import com.brandonkitt.alarms.databinding.FragmentDetailsBinding
import com.brandonkitt.alarms.entities.AlarmEntity
import com.brandonkitt.alarms.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(FragmentDetailsBinding.inflate(inflater, container, false)){
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            afterTextChanged = viewModel.afterTextChanged
            alarm = viewModel.alarm
            viewModel.alarm.observe(viewLifecycleOwner) { updateForAlarm(it) }
            viewModel.action.observe(viewLifecycleOwner) { action(it) }
            return root
        }
    }

    private fun action(action: DetailsViewModel.Action) {
        when(action){
            DetailsViewModel.Action.Dismiss -> findNavController().navigate(R.id.action_DetailsFragment_to_ListingFragment)
        }
    }

    private fun updateForAlarm(alarmEntity: AlarmEntity) {

        val calendar = Calendar.getInstance().apply { time = alarmEntity.time }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            time_picker.hour = minute
        } else {
            time_picker.currentHour = hour
        }
    }
}