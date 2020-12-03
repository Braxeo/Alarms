package com.brandonkitt.alarms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandonkitt.alarms.databinding.ItemListingBinding
import com.brandonkitt.alarms.entities.AlarmEntity
import java.text.SimpleDateFormat
import java.util.*

class ListingAdapter : RecyclerView.Adapter<ListingAdapter.AlarmViewHolder>() {

    private var items: List<AlarmEntity> = emptyList()
    private var alarmSelected: ((id: String) -> Unit)? = null

    fun submitList(list: List<AlarmEntity>, onAlarmSelected: (id: String) -> Unit){
        items = list
        alarmSelected = onAlarmSelected
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(ItemListingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(items[position], alarmSelected)
    }

    override fun getItemCount(): Int = items.size

    class AlarmViewHolder(private val binding: ItemListingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(alarm: AlarmEntity, alarmSelected: ((id: String) -> Unit)?){
            binding.description = alarm.description
            binding.checked = alarm.enabled
            binding.time = SimpleDateFormat.getDateInstance().format(alarm.time)
            binding.root.setOnClickListener { alarmSelected?.invoke(alarm.alarmId) }
        }
    }
}