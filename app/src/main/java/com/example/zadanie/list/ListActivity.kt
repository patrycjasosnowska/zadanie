package com.example.zadanie.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie.R
import com.example.zadanie.databinding.ActivityListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by viewModel()

    private lateinit var binding: ActivityListBinding

    private lateinit var libraryItemsRecyclerView: RecyclerView

    private lateinit var listItemsRecyclerAdapter: ListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        listViewModel.viewState.observe(this) { state ->
            binding.viewState = state
            if (state is ListViewState.Data) {
                setupRecycler()
            }
        }
    }

    private fun setupRecycler() {
        libraryItemsRecyclerView = binding.listItemsRecyclerView

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        libraryItemsRecyclerView.layoutManager = linearLayoutManager

        listItemsRecyclerAdapter = ListRecyclerAdapter(binding.viewState!!.data, this)
        libraryItemsRecyclerView.adapter = listItemsRecyclerAdapter
    }
}