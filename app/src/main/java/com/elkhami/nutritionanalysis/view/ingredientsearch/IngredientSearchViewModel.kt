package com.elkhami.nutritionanalysis.view.ingredientsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by A.Elkhami on 28,September,2021
 */
class IngredientSearchViewModel : ViewModel() {

    private var _areIngredientsInputValid = MutableLiveData<Boolean>()

    val areIngredientsInputValid: LiveData<Boolean> = _areIngredientsInputValid

    private var _ingredientsList = MutableLiveData<List<String>>()

    val ingredientsList: LiveData<List<String>> = _ingredientsList

    fun validateIngredientsInput(ingredients: String) {
        _areIngredientsInputValid.value = ingredients.isNotBlank()
    }

    fun convertIngredientsInputToList(ingredients: String) {

        val ingredientList = ingredients.split("\n")

        _ingredientsList.value = ingredientList
    }


}