package com.example.grapup.ui.list.adapter

import com.example.grapup.data.Route

fun Route.toListItem(): RouteListItem =
    RouteListItem(
        id = id,
        name = description,
        difficulty = difficulty,
        rating = rating,
        heightMeters = heightMeters,
        photoUrl = photoUrl
    )
