package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName

data class AllTicketOffersDTO(
    @SerializedName("tickets_offers") val ticketOffers: List<TicketOfferDTO>
)