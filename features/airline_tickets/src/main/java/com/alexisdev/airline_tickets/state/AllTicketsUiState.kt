package com.alexisdev.airline_tickets.state

sealed class AllTicketsUiState<T> {
    data class Loading<T>(val data: T? = null): AllTicketsUiState<T>()
    data class Success<T>(val data: T? = null): AllTicketsUiState<T>()
    data class Error<T>(val msg: String? = null): AllTicketsUiState<T>()
}