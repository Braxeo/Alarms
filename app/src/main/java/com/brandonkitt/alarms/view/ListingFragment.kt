package com.brandonkitt.alarms.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.brandonkitt.alarms.R
import com.brandonkitt.alarms.adapters.ListingAdapter
import com.brandonkitt.alarms.databinding.FragmentListingBinding
import com.brandonkitt.alarms.viewmodel.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_listing.*

@AndroidEntryPoint
class ListingFragment: Fragment() {

    private val viewModel: ListingViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    private val adapter = ListingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(FragmentListingBinding.inflate(inflater, container, false)){
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
            viewModel.alarms.observe(viewLifecycleOwner) { adapter.submitList(it) { id -> viewModel.onAlarmSelected(id) } }
            viewModel.action.observe(viewLifecycleOwner) { action(it) }
            return root
        }
    }

    private fun action(action: ListingViewModel.Action){
        when(action){
            ListingViewModel.Action.AddAlarm -> showAlarmDetails(alarmId = null)
            is ListingViewModel.Action.ViewAlarmDetails -> showAlarmDetails(alarmId = action.alarmId)
        }
    }

    private fun showAlarmDetails(alarmId: String?){
        findNavController().navigate(R.id.action_ListingFragment_to_DetailsFragment , bundleOf("id" to alarmId))
    }
}

