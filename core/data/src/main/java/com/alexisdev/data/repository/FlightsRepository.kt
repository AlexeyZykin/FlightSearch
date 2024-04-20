package com.alexisdev.data.repository

import com.alexisdev.model.Offer
import com.alexisdev.model.Ticket
import com.alexisdev.model.TicketOffer
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun fetchOffers(): Flow<List<Offer>>

    fun fetchTicketsOffers(): Flow<List<TicketOffer>>

    fun fetchTickets(): Flow<List<Ticket>>
}