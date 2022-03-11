package com.example.weatherchallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherchallenge.databinding.FragmentDetailsBinding
import com.example.weatherchallenge.viewmodel.WeatherViewModel
import kotlin.math.roundToInt

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val weather = viewModel.weatherInfo
            weatherTemp.text = weather?.main?.temp?.roundToInt().toString()
            weatherFeel.text = weather?.main?.feelsLike?.roundToInt().toString()
            weatherMain.text = weather?.weather?.get(0)?.main ?: "n/a"
            weatherDesc.text = weather?.weather?.get(0)?.description ?: "n/a"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}