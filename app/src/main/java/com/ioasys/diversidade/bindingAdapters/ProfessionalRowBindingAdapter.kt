package com.ioasys.diversidade.bindingAdapters

import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.ioasys.diversidade.domain.models.Professional
import com.ioasys.diversidade.presentation.ui.fragments.ProfessionalFragmentDirections

class ProfessionalRowBindingAdapter {

    companion object {

        @BindingAdapter("onProfessionalClickListener")
        @JvmStatic
        fun onProfessionalClickListener(
            rowLayout: ConstraintLayout,
            professional: Professional
        ) {
            rowLayout.setOnClickListener {
                val action = ProfessionalFragmentDirections.actionProfessionalFragmentToFragmentDetails(
                    professionalId = professional.id,
                    name = professional.name,
                    lastName = professional.lastName,
                    crp = professional.crp,
                    email = professional.email,
                    city = professional.city,
                    remotely = professional.remotely,
                    biography = professional.biography
                )
                rowLayout.findNavController().navigate(action)
            }
        }

        @BindingAdapter("setupLocationCity", "setupLocationRemotely", requireAll = true)
        @JvmStatic
        fun setupLocation(
            view: TextView,
            city: String,
            remotely: Boolean
        ) {
            if (remotely) {
                view.text = "Atendimento: Remoto ou em $city"
            } else {
                view.text = "Atendimento: $city"
            }
        }

    }

}