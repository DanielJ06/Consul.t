package com.ioasys.diversidade.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.ioasys.diversidade.databinding.FragmentLoginBinding
import com.ioasys.diversidade.domain.models.DataStoreUser
import com.ioasys.diversidade.presentation.viewmodels.AuthViewModel
import com.ioasys.diversidade.utils.Constants.Companion.ACCESS_TOKEN
import com.ioasys.diversidade.utils.Constants.Companion.SHARED_PREFERENCE
import com.ioasys.diversidade.utils.ViewState
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
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        authViewModel.readUserInfo.asLiveData().observe(viewLifecycleOwner, { value: DataStoreUser ->
            if (value.accessToken.isNotEmpty() && value.userId.isNotEmpty()) {
                val userId = value.userId
                val userName = value.userName
                val accessToken = value.accessToken

                val action = LoginFragmentDirections.actionLoginFragmentToMyNav(userId, userName, accessToken)
                findNavController().navigate(action)
            }
        })

        binding.loginRegisterText.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.loginSubmitButton.setOnClickListener {
            hideKeyboard()
            val emailContent = binding.loginEmailEditText.text?.toString()
            val passwdContent = binding.loginPasswordEditText.text?.toString()
            if (emailContent.isNullOrEmpty() || passwdContent.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_SHORT).show()
            } else {
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.loadingEffect.visibility = View.VISIBLE
                submit(emailContent, passwdContent)
            }
        }

        setupInputs()

        authViewModel.userData.observe(viewLifecycleOwner, {res ->
            when (res) {
                is ViewState.Success -> {
                    hideLoading()
                    binding.loginEmailInputLayout.error = null
                    binding.loginPasswordInputLayout.error = null

                    val id = res.data?.user?.id
                    val name = res.data?.user?.firstName
                    val token = res.data?.token

                    val sharedPreferences =
                        context?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)

                    sharedPreferences?.edit {
                        putString(ACCESS_TOKEN, token)
                    }

                    if (id != null && name != null && token != null) {
                        authViewModel.saveUserInfo(id, name, token)
                    }

                    val action = LoginFragmentDirections.actionLoginFragmentToMyNav(id, name, token)
                    findNavController().navigate(action)
                    authViewModel.userData.postValue(null)
                }
                is ViewState.Loading -> {
                    showLoading()
                }
                is ViewState.Error -> {
                    hideLoading()
                    binding.errorCard.visibility = View.VISIBLE
                    binding.errorMessage.text = res.message
                    binding.loginEmailInputLayout.error = "*E-mail ou senha incorretos, tente novamente"
                    binding.loginPasswordInputLayout.error = "*E-mail ou senha incorretos, tente novamente"
                }
            }
        })

        return binding.root
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun hideLoading() {
        binding.loadingProgressBar.visibility = View.INVISIBLE
        binding.loadingEffect.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.loadingProgressBar.visibility = View.VISIBLE
        binding.loadingEffect.visibility = View.VISIBLE
    }

    private fun setupInputs() {
        binding.loginEmailEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.loginEmailInputLayout.error = null
            }
        }
        binding.loginPasswordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.loginPasswordInputLayout.error = null
            }
        }
    }

    private fun submit(email: String, password: String) {
        authViewModel.signIn(email, password)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}