package com.ioasys.diversidade.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ioasys.diversidade.databinding.ConsultRowLayoutBinding
import com.ioasys.diversidade.models.Consult

class ConsultAdapter: RecyclerView.Adapter<ConsultAdapter.ConsultViewHolder>() {

    private var consults = emptyList<Consult>()

    class ConsultViewHolder(
        private val binding: ConsultRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(consult: Consult) {
            binding.consult = consult
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ConsultViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ConsultRowLayoutBinding.inflate(layoutInflater, parent, false)
                return ConsultViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultViewHolder {
        return ConsultViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ConsultViewHolder, position: Int) {
        val current = consults[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return consults.size
    }

    fun setData(data: List<Consult>) {
        consults = data
        notifyDataSetChanged()
    }
}