package com.example.herolist.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.herolist.databinding.ListItemBinding
import com.example.herolist.network.HeroProp

class RvAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<HeroProp, RvAdapter.HeroPropViewHolder>(DiffCallback) {


    class HeroPropViewHolder(private var binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(heroProperty: HeroProp) {
            binding.property = heroProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HeroProp>() {
        override fun areItemsTheSame(oldItem: HeroProp, newItem: HeroProp): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HeroProp, newItem: HeroProp): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroPropViewHolder {
        return HeroPropViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HeroPropViewHolder, position: Int) {
        val heroProp = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(heroProp)
        }
        holder.bind(heroProp)
    }
    class OnClickListener(val clickListener: (heroProperty:HeroProp) -> Unit) {
        fun onClick(heroProperty:HeroProp) = clickListener(heroProperty)
    }



}