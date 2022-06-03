package com.example.grapup.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.grapup.R
import com.example.grapup.databinding.FragmentRoutesListBinding
import com.example.grapup.ui.details.RouteDetailsFragment
import com.example.grapup.ui.list.adapter.RouteViewHolder
import com.example.grapup.ui.list.adapter.RoutesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoutesListFragment : Fragment() {

    private val binding by lazy { FragmentRoutesListBinding.inflate(layoutInflater) }
    private val viewModel: RoutesListViewModel by viewModels()
    private val routesListAdapter = RoutesListAdapter(
        routeItemFactory = {
            RouteViewHolder(it) { itemId ->
                viewModel.setEvent(Event.ListItemClicked(itemId))
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        handleState()
        handleEffect()
    }

    private fun setUpView() = binding.run {
        routesRV.adapter = routesListAdapter
        routesSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setEvent(Event.SearchQueryChanged(newText.orEmpty()))
                return true
            }
        })
    }

    private fun handleState() = lifecycleScope.launch {
        viewModel.uiState.collect { state ->
            when (state) {
                is State.Data -> {
                    binding.progress.isVisible = false
                    routesListAdapter.submitList(state.items)
                }
                State.Error -> {
                    binding.progress.isVisible = false
                }
                State.Loading -> {
                    binding.progress.isVisible = true
                }
            }
        }
    }

    private fun handleEffect() = lifecycleScope.launch {
        viewModel.effect.collect { effect ->
            when (effect) {
                is Effect.OpenDetails -> findNavController()
                    .navigate(
                        R.id.routeDetails,
                        bundleOf(
                            RouteDetailsFragment.ARG_ROUTE_ID to effect.routeId
                        )
                    )
            }
        }
    }
}
