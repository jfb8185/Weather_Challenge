package com.example.weatherchallenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherchallenge.R
import com.example.weatherchallenge.databinding.ItemResultBinding
import com.example.weatherchallenge.network.models.WeatherInfo
import com.example.weatherchallenge.network.models.WeatherResponse
import kotlin.math.roundToInt

class WeatherAdapter(
    private val forecast: List<WeatherInfo>,
    private val listener: (WeatherInfo) -> Unit
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    class WeatherViewHolder(
        private val binding: ItemResultBinding,
        private val listener: (WeatherInfo) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherInfo: WeatherInfo) {
            with(binding) {
                mainTv.text = weatherInfo.weather?.get(0)?.main ?: ""
                tempTv.text = String.format(
                    root.context.getString(R.string.temp_with_degrees),
                    weatherInfo.main?.temp?.roundToInt().toString()
                )
                root.setOnClickListener {
                    listener.invoke(weatherInfo)
                }
            }
        }
    }
}