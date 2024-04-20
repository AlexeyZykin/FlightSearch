package com.alexisdev.network.model

data class TicketOfferDTO(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceDTO
)