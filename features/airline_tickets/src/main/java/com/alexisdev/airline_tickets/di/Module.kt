package com.alexisdev.airline_tickets.di

import com.alexisdev.airline_tickets.AirlineTicketsViewModel
import com.alexisdev.airline_tickets.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val airlineTicketsFeatureModule = module {
    viewModel { AirlineTicketsViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
}