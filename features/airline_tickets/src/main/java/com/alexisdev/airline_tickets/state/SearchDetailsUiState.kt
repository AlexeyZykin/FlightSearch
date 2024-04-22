package com.alexisdev.airline_tickets.state

sealed class SearchDetailsUiState<T> {
    data class Loading<T>(val data: T? = null): SearchDetailsUiState<T>()
    data class Success<T>(val data: T? = null): SearchDetailsUiState<T>()
    data class Error<T>(val msg: String? = null): SearchDetailsUiState<T>()
}