package com.example.android.lesson8.fragments

import android.os.Bundle
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
import com.example.android.lesson8.databinding.FragmentEmployeesBinding
import com.example.android.lesson8.fragments.adapters.EmployeeRecyclerViewAdapter
import com.example.android.lesson8.fragments.factories.EmployeeViewModelFactory
import com.example.android.lesson8.fragments.viewmodels.EmployeeViewModel
import android.os.Parcelable

private const val BUNDLE_RECYCLER_LAYOUT = "employees.fragment.recycler.layout"

class EmployeesFragment : Fragment() {
    private lateinit var binding: FragmentEmployeesBinding
    private lateinit var employeeViewModel:  EmployeeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employees, container, false)

        // Initialize ViewModel
        val application = requireNotNull(this.activity).application as Lesson8Application
        val employeeViewModelFactory = EmployeeViewModelFactory(application.employeeRepository)
        employeeViewModel = ViewModelProvider(requireActivity(), employeeViewModelFactory)[EmployeeViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize RecyclerView
        recyclerView = binding.employeesRecyclerView
        val employeeRecyclerViewAdapter = EmployeeRecyclerViewAdapter()
        recyclerView.adapter = employeeRecyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        employeeViewModel.employees.observe(viewLifecycleOwner, Observer { it ->
            employeeRecyclerViewAdapter.setData(it)

            binding.progressBar.visibility = View.GONE
            binding.employeesRecyclerView.visibility = View.VISIBLE
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