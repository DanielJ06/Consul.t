package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ioasys.diversidade.databinding.FragmentSuccessConsultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessConsultFragment: Fragment() {

    private var _binding: FragmentSuccessConsultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessConsultBinding.inflate(inflater, container, false)

        binding.successBack.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}