package com.example.openweathermap.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.openweathermap.R
import com.example.openweathermap.databinding.ActivityLookupBinding
import com.example.openweathermap.utils.Status
import com.example.openweathermap.viewmodels.LookupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LookupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLookupBinding
    private val lookupViewModel: LookupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLookupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lookupButton.setOnClickListener {
            lookupViewModel
                .getForecast(binding.cityName.text.toString())
                .observe(this, Observer {
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding.progress.visibility = View.GONE
                            val i = Intent(this, ForecastActivity::class.java).apply {
                                putExtra(ForecastActivity.KEY_FORECAST, it.data as Parcelable)
                            }
                            startActivity(i)
                        }
                        Status.ERROR -> {
                            binding.progress.visibility = View.GONE
                            Toast.makeText(this, R.string.lookup_error, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                    }
                })
        }
    }
}
