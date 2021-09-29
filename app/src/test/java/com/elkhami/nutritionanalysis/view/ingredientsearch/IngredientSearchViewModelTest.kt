package com.elkhami.nutritionanalysis.view.ingredientsearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by A.Elkhami on 28,September,2021
 */
class IngredientSearchViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: IngredientSearchViewModel

    @Before
    fun setUp() {
        viewModel = IngredientSearchViewModel()
    }

    @Test
    fun `getting valid ingredient input, return true`() {
        viewModel.validateIngredientsInput("Apple 70 g")
        val validation = viewModel.areIngredientsInputValid
        validation.value?.let {
            assertTrue(it)
        }
    }

    @Test
    fun `converting ingredients to list, lines count must be same as list item count, return true`() {

        val lineCount = 3
        val ingredientsInputString = "apple 70 g\norange 50 g\ncarrot 40 g"

        viewModel.convertIngredientsInputToList(ingredientsInputString)

        assertThat(viewModel.ingredientsList.value?.size).isEqualTo(lineCount)

    }

    @Test
    fun `check that _ingredientsList variable is null after navigation is done, return true`(){

         viewModel.navigationComplete()

        assertThat(viewModel.ingredientsList.value).isEqualTo(null)
    }


}