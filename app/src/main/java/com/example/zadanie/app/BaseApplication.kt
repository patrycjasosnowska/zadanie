package com.example.zadanie.app

import android.app.Application
import com.example.zadanie.details.di.detailsModule
import com.example.zadanie.list.di.listModule
import com.example.zadanie.networking.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listModule,
                detailsModule,
                networkingModule
            )
        }
    }
}