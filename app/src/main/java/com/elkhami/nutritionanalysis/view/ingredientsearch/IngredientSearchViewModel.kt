package com.elkhami.nutritionanalysis.view.ingredientsearch

import androidx.lifecycle.ViewModel

/**
 * Created by A.Elkhami on 28,September,2021
 */
class IngredientSearchViewModel: ViewModel() {

    private var _areIngredientsInputValid: Boolean = false

    val areIngredientsInputValid: Boolean
    get() = _areIngredientsInputValid

    private lateinit var _ingredientsList: List<String>

    val ingredientsList: List<String>
    get() = _ingredientsList

    fun validateIngredientsInput(ingredients: String){

    }

    fun convertIngredientsInputToList(ingredients: String){

    }




}