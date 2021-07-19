package com.ioasys.diversidade.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ioasys.diversidade.databinding.FragmentEditProfileBinding
import com.ioasys.diversidade.utils.ViewState
import com.ioasys.diversidade.presentation.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel
    private val args: EditProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.arrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        requestDetails(args.userId.toString())
        Log.i("debug", args.userId.toString())

        return binding.root
    }

    private fun requestDetails(id: String) {
        authViewModel.getDetailsAccount(id)
        authViewModel.profile.observe(viewLifecycleOwner, { res ->
            when (res) {
                is ViewState.Success -> {
                    binding.registerFirstNameEditText.setText(res.data?.firstName)
                    binding.registerLastNameEditText.setText(res.data?.lastName)
                    binding.registerPhoneEditText.setText(res.data?.telephone)
                    binding.registerEmailEditText.setText(res.data?.email)
                    Log.i("DEBUG", res.data.toString())
                }
                is ViewState.Error -> {
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