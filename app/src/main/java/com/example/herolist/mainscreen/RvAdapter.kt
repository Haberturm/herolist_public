package com.example.herolist.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.herolist.databinding.ListItemBinding
import com.example.herolist.network.HeroProperty

class RvAdapter :
    ListAdapter<HeroProperty, RvAdapter.HeroPropertyViewHolder>(DiffCallback) {


    class HeroPropertyViewHolder(private var binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: HeroProperty) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HeroProperty>() {
        override fun areItemsTheSame(oldItem: HeroProperty, newItem: HeroProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HeroProperty, newItem: HeroProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroPropertyViewHolder {
        return HeroPropertyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HeroPropertyViewHolder, position: Int) {
        val heroProperty = getItem(position)
        holder.bind(heroProperty)
    }



}