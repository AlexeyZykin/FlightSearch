package com.alexisdev.airline_tickets.adapter

import com.alexisdev.airline_tickets.R
import com.alexisdev.airline_tickets.util.imgRes
import com.alexisdev.airline_tickets.util.offerPriceFormat
import com.alexisdev.model.TicketOffer
import com.alexisdev.resources.databinding.FlightItemBinding
import com.alexisdev.ui.adapter.ViewBindingDelegateAdapter
import com.bumptech.glide.Glide

class FlightDelegateAdapter :
    ViewBindingDelegateAdapter<TicketOffer, FlightItemBinding>(FlightItemBinding::inflate) {
    override fun FlightItemBinding.onBind(item: TicketOffer) {
        val context = root.context
        tvCompanyName.text = item.title
        tvFlightTime.text = item.timeRange.joinToString(separator = " ")
        val price = item.price.value.offerPriceFormat()
        tvFlightPrice.text = "$price ${context.getString(R.string.price_currency)}"
        Glide
            .with(context)
            .load(item.imgRes())
            .centerCrop()
            .into(imgFlightItem)
    }

    override fun isForViewType(item: Any): Boolean = item is TicketOffer

    override fun TicketOffer.getItemId(): Any = id
}