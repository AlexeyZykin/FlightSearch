package com.alexisdev.domain.di

import com.alexisdev.domain.FetchOffersUseCase
import com.alexisdev.domain.FetchRecommendationsUseCase
import com.alexisdev.domain.GetSearchTipsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchOffersUseCase(get()) }
    factory { GetSearchTipsUseCase(get()) }
    factory { FetchRecommendationsUseCase(get()) }
}