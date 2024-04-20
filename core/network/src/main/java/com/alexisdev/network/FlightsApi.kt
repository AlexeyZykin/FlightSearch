package com.alexisdev.network

import com.alexisdev.network.model.AllOffersDTO
import com.alexisdev.network.model.AllTicketsDTO
import com.alexisdev.network.model.AllTicketsOffersDTO
import retrofit2.http.GET


interface FlightsApi {
    @GET("offers.json")
    suspend fun fetchOffers(): AllOffersDTO

    @GET("offers_tickets.json")
    fun fetchTicketsOffers(): AllTicketsOffersDTO

    @GET("tickets.json")
    fun fetchTickets(): AllTicketsDTO
}