package com.example.zadanie.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ListItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<ListItemEntity>)
}