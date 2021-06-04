package com.example.zadanie.data.di

import androidx.room.Room
import com.example.zadanie.data.ListItemsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single {

        Room.databaseBuilder(
            androidApplication(),
            ListItemsDatabase::class.java,
            "listitemdatabase.db"
        )
            .build()
    }

    factory {
        val database: ListItemsDatabase = get()
        database.listItemDao()
    }
}
