package com.alexisdev.airline_tickets.di

import com.alexisdev.airline_tickets.AirlineTicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val airlineTicketsFeatureModule = module {
    viewModel { AirlineTicketsViewModel(get()) }
}