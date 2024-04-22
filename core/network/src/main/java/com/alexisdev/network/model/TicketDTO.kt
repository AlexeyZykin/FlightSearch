package com.alexisdev.network.model

import com.google.gson.annotations.SerializedName

data class TicketDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("badge") val badge: String?,
    @SerializedName("price") val price: PriceDTO,
    @SerializedName("provider_name") val providerName: String,
    @SerializedName("company") val company: String,
    @SerializedName("departure") val departure: FlightInfoDTO,
    @SerializedName("arrival") val arrival: FlightInfoDTO,
    @SerializedName("has_transfer") val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    @SerializedName("luggage") val luggage: LuggageDTO,
    @SerializedName("hand_luggage") val handLuggage: HandLuggageDTO,
    @SerializedName("is_returnable") val isReturnable: Boolean,
    @SerializedName("is_exchangable") val isExchangeable: Boolean,
)

data class FlightInfoDTO(
    @SerializedName("town") val town: String,
    @SerializedName("date") val date: String,
    @SerializedName("airport") val airport: String
)

data class LuggageDTO(
    @SerializedName("has_luggage") val hasLuggage: Boolean,
    @SerializedName("price") val price: PriceDTO?,
)

data class HandLuggageDTO(
    @SerializedName("has_hand_luggage") val hasHandLuggage: Boolean,
    @SerializedName("size") val size: String?
)
