package com.example.openweathermap.service

import com.example.openweathermap.data.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast?appid=65d00499677e59496ca2f318eb68c049&units=imperial")
    suspend fun getFiveDayForecast(@Query("q") cityName: String): Forecast
}