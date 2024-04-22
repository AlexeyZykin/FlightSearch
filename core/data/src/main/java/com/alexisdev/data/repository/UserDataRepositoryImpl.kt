package com.alexisdev.data.repository

import com.alexisdev.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.Flow

internal class UserDataRepositoryImpl(
    private val preferencesDataSource: PreferencesDataSource
) : UserDataRepository {

    override val departurePoint: Flow<String?>
        get() = preferencesDataSource.readDeparturePoint

    override val pathway: Flow<String?>
        get() = preferencesDataSource.readPathway

    override val flightDetails: Flow<String?>
        get() = preferencesDataSource.readFlightDetails

    override suspend fun saveDeparturePoint(query: String) {
        preferencesDataSource.saveDeparturePoint(query)
    }

    override suspend fun saveFlightData(pathway: String, flightDetails: String) {
        preferencesDataSource.saveFlightDetails(flightDetails)
        preferencesDataSource.savePathway(pathway)
    }

}