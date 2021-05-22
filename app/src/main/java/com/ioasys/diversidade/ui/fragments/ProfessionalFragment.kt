package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioasys.diversidade.adapters.ProfessionalAdapter
import com.ioasys.diversidade.databinding.FragmentProfessionalsBinding
import com.ioasys.diversidade.models.Professional
import com.ioasys.diversidade.utils.NetworkResult
import com.ioasys.diversidade.viewmodels.ProfessionalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfessionalFragment: Fragment() {

    private var _binding: FragmentProfessionalsBinding? = null
    private val binding get() = _binding!!

    private val args: ProfessionalFragmentArgs by navArgs()
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

        setupRecycler()
        requestProfessionals(args.token!!)

        return binding.root
    }

    private fun requestProfessionals(token: String) {
        Log.i("PROFESSIONAL", "home called")
        professionalViewModel.loadProfessionals(token)
        professionalViewModel.professionals.observe(viewLifecycleOwner, {res ->
            when(res) {
                is NetworkResult.Success -> {
                    Log.i("PROFESSIONAL", res.data?.data.toString())
                    res.data?.let { mAdapter.setData(it.data) }
                }
                is NetworkResult.Loading -> {}
                is NetworkResult.Error -> {
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