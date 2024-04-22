package com.alexisdev.airline_tickets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.data.repository.UserDataRepository
import com.alexisdev.domain.FetchRecommendationsUseCase
import com.alexisdev.domain.GetSearchTipsUseCase
import com.alexisdev.model.Recommendation
import com.alexisdev.model.Tip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getTipsUseCase: GetSearchTipsUseCase,
    private val fetchRecommendationsUseCase: FetchRecommendationsUseCase,
    private val userDataRepository: UserDataRepository
    ) : ViewModel() {
    private val _tips = MutableLiveData<List<Tip>>()
    val tips: LiveData<List<Tip>> get() = _tips
    private val _recommendations = MutableLiveData<List<Recommendation>>()
    val recommendations: LiveData<List<Recommendation>> = _recommendations
    private val _departurePoint = MutableLiveData<String>()
    val departurePoint get() = _departurePoint

    init {
        getTips()
        fetchRecommendations()
        getSavedDeparturePoint()
    }

    private fun fetchRecommendations() = viewModelScope.launch(Dispatchers.IO) {
        fetchRecommendationsUseCase.invoke().distinctUntilChanged().collect { list ->
            _recommendations.postValue(list)
        }
    }

    private fun getTips() = viewModelScope.launch(Dispatchers.IO) {
        _tips.postValue(getTipsUseCase.invoke())
    }

    private fun getSavedDeparturePoint() = viewModelScope.launch {
        userDataRepository.departurePoint.distinctUntilChanged().collect { departurePointValue ->
            departurePointValue?.let {
                _departurePoint.postValue(it)
            }
        }
    }
}