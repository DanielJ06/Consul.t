package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioasys.diversidade.adapters.ProfessionalAdapter
import com.ioasys.diversidade.databinding.FragmentProfessionalsBinding
import com.ioasys.diversidade.models.Professional
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfessionalFragment: Fragment() {

    private var _binding: FragmentProfessionalsBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { ProfessionalAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfessionalsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        setupRecycler()

        val fakeData: ArrayList<Professional> = ArrayList()
        fakeData.add(
            Professional(
            "2190312309",
            "Daniel de Jesus",
            "daniel@jesus.com",
            "61996909584",
            false,
            "",
            "",
            false
        ))
        fakeData.add(
            Professional(
                "2190312309",
                "Fabrício",
                "daniel@jesus.com",
                "61996909584",
                false,
                "",
                "",
                false
        ))
        fakeData.add(
            Professional(
                "2190312309",
                "Antônio",
                "daniel@jesus.com",
                "61996909584",
                false,
                "",
                "",
                false
        ))
        fakeData.add(
            Professional(
                "2190312309",
                "Elena",
                "daniel@jesus.com",
                "61996909584",
                false,
                "",
                "",
                false
        ))
        fakeData.add(
            Professional(
                "2190312309",
                "Ana",
                "daniel@jesus.com",
                "61996909584",
                false,
                "",
                "",
                false
        ))

        mAdapter.setData(fakeData)

        return binding.root
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