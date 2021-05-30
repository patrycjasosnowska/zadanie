package com.example.zadanie.list

import com.example.zadanie.networking.ListItem
import io.reactivex.Observable

class ListContract {
    interface Repository {
        fun getListItems(): Observable<List<ListItem>>
    }
}