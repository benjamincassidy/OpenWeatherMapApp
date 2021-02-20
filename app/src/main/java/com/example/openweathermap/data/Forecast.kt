package com.example.openweathermap.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Forecast(val cnt: Int, val list: List<ForecastItem>, val city: City) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ForecastItem(
    val dt: Long,
    val main: ForecastMain,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ForecastMain(
    val temp: Float,
    @Json(name = "feels_like")
    val feelsLike: Float,
    @Json(name = "temp_min")
    val tempMin: Float,
    @Json(name = "temp_max")
    val tempMax: Float,
    val pressure: Int,
    @Json(name = "sea_level")
    val seaLevel: Int,
    @Json(name = "grnd_level")
    val groundLevel: Int,
    val humidity: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Weather(
    val main: String,
    val description: String,
    val icon: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Clouds(val all: Int) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Wind(val speed: Float, val deg: Int) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class City(
    val name: String,
    val country: String
) : Parcelable
