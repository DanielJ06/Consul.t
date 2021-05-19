package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioasys.diversidade.databinding.FragmentLoginBinding
import com.ioasys.diversidade.utils.NetworkResult
import com.ioasys.diversidade.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.loginSubmitButton.setOnClickListener {
            val emailContent = binding.loginEmailInputLayout.text?.toString()
            val passwdContent = binding.loginPasswordInputLayout.text?.toString()
            if (emailContent.isNullOrEmpty() || passwdContent.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_SHORT).show()
            } else {
                submit(emailContent!!, passwdContent!!)
            }
        }

        authViewModel.userData.observe(viewLifecycleOwner, {res ->
            when (res) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {}
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        return binding.root
    }

    private fun submit(email: String, password: String) {
        authViewModel.signIn(email, password)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}