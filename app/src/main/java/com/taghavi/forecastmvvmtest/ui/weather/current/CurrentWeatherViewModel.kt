package com.taghavi.forecastmvvmtest.ui.weather.current

import androidx.lifecycle.ViewModel
import com.taghavi.forecastmvvmtest.data.repository.ForecastRepository
import com.taghavi.forecastmvvmtest.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}