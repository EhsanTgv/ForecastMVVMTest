package com.taghavi.forecastmvvmtest.ui.weather.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.taghavi.forecastmvvmtest.R
import com.taghavi.forecastmvvmtest.databinding.CurrentWeatherFragmentBinding
import com.taghavi.forecastmvvmtest.internal.glide.ForecastAppGlideModule
import com.taghavi.forecastmvvmtest.internal.glide.GlideApp
import com.taghavi.forecastmvvmtest.ui.base.ScopedFragment
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()

    private lateinit var binding: CurrentWeatherFragmentBinding
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentWeatherFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrentWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            binding.groupLoading.visibility = View.GONE

            updateLocation("London")
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
//            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
//            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)

//            GlideApp.with(this@CurrentWeatherFragment)
//                .load("http:${it.conditionIconUrl}")
//                .into(binding.imageViewConditionIcon)
        })
    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        binding.textViewTemperature.text = "$temperature C"
        binding.textViewFeelsLikeTemperature.text = "Feels like $feelsLike C"
    }

    private fun updateCondition(condition: String) {
        binding.textViewCondition.text = condition
    }

    private fun updatePrecipitation(precipitationValume: Double) {
        val unitAbbreviation = "Precipitation: $precipitationValume mm"
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        binding.textViewWind.text = "Wind: $windDirection, $windSpeed kph"
    }

    private fun updateVisibility(visibilityDistance: Double) {
        binding.textViewVisibility.text = "Visibility: $visibilityDistance km"
    }
}