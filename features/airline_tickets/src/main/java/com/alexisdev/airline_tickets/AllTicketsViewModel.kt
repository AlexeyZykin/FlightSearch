package com.alexisdev.airline_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.airline_tickets.state.AllTicketsUiState
import com.alexisdev.common.Response
import com.alexisdev.data.repository.UserDataRepository
import com.alexisdev.domain.FetchAllTicketsUseCase
import com.alexisdev.model.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AllTicketsViewModel(
    private val fetchAllTicketsUseCase: FetchAllTicketsUseCase,
    private val userDataRepository: UserDataRepository
    ) : ViewModel() {
    private val _tickets = MutableLiveData<AllTicketsUiState<List<Ticket>>>()
    val tickets: LiveData<AllTicketsUiState<List<Ticket>>>  get() = _tickets
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
        fetchAllTicketsUseCase.invoke().distinctUntilChanged().collect { response ->
            when (response) {
                is Response.Loading -> _tickets.postValue(AllTicketsUiState.Loading())

                is Response.Success -> if (response.data != null) {
                    _tickets.postValue(AllTicketsUiState.Success(response.data))
                }

                is Response.Error -> _tickets.postValue(AllTicketsUiState.Error(response.msg))
            }
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