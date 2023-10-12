package com.example.mitweather.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mitweather.core.base.BaseViewModel
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.repository.remote.WeatherRepositoryImpl

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepositoryImpl) : BaseViewModel() {

    private var _currentWeatherResponse = MutableLiveData<Response<CurrentWeather>>()
    val currentWeatherResponse: LiveData<Response<CurrentWeather>> get() = _currentWeatherResponse
    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _currentWeatherResponse.value = weatherRepository.getCurrentWeather(lat, lon)
        }
    }
}