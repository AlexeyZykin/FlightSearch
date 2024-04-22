package com.alexisdev.domain

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.Ticket
import kotlinx.coroutines.flow.Flow

class FetchAllTicketsUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<List<Ticket>> {
        return flightsRepository.fetchTickets()
    }
}