package com.example.openweathermap.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermap.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val today = LocalDateTime.now().dayOfYear
private val tomorrow = today + 1

class DateTimeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(dt: LocalDateTime) {
        (itemView as TextView).text = when (dt.dayOfYear) {
            today -> {
                itemView.resources.getString(R.string.today)
            }
            tomorrow -> {
                itemView.resources.getString(R.string.tomorrow)
            }
            else -> {
                val formatter = DateTimeFormatter.ofPattern("EEE MMM dd")
                dt.format(formatter)
            }
        }
    }
}