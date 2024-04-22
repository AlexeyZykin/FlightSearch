package com.alexisdev.airline_tickets.adapter

import com.alexisdev.airline_tickets.util.imgRes
import com.alexisdev.model.Recommendation
import com.alexisdev.resources.databinding.RecommendationItemBinding
import com.alexisdev.ui.adapter.ViewBindingDelegateAdapter
import com.bumptech.glide.Glide

class RecommendationDelegateAdapter(private val onClickItem: (Recommendation) -> Unit) :
    ViewBindingDelegateAdapter<Recommendation, RecommendationItemBinding>(RecommendationItemBinding::inflate) {
    override fun RecommendationItemBinding.onBind(item: Recommendation) {
        val context = root.context
        tvRecommendationTitle.text = item.town
        tvRecommendationSubtitle.hint = item.subtitle
        root.setOnClickListener { onClickItem(item) }
        Glide
            .with(context)
            .load(item.imgRes())
            .centerCrop()
            .into(imgRecommendation)
    }

    override fun isForViewType(item: Any): Boolean = item is Recommendation

    override fun Recommendation.getItemId(): Any = id
}