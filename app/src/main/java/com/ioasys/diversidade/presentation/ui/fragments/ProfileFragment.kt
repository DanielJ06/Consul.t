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
import com.ioasys.diversidade.R
import com.ioasys.diversidade.databinding.FragmentProfileBinding
import com.ioasys.diversidade.utils.ViewState
import com.ioasys.diversidade.presentation.viewmodels.AuthViewModel
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

        binding.signoutBtn.setOnClickListener {
            authViewModel.logout()
            findNavController().setGraph(R.navigation.auth_graph)
        }

        binding.goToEdit.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(
                args.userId
            )
            findNavController().navigate(action);
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
                    binding.userName.text = "${res.data?.firstName} ${res.data?.lastName}"
                    binding.userPhone.text = res.data?.telephone
                    binding.userEmail.text = res.data?.email
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