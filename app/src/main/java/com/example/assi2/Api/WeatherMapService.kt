import com.example.assi2.Api.HistoricalWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherMapService {
    @GET("archive")
    suspend fun getHistoricalWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("hourly") hourly: String, // Default value for hourly parameter
        @Query("daily") daily: String // Default value for daily parameter

    ): Response<HistoricalWeatherResponse>
}
