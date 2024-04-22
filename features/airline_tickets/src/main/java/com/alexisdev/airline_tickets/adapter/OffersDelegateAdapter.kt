package com.alexisdev.airline_tickets.adapter

import com.alexisdev.airline_tickets.R
import com.alexisdev.airline_tickets.util.imgRes
import com.alexisdev.airline_tickets.util.offerPriceFormat
import com.alexisdev.model.Offer
import com.alexisdev.resources.databinding.OfferItemBinding
import com.alexisdev.ui.adapter.ViewBindingDelegateAdapter
import com.bumptech.glide.Glide

class OffersDelegateAdapter : ViewBindingDelegateAdapter<Offer, OfferItemBinding>(OfferItemBinding::inflate) {
    override fun OfferItemBinding.onBind(item: Offer) {
        val context = root.context
        tvOfferTitle.text = item.title
        tvOfferTown.text = item.town

        val price = item.price.value.offerPriceFormat()
        tvOfferPrice.text =
            "${context.getString(R.string.offer_item_preposition_from)} $price ${
                context.getString(R.string.price_currency)}"

        Glide
            .with(context)
            .load(item.imgRes())
            .centerCrop()
            .into(imgOffer)
    }

    override fun isForViewType(item: Any): Boolean = item is Offer

    override fun Offer.getItemId(): Any = id
}