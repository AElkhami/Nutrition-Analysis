package com.elkhami.nutritionanalysis.view.totalnutritionfacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.elkhami.nutritionanalysis.R
import com.elkhami.nutritionanalysis.databinding.FragmentTotalNutritionFactsBinding
import com.elkhami.nutritionanalysis.other.Constants
import com.elkhami.nutritionanalysis.other.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 01,October,2021
 */
@AndroidEntryPoint
class TotalNutritionFactsFragment : Fragment() {

    private val viewModel: TotalNutritionFactsViewModel by viewModels()

    private lateinit var binding: FragmentTotalNutritionFactsBinding

    private val args: TotalNutritionFactsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_total_nutrition_facts,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ingredientList = args.ingredientList?.toList() ?: listOf()

        viewModel.getTotalNutritionalFacts(ingredientList)

        viewModel.totalNutritionDataList.observe(viewLifecycleOwner, {
            val adapter = TotalNutritionFactsAdapter()
            adapter.submitList(it)
            binding.adapter = adapter
            binding.totalNutritionRecyclerView.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.recycler_item_margin),
                    Constants.LINEAR_LAYOUT_RECYCLER_VIEW_SPAN, LinearLayoutManager.VERTICAL
                )
            )

            viewModel.isDataRetrieved = true
        })

        viewModel.errorMessageType.observe(viewLifecycleOwner, {

            val errorString = when (it) {
                Constants.UNKNOWN_ERROR -> {
                    getString(R.string.unknown_error_message)
                }
                Constants.NETWORK_ERROR -> {
                    getString(R.string.network_error_message)
                }
                Constants.LOW_QUALITY_ERROR ->{
                    getString(R.string.low_quality_error_message)
                }
                else -> {
                    it
                }
            }
            Toast.makeText(requireContext(), errorString, Toast.LENGTH_LONG).show()
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
        binding.totalNutritionRecyclerView.visibility = View.GONE
        binding.loadingProgressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.totalNutritionRecyclerView.visibility = View.VISIBLE
        binding.loadingProgressBar.visibility = View.GONE
    }
}