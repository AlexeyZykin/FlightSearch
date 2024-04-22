package com.alexisdev.data.repository

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val departurePoint: Flow<String?>
    val pathway: Flow<String?>
    val flightDetails: Flow<String?>
    suspend fun saveDeparturePoint(query: String)
    suspend fun saveFlightData(pathway: String, flightDetails: String)
}