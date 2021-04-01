package com.suy.bts.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suy.bts.databinding.ItemChecklistBinding
import com.suy.bts.model.data.ListChecklistModel

class ChecklistAdapter(private val list: List<ListChecklistModel>) :
    RecyclerView.Adapter<ChecklistAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemChecklistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size

    class ViewHolder(private val binding: ItemChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listChecklistModel: ListChecklistModel) {
            with(binding) {
                tvName.text = listChecklistModel.name
                cb.isChecked = listChecklistModel.checklistCompletionStatus ?: false
            }
        }
    }
}