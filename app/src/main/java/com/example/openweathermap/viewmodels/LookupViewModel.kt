package com.example.openweathermap.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.openweathermap.data.Forecast
import com.example.openweathermap.service.WeatherService
import com.example.openweathermap.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LookupViewModel @Inject constructor(private val service: WeatherService) : ViewModel() {

    fun getForecast(cityName: String) = liveData (Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(service.getFiveDayForecast(cityName)))
        } catch (ex: Exception) {
            emit(Resource.error(ex, ex.message ?: "Error Occurred!"))
        }
    }
}