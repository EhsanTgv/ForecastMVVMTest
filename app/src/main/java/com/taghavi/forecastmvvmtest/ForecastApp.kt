package com.taghavi.forecastmvvmtest

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.taghavi.forecastmvvmtest.data.db.ForecastDatabase
import com.taghavi.forecastmvvmtest.data.network.*
import com.taghavi.forecastmvvmtest.data.provider.LocationProvider
import com.taghavi.forecastmvvmtest.data.provider.LocationProviderImpl
import com.taghavi.forecastmvvmtest.data.repository.ForecastRepository
import com.taghavi.forecastmvvmtest.data.repository.ForecastRepositoryImpl
import com.taghavi.forecastmvvmtest.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@ForecastApp))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(),instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(),instance(),instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}