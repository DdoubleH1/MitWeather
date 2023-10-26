package com.example.mitweather.ui.detailWeatherScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitweather.R
import com.example.mitweather.adapter.DailyForecastAdapter
import com.example.mitweather.core.base.BaseFragment
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.Location
import com.example.mitweather.data.model.WeatherForecast
import com.example.mitweather.databinding.FragmentDetailWeatherScreenBinding
import com.example.mitweather.di.RoomDBModule
import com.example.mitweather.navigation.AppNavigation
import com.example.mitweather.utils.Constants
import com.example.mitweather.utils.ContactEvent
import com.example.mitweather.utils.MyResponse
import com.example.mitweather.utils.Standardize
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailWeatherScreen:
    BaseFragment<FragmentDetailWeatherScreenBinding, DetailWeatherScreenViewModel>(R.layout.fragment_detail_weather_screen) {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: DetailWeatherScreenViewModel by viewModels()
    private lateinit var dailyForecastAdapter: DailyForecastAdapter
    override fun getVM() = viewModel
    private var bundle = Bundle()
    private val TAG = "DetailWeatherScreen"
    private  var  longitude: Double = 0.0
    private  var  latitude: Double = 0.0
    private lateinit var currentWeather: MyResponse.Success<CurrentWeather>
    private lateinit var weatherForecast: MyResponse.Success<WeatherForecast>

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        bundle = requireArguments()
        longitude = bundle.getDouble(Constants.BUNDLE_LONGITUDE)
        latitude = bundle.getDouble(Constants.BUNDLE_LATITUDE)
        viewModel.apply {
            getCurrentWeather(latitude, longitude)
            getWeatherForecast(latitude, longitude)
        }

        viewModel.isLoadingComplete.observe(viewLifecycleOwner) { isComplete ->
            when (isComplete) {
                true -> {
                    Log.e(TAG, "initView: Success")
                    currentWeather = viewModel.currentWeatherResponse.value as MyResponse.Success<CurrentWeather>
                    weatherForecast = viewModel.weatherForecastResponse.value as MyResponse.Success<WeatherForecast>
                    bindingView(
                        currentWeather,
                        weatherForecast
                    )
                    hideLoading()
                }

                false -> {
                    showLoading()
                    Log.e(TAG, "initView: Loading")
                }
            }
        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.apply {
            btnBack.setOnClickListener {
                appNavigation.openDetailToHome(bundle)
            }
            btnMore.setOnClickListener {
                showPopUpMenu()
            }
        }
    }

    private fun bindingView(
        currentWeather: MyResponse.Success<CurrentWeather>,
        forecastList: MyResponse.Success<WeatherForecast>
    ) {
        dailyForecastAdapter = DailyForecastAdapter(forecastList.data.list)

        binding.apply {
            rvDailyForecast.adapter = dailyForecastAdapter
            rvDailyForecast.layoutManager = LinearLayoutManager(context)
            tvCity.text = currentWeather.data.name
            tvCurrentTemp.text = getString(
                R.string.current_temp,
                Standardize.standardizeDegreeCelsius(currentWeather.data.main.temp).toInt()
                    .toString()
            )
            tvWeatherStatus.text = currentWeather.data.weather[0].description
            tvHLTemp.text = getString(
                R.string.high_low_temp,
                Standardize.standardizeDegreeCelsius(currentWeather.data.main.tempMax).toInt()
                    .toString(),
                Standardize.standardizeDegreeCelsius(currentWeather.data.main.tempMin).toInt()
                    .toString()
            )
            tvHumidity.text = currentWeather.data.main.humidity.toString() + '%'
            tvWind.text = getString(R.string.km_h, currentWeather.data.wind.speed.toString())
            tvFeelsLike.text = getString(
                R.string.current_temp,
                Standardize.standardizeDegreeCelsius(currentWeather.data.main.feelsLike).toInt()
                    .toString()
            )
            tvVisibility.text = getString(
                R.string.km,
                Standardize.standardizeMeterToKilometer(currentWeather.data.visibility).toString()
            )
        }
    }

    private fun showLoading() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            layoutDetail.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        binding.apply {
            progressBar.visibility = View.GONE
            layoutDetail.visibility = View.VISIBLE
        }
    }

    private fun showPopUpMenu() {
        val popupMenu = PopupMenu(context, binding.btnMore)
        popupMenu.menuInflater.inflate(R.menu.popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_to_list -> {
                    viewModel.onEvent(ContactEvent.AddToList(Location(longitude, latitude, currentWeather.data.name, null)), requireContext())
                    appNavigation.openDetailToHome(bundle)
                    true
                }

                R.id.remove_from_list -> {
                    viewModel.onEvent(ContactEvent.RemoveFromList(longitude, latitude), requireContext())
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

}
