package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ioasys.diversidade.databinding.FragmentDetailsBinding
import com.ioasys.diversidade.models.DataStoreUser
import com.ioasys.diversidade.utils.NetworkResult
import com.ioasys.diversidade.viewmodels.AuthViewModel
import com.ioasys.diversidade.viewmodels.ConsultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var authViewModel: AuthViewModel
    private lateinit var consultViewModel: ConsultViewModel

    private lateinit var token: String
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        consultViewModel = ViewModelProvider(requireActivity()).get(ConsultViewModel::class.java)
    }

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

        authViewModel.readUserInfo.asLiveData()
            .observe(viewLifecycleOwner, { value: DataStoreUser ->
                if (value.accessToken.isNotEmpty() && value.userId.isNotEmpty()) {
                    token = value.accessToken
                    userId = value.userId
                }
            })

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

        binding.submitButton.setOnClickListener {
            requestConsult(userId, args.professionalId!!, "Problems")
        }

        consultViewModel.requestStatus.observe(viewLifecycleOwner, {res ->
            when (res) {
                is NetworkResult.Success -> {
                    val action = DetailsFragmentDirections.actionFragmentDetailsToSuccessConsultFragment()
                    findNavController().navigate(action)
                }
                is NetworkResult.Error -> {
                    res.message?.let { Log.i("DEBUG", it) }
                }
            }
        })

        return binding.root
    }

    private fun requestConsult(
        userId: String,
        professionalId: String,
        reason: String
    ) {
        consultViewModel.requestConsult(userId, professionalId, reason)
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }

}