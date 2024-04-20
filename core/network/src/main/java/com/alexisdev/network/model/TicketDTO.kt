package com.alexisdev.network.model

data class TicketDTO(
    val id: Int,
    val badge: String,
    val price: PriceDTO,
    val providerName: String,
    val company: String,
    val departure: FlightInfoDTO,
    val arrival: FlightInfoDTO,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageDTO,
    val handLuggage: HandLuggageDTO,
    val isReturnable: Boolean,
    val isExchangable: Boolean,
)

data class FlightInfoDTO(
    val town: String,
    val date: String,
    val airport: String
)

data class LuggageDTO(
    val hasLuggage: Boolean,
    val price: PriceDTO
)

data class HandLuggageDTO(
    val hasHandLuggage: Boolean,
    val size: String
)
