package com.alexisdev.domain.di

import com.alexisdev.domain.FetchOffersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchOffersUseCase(get()) }
}