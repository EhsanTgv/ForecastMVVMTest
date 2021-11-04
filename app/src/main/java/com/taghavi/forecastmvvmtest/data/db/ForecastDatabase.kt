package com.taghavi.forecastmvvmtest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taghavi.forecastmvvmtest.data.db.entity.Current
import com.taghavi.forecastmvvmtest.data.db.entity.WeatherLocation

@Database(
    entities = [Current::class, WeatherLocation::class],
    version = 1,
    exportSchema = false
)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object {
        private val instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java,
                "forecast.db"
            )
    }
}