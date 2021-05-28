package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ioasys.diversidade.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.arrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val name = "${args.name} ${args.lastName}"
        val crp = args.crp
        val email = args.email
        val bio = args.biography
        val city = args.city
        val remotely = args.remotely

        binding.professionalName.text = name
        binding.professionalCrp.text = crp
        binding.professionalEmail.text = email
        binding.professionalReadme.text = bio

        if (remotely) {
            binding.professionalLocation.text = "Atendimento: Remoto ou em $city"
        } else {
            binding.professionalLocation.text = "Atendimento: $city"
        }

        return binding.root
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }

}