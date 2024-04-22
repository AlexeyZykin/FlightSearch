package com.alexisdev.domain

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.TicketOffer
import kotlinx.coroutines.flow.Flow

class FetchTicketOffersUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<List<TicketOffer>> {
        return flightsRepository.fetchTicketOffers()
    }
}