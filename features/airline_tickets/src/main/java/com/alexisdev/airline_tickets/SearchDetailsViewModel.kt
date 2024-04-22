package com.alexisdev.airline_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _ticketOffers = MutableLiveData<List<TicketOffer>>()
    val ticketOffers: LiveData<List<TicketOffer>> get() = _ticketOffers
    private val _departurePoint = MutableLiveData<String>()
    val departurePoint get() = _departurePoint


    init {
        fetchTicketOffers()
        getSavedDeparturePoint()
    }

    private fun fetchTicketOffers() = viewModelScope.launch(Dispatchers.IO) {
        fetchTicketOffersUseCase.invoke().distinctUntilChanged().collect { ticketOffers ->
            _ticketOffers.postValue(ticketOffers)
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