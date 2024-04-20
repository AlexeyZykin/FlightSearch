package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName

data class OfferDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("town")
    val town: String,
    @SerializedName("price")
    val price: PriceDTO
)
