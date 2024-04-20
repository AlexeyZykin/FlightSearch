package com.alexisdev.data.repository

import com.alexisdev.data.toOffer
import com.alexisdev.model.Offer
import com.alexisdev.model.Ticket
import com.alexisdev.model.TicketOffer
import com.alexisdev.network.FlightsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FlightsRepositoryImpl(private val flightsApi: FlightsApi) : FlightsRepository  {
    override fun fetchOffers(): Flow<List<Offer>> = flow {
        val offers = flightsApi.fetchOffers().offers
            .map { offerDTO ->
                offerDTO.toOffer()
            }
        emit(offers)
    }

    override fun fetchTicketsOffers(): Flow<List<TicketOffer>> {
        TODO("Not yet implemented")
    }

    override fun fetchTickets(): Flow<List<Ticket>> {
        TODO("Not yet implemented")
    }
}