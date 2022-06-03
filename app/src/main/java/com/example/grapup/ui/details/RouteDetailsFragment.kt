package com.example.grapup.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.grapup.R
import com.example.grapup.data.FakeData
import com.example.grapup.databinding.FragmentRouteDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RouteDetailsFragment : Fragment() {

    private val binding by lazy { FragmentRouteDetailsBinding.inflate(layoutInflater) }
    private val routeId by lazy { requireArguments().getString(ARG_ROUTE_ID) }
    private val route by lazy { FakeData.routes.first { it.id == routeId } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() = binding.run {
        Picasso.get().load(route.photoUrl).into(photoIV)
        ratingBar.rating = route.rating
        nameTv.text = route.description
        levelTv.text = root.context.getString(R.string.level, route.difficulty.name)
        rockTypeTv.text = root.context.getString(R.string.rock_type, route.rockType)
        heightTV.text = root.context.getString(R.string.height_meters, route.heightMeters)
        belayingTv.text = root.context.getString(R.string.belaying, route.belaying.name)
        typeTv.text = root.context.getString(R.string.type, route.type.name)
    }

    companion object {
        const val ARG_ROUTE_ID = "route_id"
    }
}
