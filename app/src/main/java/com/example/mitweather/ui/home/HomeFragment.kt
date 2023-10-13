package com.example.mitweather.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitweather.BuildConfig
import com.example.mitweather.R
import com.example.mitweather.adapter.LocationAdapter
import com.example.mitweather.adapter.OnItemClickListener
import com.example.mitweather.core.base.BaseFragment
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.Location
import com.example.mitweather.databinding.FragmentHomeBinding
import com.example.mitweather.databinding.LayoutLocationItemBinding
import com.example.mitweather.navigation.AppNavigation
import com.example.mitweather.utils.Constants
import com.example.mitweather.utils.Geocode
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteFragment
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    OnItemClickListener {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: HomeViewModel by viewModels()
    override fun getVM() = viewModel
    private var bundle: Bundle = Bundle()
    private var geocode: Geocode = Geocode()
    private var coordinates: Map<String, Double?> = mapOf()
    private val dataset: List<Location> = listOf(
        Location(0.0, 0.0, "Ha Noi", null),
        Location(1.0, 0.0, "Hai Phong", null),
        Location(2.0, 0.0, "Da Nang", null)
    )
    private val locationAdapter = LocationAdapter(dataset, this)
    private lateinit var autocompleteFragment: AutocompleteSupportFragment

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)


    }
    override fun bindingStateView() {
        super.bindingStateView()
        binding.rcvLocations.adapter = locationAdapter
        binding.rcvLocations.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun bindingAction() {
        super.bindingAction()
        Places.initialize(requireContext(), BuildConfig.MAP_API_KEY)
        autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG))
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.e("HomeFragment", "onPlaceSelected: ${place.name}")
                coordinates = geocode.getLocationFromName(context!!, place.name.toString(), 1)
                Log.e("HomeFragment", "bindingAction: $coordinates")
                prepareBundle(Location(coordinates["lon"]!!, coordinates["lat"]!!, place.name, null))
                appNavigation.openHomeToDetail(bundle)
            }

            override fun onError(p0: Status) {
                Log.e("HomeFragment", "onError: ${p0.statusMessage}")
            }

        })
    }

    override fun onItemClicked(item: Location, view: LayoutLocationItemBinding) {
        prepareBundle(item)
        appNavigation.openHomeToDetail(bundle)
    }

    private fun prepareBundle(location: Location) {
        bundle.putParcelable(Constants.BUNDLE_LOCATION, location)
    }




}