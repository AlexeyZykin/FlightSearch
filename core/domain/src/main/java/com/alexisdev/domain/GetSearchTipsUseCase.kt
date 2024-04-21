package com.alexisdev.domain

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.model.Tip

class GetSearchTipsUseCase(private val flightsRepository: FlightsRepository) {
    suspend fun invoke(): List<Tip> {
        return flightsRepository.getSearchTips()
    }
}