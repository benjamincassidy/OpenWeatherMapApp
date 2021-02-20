package com.example.openweathermap.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermap.R
import com.example.openweathermap.data.Forecast
import com.example.openweathermap.data.ForecastItem
import com.example.openweathermap.viewholders.ForecastItemViewHolder

class ForecastAdapter(
    private val forecast: Forecast,
    private val listener: (forecastItem: ForecastItem) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_forecast_item, parent, false)
        return ForecastItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastItemViewHolder -> holder.bind(forecast.list[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return forecast.list.size
    }
}