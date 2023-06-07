package com.example.finance.presentation.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.R
import com.example.finance.data.local.TransactionLocal

class TransfersAdapter(
) : ListAdapter<TransactionLocal, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<TransactionLocal>() {
        override fun areItemsTheSame(
            oldItem: TransactionLocal,
            newItem: TransactionLocal
        ): Boolean = (oldItem as? TransactionLocal)?.id == (newItem as? TransactionLocal)?.id

        override fun areContentsTheSame(
            oldItem: TransactionLocal,
            newItem: TransactionLocal
        ): Boolean = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_transfer -> TransfersHolder.create(parent)
            else -> throw IllegalArgumentException("Error!")
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val currentItem = currentList[position]
        when (holder) {
            is TransfersHolder -> holder.onBind(currentItem as TransactionLocal)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (currentList[position]) {
            is TransactionLocal -> R.layout.item_transfer
            else -> throw IllegalArgumentException("Error")
        }
}
