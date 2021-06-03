package com.example.zadanie.list.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zadanie.databinding.ListItemBinding
import com.example.zadanie.networking.ListItem
import com.example.zadanie.utils.extensions.getUrlFromDescription
import com.example.zadanie.utils.extensions.removeUrl

class ListRecyclerAdapter(private val context: Context) :
    ListAdapter<ListItem, ListRecyclerAdapter.ViewHolder>(ListItemDiffCallback()) {

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
        val item = getItem(position)
        viewHolder.title.text = item.title
        viewHolder.description.text = item.description.removeUrl()
        viewHolder.date.text = item.modificationDate
        Glide.with(context).load(item.imageUrl).into(viewHolder.image)
        viewHolder.item.setOnClickListener {
            val url = item.description.getUrlFromDescription()
            onItemAction?.invoke(ClickItem(url))
        }
    }

    class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }
}

