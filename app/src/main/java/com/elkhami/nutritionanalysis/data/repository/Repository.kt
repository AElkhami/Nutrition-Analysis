package com.elkhami.nutritionanalysis.data.repository

import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.other.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by A.Elkhami on 27,September,2021
 */
interface Repository {
    suspend fun getNutritionForItem(ingr: String)
            : Resource<NutritionalFactsResponse>

    suspend fun processAllNutritionRequests(ingrs: List<String>)
            : Flow<Resource<NutritionalFactsResponse>>

    fun processIngredientDetails(ingr: String): Triple<String, Int, String>

    suspend fun getNutritionForList(request: IngredientsRequest)
            : Resource<NutritionalFactsResponse>
}