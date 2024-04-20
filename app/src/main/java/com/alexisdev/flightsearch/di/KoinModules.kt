package com.alexisdev.flightsearch.di

import com.alexisdev.airline_tickets.di.airlineTicketsFeatureModule
import com.alexisdev.data.di.dataModule
import com.alexisdev.domain.di.domainModule
import com.alexisdev.network.di.networkModule

val koinModules = listOf(
    networkModule,
    dataModule,
    domainModule,
    airlineTicketsFeatureModule
)