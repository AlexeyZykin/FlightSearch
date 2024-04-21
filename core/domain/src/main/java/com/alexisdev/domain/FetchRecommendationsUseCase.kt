package com.alexisdev.domain

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.Recommendation
import kotlinx.coroutines.flow.Flow

class FetchRecommendationsUseCase(private val flightsRepository: FlightsRepository) {
    fun invoke(): Flow<List<Recommendation>> {
        return flightsRepository.fetchRecommendations()
    }
}