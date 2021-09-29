package com.elkhami.nutritionanalysis.view.ingredientsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elkhami.nutritionanalysis.R
import com.elkhami.nutritionanalysis.databinding.FragmentIngredientSearchBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 28,September,2021
 */
@AndroidEntryPoint
class IngredientSearchFragment : Fragment() {

    private lateinit var binding: FragmentIngredientSearchBinding
    private val viewModel: IngredientSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_ingredient_search,
                container,
                false
            )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        viewModel.areIngredientsInputValid.observe(viewLifecycleOwner, {
            binding.analyseButton.isEnabled = it
            binding.analyseButton.isClickable = it
        })

        viewModel.ingredientsList.observe(viewLifecycleOwner, { ingredientsList ->
            ingredientsList?.let{
                if (it.isNotEmpty()){
                    val bundle = Bundle().apply {
                        putStringArray("ingredientList", it.toTypedArray())
                    }
                    findNavController()
                        .navigate(
                            R.id.action_ingredientSearchFragment_to_nutritionBreakdownFragment,
                            bundle)

                    viewModel.navigationComplete()
                }
            }
        })
    }
}