package com.alexisdev.data.repository

import com.alexisdev.model.Offer
import com.alexisdev.model.Recommendation
import com.alexisdev.model.Ticket
import com.alexisdev.model.TicketOffer
import com.alexisdev.model.Tip
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun fetchOffers(): Flow<List<Offer>>

    fun fetchTicketsOffers(): Flow<List<TicketOffer>>

    fun fetchTickets(): Flow<List<Ticket>>

    fun fetchRecommendations(): Flow<List<Recommendation>>

    suspend fun getSearchTips(): List<Tip>
}