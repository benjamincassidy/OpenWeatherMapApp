package com.example.openweathermap.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openweathermap.R
import com.example.openweathermap.data.ForecastItem
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private val FORMATTER = DateTimeFormatter.ofPattern("h:mm a")

class ForecastItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val icon: ImageView = itemView.findViewById(R.id.icon)
    private val condition: TextView = itemView.findViewById(R.id.condition)
    private val temperature: TextView = itemView.findViewById(R.id.temp)
    private val time: TextView = itemView.findViewById(R.id.time)

    fun bind(item: ForecastItem, listener: (forecastItem: ForecastItem) -> Unit) {
        Glide.with(icon.context)
            .load("https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png").into(icon)
        val dateTime = LocalDateTime.ofEpochSecond(item.dt, 0, OffsetDateTime.now().offset)
        itemView.setOnClickListener {
            listener(item)
        }
        time.text = dateTime.format(FORMATTER)
        condition.text = item.weather[0].main
        temperature.text = itemView.resources.getString(R.string.temp, item.main.temp)
    }
}