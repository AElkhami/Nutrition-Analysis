package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elkhami.nutritionanalysis.R
import com.elkhami.nutritionanalysis.databinding.FragmentNutritionBreakdownBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 29,September,2021
 */
@AndroidEntryPoint
class NutritionBreakdownFragment : Fragment() {

    val viewModel: NutritionBreakdownViewModel by viewModels()

    lateinit var binding: FragmentNutritionBreakdownBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_nutrition_breakdown,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}