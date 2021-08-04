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
import androidx.recyclerview.widget.LinearLayoutManager
import com.ioasys.diversidade.adapters.ConsultAdapter
import com.ioasys.diversidade.databinding.FragmentHistoryBinding
import com.ioasys.diversidade.utils.ViewState
import com.ioasys.diversidade.presentation.viewmodels.ConsultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment: Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val args: HistoryFragmentArgs by navArgs()
    private lateinit var consultViewModel: ConsultViewModel
    private val mAdapter by lazy { ConsultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        consultViewModel = ViewModelProvider(requireActivity()).get(ConsultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        setupRecycler()

        args.userId?.let { consultViewModel.loadConsults(it) }
        consultViewModel.consults.observe(viewLifecycleOwner, {res ->
            when (res) {
                is ViewState.Success -> {
                    if (res.data?.data.isNullOrEmpty()) {
                        binding.emptyImage.visibility = View.VISIBLE
                        binding.emptyText.visibility = View.VISIBLE
                        binding.consultRv.visibility = View.INVISIBLE
                    }
                    res.data?.let { mAdapter.setData(it.data) }
                }
                is ViewState.Error -> {
                    Log.i("DEBUG Error", res.message!!)
                } else -> {
                Log.i("DEBUG else", res.toString())
                }
            }
        })

        binding.arrowLeft.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun setupRecycler() {
        binding.consultRv.adapter = mAdapter
        binding.consultRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}