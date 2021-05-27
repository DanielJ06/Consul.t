package com.ioasys.diversidade.bindingAdapters

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.ioasys.diversidade.models.Professional
import com.ioasys.diversidade.ui.fragments.ProfessionalFragmentDirections

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

    }

}