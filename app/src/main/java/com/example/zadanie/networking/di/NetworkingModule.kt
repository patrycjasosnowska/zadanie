package com.example.zadanie.networking.di

import com.example.zadanie.networking.ListApi
import com.example.zadanie.networking.ListService
import com.example.zadanie.networking.ListServiceApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val networkingModule = module {

    single { provideListServiceApi() }

    single { ListService(get()) }
}

fun provideListServiceApi(): ListServiceApi =
    Retrofit.Builder()
        .baseUrl(ListApi.LIST_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create()
