package com.example.zadanie.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class ListItemEntity(
    @PrimaryKey @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "modificationDate") val modificationDate: String,
    @ColumnInfo(name = "order_id") val orderId: String,
    @ColumnInfo(name = "title") val title: String
)