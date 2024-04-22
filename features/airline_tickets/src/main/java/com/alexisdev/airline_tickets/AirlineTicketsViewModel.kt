package com.alexisdev.airline_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.airline_tickets.state.AirlineTicketsUiState
import com.alexisdev.common.Response
import com.alexisdev.data.repository.UserDataRepository
import com.alexisdev.domain.FetchOffersUseCase
import com.alexisdev.model.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AirlineTicketsViewModel(
    private val fetchOffersUseCase: FetchOffersUseCase,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    private val _offers = MutableLiveData<AirlineTicketsUiState<List<Offer>>>()
    val offers: LiveData<AirlineTicketsUiState<List<Offer>>> get() = _offers
    private val _departurePoint = MutableLiveData<String>()
    val departurePoint get() = _departurePoint


    init {
        fetchOffers()
        getSavedDeparturePoint()
    }

    private fun fetchOffers() = viewModelScope.launch(Dispatchers.IO) {
        fetchOffersUseCase.invoke().distinctUntilChanged().collect { response ->
            when (response) {
                is Response.Loading -> _offers.postValue(AirlineTicketsUiState.Loading())

                is Response.Success -> if (response.data != null) {
                    _offers.postValue(AirlineTicketsUiState.Success(response.data))
                }

                is Response.Error -> _offers.postValue(AirlineTicketsUiState.Error(response.msg))
            }
        }
    }

    fun saveDeparturePoint(query: String) = viewModelScope.launch(Dispatchers.IO) {
        userDataRepository.saveDeparturePoint(query)
    }

    fun getSavedDeparturePoint() = viewModelScope.launch {
        userDataRepository.departurePoint.collect { departurePointValue ->
            departurePointValue?.let {
                _departurePoint.postValue(it)
            }
        }
    }
}