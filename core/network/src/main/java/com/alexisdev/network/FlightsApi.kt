package com.alexisdev.network

import com.alexisdev.network.model.AllOffersDTO
import com.alexisdev.network.model.AllTicketsDTO
import com.alexisdev.network.model.AllTicketOffersDTO
import retrofit2.http.GET


interface FlightsApi {
    @GET("offers.json")
    suspend fun fetchOffers(): AllOffersDTO

    @GET("offers_tickets.json")
    suspend fun fetchTicketOffers(): AllTicketOffersDTO

    @GET("tickets.json")
    suspend fun fetchTickets(): AllTicketsDTO
}