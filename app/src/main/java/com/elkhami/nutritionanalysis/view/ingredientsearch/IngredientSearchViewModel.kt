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

    fun validateIngredientsInput(ingredients: CharSequence) {
        _areIngredientsInputValid.value = ingredients.isNotBlank()
    }

    fun convertIngredientsInputToList(ingredients: String) {

        val ingredientList = ingredients.split("\n") as MutableList

        //remove any empty lines
        val iterator = ingredientList.iterator()
        while (iterator.hasNext()) {
            val ingredient = iterator.next()
            if (ingredient == "") {
                iterator.remove()
            }
        }

        _ingredientsList.value = ingredientList
    }

    fun navigationComplete() {
        _ingredientsList.value = null
    }

}