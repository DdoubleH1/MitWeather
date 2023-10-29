package com.example.mitweather.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitweather.R
import com.example.mitweather.adapter.LocationAdapter
import com.example.mitweather.adapter.OnItemClickListener
import com.example.mitweather.core.base.BaseFragment
import com.example.mitweather.data.model.Location
import com.example.mitweather.databinding.FragmentHomeBinding
import com.example.mitweather.databinding.LayoutLocationItemBinding
import com.example.mitweather.navigation.AppNavigation
import com.example.mitweather.utils.Constants
import com.example.mitweather.utils.Geocode
import com.example.mitweather.utils.MyResponse
import dagger.hilt.android.AndroidEntryPoint
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
    private lateinit var locationList: MutableList<Location>


//    private val locationAdapter = LocationAdapter(, this)


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        viewModel.apply {
            getAllLocation()
            locationListResponse.observe(viewLifecycleOwner) { list ->
                Log.e("HomeFragment", "initView: $list")
                locationList = list
                getCurrentWeatherOfLocationList()
            }
            isLoadingDataComplete.observe(viewLifecycleOwner) { isComplete ->
                when (isComplete) {
                    true -> {
                        Log.e("HomeFragment", "initView: Success")
                        Log.e("HomeFragment", "initView: $locationList")
                        binding.rcvLocations.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = LocationAdapter(locationList, this@HomeFragment)
                        }
                    }

                    else -> {
                        Log.e("HomeFragment", "initView: Loading")
                    }
                }
            }
        }

    }

    override fun bindingStateView() {
        super.bindingStateView()

    }


    override fun bindingAction() {
        super.bindingAction()
        binding.locationSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                coordinates = geocode.getLocationFromName(context!!, query.toString(), 1)
                Log.e("HomeFragment", "bindingAction: $coordinates")
                prepareBundle(Location(coordinates["lon"]!!, coordinates["lat"]!!, query, null))
                appNavigation.openHomeToDetail(bundle)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    override fun onItemClicked(item: Location, view: LayoutLocationItemBinding) {
        prepareBundle(item)
        appNavigation.openHomeToDetail(bundle)
    }

    private fun prepareBundle(location: Location) {
        bundle.putDouble(Constants.BUNDLE_LONGITUDE, location.lon)
        bundle.putDouble(Constants.BUNDLE_LATITUDE, location.lat)
    }

    private fun getCurrentWeatherOfLocationList() {
        lifecycleScope.launch {
            for (location in locationList) {
                val currentWeather =
                    viewModel.getCurrentWeather(location.lat, location.lon) as MyResponse.Success
                location.currentWeather = currentWeather.data
            }
            viewModel.isLoadingDataComplete.postValue(true)
        }
    }
}