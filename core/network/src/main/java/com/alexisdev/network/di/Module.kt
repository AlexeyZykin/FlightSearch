package com.alexisdev.network.di

import com.alexisdev.network.Constants
import com.alexisdev.network.FlightsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideProductsService(get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

private fun provideProductsService(retrofit: Retrofit): FlightsApi {
    return retrofit.create(FlightsApi::class.java)
}

private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}

private fun provideInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.HEADERS
    }
}