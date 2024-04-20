package com.alexisdev.domain

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.Offer
import kotlinx.coroutines.flow.Flow

class FetchOffersUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<List<Offer>> {
        return flightsRepository.fetchOffers()
    }
}