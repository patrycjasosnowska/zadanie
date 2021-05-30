package com.example.zadanie.list.di

import com.example.zadanie.list.ListRepository
import com.example.zadanie.list.ListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {
    single { ListRepository(get()) }

    viewModel { ListViewModel(androidApplication(), get()) }
}