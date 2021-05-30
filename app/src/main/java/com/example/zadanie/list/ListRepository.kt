package com.example.zadanie.list

import com.example.zadanie.networking.ListItem
import com.example.zadanie.networking.ListService
import io.reactivex.Observable

class ListRepository(
    private val listServiceApi: ListService
) : ListContract.Repository {

    override fun getListItems(): Observable<List<ListItem>> {
        return listServiceApi.getListItems()
    }
}