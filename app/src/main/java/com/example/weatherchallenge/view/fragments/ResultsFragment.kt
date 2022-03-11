package com.example.weatherchallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherchallenge.adapter.WeatherAdapter
import com.example.weatherchallenge.databinding.FragmentResultsBinding
import com.example.weatherchallenge.network.models.WeatherInfo
import com.example.weatherchallenge.utils.Resource
import com.example.weatherchallenge.viewmodel.WeatherViewModel

class ResultsFragment: Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            resultRv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }

            viewModel.weather.observe(viewLifecycleOwner) { result ->
                val data = (result as? Resource.Success)?.data?.list ?: emptyList()
                resultRv.adapter = WeatherAdapter(data, this@ResultsFragment::onWeatherInfoClick)
            }
        }
    }

    private fun onWeatherInfoClick(weatherInfo: WeatherInfo) {
        viewModel.weatherInfo = weatherInfo
        val directions =
            ResultsFragmentDirections.actionResultsFragmentToDetailsFragment()
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}