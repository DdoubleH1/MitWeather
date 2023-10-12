package com.example.mitweather.ui.detailWeatherScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mitweather.R
import com.example.mitweather.adapter.DailyForecastAdapter
import com.example.mitweather.core.base.BaseFragment
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.Location
import com.example.mitweather.data.model.Weather
import com.example.mitweather.databinding.FragmentDetailWeatherScreenBinding
import com.example.mitweather.utils.Constants
import com.example.mitweather.utils.MyResponse
import com.example.mitweather.utils.Standardize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWeatherScreen :
    BaseFragment<FragmentDetailWeatherScreenBinding, DetailWeatherScreenViewModel>(R.layout.fragment_detail_weather_screen) {

    private val viewModel: DetailWeatherScreenViewModel by viewModels()
    private val dataset: List<Weather> = listOf()
    private val dailyForecastAdapter = DailyForecastAdapter(dataset)
    override fun getVM() = viewModel
    private var bundle = Bundle()
    private val TAG = "DetailWeatherScreen"

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        bundle = requireArguments()
        val location = bundle.getParcelable<Location>(Constants.BUNDLE_LOCATION)
        viewModel.getCurrentWeather(location!!.lat, location.lon)
        viewModel.currentWeatherResponse.observe(viewLifecycleOwner) {response ->
            when(response) {
                is MyResponse.Loading -> {
                    Log.e(TAG, "initView: Loading")
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        layoutDetail.visibility = View.GONE
                    }
                }
                is MyResponse.Success -> {
                    Log.e(TAG, "initView: Success")
                    bindingView(response.data, location)
                    binding.apply {
                        progressBar.visibility = View.GONE
                        layoutDetail.visibility = View.VISIBLE
                    }
                }
                is MyResponse.Failure -> {
                    Log.e(TAG, "initView: Failure")
                }
            }



        }
    }

    private fun bindingView(currentWeather: CurrentWeather, location: Location) {
        binding.apply {
            rvDailyForecast.adapter = dailyForecastAdapter
            rvDailyForecast.layoutManager = LinearLayoutManager(context)
            tvCity.text = location.cityName
            tvCurrentTemp.text = getString(R.string.current_temp, Standardize.standardizeDegreeCelsius(currentWeather.main.temp).toInt().toString())
            tvWeatherStatus.text = currentWeather.weather[0].description
            tvHLTemp.text = getString(R.string.high_low_temp, Standardize.standardizeDegreeCelsius(currentWeather.main.temp_max).toInt().toString(), Standardize.standardizeDegreeCelsius(currentWeather.main.temp_min).toInt().toString())
            tvHumidity.text = currentWeather.main.humidity.toString() + '%'
            tvWind.text = getString(R.string.km_h, currentWeather.wind.speed.toString())
            tvFeelsLike.text = getString(R.string.current_temp, Standardize.standardizeDegreeCelsius(currentWeather.main.feels_like).toInt().toString())
            tvVisibility.text = getString(R.string.km, Standardize.standardizeMeterToKilometer(currentWeather.visibility).toString())
        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.apply {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            btnMore.setOnClickListener {
                showPopUpMenu()
            }
        }
    }


    private fun showPopUpMenu() {
        val popupMenu = PopupMenu(context, binding.btnMore)
        popupMenu.menuInflater.inflate(R.menu.popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_to_list -> {
                    true
                }

                R.id.remove_from_list -> {
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }


}