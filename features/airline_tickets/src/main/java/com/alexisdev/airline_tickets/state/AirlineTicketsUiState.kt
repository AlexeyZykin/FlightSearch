package com.alexisdev.airline_tickets.state

sealed class AirlineTicketsUiState<T> {
    data class Loading<T>(val data: T? = null): AirlineTicketsUiState<T>()
    data class Success<T>(val data: T? = null): AirlineTicketsUiState<T>()
    data class Error<T>(val msg: String? = null): AirlineTicketsUiState<T>()
}