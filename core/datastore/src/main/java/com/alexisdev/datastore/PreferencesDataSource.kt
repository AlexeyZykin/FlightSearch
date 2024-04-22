package com.alexisdev.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataSource(private val preferencesDataStore: DataStore<Preferences>) {

    val readDeparturePoint: Flow<String?> = preferencesDataStore.data
        .map { preferences ->
            preferences[PreferenceKeys.departurePoint]
        }

    val readFlightDetails: Flow<String?> = preferencesDataStore.data
        .map { preferences ->
            preferences[PreferenceKeys.flightDetails]
        }

    val readPathway: Flow<String?> = preferencesDataStore.data
        .map { preferences ->
            preferences[PreferenceKeys.pathway]
        }

    suspend fun saveDeparturePoint(query: String) {
        preferencesDataStore.edit { preferences ->
            preferences[PreferenceKeys.departurePoint] = query
        }
    }

    suspend fun saveFlightDetails(data: String) {
        preferencesDataStore.edit { preferences ->
            preferences[PreferenceKeys.flightDetails] = data
        }
    }

    suspend fun savePathway(data: String) {
        preferencesDataStore.edit { preferences ->
            preferences[PreferenceKeys.pathway] = data
        }
    }

    private object PreferenceKeys {
        val departurePoint = stringPreferencesKey(Constants.DEPARTURE_POINT_PREFERENCE)
        val pathway = stringPreferencesKey(Constants.PATHWAY_PREFERENCE)
        val flightDetails = stringPreferencesKey(Constants.FLIGHT_DETAILS)
    }
}