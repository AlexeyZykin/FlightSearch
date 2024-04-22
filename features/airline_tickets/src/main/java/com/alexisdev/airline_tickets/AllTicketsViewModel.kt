package com.alexisdev.airline_tickets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.data.repository.UserDataRepository
import com.alexisdev.domain.FetchAllTicketsUseCase
import com.alexisdev.model.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AllTicketsViewModel(
    private val fetchAllTicketsUseCase: FetchAllTicketsUseCase,
    private val userDataRepository: UserDataRepository
    ) : ViewModel() {
    private val _tickets = MutableLiveData<List<Ticket>>()
    val tickets: LiveData<List<Ticket>> get() = _tickets
    private val _pathway = MutableLiveData<String>()
    val pathway: LiveData<String> get() = _pathway
    private val _flightDetails = MutableLiveData<String>()
    val flightDetails: LiveData<String> get() = _flightDetails

    init {
        fetchTickets()
        getSavedPathway()
        getFlightDetails()
    }

    private fun fetchTickets() = viewModelScope.launch(Dispatchers.IO) {
        fetchAllTicketsUseCase.invoke().distinctUntilChanged().collect { tickets ->
            Log.d("AllTicketsViewModel", tickets.toString())
            _tickets.postValue(tickets)
        }
    }

    private fun getSavedPathway() = viewModelScope.launch(Dispatchers.IO) {
        userDataRepository.pathway.collect { pathwayValue ->
            pathwayValue?.let {
                _pathway.postValue(it)
            }
        }
    }

    private fun getFlightDetails() = viewModelScope.launch(Dispatchers.IO) {
        userDataRepository.flightDetails.collect { flightDetailsValue ->
            flightDetailsValue?.let {
                _flightDetails.postValue(it)
            }
        }
    }
}