package com.example.openweathermap.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweathermap.adapters.ForecastAdapter
import com.example.openweathermap.data.Forecast
import com.example.openweathermap.databinding.ActivityForecastBinding

class ForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.forecastList.layoutManager = LinearLayoutManager(this)
        val forecast = intent.getParcelableExtra<Forecast>(KEY_FORECAST)
        title = forecast.city.name
        binding.forecastList.adapter = ForecastAdapter(forecast) {
            val i = Intent(this, ForecastDetailActivity::class.java).apply {
                putExtra(ForecastDetailActivity.KEY_FORECAST_ITEM, forecast)
                putExtra(ForecastDetailActivity.KEY_CITY, forecast.city)
            }
            startActivity(i)
        }
    }

    companion object {
        const val KEY_FORECAST = "com.example.openweathermap.view.ForecastActivity.KEY_FORECAST"
    }
}