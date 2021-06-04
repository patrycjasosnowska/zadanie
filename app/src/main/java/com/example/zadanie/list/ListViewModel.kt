package com.example.zadanie.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zadanie.app.AppViewModel
import com.example.zadanie.data.ListItemDao
import com.example.zadanie.data.ListItemEntity
import com.example.zadanie.utils.extensions.mapToEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListViewModel(
    application: Application,
    private val repository: ListRepository,
    private val listItemDao: ListItemDao
) : AppViewModel(application) {

    val viewState: LiveData<ListViewState>
        get() = _viewState

    private val _viewState = MutableLiveData<ListViewState>()

    init {
        _viewState.value = ListViewState.Empty()
        getListItems()
    }

    fun getListItems() {
        disposables.add(
            repository.getListItems()
                .subscribeOn(Schedulers.io())
                .map { items ->
                    if (items.isEmpty()) {
                        ListViewState.Empty()
                    } else {
                        ListViewState.Data(data = items)
                    }
                }
                .doOnNext { items ->
                    val mappedItems = items.data.mapToEntity()
                    saveItemsToDatabase(mappedItems)
                }
                .observeOn(AndroidSchedulers.mainThread())
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

    private fun saveItemsToDatabase(items: List<ListItemEntity>) {
        listItemDao.insert(items)
    }
}