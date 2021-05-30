package com.example.zadanie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie.databinding.ListItemBinding
import com.example.zadanie.networking.ListItem

class ListRecyclerAdapter(private val dataSet: List<ListItem>) :
    RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(
        itemsBinding: ListItemBinding
    ) : RecyclerView.ViewHolder(itemsBinding.root) {
        val title = itemsBinding.listItemTitle
        val description = itemsBinding.listItemDescription
        val imageUrl = itemsBinding.listItemImage
        val date = itemsBinding.listItemDate
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemsBinding = ListItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].title
        viewHolder.description.text = dataSet[position].description
        viewHolder.date.text = dataSet[position].modificationDate
    }

    override fun getItemCount() = dataSet.size

}

