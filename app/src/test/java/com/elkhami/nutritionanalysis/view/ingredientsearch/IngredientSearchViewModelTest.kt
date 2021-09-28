package com.elkhami.nutritionanalysis.view.ingredientsearch

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by A.Elkhami on 28,September,2021
 */
class IngredientSearchViewModelTest {

    private lateinit var viewModel: IngredientSearchViewModel

    @Before
    fun setUp() {
        viewModel = IngredientSearchViewModel()
    }

    @Test
    fun `getting valid ingredient input, return true`(){
        viewModel.validateIngredientsInput("Apple 70 g")
        val validation = viewModel.areIngredientsInputValid
        assertTrue(validation)
    }

    @Test
    fun `converting ingredients to list, lines count must be same as list item count, return true`(){

        val lineCount = 3
        val ingredientsInputString = "apple 70 g\norange 50 g\ncarrot 40 g"

        val ingredientsList= viewModel.convertIngredientsInputToList(ingredientsInputString)

        assertThat(viewModel.ingredientsList.size).isEqualTo(lineCount)

    }


}