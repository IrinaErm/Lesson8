package com.example.android.lesson8.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lesson8.databinding.FactsListItemLayoutBinding
import com.example.android.lesson8.model.models.Species
import androidx.databinding.DataBindingUtil
import com.example.android.lesson8.R


class SpeciesRecyclerViewAdapter(
    private val changeFavour: (Species) -> Boolean
) : RecyclerView.Adapter<SpeciesRecyclerViewAdapter.SpeciesHolder>() {
    private var dataSet: List<Species>? = listOf()

    fun setData(data: List<Species>?) {
        dataSet = data
        notifyDataSetChanged()
    }

    class SpeciesHolder(binding: FactsListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding: FactsListItemLayoutBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FactsListItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.facts_list_item_layout, parent, false)
        return SpeciesHolder(binding)
    }

    override fun onBindViewHolder(holder: SpeciesHolder, position: Int) {
        dataSet?.get(position)?.let { holder.binding.species = it }

        dataSet?.get(position)?.let {
            if (it.isFavourite) {
                holder.binding.favouriteSign.setImageResource(R.drawable.favour_star)
            } else {
                holder.binding.favouriteSign.setImageResource(R.drawable.disfavour_star)
            }
        }

        holder.binding.favouriteSign.setOnClickListener {
            dataSet?.get(position)?.let {
                if (changeFavour(it)) {
                    holder.binding.favouriteSign.setImageResource(R.drawable.favour_star)
                } else {
                    holder.binding.favouriteSign.setImageResource(R.drawable.disfavour_star)
                }
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0
}

