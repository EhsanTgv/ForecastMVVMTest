package com.taghavi.forecastmvvmtest.data.network

import androidx.lifecycle.LiveData
import com.taghavi.forecastmvvmtest.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String)
}