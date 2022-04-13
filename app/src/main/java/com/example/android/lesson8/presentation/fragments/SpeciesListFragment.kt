package com.example.android.lesson8.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.lesson8.MyApplication
import com.example.android.lesson8.databinding.FragmentSpeciesListBinding
import com.example.android.lesson8.presentation.adapters.SpeciesRecyclerViewAdapter
import com.example.android.lesson8.presentation.viewmodels.SpeciesViewModel

class SpeciesListFragment : Fragment() {
    private lateinit var speciesViewModel: SpeciesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val speciesViewModelFactory =
            (requireNotNull(activity).application as MyApplication).dependencyContainer.speciesViewModelFactory
        speciesViewModel =
            ViewModelProvider(this, speciesViewModelFactory)[SpeciesViewModel::class.java]

        val binding = FragmentSpeciesListBinding.inflate(inflater)

        val recyclerView = binding.factsRecyclerView
        val recyclerViewAdapter =
            SpeciesRecyclerViewAdapter() { speciesViewModel.onClickFavourite(it) }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        speciesViewModel.allSpecies.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            binding.factsRecyclerView.visibility = View.VISIBLE
            recyclerViewAdapter.setData(it)
        })

        return binding.root
    }
}
