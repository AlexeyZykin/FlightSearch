package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName

data class AllTicketsDTO(
    @SerializedName("tickets") val tickets: List<TicketDTO>
)