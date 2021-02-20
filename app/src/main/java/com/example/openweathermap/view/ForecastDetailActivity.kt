package com.example.openweathermap.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.openweathermap.R
import com.example.openweathermap.data.City
import com.example.openweathermap.data.Forecast
import com.example.openweathermap.data.ForecastItem
import com.example.openweathermap.databinding.ActivityForecastDetailBinding
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ForecastDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityForecastDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        binding = ActivityForecastDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val forecast = intent.getParcelableExtra<ForecastItem>(KEY_FORECAST_ITEM)
        val city = intent.getParcelableExtra<City>(KEY_CITY)
        title = city.name
        binding.temperature.text = getString(R.string.temperature, forecast.main.temp)
        binding.feelsLike.text = getString(R.string.feels_like, forecast.main.feelsLike)
        val forecastDate = LocalDateTime.ofEpochSecond(forecast.dt, 0, OffsetDateTime.now().offset)
        val formatter = DateTimeFormatter.ofPattern("MMMM d\nh:mma")
        binding.dateTime.text = forecastDate.format(formatter)
        if (forecast.weather.isNotEmpty()) {
            binding.condition.text = forecast.weather[0].main
            binding.conditionDescription.text = forecast.weather[0].description
            Glide.with(binding.root.context)
                .load("https://openweathermap.org/img/wn/${forecast.weather[0].icon}@2x.png")
                .into(binding.conditionImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val KEY_FORECAST_ITEM =
            "com.example.openweathermap.view.ForecastDetailActivity.KEY_FORECAST"
        const val KEY_CITY = "com.example.openweathermap.view.ForecastDetailActivity.KEY_CITY"
    }
}