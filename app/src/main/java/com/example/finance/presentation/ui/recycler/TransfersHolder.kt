package com.example.finance.presentation.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.data.local.TransactionLocal
import com.example.finance.databinding.ItemTransferBinding

class TransfersHolder(
private val binding: ItemTransferBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private var transfer: TransactionLocal? = null

    @SuppressLint("SetTextI18n")
    fun onBind(transfer: TransactionLocal) {
        this.transfer = transfer
        with(binding) {
            tvNumber.text = "111111"
            tvSumm.text = transfer.amount.toString()
            tvTransfer.text = transfer.transactionType
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
        ): TransfersHolder = TransfersHolder(
            binding = ItemTransferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }
}
