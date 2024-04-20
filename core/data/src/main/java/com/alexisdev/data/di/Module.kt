package com.alexisdev.data.di

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.data.repository.FlightsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<FlightsRepository> { FlightsRepositoryImpl(get()) }
}