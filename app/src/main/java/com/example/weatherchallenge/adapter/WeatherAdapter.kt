package com.example.weatherchallenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherchallenge.databinding.ItemResultBinding
import com.example.weatherchallenge.network.models.WeatherResponse

class WeatherAdapter(
    private val forecast: List<WeatherResponse>
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemResultBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    class WeatherViewHolder(private val binding: ItemResultBinding):
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(weatherResponse: WeatherResponse) {

            with(binding) {

            }
        }
    }
}