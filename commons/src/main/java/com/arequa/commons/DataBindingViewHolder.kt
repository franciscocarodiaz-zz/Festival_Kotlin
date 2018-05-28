package com.arequa.commons

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * Created by FCD on 27/05/2018.
 */
class DataBindingViewHolder<MODEL>(val itemVariableId: Int, val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
    fun bindItem(item: MODEL) {
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }
}