package com.example.weatherchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherchallenge.network.models.WeatherInfo
import com.example.weatherchallenge.network.models.WeatherResponse
import com.example.weatherchallenge.network.repository.WeatherRepository
import com.example.weatherchallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val _weather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weather: LiveData<Resource<WeatherResponse>> get() = _weather

    var shouldNavigate = true

    var weatherInfo: WeatherInfo? = null

    fun getWeatherForCity(cityName: String) {
        viewModelScope.launch {
            weatherRepository.getWeatherForCity(cityName).collect {
                _weather.postValue(it)
            }
        }
    }
}