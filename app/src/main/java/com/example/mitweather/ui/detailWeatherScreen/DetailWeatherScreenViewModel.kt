package com.example.mitweather.ui.detailWeatherScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mitweather.core.base.BaseViewModel
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.repository.remote.WeatherRepositoryImpl
import com.example.mitweather.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailWeatherScreenViewModel @Inject constructor(private val weatherRepositoryImpl: WeatherRepositoryImpl) : BaseViewModel() {
    private var _currentWeatherResponse = MutableLiveData<MyResponse<CurrentWeather>>()
    val currentWeatherResponse: LiveData<MyResponse<CurrentWeather>> get() = _currentWeatherResponse
    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _currentWeatherResponse.value = MyResponse.Loading
            weatherRepositoryImpl.getCurrentWeather(lat, lon).let {
                if (it.isSuccessful) {
                    Log.e("DetailWeatherScreen", "getCurrentWeather: ${it.body()}")
                    _currentWeatherResponse.value = MyResponse.Success(it.body()!!)
                } else {
                    Log.e("DetailWeatherScreen", "getCurrentWeather: ${it.errorBody()}")
                    _currentWeatherResponse.value = MyResponse.Failure(Exception(it.errorBody().toString()))
                }
            }
        }
    }




}