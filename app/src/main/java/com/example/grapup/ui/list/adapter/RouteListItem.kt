package com.example.grapup.ui.list.adapter

import com.example.grapup.data.Difficulty

data class RouteListItem(
    val id: String,
    val name: String,
    val difficulty: Difficulty,
    val rating: Float,
    val heightMeters: Float,
    val photoUrl: String
)
