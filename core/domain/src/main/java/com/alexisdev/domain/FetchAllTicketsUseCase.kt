package com.alexisdev.domain

import com.alexisdev.common.Response
import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.Ticket
import kotlinx.coroutines.flow.Flow

class FetchAllTicketsUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<Response<List<Ticket>>> {
        return flightsRepository.fetchTickets()
    }
}