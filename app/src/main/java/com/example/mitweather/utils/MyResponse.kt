package com.example.mitweather.utils

sealed class MyResponse<out T>{
    object Loading: MyResponse<Nothing>()

    data class Success<out T>(
        val data: T
    ): MyResponse<T>()

    data class Failure(
        val e: Exception
    ): MyResponse<Nothing>()
}
