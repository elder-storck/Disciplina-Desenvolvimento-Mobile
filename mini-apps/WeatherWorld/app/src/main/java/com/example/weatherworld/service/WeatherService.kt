package com.example.weatherworld.service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherworld.model.WeatherEntity


interface WeatherGetService {

    @GET("data/2.5/weather")
    fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pt_br"
    ): Call<WeatherEntity>

}