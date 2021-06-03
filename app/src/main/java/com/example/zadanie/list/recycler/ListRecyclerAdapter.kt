package com.example.zadanie.list.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zadanie.databinding.ListItemBinding
import com.example.zadanie.networking.ListItem
import com.example.zadanie.utils.extensions.getUrlFromDescription
import com.example.zadanie.utils.extensions.removeUrl

class ListRecyclerAdapter(private val dataSet: List<ListItem>, private val context: Context) :
    RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    var onItemAction: ((ListItemAction) -> Unit)? = null

    class ViewHolder(
        itemsBinding: ListItemBinding
    ) : RecyclerView.ViewHolder(itemsBinding.root) {
        val item = itemsBinding.root
        val title = itemsBinding.listItemTitle
        val description = itemsBinding.listItemDescription
        val image = itemsBinding.listItemImage
        val date = itemsBinding.listItemDate
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemsBinding = ListItemBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].title
        viewHolder.description.text = dataSet[position].description.removeUrl()
        viewHolder.date.text = dataSet[position].modificationDate
        Glide.with(context).load(dataSet[position].imageUrl).into(viewHolder.image)
        viewHolder.item.setOnClickListener {
            val url = dataSet[position].description.getUrlFromDescription()
            onItemAction?.invoke(ClickItem(url))
        }
    }

    override fun getItemCount() = dataSet.size
}

