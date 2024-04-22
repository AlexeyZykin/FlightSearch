package com.alexisdev.airline_tickets.di

import com.alexisdev.airline_tickets.AirlineTicketsViewModel
import com.alexisdev.airline_tickets.AllTicketsViewModel
import com.alexisdev.airline_tickets.SearchDetailsViewModel
import com.alexisdev.airline_tickets.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val airlineTicketsFeatureModule = module {
    viewModel { AirlineTicketsViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { SearchDetailsViewModel(get(), get()) }
    viewModel { AllTicketsViewModel(get(), get()) }
}