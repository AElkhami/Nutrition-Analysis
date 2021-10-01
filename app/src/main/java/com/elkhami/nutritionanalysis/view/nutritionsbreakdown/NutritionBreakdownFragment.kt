package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.elkhami.nutritionanalysis.R
import com.elkhami.nutritionanalysis.databinding.FragmentNutritionBreakdownBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 29,September,2021
 */
@AndroidEntryPoint
class NutritionBreakdownFragment : Fragment() {

    private val viewModel: NutritionBreakdownViewModel by viewModels()

    private lateinit var binding: FragmentNutritionBreakdownBinding

    private val args: NutritionBreakdownFragmentArgs by navArgs()

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

        val ingredientList = args.ingredientList?.toList() ?: listOf()

        viewModel.getNutritionalDataForIngredients(ingredientList)

        viewModel.nutritionBreakdownList.observe(viewLifecycleOwner, {
            val adapter = NutritionBreakdownAdapter()
            adapter.submitList(it)
            binding.adapter = adapter
        })

        viewModel.errorMessageType.observe(viewLifecycleOwner, {
            println(it)
        })

        viewModel.loadingState.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                startLoading()
            } else {
                stopLoading()
            }
        })

    }

    private fun startLoading() {
        binding.analyseButton.visibility = View.GONE
        binding.nutritionRecyclerView.visibility = View.GONE
        binding.loadingProgressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.analyseButton.visibility = View.VISIBLE
        binding.nutritionRecyclerView.visibility = View.VISIBLE
        binding.loadingProgressBar.visibility = View.GONE
    }
}