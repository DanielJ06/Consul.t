package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ioasys.diversidade.databinding.FragmentRegisterBinding
import com.ioasys.diversidade.models.RegisterCredentials
import com.ioasys.diversidade.utils.NetworkResult
import com.ioasys.diversidade.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var firstNameIL: TextInputLayout
    private lateinit var firstNameET: TextInputEditText
    private lateinit var lastNameIL: TextInputLayout
    private lateinit var lastNameET: TextInputEditText
    private lateinit var phoneIL: TextInputLayout
    private lateinit var phoneET: TextInputEditText
    private lateinit var emailIL: TextInputLayout
    private lateinit var emailET: TextInputEditText
    private lateinit var passwordIL: TextInputLayout
    private lateinit var passwordET: TextInputEditText
    private lateinit var confirmPasswordIL: TextInputLayout
    private lateinit var confirmPasswordET: TextInputEditText

    private var validation = 0

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.arrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        firstNameIL = binding.registerFirstNameInputLayout
        firstNameET = binding.registerFirstNameEditText
        lastNameIL = binding.registerLastNameInputLayout
        lastNameET = binding.registerLastNameEditText
        phoneIL = binding.registerPhoneInputLayout
        phoneET = binding.registerPhoneEditText
        emailIL = binding.registerEmailInputLayout
        emailET = binding.registerEmailEditText
        passwordIL = binding.registerPasswordInputLayout
        passwordET = binding.registerPasswordEditText
        confirmPasswordIL = binding.registerConfirmPasswordInputLayout
        confirmPasswordET = binding.registerConfirmPasswordEditText

        binding.registerSubmitButton.setOnClickListener {
            clearErrors()
            validate()

            if (validation != 0) {
                signUp()
            }
        }

        authViewModel.registerData.observe(viewLifecycleOwner, {res ->
            when (res) {
                is NetworkResult.Success -> {
                    val email = emailET.text.toString()
                    val pass = passwordET.text.toString()
                    val action = RegisterFragmentDirections.actionRegisterFragmentToSuccessAuthFragment(email, pass)
                    findNavController().navigate(action)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        return binding.root
    }

    private fun signUp() {
        val email = emailET.text.toString()
        val password = passwordET.text.toString()
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val telephone = phoneET.text.toString()

        authViewModel.signUp(email, password, firstName, lastName, telephone)
    }

    private fun validate() {
        validation = 1

        inputNullOrEmpty(firstNameET, firstNameIL)
        inputNullOrEmpty(lastNameET, lastNameIL)
        inputNullOrEmpty(phoneET, phoneIL)
        inputNullOrEmpty(emailET, emailIL)
        inputNullOrEmpty(passwordET, passwordIL)
        inputNullOrEmpty(confirmPasswordET, confirmPasswordIL)

        if (passwordET.text.toString() != confirmPasswordET.text.toString()) {
            validation = 0
            confirmPasswordIL.error = "*As senhas não coincidem"
        }

        if (passwordET.text!!.length < 8) {
            validation = 0
            passwordIL.error = "*Sua senha deve ter 8 caracteres ou mais"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailET.text!!).matches()) {
            validation = 0
            emailIL.error = "*Digite um email válido"
        }
    }

    private fun clearErrors() {
        firstNameIL.error = null
        lastNameIL.error = null
        phoneIL.error = null
        emailIL.error = null
        passwordIL.error = null
        confirmPasswordIL.error = null
    }

    private fun inputNullOrEmpty(fieldA: TextInputEditText, fieldB: TextInputLayout) {
        if (fieldA.text.isNullOrEmpty()) {
            fieldB.error = "*Preencha o campo"
            validation = 0
        }
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}