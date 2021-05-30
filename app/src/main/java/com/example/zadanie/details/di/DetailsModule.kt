package com.example.zadanie.details.di

import com.example.zadanie.details.DetailsRepository
import com.example.zadanie.details.DetailsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    single { DetailsRepository() }

    viewModel { DetailsViewModel(androidApplication()) }
}