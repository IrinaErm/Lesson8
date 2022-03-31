package com.example.android.lesson8.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.lesson8.databinding.FragmentSpeciesListBinding
import com.example.android.lesson8.viewmodels.SpeciesViewModel

class SpeciesListFragment : Fragment() {
    private val speciesViewModel: SpeciesViewModel by lazy {
        ViewModelProvider(this).get(SpeciesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSpeciesListBinding.inflate(inflater)
        binding.speciesViewModel = speciesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val recyclerView = binding.factsRecyclerView
        val recyclerViewAdapter = SpeciesRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        speciesViewModel.speciesList.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setData(it)
        })

        return binding.root
    }
}