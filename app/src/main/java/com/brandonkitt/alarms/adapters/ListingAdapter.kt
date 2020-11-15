package com.brandonkitt.alarms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandonkitt.alarms.databinding.ItemListingBinding
import com.brandonkitt.alarms.entities.AlarmEntity

class ListingAdapter : RecyclerView.Adapter<ListingAdapter.AlarmViewHolder>() {

    private var items: List<AlarmEntity> = emptyList()

    fun submitList(list: List<AlarmEntity>){
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(ItemListingBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class AlarmViewHolder(private val binding: ItemListingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(alarm: AlarmEntity){
            binding.description = "This is the description"//alarm.description
            binding.checked = alarm.enabled
            binding.time = "time"
        }
    }

}