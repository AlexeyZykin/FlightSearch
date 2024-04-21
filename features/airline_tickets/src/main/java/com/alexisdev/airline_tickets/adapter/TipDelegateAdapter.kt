package com.alexisdev.airline_tickets.adapter

import android.view.View
import com.alexisdev.airline_tickets.databinding.TipItemBinding
import com.alexisdev.airline_tickets.util.imgRes
import com.alexisdev.model.Tip
import com.alexisdev.ui.adapter.ViewBindingDelegateAdapter
import com.bumptech.glide.Glide

class TipDelegateAdapter(private val onClickItem: (Tip) -> Unit) : ViewBindingDelegateAdapter<Tip, TipItemBinding>(TipItemBinding::inflate) {
    override fun TipItemBinding.onBind(item: Tip) {
        val context = root.context
        tvTipTitle.text = item.title
        root.setOnClickListener{onClickItem(item) }
        Glide
            .with(context)
            .load(item.imgRes())
            .centerCrop()
            .into(imgTip)

    }

    override fun isForViewType(item: Any): Boolean = item is Tip

    override fun Tip.getItemId(): Any = id
}