package com.example.mitweather.ui.detailWeatherScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mitweather.core.base.BaseViewModel
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.WeatherForecast
import com.example.mitweather.data.repository.local.LocationRepository
import com.example.mitweather.data.repository.remote.WeatherRepositoryImpl
import com.example.mitweather.di.RoomDBModule
import com.example.mitweather.utils.ContactEvent
import com.example.mitweather.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailWeatherScreenViewModel @Inject constructor(private val weatherRepositoryImpl: WeatherRepositoryImpl, private val locationRepository: LocationRepository) : BaseViewModel() {
    private var _currentWeatherResponse = MutableLiveData<MyResponse<CurrentWeather>>()
    val currentWeatherResponse: LiveData<MyResponse<CurrentWeather>> get() = _currentWeatherResponse

    private var _weatherForecastResponse = MutableLiveData<MyResponse<WeatherForecast>>()
    val weatherForecastResponse: LiveData<MyResponse<WeatherForecast>> get() = _weatherForecastResponse

    private var _isLoadingComplete = MutableLiveData(false)
    val isLoadingComplete: LiveData<Boolean> get() = _isLoadingComplete



    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _currentWeatherResponse.value = MyResponse.Loading
            weatherRepositoryImpl.getCurrentWeather(lat, lon).let {
                if (it.isSuccessful) {
                    Log.e("DetailWeatherScreen", "getCurrentWeather: ${it.body()}")
                    _currentWeatherResponse.value = MyResponse.Success(it.body()!!)
                    checkLoadingComplete()
                } else {
                    Log.e("DetailWeatherScreen", "getCurrentWeather: ${it.errorBody()}")
                    _currentWeatherResponse.value = MyResponse.Failure(Exception(it.errorBody().toString()))
                }
            }
        }
    }

    fun getWeatherForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherForecastResponse.value = MyResponse.Loading
            weatherRepositoryImpl.getWeatherForecast(lat, lon).let {
                if (it.isSuccessful) {
                    Log.e("DetailWeatherScreen", "getWeatherForecast: ${it.body()}")
                    _weatherForecastResponse.value = MyResponse.Success(it.body()!!)
                    checkLoadingComplete()
                } else {
                    Log.e("DetailWeatherScreen", "getWeatherForecast: ${it.errorBody()}")
                    _weatherForecastResponse.value = MyResponse.Failure(Exception(it.errorBody().toString()))
                }
            }
        }
    }

    fun onEvent(event: ContactEvent, context: Context){
        when(event){
            is ContactEvent.AddToList -> {
                viewModelScope.launch {
                    locationRepository.insertLocation(event.location)
                }
                Toast.makeText(context, "Added to list", Toast.LENGTH_SHORT).show()
            }
            is ContactEvent.RemoveFromList -> {
                viewModelScope.launch {
                    locationRepository.deleteLocation(event.longitude, event.latitude)
                }
                Toast.makeText(context, "Removed from list", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLoadingComplete() {
        Log.e("DetailWeatherScreenViewModel", "${_isLoadingComplete.value}")
        _isLoadingComplete.value = _currentWeatherResponse.value is MyResponse.Success && _weatherForecastResponse.value is MyResponse.Success
    }

}