package com.example.zadanie.list.recycler

sealed class ListItemAction

// possibility to add more action to item in list (e.g. double click)
data class ClickItem(val url: String) : ListItemAction()