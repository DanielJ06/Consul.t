package com.ioasys.diversidade.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ioasys.diversidade.databinding.ProfessionalRowLayoutBinding
import com.ioasys.diversidade.domain.models.Professional
import com.ioasys.diversidade.domain.models.ProfessionalsList

class ProfessionalAdapter: RecyclerView.Adapter<ProfessionalAdapter.ProfessionalViewHolder>() {

    private var professionals = emptyList<Professional>()

    class ProfessionalViewHolder(
        private val binding: ProfessionalRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(professional: Professional) {
            binding.professional = professional
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProfessionalViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfessionalRowLayoutBinding.inflate(layoutInflater, parent, false)
                return ProfessionalViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalViewHolder {
        return ProfessionalViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProfessionalViewHolder, position: Int) {
        val current = professionals[position]
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return professionals.size
    }

    fun setData(data: List<Professional>) {
        professionals = data
        notifyDataSetChanged()
    }

}