package com.alexisdev.airline_tickets.adapter

import android.view.View
import com.alexisdev.airline_tickets.R
import com.alexisdev.airline_tickets.util.DateManager
import com.alexisdev.airline_tickets.util.offerPriceFormat
import com.alexisdev.model.Ticket
import com.alexisdev.resources.databinding.TicketItemBinding
import com.alexisdev.ui.adapter.ViewBindingDelegateAdapter

class TicketDelegateAdapter :
    ViewBindingDelegateAdapter<Ticket, TicketItemBinding>(TicketItemBinding::inflate) {
    override fun TicketItemBinding.onBind(item: Ticket) {
        val context = root.context
        if (item.badge != null) {
            cvBadge.visibility = View.VISIBLE
            tvBadge.text = item.badge
        }

        val price = item.price.value.offerPriceFormat()
        val departureDate = DateManager.convertDateFormat(item.departure.date)
        val arrivalDate = DateManager.convertDateFormat(item.arrival.date)
        val flightDuration = DateManager.calculateFlightDuration(item.departure.date, item.arrival.date)
        val hasTransfer = if (item.hasTransfer) "/${context.getString(R.string.nonstop)}" else ""

        tvTicketPrice.text = "$price ${context.getString(R.string.price_currency)}"
        tvTicketInfo.text = "${departureDate} - ${arrivalDate}  ${flightDuration} в пути${hasTransfer}"
        tvLocationCodes.text = "${item.departure.airport}   ${item.arrival.airport}"
    }

    override fun isForViewType(item: Any): Boolean = item is Ticket

    override fun Ticket.getItemId(): Any = id
}