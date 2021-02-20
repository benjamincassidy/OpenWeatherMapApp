package com.example.openweathermap.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermap.R
import com.example.openweathermap.data.Forecast
import com.example.openweathermap.data.ForecastItem
import com.example.openweathermap.viewholders.DateTimeViewHolder
import com.example.openweathermap.viewholders.ForecastItemViewHolder
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.time.OffsetDateTime

private const val VIEW_TYPE_DATE = 0
private const val VIEW_TYPE_ITEM = 1

class ForecastAdapter(
    forecast: Forecast,
    private val listener: (forecastItem: ForecastItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: List<Any>

    init {
        items = mutableListOf()
        val zoneOffset = OffsetDateTime.now().offset
        forecast.list.forEach { item ->
            val dt = LocalDateTime.ofEpochSecond(item.dt, 0, zoneOffset)
            if (items.firstOrNull { it is LocalDateTime && it.dayOfYear == dt.dayOfYear } == null) {
                items.add(dt)
            }
            items.add(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is LocalDateTime -> VIEW_TYPE_DATE
            is ForecastItem -> VIEW_TYPE_ITEM
            else -> throw IllegalArgumentException("Unsupported view type ${items[position]}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_forecast_item, parent, false)
            ForecastItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_day, parent, false)
            DateTimeViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastItemViewHolder -> holder.bind(items[position] as ForecastItem, listener)
            is DateTimeViewHolder -> holder.bind(items[position] as LocalDateTime)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}