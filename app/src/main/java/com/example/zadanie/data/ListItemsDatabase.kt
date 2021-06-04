package com.example.zadanie.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ListItemEntity::class), version = 1, exportSchema = false)
abstract class ListItemsDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao

    companion object {
        @Volatile
        private var INSTANCE: ListItemsDatabase? = null

        fun getDatabase(context: Context): ListItemsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListItemsDatabase::class.java,
                    "list_items_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}