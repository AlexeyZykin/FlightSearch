package com.alexisdev.airline_tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.FetchOffersUseCase
import com.alexisdev.model.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AirlineTicketsViewModel(private val fetchOffersUseCase: FetchOffersUseCase) : ViewModel() {
    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers

    init {
        fetchOffers()
    }

    private fun fetchOffers() = viewModelScope.launch(Dispatchers.IO) {
        fetchOffersUseCase.invoke().distinctUntilChanged().collect { offers ->
            _offers.postValue(offers)
        }
    }
}