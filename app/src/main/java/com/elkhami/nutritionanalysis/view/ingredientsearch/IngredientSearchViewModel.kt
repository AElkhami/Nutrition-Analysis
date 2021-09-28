package com.elkhami.nutritionanalysis.view.ingredientsearch

import androidx.lifecycle.ViewModel
import com.elkhami.nutritionanalysis.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by A.Elkhami on 28,September,2021
 */
@HiltViewModel
class IngredientSearchViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {

    private var _areIngredientsInputValid: Boolean = false

    val areIngredientsInputValid: Boolean
    get() = _areIngredientsInputValid

    private lateinit var _ingredientsList: ArrayList<String>

    val ingredientsList: ArrayList<String>
    get() = _ingredientsList

    fun validateIngredientsInput(ingredients: String){

    }

    fun convertIngredientsInputToList(ingredients: String){

    }




}