package com.example.grapup.ui.list

import androidx.lifecycle.viewModelScope
import com.example.grapup.base.BaseViewModel
import com.example.grapup.data.FakeData
import com.example.grapup.ui.list.adapter.RouteListItem
import com.example.grapup.ui.list.adapter.toListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesListViewModel @Inject constructor() : BaseViewModel<Event, State, Effect>() {

    init {
        viewModelScope.launch {
            delay(1000)
            if (currentState is State.Loading) {
                setState { State.Data(getFakeList("")) }
            }
        }
    }

    override fun createInitialState(): State = State.Loading

    override fun handleEvent(event: Event) = when (event) {
        is Event.ListItemClicked -> setEffect { Effect.OpenDetails(event.itemId) }
        is Event.SearchQueryChanged -> setState { State.Data(getFakeList(event.query)) }
    }

    private fun getFakeList(query: String): List<RouteListItem> = FakeData.routes
        .filter { it.description.lowercase().contains(query) }
        .map { it.toListItem() }
}
