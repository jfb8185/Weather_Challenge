package com.example.weatherchallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weatherchallenge.databinding.FragmentResultsBinding

class ResultsFragment: Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding get() = _binding!!

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

        with(binding){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}