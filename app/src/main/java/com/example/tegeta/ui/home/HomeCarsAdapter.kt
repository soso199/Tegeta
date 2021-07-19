package com.example.tegeta.ui.home

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tegeta.R
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.model.Status
import com.example.tegeta.databinding.HomeCarsItemViewBinding

class HomeCarsAdapter(private val onAddClicked: (CurrentCar) -> Unit) :
    RecyclerView.Adapter<HomeCarsAdapter.ViewHolder>() {
    private val list: MutableList<CurrentCar> = mutableListOf()

    fun updateData(list: List<CurrentCar>) {
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
        holder.binding.serviceType.text = item.serviceName
        when (Status.fromInt(item.status)) {
            Status.ADDED -> {
                holder.binding.status.text =
                    holder.binding.root.context.getString(R.string.status_added)
                holder.binding.btnEnd.visibility = View.VISIBLE
                holder.binding.amount.visibility = View.GONE
            }
            Status.FINISHED -> {
                holder.binding.status.text =
                    holder.binding.root.context.getString(R.string.status_finished)
                holder.binding.btnEnd.visibility = View.GONE
                holder.binding.amount.visibility = View.VISIBLE
            }
            null -> {
                holder.binding.status.text = ""
                holder.binding.btnEnd.visibility = View.GONE
                holder.binding.amount.visibility = View.GONE
            }
        }

        holder.binding.date.text = DateFormat.format("hh:mm:ss", item.addDate.time)

        holder.binding.amount.text = item.amount?.let {
            holder.binding.root.context.getString(R.string.amount_placeholder, it.toString())
        } ?: ""

        holder.binding.btnEnd.setOnClickListener {
            onAddClicked.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}