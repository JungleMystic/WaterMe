package com.lrm.waterme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lrm.waterme.databinding.ListItemBinding
import com.lrm.waterme.model.Plant


class PlantAdapter(private val longClickListener: PlantListener): ListAdapter<Plant, PlantAdapter.PlantViewHolder>(DiffCallback) {

    class PlantViewHolder(
        private val binding: ListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(longClickListener: PlantListener,
            plant: Plant
        ) {
            binding.plant = plant
            binding.longClickListener = longClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(longClickListener, plant)
    }


    companion object DiffCallback: DiffUtil.ItemCallback<Plant>(){
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class PlantListener(val longClickLister: (plant: Plant) -> Boolean) {
        fun onLongClick(plant: Plant) = longClickLister(plant)
    }
}