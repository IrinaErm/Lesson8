package com.example.android.lesson8.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lesson8.databinding.FactsListItemLayoutBinding
import com.example.android.lesson8.models.Species
import androidx.databinding.DataBindingUtil
import com.example.android.lesson8.R


class SpeciesRecyclerViewAdapter () : RecyclerView.Adapter<SpeciesRecyclerViewAdapter.SpeciesHolder>() {
    private var dataSet: List<Species> = listOf()

    fun setData(data: List<Species>) {
        dataSet = data
        notifyDataSetChanged()
    }

    class SpeciesHolder(binding: FactsListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding: FactsListItemLayoutBinding = binding

        fun bind(species: Species) {
            binding.species = species
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FactsListItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.facts_list_item_layout, parent, false)
        return SpeciesHolder(binding)
    }

    /*override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SpeciesHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.facts_list_item_layout, viewGroup, false)

        return SpeciesHolder(view)
    }*/

    override fun onBindViewHolder(holder: SpeciesHolder, position: Int) {
        dataSet?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = dataSet?.size ?: 0
}

