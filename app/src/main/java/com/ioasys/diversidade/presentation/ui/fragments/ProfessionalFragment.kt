package com.ioasys.diversidade.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioasys.diversidade.adapters.ProfessionalAdapter
import com.ioasys.diversidade.databinding.FragmentProfessionalsBinding
import com.ioasys.diversidade.utils.ViewState
import com.ioasys.diversidade.presentation.viewmodels.ProfessionalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfessionalFragment: Fragment() {

    private var _binding: FragmentProfessionalsBinding? = null
    private val binding get() = _binding!!

    private lateinit var professionalViewModel: ProfessionalViewModel

    private val mAdapter by lazy { ProfessionalAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        professionalViewModel = ViewModelProvider(requireActivity()).get(ProfessionalViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfessionalsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.arrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        setupRecycler()
        requestProfessionals()

        return binding.root
    }

    private fun requestProfessionals() {
        Log.i("PROFESSIONAL", "home called")
        professionalViewModel.loadProfessionals()
        professionalViewModel.professionals.observe(viewLifecycleOwner, {res ->
            when(res) {
                is ViewState.Success -> {
                    Log.i("PROFESSIONAL", res.data?.data.toString())
                    res.data?.let { mAdapter.setData(it.data) }
                }
                is ViewState.Loading -> {}
                is ViewState.Error -> {
                    Log.i("ProfessionalInfo", res.message.toString())
                }
            }
        })
    }

    private fun setupRecycler() {
        binding.professionalRv.adapter = mAdapter;
        binding.professionalRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }

}