package com.example.zadanie.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie.R
import com.example.zadanie.databinding.ActivityListBinding
import com.example.zadanie.details.DetailsActivity
import com.example.zadanie.list.recycler.ClickItem
import com.example.zadanie.list.recycler.ListRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by viewModel()

    private lateinit var binding: ActivityListBinding

    private lateinit var listItemsRecyclerView: RecyclerView

    private lateinit var listItemsRecyclerAdapter: ListRecyclerAdapter

    private val openItemDetails =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d(javaClass.name, "Intent result: ok")
            } else {
                Log.e(javaClass.name, "Error while starting DetailsActivity: ${result.resultCode}")
            }
        }

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
        listItemsRecyclerView = binding.listItemsRecyclerView

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        listItemsRecyclerView.layoutManager = linearLayoutManager

        listItemsRecyclerAdapter = ListRecyclerAdapter(binding.viewState!!.data, this)
        listItemsRecyclerAdapter.onItemAction = { action ->
            when (action) {
                is ClickItem -> {
                    openItemDetails.launch(
                        Intent(this, DetailsActivity::class.java).apply {
                            putExtra(getString(R.string.intent_key_uri), action.url)
                        }
                    )
                }
            }
        }
        listItemsRecyclerView.adapter = listItemsRecyclerAdapter
    }
}