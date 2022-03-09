package com.example.weatherchallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherchallenge.databinding.FragmentSearchBinding
import com.example.weatherchallenge.utils.Resource
import com.example.weatherchallenge.utils.showToast
import com.example.weatherchallenge.viewmodel.WeatherViewModel

class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            lookupBtn.setOnClickListener{
                val input = cityET.text.toString()
                cityET.error = if (input.isBlank()) "Invalid Input" else ""

                if (input.isNotBlank())
                    viewModel.getWeatherForCity(input)

                val directions =
                    SearchFragmentDirections.actionSearchFragmentToResultsFragment()
                findNavController().navigate(directions)
            }

            viewModel.weather.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        updateUi(true)
                    }
                    is Resource.Success  -> {
                        updateUi(false)
                        showToast(resource.data.toString())
                    }
                    is Resource.Error -> {
                        updateUi(false)
                        cityET.error = resource.msg
                    }
                }
            }
        }
    }

    private fun updateUi(isLoading: Boolean) {
        with(binding) {
            progressBar.isVisible = isLoading
            cityET.isEnabled = !isLoading
            lookupBtn.isEnabled = !isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}