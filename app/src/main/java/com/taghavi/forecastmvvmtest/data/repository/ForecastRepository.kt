package com.taghavi.forecastmvvmtest.data.repository

import androidx.lifecycle.LiveData
import com.taghavi.forecastmvvmtest.data.db.unitLocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<out UnitSpecificCurrentWeatherEntry>
}