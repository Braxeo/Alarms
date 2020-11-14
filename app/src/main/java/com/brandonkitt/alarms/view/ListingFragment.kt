package com.brandonkitt.alarms.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
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
    ): View? {
        with(FragmentListingBinding.inflate(inflater, container, false)){
            recyclerView.adapter = adapter
            viewModel.alarms.observe(viewLifecycleOwner) { adapter.submitList(it) }
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { findNavController().navigate(R.id.action_ListingFragment_to_SecondFragment) }
    }
}

