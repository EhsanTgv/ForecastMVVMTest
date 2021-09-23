package com.taghavi.forecastmvvmtest.data.network.response


import com.google.gson.annotations.SerializedName
import com.taghavi.forecastmvvmtest.data.db.entity.Current
import com.taghavi.forecastmvvmtest.data.db.entity.Location
import com.taghavi.forecastmvvmtest.data.db.entity.Request

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)