package com.example.zadanie.networking

import io.reactivex.Observable

class ListService(
    private val api: ListServiceApi
) {
    fun getListItems(): Observable<List<ListItem>> {
        return api.getListData()
    }
}