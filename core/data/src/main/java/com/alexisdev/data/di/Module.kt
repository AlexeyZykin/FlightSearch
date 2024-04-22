package com.alexisdev.data.di

import com.alexisdev.data.repository.FlightsRepository
import com.alexisdev.data.repository.FlightsRepositoryImpl
import com.alexisdev.data.repository.UserDataRepository
import com.alexisdev.data.repository.UserDataRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<FlightsRepository> { FlightsRepositoryImpl(get()) }
    single<UserDataRepository> { UserDataRepositoryImpl(get()) }
}