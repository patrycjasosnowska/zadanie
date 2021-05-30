package com.example.zadanie.networking

import io.reactivex.Observable
import retrofit2.http.GET

interface ListServiceApi {
    @GET(ListApi.LIST_URL)
    fun getListData(): Observable<List<ListItem>>
}