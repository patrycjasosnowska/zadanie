package com.example.zadanie.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zadanie.app.AppViewModel
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class ListViewModel(
    application: Application,
    private val repository: ListRepository
) : AppViewModel(application) {

    val viewState: LiveData<ListViewState>
        get() = _viewState

    private val _viewState = MutableLiveData<ListViewState>()

    init {
        _viewState.value = ListViewState.Empty()
        getListItems()
    }

    private fun getListItems() {
        disposables.add(
            repository.getListItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { items ->
                    if (items.isEmpty()) {
                        ListViewState.Empty()
                    } else {
                        ListViewState.Data(data = items)
                    }
                }
                .subscribe(
                    { state ->
                        _viewState.value = state
                    },
                    { error ->
                        Log.e(javaClass.name, "Error fetching list items: $error")
                    }
                )
        )
    }
}