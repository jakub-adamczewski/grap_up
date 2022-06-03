package com.example.grapup.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RoutesListAdapter(
    private val routeItemFactory: (ViewGroup) -> ViewHolder
) : ListAdapter<RouteListItem, ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        routeItemFactory(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        (holder as RouteViewHolder).bind(getItem(position) as RouteListItem)

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RouteListItem>() {
            override fun areItemsTheSame(oldItem: RouteListItem, newItem: RouteListItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RouteListItem, newItem: RouteListItem) =
                oldItem == newItem
        }
    }
}
