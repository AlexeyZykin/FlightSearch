package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName

data class PriceDTO(
    @SerializedName("value")
    val value: Int
)