package com.alexisdev.domain

import com.alexisdev.common.Response
import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.Offer
import kotlinx.coroutines.flow.Flow

class FetchOffersUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<Response<List<Offer>>> {
        return flightsRepository.fetchOffers()
    }
}