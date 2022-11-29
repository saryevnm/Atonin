package com.it.atonin.ui.home.filter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.atonin.databinding.ItemListFilterBinding
import com.it.atonin.model.Brand
import com.it.atonin.model.FilterUIModel
import com.it.atonin.model.Store

class FilterListAdapter(
    private val itemClicked: (item: FilterUIModel) -> Unit
) :
    RecyclerView.Adapter<FilterListAdapter.FilterViewHolder>() {

    var data = arrayListOf<FilterUIModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FilterUIModel>) {
        data = ArrayList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            ItemListFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearSelections() {
        data.forEach {
            it.isSelected = false
        }
        notifyDataSetChanged()
    }

    inner class FilterViewHolder(private val binding: ItemListFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: FilterUIModel) {
            with(binding) {
                titleTxt.text = when (model) {
                    is Brand -> model.name
                    is Store -> model.name
                    else -> ""
                }
                checkBx.isChecked = model.isSelected
                root.setOnClickListener {
                    checkBx.isChecked = !checkBx.isChecked
                    model.isSelected = checkBx.isChecked
                    itemClicked(model)
                }
                checkBx.setOnClickListener {
                    model.isSelected = checkBx.isChecked
                    itemClicked(model)
                }
            }
        }
    }
}