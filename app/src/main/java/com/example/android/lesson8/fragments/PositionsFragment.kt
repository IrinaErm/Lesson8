package com.example.android.lesson8.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lesson8.Lesson8Application
import com.example.android.lesson8.R
import com.example.android.lesson8.databinding.FragmentPositionsBinding
import com.example.android.lesson8.fragments.adapters.PositionRecyclerViewAdapter
import com.example.android.lesson8.fragments.factories.PositionViewModelFactory
import com.example.android.lesson8.fragments.viewmodels.PositionViewModel

private const val BUNDLE_RECYCLER_LAYOUT = "position.fragment.recycler.layout"

class PositionsFragment : Fragment() {
    private lateinit var binding: FragmentPositionsBinding
    private lateinit var positionViewModel: PositionViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_positions, container, false)

        // Initialize ViewModel
        val application = requireNotNull(this.activity).application as Lesson8Application
        val positionViewModelFactory = PositionViewModelFactory(application.positionRepository)
        positionViewModel = ViewModelProvider(requireActivity(), positionViewModelFactory)[PositionViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize RecyclerView
        recyclerView = binding.positionsRecyclerView
        val positionRecyclerViewAdapter = PositionRecyclerViewAdapter()
        recyclerView.adapter = positionRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        positionViewModel.positions.observe(viewLifecycleOwner, Observer { it ->
            positionRecyclerViewAdapter.setData(it)

            binding.progressBar.visibility = View.GONE
            binding.positionsRecyclerView.visibility = View.VISIBLE
        })

        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState =
                savedInstanceState.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
            recyclerView.layoutManager!!.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            BUNDLE_RECYCLER_LAYOUT,
            recyclerView.layoutManager!!.onSaveInstanceState()
        )
    }
}