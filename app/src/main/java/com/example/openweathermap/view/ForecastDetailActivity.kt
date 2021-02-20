package com.example.openweathermap.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.openweathermap.data.City
import com.example.openweathermap.data.Forecast

class ForecastDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val forecast = intent.getParcelableExtra<Forecast>(KEY_FORECAST_ITEM)
        val city = intent.getParcelableExtra<City>(KEY_CITY)
        title = city.name
    }

    companion object {
        const val KEY_FORECAST_ITEM =
            "com.example.openweathermap.view.ForecastDetailActivity.KEY_FORECAST"
        const val KEY_CITY = "com.example.openweathermap.view.ForecastDetailActivity.KEY_CITY"
    }
}