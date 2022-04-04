package com.example.android.lesson8.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lesson8.R
import com.example.android.lesson8.databinding.EmployeesRecyclerViewItemBinding
import com.example.android.lesson8.models.EmployeeWithPosition

class EmployeeRecyclerViewAdapter :
    RecyclerView.Adapter<EmployeeRecyclerViewAdapter.EmployeeHolder>() {
    private var data: List<EmployeeWithPosition> = listOf()

    fun setData(employees: List<EmployeeWithPosition>) {
        data = employees
        notifyDataSetChanged()
    }

    class EmployeeHolder(private val binding: EmployeesRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: EmployeeWithPosition) {
            binding.employee = employee
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeRecyclerViewAdapter.EmployeeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: EmployeesRecyclerViewItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.employees_recycler_view_item, parent, false)
        return EmployeeHolder((binding))
    }

    override fun onBindViewHolder(
        holder: EmployeeRecyclerViewAdapter.EmployeeHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size ?: 0
    }
}