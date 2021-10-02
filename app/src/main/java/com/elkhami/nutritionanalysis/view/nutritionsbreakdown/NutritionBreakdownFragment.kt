package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.elkhami.nutritionanalysis.R
import com.elkhami.nutritionanalysis.databinding.FragmentNutritionBreakdownBinding
import com.elkhami.nutritionanalysis.other.Constants.LINEAR_LAYOUT_RECYCLER_VIEW_SPAN
import com.elkhami.nutritionanalysis.other.Constants.NETWORK_ERROR
import com.elkhami.nutritionanalysis.other.Constants.UNKNOWN_ERROR
import com.elkhami.nutritionanalysis.other.MarginItemDecoration
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
            binding.nutritionRecyclerView.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.recycler_item_margin),
                    LINEAR_LAYOUT_RECYCLER_VIEW_SPAN, LinearLayoutManager.VERTICAL
                )
            )
        })

        viewModel.errorMessageType.observe(viewLifecycleOwner, {

            val errorString = when (it) {
                UNKNOWN_ERROR -> {
                    getString(R.string.unknown_error_message)
                }
                NETWORK_ERROR -> {
                    getString(R.string.network_error_message)
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

        binding.getTotalNutritionButton.setOnClickListener {
            val bundle = Bundle().apply {
                putStringArray("ingredientList", ingredientList.toTypedArray())
            }
            findNavController()
                .navigate(
                    R.id.action_nutritionBreakdownFragment_to_totalNutritionFactsFragment,
                    bundle)

            viewModel.navigationComplete()
        }

    }

    private fun startLoading() {
        binding.getTotalNutritionButton.visibility = View.GONE
        binding.nutritionRecyclerView.visibility = View.GONE
        binding.loadingProgressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.getTotalNutritionButton.visibility = View.VISIBLE
        binding.nutritionRecyclerView.visibility = View.VISIBLE
        binding.loadingProgressBar.visibility = View.GONE
    }
}