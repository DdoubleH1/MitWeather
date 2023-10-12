package com.example.mitweather.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mitweather.R
import com.example.mitweather.core.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    private val _actionSPlash = MutableLiveData<SplashActionState>()
    val actionSPlash : LiveData<SplashActionState> = _actionSPlash
    init {
        viewModelScope.launch {
            delay(1000)
            _actionSPlash.value = SplashActionState.Finish
        }
    }
}

sealed class SplashActionState {
    object Finish : SplashActionState()
}