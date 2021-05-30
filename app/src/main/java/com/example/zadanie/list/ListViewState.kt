package com.example.zadanie.list

import com.example.zadanie.networking.ListItem

sealed class ListViewState {
    abstract val data: List<ListItem>

    data class Empty(
        override val data: List<ListItem> = emptyList()
    ) : ListViewState()

    data class Data(
        override val data: List<ListItem>
    ) : ListViewState()
}