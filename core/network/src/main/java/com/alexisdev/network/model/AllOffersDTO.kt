package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName


data class AllOffersDTO(
    @SerializedName("offers") val offers: List<OfferDTO>
)