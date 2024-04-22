package com.alexisdev.airline_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.airline_tickets.state.AirlineTicketsUiState
import com.alexisdev.airline_tickets.state.SearchDetailsUiState
import com.alexisdev.common.Response
import com.alexisdev.data.repository.UserDataRepository
import com.alexisdev.domain.FetchTicketOffersUseCase
import com.alexisdev.model.TicketOffer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchDetailsViewModel(
    private val fetchTicketOffersUseCase: FetchTicketOffersUseCase,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    private val _ticketOffers = MutableLiveData<SearchDetailsUiState<List<TicketOffer>>>()
    val ticketOffers: LiveData<SearchDetailsUiState<List<TicketOffer>>> get() = _ticketOffers
    private val _departurePoint = MutableLiveData<String>()
    val departurePoint get() = _departurePoint


    init {
        fetchTicketOffers()
        getSavedDeparturePoint()
    }

    private fun fetchTicketOffers() = viewModelScope.launch(Dispatchers.IO) {
        fetchTicketOffersUseCase.invoke().distinctUntilChanged().collect { response ->
            when (response) {
                is Response.Loading -> _ticketOffers.postValue(SearchDetailsUiState.Loading())

                is Response.Success -> if (response.data != null) {
                    _ticketOffers.postValue(SearchDetailsUiState.Success(response.data))
                }

                is Response.Error -> _ticketOffers.postValue(SearchDetailsUiState.Error(response.msg))
            }
        }
    }

    private fun getSavedDeparturePoint() = viewModelScope.launch {
        userDataRepository.departurePoint.distinctUntilChanged().collect { departurePointValue ->
            departurePointValue?.let {
                _departurePoint.postValue(it)
            }
        }
    }

    fun saveFlightData(pathway: String, flightDetails: String) = viewModelScope.launch {
        userDataRepository.saveFlightData(pathway, flightDetails)
    }

    fun saveDeparturePoint(data: String) = viewModelScope.launch {
        userDataRepository.saveDeparturePoint(data)
    }

}