package com.taghavi.forecastmvvmtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taghavi.forecastmvvmtest.data.db.entity.CURRENT_WEATHER_ID
import com.taghavi.forecastmvvmtest.data.db.entity.Current
import com.taghavi.forecastmvvmtest.data.db.unitLocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: Current)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>
}