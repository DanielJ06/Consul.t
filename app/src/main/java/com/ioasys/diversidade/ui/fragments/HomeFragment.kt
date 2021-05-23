package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ioasys.diversidade.R
import com.ioasys.diversidade.databinding.FragmentHomeBinding
import com.ioasys.diversidade.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val args: HomeFragmentArgs by navArgs()

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeHelloMessage.text = "Olá, ${args.userName}!"

        binding.buttonTest.setOnClickListener {
            authViewModel.logout()
            findNavController().setGraph(R.navigation.auth_graph)
        }

        binding.homeOurProfessionals.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProfessionalFragment(args.token)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}