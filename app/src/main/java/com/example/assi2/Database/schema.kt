package com.example.assi2.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historical_weather")
data class HistoricalWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
   val location: String,
    val date: String, // Date in yyyy-MM-dd format
    val time: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val rain_sum: List<Float>,
    val wind_speed_10m_max: List<Double>,
    val wind_gusts_10m_max: List<Double>,
    val weather_code: List<Int>, // Changed Double to Int
    val shortwave_radiation_sum: List<Double>, // Added shortwave_radiation_sum
    val precipitation_sum : List<Float>,
    val wind_direction_10m_dominant:List<Double>
)
