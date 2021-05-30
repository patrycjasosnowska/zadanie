package com.example.zadanie.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListItem(
    @Json(name = "description") val description: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "modificationDate") val modificationDate: String,
    @Json(name = "orderId") val orderId: String,
    @Json(name = "title") val title: String
)