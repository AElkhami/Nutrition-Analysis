package com.elkhami.nutritionanalysis.data.repository

import androidx.core.text.isDigitsOnly
import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.remote.NutritionAnalysisAPI
import com.elkhami.nutritionanalysis.other.Constants.MAX_CONCURRENT_REQUESTS
import com.elkhami.nutritionanalysis.other.Constants.NETWORK_ERROR
import com.elkhami.nutritionanalysis.other.Constants.UNKNOWN_ERROR
import com.elkhami.nutritionanalysis.other.Resource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by A.Elkhami on 27,September,2021
 */
class NutritionAnalysisRepository @Inject constructor(
    private val api: NutritionAnalysisAPI
) : Repository {

    override suspend fun getNutritionForItem(ingr: String): Resource<NutritionalFactsResponse> {
        return try {

            Resource.Loading<NutritionalFactsResponse>()

            val response = api.getNutritionForItem(ingr = ingr)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Fail(errorMessage = UNKNOWN_ERROR)
            } else {
                return Resource.Fail(errorMessage = response.message())
            }

        } catch (e: Exception) {
            return Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }

    @FlowPreview
    override suspend fun processAllNutritionRequests(ingrs: List<String>)
            : Flow<Resource<NutritionalFactsResponse>> {
        return ingrs
            .asFlow()
            .flatMapMerge(concurrency = MAX_CONCURRENT_REQUESTS) { ingr ->
                flow {
                    val item = getNutritionForItem(ingr)

                    val ingredientDetails = processIngredientDetails(ingr)

                    item.data?.let {
                        it.foodName = ingredientDetails.first
                        it.weight = ingredientDetails.second.toString()
                        it.unit = ingredientDetails.third
                    }
                    emit(item)
                }
            }
    }

    override fun processIngredientDetails(ingr: String): Triple<String, Int, String> {
        val ingredientDetails = ingr.split(" ")

        val foodNameBuilder = StringBuilder()
        var foodName = ""
        var weight = 0
        var unit = ""

        var weightIndex = 0
        var isBeforeDigit = true

        for (i in ingredientDetails.indices) {
            if (ingredientDetails[i].isDigitsOnly()) {
                weight = ingredientDetails[i].toInt()

                weightIndex = i
                isBeforeDigit = false
            } else {
                if (isBeforeDigit) {
                    foodNameBuilder.append(ingredientDetails[i])
                    foodName = foodNameBuilder.toString()
                }
            }
            if (i > weightIndex) {
                unit = ingredientDetails[i]
            }
        }

        return Triple(foodName, weight, unit)
    }

    override suspend fun getNutritionForList(request: IngredientsRequest)
            : Resource<NutritionalFactsResponse> {

        return try {
            Resource.Loading<NutritionalFactsResponse>()

            val response = api.getNutritionForList(body = request)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Fail(errorMessage = UNKNOWN_ERROR)
            } else {
                return Resource.Fail(errorMessage = response.message())
            }

        } catch (e: Exception) {
            return Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }
}