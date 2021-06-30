package com.example.tegeta.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.databinding.HomeCarsItemViewBinding

class HomeCarsAdapter() :
    RecyclerView.Adapter<HomeCarsAdapter.ViewHolder>() {
    private val list: MutableList<CurrentCar> = mutableListOf()

    fun updateData(list: List<CurrentCar>){
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: HomeCarsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeCarsItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.number.text = item.number
    }

    override fun getItemCount(): Int {
        return list.size
    }
}