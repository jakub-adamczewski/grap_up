package com.example.grapup.ui.list

import com.example.grapup.base.UiEffect
import com.example.grapup.base.UiEvent
import com.example.grapup.base.UiState
import com.example.grapup.ui.list.adapter.RouteListItem

sealed class State : UiState {
    object Loading : State()
    object Error : State()
    data class Data(
        val items: List<RouteListItem>
    ) : State()
}

sealed class Effect : UiEffect {
    data class OpenDetails(val routeId: String) : Effect()
}

sealed class Event : UiEvent {
    data class ListItemClicked(val itemId: String) : Event()
    data class SearchQueryChanged(val query: String) : Event()
}
