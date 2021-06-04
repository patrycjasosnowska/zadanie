package com.example.zadanie.utils.extensions

import com.example.zadanie.data.ListItemEntity
import com.example.zadanie.networking.ListItem

fun List<ListItem>.mapToEntity(): List<ListItemEntity> {
    return map { listItem ->
        ListItemEntity(
            description = listItem.description,
            imageUrl = listItem.imageUrl,
            modificationDate = listItem.modificationDate,
            orderId = listItem.orderId,
            title = listItem.title
        )
    }
}