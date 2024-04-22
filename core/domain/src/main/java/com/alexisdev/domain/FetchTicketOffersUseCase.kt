package com.alexisdev.domain

import com.alexisdev.common.Response
import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.TicketOffer
import kotlinx.coroutines.flow.Flow

class FetchTicketOffersUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<Response<List<TicketOffer>>> {
        return flightsRepository.fetchTicketOffers()
    }
}