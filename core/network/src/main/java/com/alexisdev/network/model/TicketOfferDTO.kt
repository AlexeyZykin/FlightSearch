package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName

data class TicketOfferDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: PriceDTO
)