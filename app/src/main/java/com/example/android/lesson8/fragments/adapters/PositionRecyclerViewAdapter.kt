package com.example.android.lesson8.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lesson8.R
import com.example.android.lesson8.databinding.PositionsRecyclerViewItemBinding
import com.example.android.lesson8.models.Position

class PositionRecyclerViewAdapter() :
    RecyclerView.Adapter<PositionRecyclerViewAdapter.PositionHolder>() {
    private var data: List<Position> = listOf()

    fun setData(positions: List<Position>) {
        data = positions
        notifyDataSetChanged()
    }

    class PositionHolder(private val binding: PositionsRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Position) {
            binding.position = position
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PositionRecyclerViewAdapter.PositionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PositionsRecyclerViewItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.positions_recycler_view_item, parent, false)
        return PositionHolder((binding))
    }

    override fun onBindViewHolder(
        holder: PositionRecyclerViewAdapter.PositionHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size ?: 0
    }
}