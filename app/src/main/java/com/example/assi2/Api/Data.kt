package com.example.assi2.Api

data class HourlyData(
    val time: List<String>,
    val temperature_2m: List<Double>
)

data class WeatherUnits(
    val time: String,
    val temperature_2m: String
)

data class DailyData(
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

data class DailyUnits(
    val time: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val rain_sum: String,
    val wind_speed_10m_max: String,
    val wind_gusts_10m_max: String,
    val weather_code: String,
    val shortwave_radiation_sum: String,
    val precipitation_sum: String,
    val wind_direction_10m_dominant:String
)

data class HistoricalWeatherResponse(
    val latitude: Double?,
    val longitude: Double?,
    val generationtime_ms: Double?,
    val utc_offset_seconds: Int?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val elevation: Int?,
    val hourly_units: WeatherUnits?,
    val hourly: HourlyData?,
    val daily_units: DailyUnits?,
    val daily: DailyData
)

