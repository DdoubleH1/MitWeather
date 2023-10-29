package com.example.mitweather.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mitweather.core.base.BaseViewModel
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.Location
import com.example.mitweather.data.repository.local.LocationRepositoryImpl
import com.example.mitweather.data.repository.remote.WeatherRepositoryImpl
import com.example.mitweather.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepositoryImpl,
    private val locationRepository: LocationRepositoryImpl
) : BaseViewModel() {

    private var _currentWeatherResponse = MutableLiveData<MyResponse<CurrentWeather>>()
     val currentWeatherResponse: LiveData<MyResponse<CurrentWeather>> get() = _currentWeatherResponse

    private var _locationListResponse = MutableLiveData<MutableList<Location>>()
    val locationListResponse: LiveData<MutableList<Location>> get() = _locationListResponse

    var isLoadingDataComplete = MutableLiveData(false)



    suspend fun getCurrentWeather(lat: Double, lon: Double): MyResponse<CurrentWeather>{
        val currentWeather = GlobalScope.async { weatherRepository.getCurrentWeather(lat, lon) }
        return MyResponse.Success(currentWeather.await().body()!!)

    }

    fun getAllLocation() {
        viewModelScope.launch {
            locationRepository.getAllLocation().flowOn(Dispatchers.IO).catch { e ->
                Log.e("HomeViewModel", "getAllLocation: ${e.message}")
            }.collect { list ->
                _locationListResponse.value = list
            }

        }
    }
}