package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ioasys.diversidade.databinding.FragmentSuccessAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessAuthFragment: Fragment() {

    private var _binding: FragmentSuccessAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}