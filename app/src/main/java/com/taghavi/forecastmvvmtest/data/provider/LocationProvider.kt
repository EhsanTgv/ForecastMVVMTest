package com.taghavi.forecastmvvmtest.data.provider

import com.taghavi.forecastmvvmtest.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}