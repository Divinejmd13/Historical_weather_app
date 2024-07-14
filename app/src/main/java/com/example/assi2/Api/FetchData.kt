package com.example.assi2.Api

import WeatherMapService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.location.Address
import com.example.assi2.Database.HistoricalWeather
import com.example.assi2.Database.HistoricalWeatherDao
import com.example.assi2.Database.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://archive-api.open-meteo.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherMapService::class.java)

    private val _weatherData = MutableLiveData<Resource<HistoricalWeatherResponse>>()
    val weatherData: LiveData<Resource<HistoricalWeatherResponse>> = _weatherData
    var jaga_ka_nam: Address? = null
    var jaga_ka_offline_naam =""
    var date= ""
    suspend fun getAllWeather(): List<HistoricalWeather> {
        return viewModelScope.async {
            withContext(Dispatchers.IO) {
                weatherRepository.getAllWeather()
            }
        }.await()
    }


    fun deleteAllWeatherData() {
        viewModelScope.launch {
            weatherRepository.deleteAllWeatherData()
        }
    }
    suspend fun insertWeatherDataIntoDB(weatherResponse: HistoricalWeatherResponse) {

        val location = jaga_ka_nam?.let { "${it.featureName ?: ""} ${it.locality ?: ""} ${it.countryName ?: ""}" } ?: ""

        val timeList = weatherResponse.hourly?.time
        val maxTempList = weatherResponse.daily.temperature_2m_max
        val minTempList = weatherResponse.daily.temperature_2m_min
        val rainSumList = weatherResponse.daily.rain_sum
        val windSpeedMaxList = weatherResponse.daily.wind_speed_10m_max
        val windGustsMaxList = weatherResponse.daily.wind_gusts_10m_max
        val weatherCodeList = weatherResponse.daily.weather_code
        val radiationSumList = weatherResponse.daily.shortwave_radiation_sum
        val precipitationSumList = weatherResponse.daily.precipitation_sum
        val windDirectionList = weatherResponse.daily.wind_direction_10m_dominant


        val historicalWeather = HistoricalWeather(
            location = location,
            date = timeList?.getOrNull(0) ?: "",
            time = listOf(timeList?.getOrNull(0) ?: ""),
            temperature_2m_max = maxTempList,
            temperature_2m_min = (minTempList),
            rain_sum = (rainSumList),
            wind_speed_10m_max = (windSpeedMaxList),
            wind_gusts_10m_max = (windGustsMaxList),
            weather_code = (weatherCodeList),
            shortwave_radiation_sum = (radiationSumList),
            precipitation_sum = (precipitationSumList),
            wind_direction_10m_dominant = (windDirectionList)     )

//            historicalWeatherDao.insertWeather(historicalWeather)
        weatherRepository.insertWeather(historicalWeather)

    }

    fun fetchDataFromDatabase(id: Long) {
        _weatherData.value = Resource.Loading()
        viewModelScope.launch {
            try {

                val weather = weatherRepository.getWeatherById(id)
                if (weather != null) {
                    val response = HistoricalWeatherResponse(
                        latitude = null,
                        longitude = null,
                        generationtime_ms = null,
                        utc_offset_seconds = null,
                        timezone = null,
                        timezone_abbreviation = null,
                        elevation = null,
                        hourly_units = null,
                        hourly = null,
                        daily_units = null,
                        daily = DailyData(
                            time = listOf(weather.date),
                            temperature_2m_max = weather.temperature_2m_max,
                            temperature_2m_min = weather.temperature_2m_min,
                            rain_sum = weather.rain_sum,
                            wind_speed_10m_max = weather.wind_speed_10m_max,
                            wind_gusts_10m_max = weather.wind_gusts_10m_max,
                            weather_code = weather.weather_code,
                            shortwave_radiation_sum = weather.shortwave_radiation_sum,
                            precipitation_sum = weather.precipitation_sum,
                            wind_direction_10m_dominant = weather.wind_direction_10m_dominant
                        )
                    )
                    _weatherData.value = Resource.Success(response)
                } else {
                    _weatherData.value = Resource.Error("No data found in the database")
                }
            } catch (e: Exception) {
                _weatherData.value = Resource.Error("Error: ${e.message}")
            }
        }
    }




    fun fetchWeatherData(
        latitude: Double, longitude: Double, start_date: String, end_date: String,
        address: android.location.Address?
    ) {
       jaga_ka_nam=address
        _weatherData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getHistoricalWeather(latitude, longitude, start_date, end_date, "temperature_2m","temperature_2m_max,temperature_2m_min,rain_sum,wind_speed_10m_max,wind_gusts_10m_max,weather_code,shortwave_radiation_sum,precipitation_sum,wind_direction_10m_dominant")
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        _weatherData.postValue(Resource.Success(weatherResponse))
                    } else {
                        _weatherData.postValue(Resource.Error("No data found"))
                    }
                } else {
                    _weatherData.postValue(Resource.Error("Failed to fetch data"))
                }
            } catch (e: Exception) {
                _weatherData.postValue(Resource.Error("Error: ${e.message}"))
            }
        }
    }
}
