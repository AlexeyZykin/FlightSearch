package com.alexisdev.data.repository

import com.alexisdev.common.Response
import com.alexisdev.model.Offer
import com.alexisdev.model.Recommendation
import com.alexisdev.model.Ticket
import com.alexisdev.model.TicketOffer
import com.alexisdev.model.Tip
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun fetchOffers(): Flow<Response<List<Offer>>>

    fun fetchTicketOffers(): Flow<Response<List<TicketOffer>>>

    fun fetchTickets(): Flow<Response<List<Ticket>>>

    fun fetchRecommendations(): Flow<List<Recommendation>>

    suspend fun getSearchTips(): List<Tip>
}