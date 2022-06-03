package com.example.grapup.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grapup.R
import com.example.grapup.databinding.ListItemRouteBinding
import com.squareup.picasso.Picasso

class RouteViewHolder(
    private val binding: ListItemRouteBinding,
    private val onItemClicked: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup, onItemClicked: (String) -> Unit) : this(
        ListItemRouteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClicked
    )

    fun bind(model: RouteListItem) = binding.run {
        root.setOnClickListener { onItemClicked(model.id) }
        Picasso.get().load(model.photoUrl).into(photoIV)
        nameTV.text = model.name
        ratingBar.rating = model.rating
        difficultyTv.text = model.difficulty.name
        heightTV.text = root.context.getString(R.string.height_meters, model.heightMeters)
    }
}
