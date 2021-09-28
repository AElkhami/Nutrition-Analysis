package com.elkhami.nutritionanalysis.view.ingredientsearch

import com.elkhami.nutritionanalysis.data.repository.TestRepository
import org.junit.Assert.*

import org.junit.Before

/**
 * Created by A.Elkhami on 28,September,2021
 */
class IngredientSearchViewModelTest {

    private lateinit var viewModel: IngredientSearchViewModel

    @Before
    fun setUp() {
        viewModel = IngredientSearchViewModel(TestRepository())
    }
}