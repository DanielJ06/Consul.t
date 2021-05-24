package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ioasys.diversidade.databinding.FragmentProfileBinding
import com.ioasys.diversidade.utils.NetworkResult
import com.ioasys.diversidade.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel
    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.arrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        requestDetails(args.userId.toString(), args.token.toString())
        Log.i("debug", args.userId.toString())

        return binding.root
    }

    private fun requestDetails(id: String, token: String) {
        authViewModel.getDetailsAccount(id, token)
        authViewModel.profile.observe(viewLifecycleOwner, { res ->
            when (res) {
                is NetworkResult.Success -> {
                    binding.registerFirstNameEditText.setText(res.data?.firstName)
                    binding.registerLastNameEditText.setText(res.data?.lastName)
                    binding.registerPhoneEditText.setText(res.data?.telephone)
                    binding.registerEmailEditText.setText(res.data?.email)
                    Log.i("DEBUG", res.data.toString())
                }
                is NetworkResult.Error -> {
                    Log.i("DEBUG", res.message.toString())
                }
            }
        })
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }

}