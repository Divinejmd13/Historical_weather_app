package com.example.assi2.Database

class WeatherRepository(private val weatherDao: HistoricalWeatherDao) {
    suspend fun insertWeather(weather: HistoricalWeather) {
        weatherDao.insertWeather(weather)
    }

    suspend fun getAllWeather(): List<HistoricalWeather> {
        return weatherDao.getAllWeatherStored()
    }

    suspend fun deleteAllWeatherData() {
        weatherDao.deleteAllWeatherData()
    }

    suspend fun getWeatherById(id: Long): HistoricalWeather? {
        return weatherDao.getWeatherById(id)
    }
}
