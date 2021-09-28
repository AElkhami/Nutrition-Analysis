package com.elkhami.nutritionanalysis.view.ingredientsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elkhami.nutritionanalysis.R
import com.elkhami.nutritionanalysis.databinding.FragmentIngredientSearchBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 28,September,2021
 */
@AndroidEntryPoint
class IngredientSearchFragment: Fragment() {

    private val viewModel: IngredientSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentIngredientSearchBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_ingredient_search,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}